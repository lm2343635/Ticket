package com.xwkj.ticket.domain;

import java.io.Serializable;
import java.util.Date;

public class Photo implements Serializable {

	private static final long serialVersionUID = 6842970325464058787L;
	
	private String pid;
	private String filename;
	private Date upload;
	private Scenic scenic;
	
	public String getPid() {
		return pid;
	}
	public String getFilename() {
		return filename;
	}
	public Date getUpload() {
		return upload;
	}
	public Scenic getScenic() {
		return scenic;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setUpload(Date upload) {
		this.upload = upload;
	}
	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

}
