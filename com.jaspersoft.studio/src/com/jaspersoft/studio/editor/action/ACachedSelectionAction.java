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
import com.jaspersoft.studio.utils.ModelUtils;

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
	
	/**
	 * Verifies that there is only one EditPart selected
	 * referring to a model object of the allowed class types.
	 * 
	 * @param classes the allowed type(s) for the model object 
	 * @return <code>true</code> the single model object is instance of
	 * 					one of the allowed types,<code>false</code> otherwise
	 */
	public boolean checkSingleSelectedObject(Class<?>...classes) {
		List<?> selectedObjects = getSelectedObjects();
		if(selectedObjects.size()!=1) return false;
		return ModelUtils.checkTypesForSingleEditPartModel(getSelectedObjects().get(0), true, classes);
	}
	
	/**
	 * Verifies that all the currently selected objects are EditParts
	 * referring to model objects of the allowed class types.
	 * 
	 * @param classes the allowed type(s) for the model objects 
	 * @return <code>true</code> all model objects are instances of 
	 * 					one of the allowed types,<code>false</code> otherwise
	 */
	public boolean checkAllSelectedObjects(Class<?>... classes){
		return ModelUtils.checkTypesForAllEditParModels(getSelectedObjects(), true, classes);
	}
	
}
