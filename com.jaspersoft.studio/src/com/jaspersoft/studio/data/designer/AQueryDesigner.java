/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.designer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
		container.setFields(fields);
	}

	public void setParameters(List<JRDesignParameter> params) {
		container.setParameters(params);
	}
}
