package be.acagroup.bdd.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {

    private final List<Ticket> tickets = new ArrayList<>();

    // BigDecimal getTotalValue()
     List<Ticket> getAllTickets() {
         return tickets;
     }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
