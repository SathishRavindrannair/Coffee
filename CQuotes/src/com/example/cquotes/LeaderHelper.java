package com.example.cquotes;

import java.util.Arrays;

import android.graphics.Bitmap;

public class LeaderHelper {
	private String leader_name;
	private Bitmap image_url;
	private String Active;
	private String[] quotes;
	private String id;
	private String content;
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
	
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}
	public Bitmap getImage_url() {
		return image_url;
	}
	public void setImage_url(Bitmap image_url) {
		this.image_url = image_url;
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
	@Override
	public String toString() {
		return "LeaderHelper [leader_name=" + leader_name + ", image_url="
				+ image_url + ", Active=" + Active + ", quotes="
				+ Arrays.toString(quotes) + ", id=" + id + ", content="
				+ content + "]";
	}
	

	
	

}
