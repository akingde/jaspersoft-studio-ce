package com.jaspersoft.studio.data.widget;

import com.jaspersoft.studio.data.DataAdapterDescriptor;

public interface IDataAdapterRunnable {
	public boolean isNotRunning();

	public void runReport(DataAdapterDescriptor myDataAdapter);
}
