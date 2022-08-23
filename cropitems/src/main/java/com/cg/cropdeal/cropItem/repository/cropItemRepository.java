package com.cg.cropdeal.cropItem.repository;

import com.cg.cropdeal.cropItem.model.cropItem;

public interface cropItemRepository {
	
public cropItem cropitemGetRepo(String cropid);
	
	public cropItem cropitemAddRepo(cropItem cropitem);
	
	public cropItem cropitemUpdateRepo(String cropid,cropItem cropitem);
	
	public String cropitemRemoveRepo(String cropid);

}
