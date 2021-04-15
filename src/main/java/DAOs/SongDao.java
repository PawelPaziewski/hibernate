package DAOs;

import entities.Track;

public class SongDao extends GenericDao<Track, Long> {

    @Override
    protected Class<Track> getClazz() {
        return Track.class;
    }

    @Override
    public void save(Track entity) {
        doInTx(s -> {
            s.save(entity);
            entity.getAuthors().forEach(
                    artist -> {
                        artist.addSong(entity);
                        s.merge(artist);
                    }
            );
            return entity;
        });
    }
}
