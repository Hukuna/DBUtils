package com.llrpg.common.util;

import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
    	JdbcTemplateUtils utils = new JdbcTemplateUtils("localhost", "3306", "root", "root");
    	String sql = "SELECT LANGUAGE,id FROM `test`.`java_vs_python` WHERE id IN (0,1,2,3,4,5,6,7)";
    	List<Map<String,Object>> list = utils.query(sql);
    	System.out.println(list);
    	
    	sql = "INSERT INTO `test`.`java_vs_python`(LANGUAGE,id) VALUES('scala',1),('scala',2)";
    	int count = utils.insert(sql);
    	System.out.println(count);
	}
}
