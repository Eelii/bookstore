package site.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.bookstore.domain.Book;
import site.bookstore.domain.BookRepository;
import site.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CategoryRepository categoryRepository; 
	
	@GetMapping("/index")
	public String getBooksIndex(Model model){
		/*model.addAttribute("????",????)*/
		return "booksIndex";
	}
	
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {	
    	return bookRepository.findById(id);
    } 
	
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest(){
		return(List<Book>) bookRepository.findAll();
	}     
	
	@GetMapping("/booklist")
	public String getBooklist(Model model) {
		List<Book> books =  (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	@GetMapping("/addbook")
	public String getAddbook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String postAddbook(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id) {
		bookRepository.deleteById(Id);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long Id, Model model) {
		
		Optional<Book> op = bookRepository.findById(Id);
		model.addAttribute("book", op.get());
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}

}
