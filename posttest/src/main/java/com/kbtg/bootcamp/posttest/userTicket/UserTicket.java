package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import jakarta.persistence.*;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Lottery ticket;

    public UserTicket() {}

    public UserTicket(String userId, Lottery ticket) {
        this.userId = userId;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Lottery getTicket() {
        return ticket;
    }
}
