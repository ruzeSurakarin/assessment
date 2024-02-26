package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {
    LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("")
    public ResponseEntity<List<String>> getLotteryList() {
        return ResponseEntity.status(HttpStatus.OK).body(lotteryService.getLotteries());
    }
}
