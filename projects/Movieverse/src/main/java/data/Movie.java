package data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "urlPoster")
    private String urlPoster;

    @Column(name = "urlBackdrop")
    private String urlBackdrop;

    @Column(name = "plot")
    private String plot;

    @Column(name = "imdbId")
    private String imdbId;

    @Column(name = "releaseDate")
    private Date releaseDate;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "ratingSum")
    private int ratingSum;

    @Column(name = "ratingCount")
    private int ratingCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlBackdrop() {
        return urlBackdrop;
    }

    public void setUrlBackdrop(String urlBackdrop) {
        this.urlBackdrop = urlBackdrop;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
