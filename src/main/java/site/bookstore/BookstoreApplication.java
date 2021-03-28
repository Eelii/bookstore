package site.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import site.bookstore.domain.Book;
import site.bookstore.domain.BookRepository;
import site.bookstore.domain.Category;
import site.bookstore.domain.CategoryRepository;
import site.bookstore.domain.User;
import site.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean public CommandLineRunner demo(BookRepository repository, CategoryRepository repository2, UserRepository urepository) {
		return (args) ->{
			Category c1 = new Category("Comedy");
			Category c2 = new Category("Fantasy");
			repository2.save(c1);
			repository2.save(c2);
			Book book1 = new Book("Self Help for Dummies", "Phony Robbers", 1984, "ISBNLOLXD", 99, c1);
			Book book2 = new Book("How to Influence Friends and Win People", "Kari Kalmasuo", 2012, "ISBNOMGWTF", 2, c2);
			repository.save(book1);
			repository.save(book2);
			
			User admin =new User("admin","$2a$04$v3NzF6VipLCyJNzYtado/e7F.0FPx.vtfr58/8JfUoAdl0d6GQEPi","ADMIN", "sdf@dsfsd.fi");
			urepository.save(admin);
		};
	}
}
