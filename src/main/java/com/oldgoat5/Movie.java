package com.oldgoat5;

public class Movie {

    public final String title;
    public final PriceCode priceCode;


    public Movie(String title, PriceCode priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }


    public enum PriceCode{
        REGULAR, NEW_RELEASE, CHILDRENS
    }
}