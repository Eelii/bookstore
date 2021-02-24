package site.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import site.bookstore.domain.Category;
import site.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository; 
	
	@GetMapping("/categorylist")
	public String getCategorylist(Model model, @ModelAttribute Category category){
		List<Category> categories =  (List<Category>) categoryRepository.findAll();
		//Luotu Category-tyyppisistä luokista koostuva lista lisätään model-luokkaan, joka edelleen lähettää categories-listan käytettäväksi
		//osana HTML-tiedostoa nimellä "categories".
		model.addAttribute("categories", categories);
		return "categorylist";
	}
	
	@PostMapping("/savecategory")
	public String postCategorylist(@ModelAttribute Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist";
	}
}
