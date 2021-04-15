package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@ToString(exclude = "songs")
@Setter
@Getter
public class Artist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Artist_Track",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )
    private Set<Track> songs = new HashSet<>();

    public Artist(String firstName, String lastName, String pseudonym) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudonym = pseudonym;
    }

    public void addSong(Track song) {
        songs.add(song);
    }
}
