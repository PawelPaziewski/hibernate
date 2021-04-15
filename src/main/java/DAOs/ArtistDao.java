package DAOs;

import entities.Artist;

public class ArtistDao extends GenericDao<Artist, Long> {
    @Override
    protected Class<Artist> getClazz() {
        return Artist.class;
    }
}
