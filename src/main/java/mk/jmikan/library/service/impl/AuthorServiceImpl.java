package mk.jmikan.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.jmikan.library.model.Author;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.repository.AuthorRepository;
import mk.jmikan.library.repository.BookRepository;
import mk.jmikan.library.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository<Book> bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository<Book> bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // get all authors and their books - zadacha 3d
    @Override
    public Set<Author> getAll() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().iterator().forEachRemaining(authors::add);
        return authors;
    }

    // get author by id
    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Not Found!!!"));
    }

    // create new author
    @Transactional
    @Override
    public Author save(Author author) {
        Author savedAuthor = authorRepository.save(author);
        log.debug("Saved author id:" + savedAuthor.getId());
        return savedAuthor;
    }

    // delete author by id
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    // find all authors who have written more than 3 books - zadacha 2g
    @Override
    public Set<Author> getAllWithMoreThan3Books() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAllByBooksLength().iterator().forEachRemaining(authors::add);
        return authors;
    }

    // find authors and books by decades - zadacha 2v
    @Override
    public Set<Author> getAllByBookDecadesExists() {
        Set<Author> allAuthors = new HashSet<>();
        authorRepository.findAll().iterator().forEachRemaining(allAuthors::add);
        Set<Integer> startYears = new HashSet<>();
        Set<Author> authorsExist = new HashSet<>();
        for (Author a : allAuthors) {
            int year = a.getBirthYear() / 10 * 10;
            log.debug("Birth year is:" + a.getBirthYear() + "start year is:" + year);
            if (!startYears.contains(year)) {
                Optional<Book> book = bookRepository
                        .findFirstByYearPublishedGreaterThanAndYearPublishedLessThan(year, year + 10);
                if (book.isPresent()) {
                    startYears.add(year);
                    authorsExist.add(a);
                }
            } else {
                authorsExist.add(a);
            }
        }
        return authorsExist;
    }
}
