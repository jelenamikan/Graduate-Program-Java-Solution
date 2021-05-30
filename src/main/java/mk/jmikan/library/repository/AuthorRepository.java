package mk.jmikan.library.repository;

import mk.jmikan.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // find all authors who have written more than int i books - zadacha 2g
    List<Author> findAllByBooksGreaterThan(Integer i);

    // zadacha 2v - to do

}
