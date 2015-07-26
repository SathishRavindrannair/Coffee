package com.example.coffeequotes;

import java.util.Arrays;

import android.graphics.Bitmap;

public class FilmyHelper {
	private String movie_name;
	private Bitmap image_url;
	private String starring;
	private String[] quotes;
	private String id;
	private String content;
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}	
	public String[] getQuotes() {
		return quotes;
	}
	public void setQuotes(String[] quotes) {
		this.quotes = quotes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStarring() {
		return starring;
	}
	public void setStarring(String starring) {
		this.starring = starring;
	}
	public Bitmap getImage_url() {
		return image_url;
	}
	public void setImage_url(Bitmap image_url) {
		this.image_url = image_url;
	}
	@Override
	public String toString() {
		return "FilmyHelper [movie_name=" + movie_name + ", image_url="
				+ image_url + ", starring=" + starring + ", quotes="
				+ Arrays.toString(quotes) + ", id=" + id + ", content="
				+ content + "]";
	}
	
}
