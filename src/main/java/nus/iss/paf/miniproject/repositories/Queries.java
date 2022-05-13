package nus.iss.paf.miniproject.repositories;

public interface Queries {

    public final static String SQL_SELECT_AND_COUNT_USERS_BY_NAME = "select count(*) as user_count from users where name = ? and email = ? and password = sha1(?)";

    public final static String SQL_INSERT_USER = "insert into users (user_id, name, email, password) values (?, ?, ?, sha1(?))";

    public final static String SQL_SELECT_USER_BY_EMAIL = "select * from users where email = ?";
    
}
