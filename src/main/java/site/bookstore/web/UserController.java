package site.bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.bookstore.domain.SignupForm;
import site.bookstore.domain.User;
import site.bookstore.domain.UserRepository;


@Controller
public class UserController {

	@Autowired
    private UserRepository repository; 
	
    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
  
    //Lähetetään POST-pyynnöllä saatu Model-olio save-metodille, joka luo Model-olion perusteella ennalta määäritellyn SignupForm-olion.
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) 	{

    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
    			
    			//Haetaan get-metodeilla SignupForm-olion tiedot. Tietojen perusteella luodaan uusi User-olio, 
    			//jonka salasana tallennetaan tietosuojasyistä tarkistemuodossa (hash) tietokantaan.
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	newUser.setEmail(signupForm.getEmail());
		    	//Ennen tallentamista tarkistetaan, ettei valittu käyttäjänimi ole jo käytössä. 
		    	if (repository.findByUsername(signupForm.getUsername()) == null) {
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
	
	
}
