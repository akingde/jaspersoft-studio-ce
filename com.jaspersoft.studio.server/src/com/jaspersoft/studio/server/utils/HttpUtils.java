package com.jaspersoft.studio.server.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpUtils {
	public static HttpMethod get(HttpClient client, String url)
			throws HttpException, IOException {
		HttpMethod method = new GetMethod(url);
		method.setRequestHeader("Content-Type", "application/json");

		method.toString();
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK)
			System.err.println("Method failed: " + method.getStatusLine());
		return method;
	}
}
