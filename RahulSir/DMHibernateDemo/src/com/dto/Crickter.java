package com.dto;

import java.util.List;

public class Crickter {
	
	private long jersyNo;
	private String name;
	private long noOfRuns;
	private long noOfmatchs;
	private Actor actor;
	private Singer singer;
	private List<Cars> list;
	
	public List<Cars> getList() {
		return list;
	}
	public void setList(List<Cars> list) {
		this.list = list;
	}
	public long getJersyNo() {
		return jersyNo;
	}
	public void setJersyNo(long jersyNo) {
		this.jersyNo = jersyNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNoOfRuns() {
		return noOfRuns;
	}
	public void setNoOfRuns(long noOfRuns) {
		this.noOfRuns = noOfRuns;
	}
	public long getNoOfmatchs() {
		return noOfmatchs;
	}
	public void setNoOfmatchs(long noOfmatchs) {
		this.noOfmatchs = noOfmatchs;
	}
	
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}		
}

