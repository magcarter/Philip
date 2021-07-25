/* 
 * Copyright 2020 Rene Bylander at WITC
 */
package edu.witc.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rene Bylander
 * @created Jul 17, 2017
 * @updated Sept, 2020 - to take advantage of JDK 11 strip() method and fix
 * isAnyPositiveNumber which would not accept a single digit number
 * @updated Nov, 2020 - to include validateTypeWithNumber()
 */
public class ValidatorUtil {

    /**
     * Static methods can be access without instantiating the ValidatorUtil
     * class, you must however, go through the class to get to the function
     *
     * @param text: any String variable that you want to see if there is a value
     * in it
     * @return isValid - true, there is a value. false there is not.
     */
    public static boolean hasText(String text) {
        boolean isValid = false;

        if (text != null) {
            //strip() the leading and trailing whitespace then check
            //if the text has a length
            if (text.strip().length() > 0) {
                isValid = true;
            }

        }
        return isValid;
    }//hasText

    public static boolean isPositiveWholeNumber(String text) {
        boolean isValid = false;

        if (hasText(text)) {
            if (text.matches("[0-9]+")) {
                isValid = true;
            }
        }
        return isValid;
    }//end isPositiveWholeNumber

    public static boolean isAlphabetic(String text) {
        //English language string only, with or without spaces
        // ^[a-zA-Z]+( [a-zA-z]+)*$
        boolean isValid = false;

        // Text only, no numbers, spaces or special characters
        //  [a-zA-Z]+
        //Allow a space but not a dash
        // "^\\p{L}+(?: \\p{L}+)*$"
        //Allows a space and/or dash
        // "^\\p{L}+(?:[ \\p{Pd}]\\p{L}+)*$"
        //String must start with at least one, not space
        //String can contain few words, but every word beside 
        //first must have space before it. Strings from any language
        //if(text.matches("^\\p{L}+(?: \\p{L}+)*$")) 
        if (text != null) {
            //work around for St. Paul
            if (text.contains(".")) {
                text = text.replaceAll("[\\.]+", "");
            }
            //this works for a space (La Duke) or a hypen (Lens-Lens)4/3/2020
            if (text.matches("^\\p{L}+(?:[\\s\\p{Pd}]\\p{L}+)*$")) {
                isValid = true;
            }
            //try to get a dot and a space for St. Paul
            //not working yet
            //if(text.matches("^\\p{L}+(?:[\\s\\p{P}]\\p{L}+)*$"))
        }

        return isValid;

    } // End of isAlphabetic()

    public static boolean isAnyWholeNumber(String text) {
        boolean isValid = false;

        if (hasText(text)) {
            if (text.matches("[-?, 0-9]+")) {
                isValid = true;
            }

        }
        return isValid;
    }//end isAnyWholeNumber

    public static boolean isAnyDecimalNumber(String text) {
        /*
            ^           # Beginning of string
            [+-]?       # Optional plus or minus character
            (           # Followed by either:
            (           # Start of first option
            \d+         # One or more digits
            (\.\d*)?    # Optionally followed by: one decimal point and zero or more digits
            )           # End of first option
            |           # or
            (\.\d+)     # One decimal point followed by one or more digits
            )           # End of grouping of the OR options
            $           # End of string (i.e. no extra characters remaining)
         */
        boolean isValid = false;
        if (hasText(text)) {
            if (text.matches("^[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))$")) {
                isValid = true;
            }
        }
        return isValid;
    }//end isAnyDecimalNumber

    public static boolean isAnyPositiveNumber(String text) {
        boolean isValid = false;
        if (hasText(text)) {
            if (text.matches("(\\+)?([0-9]+((\\.)?[0-9]+))")) {
                isValid = true;
            }
        }

        return isValid;
    }//end isAnyPositiveNumber

