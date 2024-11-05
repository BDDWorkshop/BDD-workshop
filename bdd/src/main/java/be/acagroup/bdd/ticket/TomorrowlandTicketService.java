package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TomorrowlandTicketService implements TicketService {

    private final Collection<Email> peopleAllowedToBuyTickets = new ArrayList<>();
    private final Map<Email, Basket> baskets = new HashMap<>();
    private final TicketInventory ticketInventory;

    public TomorrowlandTicketService(TicketInventory ticketInventory) {
        this.ticketInventory = ticketInventory;
    }

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
        Optional<Ticket> ticket = ticketInventory.tryToGetTicket(ticketType);
        ticket.ifPresent(value -> getBasket(email).addTicket(value));
    }

    @Override
    public void removeTicketFromBasket(Email email, TicketType ticketType) {
        getBasket(email).removeTicket(ticketType);
    }

}
