package com.xwkj.ticket.service;

import java.util.List;

import com.xwkj.ticket.bean.ScenicBean;

public interface ScenicManager {
	
	/**
	 * 新增景区
	 * @param sname
	 * @param location
	 * @param price
	 * @param description
	 * @return
	 */
	String addScenic(String sname, String location, double price, String description);
	
	/**
	 * 获取景区
	 * @param sid
	 * @return
	 */
	ScenicBean getScenic(String sid);
	
	/**
	 * 修改景区
	 * @param sid
	 * @param sname
	 * @param location
	 * @param price
	 * @param description
	 */
	void modifyScenic(String sid, String sname, String location, double price, String description);
	
	/**
	 * 删除景区
	 * @param sid
	 */
	boolean removeScenic(String sid);
	
	/**
	 * 获取所有景区信息
	 * @param enable 是否查询可用景点
	 * @return
	 */
	List<ScenicBean> getAll(boolean enabl);
	
	/**
	 * 设置景区可用性
	 * @param sid
	 * @param enable
	 */
	void enable(String sid, boolean enable);
}
