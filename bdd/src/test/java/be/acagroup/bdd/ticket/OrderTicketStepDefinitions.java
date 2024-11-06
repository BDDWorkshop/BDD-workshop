package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTicketStepDefinitions {

    private final TicketService ticketService = new TomorrowlandTicketService();

    @When("{} is allowed to buy tickets")
    public void someoneIsAllowedToBuyTickets(String email) {
        assertThat(ticketService.isAllowedToBuyTickets(new Email(email))).isTrue();
    }

}
