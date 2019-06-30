package com.oldgoat5;

public class NewReleaseStrategy implements CostStrategy {
    @SuppressWarnings("FieldCanBeLocal")
    private static int BASE_COST = 3;

    @Override
    public double getCost(Rental each) {
        return each.daysRented * BASE_COST;
    }
}