package com.darkhorse.feidegao.userinterface;

import com.darkhorse.feidegao.applicationservice.OrderApplicationService;
import com.darkhorse.feidegao.applicationservice.exception.AppException;
import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainmodel.PaymentRequest;
import com.darkhorse.feidegao.userinterface.command.CreateOrderCommand;
import com.darkhorse.feidegao.userinterface.representation.OrderRepresentation;
import com.darkhorse.feidegao.userinterface.representation.PaymentRequestRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderApiUserInterface {

    private final OrderApplicationService orderApplicationService;

    public OrderApiUserInterface(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping(path = "/proposals/{pid}/orders")
    public ResponseEntity<OrderRepresentation> createOrder(@PathVariable String pid,
                                                           @RequestBody CreateOrderCommand command) {
        // use manual validation because putting lots of logic into a single place it's easier to manage and maintain.
        // TODO: use exception handler to handle exception and error code.
        if (!command.validate()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Order order = orderApplicationService.createOrder(pid, command.getContactor(), command.getPassengers());
            return new ResponseEntity<>(OrderRepresentation.from(order), HttpStatus.CREATED);
        } catch (Exception exception) {
            if (exception instanceof AppException) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @PostMapping(path = "/orders/{orderId}/payment")
    public ResponseEntity<PaymentRequestRepresentation> requestPayment(@PathVariable String orderId, @RequestParam String method) {

        PaymentRequest paymentRequest = orderApplicationService.requestPayment(orderId, method);

        return new ResponseEntity<>(PaymentRequestRepresentation.from(paymentRequest), HttpStatus.CREATED);
    }
}
