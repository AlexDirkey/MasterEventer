package org.example.mastereventer.Objects;

import java.util.UUID;

public class Ticket {
    private final String id;
    private String description;  // Evt. billettype eller beskrivelse
    private String customerName;
    private String customerEmail;

    public Ticket(String description, String customerName, String customerEmail) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    // Du kan evt. tilføje setters, hvis det er nødvendigt
}
