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
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public abstract class ABooleanPropertyAction extends ACachedSelectionAction {

	public ABooleanPropertyAction(IWorkbenchPart part) {
		super(part, AS_CHECK_BOX);
	}

	@Override
	public boolean isChecked() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty())
			return false;
		Object obj = editparts.get(0);
		if (obj instanceof EditPart)
			obj = ((EditPart) obj).getModel();
		if (checkSelection(obj))
			return getBooleanValue(obj);
		return false;
	}

	public void run() {
		execute(createCommand(getSelectedObjects()));
		setChecked(!isChecked());
	}

	protected Command createCommand(List<?> editparts) {
		if (editparts.isEmpty())
			return null;
		boolean checked = !isChecked();
		JSSCompoundCommand cc = new JSSCompoundCommand(getText(), null);
		for (Object obj : editparts) {
			if (obj instanceof EditPart){
				EditPart part = (EditPart) obj;
				cc.setReferenceNodeIfNull(part.getModel());
				obj = part.getModel();
			}
			
			if (checkSelection(obj)) {
				cc.add(createCommand(obj, checked));
			} else
				return null;
		}
		return cc;
	}

	protected abstract Object getPropertyName();

	protected boolean getBooleanValue(Object obj) {
		Object res = ((APropertyNode) obj).getPropertyActualValue(getPropertyName());
		if (res instanceof Boolean)
			return (Boolean) res;
		return false;
	}

	protected abstract boolean checkSelection(Object obj);

	protected Command createCommand(Object model, Object v) {
		if (!(model instanceof IPropertySource))
			return null;
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget((IPropertySource) model);
		cmd.setPropertyId(getPropertyName());
		cmd.setPropertyValue(v);
		return cmd;
	}

	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	}

	private ModelListener modelListener = new ModelListener();

	@Override
	protected void setSelection(ISelection selection) {
		List<?> editparts = getSelectedObjects();
		for (Object obj : editparts) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof APropertyNode)
				((APropertyNode) obj).getPropertyChangeSupport().removePropertyChangeListener(modelListener);
		}
		super.setSelection(selection);
		for (Object obj : editparts) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof APropertyNode)
				((APropertyNode) obj).getPropertyChangeSupport().addPropertyChangeListener(modelListener);
		}
	}

	@Override
	protected void handleSelectionChanged() {
		super.handleSelectionChanged();
		setChecked(isChecked());
	}
}
