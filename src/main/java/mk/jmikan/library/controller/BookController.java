package mk.jmikan.library.controller;

import mk.jmikan.library.model.Book;
import mk.jmikan.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping("/chronological")
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAllChronological(){
        return bookService.getAllChronological();
    }

    @GetMapping("/author/{s}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAllByAuthorLNameStartsWith(@PathVariable String s){
        return bookService.getAllByAuthorLNameStartsWith(s);
    }

    @GetMapping("/oldest")
    @ResponseStatus(HttpStatus.OK)
    public Book getOldest(){
        return bookService.getOldest();
    }

    @GetMapping("/newest")
    @ResponseStatus(HttpStatus.OK)
    public Book getNewest(){
        return bookService.getNewest();
    }

}
