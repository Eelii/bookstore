package site.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import site.bookstore.domain.User;
import site.bookstore.domain.UserRepository;


//Kustomoitu UserDetailService. Spring Security käyttää oletuksena UserDetailServicen vakioasetuksia.
@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final UserRepository repository;
	
	//Liitetään käyttäjätietokanta osaksi Spring Security -toiminnallisuutta.
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 