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

import site.bookstore.domain.Category;
import site.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)  
@DataJpaTest
public class CategoryRepositoryTest {

	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Test
	public void findByName() throws Exception {
		assertThat(categoryRepo.findByName("Comedy")).isNotNull();
		assertThat(categoryRepo.findByName("Comedy").getName()).isEqualTo("Comedy");
	}
	
	@Test
	public void createCategory() throws Exception {
		Category category = new Category("testi");
		categoryRepo.save(category);
		Category testCategory = categoryRepo.findByName("testi");
		assertThat(testCategory).isNotNull();
		assertThat(testCategory.getCatId()).isNotNull();
	}
	
	@Test
	public void deleteAllCategories() throws Exception {
		categoryRepo.deleteAll();
		assertThat(categoryRepo.findAll()).isNotNull();
	}
	
}
