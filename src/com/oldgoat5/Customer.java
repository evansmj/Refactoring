package com.oldgoat5;

import java.util.Enumeration;
import java.util.Vector;

/*********************************************************************
 * from martin fowler
 *********************************************************************/
public class Customer {
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
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";

        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private double getThisAmount(Rental each, CostStrategy strategy) {
        return strategy.getCost(each);
//        double thisAmount = (double) 0;
//        switch (each.getMovie().getPriceCode()) {
//            case Movie.REGULAR: {
//                return new RegularStrategy().getCost(each);
//            }
//            case Movie.NEW_RELEASE: {
//                thisAmount += each.getDaysRented() * 3;
//                break;
//            }
//            case Movie.CHILDRENS: {
//                thisAmount += 1.5;
//                if (each.getDaysRented() > 3)
//                    thisAmount += (each.getDaysRented() - 3) * 1.5;
//                break;
//            }
//        }
//        return thisAmount;
    }


}