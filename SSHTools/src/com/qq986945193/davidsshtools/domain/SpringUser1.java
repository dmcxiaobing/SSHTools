package com.qq986945193.davidsshtools.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 演示集合注入的一个javabean
 * 
 * 分别有数组。List集合，还有一个Properties注入
 */
public class SpringUser1 {	
	private String[] arrs;

	public String[] getArrs() {
		return arrs;
	}

	public void setArrs(String[] arrs) {
		this.arrs = arrs;
	}	

	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	private Map<String, String> map;
	
	
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "SpringUser1 [arrs=" + Arrays.toString(arrs) + ", list=" + list + ", map=" + map + ", properties="
				+ properties + "]";
	}

	
	
}
