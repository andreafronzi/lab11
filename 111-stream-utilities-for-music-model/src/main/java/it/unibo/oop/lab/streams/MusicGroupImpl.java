package it.unibo.oop.lab.streams;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 */
public final class MusicGroupImpl implements MusicGroup {

    private final Map<String, Integer> albums = new HashMap<>();
    private final Set<Song> songs = new HashSet<>();

    @Override
    public void addAlbum(final String albumName, final int year) {
        this.albums.put(albumName, year);
    }

    @Override
    public void addSong(final String songName, final Optional<String> albumName, final double duration) {
        if (albumName.isPresent() && !this.albums.containsKey(albumName.get())) {
            throw new IllegalArgumentException("invalid album name");
        }
        this.songs.add(new MusicGroupImpl.Song(songName, albumName, duration));
    }

    @Override
    public Stream<String> orderedSongNames() {
        return Collections.unmodifiableSet(this.songs).stream()
            .map(Song::getSongName)
            .sorted(String::compareTo);
    }

    @Override
    public Stream<String> albumNames() {
        return Collections.unmodifiableSet(this.songs).stream().filter()
    }

    @Override
    public Stream<String> albumInYear(final int year) {
        return Collections.unmodifiableMap(this.albums).values().stream().
        
        
        Collections.unmodifiableSet(this.songs).stream()
        .filter(y -> y.equals(year))
        .map(Song::getAlbumName)
        .map(Optional::get)
        .distinct();
    }

    @Override
    public int countSongs(final String albumName) {
        return -1;
    }

    @Override
    public int countSongsInNoAlbum() {
        return -1;
    }

    @Override
    public OptionalDouble averageDurationOfSongs(final String albumName) {
        return OptionalDouble.empty();
    }

    @Override
    public Optional<String> longestSong() {
        return Optional.empty();
    }

    @Override
    public Optional<String> longestAlbum() {
        return Optional.empty();
    }

    private static final class Song {

        private final String songName;
        private final Optional<String> albumName;
        private final double duration;
        private int hash;

        Song(final String name, final Optional<String> album, final double len) {
            super();
            this.songName = name;
            this.albumName = album;
            this.duration = len;
        }

        public String getSongName() {
            return songName;
        }

        public Optional<String> getAlbumName() {
            return albumName;
        }

        public double getDuration() {
            return duration;
        }

        @Override
        public int hashCode() {
            if (hash == 0) {
                hash = songName.hashCode() ^ albumName.hashCode() ^ Double.hashCode(duration);
            }
            return hash;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof Song) {
                final Song other = (Song) obj;
                return albumName.equals(other.albumName) && songName.equals(other.songName)
                        && duration == other.duration;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Song [songName=" + songName + ", albumName=" + albumName + ", duration=" + duration + "]";
        }

    }

}
