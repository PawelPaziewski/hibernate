package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Album {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String title;
    Date releaseDate;
    @OneToMany(mappedBy = "album")
    List<Track> songs;
}
