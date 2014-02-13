package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public abstract class ACachedSelectionAction extends SelectionAction {

	public ACachedSelectionAction(IWorkbenchPart part) {
		super(part);
	}

	public ACachedSelectionAction(IWorkbenchPart part, int style) {
		super(part, style);
	}

	@Override
	protected void handleSelectionChanged() {
		fresh = false;
		freshChecked = false;
		super.handleSelectionChanged();
	}

	protected boolean fresh = false;
	protected boolean freshChecked = false;
	protected Command command;
	protected boolean ischecked = false;

	@Override
	protected boolean calculateEnabled() {
		if (!fresh)
			command = createCommand(getSelectedObjects());
		fresh = true;
		return command != null && command.canExecute();
	}

	protected Command createCommand(List<?> editparts) {
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart))
			return null;
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement) {
				JSSCompoundCommand cmd = new JSSCompoundCommand((ANode)editpart.getModel());
				cmd.add(new SetValueCommand());
				return cmd;
			}
		}
		return null;
	}
}
