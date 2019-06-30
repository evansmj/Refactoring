package com.oldgoat5;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************
 * from martin fowler
 *********************************************************************/
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String statement() {
        return createHeader().append(appendLineItems()).append(appendFooter(getTotalAmount(), getFrequentRenterPoints())).toString();
    }

    StringBuilder appendLineItems(){
        StringBuilder lineItems = new StringBuilder();
        for (Rental rental : rentals) {
            lineItems.append("\t")
                    .append(rental.movie.title)
                    .append("\t")
                    .append(getCost(rental))
                    .append("\n");
        }
        return lineItems;
    }



    StringBuilder createHeader(){
        StringBuilder header = new StringBuilder();
        header.append("Rental Record for ")
                .append(this.name)
                .append("\n");

        return header;
    }

    @NotNull
    StringBuilder appendFooter(double totalAmount, int frequentRenterPoints) {
        StringBuilder footer = new StringBuilder();
        footer.append("Amount owed is ").append(totalAmount).append("\n");
        footer.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return footer;
    }

    double getTotalAmount() {
        double accumulate = 0;
        for (Rental rental : rentals) {
            accumulate += getCost(rental);
        }
        return accumulate;
    }

    int getFrequentRenterPoints(){
        int accumulate = 0;
        for (Rental rental : rentals) {
            accumulate++;
            if ((rental.movie.priceCode == Movie.PriceCode.NEW_RELEASE) && rental.daysRented > 1) {
                accumulate++;
            }
        }
        return accumulate;
    }

    private double getCost(Rental rental) {
        switch (rental.movie.priceCode) {
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