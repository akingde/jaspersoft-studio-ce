/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.palette.PaletteContextMenuProvider;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;

import com.jaspersoft.studio.editor.tools.DeleteCompositeElementAction;
import com.jaspersoft.studio.editor.tools.EditCompositeElementAction;
import com.jaspersoft.studio.editor.tools.ExportCompositeElementAction;
import com.jaspersoft.studio.editor.tools.ImportCompositeElementAction;
import com.jaspersoft.studio.editor.tools.OpenElementInDesignerAction;
import com.jaspersoft.studio.editor.tools.CompositeElementTemplateCreationEntry;

/**
 * Context menu provider for the palette that show a different context menu if a custom
 * tool is selected, allowing to delete it
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPaletteContextMenuProvider extends PaletteContextMenuProvider {

	/**
	 * Context menu group for actions of the composite element
	 */
	public static final String MANAGE_ELEMENT = "com.jaspersoft.studio.editor.palette.composite_element.manage"; //$NON-NLS-1$	

	/**
	 * Context menu group for import and export actions of the composite element
	 */
	public static final String IMPORT_ELEMENT = "com.jaspersoft.studio.editor.palette.composite_element.importexport"; //$NON-NLS-1$	
	
	/**
	 * Create the context menu provider for the palette
	 * 
	 * @param palette a not null palette
	 */
	public JSSPaletteContextMenuProvider(PaletteViewer palette) {
		super(palette);
	}
	
	/**
	 * If on the palette it is selected exactly one CompositeElementTemplateCreationEntry then
	 * return it, otherwise it return null
	 */
	protected CompositeElementTemplateCreationEntry getSelectedCompositeElement(){
		if (getPaletteViewer().getSelectedEditParts().size() == 1){
			EditPart selectedPart = (EditPart) getPaletteViewer().getSelectedEditParts().get(0);
			if (selectedPart.getModel() instanceof CompositeElementTemplateCreationEntry){
				return (CompositeElementTemplateCreationEntry)selectedPart.getModel();
			}
		}
		return null;
	}

	/**
	 * Create the menu for the palette, if the selected element is a CompositeElementTemplateCreationEntry to
	 * the menu will be added the action to delete the selected composite element
	 */
	public void buildContextMenu(IMenuManager menu) {
		CompositeElementTemplateCreationEntry selectedElement = getSelectedCompositeElement();
		if (selectedElement != null){
			menu.add(new Separator(MANAGE_ELEMENT));
			menu.appendToGroup(MANAGE_ELEMENT, new EditCompositeElementAction(selectedElement));
			menu.appendToGroup(MANAGE_ELEMENT, new OpenElementInDesignerAction(selectedElement));
			menu.appendToGroup(MANAGE_ELEMENT, new DeleteCompositeElementAction(selectedElement));
		}
		menu.add(new Separator(IMPORT_ELEMENT));
		if (selectedElement != null){
			menu.appendToGroup(IMPORT_ELEMENT, new ExportCompositeElementAction(selectedElement));
		}
		menu.appendToGroup(IMPORT_ELEMENT, new ImportCompositeElementAction());
		super.buildContextMenu(menu);
	}
}
