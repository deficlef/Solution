package com.company.licence;


public class NewTaxiLicence extends TaxiLicence {

    /**
     * Licence's user's minimum age
     */
    public static final int MIN_AGE = 18;
    /**
     * Licence's max renewal length
     */
    public static final int MAX_RENEWAL_LENGTH = 0;


    public NewTaxiLicence() {
        applicationFee = 19.50;
        annualFee = 59;
        contractLength = 1;
        discounts = null;
    }
}