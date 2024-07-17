package com.retail.service;


import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DiscountServiceTest {

    @Test
    public void testCalculateNetPayableAmount() {
        DiscountCalculator discountCalculator = mock(DiscountCalculator.class);
        User user = new User(1L, UserType.EMPLOYEE, LocalDate.now().minusYears(1));
        Bill bill = new Bill(1000, user);
        when(discountCalculator.calculateDiscount(bill)).thenReturn(350.0);

        DiscountService discountService = new DiscountService(discountCalculator);
        double netPayableAmount = discountService.calculateNetPayableAmount(bill);
        assertEquals(650.0, netPayableAmount);
    }
}
