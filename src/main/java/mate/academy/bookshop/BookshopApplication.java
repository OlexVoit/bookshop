package mate.academy.bookshop;

import java.math.BigDecimal;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookshopApplication {
    @Autowired
   private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("title");
            book.setAuthor("Bob");
            book.setIsbn("1234");
            book.setPrice(BigDecimal.valueOf(99));
            book.setDescription("description");

            bookService.save(book);

            System.out.println(bookService.findAll());
        };
    }
}
