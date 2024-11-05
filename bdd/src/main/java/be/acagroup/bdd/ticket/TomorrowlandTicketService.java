package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TomorrowlandTicketService implements TicketService {

    private final Collection<Email> peopleAllowedToBuyTickets = new ArrayList<>();
    private final Map<Email, Basket> baskets = new HashMap<>();
    private final Map<TicketType, BigDecimal> pricings = new HashMap<>();
    private final Map<TicketType, Integer> availableTickets = new HashMap<>();

    @Override
    public void allowToBuyTickets(Email email) {
        peopleAllowedToBuyTickets.add(email);
    }

    @Override
    public boolean isAllowedToBuyTickets(Email email) {
        return peopleAllowedToBuyTickets.contains(email);
    }

    @Override
    public Basket getBasket(Email email) {
        if (peopleAllowedToBuyTickets.contains(email)) {
            return baskets.computeIfAbsent(email, k -> new Basket());
        }
        return null;
    }

    @Override
    public void addTicketToBasket(Email email, TicketType ticketType) {
        if (availableTickets.getOrDefault(ticketType, 0) > 0) {
            availableTickets.computeIfPresent(ticketType, (k, currentAvailability) -> currentAvailability - 1);
            getBasket(email).addTicket(new Ticket(ticketType, pricings.get(ticketType)));
        }
    }

    @Override
    public void removeTicketFromBasket(Email email, TicketType ticketType) {
        getBasket(email).removeTicket(ticketType);
    }

    @Override
    public void makeTicketsAvailable(int numberOfTickets, TicketType ticketType) {
        this.availableTickets.putIfAbsent(ticketType, 0);
        this.availableTickets.compute(ticketType, (k, currentAvailability) -> currentAvailability + numberOfTickets);
    }

    @Override
    public void setPricing(TicketType ticketType, BigDecimal price) {
        this.pricings.put(ticketType, price);
    }
}
