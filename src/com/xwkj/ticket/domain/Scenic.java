package com.xwkj.ticket.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Scenic implements Serializable {

	private static final long serialVersionUID = 9142530619826580348L;
	
	private String sid;
	private String sname;
	private Double price;
	private String location;
	private String description;
	private Date createDate;
	private Boolean enable;
	private Integer sold;
	private Photo cover;
	private Set<Photo> photos;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	
}
