package mk.jmikan.library.repository;

import mk.jmikan.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // find all authors who have written more than 3 books - zadacha 2g
    @Query("SELECT a FROM Author a JOIN Book b ON a.id = b.author.id group by a.id having count(a.id) > 3")
    List<Author> findAllByBooksLength();

}
