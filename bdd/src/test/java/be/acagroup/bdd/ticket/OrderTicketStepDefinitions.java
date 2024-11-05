package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static be.acagroup.bdd.ticket.TicketType.COMBI;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTicketStepDefinitions {

    private final TicketService ticketService = new TomorrowlandTicketService();

    @Given("{email} is allowed to buy tickets")
    public void makeAllowedToBuyTickets(Email email) {
        ticketService.allowToBuyTickets(email);
    }

    @Given("{email} is not allowed to buy tickets")
    public void makeNotAllowedToBuyTickets(Email email) {
    }

    @Then("{} has an empty basket")
    public void assertEmptyBasket(String email) {
        assertThat(ticketService.getBasket(new Email(email)).getAllTickets()).isEmpty();
    }

    @Then("{email} has no basket")
    public void assertNoBasket(Email email) {
        assertThat(ticketService.getBasket(email)).isNull();
    }

    @When("{email} adds a ticket to the basket")
    public void addTicketToBasket(Email email) {
        addTicket(email, COMBI);
    }

    @When("{email} adds a {ticketType} ticket to the basket")
    public void addTicket(Email email, TicketType ticketType) {
        ticketService.addTicketToBasket(email, ticketType);
    }

    @When("{email} adds {int} {ticketType} ticket(s) to the basket")
    public void addMultipleTickets(Email email, int numberOfTickets, TicketType ticketType) {
        for (int i = 0; i < numberOfTickets; i++) {
            ticketService.addTicketToBasket(email, ticketType);
        }
    }

    @When("{email} removes a {ticketType} ticket from the basket")
    public void removeTicket(Email email, TicketType ticketType) {
        ticketService.removeTicketFromBasket(email, ticketType);
    }

    @Then("The basket of {email} contains {int} ticket(s)")
    public void assertNumberOfTicketsInTheBasket(Email email, int numberOfTickets) {
        assertThat(ticketService.getBasket(email).getAllTickets()).hasSize(numberOfTickets);
    }

    @Then("The basket of {email} contains {int} {ticketType} ticket(s)")
    public void assertNumberOfTypedTickets(Email email, int numberOfTickets, TicketType ticketType) {
        assertThat(ticketService.getBasket(email).getAllTickets()).filteredOn(ticket -> ticket.type() == ticketType).hasSize(numberOfTickets);
    }

    @And("{ticketType} tickets are priced at {bigdecimal} euro")
    public void setTicketPricing(TicketType ticketType, BigDecimal price) {
        ticketService.setPricing(ticketType, price);
    }

    @Then("The total price of the basket of {email} is {bigdecimal} euro")
    public void assertTotalBasketPrice(Email email, BigDecimal price) {
        assertEqualPrices(ticketService.getBasket(email).getTotalPrice(), price);
    }

    private void assertEqualPrices(BigDecimal actualPrice, BigDecimal expectedPrice) {
        assertThat(actualPrice.setScale(2, RoundingMode.FLOOR))
                .isEqualTo(expectedPrice.setScale(2, RoundingMode.FLOOR));
    }

    @When("the ticket service makes {int} {ticketType} ticket(s) available")
    public void makeTicketsAvailable(int numberOfTickets, TicketType ticketType) {
        ticketService.makeTicketsAvailable(numberOfTickets, ticketType);
    }
}
