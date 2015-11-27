package com.xwkj.ticket.domain;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 4887139726487362142L;
	
	private String tid;
	private String tno;
	private String password;
	private Double price;
	private Integer count;
	private Double amount;
	private Date date;
	private Date createDate;
	private String name;
	private String telephone;
	private String email;
	private Boolean pay;
	private Boolean timeout;
	private Date payDate;
	private Scenic scenic;
	
	public String getTid() {
		return tid;
	}
	public String getTno() {
		return tno;
	}
	public String getPassword() {
		return password;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getCount() {
		return count;
	}
	public Double getAmount() {
		return amount;
	}
	public Date getDate() {
		return date;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getEmail() {
		return email;
	}
	public Boolean getPay() {
		return pay;
	}
	public Date getPayDate() {
		return payDate;
	}
	public Scenic getScenic() {
		return scenic;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPay(Boolean pay) {
		this.pay = pay;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getTimeout() {
		return timeout;
	}
	public void setTimeout(Boolean timeout) {
		this.timeout = timeout;
	}

}
