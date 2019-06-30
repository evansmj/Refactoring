package com.oldgoat5;

public class Rental {
    public final Movie movie;
    public final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }
}