package nus.iss.paf.miniproject.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.paf.miniproject.models.User;

import static nus.iss.paf.miniproject.repositories.Queries.*;


@Repository
public class UsersRepositories {

    @Autowired
    private JdbcTemplate template;

    public int countUsersByNameAndPassword(String name, String email, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_AND_COUNT_USERS_BY_NAME, name, email, password);
        if (!rs.next())
            return 0;
        return rs.getInt("user_count");
    }

    public boolean insertUser(User user) {
        int count = template.update(SQL_INSERT_USER, user.getUserId(), user.getName(), user.getEmail(), user.getPassword());
        return 1 == count;
    }
    
}