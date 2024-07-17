package com.retail.controller;

import com.retail.model.Bill;
import com.retail.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Double> getNetPayableAmount(@RequestBody Bill bill) {
        double netPayableAmount = discountService.calculateNetPayableAmount(bill);
        return ResponseEntity.ok(netPayableAmount);
    }
}
