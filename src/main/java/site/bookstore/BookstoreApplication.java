package site.bookstore;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import site.bookstore.domain.Book;
import site.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean public CommandLineRunner demo(BookRepository repository) {
		return (args) ->{
			Book book1 = new Book("Self Help for Dummies", "Phony Robbers", 1984, "ISBNLOLXD", 99);
			Book book2 = new Book("How to Influence Friends and Win People", "Kari Kalmasuo", 2012, "ISBNOMGWTF", 2);
			
			repository.save(book1);
			repository.save(book2);
			
		};
	}
}
