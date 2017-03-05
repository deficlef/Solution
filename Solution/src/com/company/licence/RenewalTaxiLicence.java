package com.company.licence;

import java.util.HashMap;


public class RenewalTaxiLicence extends TaxiLicence {

    /**
     * Licence's max renewal length
     */
    public static final int MAX_RENEWAL_LENGTH = 3;

    public RenewalTaxiLicence(int length) {
        applicationFee = 0;
        annualFee = 59;
        contractLength = length;
        discounts = new HashMap<Integer, Integer>();
        discounts.put(2, 5);
        discounts.put(3, 10);
    }
}