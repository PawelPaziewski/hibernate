package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Artist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String firstName;
    String lastName;
    String pseudonym;
    @ManyToMany
    @JoinTable(
            name = "Artist_Track",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )
    Set<Track> songs;
}
