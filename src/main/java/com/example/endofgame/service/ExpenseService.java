package com.example.endofgame.service;

import com.example.endofgame.converter.ExpensesConverter;
import com.example.endofgame.dto.ExpenseSummary;
import com.example.endofgame.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {

    private final ExpenseRepository repository;

    private final ExpensesConverter converter;


    public ExpenseService(final ExpenseRepository repository, final ExpensesConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<ExpenseSummary> readAllExpenses() {
        log.info("read all expenses");

        var result = repository.findAll()
                .stream()
                .map(converter::fromEntityToDto)
                .toList();
        log.info("number of read expenses: [{}]", result.size());
        log.debug("result: {}", result);

        return result;
    }
    public Optional<ExpenseSummary> readExpenseById(Long myId) {
        var result = repository.findById(myId);
        log.info("item with id: [{}] exists? - [{}]", myId, result.isPresent());
        log.debug("received expense: [{}]", result.orElse(null));
        return result.map(expense -> converter.fromEntityToDto(expense));

    }
}
