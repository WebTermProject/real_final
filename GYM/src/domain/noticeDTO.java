package domain;

import java.time.LocalDateTime;

public class noticeDTO {
	private int id;
	private String title;
	private String contents;
	private String writer;
	private LocalDateTime regdate; 
	
	public noticeDTO(int id, String title, String contents, String writer, LocalDateTime regdate) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.regdate = regdate;
	}

	public noticeDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}
	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}
}
