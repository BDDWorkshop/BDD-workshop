package be.acagroup.bdd;

import be.acagroup.bdd.queue.QueuePosition;
import be.acagroup.bdd.ticket.TicketType;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("\\d+")
    public QueuePosition position(String position) {
        return new QueuePosition(Integer.parseInt(position));
    }

    @ParameterType(".*")
    public Email email(String email) {
        return new Email(email);
    }

    @ParameterType(".*")
    public TicketType ticketType(String ticketType) {
        return TicketType.valueOf(ticketType.toUpperCase());
    }
}
