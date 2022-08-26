package com.cg.cropdeal.cropItem.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component //act as component like bean
public class DummycropItemDB {

	public static DummycropItemDB cropitemDB=null;//created singleton class
	
	public static Map<String,Object>map; //key is cropid which is string value is cropitem
	
	private DummycropItemDB() {
		map=new HashMap<String,Object>();
	}
//create singleton class
	public static DummycropItemDB getInstance() {
		// TODO Auto-generated method stub
		if(cropitemDB==null) //check cropitemDB instance is null or not if it null then it create new instance
			cropitemDB=new DummycropItemDB();
		return cropitemDB; //if not null then return same instance
	}
	
}
