package com.xwkj.ticket.dao;

import java.util.List;

import com.xwkj.ticket.domain.Photo;
import com.xwkj.ticket.domain.Scenic;

public interface PhotoDao {
	
	Photo get(String pid);
	String save(Photo photo);
	void update(Photo photo);
	void delete(Photo photo);
	
	/**
	 * 根据景区查找照片
	 * @param scenic
	 * @return
	 */
	List<Photo> findByScenic(Scenic scenic);
}
