/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.custom.CLabel;
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
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public abstract class ASPropertyWidget {
	protected IPropertyDescriptor pDescriptor;
	protected AbstractSection section;

	public ASPropertyWidget(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		this.pDescriptor = pDescriptor;
		this.section = section;
		createComponent(parent);
		if (getControl() != null)
			getControl().addFocusListener(focusListener);
	}

	public void setReadOnly(boolean readonly) {
		if (getControl() != null)
			getControl().setEnabled(!readonly);
	}

	protected abstract void createComponent(Composite parent);

	public abstract void setData(APropertyNode pnode, Object value);

	public abstract Control getControl();

	private CLabel label;

	public CLabel getLabel() {
		return label;
	}

	public void setLabel(CLabel label) {
		this.label = label;
	}

	protected FocusListener focusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
			handleFocusLost();
		}

		@Override
		public void focusGained(FocusEvent e) {
			handleFocusGained();
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

	protected void handleFocusGained() {
		IStatusLineManager statusLineManager = getStatusLineManager();
		if (statusLineManager != null)
			statusLineManager.setMessage(pDescriptor.getDescription());
	}

	protected void handleFocusLost() {
		IStatusLineManager statusLineManager = getStatusLineManager();
		if (statusLineManager != null)
			statusLineManager.setMessage(null);
	}

}
