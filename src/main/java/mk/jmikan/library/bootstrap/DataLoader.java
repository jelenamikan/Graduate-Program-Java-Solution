package mk.jmikan.library.bootstrap;

import mk.jmikan.library.model.*;
import mk.jmikan.library.service.AuthorService;
import mk.jmikan.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;

    public DataLoader(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Author a1 = Author.builder().firstName("Фјодор").lastName("Достоевски").birthYear(1821).build();
        Author a2 = Author.builder().firstName("Херман").lastName("Хесе").birthYear(1877).build();
        Author a3 = Author.builder().firstName("Чарлс").lastName("Буковски").birthYear(1920).build();
        Author a4 = Author.builder().firstName("Иво").lastName("Андриќ").birthYear(1892).build();

        Author a1Saved = authorService.save(a1);
        Author a2Saved = authorService.save(a2);
        Author a3Saved = authorService.save(a3);
        Author a4Saved = authorService.save(a4);

        PrintCopyBook b1 = createPrintBook("9780743487634", "Злосторство и казна",
                a1Saved, 1866, 704, 350);

        PrintCopyBook b2 = createPrintBook("0374528373", "Браќа Карамазови",
                a1Saved, 1879, 824, 400);

        EBook b3 = createEBook("0030853184", "Степскиот волк", a2Saved, 1927, EBookFormat.PDF, 3);

        PrintCopyBook b4 = createPrintBook("0553208845", "Сидарта", a2Saved, 1922, 160, 80);

        EBook b5 = createEBook("0061177571", "Пошта", a3Saved, 1971, EBookFormat.EPUB, 3);

        PrintCopyBook b6 = createPrintBook("8649504191", "Проклета авлија",
                a4Saved, 1954, 212, 100);

        EBook b7 = createEBook("4563782902", "Коцкар", a1Saved, 1867, EBookFormat.HTML5, 4);

        PrintCopyBook b8 = createPrintBook("0575569048", "Идиот", a1Saved, 1868, 667, 330);

        bookService.save(b1);
        bookService.save(b2);
        bookService.save(b3);
        bookService.save(b4);
        bookService.save(b5);
        bookService.save(b6);
        bookService.save(b7);
        bookService.save(b8);

    }

    private PrintCopyBook createPrintBook(String isbn, String title, Author author, int year, int pages, int weight) {
        PrintCopyBook b = new PrintCopyBook();
        b.setIsbn(isbn);
        b.setTitle(title);
        b.setAuthor(author);
        b.setYearPublished(year);
        b.setNumPages(pages);
        b.setWeight(weight);
        return b;
    }

    private EBook createEBook(String isbn, String title, Author author, int year, EBookFormat format, int size) {
        EBook b = new EBook();
        b.setIsbn(isbn);
        b.setTitle(title);
        b.setAuthor(author);
        b.setYearPublished(year);
        b.setFormat(format);
        b.setMbSize(size);
        return b;
    }

}
