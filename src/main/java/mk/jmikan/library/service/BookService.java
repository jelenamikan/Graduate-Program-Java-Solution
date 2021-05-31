package mk.jmikan.library.service;

import mk.jmikan.library.model.Book;
import mk.jmikan.library.model.EBook;
import mk.jmikan.library.model.PrintCopyBook;

import java.util.List;
import java.util.Set;

public interface BookService<T extends Book> {

    Set<Book> getAll();

    Book getById(Long id);

    T save(T book);

    Book updateEBook(Long id, EBook book);

    Book updatePrintBook(Long id, PrintCopyBook book);

    void delete(Long id);

    List<Book> getAllChronological();

    Set<Book> getAllByAuthorLNameStartsWith(String s);

    Book getOldest();

    Book getNewest();

}
