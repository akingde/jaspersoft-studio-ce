package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

public abstract class ACachedSelectionAction extends SelectionAction {

	public ACachedSelectionAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void handleSelectionChanged() {
		fresh = false;
		super.handleSelectionChanged();
	}

	private boolean fresh = false;
	private Command command;

	@Override
	protected boolean calculateEnabled() {
		if (!fresh)
			command = createCommand(getSelectedObjects());
		fresh = true;
		return command != null && command.canExecute();
	}

	protected abstract Command createCommand(List<?> selectedObjects);
}
