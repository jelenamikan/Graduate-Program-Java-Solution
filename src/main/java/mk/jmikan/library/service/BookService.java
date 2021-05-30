package mk.jmikan.library.service;

import mk.jmikan.library.model.Book;

import java.util.Set;

public interface BookService {

    Set<Book> getAll();

    Book getOne(Long id);

    Book save(Book book);

    void delete(Long id);

    Set<Book> getAllChronological();

    Set<Book> getAllByAuthorLNameStartsWith(String s);

    Book getOldest();

    Book getNewest();

}
