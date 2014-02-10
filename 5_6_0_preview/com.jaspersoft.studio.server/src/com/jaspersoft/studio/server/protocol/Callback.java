package com.jaspersoft.studio.server.protocol;

public interface Callback<T> {
	public void completed(T value);
}
