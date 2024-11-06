package be.acagroup.bdd.queue;

import be.acagroup.bdd.Email;

public interface WaitingQueue {

    void enterTheQueue(Email mail);

    void allowPeopleToBuyTickets(int numberOfPeople);

    QueuePosition getPosition(Email email);
}
