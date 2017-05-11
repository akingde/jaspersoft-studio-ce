/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.swt.widgets.CSashForm.ICustomSashFormListener;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.AWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JasperDesign;

public abstract class AQueryDesigner implements IQueryDesigner, IRunnableContext {
	protected AQueryDesignerContainer container;
	protected JasperDesign jDesign;
	protected JRDesignDataset jDataset;
	protected JasperReportsConfiguration jConfig;

	public AQueryDesigner() {
	}

	public JRDesignDataset getjDataset() {
		return jDataset;
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

	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException {
		container.run(fork, cancelable, runnable);
	}

	public AQueryDesignerContainer getContainer() {
		return container;
	}

	public JasperDesign getjDesign() {
		return jDesign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(JasperDesign jDesign, JRDataset jDataset, JasperReportsConfiguration jConfig) {
		this.jDesign = jDesign;
		this.jDataset = (JRDesignDataset) jDataset;
		this.jConfig = jConfig;
	}

	public String getQuery() {
		if (jDataset != null)
			return jDataset.getQuery().getText();
		else
			return "";
	}

	public void setParentContainer(AQueryDesignerContainer parent) {
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

	public void setParameters(List<JRParameter> params) {
		for (JRParameter dp : params) {
			if (!jDataset.getParametersMap().containsKey(dp.getName()))
				try {
					jDataset.addParameter(dp);
				} catch (JRException e) {
					e.printStackTrace();
				}
		}
		container.setParameters(params);
	}

	public static void showError(IRunnableContext container, Throwable e) {
		if (container instanceof AQueryDesigner)
			((AQueryDesigner) container).showError(e);
		else if (container instanceof AWizardPage)
			((AWizardPage) container).setErrorMessage(e.getMessage());
		else
			UIUtils.showError(e);
	}

	public static void showInfo(IRunnableContext container, String msg) {
		if (container instanceof AQueryDesigner)
			((AQueryDesigner) container).showInfo(msg);
		else if (container instanceof AWizardPage)
			((AWizardPage) container).setMessage(msg);
		else if (!Misc.isNullOrEmpty(msg))
			UIUtils.showInformation(msg);
	}

	public void setJasperConfiguration(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	protected void initSashForm(CSashForm sashform) {
		final String SASH_W1 = getClass().getCanonicalName() + ".sash.w1";
		final String SASH_W2 = getClass().getCanonicalName() + ".sash.w2";
		int w1 = 450;
		int w2 = 500;
		if (jDataset != null) {
			try {
				String sw1 = jDataset.getPropertiesMap().getProperty(SASH_W1);
				if (sw1 != null)
					w1 = Integer.parseInt(sw1);
			} catch (NumberFormatException e) {
			}
			try {
				String sw2 = jDataset.getPropertiesMap().getProperty(SASH_W2);
				if (sw2 != null)
					w2 = Integer.parseInt(sw2);
			} catch (NumberFormatException e) {
			}
		}
		sashform.setWeights(new int[] { w1, w2 });
		sashform.addCustomSashFormListener(new ICustomSashFormListener() {

			@Override
			public void dividerMoved(int firstControlWeight, int secondControlWeight) {
				jDataset.getPropertiesMap().setProperty(SASH_W1, Integer.toString(firstControlWeight));
				jDataset.getPropertiesMap().setProperty(SASH_W2, Integer.toString(secondControlWeight));
			}
		});
	}
}
