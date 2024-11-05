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
    private Map<TicketType, BigDecimal> pricings = new HashMap<>();

    @Override
    public void allowToBuyTickets(Email email) {
        peopleAllowedToBuyTickets.add(email);
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
        getBasket(email).addTicket(new Ticket(ticketType, pricings.get(ticketType)));
    }

    @Override
    public void setPricing(TicketType ticketType, BigDecimal price) {
        this.pricings.put(ticketType, price);
    }

    @Override
    public boolean isAllowedToBuyTickets(Email email) {
        return peopleAllowedToBuyTickets.contains(email);
    }
}
