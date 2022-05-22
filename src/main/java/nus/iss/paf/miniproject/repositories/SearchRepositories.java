package nus.iss.paf.miniproject.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static nus.iss.paf.miniproject.repositories.Queries.*;

@Repository
public class SearchRepositories {

    @Autowired
    private JdbcTemplate template;

    public boolean insertSearchHistory(String cardName, String email) {
        int count = template.update(SQL_UPDATE_SEARCH_HISTORY, cardName, email);
        return 1 == count;
    }

    public boolean deleteSearchHistory(String email) {
        int count = template.update(SQL_DELETE_SEARCH_HISTORY_FOR_EMAIL, email);
        return 1 == count;
    }
    
}
