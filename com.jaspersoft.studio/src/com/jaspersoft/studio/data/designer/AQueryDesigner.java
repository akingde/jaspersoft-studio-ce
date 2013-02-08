/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public abstract class AQueryDesigner implements IQueryDesigner, IRunnableContext {
	protected DataQueryAdapters container;
	protected JasperDesign jDesign;
	protected JRDesignDataset jDataset;

	public AQueryDesigner() {
	}

	public void showError(Throwable t) {
		container.getQueryStatus().showError(t);
	}

	public void showWarning(String msg) {
		container.getQueryStatus().showWarning(msg);
	}

	public void showInfo(String msg) {
		container.getQueryStatus().showInfo(msg);
	}

	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException,
			InterruptedException {
		container.run(fork, cancelable, runnable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		this.jDesign = jDesign;
		this.jDataset = (JRDesignDataset) jDataset;
	}

	public String getQuery() {
		if (jDataset != null)
			return jDataset.getQuery().getText();
		else
			return "";
	}

	public void setParentContainer(DataQueryAdapters parent) {
		this.container = parent;
	}

	public void setFields(List<JRDesignField> fields) {
		// remove duplicates
		List<JRDesignField> toadd = new ArrayList<JRDesignField>();
		Set<String> names = new HashSet<String>();
		for (JRDesignField f : fields) {
			if (names.contains(f.getName()))
				continue;
			names.add(f.getName());
			toadd.add(f);
		}
		container.setFields(toadd);
	}

	public void setParameters(List<JRDesignParameter> params) {
		container.setParameters(params);
	}
}
