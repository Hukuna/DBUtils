package com.llrpg.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Created by zxvf on 2017/4/10.
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;
    
    private static String user;
    private static String pwd;
    private static String url;

    public JdbcTemplateUtils(){
    	
    }
    
    public JdbcTemplateUtils(String host, String port, String user, String pwd) {
    	JdbcTemplateUtils.user = user;
    	JdbcTemplateUtils.pwd = pwd;
    	JdbcTemplateUtils.url = "jdbc:mysql://"+host+":"+port+"/?useUnicode=true&characterEncoding=utf8";
    }
    
    private static JdbcTemplate initialJdbcTemplate() throws Exception{
    	try {
    		if (jdbcTemplate == null) {
        		DruidDataSource ds = new DruidDataSource();
        		ds.setDriverClassName("com.mysql.jdbc.Driver");
        		ds.setUrl(url);
        		ds.setUsername(user);
        		ds.setPassword(pwd);
        		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        		return jdbcTemplate;
        	}
        	return jdbcTemplate;
		} catch (Exception e) {
			throw new Exception("数据库连接池创建失败:" + e.getMessage());
		}
    }
    
    public int insert(String sql) {
    	try {
			jdbcTemplate = initialJdbcTemplate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    	int i = jdbcTemplate.update(sql);
    	return i;
    }
    
    public List<Map<String,Object>> query(String sql) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
			jdbcTemplate = initialJdbcTemplate();
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
    	list = jdbcTemplate.queryForList(sql);
    	return list;
    }
    

}
