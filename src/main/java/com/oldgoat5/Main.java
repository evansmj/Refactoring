package com.oldgoat5;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("ME");

        Movie gradle = new Movie("Deep Dive Into Gradle", Movie.PriceCode.NEW_RELEASE);
        Rental gradleRental = new Rental(gradle, 3);

        customer.addRental(gradleRental);

        System.out.print(customer.statement());
    }
}
