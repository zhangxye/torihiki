package com.tsrs.webedi.common.exception;

/**
 * @Description 所有业务异常的枚举
 * @author Tsrs-WebEDI
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {

	/**
	 * 字典
	 */
	DICT_EXISTED(400,"字典已经存在"),
	ERROR_CREATE_DICT(500,"创建字典失败"),
	ERROR_WRAPPER_FIELD(500,"包装字典属性失败"),


	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400,"FILE_READING_ERROR!"),
	FILE_NOT_FOUND(400,"FILE_NOT_FOUND!"),
	UPLOAD_ERROR(500,"上传图片出错"),

	/**
	 * 权限和数据问题
	 */
	DB_RESOURCE_NULL(400,"数据库中没有该资源"),
	NO_PERMITION(405, "权限异常"),
	REQUEST_INVALIDATE(400,"请求数据格式不正确"),
	INVALID_KAPTCHA(400,"验证码不正确"),
	CANT_DELETE_ADMIN(600,"不能削除超级管理员"),
	CANT_FREEZE_ADMIN(600,"不能凍結超级管理员"),
	CANT_CHANGE_ADMIN(600,"不能修正超级管理员役割"),

	/**
	 * アカウント问题
	 */
	USER_ALREADY_REG(401,"このユーザーが存在しました。"),
	NO_THIS_USER(400,"没有此用户"),
	USER_NOT_EXISTED(400, "ユーザー存在しません"),
	ACCOUNT_FREEZED(401, "アカウント被凍結"),
	OLD_PWD_NOT_RIGHT(402, "元パスワードが間違いです。"),
	TWO_PWD_NOT_MATCH(405, "二つ入力パスワードが不一致です。"),

	/**
	 * 错误的请求
	 */
	REQUEST_NULL(400, "请求有错误"),
	SESSION_TIMEOUT(400, "会话超时"),
	SERVER_ERROR(500, "服务器异常");

	BizExceptionEnum(int code, String message) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
	}
	
	BizExceptionEnum(int code, String message,String urlPath) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
		this.urlPath = urlPath;
	}

	private int friendlyCode;

	private String friendlyMsg;
	
	private String urlPath;

	public int getCode() {
		return friendlyCode;
	}

	public void setCode(int code) {
		this.friendlyCode = code;
	}

	public String getMessage() {
		return friendlyMsg;
	}

	public void setMessage(String message) {
		this.friendlyMsg = message;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
