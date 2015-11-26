package com.xwkj.ticket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.directwebremoting.WebContextFactory;

import com.xwkj.common.util.FileTool;
import com.xwkj.ticket.bean.ScenicBean;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.service.ScenicManager;
import com.xwkj.ticket.service.util.ManagerTemplate;
import com.xwkj.ticket.servlet.PhotoServlet;

public class ScenicManagerImpl extends ManagerTemplate implements ScenicManager {

	@Override
	public String addScenic(String sname, String location, double price, String description) {
		Scenic scenic=new Scenic();
		scenic.setSname(sname);
		scenic.setPrice(price);
		scenic.setLocation(location);
		scenic.setDescription(description);
		scenic.setCreateDate(new Date());
		scenic.setEnable(true);
		scenic.setSold(0);
		return scenicDao.save(scenic);
	}

	@Override
	public ScenicBean getScenic(String sid) {
		Scenic scenic=scenicDao.get(sid);
		if(scenic==null)
			return null;
		return new ScenicBean(scenic);
	}

	@Override
	public void modifyScenic(String sid, String sname, String location, double price, String description) {
		Scenic scenic=scenicDao.get(sid);
		scenic.setSname(sname);
		scenic.setPrice(price);
		scenic.setLocation(location);
		scenic.setDescription(description);
		scenicDao.update(scenic);
	}

	@Override
	public boolean removeScenic(String sid) {
		Scenic scenic=scenicDao.get(sid);
		if(scenic.getSold()>0)
			return false;
		scenic.setCover(null);
		scenicDao.update(scenic);
		String rootPath=WebContextFactory.get().getServletContext().getRealPath("/");
		String path=rootPath+PhotoServlet.PHOTO_FOLDER+"/"+scenic.getSid();
		FileTool.delAllFile(path);
		FileTool.delFolder(path);
		scenicDao.delete(scenic);
		return true;
	}

	@Override
	public List<ScenicBean> getAll(boolean enable) {
		List<ScenicBean> scenics=new ArrayList<>();
		for(Scenic scenic: scenicDao.findAll(enable)) {
			scenics.add(new ScenicBean(scenic));
		}
		return scenics;
	}

	@Override
	public void enable(String sid, boolean enable) {
		Scenic scenic=scenicDao.get(sid);
		scenic.setEnable(enable);
		scenic.setCreateDate(new Date());
		scenicDao.update(scenic);
	}

}
