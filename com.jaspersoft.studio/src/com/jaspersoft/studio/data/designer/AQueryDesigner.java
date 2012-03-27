package com.jaspersoft.studio.data.designer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.operation.IRunnableWithProgress;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jobs.IRunWithProgress;

public abstract class AQueryDesigner implements IQueryDesigner, IRunWithProgress {
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

	public void runJob(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		container.run(runnable);
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
