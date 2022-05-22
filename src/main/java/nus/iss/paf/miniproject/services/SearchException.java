package nus.iss.paf.miniproject.services;

public class SearchException extends Exception{

    private String reason;

    public String getReason() {
        return reason;
    }

    public SearchException(String reason) {
        this.reason = reason;
    }

    
}
