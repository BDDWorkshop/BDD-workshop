package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;

public interface TicketService {

    void allowToBuyTickets(Email email);
    boolean isAllowedToBuyTickets(Email person);

    Basket getBasket(Email email);

    // void addTicketsToBasket(...);
    // void makeTicketsAvailable(...);
}
