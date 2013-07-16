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
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintListener;
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

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.internal.IHighlightPropertyWidget;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.UIUtil;

public abstract class ASPropertyWidget implements IHighlightPropertyWidget {
	protected IPropertyDescriptor pDescriptor;
	protected AbstractSection section;

	protected ControlListener checkResize = new ControlAdapter() {

		@Override
		public void controlResized(ControlEvent e) {
			getControlToBorder().redraw();

		}
	};

	public ASPropertyWidget(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		this.pDescriptor = pDescriptor;
		this.section = section;
		createComponent(parent);
		if (getControl() != null)
			setupFocusControl(pDescriptor, getControl());
	}

	protected void setupFocusControl(IPropertyDescriptor pDescriptor, Control c) {
		if (c.isEnabled()) {
			c.addFocusListener(focusListener);
			HelpSystem.bindToHelp(pDescriptor, c);
		}
		if (c instanceof Composite) {
			for (Control cc : ((Composite) c).getChildren())
				setupFocusControl(pDescriptor, cc);
		}
	}

	public void setReadOnly(boolean readonly) {
		if (getControl() != null)
			getControl().setEnabled(!readonly);
	}

	protected abstract void createComponent(Composite parent);

	public abstract void setData(APropertyNode pnode, Object value);

	public String getId() {
		return pDescriptor.getId().toString();
	}

	public String getName() {
		return pDescriptor.getDisplayName();
	}

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

	public abstract Control getControl();

	/**
	 * Since a property widget can have many controls inside it, this method return the control to which a border will be
	 * added to highlight the widget
	 * 
	 * @return control to border
	 */
	protected Control getControlToBorder() {
		return getControl();
	}

	/**
	 * Get the paint listener to highlight the control returned from getControlToBorder(). By default the PaintListner is
	 * read from a container where are defined some listener for the highlight of the most common controls.
	 * 
	 * @return a not null paint listener
	 */
	protected PaintListener getPaintControlListener() {
		return DefaultWidgetsHighlighters.getWidgetForType(getControlToBorder().getClass());
	}

	/**
	 * Default behavior for the highlight of a widget, it add a colored bored around one of the controls inside the
	 * ASPropertyWidget. This border is removed after a the specified time
	 * 
	 */
	@Override
	public void highLightWidget(long ms) {
		// if there isn't a control defined where add the border then return
		if (getControlToBorder() == null)
			return;
		final PaintListener highlighter = getPaintControlListener();
		getControlToBorder().addPaintListener(highlighter);
		// getControlToBorder().addControlListener(checkResize);
		getControlToBorder().redraw();
		final long sleepTime = ms;
		// Create a thread to remove the paint listener after specified time
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(sleepTime);
					// It need two thread to avoid to freeze the UI during the sleep
					getControlToBorder().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							getControlToBorder().removePaintListener(highlighter);
							// getControlToBorder().removeControlListener(checkResize);
							getControlToBorder().redraw();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static int defCharWidth = -1;

	public static int getCharWidth(Control c) {
		if (defCharWidth < 0)
			defCharWidth = UIUtil.getCharWidth(c);
		return defCharWidth;
	}
}
