package site.bookstore;



import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import site.bookstore.domain.Book;
import site.bookstore.domain.BookRepository;
import site.bookstore.domain.Category;

@RunWith(SpringRunner.class)  
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository book;
	
	@Test
	public void findByAuthorWorks() throws Exception {
		List <Book> books = book.findByAuthor("Phony Robbers");
		assertThat(books).hasSize(1);
		assertThat(books).isNotNull();
		assertThat(books).hasOnlyElementsOfType(Book.class);
		assertThat(books.get(0).getAuthor()).isEqualTo("Phony Robbers");
	}
	
	@Test 
	public void deleteById() throws Exception {
		long id = Long.valueOf(3);
		book.deleteById(id);
		assertThat(book.findById(id).isEmpty());
	}
	
	@Test
	public void createBook() throws Exception {
		Book aBook = new Book("Test", "Test Testings", 9999, "ISBNTEST", 1, new Category("Testing"));
		book.save(aBook);
	}
}
