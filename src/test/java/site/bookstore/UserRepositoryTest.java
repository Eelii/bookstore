package site.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import site.bookstore.domain.User;
import site.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)    // JUnit5 eli Jupiter
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void findByUsername() throws Exception {
		User user = userRepo.findByUsername("admin");
		assertThat(user).isNotNull();
		assertThat(user.getUsername()).isEqualTo("admin");
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void deleteAdmin() throws Exception {
		User admin = userRepo.findByUsername("admin");
		userRepo.deleteById(admin.getId());
		User adminAgain = userRepo.findByUsername("admin");
		assertThat(adminAgain).isNull();
	}
	
	@Test
	public void createUser() throws Exception {
		User testUser = new User("TestUser", "$2a$04$pPdjKw.pQtdGtlyMI4E8ou5ZGJCpXCwaU9hjyCaPJyHoYzrTmNKAW", "USER", "testing@test.com");
		userRepo.save(testUser);
		assertThat(userRepo.findByUsername("TestUser")).isNotNull();
	}
}
