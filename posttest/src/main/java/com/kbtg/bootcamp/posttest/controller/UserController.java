package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.dto.LotteryResponse;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final LotteryService lotteryService;

    public UserController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<LotteryResponse> getLotteries(
            @PathVariable
            @NotBlank
            @NotNull
            String userId) {
        LotteryResponse responseBody = lotteryService.getLotteryByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Map<String, String>> buyLottery(
            @PathVariable
            @NotBlank
            @NotNull
            String userId,
            @PathVariable
            @NotBlank
            @NotNull
            String ticketId) {
        String id = lotteryService.buyLottery(userId, ticketId);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", id);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Map<String, String>> deleteLottery(
            @PathVariable
            @NotBlank
            @NotNull
            String userId,
            @PathVariable
            @NotBlank
            @NotNull
            String ticketId) {
        String id = lotteryService.deleteLottery(userId, ticketId);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("ticket", id);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
