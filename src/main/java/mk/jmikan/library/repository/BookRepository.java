package mk.jmikan.library.repository;

import mk.jmikan.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository<T extends Book> extends JpaRepository<T, Long>{

    // find all in chronological order by year published - zadacha 2a
    List<T> findAllOrderByYearPublished();

    // find all by author who's last name starts with string s - zadacha 2b
    List<T> findAllByAuthor_LastNameStartsWith(String s);

    // find oldest book - zadacha 2d
    T findTopByOrderByYearPublished();

    // find newest book - zadacha 2d
    T findTopByOrderByYearPublishedDesc();

    // find books by decades - zadacha 2v
    Optional<Book> findFirstByYearPublishedGreaterThanAndYearPublishedLessThan(Integer i1, Integer i2);

}
