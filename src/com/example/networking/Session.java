package com.example.networking;

public class Session {
	
	private String id;
	
	public Session(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Session[");
		sb.append("id:").append(id).append("]");
		
		return sb.toString();
	}
}
