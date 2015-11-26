package com.xwkj.ticket.bean;

import java.util.Date;

import com.xwkj.ticket.domain.Scenic;

public class ScenicBean {
	
	private String sid;
	private String sname;
	private double price;
	private String location;
	private String description;
	private Date createDate;
	private boolean enable;
	private PhotoBean cover;
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public PhotoBean getCover() {
		return cover;
	}

	public void setCover(PhotoBean cover) {
		this.cover = cover;
	}

	public ScenicBean(Scenic scenic) {
		super();
		this.sid = scenic.getSid();
		this.sname = scenic.getSname();
		this.price = scenic.getPrice();
		this.location = scenic.getLocation();
		this.description = scenic.getDescription();
		this.createDate = scenic.getCreateDate();
		this.enable = scenic.getEnable();
		this.cover = scenic.getCover()==null? null: new PhotoBean(scenic.getCover());
	}
	
}
