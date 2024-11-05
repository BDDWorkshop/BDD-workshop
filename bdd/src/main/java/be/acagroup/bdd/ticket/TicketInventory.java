package be.acagroup.bdd.ticket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketInventory {

    private final Map<TicketType, Integer> availableTickets = new HashMap<>();
    private final Map<TicketType, BigDecimal> pricings = new HashMap<>();

    public synchronized Optional<Ticket> tryToGetTicket(TicketType ticketType) {
        if (availableTickets.getOrDefault(ticketType, 0) > 0) {
            availableTickets.computeIfPresent(ticketType, (k, currentAvailability) -> currentAvailability - 1);
            return Optional.of(new Ticket(ticketType, pricings.get(ticketType)));
        }
        return Optional.empty();
    }

    public void makeTicketsAvailable(int numberOfTickets, TicketType ticketType) {
        this.availableTickets.putIfAbsent(ticketType, 0);
        this.availableTickets.compute(ticketType, (k, currentAvailability) -> currentAvailability + numberOfTickets);
    }

    public void setPricing(TicketType ticketType, BigDecimal price) {
        this.pricings.put(ticketType, price);
    }


}