    //this will get rid of all non-numerical characters INCLUDING a dot
    public static String replaceNonNumerics(String text) {
        //call the hasText to insure the variable text is not null and has data
        if (hasText(text)) {
            text = text.replaceAll("[^\\d]", "");
        }
        return text;
    }

    public static String replaceCharacters(String text) {
        //first argument is a regular express
        //replace all $ and , and - and ( and ) and spaces with an empty string
        if (hasText(text)) {
            text = text.replaceAll("[\\$,\\,\\-,\\(,\\),\\s]+", "");
        }
        return text;
    }

    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern;
        Matcher matcher;
        //This expression handles much more then just making sure the @ 
        //and ._ _ _ is correct...
        final String REGX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //Pattern holds the regular expression
        pattern = Pattern.compile(REGX_EMAIL);
        //matcher object to regex a string against my pattern
        matcher = pattern.matcher(emailAddress);
        //return if email is valid (true) or not (false)
        return matcher.matches();
    }//end isValidEmail

    /**
     * Determines if the credit card number is valid based on the Luhn
     * Algorithm. Credit card must be between 13 and 16 characters long This
     * function can be tested using these numbers Visa: 4111111111111111
     * MasterCard: 5555555555554444 AMEX: 378282246310005 Discover:
     * 6011000990139424
     *
     * @param ccNum a string representing a credit card number
     * @return
     */
    public static boolean isValidCreditCard(String ccNum) {

        int index;
        long number = 0;
        boolean alternate = false;
        long total = 0;
        boolean isValid = false;

        if (hasText(ccNum)) {
            ccNum = replaceNonNumerics(ccNum);
            index = ccNum.length();

            if (!isPositiveWholeNumber(ccNum)) {
                isValid = false;
            } else {
                if (ccNum.length() > 12 && ccNum.length() < 17) {
                    while (index-- > 0) {
                        number = Long.parseLong(ccNum.substring(index, index + 1));
                        if (alternate) {
                            number *= 2;

                            if (number > 9) {
                                number -= 9;
                            }

                        }
                        total += number;

                        alternate = !alternate;
                    }//end of while loop

                    isValid = (total % 10) == 0;

                }//end if for length check
                else {
                    isValid = false;
                }//end else for length check
            }//end else for !isPositiveWholeNumber

        }//end hasText()
        return isValid;
    }//end isValidCreditCard

    public static boolean validateTypeWithNumber(String ccNumber, String cardTypeShortDesc) {

        Pattern pattern = null;
        Matcher matcher = null;
        boolean isValid = false;
        //Remove any non-numeric values
        ccNumber = ValidatorUtil.replaceNonNumerics(ccNumber);

        //regular expression are from the below website
        //https://www.regular-expressions.info/creditcard.html
        switch (cardTypeShortDesc.toLowerCase()) {
            case "visa":
                pattern = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
                break;
//            case "discover":
            case "ds":
                pattern = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
                break;
            case "amex":
                pattern = Pattern.compile("^3[47][0-9]{13}$");
                break;
            case "mc":
                pattern = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
                //pattern = Pattern.compile("^5[1-5][0-9]{14}$");
                //alternate pattern for mc
                //^222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}
                break;
            default:
                break;
        }
        //if pattern is not checked for null, then the matcher part of
        //pattern.matcher has the warning dereferncing possible null pointer
        if (pattern != null) {
            matcher = pattern.matcher(ccNumber);
            isValid = matcher.matches();
        }

        return isValid;
    }

    public static boolean isValidZipCode(String zipCode) {
        //pattern matching for US zip code ONLY
        Pattern pattern;
        Matcher matcher;

        final String REGX_ZIP = "^[0-9]{5}(-[0-9]{4})?$";
        //Pattern holds the regular expression
        pattern = Pattern.compile(REGX_ZIP);
        //matcher object to regex a string against my pattern
        matcher = pattern.matcher(zipCode);
        //return if email is valid (true) or not (false)
        return matcher.matches();
    }//end isValidZipCode

}//end class
