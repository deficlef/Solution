package com.company.licence;

import java.util.HashMap;


public abstract class TaxiLicence {

    /**
     * Licence's annual fee
     */
    protected double annualFee = 0;

    /**
     * Licence's application fee
     */
    protected double applicationFee = 0;

    /**
     * Licence's length
     */
    protected int contractLength = 0;

    /**
     * Licence's discounts
     */
    protected HashMap<Integer, Integer> discounts;


    /**
     * For getting the discount value according to licence's length
     *
     * @return Integer
     */
    public int getContractLengthDiscount() {
        int discount = 0;

        if (discounts != null) {
            discount = (discounts.get(contractLength) != null) ? discounts.get(contractLength) : 0;
        }

        return discount;
    }

    /**
     * For calculating the total net value of the taxi licence
     *
     * @return Double
     */
    public double getTotalNetFee() {
        int discount = getContractLengthDiscount();
        double discountedFee = ((annualFee * contractLength) * (100 - discount)) / 100;

        return discountedFee + applicationFee;
    }

    // Getters
    public double getApplicationFee() {
        return applicationFee;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public int getContractLength() {
        return contractLength;
    }

    public HashMap getDiscounts() {
        return discounts;
    }

    // Setters
    public void setApplicationFee(Double af) {
        applicationFee = af;
    }

    public void setAnnualFee(Double af) {
        annualFee = af;
    }

    public void setDiscounts(HashMap<Integer, Integer> d) {
        discounts = d;
    }
}


