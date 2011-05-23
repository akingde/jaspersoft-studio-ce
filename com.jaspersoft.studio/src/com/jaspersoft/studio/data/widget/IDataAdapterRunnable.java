package com.jaspersoft.studio.data.widget;

import com.jaspersoft.studio.data.DataAdapter;

public interface IDataAdapterRunnable {
	public boolean isNotRunning();

	public void runReport(DataAdapter myDataAdapter);
}
