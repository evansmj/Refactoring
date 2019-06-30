package com.oldgoat5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*********************************************************************
 * @author Michael Evans 
 * @since 6/17/19
 *********************************************************************/
public class CustomerTest {

    private Movie regular = new Movie("Regular", Movie.PriceCode.REGULAR);
    private Movie childrens = new Movie("Childrens", Movie.PriceCode.CHILDRENS);
    private Movie newRelease = new Movie("New Release", Movie.PriceCode.NEW_RELEASE);

    @Test
    public void statementTest() {
        Customer customer = new Customer("ME");
        Rental gradleRental = new Rental(newRelease, 3);
        customer.addRental(gradleRental);
        assertEquals("Rental Record for ME", customer.statement().split("\n")[0]);
    }

    @Test
    public void given_PriceCode_CHILDRENS_assert_amount_is_correct(){
        Customer customer = new Customer("ME");
        Rental gradleRental = new Rental(childrens, 3);
        customer.addRental(gradleRental);
        assertEquals("Rental Record for ME\n" +
                "\tChildrens\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void given_PriceCode_REGULAR_assert_amount_is_correct(){
        Customer customer = new Customer("ME");
        Rental gradleRental = new Rental(regular, 3);
        customer.addRental(gradleRental);
        assertEquals("Rental Record for ME\n" +
                "\tRegular\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void given_PriceCode_NEW_RELEASE_assert_amount_is_correct(){
        Customer customer = new Customer("ME");

        Rental gradleRental = new Rental(newRelease, 3);

        customer.addRental(gradleRental);

        assertEquals("Rental Record for ME\n" +
                "\tNew Release\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @Test
    public void appendFooter_givenSomeValues_shouldConstructValidFooter() {
        Customer customer = new Customer("ME");
        StringBuilder test = customer.appendFooter(1d, 9);
        assertEquals("Amount owed is 1.0\n" + "You earned 9 frequent renter points", test.toString());
    }

    @Test
    public void createHeader_assert_that_header_is_correct() {
        Customer customer = new Customer("ME");
        StringBuilder test = customer.createHeader();
        assertEquals("Rental Record for ME\n", test.toString());
    }

    @Test
    public void givenNonNewReleaseRental_assertFrequentPointsIsEqualToTotalRentals(){
        Customer customer = new Customer("ME");
        customer.addRental(new Rental(regular, 1));
        customer.addRental(new Rental(childrens, 1));

        assertEquals(2, customer.getFrequentRenterPoints());
    }
    @Test
    public void givenNewReleaseRental_assertFrequentPointsIsEqualToTotalRentals(){
        Customer customer = new Customer("ME");
        customer.addRental(new Rental(newRelease, 1));
        customer.addRental(new Rental(newRelease, 1));

        assertEquals(2, customer.getFrequentRenterPoints());
    }

    @Test
    public void givenNewReleaseRental_assertFrequentPointsIsEqualToTotalRentalsPlusOneBonusIfMoreThanSingleDayRental(){
        Customer customer = new Customer("ME");
        customer.addRental(new Rental(newRelease, 2));
        customer.addRental(new Rental(newRelease, 1));

        assertEquals(3, customer.getFrequentRenterPoints());
    }

    @Test
    public void givenRentals_assertTotalCost(){
        Customer customer = new Customer("ME");
        customer.addRental(new Rental(regular, 1));
        customer.addRental(new Rental(regular, 1));

        assertEquals(4d, customer.getTotalAmount(), 0d);
    }

}