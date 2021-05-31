package mk.jmikan.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mk.jmikan.library.model.Author;
import mk.jmikan.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api("This is my author controller!")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "This will get all authors and the books they wrote.")
    // get all authors and the book they have written - zadacha 3d
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAll(){
        return authorService.getAll();
    }

    @ApiOperation(value = "This will get an author by Id.")
    // get author by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getById(@PathVariable Long id){
        return authorService.getById(id);
    }

    @ApiOperation(value = "This will create a new author.")
    // create new author
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author){
        return authorService.save(author);
    }

    @ApiOperation(value = "This will delete an author by Id.")
    // delete author
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
    }

    @ApiOperation(value = "This will get authors who wrote more than 3 books.")
    // get all authors who have written more than 3 books - zadacha 2g
    @GetMapping("/more/3")
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAllWithMoreThan3Books(){
        return authorService.getAllWithMoreThan3Books();
    }

    @ApiOperation(value = "This will get authors who were born in the decade that we have books from.")
    // get all authors who have been born in the decade that we have books from - zadacha 2v
    @GetMapping("/decades")
    @ResponseStatus(HttpStatus.OK)
    public Set<Author> getAllByBookDecades(){
        return authorService.getAllByBookDecadesExists();
    }

}
