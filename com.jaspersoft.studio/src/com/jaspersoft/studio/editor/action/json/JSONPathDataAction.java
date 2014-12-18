/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.decorator.json.PathAndDataDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

/**
 * This class implement a JSON action that create a new column in the JSON. This action can be performed only on textual
 * elements (Textfield and static text). When a column is created it is asked using a dialog the column name. The order
 * of the column in the JSON will be the same order of creation, anyway this can be changed using an appropriate action
 * 
 * @author Veaceslav Chicu
 * 
 */
public class JSONPathDataAction extends JSONAction {

	private String path;
	private JRExpression data;
	private boolean repeat = false;
	
	public static final String JSON_EXPORTER_PROPERTIES_PREFIX = JRPropertiesUtil.PROPERTY_PREFIX + "export.json.";
	public static final String JSON_EXPORTER_PATH_PROPERTY = JSON_EXPORTER_PROPERTIES_PREFIX + "path";
	public static final String JSON_EXPORTER_REPEAT_VALUE_PROPERTY = JSON_EXPORTER_PROPERTIES_PREFIX + "repeat.value";
	public static final String JSON_EXPORTER_DATA_PROPERTY = JSON_EXPORTER_PROPERTIES_PREFIX + "data";


	/**
	 * Create the action with id @JSONAction.PROP_DATA
	 * 
	 * @param part
	 * @param actionName
	 *          the textual description of the action
	 */
	public JSONPathDataAction(IWorkbenchPart part) {
		super(part, JSON_EXPORTER_PATH_PROPERTY, Messages.JSONPathDataAction_0);
	}

	/**
	 * If an element is already a column than the property is removed from it, otherwise it will became a csw column with
	 * a column name defined by the user
	 */
	@Override
	public void run() {
		if (checkFrameParent()) {
			boolean dialogResult = MessageDialog
					.openQuestion(
							UIUtils.getShell(),
							Messages.JSONPathDataAction_1,
							Messages.JSONPathDataAction_2);
			if (!dialogResult)
				return;
		}
		MTextElement element = getSelectedElement();
		if (element == null)
			return;
		PathAndDataDialog dialog = new PathAndDataDialog(UIUtils.getShell(), element);
		if (dialog.open() == Dialog.OK) {
			path = dialog.getName();
			data = dialog.getData();
			repeat = dialog.isRepeat();
			execute(createCommand());
		}
	}

	private MTextElement getSelectedElement() {
		List<Object> textElements = editor.getSelectionCache().getSelectionModelForType(MTextElement.class);
		if (textElements.isEmpty() || textElements.size() != getSelectedObjects().size())
			return null;
		return (MTextElement) textElements.get(0);
	}

	@Override
	public boolean isChecked() {
		List<Object> textElements = editor.getSelectionCache().getSelectionModelForType(MTextElement.class);
		if (textElements.isEmpty() || textElements.size() != getSelectedObjects().size())
			return false;
		for (Object element : textElements) {
			MTextElement model = (MTextElement) element;
			JRPropertiesMap colDataMap = (JRPropertiesMap) model.getPropertiesMap();
			return colDataMap.containsProperty(JSON_EXPORTER_PATH_PROPERTY);
		}
		return true;
	}

	private boolean checkFrameParent() {
		List<Object> textElements = editor.getSelectionCache().getSelectionModelForType(MTextElement.class);
		if (textElements.isEmpty() || getSelectedObjects().size() > 1)
			return false;
		MTextElement element = (MTextElement) textElements.get(0);
		if (element.getParent() instanceof MFrame)
			return true;
		return false;
	}

	@Override
	protected boolean calculateEnabled() {
		return !editor.getSelectionCache().getSelectionModelForType(MTextElement.class).isEmpty();
	}

	/**
	 * Create the commands necessary to transform a textual element into a JSON column or to remove it is it is already a
	 * JSON column
	 * 
	 */
	@Override
	protected Command createCommand() {
		MTextElement n = getSelectedElement();
		if (n == null)
			return null;

		PropertyExpressionsDTO peDTO = (PropertyExpressionsDTO) n
				.getPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
		JRPropertiesMap map = (JRPropertiesMap) n.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		if (map == null)
			map = new JRPropertiesMap();

		if (path == null) {
			map.removeProperty(JSON_EXPORTER_PATH_PROPERTY);
			map.removeProperty(JSON_EXPORTER_REPEAT_VALUE_PROPERTY);

			removeDataPropertyExpression(peDTO);
		} else {
			map.setProperty(JSON_EXPORTER_PATH_PROPERTY, path);
			if (repeat)
				map.setProperty(JSON_EXPORTER_REPEAT_VALUE_PROPERTY, "true"); //$NON-NLS-1$
			else
				map.removeProperty(JSON_EXPORTER_REPEAT_VALUE_PROPERTY);
		}

		if (data == null)
			removeDataPropertyExpression(peDTO);
		else {
			if (peDTO.getPropExpressions() == null)
				peDTO.setPropExpressions(new JRPropertyExpression[0]);
			List<JRPropertyExpression> newPE = new ArrayList<JRPropertyExpression>();
			JRDesignPropertyExpression dpe = null;
			for (JRPropertyExpression pe : peDTO.getPropExpressions()) {
				newPE.add(pe);
				if (pe.getName().equals(JSON_EXPORTER_DATA_PROPERTY))
					dpe = (JRDesignPropertyExpression) pe;
			}
			if (dpe == null)
				dpe = new JRDesignPropertyExpression();
			dpe.setName(JSON_EXPORTER_DATA_PROPERTY);
			dpe.setValueExpression(data);
			newPE.add(dpe);
			peDTO.setPropExpressions(newPE.toArray(new JRPropertyExpression[newPE.size()]));
		}
		peDTO.setPropMap(map);

		JSSCompoundCommand command = new JSSCompoundCommand(null);
		command.setDebugLabel(getText());
		command.setReferenceNodeIfNull(n);

		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(n);
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		cmd.setPropertyValue(map);
		command.add(cmd);

		cmd = new SetValueCommand();
		cmd.setTarget(n);
		cmd.setPropertyId(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
		cmd.setPropertyValue(peDTO);
		command.add(cmd);

		return command;
	}

	protected void removeDataPropertyExpression(PropertyExpressionsDTO peDTO) {
		if (peDTO.getPropExpressions() == null)
			return;
		List<JRPropertyExpression> newPE = new ArrayList<JRPropertyExpression>();
		for (JRPropertyExpression pe : peDTO.getPropExpressions()) {
			if (!pe.getName().equals(JSON_EXPORTER_DATA_PROPERTY))
				newPE.add(pe);
		}
		peDTO.setPropExpressions(newPE.toArray(new JRPropertyExpression[newPE.size()]));
	}
}
