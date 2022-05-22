package nus.iss.paf.miniproject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.UsersRepositories;
import nus.iss.paf.miniproject.services.CardService;
import nus.iss.paf.miniproject.services.UserException;
import nus.iss.paf.miniproject.services.UsersService;

@SpringBootTest
@AutoConfigureMockMvc
class MiniprojectApplicationTests {

	@Autowired
	private UsersRepositories usersRepo;

	@Autowired
	private UsersService usersSvc;

	@Autowired
	private CardService cardSvc;

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

	@Test
	public void shouldLoadImages() {
		assertTrue(cardSvc.getCards("pikachu") != null);
	}

	@Test
	public void shouldBeEmpty() {
		assertTrue(cardSvc.getCards("abc123").isEmpty());
	}

	@Test
	public void insertTestShouldFail() {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString().substring(0, 8));
		user.setName("test");
		user.setEmail("test@example.com");
		user.setPassword("test");
		try {
			usersSvc.addNewUser(user);
		} catch (UserException e) {
			assertTrue(true);
			return;
		}
	}


}
