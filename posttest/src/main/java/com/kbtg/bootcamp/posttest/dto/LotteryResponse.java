package com.kbtg.bootcamp.posttest.dto;

import java.util.List;

public class LotteryResponse {
    private List<String> tickets;
    private int count;
    private Double cost;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
