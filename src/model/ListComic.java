package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comics")
public class ListComic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="WRITER")
	private String writer;
	@Column(name="ARTIST")
	private String artist;
	@Column(name="TITLE")
	private String title;
	
	public ListComic() {
		
	}

	public  ListComic(String writer, String artist, String title) {
		this.writer = writer;
		this.artist = artist;
		this.title = title;
	}
	
	public String returnComicDetails() {
		return writer+ ": " + title;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
