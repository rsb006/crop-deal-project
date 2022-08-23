package com.cg.cropdeal.cropItem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cropdeal.cropItem.model.cropItem;
import com.cg.cropdeal.cropItem.repository.cropItemRepository;
import com.cg.cropdeal.cropItem.service.cropItemService;

@Service("cropitemService")
public class cropItemServiceImpl implements cropItemService {

	@Autowired
	private cropItemRepository cropitemRepository;
	
	@Override
	public cropItem cropitemGetServ(String cropid) {
		
		return cropitemRepository.cropitemGetRepo(cropid);
	}

	@Override
	public cropItem cropitemAddServ(cropItem cropitem) {
		
		return cropitemRepository.cropitemAddRepo(cropitem);
	}

	@Override
	public cropItem cropitemUpdateServ(String cropid, cropItem cropitem) {
		
		return cropitemRepository.cropitemUpdateRepo(cropid,cropitem);
	}

	@Override
	public String cropitemRemoveServ(String cropid) {
		
		return cropitemRepository.cropitemRemoveRepo(cropid);
	}

}
