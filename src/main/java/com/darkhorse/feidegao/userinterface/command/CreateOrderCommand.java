package com.darkhorse.feidegao.userinterface.command;

import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Passenger;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateOrderCommand {
    private ContactorCommand contactor;
    private List<PassengerCommand> passengers;

    public Contactor getContactor() {
        return contactor.to();
    }

    public List<Passenger> getPassengers() {
        return passengers.stream().map(PassengerCommand::to).collect(Collectors.toList());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class ContactorCommand {
        private String phoneNumber;

        public Contactor to() {
           return new Contactor(UUID.randomUUID().toString(), phoneNumber);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class PassengerCommand {
        private String name;
        private String idNumber;

        public Passenger to() {
            return new Passenger(UUID.randomUUID().toString(), name, idNumber);
        }
    }
}
