package org.cloudplayer.neetwords.utils;

public enum ResCode {
	SUCCESS(200),
	ACCESS_DENY(400),
	FORBIDDEN(403),
	NOT_FOUND(404),
	RESOURCE_FORBIDDEN(405),
	TIMEOUT(408),
	INTERNAL_SERVER_ERROR(500),
	NOT_SUPPORT(501),
	UNAUTHORIZED(401);



	private int code;
	private ResCode(int c) {code = c;}

	public int getCode() {
		return code;
	}
}
