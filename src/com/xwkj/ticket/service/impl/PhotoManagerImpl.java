package com.xwkj.ticket.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.WebContextFactory;

import com.xwkj.ticket.bean.PhotoBean;
import com.xwkj.ticket.domain.Photo;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.service.PhotoManager;
import com.xwkj.ticket.service.util.ManagerTemplate;
import com.xwkj.ticket.servlet.PhotoServlet;

public class PhotoManagerImpl extends ManagerTemplate implements PhotoManager {

	@Override
	public List<PhotoBean> getPhotosBySid(String sid) {
		Scenic scenic=scenicDao.get(sid);
		List<PhotoBean> photos=new ArrayList<>();
		for(Photo photo: photoDao.findByScenic(scenic)) {
			photos.add(new PhotoBean(photo));
		}
		return photos;
	}

	@Override
	public void removePhoto(String pid) {
		Photo photo=photoDao.get(pid);
		Scenic scenic=photo.getScenic();
		String rootPath=WebContextFactory.get().getServletContext().getRealPath("/");
		String pathname=rootPath+PhotoServlet.PHOTO_FOLDER+"/"+scenic.getSid()+"/"+photo.getFilename();
		File file=new File(pathname);
		file.delete();
		if(scenic.getCover()!=null) {
			if(scenic.getCover().getPid().equals(photo.getPid())) {
				scenic.setCover(null);
				scenicDao.update(scenic);
			}
		}
		photoDao.delete(photo);
		
	}

	@Override
	public void setAsCover(String pid) {
		Photo photo=photoDao.get(pid);
		Scenic scenic=photo.getScenic();
		scenic.setCover(photo);
		scenicDao.update(scenic);
	}

}
