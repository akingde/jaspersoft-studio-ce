/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.IContainer;

/**
 * Custom creation tool that add support for dialog prompted during the creation of an element dragged from the palette.
 * It also use a custom pointer for the marquee creation of the elements
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see DialogEnabledCommand
 * 
 */
public final class JDCreationTool extends CreationTool {
	
	/**
	 * Cursor for the marquee creation of the elements
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_CREATE = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/Tree_Add_Mask.gif", "/icons/cursors/Tree_Add.gif", 8, 12);
	
	public JDCreationTool() {
		super();
		setDefaultCursor(CURSOR_CREATE);
		setDisabledCursor(SharedCursors.NO);
	}

	public JDCreationTool(CreationFactory aFactory) {
		super(aFactory);
	}

	@Override
	protected void performCreation(int button) {
		Command currCommand = getCurrentCommand();
		if (currCommand instanceof DialogEnabledCommand && currCommand.canExecute()) {
			// If we have a special command that supports dialog (i.e: image creation)
			// we'll show the popup dialog and continue with creation only if
			// the user has confirmed.
			if (((DialogEnabledCommand) currCommand).openDialog() == Dialog.CANCEL) {
				return;
			}

			// we have to execute on the same command, because between getCurrentCommand , mouse events call setCurrentCommand
			if (currCommand != null && currCommand.canExecute())
				executeCommand(currCommand);
			setCurrentCommand(null);
		}
		super.performCreation(button);
	}
	
	/**
	 * Take an edit part and search it's container if it is not a container.
	 * 
	 * @return the container of the actual target edit part, if the target edit part is not a container, or the target
	 *         edit part itself otherwise
	 */
	private EditPart getContainer(EditPart target) {
		if (target instanceof FigureEditPart) {
			return ((FigureEditPart)target).getDropContainer();
		} else if (target instanceof IContainer) {
			return target;
		} else {
			return FigureEditPart.getParentEditPart(target);
		}
	}
	
	
	/**
	 * Updates the target editpart and returns <code>true</code> if the target
	 * changes. The target is updated by using the target conditional and the
	 * target request. If the target has been locked, this method does nothing
	 * and returns <code>false</code>.
	 * 
	 * @return <code>true</code> if the target was changed
	 */
	protected boolean updateTargetUnderMouse() {
		if (!isTargetLocked()) {
			EditPart editPart = null;
			if (getCurrentViewer() != null)
				editPart = getCurrentViewer().findObjectAtExcluding(getLocation(), getExclusionSet(),getTargetingConditional());
			if (editPart != null)
				editPart = editPart.getTargetEditPart(getTargetRequest());
			EditPart container = getContainer(editPart);
			boolean changed = getTargetEditPart() != container;
			setTargetEditPart(container);
			return changed;
		} else
			return false;
	}
}
