package com.kbtg.bootcamp.posttest.dto;

import jakarta.validation.constraints.*;

public record LotteryRequest(
        @NotBlank(message = "Lottery number should not be null.")
        @Size(min = 6, max = 6, message = "Lottery number should have 6 characters.")
        String ticket
        ,
        @NotNull(message = "Price should not be null.")
        @Positive(message = "Price should be positive number.")
        Integer price
        ,
        @NotNull(message = "Amount should not be null.")
        @Positive(message = "Amount should be positive number.")
        Integer amount){
}
