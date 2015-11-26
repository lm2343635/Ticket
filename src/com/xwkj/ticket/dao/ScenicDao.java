package com.xwkj.ticket.dao;

import java.util.List;

import com.xwkj.ticket.domain.Scenic;

public interface ScenicDao {
	Scenic get(String sid);
	String save(Scenic scenic);
	void update(Scenic scenic);
	void delete(Scenic scenic);
	
	List<Scenic> findAll();
}
