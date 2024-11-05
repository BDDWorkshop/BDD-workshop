package be.acagroup.bdd.queue;

import be.acagroup.bdd.Email;
import be.acagroup.bdd.ticket.TicketService;
import be.acagroup.bdd.ticket.TomorrowlandTicketService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueStepDefinitions {

    private final TicketService ticketService = new TomorrowlandTicketService(null);
    private final WaitingQueue waitingQueue = new FirstComeFirstServedQueue(ticketService);

    @When("{} enters the queue")
    public void someoneEntersTheQueue(String email) {
        waitingQueue.enterTheQueue(new Email(email));
    }

    @Given("{} is in the queue")
    public void someoneIsInTheQueue(String email) {
        waitingQueue.enterTheQueue(new Email(email));
    }

    @Then("{} is not in the queue")
    public void isNotInTheQueue(String email) {
        assertThat(waitingQueue.getPosition(new Email(email))).isNull();
    }

    @When("{} enters the queue again")
    public void enterTheQueueAgain(String email) {
        waitingQueue.enterTheQueue(new Email(email));
    }

    @Then("{email} is assigned number {position} in the queue")
    public void isAssignedNumberInTheQueue(Email email, QueuePosition expectedPosition) {
        var actualPosition = waitingQueue.getPosition(email);
        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    @Then("{email} is still assigned number {position} in the queue")
    public void isStillAssignedNumberInTheQueue(Email email, QueuePosition expectedPosition) {
        isAssignedNumberInTheQueue(email, expectedPosition);
    }

    @When("the registration queue allows {int} people/person to start buying tickets")
    public void allowPeopleToBuyTickets(int numberOfTickets) {
        waitingQueue.allowPeopleToBuyTickets(numberOfTickets);
    }

    @Then("{} is allowed to buy tickets in the ticket service")
    public void isAllowedToBuyTickets(String email) {
        assertThat(ticketService.isAllowedToBuyTickets(new Email(email))).isTrue();
    }

    @Then("{} is not allowed to buy tickets in the ticket service")
    public void notAllowedToBuyTicketsInTheTicketService(String email) {
        assertThat(ticketService.isAllowedToBuyTickets(new Email(email))).isFalse();
    }
}
