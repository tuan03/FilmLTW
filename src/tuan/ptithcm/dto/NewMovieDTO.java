package ptithcm.dto;

public class NewMovieDTO {

	private String title;
	private String description;

	public NewMovieDTO() {
	}

	public NewMovieDTO(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MovieWithViewsDTO [title=" + title + ", description=" + description +  "]";
	}

}
