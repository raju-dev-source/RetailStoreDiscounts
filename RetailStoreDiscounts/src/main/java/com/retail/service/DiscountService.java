package com.retail.service;

import com.retail.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to handle the calculation of net payable amount after applying discounts.
 */
@Service
public class DiscountService {
    private final DiscountCalculator discountCalculator;

    /**
     * Constructor for injecting the DiscountCalculator dependency.
     *
     * @param discountCalculator The DiscountCalculator instance to be used for calculating discounts.
     */
@Autowired
    public DiscountService(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    /**
     * Calculates the net payable amount after applying discounts to the given bill.
     *
     * @param bill The bill for which the net payable amount needs to be calculated.
     * @return The net payable amount after subtracting the applicable discount from the total amount.
     */

    public double calculateNetPayableAmount(Bill bill) {
        double discount = discountCalculator.calculateDiscount(bill);
        return bill.getTotalAmount() - discount;
    }
}
