package be.acagroup.bdd.ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Ticket> tickets = new ArrayList<>();

     List<Ticket> getAllTickets() {
         return tickets;
     }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public BigDecimal getTotalPrice() {
        return tickets.stream().map(Ticket::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removeTicket(TicketType ticketType) {
        var ticketToRemove = tickets.stream()
                .filter(ticket -> ticket.type() == ticketType)
                .findFirst();
        ticketToRemove.ifPresent(tickets::remove);
    }
}
