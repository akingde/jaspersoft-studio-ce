package com.jaspersoft.studio.editor.dnd;

import net.sf.jasperreports.engine.design.JRDesignImage;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.dnd.DND;

import com.jaspersoft.studio.editor.palette.JDCreationTool;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.image.command.dialog.ImageCreationDialog;

/**
 * Custom transfer drop listener for DND operations that supports 
 * the {@link DialogEnabledCommand} commands.
 * <p>
 * 
 * The code in the {@link #handleDrop()} method is similar to the one used in 
 * {@link JDCreationTool#performCreation(int)}. We want to emulate the same behavior.<br/>
 * A working example for this situation is the {@link ImageCreationDialog} that is
 * popup when creating an {@link JRDesignImage} element. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see DialogEnabledCommand
 *
 */
public class JSSTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public JSSTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected CreationFactory getFactory(Object template) {
		return new JDPaletteCreationFactory(template);
	}
	
	@Override
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();
		if (getTargetEditPart() != null) {
			Command command = getCommand();
			if(command instanceof DialogEnabledCommand){
				// If we have a special command that supports dialog (i.e: image creation)
				// we'll show the popup dialog and continue with creation only if
				// the user has confirmed.
				if(((DialogEnabledCommand)command).openDialog()==Dialog.CANCEL){
					getCurrentEvent().detail = DND.DROP_NONE;
					return;
				}
			}
			if (command != null && command.canExecute())
				getViewer().getEditDomain().getCommandStack().execute(command);
			else
				getCurrentEvent().detail = DND.DROP_NONE;
		} else
			getCurrentEvent().detail = DND.DROP_NONE;
		selectAddedObject();
	}

	private void selectAddedObject() {
		Object model = getCreateRequest().getNewObject();
		if (model == null)
			return;
		EditPartViewer viewer = getViewer();
		viewer.getControl().forceFocus();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) {
			// Force a layout first.
			getViewer().flush();
			viewer.select((EditPart) editpart);
		}
	}
	
}
