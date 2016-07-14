package com.supsms.web.form;

import javax.servlet.http.HttpServletRequest;

/**
 * Aide à la validation des formulaires
 */
public class FormValidator {

    /**
     * Obtenir la valeur d'un champs de formulaire
     * @param req wrapper de requête
     * @param field nom du champs
     * @return contenu du champs
     */
    public static String getField(HttpServletRequest req, String field) {
        String value = req.getParameter(field);
        if (value == null || value.trim().length() == 0) {
            return null;
        }else{
            return value;
        }
    }

    /**
     * Obtenir les valeurs d'une serie de check boxes.
     * @param req wrapper de requête
     * @param field nom du champs
     * @return collection de contenus des check boxes
     */
    public static String[] getFieldCB(HttpServletRequest req, String field) {
        String[] value = req.getParameterValues(field);
        if (value == null || value.length == 0) {
            return null;
        }else{
            return value;
        }
    }
    
    /**
     * Valider une chaine de caractères
     * @param str chaine à valider
     * @param isNullable peut être null ? (true)
     * @param minLength nombre de caractères minimum
     * @return chaine de caractère si valide
     * @throws FormValidatorException 
     */
    public static String validateString(String str, Boolean isNullable, Integer minLength) throws FormValidatorException {
        if(!isNullable && (str == null || str.isEmpty())){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else if(str != null && str.length() < minLength){
            throw new FormValidatorException(FormValidatorException.Issue.TOO_SHORT);
        }else{
            return str;
        }
    }

    /**
     * Valider un entier
     * @param integer entier à valider
     * @param nullable peut être null ? (true)
     * @param minValue valeur minimale
     * @param maxValue valeur maximale
     * @return entier si valide
     * @throws FormValidatorException 
     */
    public static Integer validateInteger(String integer, Boolean nullable, Integer minValue, Integer maxValue) throws FormValidatorException {
        Integer tmpInteger;

        if(!nullable && integer == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else{
            try {
                tmpInteger = Integer.parseInt(integer);

                if(minValue != null && tmpInteger < minValue){
                    throw new FormValidatorException(FormValidatorException.Issue.TOO_LOW);
                }

                if(maxValue != null && tmpInteger > maxValue){
                    throw new FormValidatorException(FormValidatorException.Issue.TOO_HIGH);
                }
            }catch(NumberFormatException e){
                throw new FormValidatorException(FormValidatorException.Issue.TYPE);
            }
        }

        return tmpInteger;
    }

    /**
     * Valider un entier
     * @param integer entier à valider
     * @param isNullable peut être null ? (true)
     * @return entier si valide
     * @throws FormValidatorException 
     */
    public static Integer validateInteger(String integer, Boolean isNullable) throws FormValidatorException {
        if(!isNullable && integer == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        } else if (integer != null) {
            try {
                return Integer.parseInt(integer);
            } catch(NumberFormatException e) {
                throw new FormValidatorException(FormValidatorException.Issue.TYPE);
            }
        }
        return null;
    }

    /**
     * Valider un double
     * @param doubleVal double à valider
     * @param nullable peut être null ? (true)
     * @param minValue valeur minimale
     * @param maxValue valeur maximale
     * @return double si valide
     * @throws FormValidatorException 
     */
    public static Double validateDouble(String doubleVal, Boolean nullable, Double minValue, Double maxValue) throws FormValidatorException {
        Double tmpDouble;

        if(!nullable && doubleVal == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else{
            try {
                tmpDouble = Double.parseDouble(doubleVal);

                if(minValue != null && tmpDouble < minValue){
                    throw new FormValidatorException(FormValidatorException.Issue.TOO_LOW);
                }

                if(maxValue != null && tmpDouble > maxValue){
                    throw new FormValidatorException(FormValidatorException.Issue.TOO_HIGH);
                }
            }catch(NumberFormatException e){
                throw new FormValidatorException(FormValidatorException.Issue.TYPE);
            }
        }

        return tmpDouble;
    }

    /**
     * Valider un booléen
     * @param bool booléen à valider
     * @return booléen si valide
     * @throws FormValidatorException 
     */
    static Boolean validateBoolean(String bool) throws FormValidatorException {
        Boolean boo;
        if(bool == null || bool.isEmpty()) {
            boo = false;
        }else{
            boo = true;
        }
        return boo;
    }

    /**
     * Valider deux mots de passe
     * @param pwd1 premier mot de passe
     * @param pwd2 confirmation du mot de passe
     * @param nullable peuvent être null ? (true)
     * @param minLength nombre minimum de caractères
     * @return mot de passe en clair si valide
     * @throws FormValidatorException 
     */
    public static String validatePassword(String pwd1, String pwd2, Boolean nullable, Integer minLength) throws FormValidatorException {
        validateString(pwd1, true, minLength);
        validateString(pwd2, true, minLength);
        if((!nullable && pwd1 == null) || (!nullable && pwd2 == null)){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else if((pwd1 != null && minLength != null && pwd1.length() < minLength) || ( pwd2 != null && minLength != null && pwd2.length() < minLength)){
            throw new FormValidatorException(FormValidatorException.Issue.TOO_SHORT);
        } else if(!pwd1.equals(pwd2)) {
            throw new FormValidatorException(FormValidatorException.Issue.DIFFERENT);
        }else{
            return pwd1;
        }
    }

    /**
     * Valider une adresse mail
     * @param email adresse mail à valider
     * @param nullable peut être null ? (true)
     * @return adresse mail si valide
     * @throws FormValidatorException 
     */
    public static String validateEmail(String email, Boolean nullable) throws FormValidatorException {
        if(!nullable && email == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else if(email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
            throw new FormValidatorException(FormValidatorException.Issue.ANOMALY);
        }else{
            return email;
        }
    }

    /**
     * Valider une URL
     * @param url URL à valider
     * @param nullable peut être null ? (true)
     * @return url si valide
     * @throws FormValidatorException 
     */
    public static String validateUrl(String url, Boolean nullable) throws FormValidatorException {
        if(!nullable && url == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else if(url != null && !url.matches("^(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            throw new FormValidatorException(FormValidatorException.Issue.ANOMALY);
        }else{
            return url;
        }
    }

    /**
     * Valider un numéro de téléphone Français
     * @param phoneNumber numéro de téléphone à valider
     * @param nullable peut être null ? (true)
     * @return numéro de téléphone en série de 10 chiffres continus si valide
     * @throws FormValidatorException 
     */
    public static String validatePhone(String phoneNumber, Boolean nullable) throws FormValidatorException {
        if(!nullable && phoneNumber == null){
            throw new FormValidatorException(FormValidatorException.Issue.NULL);
        }else if(phoneNumber != null && !phoneNumber.matches("^0[1-68][0-9]{8}$")) {
            throw new FormValidatorException(FormValidatorException.Issue.ANOMALY);
        }else{
            return phoneNumber;
        }
    }
}
