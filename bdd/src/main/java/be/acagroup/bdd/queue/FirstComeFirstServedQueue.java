package be.acagroup.bdd.queue;

import be.acagroup.bdd.Email;
import be.acagroup.bdd.ticket.TicketService;

import java.util.ArrayList;
import java.util.List;

public class FirstComeFirstServedQueue implements WaitingQueue {

    private final List<Email> waitingList = new ArrayList<>();
    private final TicketService ticketService;

    public FirstComeFirstServedQueue(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void enterTheQueue(Email email) {
        waitingList.add(email);
    }

    @Override
    public QueuePosition getPosition(Email email) {
        if (waitingList.contains(email)) {
            return new QueuePosition(waitingList.indexOf(email) + 1);
        }
        return null;
    }

    @Override
    public void allowPeopleToBuyTickets(int numberOfPeople) {
        for (int i = 0; i < numberOfPeople; i++) {
            allowNext();
        }
    }

    private void allowNext() {
        var firstInQueue = waitingList.get(0);
        ticketService.allowToBuyTickets(firstInQueue);
        waitingList.remove(firstInQueue);
    }
}
