package mk.jmikan.library.service;

import mk.jmikan.library.model.Author;

import java.util.Set;

public interface AuthorService {

    Set<Author> getAll();

    Author getById(Long id);

    Author save(Author author);

    void delete(Long id);

    Set<Author> getAllWithMoreThan3Books();

    Set<Author> getAllByBookDecadesExists();

}
