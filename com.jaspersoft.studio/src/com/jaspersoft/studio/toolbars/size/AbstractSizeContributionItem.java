/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.size;

import java.util.List;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.commands.ResizeCommand;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolbar controls to change the size of the selected elements. These are the commond methods, but every
 * toolitem, corresponding to the specific fit action, is provided by the implementation
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractSizeContributionItem extends CommonToolbarHandler{

	protected SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(MGraphicElement.class);
			if (selection.isEmpty())
				return;
			
			JSSCompoundCommand compoundCmd =  new JSSCompoundCommand("Resize Operation", null); 
			int alignment = (Integer)e.widget.getData(WIDGET_DATA_KEY);
			for (Object model : selection) {
				compoundCmd.add(new ResizeCommand(alignment, (MGraphicElement)model));
				compoundCmd.setReferenceNodeIfNull(model);
			}
			
			if (compoundCmd != null){
				CommandStack cs = getCommandStack();
				cs.execute(compoundCmd);
			}
		}
	};
	
	@Override
	protected abstract Control createControl(Composite parent);
	
	@Override
	protected abstract boolean fillWithToolItems(ToolBar parent);
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MGraphicElement.class);
		return selection.size() > 0;
	}
}
