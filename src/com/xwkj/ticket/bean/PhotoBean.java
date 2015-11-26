package com.xwkj.ticket.bean;

import java.util.Date;

import com.xwkj.ticket.domain.Photo;

public class PhotoBean {
	
	private String pid;
	private String filename;
	private Date upload;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getUpload() {
		return upload;
	}
	public void setUpload(Date upload) {
		this.upload = upload;
	}
	
	public PhotoBean(Photo photo) {
		super();
		this.pid = photo.getPid();
		this.filename = photo.getFilename();
		this.upload = photo.getUpload();
	}
	
}
