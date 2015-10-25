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
package com.jaspersoft.studio.toolbars;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.commands.AlignCommand;
import com.jaspersoft.studio.formatting.actions.JoinLeftAction;
import com.jaspersoft.studio.formatting.actions.JoinRightAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Toolbar controls to align the selected elements
 * 
 * @author Orlandin Marco
 *
 */
public class AlignmentContributionItem extends CommonToolbarHandler{
	
	/**
	 * Enumaration for the join buttons pressed, to know if the pressed button is
	 * join left or join right
	 */
	private enum JOIN_DIRECTION{LEFT, RIGHT};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter alignButtonPressed = new SelectionAdapter() {
		
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
	private SelectionAdapter joinButtonPressed = new SelectionAdapter() {
	
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

	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem alignToLeft = new ToolItem(parent, SWT.PUSH);
		alignToLeft.setToolTipText(Messages.Align2BorderAction_align_to_left_tool_tip);
		alignToLeft.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-left.gif"));
		alignToLeft.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.LEFT));
		alignToLeft.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToLeft);
		
		ToolItem alignToRight = new ToolItem(parent, SWT.PUSH);
		alignToRight.setToolTipText(Messages.Align2BorderAction_align_to_right_tool_tip);
		alignToRight.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-right.gif"));
		alignToRight.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.RIGHT));
		alignToRight.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToRight);

		ToolItem alignToTop = new ToolItem(parent, SWT.PUSH);
		alignToTop.setToolTipText(Messages.Align2BorderAction_align_to_top_tool_tip);
		alignToTop.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-top.gif"));
		alignToTop.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.TOP));
		alignToTop.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToTop);

		ToolItem alignToBottom = new ToolItem(parent, SWT.PUSH);
		alignToBottom.setToolTipText(Messages.Align2BorderAction_align_to_bottom_tool_tip);
		alignToBottom.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-bottom.gif"));
		alignToBottom.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.BOTTOM));
		alignToBottom.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToBottom);
		
		ToolItem alignToCenter = new ToolItem(parent, SWT.PUSH);
		alignToCenter.setToolTipText(Messages.Align2BorderAction_align_to_center_tool_tip);
		alignToCenter.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-center.gif"));
		alignToCenter.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.CENTER));
		alignToCenter.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToCenter);

		ToolItem alignToMiddle = new ToolItem(parent, SWT.PUSH);
		alignToMiddle.setToolTipText(Messages.Align2BorderAction_align_to_middle_tool_tip);
		alignToMiddle.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-middle.gif"));
		alignToMiddle.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.MIDDLE));
		alignToMiddle.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToMiddle);
		
		ToolItem joinLeft = new ToolItem(parent, SWT.PUSH);
		joinLeft.setToolTipText(Messages.JoinLeftAction_actionDescription);
		joinLeft.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinleft.png"));
		joinLeft.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.LEFT);
		joinLeft.addSelectionListener(joinButtonPressed);
		getToolItems().add(joinLeft);
		
		ToolItem joinRight = new ToolItem(parent, SWT.PUSH);
		joinRight.setToolTipText(Messages.JoinRightAction_actionDescription);
		joinRight.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinright.png"));
		joinRight.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.RIGHT);
		joinRight.addSelectionListener(joinButtonPressed);
		getToolItems().add(joinRight);
		
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MGraphicElement.class);
		return selection.size() > 0;
	}
}
