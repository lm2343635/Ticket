package com.xwkj.ticket.service.util;

import com.xwkj.ticket.dao.PhotoDao;
import com.xwkj.ticket.dao.ScenicDao;
import com.xwkj.ticket.dao.TicketDao;

public class ManagerTemplate {
	
	protected PhotoDao photoDao;
	protected ScenicDao scenicDao;
	protected TicketDao ticketDao;
	
	public PhotoDao getPhotoDao() {
		return photoDao;
	}
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	public ScenicDao getScenicDao() {
		return scenicDao;
	}
	public void setScenicDao(ScenicDao scenicDao) {
		this.scenicDao = scenicDao;
	}
	public TicketDao getTicketDao() {
		return ticketDao;
	}
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
}
