package com.oldgoat5;

/*********************************************************************
 * @author Michael Evans 
 * @since 6/17/19
 *********************************************************************/
public class RegularStrategy implements CostStrategy {

    private static final double COST_MULTIPLIER = 1.5;
    private static final double BASE_COST = 2;

    @Override
    public double getCost(Rental rental) {
        if (rental.daysRented > 2) {
            return BASE_COST + (rental.daysRented - 2) * COST_MULTIPLIER;
        } else {
            return BASE_COST;
        }
    }
}
