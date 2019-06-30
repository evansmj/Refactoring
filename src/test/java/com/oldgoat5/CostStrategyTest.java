package com.oldgoat5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostStrategyTest {

    private Movie regular = new Movie("Regular", Movie.PriceCode.REGULAR);
    private Movie childrens = new Movie("Childrens", Movie.PriceCode.CHILDRENS);
    private Movie newRelease = new Movie("New Release", Movie.PriceCode.NEW_RELEASE);

    @Test
    public void givenRegularRental_lessThanTwoDays_assertCost() {
        CostStrategy costStrategy = new RegularStrategy();
        assertEquals(2d, costStrategy.getCost(new Rental(regular, 2)), 0d);
    }

    @Test
    public void givenRegularRental_moreThanTwoDays_assertCost() {
        CostStrategy costStrategy = new RegularStrategy();
        assertEquals(3.5d, costStrategy.getCost(new Rental(regular, 3)), 0d);
    }

    @Test
    public void givenChildrenRental_lessThanThreeDays_assertCost() {
        CostStrategy costStrategy = new ChildrensMovieStrategy();
        assertEquals(3.0d, costStrategy.getCost(new Rental(childrens, 2)), 0d);
    }

    @Test
    public void givenChildrenRental_moreThanThreeDays_assertCost() {
        CostStrategy costStrategy = new ChildrensMovieStrategy();
        assertEquals(1.5d, costStrategy.getCost(new Rental(childrens, 3)), 0d);
    }

    @Test
    public void givenNewReleaseRental_assertCost() {
        CostStrategy costStrategy = new NewReleaseStrategy();
        assertEquals(6.0d, costStrategy.getCost(new Rental(newRelease, 2)), 0d);
    }
}
