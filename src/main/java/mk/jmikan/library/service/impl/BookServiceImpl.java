package mk.jmikan.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.jmikan.library.model.Author;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.model.EBook;
import mk.jmikan.library.model.PrintCopyBook;
import mk.jmikan.library.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

    protected BookServiceImpl(BookRepository<Book> bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // get all book and their info - zadacha 3g
    @Override
    public Set<Book> getAll() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;
    }

    // get book by id
    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found!!!"));
    }

    // create new book - zadacha 3a
    @Transactional
    @Override
    public Book save(Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author Not Found!!!"));
        Book savedBook = bookRepository.save(book);
        log.debug("Saved book id:" + savedBook.getId());
        author.getBooks().add(savedBook);
        return savedBook;
    }

    // update ebook - zadacha 3b
    @Transactional
    @Override
    public Book updateEBook(Long id, EBook book) {
        Book oldBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found!!!"));
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author Not Found!!!"));

        EBook eBook = new EBook();
        eBook.setId(id);
        eBook.setAuthor(author);
        eBook.setIsbn(book.getIsbn());
        eBook.setTitle(book.getTitle());
        eBook.setYearPublished(book.getYearPublished());
        eBook.setFormat(book.getFormat());
        eBook.setMbSize(book.getMbSize());
        Book savedBook = bookRepository.save(eBook);
        log.debug("Saved book id:" + savedBook.getId());
        return savedBook;
    }

    // update print copy book - zadacha 3b
    @Transactional
    @Override
    public Book updatePrintBook(Long id, PrintCopyBook book){
        Book oldBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found!!!"));
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author Not Found!!!"));

        PrintCopyBook printCopyBook = new PrintCopyBook();
        printCopyBook.setId(id);
        printCopyBook.setAuthor(author);
        printCopyBook.setIsbn(book.getIsbn());
        printCopyBook.setTitle(book.getTitle());
        printCopyBook.setYearPublished(book.getYearPublished());
        printCopyBook.setWeight(book.getWeight());
        printCopyBook.setNumPages(book.getNumPages());
        Book savedBook = bookRepository.save(printCopyBook);
        log.debug("Saved book id:" + savedBook.getId());
        return savedBook;
    }

    // delete book by id - zadacha 3v
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // find all in chronological order by year published - zadacha 2a
    @Override
    public List<Book> getAllChronological() {
//        Set<Book> books = new HashSet<>();
//        bookRepository.findByOrderByYearPublished().iterator().forEachRemaining(books::add);
        return bookRepository.findByOrderByYearPublished();
    }

    // find all by author who's last name starts with string s - zadacha 2b
    @Override
    public Set<Book> getAllByAuthorLNameStartsWith(String s) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAllByAuthor_LastNameStartsWithIgnoreCase(s).iterator().forEachRemaining(books::add);
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
