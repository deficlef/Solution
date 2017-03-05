package test;

import com.company.Application;
import com.company.ApplicationUI;
import com.company.Step;
import com.company.licence.NewTaxiLicence;
import com.company.licence.RenewalTaxiLicence;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertTrue;


public class ApplicationUITest {

    ApplicationUI applicationuUI;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        applicationuUI = new ApplicationUI();
    }

    @Test
    public void testShowWelcomeMessageDisplaysExpectedBannerText() throws Exception {
        applicationuUI.showWelcomeMessage();
        assertTrue(outContent.toString().contains("Bristol City Council Taxi Licencing Service"));
    }

    @Test
    public void testShowWelcomeMessageDisplaysExpectedBodyText() throws Exception {
        applicationuUI.showWelcomeMessage();
        assertTrue(outContent.toString().contains(
                "Welcome to Bristol City Council Taxi Licencing Service")
        );
    }

    @Test
    public void testAskUserForLicenceTypePromptsForLicenceType() throws Exception {
        applicationuUI.askUserForLicenceType();
        assertTrue(outContent.toString().contains(
                "Please enter the taxi licence you are interested in (New or Renewal):")
        );
    }

    @Test
    public void testAskUserForAgePromptsForUserAge() throws Exception {
        applicationuUI.askUserForAge();
        assertTrue(outContent.toString().contains("Please enter the client's age (e.g. 30):"));
    }

    @Test
    public void testAskUserForDrivingLicencePromptsForUserAge() throws Exception {
        applicationuUI.askUserForDrivingLicence();
        assertTrue(outContent.toString().contains("Please enter the client's full DVLA driving license:"));
    }

    @Test
    public void testShowLicenceTypeNotificationDisplaysSelectedLicence() throws Exception {
        applicationuUI.showLicenceTypeNotification("renewal");
        assertTrue(outContent.toString().contains("RENEWAL taxi licence selected."));
    }

    @Test
    public void testAskForRenewalLengthPromptsForRenewalLength() throws Exception {
        applicationuUI.askUserForRenewalLength();
        assertTrue(outContent.toString().contains(
                "Please enter how many years the licence would be renewed for:")
        );
    }

    @Test
    public void testAskForRenewalLengthDisplaysMaxRenewalLength() throws Exception {
        applicationuUI.askUserForRenewalLength();
        assertTrue(outContent.toString().contains(
                "(Max allowed: " + RenewalTaxiLicence.MAX_RENEWAL_LENGTH + " years):")
        );
    }

    @Test
    public void testAskUserToRetryDisplaysRetrialPrompt() throws Exception {
        applicationuUI.askUserToRetry();
        assertTrue(outContent.toString().contains("Try again? (Y or N):"));
    }

    @Test
    public void testShowInvalidErrorDisplaysInvalidAge() throws Exception {
        applicationuUI.showInvalidError(Step.AGE);
        assertTrue(outContent.toString().contains("Invalid age."));
    }

    @Test
    public void testShowInvalidErrorDisplaysInvalidDrivingLicence() throws Exception {
        applicationuUI.showInvalidError(Step.DRIVING_LICENCE);
        assertTrue(outContent.toString().contains(
                "Invalid licence. DVLA driving license must be 16 or more characters.")
        );
    }

    @Test
    public void testShowInvalidErrorDisplaysInvalidTaxiLicence() throws Exception {
        applicationuUI.showInvalidError(Step.TAXI_LICENCE);
        assertTrue(outContent.toString().contains("Invalid taxi licence."));
    }

    @Test
    public void testShowInvalidEntryErrorDisplaysInvalidEntry() throws Exception {
        applicationuUI.showInvalidError(Step.RENEWAL_LENGTH);
        assertTrue(outContent.toString().contains("Invalid entry."));
    }

    @Test
    public void testShowEligibilityMessageDisplaysAgeEligibilityMessage() throws Exception {
        applicationuUI.showEligibilityMessage(Step.AGE);
        assertTrue(outContent.toString().contains(
                "Sorry. New drivers must be " + NewTaxiLicence.MIN_AGE + " or over to be eligible.")
        );
    }

    @Test
    public void testShowEligibilityMessageDisplaysRenewalLengthEligibilityMessage() throws Exception {
        applicationuUI.showEligibilityMessage(Step.RENEWAL_LENGTH);
        assertTrue(outContent.toString().contains(
                "Maximum allowed is " + RenewalTaxiLicence.MAX_RENEWAL_LENGTH + " years.")
        );
    }

    @Test
    public void testShowCalculatingMessageDisplaysGoodbyeMessage() throws Exception {
        applicationuUI.showCalculatingMessage();
        assertTrue(outContent.toString().contains("Calculating, please wait."));
    }

    @Test
    public void testShowClosingMessageDisplaysGoodbyeMessage() throws Exception {
        applicationuUI.showClosingMessage();
        assertTrue(outContent.toString().contains("Thanks for using th app.\r\nGoodbye."));
    }

    @Test
    public void testShowEligibilityMessageDisplaysEligibilityErrorForDefaultStep() throws Exception {
        applicationuUI.showEligibilityMessage(Step.DRIVING_LICENCE);
        assertTrue(outContent.toString().contains("Eligibility error."));
    }

    @Test
    public void testDisplayLicenceInfoDisplaysLicenceApplicationFee() throws Exception {
        NewTaxiLicence tl = new NewTaxiLicence();

        applicationuUI.displayLicenceInfo(tl);
        assertTrue(outContent.toString().contains("Application fee: £" + tl.getApplicationFee()));
    }

    @Test
    public void testDisplayLicenceInfoDisplaysLicenceAnnualFee() throws Exception {
        NewTaxiLicence tl = new NewTaxiLicence();

        applicationuUI.displayLicenceInfo(tl);
        assertTrue(outContent.toString().contains("Annual fee: £" + tl.getAnnualFee()));
    }

    @Test
    public void testDisplayLicenceInfoDisplaysNoDiscountOnNonExistingDiscount() throws Exception {
        NewTaxiLicence tl = new NewTaxiLicence();

        tl.setDiscounts(null);
        applicationuUI.displayLicenceInfo(tl);
        assertTrue(outContent.toString().contains("No available discount."));
    }

    @Test
    public void testDisplayLicenceInfoDisplaysTitleWithClassName() throws Exception {
        RenewalTaxiLicence tl = new RenewalTaxiLicence(3);
        applicationuUI.displayLicenceInfo(tl);
        assertTrue(
                outContent.toString().contains("2 year discount: " +
                String.valueOf(tl.getDiscounts().get(2)) + "%")
        );
    }

    @Test
    public void testDisplayLicenceInfoDisplaysTotalNetFee() throws Exception {
        NewTaxiLicence tl = new NewTaxiLicence();

        applicationuUI.displayLicenceInfo(tl);
        assertTrue(outContent.toString().contains("Total Net fee: £" + tl.getTotalNetFee()));
    }

}