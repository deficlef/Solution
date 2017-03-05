package com.company;

import com.company.licence.NewTaxiLicence;
import com.company.licence.RenewalTaxiLicence;
import com.company.licence.TaxiLicence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.company.Step.*;


public class Application {

    /**
     * List of available licence types
     */
    private static String[] licenceTypes = new String[]{"new", "renewal"};

    /**
     * TaxiLicence class
     */
    private static TaxiLicence taxiLicence;


    /**
     * ApplicationUI class
     */
    private ApplicationUI ui;

    /**
     * Class constructor
     *
     * @param ui ApplicationUI
     */
    public Application(ApplicationUI ui) {
        this.ui = ui;
    }

    /**
     * Run application
     *
     * @throws IOException
     */
    public void run() throws IOException {

        ui.showWelcomeMessage();

        String selectedLicence = getLicenceType(System.in);

        if (selectedLicence.equals("new")) {

            ui.showLicenceTypeNotification("new");

            if (getUserAge(System.in) < NewTaxiLicence.MIN_AGE) {

                ui.showEligibilityMessage(AGE);

                if (isRetry(System.in)) {
                    run();
                } else {
                    ui.showClosingMessage();
                    return;
                }
            }

            if (getDrivingLicence(System.in) != null) {
                taxiLicence = new NewTaxiLicence();
            }

        } else if (selectedLicence.equals("renewal")) {

            ui.showLicenceTypeNotification("renewal");

            int renewalLength = getRenewalLength(System.in);

            taxiLicence = new RenewalTaxiLicence(renewalLength);
        }

        if (taxiLicence != null) {
            ui.showCalculatingMessage();
            ui.displayLicenceInfo(taxiLicence);
        }

        if (isRetry(System.in)) {
            run();
        } else {
            ui.showClosingMessage();
        }
    }

    /**
     * For getting integer value of user entry
     *
     * @param response entry string
     * @return Integer
     */
    public int getIntegerValue(String response) {
        try {
            return Integer.parseInt(response);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * For analysing the user's entry as a driver's licence
     *
     * @param in InputStream
     * @return String driving licence
     */
    public String getDrivingLicence(InputStream in) {
        Scanner keyboard = new Scanner(in);

        ui.askUserForDrivingLicence();

        String input = keyboard.next();

        while (input.length() < 16) {
            ui.showInvalidError(DRIVING_LICENCE);
            ui.askUserForDrivingLicence();

            if(keyboard.hasNext()) {
                input = keyboard.next();
            } else {
                return null;
            }
        }

        return input;
    }

    /**
     * For getting the licence type from the licenceType list, based on user's entry
     *
     * @param in InputStream
     * @return String licence type
     */
    public String getLicenceType(InputStream in) {
        boolean isExistng = false;
        Scanner keyboard = new Scanner(in);

        ui.askUserForLicenceType();

        String input = keyboard.next().toLowerCase();

        for (String licence : licenceTypes) {
            if (input.toLowerCase().equals(licence)) {
                isExistng = true;
            }
        }

        while (!isExistng) {
            ui.showInvalidError(TAXI_LICENCE);
            ui.askUserForLicenceType();

            if(keyboard.hasNext()) {
                input = keyboard.next();
            } else {
                return null;
            }
        }

        return input;
    }


    /**
     * For getting the user's age
     * @param in InputStream
     * @return Integer user age
     */
    public int getUserAge(InputStream in) {
        Scanner keyboard = new Scanner(in);

        ui.askUserForAge();

        Integer input = getIntegerValue(keyboard.next());

        while (input == 0) {
            ui.showInvalidError(AGE);
            ui.askUserForAge();

            if (keyboard.hasNext()) {
                input = getIntegerValue(keyboard.next());
            } else {
                return 0;
            }
        }

        return input;
    }

    /**
     * For getting the renewal taxi licence renewal length
     *
     * @param in InputStream
     * @return Integer renewal length
     */
    public int getRenewalLength(InputStream in) {
        Scanner keyboard = new Scanner(in);

        ui.askUserForRenewalLength();

        Integer input = getIntegerValue(keyboard.next());

        while (input == 0 || input > RenewalTaxiLicence.MAX_RENEWAL_LENGTH) {

            if (input > 3) {
                ui.showEligibilityMessage(RENEWAL_LENGTH);
            } else {
                ui.showInvalidError(RENEWAL_LENGTH);
            }

            ui.askUserForRenewalLength();

            if (keyboard.hasNext()) {
                input = getIntegerValue(keyboard.next());
            } else {
                return 0;
            }
        }

        return input;
    }

    /**
     * Confirm if the user wants to retry
     *
     * @param in InputStream
     * @return boolean
     */
    public boolean isRetry(InputStream in) {
        Scanner keyboard = new Scanner(in);

        ui.askUserToRetry();

        String input = keyboard.next().toLowerCase();

        while (!input.equals("y") && !input.equals("n")) {
            ui.showInvalidError(Step.RETRY);
            ui.askUserToRetry();

            if(keyboard.hasNext()) {
                input = keyboard.next();
            } else {
                return false;
            }
        }

        return (input.equals("y"));
    }

}