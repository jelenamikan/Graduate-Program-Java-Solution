package mk.jmikan.library.repository;

import mk.jmikan.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository<T extends Book> extends JpaRepository<T, Long>{

    // find all in chronological order by year published - zadacha 2a
    List<Book> findAllOrderByYearPublished();

    // find all by author who's last name starts with string s - zadacha 2b
    List<Book> findAllByAuthor_LastNameStartsWith(String s);

    // find oldest book - zadacha 2d
    Book findTopByOrderByYearPublished(Integer i);

    // find newest book - zadacha 2d
    Book findTopByOrderByYearPublishedDesc(Integer i);

}
