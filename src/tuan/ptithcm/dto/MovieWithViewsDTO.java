package ptithcm.dto;

public class MovieWithViewsDTO {

    private Long movieId;
    private String title;
    private String poster_url;
    private Long totalViews;

    public MovieWithViewsDTO(Long movieId, String title,String poster_url ,Long totalViews) {
        this.movieId = movieId;
        this.title = title;
        this.totalViews = totalViews;
        this.poster_url = poster_url;
    }

    // Getters and Setters
    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
    }
}
