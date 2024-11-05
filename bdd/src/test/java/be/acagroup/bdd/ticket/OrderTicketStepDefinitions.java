package be.acagroup.bdd.ticket;

import be.acagroup.bdd.Email;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    @And("{ticketType} tickets are priced at {bigdecimal} euro")
    public void ticketsArePriced(TicketType ticketType, BigDecimal price) {
        ticketService.setPricing(ticketType, price);
    }

    @Then("The total price of the basket of {email} is {bigdecimal} euro")
    public void theTotalPriceOfTheBasketOfBddAcagroupBeIsEuro(Email email, BigDecimal price) {
        assertEqualPrices(ticketService.getBasket(email).getTotalPrice(), price);
    }

    private void assertEqualPrices(BigDecimal actualPrice, BigDecimal expectedPrice) {
        assertThat(actualPrice.setScale(2, RoundingMode.FLOOR)).isEqualTo(expectedPrice.setScale(2, RoundingMode.FLOOR));
    }

    @When("{email} removes a {ticketType} ticket to the basket")
    public void removeATicketFromTheBasket(Email email, TicketType ticketType) {
        ticketService.removeTicketFromBasket(email, ticketType);
    }
}
