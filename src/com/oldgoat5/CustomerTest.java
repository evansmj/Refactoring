package com.oldgoat5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*********************************************************************
 * @author Michael Evans 
 * @since 6/17/19
 *********************************************************************/
public class CustomerTest {

    @Test
    public void statementTest() {
        Customer customer = new Customer("ME");

        Movie gradle = new Movie("Deep Dive Into Gradle", 1);
        Rental gradleRental = new Rental(gradle, 3);

        customer.addRental(gradleRental);

        assertEquals("Rental Record for ME", customer.statement().split("\n")[0]);
    }

    @Test
    public void given_PriceCode_CHILDRENS_assert_amount_is_correct(){
        Customer customer = new Customer("ME");

        Movie gradle = new Movie("Deep Dive Into Gradle", Movie.CHILDRENS);
        Rental gradleRental = new Rental(gradle, 3);

        customer.addRental(gradleRental);

        assertEquals("Rental Record for ME\n" +
                "\tDeep Dive Into Gradle\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void given_PriceCode_REGULAR_assert_amount_is_correct(){
        Customer customer = new Customer("ME");

        Movie gradle = new Movie("Deep Dive Into Gradle", Movie.REGULAR);
        Rental gradleRental = new Rental(gradle, 3);

        customer.addRental(gradleRental);

        assertEquals("Rental Record for ME\n" +
                "\tDeep Dive Into Gradle\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void given_PriceCode_NEW_RELEASE_assert_amount_is_correct(){
        Customer customer = new Customer("ME");

        Movie gradle = new Movie("Deep Dive Into Gradle", Movie.NEW_RELEASE);
        Rental gradleRental = new Rental(gradle, 3);

        customer.addRental(gradleRental);

        assertEquals("Rental Record for ME\n" +
                "\tDeep Dive Into Gradle\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

}