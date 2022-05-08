package nus.iss.paf.miniproject.repositories;

public interface Queries {

    public final static String SQL_SELECT_AND_COUNT_USERS_BY_NAME = "select count(*) as user_count from users where email = ? and password = ?";
    
}
