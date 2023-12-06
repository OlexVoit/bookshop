package mate.academy.bookshop.service.impl;

import java.util.List;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.repository.impl.BookRepositoryImpl;
import mate.academy.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepositoryImpl bookRepository;

    @Autowired
    public BookServiceImpl(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
