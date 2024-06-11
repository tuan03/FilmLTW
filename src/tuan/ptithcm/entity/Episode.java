package ptithcm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Movie is mandatory")
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull(message = "Episode number is mandatory")
    @Column(name = "episode_number", nullable = false)
    private Integer episodeNumber;

    @NotNull(message = "Video URL is mandatory")
    @Pattern(regexp = "^(http[s]?://.*)$", message = "Video URL should be a valid URL")
    @Size(max = 255, message = "Video URL should not be longer than 255 characters")
    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "views", nullable = false)
    private Integer views = 0;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
