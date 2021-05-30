package mk.jmikan.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.repository.BookRepository;
import mk.jmikan.library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository<Book> bookRepository;

    public BookServiceImpl(BookRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Book> getAll() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;
    }

    @Override
    public Book getOne(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found!!!"));
    }

    @Transactional
    @Override
    public Book save(Book book) {
        Book savedBook = bookRepository.save(book);
        log.debug("Saved book id:" + savedBook.getId());
        return savedBook;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // find all in chronological order by year published - zadacha 2a
    @Override
    public Set<Book> getAllChronological() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAllOrderByYearPublished().iterator().forEachRemaining(books::add);
        return books;
    }

    // find all by author who's last name starts with string s - zadacha 2b
    @Override
    public Set<Book> getAllByAuthorLNameStartsWith(String s) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAllByAuthor_LastNameStartsWith(s).iterator().forEachRemaining(books::add);
        return books;
    }

    // find oldest book - zadacha 2d
    @Override
    public Book getOldest() {
        return bookRepository.findTopByOrderByYearPublished();
    }

    // find newest book - zadacha 2d
    @Override
    public Book getNewest() {
        return bookRepository.findTopByOrderByYearPublishedDesc();
    }
}
