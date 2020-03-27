package com.kevin.usc.core.bean;

import lombok.Data;

@Data
public class WResponse<T> {
	
	private Integer code;
	
	private String msg;
	
	private T data;
	
	private Boolean  more;
	
	
	
	public WResponse() {
		this.code = 200;
		this.msg = Status.SUCCESS.getMsg();
	}
	
	public WResponse(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

   
	public static WResponse offMsg(Integer code,String msg){
		
		return new WResponse<>(code, msg, null);
	}

	
	public static<T> WResponse<T> offSuccess(T data) {
		
		return  new WResponse<>(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg(), data);
		
	}
	
	
	public static<T> WResponse<T> offStatus(Status status){
		
		return new WResponse<>(status.getCode(),status.getMsg(),null);
	}



	public enum Status {
		
		SUCCESS(200,"OK"),
		BAD_REQUEST(400,"Bad request"),
		INTERNAL_SERVER_ERROR(500,"Unknown Internal Error"),
		NOT_VALID_PARAM(40005,"Not valid Params"),
		NOT_SUPPORTED_OPERATION(40006,"Operation not supported");
		
		private Integer code;
		
		private String msg; 
		
		
		private Status(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		
		
		public void setCode(Integer code) {
			
			this.code =  code;
		}
		
		public Integer getCode() {
			
			return this.code;
		}
		
		public void setMsg(String msg) {
			
			this.msg =  msg;
		}
		
		public String getMsg() {
			
			return this.msg;
		}
	}

}
