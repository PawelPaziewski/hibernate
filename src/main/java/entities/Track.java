package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Track {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    private String title;
    private int releaseDate;
    @Getter
    @ManyToMany(mappedBy = "songs", fetch = FetchType.EAGER)
    private Set<Artist> authors = new HashSet<>();
    @ManyToOne
    private Album album;

    public Track(String title, int releaseDate, Set<Artist> authors, Album album) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.authors = authors;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", authors=" + authors +
                ", album=" + album +
                '}';
    }
}
