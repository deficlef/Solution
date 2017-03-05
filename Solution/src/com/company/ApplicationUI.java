package com.company;

import com.company.licence.NewTaxiLicence;
import com.company.licence.RenewalTaxiLicence;
import com.company.licence.TaxiLicence;

import java.util.HashMap;
import java.util.Map;


public class ApplicationUI {

    /**
     * For displaying welcome message
     */
    public void showWelcomeMessage() {
        System.out.println("------------------------------------------------------");
        System.out.println("Bristol City Council Taxi Licencing Service");
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("Welcome to Bristol City Council Taxi Licencing Service.");
        System.out.println();
    }

    /**
     * For requesting taxi licence type
     */
    public void askUserForLicenceType() {
        System.out.println("Please enter the taxi licence you are interested in (New or Renewal):");
    }

    /**
     * For requesting driver's age
     */
    public void askUserForAge() {
        System.out.println("Please enter the client's age (e.g. 30):");
    }

    /**
     * For requesting driver's driving licence
     */
    public void askUserForDrivingLicence() {
        System.out.println("Please enter the client's full DVLA driving license:");
    }

    /**
     * For confirming entered licence type to user
     *
     * @param name selected licence name
     */
    public void showLicenceTypeNotification(String name) {
        System.out.println(name.toUpperCase() + " taxi licence selected.");
    }

    /**
     * For requesting taxi licence renewal length
     */
    public void askUserForRenewalLength() {
        System.out.println("Please enter how many years the licence would be renewed for:" +
                " (Max allowed: " + RenewalTaxiLicence.MAX_RENEWAL_LENGTH + " years):");
    }


    /**
     * For prompting a re-trial or closure
     */
    public void askUserToRetry() {
        System.out.println("Try again? (Y or N):");
    }

    /**
     * For displaying error messages for invalid entries
     *
     * @param step application step
     */
    public void showInvalidError(Step step) {
        switch (step) {
            case AGE:
                System.out.println("Invalid age.");
                break;
            case DRIVING_LICENCE:
                System.out.println("Invalid licence. DVLA driving license must be 16 or more characters.");
                break;
            case TAXI_LICENCE:
                System.out.println("Invalid taxi licence.");
                break;
            case RENEWAL_LENGTH:
            default:
                System.out.println("Invalid entry.");
                break;

        }
    }

    /**
     * For displaying application's calculating message
     */
    public void showCalculatingMessage() {
        System.out.println();
        System.out.print("Calculating, please wait.");
        for(int i =0; i <=20; i++) {
            System.out.print(".");
        }
        System.out.println();
    }

    /**
     * For displaying application's closing message
     */
    public void showClosingMessage() {
        System.out.println("Thanks for using th app.\r\nGoodbye.");
    }

    /**
     * For displaying eligibility messages
     *
     * @param step application step
     */
    public void showEligibilityMessage(Step step) {
        switch (step) {
            case AGE:
                System.out.println("Sorry. New drivers must be " + NewTaxiLicence.MIN_AGE + " or over to be eligible.");
                break;
            case RENEWAL_LENGTH:
                System.out.println("Maximum allowed is " + RenewalTaxiLicence.MAX_RENEWAL_LENGTH + " years.");
                break;
            default:
                System.out.println("Eligibility error.");
                break;
        }
    }

    /**
     * For displaying the Taxi licence information
     *
     * @param tl TaxiLicence
     */
    public void displayLicenceInfo(TaxiLicence tl) {
        HashMap<Integer, Integer> discounts;
        String className = tl.getClass().getSimpleName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println(className + " Information");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("************ FEES **************");
        System.out.println("Application fee: £" + String.format( "%.2f", tl.getApplicationFee() ));
        System.out.println("Annual fee: £" + String.format( "%.2f", tl.getAnnualFee() ));
        System.out.println();
        System.out.println("************ DISCOUNTS **************");

        discounts = tl.getDiscounts();

        if (discounts == null) {
            System.out.println("No available discount.");
        } else {
            for (Map.Entry<Integer, Integer> discount : discounts.entrySet()) {
                System.out.println(String.valueOf(discount.getKey()) + " year discount: " +
                        String.valueOf(discount.getValue()) + "%");
            }
        }

        System.out.println();
        System.out.println("************ TOTAL FEES **************");
        System.out.println("Total Net fee: £" + String.format( "%.2f", tl.getTotalNetFee()));
        System.out.println();
    }

}
