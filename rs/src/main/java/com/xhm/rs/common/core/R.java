package com.xhm.rs.common.core;

import java.io.Serializable;

/**
 * 返回数据，构造统一返回JSON
 * 
 */
public class R  implements Serializable{

	private static final long serialVersionUID = 1L;

	private int code;   // 200是正常，非200表示异常
    private String msg;
    private Object data;
	

	
	public static R ok(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

	public static R ok(String msg, Object data) {
        return ok(200,msg,data);
    }

	public static R ok(Object data) {
        return ok(200,"操作成功",data);
    }

	public static R ok() {
		return ok(200,"操作成功",null);
    }


	public static R error(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

	public static R error(String msg, Object data) {
		return error(500,msg,data);
    }

	public static R error(String msg) {
		return error(500, msg,null);
	}

	public static R error() {
		return error(500,"未知异常，请联系管理员",null);
    }

	
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
