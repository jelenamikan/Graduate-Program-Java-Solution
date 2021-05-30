package mk.jmikan.library.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class EBook {

    @Enumerated(EnumType.STRING)
    private EBookFormat format;

    private Integer mbSize;

}
