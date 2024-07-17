package com.retail.service;

import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void testCalculateDiscountForEmployee() {
        User user = new User(1L, UserType.EMPLOYEE, LocalDate.now().minusYears(1));
        Bill bill = new Bill(1000, user);
        DiscountCalculator discountCalculator = new DiscountCalculator();
        double discount = discountCalculator.calculateDiscount(bill);
        assertEquals(350.0, discount); // 30% of 1000 + $5 for every $100
    }

    @Test
    public void testCalculateDiscountForAffiliate() {
        User user = new User(2L, UserType.AFFILIATE, LocalDate.now().minusYears(1));
        Bill bill = new Bill(1000, user);
        DiscountCalculator discountCalculator = new DiscountCalculator();
        double discount = discountCalculator.calculateDiscount(bill);
        assertEquals(150.0, discount); // 10% of 1000 + $5 for every $100
    }

    @Test
    public void testCalculateDiscountForLoyalCustomer() {
        User user = new User(3L, UserType.CUSTOMER, LocalDate.now().minusYears(3));
        Bill bill = new Bill(1000, user);
        DiscountCalculator discountCalculator = new DiscountCalculator();
        double discount = discountCalculator.calculateDiscount(bill);
        assertEquals(100.0, discount); // 5% of 1000 + $5 for every $100
    }

}
