package net.flower.ixmsxms_server.domain;

public class JSonResult extends BaseObject {
	private static final long serialVersionUID = 1414896140470148281L;

	private String message = "OK";
	private Object data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}