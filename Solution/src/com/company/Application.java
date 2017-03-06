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
        String input;
        Scanner keyboard = new Scanner(in);

        do {

            ui.askUserForDrivingLicence();

            if (keyboard.hasNext()) {
                input = keyboard.next();
            } else {
                return null;
            }

            if (input.length() < 16) {
                ui.showInvalidError(DRIVING_LICENCE);
            }

        } while (input.length() < 16);

        return input;
    }

    /**
     * For getting the licence type from the licenceType list, based on user's entry
     *
     * @param in InputStream
     * @return String licence type
     */
    public String getLicenceType(InputStream in) {
        String input = null;
        boolean isExistng = false;
        Scanner keyboard = new Scanner(in);

        do {

            ui.askUserForLicenceType();

            if (keyboard.hasNext()) {
                input = keyboard.next().toLowerCase();
            } else {
                return input;
            }

            for (String licence : licenceTypes) {
                if (input.toLowerCase().equals(licence)) {
                    isExistng = true;
                }
            }

            if (!isExistng) {
                ui.showInvalidError(TAXI_LICENCE);
            }

        } while (!isExistng);

        return input;
    }


    /**
     * For getting the user's age
     *
     * @param in InputStream
     * @return Integer user age
     */
    public int getUserAge(InputStream in) {
        Scanner keyboard = new Scanner(in);
        Integer input;

        do {

            ui.askUserForAge();

            if (keyboard.hasNext()) {
                input = getIntegerValue(keyboard.next());
            } else {
                return 0;
            }

            if (input == 0) {
                ui.showInvalidError(AGE);
            }

        } while (input == 0);

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
        Integer input;

        do {

            ui.askUserForRenewalLength();

            if (keyboard.hasNext()) {
                input = getIntegerValue(keyboard.next());
            } else {
                return 0;
            }

            if (input == 0) {
                ui.showInvalidError(RENEWAL_LENGTH);
            } else if (input > RenewalTaxiLicence.MAX_RENEWAL_LENGTH) {
                ui.showEligibilityMessage(RENEWAL_LENGTH);
            }

        } while (input == 0 || input > RenewalTaxiLicence.MAX_RENEWAL_LENGTH);

        return input;
    }

    /**
     * Confirm if the user wants to retry
     *
     * @param in InputStream
     * @return boolean
     */
    public boolean isRetry(InputStream in) {
        String input;
        Scanner keyboard = new Scanner(in);

        do {

            ui.askUserToRetry();

            if (keyboard.hasNext()) {
                input = keyboard.next().toLowerCase();
            } else {
                return false;
            }

            if (!input.equals("y") && !input.equals("n")) {
                ui.showInvalidError(Step.RETRY);
            }

        } while (!input.equals("y") && !input.equals("n"));

        return (input.equals("y"));
    }

}