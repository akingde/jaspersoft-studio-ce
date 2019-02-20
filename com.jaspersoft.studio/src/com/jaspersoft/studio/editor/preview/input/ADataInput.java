/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public abstract class ADataInput implements IDataInput {
	protected Map<String, Object> params;
	protected IParameter param;
	private PropertyChangeSupport pcsupport = new PropertyChangeSupport(this);
	private static ControlDecoration errControlDeco;

	public void addChangeListener(PropertyChangeListener listener) {
		pcsupport.addPropertyChangeListener(listener);
	}

	public void removeChangeListener(PropertyChangeListener listener) {
		pcsupport.removePropertyChangeListener(listener);
	}

	public void dispose() {
		for (PropertyChangeListener pcl : pcsupport.getPropertyChangeListeners())
			pcsupport.removePropertyChangeListener(pcl);
	}

	public IDataInput getInstance() {
		try {
			return this.getClass().newInstance();
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return null;
	}

	public IParameter getParameter() {
		return param;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void createInput(Composite parent, IParameter param, Map<String, Object> params) {
		this.params = params;
		this.param = param;
	}

	public final void updateModel(Object value) {
		updateParameterMap(value);
		dirty = true;
		pcsupport.firePropertyChange(param.getName(), null, value);
	}

	protected void updateParameterMap(Object value) {
		params.put(param.getName(), value);
	}

	private boolean dirty = false;

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isLabeled() {
		return false;
	}

	protected void handleFocusLost() {
		IStatusLineManager statusLineManager = getStatusLineManager();
		if (statusLineManager != null)
			statusLineManager.setMessage(null);
		updateInput();
	}

	public static void setMandatory(IParameter prm, Control num) {
		if (prm.isMandatory()) {
			ControlDecoration controlDecoration = new ControlDecoration(num, SWT.LEFT | SWT.TOP);
			controlDecoration.setDescriptionText(Messages.ADataInput_mandatory);
			controlDecoration.setImage(FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_REQUIRED).getImage());
		}
	}

	private boolean removed = false;

	public boolean isRemoved() {
		return removed;
	}

	protected void setNullable(final IParameter prm, Control num) {
		removed = false;
		Menu menu = new Menu(num);
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText(Messages.ADataInput_removeparam);
		item.addListener(SWT.Selection, e -> {
			ADataInput.this.params.remove(prm.getName());
			ADataInput.this.updateInput();
			removed = true;
		});
		item = new MenuItem(menu, SWT.PUSH);
		item.setText(Messages.ADataInput_settonull);
		item.addListener(SWT.Selection, e -> {
			ADataInput.this.params.put(prm.getName(), null);
			ADataInput.this.updateInput();
		});

		num.setMenu(menu);

		nullDecoration = new ControlDecoration(num, SWT.LEFT | SWT.BOTTOM);
	}

	protected void setDecoratorNullable(IParameter prm) {
		if (nullDecoration == null)
			return;
		if (params.containsKey(prm.getName())) {
			if (params.get(prm.getName()) == null) {
				nullDecoration.setDescriptionText(Messages.ADataInput_settonull_explain);
				nullDecoration.setImage(FieldDecorationRegistry.getDefault()
						.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
			} else {
				nullDecoration.hide();
				return;
			}
		} else {
			nullDecoration.setDescriptionText(Messages.ADataInput_removeparam_explain);
			nullDecoration.setImage(FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
		}
		nullDecoration.show();
		return;
	}

	public static void setError(Control num, String message) {
		if (errControlDeco == null) {
			errControlDeco = new ControlDecoration(num, SWT.LEFT | SWT.BOTTOM);
			FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
			errControlDeco.setImage(fieldDecoration.getImage());
		}
		errControlDeco.show();
		errControlDeco.setDescriptionText(message);
	}

	public static void hideError(Control num) {
		if (errControlDeco != null)
			errControlDeco.hide();
	}

	protected FocusListener focusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
			handleFocusLost();
		}

		@Override
		public void focusGained(FocusEvent e) {
			IStatusLineManager statusLineManager = getStatusLineManager();
			if (statusLineManager != null)
				statusLineManager.setMessage(param.getDescription());
		}

	};
	protected TraverseListener keyListener = e -> {
		if (ADataInput.this.pcontainer != null && e.detail == SWT.TRAVERSE_RETURN)
			ADataInput.this.pcontainer.runReport();
	};

	private PreviewContainer pcontainer;

	public void setPcontainer(PreviewContainer pcontainer) {
		this.pcontainer = pcontainer;
	}

	protected IStatusLineManager getStatusLineManager() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

		IWorkbenchPage page = win.getActivePage();
		if (page == null)
			return null;
		IWorkbenchPart part = page.getActivePart();
		if (part == null)
			return null;
		IWorkbenchPartSite site = part.getSite();
		IActionBars actionBars = null;
		if (site instanceof IEditorSite)
			actionBars = ((IEditorSite) site).getActionBars();
		else if (site instanceof IViewSite)
			actionBars = ((IViewSite) site).getActionBars();
		if (actionBars == null)
			return null;

		return actionBars.getStatusLineManager();
	}

	/**
	 * Method that should called when one of the parameter changed value to allow
	 * the other parameters to update their value. This is used because some
	 * parameters could depend from the others. The default implementation does
	 * notthing
	 * 
	 * @param evt
	 *            the event contains the name of the parameter that changed value
	 */
	public void parameterChanged(PropertyChangeEvent evt) {

	}

	private static int defCharWidth = -1;
	private ControlDecoration nullDecoration;

	public static int getCharWidth(Control c) {
		if (defCharWidth < 0)
			defCharWidth = UIUtil.getCharWidth(c);
		return defCharWidth;
	}

	public JasperReportsConfiguration getjConfig() {
		if (param instanceof ParameterJasper)
			return ((ParameterJasper) param).getjConfig();
		return JasperReportsConfiguration.getDefaultInstance();
	}
}
