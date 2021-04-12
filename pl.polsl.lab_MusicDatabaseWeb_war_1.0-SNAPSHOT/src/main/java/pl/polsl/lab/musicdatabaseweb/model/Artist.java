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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class used to represent ARTIST table in the database
 * 
 * @author Wojciech Francke
 */
@Entity
@Table(name = "ARTIST")
@NamedQueries({
    @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a"),
    @NamedQuery(name = "Artist.findByLocation", query = "SELECT a FROM Artist a WHERE a.location = :location"),
    @NamedQuery(name = "Artist.findById", query = "SELECT a FROM Artist a WHERE a.id = :id"),
    @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name = :name")})
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LOCATION")
    private String location;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name; // ?
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Album> albumCollection;
    
    /**
     * Artist constructor
     *
     */
    public Artist() {
    }

    /**
     * Artist location getter
     *
     * @return artists location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Artist location setter
     *
     * @param location new artist location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Artist id getter
     *
     * @return artist id
     */
    public Long getId() {
        return id;
    }

    /**
     * Artist id setter
     *
     * @param id new artist id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Artist name getter
     *
     * @return artists name
     */
    public String getName() {
        return name;
    }

    /**
     * Artist name setter
     *
     * @param name new artist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Artist discography getter
     *
     * @return artists discography
     */
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    /**
     * Artist discography setter
     *
     * @param albumCollection artists discography
     */
    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    } 
}
