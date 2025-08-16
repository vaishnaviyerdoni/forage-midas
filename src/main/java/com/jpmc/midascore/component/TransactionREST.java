package com.jpmc.midascore.component;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jpmc.midascore.foundation.Balance;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TransactionREST {

    private TransactionService service;

    public TransactionREST(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam long userId) {
        return service.getBalancebyID(userId);
    }
}
