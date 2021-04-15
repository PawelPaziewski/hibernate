package DAOs;

import entities.Album;

public class AlbumDao extends GenericDao<Album, Long> {
    @Override
    protected Class<Album> getClazz() {
        return Album.class;
    }
}
