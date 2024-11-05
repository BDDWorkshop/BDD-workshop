package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static be.acagroup.bdd.ticket.TicketType.COMBI;
import static be.acagroup.bdd.ticket.TicketType.DAY;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTicketStepDefinitions {

    private final TicketService ticketService = new TomorrowlandTicketService();

    @Given("{email} is allowed to buy tickets")
    public void isAllowedToBuyTickets(Email email) {
        ticketService.allowToBuyTickets(email);
    }

    @Given("{email} is not allowed to buy tickets")
    public void isNotAllowedToBuyTickets(Email email) {
    }

    @Then("{} has an empty basket")
    public void hasEnEmptyBasket(String email) {
        assertThat(ticketService.getBasket(new Email(email)).getAllTickets()).isEmpty();
    }

    @Then("{email} has no basket")
    public void hasNoBasket(Email email) {
        assertThat(ticketService.getBasket(email)).isNull();
    }

    @When("{email} adds a ticket to the basket")
    public void addTicketToBasket(Email email) {
        ticketService.addTicketToBasket(email, COMBI);
    }

    @Then("The basket of {email} contains {int} ticket")
    public void numberOfTicketsInTheBasket(Email email, int numberOfTickets) {
        assertThat(ticketService.getBasket(email).getAllTickets()).hasSize(numberOfTickets);
    }

    @Then("The basket of {email} contains {int} {ticketType} ticket")
    public void numberOfCombiTicketsInTheBasket(Email email, int numberOfTickets, TicketType ticketType) {
        assertThat(ticketService.getBasket(email).getAllTickets()).filteredOn(ticket -> ticket.type() == ticketType).hasSize(numberOfTickets);
    }

    @When("{email} adds a combi ticket to the basket")
    public void addCombiTicket(Email email) {
        ticketService.addTicketToBasket(email, COMBI);
    }

    @When("{email} adds a day ticket to the basket")
    public void addDayTicket(Email email) {
        ticketService.addTicketToBasket(email, DAY);
    }

}
