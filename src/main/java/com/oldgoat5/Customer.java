package com.oldgoat5;

import org.jetbrains.annotations.NotNull;

import java.util.Enumeration;
import java.util.Vector;

/*********************************************************************
 * from martin fowler
 *********************************************************************/
public class Customer {
    public static final double BASE_CHILDRENS_PRICE = 1.5;
    public static final double CHILDRENS_MULTIPLIER = 1.5;
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //determine amounts for each line
            double thisAmount = getThisAmount(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    &&
                    each.getDaysRented() > 1) frequentRenterPoints++;

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";

            totalAmount += thisAmount;
        }

        result = appendFooter(totalAmount, frequentRenterPoints, result);
        return result;
    }

    @NotNull
    String appendFooter(double totalAmount, int frequentRenterPoints, String result) {
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";

        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private double getThisAmount(Rental each) {
        double thisAmount = 0.0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR: {
                thisAmount = new RegularStrategy().getCost(each);
            }
            case Movie.NEW_RELEASE: {
                thisAmount += each.getDaysRented() * 3;
                break;
            }
            case Movie.CHILDRENS: {
                thisAmount += BASE_CHILDRENS_PRICE;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * CHILDRENS_MULTIPLIER;
                break;
            }
        }
        return thisAmount;
    }


}