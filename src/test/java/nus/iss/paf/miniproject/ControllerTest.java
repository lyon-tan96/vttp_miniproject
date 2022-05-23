package nus.iss.paf.miniproject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.SearchRepositories;
import nus.iss.paf.miniproject.repositories.UsersRepositories;
import nus.iss.paf.miniproject.services.SearchException;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsersRepositories usersRepo;

    @Autowired
    private SearchRepositories searchRepo;
    
    @Test
    void testLogout() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/authenticate/logout")
                .sessionAttr("email", "lyon@gmail.com"))
                .andExpect(status().isFound());
    }

    @Test
    void testLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=abc123&email=abc123@test.com&password=abc123"))
                .andExpect(status().isForbidden());
    
        mvc.perform(MockMvcRequestBuilders
                .post("/newuser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&email=test@test.com&password=test"))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=test&email=test@test.com&password=test"))
                .andExpect(status().isFound());

        User user = new User();
        user.setEmail("test@gmail.com");
        usersRepo.deleteUser(user);
    }

    @Test
    void testProtectedView() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/protected/search")
                .sessionAttr("email", "lyon@gmail.com")
                .sessionAttr("name", "lyon"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearch() throws Exception {
        
        mvc.perform(MockMvcRequestBuilders
                .get("/search/cards")
                .queryParam("cardName", "zapdos")
                .sessionAttr("email", "lyon@gmail.com"))
                .andExpect(status().isOk());
        
        searchRepo.deleteSearchHistory("lyon@gmail.com", "zapdos");

    }

    @Test
    void badTestSearch() throws SearchException {
        try {
            mvc.perform(MockMvcRequestBuilders
                    .get("/search/cards")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .content("cardName=testing123")
                    .sessionAttr("email", "lyon@gmail.com"))
                    .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
