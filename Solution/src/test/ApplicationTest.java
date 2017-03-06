package test;

import com.company.Application;
import com.company.ApplicationUI;
import com.company.licence.RenewalTaxiLicence;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class ApplicationTest {

    private Application app;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
    }

    @Before
    public void setUp() {
        app = new Application(new ApplicationUI());
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGetIntegerValueReturnsZeroOnNullValue() {
        int expected = 0;
        int actual = app.getIntegerValue(null);

        assertEquals(actual, expected);
    }

    @Test
    public void testGetIntegerValueReturnsZeroOnStringCharacters() {
        int expected = 0;
        int actual = app.getIntegerValue("string");

        assertEquals(actual, expected);
    }

    @Test
    public void testGetIntegerValueReturnsIntegerOnNumberCharacters() {
        int expected = 5;
        int actual = app.getIntegerValue("5");

        assertEquals(actual, expected);
    }


    @Test
    public void testGetLicenceTypeDisplaysInvalidLicenceMessageOnNonexistingLicenceName() {
        ByteArrayInputStream in = new ByteArrayInputStream("non-existing-name".getBytes());
        app.getLicenceType(in);

        assertTrue(outContent.toString().contains("Invalid taxi licence."));
    }

    @Test
    public void testGetLicenceTypeReturnsLicenceTypeOnSentenceCaseLicenceName() {
        ByteArrayInputStream in = new ByteArrayInputStream("New".getBytes());
        String expected = "new";
        String actual = app.getLicenceType(in);

        assertEquals(actual, expected);
    }

    @Test
    public void testGetLicenceTypeReturnsLicenceTypeOnLowerCaseLicenceName() {
        ByteArrayInputStream in = new ByteArrayInputStream("new".getBytes());
        String expected = "new";
        String actual = app.getLicenceType(in);

        assertEquals(actual, expected);
    }

    @Test
    public void testGetLicenceTypeReturnsLicenceTypeOnUpperCaseLicenceName() {
        ByteArrayInputStream in = new ByteArrayInputStream("NEW".getBytes());
        String expected = "new";
        String actual = app.getLicenceType(in);

        assertEquals(actual, expected);
    }

    @Test
    public void testGetLicenceTypeReturnsLicenceTypeIrregularCaseLicenceName() {
        ByteArrayInputStream in = new ByteArrayInputStream("nEW".getBytes());
        String expected = "new";
        String actual = app.getLicenceType(in);

        assertEquals(actual, expected);
    }

    @Test
    public void testGetDriverLicenceReturnsInputOnSixteenChars() {
        String data = "MORGA657054SM9IJ";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        String actual = app.getDrivingLicence(in);

        assertEquals(actual, data);
    }

    @Test
    public void testGetDriverLicenceDisplaysInvalidErrorOnLessThanSixteenChars() {
        String data = "MORGA65705";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        app.getDrivingLicence(in);

        assertTrue(outContent.toString().contains("Invalid licence."));
    }

    @Test
    public void testGetDriverLicenceReturnsInputOnMoreThanSixteenChars() {
        String data = "MORGA657054SM9IJEXTRACHAREACTERS";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        String actual = app.getDrivingLicence(in);

        assertEquals(actual, data);
    }

    @Test
    public void testIsRetryReturnsTrueOnLowercaseYString() {
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        Boolean actual = app.isRetry(in);

        assertEquals(actual, true);
    }

    @Test
    public void testIsRetryReturnsTrueOnUppercaseYString() {
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        Boolean actual = app.isRetry(in);

        assertEquals(actual, true);
    }

    @Test
    public void testIsRetryReturnsFalseOnLowercaseNString() {
        ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
        Boolean actual = app.isRetry(in);

        assertEquals(actual, false);
    }

    @Test
    public void testIsRetryReturnsFalseOnUppercaseNString() {
        ByteArrayInputStream in = new ByteArrayInputStream("N".getBytes());
        Boolean actual = app.isRetry(in);

        assertEquals(actual, false);
    }

    @Test
    public void testIsRetryDisplaysInvalidEntryOnInvalidString() {
        ByteArrayInputStream in = new ByteArrayInputStream("invalid string".getBytes());
        app.isRetry(in);

        assertTrue(outContent.toString().contains("Invalid entry."));
    }

    @Test
    public void testGetUserAgeDisplaysInvalidAgeOnInvalidString() {
        ByteArrayInputStream in = new ByteArrayInputStream("invalid string".getBytes());
        app.getUserAge(in);

        assertTrue(outContent.toString().contains("Invalid age."));
    }

    @Test
    public void testGetUserAgeDisplaysInvalidAgeOnZeroString() {
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        app.getUserAge(in);

        assertTrue(outContent.toString().contains("Invalid age."));
    }

    @Test
    public void testGetUserAgeReturnsInputOnValidNumberString() {
        int data = 10;
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(data).getBytes());
        int actual = app.getUserAge(in);

        assertEquals(actual, data);
    }

    @Test
    public void testGetRenewalLengthReturnsInputOnNumberWithinMax() {
        int data = RenewalTaxiLicence.MAX_RENEWAL_LENGTH;
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(data).getBytes());
        int actual = app.getRenewalLength(in);

        assertEquals(actual, data);
    }

    @Test
    public void testGetRenewalLengthDisplaysInvalidEntryOnZeroString() {
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        app.getRenewalLength(in);

        assertTrue(outContent.toString().contains("Invalid entry."));
    }

    @Test
    public void testGetRenewalLengthDisplaysEligibilityMessageOnNumberAboveMax() {
        ByteArrayInputStream in = new ByteArrayInputStream("10".getBytes());
        app.getRenewalLength(in);

        assertTrue(outContent.toString().contains(
                "Maximum allowed is " + RenewalTaxiLicence.MAX_RENEWAL_LENGTH + " years.")
        );
    }

}