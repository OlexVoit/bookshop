package mate.academy.bookshop.repository.impl;

import java.util.List;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private static final String CANNOT_SAVE_BOOK_TO_DB =
            "Cannot save book to DB: ";
    private static final String FAILED_GET_ALL_BOOKS_FROM_DB =
            "Failed to get all the books from DB!";

    private final SessionFactory factory;

    @Autowired
    public BookRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(CANNOT_SAVE_BOOK_TO_DB + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = factory.openSession()) {
            Query<Book> query = session.createQuery("FROM Book", Book.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(FAILED_GET_ALL_BOOKS_FROM_DB);
        }
    }
}
