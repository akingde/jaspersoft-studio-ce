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
package com.jaspersoft.studio.editor.action.text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.Misc;

public abstract class APropertyComboContributionItem extends ContributionItem implements ISelectionContributionItem {
	protected Combo combo;
	private ToolItem toolitem;
	private SelectionListener sListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (selection.isEmpty())
				return;
			JSSCompoundCommand cc = new JSSCompoundCommand(null);
			StructuredSelection ss = (StructuredSelection) selection;
			for (Iterator<?> it = ss.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof EditPart)
					obj = ((EditPart) obj).getModel();

				Command changeValueCmd = createCommand(obj, combo.getText());
				if (changeValueCmd != null) {
					cc.add(changeValueCmd);
					cc.setReferenceNodeIfNull(obj);
				}
				getCommandStack().execute(cc);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	};

	/**
	 * @param ID
	 *          contribution item identificator
	 */
	public APropertyComboContributionItem(String ID) {
		super(ID);
	}

	protected Control createControl(Composite parent) {
		combo = new Combo(parent, SWT.DROP_DOWN);
		combo.addSelectionListener(sListener);

		if (selection != null) {
			StructuredSelection ss = (StructuredSelection) selection;
			boolean fontsareset = false;
			Object fontSize = null;
			for (Iterator<?> it = ss.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof EditPart)
					obj = ((EditPart) obj).getModel();
				if (obj instanceof MTextElement) {
					MTextElement mtext = (MTextElement) obj;
					if (!fontsareset) {
						model = mtext;
						model.getPropertyChangeSupport().addPropertyChangeListener(modelListener);
						setComboItems();
						fontsareset = true;
						fontSize = mtext.getPropertyActualValue(getPropertyName());
					} else {
						Object fsize = mtext.getPropertyValue(getPropertyName());
						if (fsize == null || !fsize.equals(fontSize)) {
							fontSize = null;
							break;
						}
					}
				}
			}
			setComboText(fontSize);
		}
		combo.pack();
		return combo;
	}

	protected abstract void setComboItems();

	protected void setComboText(Object value) {
		if (combo == null || combo.isDisposed())
			model.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
		else
			combo.setText(Misc.nvl(value, getDefaultValue()).toString());
	}

	protected abstract Object getDefaultValue();

	protected MTextElement model;

	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			setComboText(model.getPropertyActualValue(getPropertyName()));
		}
	}

	private ModelListener modelListener = new ModelListener();

	/**
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	public void dispose() {
		combo = null;
	}

	/**
	 * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Composite)
	 */
	public final void fill(Composite parent) {
		createControl(parent);
	}

	/**
	 * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
	 */
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		Control ctrl = createControl(parent);
		toolitem.setWidth(combo.getSize().x);
		toolitem.setControl(ctrl);
	}

	/**
	 * Returns the editor's command stack. This is done by asking the workbench part for its CommandStack via
	 * {@link org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)}.
	 * 
	 * @return the command stack
	 */
	protected CommandStack getCommandStack() {
		return (CommandStack) workbenchPart.getAdapter(CommandStack.class);
	}

	protected Command createCommand(Object model, Object v) {
		if (!(model instanceof IPropertySource))
			return null;
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget((IPropertySource) model);
		cmd.setPropertyId(getPropertyName());
		cmd.setPropertyValue(v);
		return cmd;
	}

	protected abstract Object getPropertyName();

	private ISelection selection;

	@Override
	public void setSelection(ISelection selection) {
		this.selection = selection;
		if (model != null) {
			model.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
			model = null;
		}
	}

	private IWorkbenchPart workbenchPart;

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
	}
}
