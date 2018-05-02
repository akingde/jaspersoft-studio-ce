/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.alignment;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.commands.AlignCommand;
import com.jaspersoft.studio.formatting.actions.JoinLeftAction;
import com.jaspersoft.studio.formatting.actions.JoinRightAction;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolbar controls to align the selected elements. These are the common methods, but every
 * toolitem, corresponding to the specific align action, is provided by the implementation
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractAlignmentContributionItem extends CommonToolbarHandler{
	
	/**
	 * Enumaration for the join buttons pressed, to know if the pressed button is
	 * join left or join right
	 */
	protected enum JOIN_DIRECTION{LEFT, RIGHT};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	protected SelectionAdapter alignButtonPressed = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(MGraphicElement.class);
			if (selection.isEmpty())
				return;
			
			Integer alignment = (Integer)e.widget.getData(WIDGET_DATA_KEY);
			JSSCompoundCommand command = new JSSCompoundCommand("Align Command", null);
			for (Object model : selection) {
				command.add(new AlignCommand(alignment, (MGraphicElement)model));
				command.setReferenceNodeIfNull(model);
			}
			getCommandStack().execute(command);
		}
	};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	protected SelectionAdapter joinButtonPressed = new SelectionAdapter() {
	
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(MGraphicElement.class);
			if (selection.isEmpty())
				return;
			JSSCompoundCommand command = null;
			@SuppressWarnings("unchecked")
			List<APropertyNode> typedSelection = (List<APropertyNode>)(List<?>)selection;
			if (JOIN_DIRECTION.LEFT.equals(e.widget.getData(WIDGET_DATA_KEY))){
				command = JoinLeftAction.generateCommand(typedSelection);
			} else if (JOIN_DIRECTION.RIGHT.equals(e.widget.getData(WIDGET_DATA_KEY))){
				command = JoinRightAction.generateCommand(typedSelection);
			}
			if (command != null) getCommandStack().execute(command);
		}
	};

	protected abstract Control createControl(Composite parent);
	
	protected abstract boolean fillWithToolItems(ToolBar parent);
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MGraphicElement.class);
		return selection.size() > 0;
	}
}
