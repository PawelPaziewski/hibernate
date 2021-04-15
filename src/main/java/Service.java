import DAOs.AlbumDao;
import DAOs.ArtistDao;
import DAOs.SongDao;
import entities.Album;
import entities.Artist;
import entities.Track;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

class Service {

    private final SongDao songDao = new SongDao();
    private final ArtistDao artistDao = new ArtistDao();
    private final AlbumDao albumDao = new AlbumDao();
    Scanner sc = new Scanner(System.in);

    void addSong() {
        System.out.println("Enter title: ");
        final String title = sc.nextLine();
        System.out.println("Enter release year");
        final int releaseYear = sc.nextInt();
        sc.nextLine();
        showArtists();
        System.out.println("Enter artists id separated by \\',\\'");
        final Set<Artist> artists = new HashSet<>(getArtists(sc.nextLine()));
        showAlbums();
        System.out.println("Enter album id");
        final int albumId = sc.nextInt();
        final Album album = getAlbum(albumId);
        final Track song = new Track(title, releaseYear, artists, album);
        songDao.save(song);
        sc.nextLine();
    }

    private void showAlbums() {
        albumDao.findAll().forEach(System.out::println);
    }

    private void showArtists() {
        artistDao.findAll().forEach(System.out::println);
    }


    private Album getAlbum(int albumId) {
        return albumDao.findById((long) albumId);
    }

    private List<Artist> getArtists(String artists) {
        long[] artistIds = Arrays.stream(artists.split(","))
                .map(String::trim)
                .filter(NumberUtils::isParsable)
                .mapToLong(Long::parseLong).toArray();
        final Long[] boxed = ArrayUtils.toObject(artistIds);
        return artistDao.findByIds(boxed);
    }

    void addArtist() {
        System.out.println("Enter firstName: ");
        String firstName = sc.nextLine();
        System.out.println("Enter lastName: ");
        String lastName = sc.nextLine();
        System.out.println("Enter pseudonym: ");
        String pseudonym = sc.nextLine();
        Artist artist = new Artist(firstName, lastName, pseudonym);
        artistDao.save(artist);
    }

    void addAlbum() {
        System.out.println("Enter title: ");
        final String title = sc.nextLine();
        System.out.println("Enter release year");
        final int releaseYear = sc.nextInt();
        final Album album = new Album(title, releaseYear);
        albumDao.save(album);
        System.out.println("add album method");
    }

    void listSongs() {
        final List<Track> all = songDao.findAll();
        all.forEach(System.out::println);
    }

    void listArtists() {
        final List<Artist> all = artistDao.findAll();
        all.forEach(System.out::println);
    }

    void listAlbums() {
        final List<Album> all = albumDao.findAll();
        all.forEach(System.out::println);
    }
}
