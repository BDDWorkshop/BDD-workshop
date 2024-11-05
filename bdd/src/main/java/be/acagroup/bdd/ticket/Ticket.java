package be.acagroup.bdd.ticket;

import java.math.BigDecimal;

public record Ticket(TicketType type, BigDecimal price) {
}
