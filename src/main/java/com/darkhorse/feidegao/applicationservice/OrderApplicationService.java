package com.darkhorse.feidegao.applicationservice;


import com.darkhorse.feidegao.applicationservice.exception.MismatchedFlightInfoException;
import com.darkhorse.feidegao.applicationservice.exception.MismatchedPositionAndPriceException;
import com.darkhorse.feidegao.domainmodel.*;
import com.darkhorse.feidegao.domainservice.OrderDomainService;
import com.darkhorse.feidegao.domainservice.externalservice.FlightInfoService;
import com.darkhorse.feidegao.domainservice.externalservice.PaymentGateway;
import com.darkhorse.feidegao.domainservice.externalservice.PositionAndPriceService;
import com.darkhorse.feidegao.domainservice.externalservice.model.PaymentRequestResponse;
import com.darkhorse.feidegao.domainservice.externalservice.model.RequestOfPaymentRequest;
import com.darkhorse.feidegao.domainservice.repository.OrderRepository;
import com.darkhorse.feidegao.domainservice.repository.ProposalRepository;
import com.darkhorse.feidegao.domainmodel.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderApplicationService {

    private PaymentGateway paymentGateway;
    private OrderDomainService orderDomainService;
    private ProposalRepository proposalRepository;
    private PositionAndPriceService positionAndPriceService;
    private FlightInfoService flightInfoService;
    private OrderRepository orderRepository;

    public Order createOrder(String proposalId, Contactor contactor, List<Passenger> passengers) {
        Proposal proposal = proposalRepository.getProposalById(proposalId);
        String flightId = proposal.getFlightInfo().getId();

        verifyFlightInfo(proposal, flightId);
        verifyPositionAndPrice(proposal, flightId);

        Order order = orderDomainService.createOrder(proposal, passengers, contactor);

        orderRepository.save(order);

        return order;
    }

    public PaymentRequest requestPayment(String orderId, String method) {
        Order order = orderRepository.getOrderById(orderId);

        PaymentRequestResponse paymentRequestResponse = paymentGateway.requestPayment(new RequestOfPaymentRequest(order.getPrice(), "/orders/"+ orderId+"/payment", method));

        Order updatedOrder = orderDomainService.requestPayment(order, paymentRequestResponse.to());

        orderRepository.save(updatedOrder);

        return order.getPaymentRequest();
    }

    private void verifyPositionAndPrice(Proposal proposal, String flightId) {
        boolean noneMatch = positionAndPriceService.getPriceAndPositionsById(flightId).stream()
                .noneMatch(positionAndPrice -> positionAndPrice.getClassType().equals(proposal.getPositionAndPrice().getClassType())
                        && positionAndPrice.getPrice() == proposal.getPositionAndPrice().getPrice()
                        && positionAndPrice.getAvailableAmount() >= proposal.getPositionAndPrice().getAvailableAmount());

        if (noneMatch) {
            throw new MismatchedPositionAndPriceException();
        }
    }

    private void verifyFlightInfo(Proposal proposal, String flightId) {
        FlightInfo flightInfo = flightInfoService.getFlightInfoById(flightId);

        if (!flightInfo.equals(proposal.getFlightInfo())) {
            throw new MismatchedFlightInfoException();
        }
    }
}
