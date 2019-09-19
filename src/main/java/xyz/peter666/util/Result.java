package xyz.peter666.util;

public class Result {

	//响应业务状态
	private Integer status;
	
	//响应消息
	private String msg;
	
	//响应数据
	private Object data;
	

	public static Result build(Integer status,String msg,Object data) {
		return new Result(status,msg,data);
	}
	
	public static Result ok() {
		return new Result(null);
	}
	public static Result ok(Object data) {
		return new Result(data);
	}
	

	public Result(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public Result(Object data) {
		this.status=200;
		this.msg="OK";
		this.data=data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}