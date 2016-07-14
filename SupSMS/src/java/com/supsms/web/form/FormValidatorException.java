package com.supsms.web.form;

/**
 * Exception lors de la validation
 */
public class FormValidatorException extends Exception {
    public static enum Issue{ NULL, TOO_SHORT, TOO_LONG, DIFFERENT, TYPE, TOO_LOW, TOO_HIGH, ANOMALY}
    Issue issue;
    
    /**
     * Créer une exception
     * @param issue nature de l'exception
     */
    public FormValidatorException(Issue issue){
        super(issue.toString());
        this.issue = issue;
    }

    /**
     * Nature de l'exception
     * @return nature de l'exception parmis les natures disponibles
     */
    public Issue getIssue() {
        return issue;
    }
}
