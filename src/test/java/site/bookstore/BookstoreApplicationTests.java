package site.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import site.bookstore.web.BookController;
import site.bookstore.web.CategoryController;
import site.bookstore.web.UserController;

@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BookController book;
	
	@Autowired
	private CategoryController category;
	
	@Autowired 
	private UserController user;
	
	//Smoke test
	@Test
	public void contextLoads() throws Exception {
		assertThat(book).isNotNull();
		assertThat(category).isNotNull();
		assertThat(user).isNotNull();
	}

}
