package nus.iss.paf.miniproject.services;

public class UserException extends Exception {

    private String reason;

    public UserException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    
}
