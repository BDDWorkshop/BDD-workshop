package be.acagroup.bdd;

import be.acagroup.bdd.queue.QueuePosition;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("\\d+")
    public QueuePosition position(String position) {
        return new QueuePosition(Integer.parseInt(position));
    }
}
