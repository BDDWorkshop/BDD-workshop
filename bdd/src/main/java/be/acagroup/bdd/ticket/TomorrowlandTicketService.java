package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;

import java.util.ArrayList;
import java.util.Collection;

public class TomorrowlandTicketService implements TicketService {

    private final Collection<Email> peopleAllowedToBuyTickets = new ArrayList<>();

    @Override
    public void allowToBuyTickets(Email email) {
        peopleAllowedToBuyTickets.add(email);
    }

    @Override
    public Basket getBasket(Email email) {
        return null;
    }

    @Override
    public boolean isAllowedToBuyTickets(Email email) {
        // naive implementation :)
        return true;
    }
}
