package com.jaspersoft.studio.data;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IQueryDesigner {

	public Control getControl();

	public void setQuery(String query);

	public String getQuery();

	public Control createControl(Composite parent);
	
	public void dispose();

}