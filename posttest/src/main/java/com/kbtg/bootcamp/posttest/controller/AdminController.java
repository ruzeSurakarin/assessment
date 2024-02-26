package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.dto.LotteryRequest;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private final LotteryService lotteryService;

    public AdminController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/lotteries")
    public ResponseEntity<Map<String, String>> addLottery(@Valid @RequestBody LotteryRequest request) {
        Lottery lottery = lotteryService.addLottery(request);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("ticket", lottery.getTicketId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
