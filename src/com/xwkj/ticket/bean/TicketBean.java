package com.xwkj.ticket.bean;

import java.util.Date;

import com.xwkj.ticket.domain.Ticket;

public class TicketBean {
	
	private String tid;
	private String tno;
	private String password;
	private double price;
	private int count;
	private double amount;
	private Date date;
	private Date createDate;
	private String name;
	private String telephone;
	private String email;
	private boolean pay;
	private String payWay;
	private String wechatNonce;
	private boolean timeout;
	private Date payDate;
	private boolean checkin;
	private ScenicBean scenic;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPay() {
		return pay;
	}
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public ScenicBean getScenic() {
		return scenic;
	}
	public void setScenic(ScenicBean scenic) {
		this.scenic = scenic;
	}
	public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}
	public boolean isCheckin() {
		return checkin;
	}
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}
	
	public String getPayWay() {
		return payWay;
	}
	public String getWechatNonce() {
		return wechatNonce;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public void setWechatNonce(String wechatNonce) {
		this.wechatNonce = wechatNonce;
	}
	public TicketBean(Ticket ticket) {
		super();
		this.tid = ticket.getTid();
		this.tno = ticket.getTno();
		this.password = ticket.getPassword();
		this.price = ticket.getPrice();
		this.count = ticket.getCount();
		this.amount = ticket.getAmount();
		this.date = ticket.getDate();
		this.createDate = ticket.getCreateDate();
		this.name = ticket.getName();
		this.telephone = ticket.getTelephone();
		this.email = ticket.getEmail();
		this.pay = ticket.getPay();
		this.payWay=ticket.getPayWay();
		this.wechatNonce=ticket.getWechatNonce();
		this.timeout = ticket.getTimeout();
		this.payDate = ticket.getPayDate();
		this.checkin = ticket.getCheckin();
		this.scenic = new ScenicBean(ticket.getScenic());
	}
	
}
