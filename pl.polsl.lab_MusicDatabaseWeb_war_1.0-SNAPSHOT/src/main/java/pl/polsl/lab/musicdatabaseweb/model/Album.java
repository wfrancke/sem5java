/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.musicdatabaseweb.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class used to represent ALBUM table in the database
 * 
 * @author Wojciech Francke
 */
@Entity
@Table(name = "ALBUM")
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByTitle", query = "SELECT a FROM Album a WHERE a.title = :title"),
    @NamedQuery(name = "Album.findByGenre", query = "SELECT a FROM Album a WHERE a.genre = :genre"),
    @NamedQuery(name = "Album.findByReleaseyear", query = "SELECT a FROM Album a WHERE a.releaseyear = :releaseyear"),
    @NamedQuery(name = "Album.findByRating", query = "SELECT a FROM Album a WHERE a.rating = :rating"),
    @NamedQuery(name = "Album.findByNumberofratings", query = "SELECT a FROM Album a WHERE a.numberofratings = :numberofratings"),
    @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.id = :id")})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "GENRE")
    private String genre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELEASEYEAR")
    private int releaseyear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATING")
    private double rating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBEROFRATINGS")
    private int numberofratings;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "albumid")
    private Collection<Track> trackCollection;
    @JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
    @ManyToOne
    private Artist author;

    /**
     * Album constructor
     *
     */
    public Album() {
    }

    /**
     * Album title getter
     *
     * @return album title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Album title setter
     *
     * @param title new album title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Album genre getter
     *
     * @return album genre
     */
    public String getGenre() {
        return genre;
    }
    
    /**
     * Album genre setter
     *
     * @param genre new album genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Album release year getter
     *
     * @return album release year
     */
    public int getReleaseyear() {
        return releaseyear;
    }

    /**
     * Album release year setter
     *
     * @param releaseyear new album release year
     */
    public void setReleaseyear(int releaseyear) {
        this.releaseyear = releaseyear;
    }

    /**
     * Album average rating getter
     *
     * @return album average rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Album average rating setter
     *
     * @param rating new album average rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Album ratings number getter
     *
     * @return album number of ratings
     */
    public int getNumberofratings() {
        return numberofratings;
    }

    /**
     * Album ratings number setter
     *
     * @param numberofratings new number of ratings
     */
    public void setNumberofratings(int numberofratings) {
        this.numberofratings = numberofratings;
    }

    /**
     * Album id getter
     *
     * @return album id
     */
    public Long getId() {
        return id;
    }

    /**
     * Album id setter
     *
     * @param id album id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Album track list getter
     *
     * @return album track list
     */
    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }

    /**
     * Album author getter
     *
     * @return album author
     */
    public Artist getAuthor() {
        return author;
    }

    /**
     * Album tracklist setter
     *
     * @param trackCollection album tracklist
     */
    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
    }

    /**
     * Album author setter
     *
     * @param author new album author
     */
    public void setAuthor(Artist author) {
        this.author = author;
    }
    
    /**
     * Method that registers new album rating
     *
     * @param rating new album rating
     * @throws AlbumException
     */
    public void addRating(double rating) throws AlbumException {
        if (rating < 1 || rating > 10) {
            throw new AlbumException("Album rating should be between 1 and 10");
        } else {
            this.rating = (this.numberofratings * this.rating + rating) / (this.numberofratings + 1);
            this.numberofratings++;
        }
    }
    
}
