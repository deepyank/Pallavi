package com.dto;

public class Cars {
	private long id;
	private String name;
	private long jersyNo;
	private Crickter crickter;
	
	public Crickter getCrickter() {
		return crickter;
	}
	public void setCrickter(Crickter crickter) {
		this.crickter = crickter;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getJersyNo() {
		return jersyNo;
	}
	public void setJersyNo(long jersyNo) {
		this.jersyNo = jersyNo;
	}
	
	
	
}
