package site.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/index")
	public String getBooksIndex(Model model){
		/*model.addAttribute("????",????)*/
		return "booksIndex";
	}

}
