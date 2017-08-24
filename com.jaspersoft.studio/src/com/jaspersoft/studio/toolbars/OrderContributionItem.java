/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.selection.SelectElementsByValueCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultVariablesAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/**
 * Create the toolbar buttons to change the order of the elements
 * 
 * @author Orlandin Marco
 *
 */
public class OrderContributionItem extends CommonToolbarHandler{
	
	/**
	 * Enumeration used internally to associate to a every button a 
	 * specific movement
	 *
	 */
	private enum ORDER_TYPE{FORWARD, BACKWARD, TOP, BOTTOM, SORT_VARIABLES, SORT_FIELDS, SORT_PARAMETERS};
	
	private ToolItem sortElement;
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(ANode.class);
			if (selection.isEmpty())
				return;
			
			
			Command compoundCmd = null;
			if (ORDER_TYPE.FORWARD.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = generateBringForwardCommand(selection);
			} else if (ORDER_TYPE.BACKWARD.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = generateBringBackwardCommand(selection);
			} else if (ORDER_TYPE.TOP.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = generateBringTopCommand(selection);
			} else if (ORDER_TYPE.BOTTOM.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = generateBringBottomCommand(selection);
			}  else if (ORDER_TYPE.BOTTOM.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = generateBringBottomCommand(selection);
			} else if (ORDER_TYPE.SORT_FIELDS.equals(e.widget.getData(WIDGET_DATA_KEY))){
				compoundCmd = sortFieldsCommand(selection);
			} else if (ORDER_TYPE.SORT_PARAMETERS.equals(e.widget.getData(WIDGET_DATA_KEY))){
				boolean proceed = UIUtils.showConfirmation(Messages.OrderContributionItem_confTitle, MessageFormat.format(Messages.OrderContributionItem_confMessage, Messages.OrderContributionItem_paramName));
				if (proceed) {
					compoundCmd = sortParametersCommand(selection);
				}	
			} else if (ORDER_TYPE.SORT_VARIABLES.equals(e.widget.getData(WIDGET_DATA_KEY))){
				boolean proceed = UIUtils.showConfirmation(Messages.OrderContributionItem_confTitle, MessageFormat.format(Messages.OrderContributionItem_confMessage, Messages.OrderContributionItem_varName));
				if (proceed) {
					compoundCmd = sortVariablesCommand(selection);
				}
			} 
			if (compoundCmd != null && compoundCmd.canExecute()){
				CommandStack cs = getCommandStack();
				cs.execute(compoundCmd);
			}
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_forward.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.FORWARD);
		moveButton.setToolTipText(Messages.BringForwardAction_bring_forward_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_backward.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.BACKWARD);
		moveButton.setToolTipText(Messages.BringBackwardAction_send_backward_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_to_front.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.TOP);
		moveButton.setToolTipText(Messages.BringToFrontAction_bring_to_front_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.BOTTOM);
		moveButton.setToolTipText(Messages.BringToBackAction_send_to_back_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		sortElement = new ToolItem(buttons, SWT.PUSH);
		sortElement.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/sort-alpha-asc.png")); //$NON-NLS-1$
		sortElement.addSelectionListener(pushButtonPressed);
		setSortButtonEnablement();
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem moveForward = new ToolItem(parent, SWT.PUSH);
		moveForward.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_forward.gif")); //$NON-NLS-1$
		moveForward.setData(WIDGET_DATA_KEY, ORDER_TYPE.FORWARD);
		moveForward.setToolTipText(Messages.BringForwardAction_bring_forward_tool_tip);
		moveForward.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveForward);
		
		ToolItem moveBackward = new ToolItem(parent, SWT.PUSH);
		moveBackward.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		moveBackward.setData(WIDGET_DATA_KEY, ORDER_TYPE.BACKWARD);
		moveBackward.setToolTipText(Messages.BringBackwardAction_send_backward_tool_tip);
		moveBackward.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBackward);
		
		ToolItem moveTop = new ToolItem(parent, SWT.PUSH);
		moveTop.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_to_front.gif")); //$NON-NLS-1$
		moveTop.setData(WIDGET_DATA_KEY, ORDER_TYPE.TOP);
		moveTop.setToolTipText(Messages.BringToFrontAction_bring_to_front_tool_tip);
		moveTop.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveTop);
		
		ToolItem moveBottom = new ToolItem(parent, SWT.PUSH);
		moveBottom.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_backward.gif")); //$NON-NLS-1$
		moveBottom.setData(WIDGET_DATA_KEY, ORDER_TYPE.BOTTOM);
		moveBottom.setToolTipText(Messages.BringToBackAction_send_to_back_tool_tip);
		moveBottom.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBottom);
		
		sortElement = new ToolItem(parent, SWT.PUSH);
		sortElement.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/sort-alpha-asc.png")); //$NON-NLS-1$
		sortElement.addSelectionListener(pushButtonPressed);
		setSortButtonEnablement();
		
		return true;
	}
	
	private boolean setSortButtonEnablement() {
		if (sortElement == null) return false;
		setSortElementStatus(false, Messages.OrderContributionItem_disabledtooltip, null);
		if (areParameters()) {
			setSortElementStatus(true, Messages.OrderContributionItem_paramTooltip, ORDER_TYPE.SORT_PARAMETERS);
		} else {
			if (areVariables()) {
				setSortElementStatus(true, Messages.OrderContributionItem_varTooltip, ORDER_TYPE.SORT_VARIABLES);
			} else {
				if (areFields()) {
					setSortElementStatus(true, Messages.OrderContributionItem_fieldstooltip, ORDER_TYPE.SORT_FIELDS);
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		
		List<Object> selection = getSelectionForType(MGraphicElement.class);
		if (selection.size() == 0){
			return setSortButtonEnablement();
		} else {
			setSortElementStatus(false, Messages.OrderContributionItem_disabledtooltip, null);
		}
		return true;
	}
	
	private void setSortElementStatus(boolean enablement, String tooltip, ORDER_TYPE type) {
		if (sortElement != null && !sortElement.isDisposed()) {
			sortElement.setEnabled(enablement);
			sortElement.setData(WIDGET_DATA_KEY, type);
			sortElement.setToolTipText(tooltip);
		}
	}
	
	private boolean areParameters() {
		List<Object> selection = getSelectionForType(MParameter.class);
		if (selection.size() == 0) {
			 selection = getSelectionForType(MParameters.class);
		}
		return !selection.isEmpty();
	}
	
	private boolean areVariables() {
		List<Object> selection = getSelectionForType(MVariable.class);
		if (selection.size() == 0) {
			 selection = getSelectionForType(MVariables.class);
		}
		return !selection.isEmpty();
	}
	
	private boolean areFields() {
		List<Object> selection = getSelectionForType(MField.class);
		if (selection.size() == 0) {
			 selection = getSelectionForType(MFields.class);
		}
		return !selection.isEmpty();
	}
	
	private CompoundCommand generateBringForwardCommand(List<Object> selection){
		CompoundCommand compoundCmd = new CompoundCommand("Move Elements"); //$NON-NLS-1$
		JSSCompoundCommand sortCommand = new JSSCompoundCommand("Move elements", null); //$NON-NLS-1$
		Command cmd = null;
		List<Object> movedElements = new ArrayList<Object>();
		EditPartViewer viewer = null;
		for(Object model : selection){
			ANode currentElement = (ANode)model;
			ANode parent =  currentElement.getParent();
			sortCommand.setReferenceNodeIfNull(parent);
			if (parent != null && parent.getChildren() != null) {
				int newIndex = parent.getChildren().indexOf(model) + 1;
				if (newIndex < parent.getChildren().size()) {
					cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, newIndex);
					if (cmd != null && cmd.canExecute()){
						movedElements.add(currentElement.getValue());
						if (viewer == null){
							EditPart part = SelectionHelper.getEditPart(currentElement);
							if (part != null){
								viewer = part.getViewer();
							}
						}
						sortCommand.add(cmd);
					}
				} else {
					return null;
				}
			}
		}
		compoundCmd.add(sortCommand);
		compoundCmd.add(new SelectElementsByValueCommand(movedElements, viewer));
		return compoundCmd;
	}
	
	private CompoundCommand generateBringBackwardCommand(List<Object> selection){
		CompoundCommand compoundCmd = new CompoundCommand("Move Elements"); //$NON-NLS-1$
		JSSCompoundCommand sortCommand = new JSSCompoundCommand("Move elements", null); //$NON-NLS-1$
		Command cmd = null;
		List<Object> movedElements = new ArrayList<Object>();
		EditPartViewer viewer = null;
		for(Object model : selection){
			ANode currentElement = (ANode)model;
			ANode parent = currentElement.getParent();
			sortCommand.setReferenceNodeIfNull(parent);
			if (parent == null) return null;
			int newIndex = parent.getChildren().indexOf(model) - 1;
			if (newIndex >= 0) {
				cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, newIndex);
				if (cmd != null && cmd.canExecute()){
					movedElements.add(currentElement.getValue());
					if (viewer == null){
						EditPart part = SelectionHelper.getEditPart(currentElement);
						if (part != null){
							viewer = part.getViewer();
						}
					}
					sortCommand.add(cmd);
				}
			} else {
				return null;
			}
		}
		compoundCmd.add(sortCommand);
		compoundCmd.add(new SelectElementsByValueCommand(movedElements, viewer));
		return compoundCmd;
	}
	
	private CompoundCommand generateBringTopCommand(List<Object> selection){
		CompoundCommand compoundCmd = new CompoundCommand("Move Elements"); //$NON-NLS-1$
		JSSCompoundCommand sortCommand = new JSSCompoundCommand("Move elements", null); //$NON-NLS-1$
		Command cmd = null;
		int j = 0;
		List<Object> movedElements = new ArrayList<Object>();
		EditPartViewer viewer = null;
		for (Object model : selection) {
			ANode currentElement = (ANode)model;
			ANode parent = currentElement.getParent();
			sortCommand.setReferenceNodeIfNull(parent);
			if (parent != null) {
				int newIndex = parent.getChildren().size() - 1;
				if (parent.getChildren().indexOf(currentElement) < parent.getChildren().size() - 1) {
					cmd = OutlineTreeEditPartFactory.getReorderCommand(currentElement, parent, newIndex - j);
					j++;
					if (cmd != null && cmd.canExecute()){
						movedElements.add(currentElement.getValue());
						if (viewer == null){
							EditPart part = SelectionHelper.getEditPart(currentElement);
							if (part != null){
								viewer = part.getViewer();
							}
						}
						sortCommand.add(cmd);
					}
				} else {
					return null;
				}
			}
		}
		compoundCmd.add(sortCommand);
		compoundCmd.add(new SelectElementsByValueCommand(movedElements, viewer));
		return compoundCmd;
	}
	
	private Command sortParametersCommand(List<Object> selection) {
		Object firstNode = selection.get(0);
		MParameters<?> parameters = null;
		
		if (firstNode instanceof MParameters) {
			parameters = (MParameters<?>)firstNode;
		} else if (firstNode instanceof MParameter) {
			parameters = (MParameters<?>)((ANode)firstNode).getParent();
		}
		
		if (parameters != null) {
			EditPart part = SelectionHelper.getEditPart(parameters);
			EditPartViewer viewer = part.getViewer();
			List<INode> children = new ArrayList<INode>(parameters.getChildren());
			Collections.sort(children, new Comparator<INode>() {
				
				@Override
				public int compare(INode o1, INode o2) {
					MParameterSystem var1 = (MParameterSystem)o1;
					MParameterSystem var2 = (MParameterSystem)o2;
					String nameVar1 = (String)var1.getPropertyActualValue(JRDesignParameter.PROPERTY_NAME);
					String nameVar2 = (String)var2.getPropertyActualValue(JRDesignParameter.PROPERTY_NAME);
					return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
				}
			});
			JSSCompoundCommand sortParametersCommand = new JSSCompoundCommand("Sort Parameters", parameters); //$NON-NLS-1$
			int systemOffset = 0;
			JasperReportsConfiguration jrContext = parameters.getJasperConfiguration();
			boolean showDefaults = jrContext != null ? jrContext.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE) : true;
			showDefaults = showDefaults && !HideDefaultVariablesAction.areDefaultVariablesHidden(jrContext);
			if (showDefaults) {
				JRDesignDataset jrDataset = ModelUtils.getDataset(parameters);
				for (JRParameter p : jrDataset.getParametersList()) {
					if (p.isSystemDefined())
						systemOffset++;
					else
						break;
				}
			}
			int j = 0;
			for (INode node : children) {
				if (!node.getClass().equals(MParameterSystem.class)) {
					Command sortCommand = OutlineTreeEditPartFactory.getReorderCommand((ANode)node, parameters, j + systemOffset);
					if (sortCommand != null) {
						sortParametersCommand.add(sortCommand);
						j++;
					}
	
				}
			}
			if (!sortParametersCommand.isEmpty()) {
				CompoundCommand cmd = new CompoundCommand("Sort Parameters"); //$NON-NLS-1$
				cmd.add(sortParametersCommand);
				cmd.add(new SelectElementsByValueCommand(selection, viewer));
				return cmd;
			}
			
		}
		return null;
	}
	
	private Command sortVariablesCommand(List<Object> selection) {
		Object firstNode = selection.get(0);
		MVariables variables = null;
		
		if (firstNode instanceof MVariables) {
			variables = (MVariables)firstNode;
		} else if (firstNode instanceof MVariable) {
			variables = (MVariables)((ANode)firstNode).getParent();
		}
		
		if (variables != null) {
			EditPart part = SelectionHelper.getEditPart(variables);
			EditPartViewer viewer = part.getViewer();
			List<INode> children = new ArrayList<INode>(variables.getChildren());
			Collections.sort(children, new Comparator<INode>() {
				
				@Override
				public int compare(INode o1, INode o2) {
					MVariableSystem var1 = (MVariableSystem)o1;
					MVariableSystem var2 = (MVariableSystem)o2;
					String nameVar1 = (String)var1.getPropertyActualValue(JRDesignVariable.PROPERTY_NAME);
					String nameVar2 = (String)var2.getPropertyActualValue(JRDesignVariable.PROPERTY_NAME);
					return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
				}
			});
			JSSCompoundCommand sortParametersCommand = new JSSCompoundCommand("Sort Variables", variables); //$NON-NLS-1$
			int systemOffset = 0;
			JasperReportsConfiguration jrContext = variables.getJasperConfiguration();
			boolean showDefaults = jrContext != null ? jrContext.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE) : true;
			showDefaults = showDefaults && !HideDefaultVariablesAction.areDefaultVariablesHidden(jrContext);
			if (showDefaults) {
				JRDesignDataset jrDataset = ModelUtils.getDataset(variables);
				for (JRVariable v : jrDataset.getVariablesList()) {
					if (v.isSystemDefined())
						systemOffset++;
					else
						break;
				}
			}
			int j = 0;
			for (INode node : children) {
				if (!node.getClass().equals(MVariableSystem.class)) {
					Command sortCommand = OutlineTreeEditPartFactory.getReorderCommand((ANode)node, variables, j + systemOffset);
					if (sortCommand != null) {
						sortParametersCommand.add(sortCommand);
						j++;
					}
	
				}
			}
			if (!sortParametersCommand.isEmpty()) {
				CompoundCommand cmd = new CompoundCommand("Sort Variables"); //$NON-NLS-1$
				cmd.add(sortParametersCommand);
				cmd.add(new SelectElementsByValueCommand(selection, viewer));
				return cmd;
			}
			
		}
		return null;
	}
	
	private Command sortFieldsCommand(List<Object> selection) {
		Object firstNode = selection.get(0);
		MFields fields = null;
		
		if (firstNode instanceof MFields) {
			fields = (MFields)firstNode;
		} else if (firstNode instanceof MField) {
			fields = (MFields)((ANode)firstNode).getParent();
		}
		
		if (fields != null) {
			EditPart part = SelectionHelper.getEditPart(fields);
			EditPartViewer viewer = part.getViewer();
			List<INode> children = new ArrayList<INode>(fields.getChildren());
			Collections.sort(children, new Comparator<INode>() {
				
				@Override
				public int compare(INode o1, INode o2) {
					MField var1 = (MField)o1;
					MField var2 = (MField)o2;
					String nameVar1 = (String)var1.getPropertyActualValue(JRDesignField.PROPERTY_NAME);
					String nameVar2 = (String)var2.getPropertyActualValue(JRDesignField.PROPERTY_NAME);
					return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
				}
			});
			JSSCompoundCommand sortParametersCommand = new JSSCompoundCommand("Sort Fields", fields); //$NON-NLS-1$
			int j = 0;
			for (INode node : children) {
				Command sortCommand = OutlineTreeEditPartFactory.getReorderCommand((ANode)node, fields, j);
				if (sortCommand != null) {
					sortParametersCommand.add(sortCommand);
					j++;
				}
			}
			if (!sortParametersCommand.isEmpty()) {
				CompoundCommand cmd = new CompoundCommand("Sort Fields"); //$NON-NLS-1$
				cmd.add(sortParametersCommand);
				cmd.add(new SelectElementsByValueCommand(selection, viewer));
				return cmd;
			}
			
		}
		return null;
	}
	
	private CompoundCommand generateBringBottomCommand(List<Object> selection){
		CompoundCommand compoundCmd = new CompoundCommand("Move Elements"); //$NON-NLS-1$
		JSSCompoundCommand sortCommand = new JSSCompoundCommand("Move elements", null); //$NON-NLS-1$
		Command cmd = null;
		int j = 0;
		List<Object> movedElements = new ArrayList<Object>();
		EditPartViewer viewer = null;
		for (Object model : selection) {
			ANode currentElement = (ANode)model;
			ANode parent = currentElement.getParent();
			sortCommand.setReferenceNodeIfNull(parent);
			if (parent != null && parent.getChildren().indexOf(model) > 0) {
				cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, j);
				j++;
				if (cmd != null && cmd.canExecute()){
					movedElements.add(currentElement.getValue());
					if (viewer == null){
						EditPart part = SelectionHelper.getEditPart(currentElement);
						if (part != null){
							viewer = part.getViewer();
						}
					}
					sortCommand.add(cmd);
				}
			} else {
				return null;
			}
		}
		compoundCmd.add(sortCommand);
		compoundCmd.add(new SelectElementsByValueCommand(movedElements, viewer));
		return compoundCmd;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (sortElement != null){
			sortElement.dispose();
			sortElement = null;
		}
	}
}
