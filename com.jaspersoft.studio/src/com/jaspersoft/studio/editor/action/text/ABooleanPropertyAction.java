package com.jaspersoft.studio.editor.action.text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public abstract class ABooleanPropertyAction extends SelectionAction {

	public ABooleanPropertyAction(IWorkbenchPart part) {
		super(part, AS_CHECK_BOX);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = getCommand();
		if (cmd == null)
			return false;
		return cmd.canExecute();
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
		execute(getCommand());
		setChecked(!isChecked());
	}

	private Command getCommand() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty())
			return null;
		boolean checked = !isChecked();
		CompoundCommand cc = new CompoundCommand(getText());
		for (Object obj : editparts) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();

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
