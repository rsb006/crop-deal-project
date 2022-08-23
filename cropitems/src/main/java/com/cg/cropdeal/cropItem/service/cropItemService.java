package com.cg.cropdeal.cropItem.service;

import com.cg.cropdeal.cropItem.model.cropItem;


//service is interface and provide the implementation in serviceimpl class
public interface cropItemService {

	public cropItem cropitemGetServ(String cropid);//provide declaration of the method
	
	public cropItem cropitemAddServ(cropItem cropitem);
	
	public cropItem cropitemUpdateServ(String cropid,cropItem cropitem);
	
	public String cropitemRemoveServ(String cropid);//returning string
	
}
