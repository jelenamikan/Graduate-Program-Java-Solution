package mk.jmikan.library.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrintCopyBook extends Book {

    private Integer numPages;

    private Integer weight;

}
