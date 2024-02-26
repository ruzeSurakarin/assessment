package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @Column(name = "ticket_id", nullable = false, length = 6)
    private String ticketId;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "amount", nullable = false)
    private int amount;

    public Lottery() {}

    public Lottery(String ticketId, int price, int amount) {
        this.ticketId = ticketId;
        this.price = price;
        this.amount = amount;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
