package com.oldgoat5;

import org.jetbrains.annotations.NotNull;

import java.util.Enumeration;
import java.util.Vector;

/*********************************************************************
 * from martin fowler
 *********************************************************************/
public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + this.name + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //determine amounts for each line
            double thisAmount = getThisAmount(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().priceCode == Movie.PriceCode.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            //show figures for this rental
            result += "\t" + each.getMovie().title + "\t" + thisAmount + "\n";

            totalAmount += thisAmount;
        }

        result = appendFooter(totalAmount, frequentRenterPoints, result);
        return result;
    }

    @NotNull
    String appendFooter(double totalAmount, int frequentRenterPoints, String result) {
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double getThisAmount(Rental rental) {
        switch (rental.getMovie().priceCode) {
            case REGULAR: {
                return new RegularStrategy().getCost(rental);
            }
            case NEW_RELEASE: {
                return new NewReleaseStrategy().getCost(rental);
            }
            case CHILDRENS: {
                return new ChildrensMovieStrategy().getCost(rental);

            }
            default:
                throw new IllegalArgumentException("Unrecognized Price Code");
        }
    }


}