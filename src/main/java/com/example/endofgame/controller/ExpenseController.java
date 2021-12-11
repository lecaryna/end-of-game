package com.example.endofgame.controller;

import com.example.endofgame.dto.ExpenseSummary;
import com.example.endofgame.entity.Expense;
import com.example.endofgame.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService service;


    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/expenses")
    public List<ExpenseSummary> allExpenses() {
        log.info("endpoint: /expenses");

        return service.readAllExpenses();
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseSummary> findExpenseById(@PathVariable("id") Long myId) {
        log.info("trying to find expense by id: [{}]", myId);

        var readExpenseSummary = service.readExpenseById(myId);
        var result = ResponseEntity.notFound().<ExpenseSummary>build();
        if (readExpenseSummary.isPresent()) {
            result = ResponseEntity.ok(readExpenseSummary.get());
        }
        return result;
    }
}
