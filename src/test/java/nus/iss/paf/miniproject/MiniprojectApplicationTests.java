package nus.iss.paf.miniproject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.SearchRepositories;
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

	@Autowired
	private SearchRepositories searchRepo;

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
		searchRepo.deleteSearchHistory("test@example.com", "pikachu");
		usersRepo.deleteUser(fakeUserInfo());
	}

	@Test
	public void userShouldExist() {
		assertTrue(usersSvc.authenticate("test", "test@example.com", "test"));
	}

	@Test
	public void shouldLoadImages() {
		assertFalse(cardSvc.getCards("pikachu", "test@example.com").isEmpty());
	}

	@Test
	public void searchShouldFail() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			cardSvc.getCards("abc123", "test@gmail.com");
		});
		String expectedMessage = "Results not found for %s.".formatted("abc123");
		String actualMessage = ex.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void insertTestAccountShouldFail() {
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

	@Test
	public void findUser() {
		assertTrue(usersRepo.findUserByEmail("test@example.com").isPresent());
	}

}
