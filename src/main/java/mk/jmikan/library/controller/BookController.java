package mk.jmikan.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.model.EBook;
import mk.jmikan.library.model.PrintCopyBook;
import mk.jmikan.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api("This is my book controller")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "This will get a list of books with their info.")
    // get all books and their info - zadacha 3g
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAll(){
        return bookService.getAll();
    }

    @ApiOperation(value = "This will get a book by Id.")
    // get a book by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @ApiOperation(value = "This will create a new PrintCopyBook.")
    // create a new print book - zadacha 3a
    @PostMapping("/printbook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody PrintCopyBook book){
        return bookService.save(book);
    }

    @ApiOperation(value = "This will create a new EBook.")
    // create a new ebook - zadacha 3a
    @PostMapping("/ebook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody EBook book){
        return bookService.save(book);
    }

    @ApiOperation(value = "This will update PrintCopyBook.")
    // update a print book - zadacha 3b
    @PutMapping("/printbook/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updatePrintBook(@PathVariable Long id, @RequestBody PrintCopyBook book){
        return bookService.updatePrintBook(id, book);
    }

    @ApiOperation(value = "This will update EBook.")
    // update an ebook - zadacha 3b
    @PutMapping("/ebook/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateEBook(@PathVariable Long id, @RequestBody EBook book){
        return bookService.updateEBook(id, book);
    }

    @ApiOperation(value = "This will delete a book by Id.")
    // delete a book by id - zadacha 3v
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    @ApiOperation(value = "This will get all books in chronological order.")
    // find all books in chronological order by year published - zadacha 2a
    @GetMapping("/chronological")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllChronological(){
        return bookService.getAllChronological();
    }

    @ApiOperation(value = "This will get all books that are written by an author who's last name start with String s.")
    // find all books by authors who's last name starts with string s - zadacha 2b
    @GetMapping("/author/{s}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAllByAuthorLNameStartsWith(@PathVariable String s){
        return bookService.getAllByAuthorLNameStartsWith(s);
    }

    @ApiOperation(value = "This will get the oldest book.")
    // find the oldest book - zadacha 2d
    @GetMapping("/oldest")
    @ResponseStatus(HttpStatus.OK)
    public Book getOldest(){
        return bookService.getOldest();
    }

    @ApiOperation(value = "This will get the newest book.")
    // find the newest book - zadacha 2d
    @GetMapping("/newest")
    @ResponseStatus(HttpStatus.OK)
    public Book getNewest(){
        return bookService.getNewest();
    }

}
