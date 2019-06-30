package com.oldgoat5;

public class ChildrensMovieStrategy implements CostStrategy {
    private static final double BASE_CHILDRENS_PRICE = 1.5;
    private static final double CHILDRENS_MULTIPLIER = 1.5;

    @Override
    public double getCost(Rental rental) {
        if (rental.getDaysRented() < 3) {
            return BASE_CHILDRENS_PRICE + ((3 - rental.getDaysRented()) * CHILDRENS_MULTIPLIER);
        } else {
            return BASE_CHILDRENS_PRICE;
        }
    }
}
