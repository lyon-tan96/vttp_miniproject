package nus.iss.paf.miniproject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.UsersRepositories;
import nus.iss.paf.miniproject.services.UserException;
import nus.iss.paf.miniproject.services.UsersService;

@SpringBootTest
@AutoConfigureMockMvc
class MiniprojectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UsersRepositories usersRepo;

	@Autowired
	private UsersService usersSvc;

	@Test
	void contextLoads() {
	}

	private User fakeUserInfo() {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString().substring(0, 8));
		user.setName("test");
		user.setEmail("test@example.com");
		user.setPassword("test");
		return user;
	}

	@BeforeEach
	public void createTestUser() {
		try {
			usersSvc.addNewUser(fakeUserInfo());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterEach
	public void deleteTestUser() {
		usersRepo.deleteUser(fakeUserInfo());
	}

	@Test
	public void userShouldExist() {
		assertTrue(usersSvc.authenticate("test", "test@example.com", "test"));
	}


}
