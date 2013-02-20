/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

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

	public static void setMandatory(IParameter param, Control num) {
		if (param.isMandatory()) {
			ControlDecoration controlDecoration = new ControlDecoration(num, SWT.LEFT | SWT.TOP);
			controlDecoration.setDescriptionText("this field is mandatory");
			controlDecoration.setImage(FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_REQUIRED).getImage());
		}
	}

	public static void setError(Control num, String message) {
		if (errControlDeco == null) {
			errControlDeco = new ControlDecoration(num, SWT.LEFT | SWT.BOTTOM);
			FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
					FieldDecorationRegistry.DEC_ERROR);
			errControlDeco.setImage(fieldDecoration.getImage());
		}
		errControlDeco.show();
		errControlDeco.setDescriptionText(message);
	}

	public static void hideError(Control num) {
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

	private IStatusLineManager getStatusLineManager() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

		IWorkbenchPage page = win.getActivePage();

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
}
