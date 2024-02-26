package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.dto.LotteryRequest;
import com.kbtg.bootcamp.posttest.dto.LotteryResponse;
import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class LotteryService {

    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    public LotteryService(LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    public Lottery addLottery(LotteryRequest request) {
        Lottery lottery = new Lottery(request.ticket(), request.price(), request.amount());
        return lotteryRepository.save(lottery);
    }

    public List<String> getLotteries() {
        List<Lottery> lotteries = lotteryRepository.findAll();
        return lotteries.stream().map(Lottery::getTicketId).toList();
    }

    private void validateEmpty(String id, String name) {
        if (id.isEmpty() || id.isBlank()) {
            throw new BadRequestException(name + " should not be null.");
        }
    }

    private void validateLength(String id, int length, String name) {
        if (id.length() != length) {
            throw new BadRequestException(name + " should have 10 characters.");
        }
    }

    private Lottery getTicketById(String id) {
        Optional<Lottery> ticket = lotteryRepository.findById(id);
        if(ticket.isEmpty()){
            throw new NotFoundException("Ticket id does not exist.");
        }

        return ticket.get();
    }

    private void validateUserId(String userId) {
        validateEmpty(userId, "User id");
        validateLength(userId, 10, "User id");
    }

    private void validate(String userId, String ticketId) {
        validateUserId(userId);

        validateEmpty(ticketId, "Ticket id");
        validateLength(ticketId, 6, "Ticket id");
    }

    public String buyLottery(String userId, String ticketId) {
        validate(userId, ticketId);

        Lottery ticket = getTicketById(ticketId);
        UserTicket userTicket = userTicketRepository.save(new UserTicket(userId, ticket));

        return userTicket.getId().toString();
    }

    public String deleteLottery(String userId, String ticketId) {
        validate(userId, ticketId);

        List<UserTicket> tickets = userTicketRepository
                .findTicketsByUserid(userId)
                .stream()
                .filter(t -> Objects.equals(t.getTicket().getTicketId(), ticketId))
                .toList();

        userTicketRepository.deleteAll(tickets);

        return ticketId;
    }

    public LotteryResponse getLotteryByUserId(String userId) {
        validateUserId(userId);

        List<UserTicket> tickets = userTicketRepository.findTicketsByUserid(userId);
        List<String> ids = tickets.stream()
                .map(UserTicket::getTicket)
                .map(Lottery::getTicketId)
                .toList();

        double totalPrice = tickets.stream()
                .mapToDouble(ticket -> ticket.getTicket().getAmount() * ticket.getTicket().getPrice())
                .sum();

        LotteryResponse response = new LotteryResponse();
        response.setTickets(ids);
        response.setCost(totalPrice);
        response.setCount(tickets.size());

        return response;
    }
}
