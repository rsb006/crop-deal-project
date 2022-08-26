package com.cg.cropdeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cg.cropdeal.cropItem.repository.cropItemRepository;

@DataJpaTest
public class cropItemTest {

	@Autowired 
	private cropItemRepository repo;
	
	
}
