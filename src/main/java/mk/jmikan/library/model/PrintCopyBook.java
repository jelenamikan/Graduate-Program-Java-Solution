package mk.jmikan.library.model;

import javax.persistence.Entity;

@Entity
public class PrintCopyBook extends Book {

    private Integer numPages;

    private Integer weight;

}
