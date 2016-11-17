/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AQueryDesignerContainer {
	public static final int CONTAINER_WITH_INFO_TABLES = 0x02;
	
	public static final int CONTAINER_WITH_NO_TABLES = 0x01;

	protected AQueryStatus qStatus;

	protected JasperReportsConfiguration jConfig;
	
	protected abstract void createStatusBar(Composite comp);

	public AQueryStatus getQueryStatus() {
		return qStatus;
	}

	public abstract void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException;

	public abstract void setFields(List<JRDesignField> fields);

	public abstract void setParameters(List<JRParameter> params);

	public abstract DataAdapterDescriptor getDataAdapter();

	public void doGetFields() {
		try {
			run(true, true, new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					doGetFields(monitor);
				}
			});
		} catch (InvocationTargetException ex) {
			getQueryStatus().showError(ex.getTargetException());
		} catch (InterruptedException ex) {
			getQueryStatus().showError(ex);
		}
	}

	protected void doGetFields(IProgressMonitor monitor) {
	}

	public abstract int getContainerType();

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	public abstract List<JRDesignField> getCurrentFields();
}
