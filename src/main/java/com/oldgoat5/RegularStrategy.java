package com.oldgoat5;

/*********************************************************************
 * @author Michael Evans 
 * @since 6/17/19
 *********************************************************************/
public class RegularStrategy implements CostStrategy {

    @Override
    public double getCost(Rental each) {
        double localThisAmount = 2;
        if (each.getDaysRented() > 2) {
            localThisAmount += (each.getDaysRented() - 2) * 1.5;
        }
        return localThisAmount;
    }
}
