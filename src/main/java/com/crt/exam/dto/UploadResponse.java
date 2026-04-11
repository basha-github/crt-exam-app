package com.crt.exam.dto;


import java.util.List;
import java.util.Map;


public class UploadResponse {
	
   	private boolean success;
    private String message;
    private List<Map<String, Object>> data;
    private int rowCount;
   
    public UploadResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadResponse(boolean success, String message, List<Map<String, Object>> data, int rowCount) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
		this.rowCount = rowCount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
    
    
    
    
    
    
}