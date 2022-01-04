package com.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

	private ResponseStatusEnum status = ResponseStatusEnum.OK;
	private String statusText = null;
	private T body;

	public static <T> Response<T> ok() {
		Response<T> response = new Response<T>();
		response.setStatus(ResponseStatusEnum.OK);
		return response;
	}
	
	public static <T> Response<T> ok(T body) {
		Response<T> response = new Response<T>();
		response.setStatus(ResponseStatusEnum.OK);
		response.setBody(body);
		return response;
	}

	public static <T> Response<T> ok(String statusText, T body) {
		Response<T> response = new Response<T>();
		response.setStatus(ResponseStatusEnum.OK);
		response.setBody(body);
		response.setStatusText(statusText);
		return response;
	}

	public static <T> Response<T> error(String statusText) {
		Response<T> response = new Response<T>();
		response.setStatus(ResponseStatusEnum.ERROR);
		response.setStatusText(statusText);
		return response;
	}

	public static <T> Response<T> error(String statusText, T body) {
		Response<T> response = new Response<T>();
		response.setStatus(ResponseStatusEnum.ERROR);
		response.setBody(body);
		response.setStatusText(statusText);
		return response;
	}
}
