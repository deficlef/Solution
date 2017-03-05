package test;

import com.company.licence.NewTaxiLicence;
import com.company.licence.RenewalTaxiLicence;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class TaxiLicenceTest {

    @Test
    public void testGetContractLengthDiscountReturnsZeroOnNullDiscount() {
        NewTaxiLicence tl = new NewTaxiLicence();
        tl.setDiscounts(null);

        int expected = 0;
        int actual = tl.getContractLengthDiscount();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetContractLengthDiscountReturnsZeroOnNonExistingDiscount() {
        int contractLength = 3;
        RenewalTaxiLicence tl = new RenewalTaxiLicence(contractLength);
        HashMap<Integer, Integer> disc = tl.getDiscounts();

        disc.remove(contractLength);
        tl.setDiscounts(disc);

        int expected = 0;
        int actual = tl.getContractLengthDiscount();

        assertEquals(actual, expected);
    }

    @Test
    public void testGetContractLengthDiscountReturnsDiscountOnExistingDiscount() {
        int contractLength = 2;
        RenewalTaxiLicence tl = new RenewalTaxiLicence(contractLength);
        HashMap<Integer, Integer> disc = tl.getDiscounts();

        int val = 100;
        disc.put(contractLength, val);

        tl.setDiscounts(disc);

        int actual = tl.getContractLengthDiscount();

        assertEquals(actual, val);
    }

    @Test
    public void testGetTotalNetFeeReturnsRightCalculationForNewTaxiLicence() {
        NewTaxiLicence tl = new NewTaxiLicence();
        double expected = tl.getApplicationFee() + tl.getAnnualFee();
        double actual = tl.getTotalNetFee();

        assertEquals(actual, expected, 0.01);
    }

    @Test
    public void testGetTotalNetFeeReturnsRightCalculationForRenewalTaxiLicence() {
        RenewalTaxiLicence tl = new RenewalTaxiLicence(2);
        double expected =
                tl.getApplicationFee() +
                (((tl.getAnnualFee() * tl.getContractLength()) * (100 - tl.getContractLengthDiscount())) / 100);
        double actual = tl.getTotalNetFee();

        assertEquals(actual, expected, 0.01);
    }

}