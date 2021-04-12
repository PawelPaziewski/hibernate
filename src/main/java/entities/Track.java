package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Track {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    private String title;
    private Date releaseDate;
    @ManyToMany(mappedBy = "songs")
    private Set<Artist> authors;
    @ManyToOne
    private Album album;
}
