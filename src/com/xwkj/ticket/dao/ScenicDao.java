package com.xwkj.ticket.dao;

import java.util.List;

import com.xwkj.ticket.domain.Scenic;

public interface ScenicDao {
	Scenic get(String sid);
	String save(Scenic scenic);
	void update(Scenic scenic);
	void delete(Scenic scenic);
	
	/**
	 * 查询景点
	 * @param enable 是否查询可用景点
	 * @return
	 */
	List<Scenic> findAll(boolean enable);
}
