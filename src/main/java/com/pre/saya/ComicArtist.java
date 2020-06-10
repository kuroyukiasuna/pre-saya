package com.pre.saya;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

@Entity
public class ComicArtist {
    private @Id @GeneratedValue Long id;
    private String twitterId;
    private String artistName;
    private String link;

    private ComicArtist() {}

    public ComicArtist(String twitterId, String artistName, String link){
        this.twitterId = twitterId;
        this.artistName = artistName;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicArtist cmArtist = (ComicArtist) o;
        return Objects.equals(id, cmArtist.id) &&
                Objects.equals(twitterId, cmArtist.twitterId) &&
                Objects.equals(artistName, cmArtist.artistName) &&
                Objects.equals(link , cmArtist.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, twitterId, artistName, link);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "Name: '" + artistName + '\'' +
                ", Twitter: '" + twitterId + '\'' +
                ", Link: '" + link + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getArtistName(){
        return artistName;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

}
