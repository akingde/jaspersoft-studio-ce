/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.palette.PaletteContextMenuProvider;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;

import com.jaspersoft.studio.editor.tools.DeleteToolAction;
import com.jaspersoft.studio.editor.tools.EditToolAction;
import com.jaspersoft.studio.editor.tools.ToolTemplateCreationEntry;

/**
 * Context menu provider for the palette that show a different context menu if a custom
 * tool is selected, allowing to delete it
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPaletteContextMenuProvider extends PaletteContextMenuProvider {

	/**
	 * Context menu group for actions of the custom tools
	 */
	public static final String GROUP_TOOL = "com.jaspersoft.studio.editor.palette.group.tool"; //$NON-NLS-1$	
	
	/**
	 * Create the context menu provider for the palette
	 * 
	 * @param palette a not null palette
	 */
	public JSSPaletteContextMenuProvider(PaletteViewer palette) {
		super(palette);
	}

	/**
	 * Create the menu for the palette, if the selected element is a ToolTemplateCreationEntry to
	 * the menu will be added the action to delete the selected custom tool
	 */
	public void buildContextMenu(IMenuManager menu) {
		if (getPaletteViewer().getSelectedEditParts().size() == 1){	
			EditPart selectedPart = (EditPart) getPaletteViewer().getSelectedEditParts().get(0);
			if (selectedPart.getModel() instanceof ToolTemplateCreationEntry){
				menu.add(new Separator(GROUP_TOOL));
				menu.appendToGroup(GROUP_TOOL, new DeleteToolAction((ToolTemplateCreationEntry)selectedPart.getModel()));
				menu.appendToGroup(GROUP_TOOL, new EditToolAction((ToolTemplateCreationEntry)selectedPart.getModel()));
			}
		}
		super.buildContextMenu(menu);
	}
}
