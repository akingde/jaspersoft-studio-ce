package com.jaspersoft.studio.data;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IQueryDesigner {

	public abstract Control getControl();

	public abstract void setQuery(String query);

	public abstract String getQuery();

	public Control createControl(Composite parent);

}