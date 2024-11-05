package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;

import java.math.BigDecimal;

public interface TicketService {

    void allowToBuyTickets(Email email);
    boolean isAllowedToBuyTickets(Email person);

    Basket getBasket(Email email);

    void addTicketToBasket(Email email, TicketType ticketType);

    void removeTicketFromBasket(Email email, TicketType ticketType);

}
