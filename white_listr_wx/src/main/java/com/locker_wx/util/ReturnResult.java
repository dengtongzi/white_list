package com.locker_wx.util;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Serializable;
import java.util.Map;

/**
* @author 作者 dengtongzi
* @describe 返回结果集
* @version 创建时间：2019年6月30日 上午10:18:44
*/

public class ReturnResult extends JSONObject implements Serializable {
	public static final String MSG_SUCCESS_DELETE = "删除成功!";
	public static final String MSG_FAIL_DELETE = "删除失败!";
	public static final String MSG_SUCCESS_UPDATE = "更新成功!";
	public static final String MSG_FAIL_UPDATE = "更新失败!";
	public static final String MSG_SUCCESS_ADD = "添加成功!";
	public static final String MSG_FAIL_ADD = "添加失败!";
	private static final long serialVersionUID = 1L;
	private int state = 1;
	private String msg = "操作成功!";

	public ReturnResult() {
		this.put("state", this.state);
		this.put("msg", this.msg);
	}

	public boolean isOk() {
		return this.getInteger("state") == 1;
	}

	public static ReturnResult error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static ReturnResult error(int state, String msg) {
		ReturnResult result = new ReturnResult();
		result.put("state", state);
		result.put("msg", msg);
		return result;
	}

	public void addError(int state, String msg) {
		this.put("state", state);
		this.put("msg", msg);
	}

	public static ReturnResult ok(Object data) {
		ReturnResult result = new ReturnResult();
		result.put("data", data);
		result.put("msg", "操作成功!");
		return result;
	}

	public static ReturnResult ok(Object data, String msg) {
		ReturnResult result = new ReturnResult();
		result.put("data", data);
		result.put("msg", msg);
		return result;
	}

	public void setSuccess(Object data, String msg) {
		this.setData(data);
		this.setMsg(msg);
	}

	public static ReturnResult put(Map<String, Object> map) {
		ReturnResult result = new ReturnResult();
		result.putAll(map);
		return result;
	}

	public static ReturnResult ok() {
		return new ReturnResult();
	}

	public static ReturnResult fail() {
		ReturnResult result = new ReturnResult();
		result.put("state", 0);
		result.put("msg", "操作失败!");
		return result;
	}

	public void addFail(String msg) {
		this.put("state", 0);
		this.put("msg", StringUtils.isBlank(msg) ? "操作失败!" : msg);
	}

	public static ReturnResult fail(String msg) {
		ReturnResult result = new ReturnResult();
		result.put("state", 0);
		result.put("msg", msg);
		return result;
	}

	public ReturnResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public int getState() {
		return this.getInteger("state");
	}

	public void setState(int state) {
		this.put("state", state);
	}

	public String getMsg() {
		return this.getString("msg");
	}

	public void setMsg(String msg) {
		this.put("msg", msg);
	}

	public Object getData() {
		return this.getString("data");
	}

	public void setData(Object data) {
		this.put("data", data);
	}

	public String getUrl() {
		return this.getString("url");
	}

	public void setUrl(String url) {
		this.put("url", url);
	}

	public int getErrorCode() {
		return this.getIntValue("errorCode");
	}

	public void setErrorCode(int errorCode) {
		this.put("errorCode", errorCode);
	}

	public String toJsonString() {
		return JSONObject.toJSONString(this, new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty});
	}

	public String toString() {
		return this.toJsonString();
	}
}
