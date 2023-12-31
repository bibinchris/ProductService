package com.poductservice.productservice;

import com.poductservice.productservice.aop.ExecutionTimeLogger;
import com.poductservice.productservice.inheritanceRelations.singleTable.*;
import com.poductservice.productservice.models.Category;
import com.poductservice.productservice.models.Price;
import com.poductservice.productservice.repositories.PriceRepository;
import com.poductservice.productservice.models.Product;
import com.poductservice.productservice.repositories.CategoryRepository;
import com.poductservice.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProductServiceApplication {//implements CommandLineRunner {

//    private MentorRepository mentorRepository;
//    private StudentRepository studentRepository;
//    private UserRepository userRepository;
//    private CategoryRepository categoryRepository;
//    private ProductRepository productRepository;
//    private PriceRepository priceRepository;

    /*public ProductServiceApplication(@Qualifier("st_mentorrepository") MentorRepository mentorRepository,
                                     @Qualifier("st_studentrepository") StudentRepository studentRepository,
                                     @Qualifier("st_userrepository") UserRepository userRepository,
                                     CategoryRepository categoryRepository,
                                     ProductRepository productRepository,
                                     PriceRepository priceRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }*/

//    public ProductServiceApplication() {
//    }
//
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    /**
     * in lazy loading data will be only fetch when we call it explicitly
     *
     * @param args
     * @Transactional is used to call lazy loading explicitly
     */
   /* @Transactional
    @Override
    public void run(String... args) {
//        testInheritanceRelations();
//        saveRecordsInDB();
//        fetchCategoryFromDB();
//        fetchProductFromDB();
    }*/


    /*private void saveRecordsInDB() {
        Category category = new Category();
        category.setName("Apple Device");

        Category savedCategory = categoryRepository.save(category);

        Price price = new Price();
        price.setCurrency("INR");
        price.setValue(150000);
        Price psavedPrice = priceRepository.save(price);

        Product product = new Product();
        product.setTittle("iPhone 15");
        product.setDescription("Best iPhone ever");
        product.setCategory(savedCategory);
        product.setPrice(psavedPrice);
        Product savedProduct = productRepository.save(product);
    }


    public void fetchCategoryFromDB() {
        Optional<Category> allCategory = categoryRepository.findById(UUID.fromString("4856ef34-15e5-4488-b478-65efec9aa1b9"));
        Category category = allCategory.get();
        System.out.println("category.getName() = " + category.getName());
        System.out.println("getDescription = " + category.getProducts().get(0).getDescription());
    }


    public void fetchProductFromDB() {
        List<Product> allCategory = productRepository.findAll();
        for (Product p : allCategory) {
            System.out.println("p1 = " + p.getDescription());
        }

        List<Product> allCategory1 = productRepository.findByTittle("iPhone 15");
        for (Product p : allCategory1) {
            System.out.println("p2 = " + p.getDescription());
        }

        List<Product> allCategory2 = productRepository.findByTittleAndDescription("iPhone 15", "Best iPhone ever");
        for (Product p : allCategory2) {
            System.out.println("p3 = " + p.getDescription());
        }

        List<Product> allCategory3 = productRepository.findByPrice_ValueGreaterThan(50000);
        for (Product p : allCategory3) {
            System.out.println("p4 = " + p.getDescription());
        }

        List<Product> allCategory4 = productRepository.findAllProductByCustomQuery();
        for (Product p : allCategory4) {
            System.out.println("p4 = " + p.getDescription());
        }
    }

    private void testInheritanceRelations() {
        Mentor mentor = new Mentor();
        mentor.setName("Bibin");
        mentor.setEmail("bibin0chris@gmail.com");
        mentor.setAvgRating(4.8);
        Mentor m = this.mentorRepository.save(mentor);
        System.out.println("m = " + m);

        Student student = new Student();
        student.setName("Abhi");
        student.setEmail("Abhi@xyz.com");
        student.setPsp(88);
        studentRepository.save(student);

        User user = new User();
        user.setName("Jaydu");
        user.setEmail("Jaydu@xyz.com");
        userRepository.save(user);

        List<User> userList = userRepository.findAll();

        userList.forEach(User -> System.out.println(user));
    }*/
}
