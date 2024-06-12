package ptithcm.dto;

public class NewMovieDTO {

	private String title;
	private String description;
	private String genres;

	public NewMovieDTO() {
	}

	public NewMovieDTO(String title, String description, String genres) {
		this.title = title;
		this.description = description;
		this.genres = genres;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getGenres() {
		return genres;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "MovieWithViewsDTO [title=" + title + ", description=" + description + ", genres=" + genres + "]";
	}

}
