package com.cg.cropdeal.cropItem.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cg.cropdeal.cropItem.common.DummycropItemDB;
import com.cg.cropdeal.cropItem.model.cropItem;
import com.cg.cropdeal.cropItem.repository.cropItemRepository;


@Repository("cropitemRepository")
public class cropItemRepositoryImpl implements cropItemRepository {

	@Autowired
	private DummycropItemDB cropitemDB;
	
	@Override
	@SuppressWarnings("static-access")
	public cropItem cropitemGetRepo(String cropid) {
		// TODO Auto-generated method stub
		 return (cropItem) cropitemDB.getInstance().map.get(cropid);//retrieved data based on the key
	}

	@Override
	@SuppressWarnings("static-access")
	public cropItem cropitemAddRepo(cropItem cropitem) {
		// TODO Auto-generated method stub
		return (cropItem) cropitemDB.getInstance().map.put(cropitem.getCropid(), cropitem);
	}

	@Override
	@SuppressWarnings("static-access")
	public cropItem cropitemUpdateRepo(String cropid, cropItem cropitem) {
		// TODO Auto-generated method stub
		return (cropItem) cropitemDB.getInstance().map.put(cropitem.getCropid(), cropitem);
	}

	@Override
	@SuppressWarnings("static-access")
	public String cropitemRemoveRepo(String cropid) {
		cropitemDB.getInstance().map.remove(cropid);
		return "Crop : " + cropid + " successfully deleted ";
	}

}