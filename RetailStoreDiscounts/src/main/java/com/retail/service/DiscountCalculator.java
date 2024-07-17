package com.retail.service;


import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Service component to calculate discounts based on a given bill and user information.
 */

@Component
public class DiscountCalculator {

    /**
     * Calculates the total discount applicable to the given bill based on user type and bill amount.
     *
     * @param bill The bill containing total amount and user information.
     * @return The calculated discount amount.
     */
    public double calculateDiscount(Bill bill) {
        double discount = 0.0;
        User user = bill.getUser();
        double totalAmount = bill.getTotalAmount();

        discount += (int) (totalAmount / 100) * 5;


        if (!isGrocery(bill)) {
            if (user.getUserType() == UserType.EMPLOYEE) {
                discount += totalAmount * 0.30;
            } else if (user.getUserType() == UserType.AFFILIATE) {
                discount += totalAmount * 0.10;
            } else if (isLoyalCustomer(user)) {
                discount += totalAmount * 0.05;
            }
        }

        return discount;
    }

    /**
     * Checks if the user is a loyal customer based on their registration date.
     *
     * @param user The user for whom loyalty status is checked.
     * @return {@code true} if the user has been registered for more than 2 years, otherwise {@code false}.
     */
    private boolean isLoyalCustomer(User user) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(user.getRegistrationDate(), now);
        return period.getYears() > 2;
    }

    /**
     * Placeholder method to implement grocery check logic based on actual criteria.
     *
     * @param bill The bill to check if it contains groceries.
     * @return {@code true} if the bill contains groceries, otherwise {@code false}.
     */
    private boolean isGrocery(Bill bill) {
        // Implement grocery check logic based on actual criteria
        return false;
    }
}
