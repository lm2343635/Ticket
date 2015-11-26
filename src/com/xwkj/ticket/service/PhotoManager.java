package com.xwkj.ticket.service;

import java.util.List;

import com.xwkj.ticket.bean.PhotoBean;

public interface PhotoManager {

	/**
	 * 根据景区获取景区图片
	 * @param sid
	 * @return
	 */
	List<PhotoBean> getPhotosBySid(String sid);
	
	/**
	 * 删除图片
	 * @param pid
	 */
	void removePhoto(String pid);
	
	/**
	 * 设为封面
	 * @param pid
	 */
	void setAsCover(String pid);
	
}
