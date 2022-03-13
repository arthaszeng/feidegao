package com.darkhorse.feidegao.userinterface.command;

import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isEmpty;

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

    public boolean validate() {
        if (contactor == null || isEmpty(contactor.phoneNumber) || contactor.phoneNumber.length() != 11) {
            return false;
        }

        return passengers != null
                && !passengers.isEmpty()
                && passengers.stream().noneMatch(a -> a.getIdNumber().isEmpty())
                && passengers.stream().map(PassengerCommand::getIdNumber).distinct().count() == passengers.size();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class ContactorCommand {
        @NotBlank
        @NotEmpty
        @NotNull
        private String phoneNumber;

        public Contactor to() {
            return new Contactor(UUID.randomUUID().toString(), phoneNumber);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class PassengerCommand {
        private String name;
        private String idNumber;

        public Passenger to() {
            return new Passenger(UUID.randomUUID().toString(), name, idNumber);
        }
    }
}
