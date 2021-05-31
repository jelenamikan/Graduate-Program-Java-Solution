package mk.jmikan.library.controller;

import mk.jmikan.library.model.Author;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // get all authors and the book they have written - zadacha 3d
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAll(){
        return authorService.getAll();
    }

    // get author by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getById(@PathVariable Long id){
        return authorService.getById(id);
    }

    // create new author
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author){
        return authorService.save(author);
    }

    // delete author
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
    }

    // get all authors who have written more than 3 books - zadacha 2g
    @GetMapping("/more/3")
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAllWithMoreThan3Books(){
        return authorService.getAllWithMoreThan3Books();
    }

    // get all authors who have been born in the decade that we have books from - zadacha 2v
    @GetMapping("/decades")
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAllByBookDecades(){
        return authorService.getAllByBookDecadesExists();
    }

}
