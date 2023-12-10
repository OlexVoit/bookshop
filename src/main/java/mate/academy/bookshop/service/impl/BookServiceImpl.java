package mate.academy.bookshop.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.BookDto;
import mate.academy.bookshop.dto.CreateBookRequestDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.mapper.BookMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.repository.impl.BookRepositoryImpl;
import mate.academy.bookshop.service.BookService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepositoryImpl bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findById(Long id) {
        Optional<Book> bookById = bookRepository.findBookById(id);

        return bookMapper.toDto(bookById.orElseThrow(
                () -> new EntityNotFoundException("Can't find a book by ID: " + id)
        ));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
