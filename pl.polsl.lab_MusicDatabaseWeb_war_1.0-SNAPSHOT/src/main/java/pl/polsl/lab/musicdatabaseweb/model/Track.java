/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.musicdatabaseweb.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class used to represent TRACK table in the database
 * 
 * @author Wojciech Francke
 */
@Entity
@Table(name = "TRACK")
@NamedQueries({
    @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t"),
    @NamedQuery(name = "Track.findByTitle", query = "SELECT t FROM Track t WHERE t.title = :title"),
    @NamedQuery(name = "Track.findByLength", query = "SELECT t FROM Track t WHERE t.length = :length"),
    @NamedQuery(name = "Track.findById", query = "SELECT t FROM Track t WHERE t.id = :id")})
public class Track implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LENGTH")
    private int length;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "ALBUMID", referencedColumnName = "ID")
    @ManyToOne
    private Album albumid;

    /**
     * Track constructor
     *
     */
    public Track() {
    }

    /**
     * Track title getter
     *
     * @return track title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Track title setter
     *
     * @param title track title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Track length getter
     *
     * @return track length in seconds
     */
    public int getLength() {
        return length;
    }

    /**
     * Track length setter
     *
     * @param length track length in seconds
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Track id getter
     *
     * @return track id
     */
    public Long getId() {
        return id;
    }

    /**
     * Track id setter
     *
     * @param id track id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Track album id getter
     *
     * @return Id of tracks album
     */
    public Album getAlbumid() {
        return albumid;
    }

    /**
     * Track album id getter
     *
     * @param albumid Id of tracks album
     */
    public void setAlbumid(Album albumid) {
        this.albumid = albumid;
    }
}
