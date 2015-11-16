/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.xls;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.export.JRXlsAbstractMetadataExporter;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.decorator.xls.PathAndDataDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

/**
 * This class implement a XLS action that create a new column in the XLS. This action can be performed only on textual
 * elements (Textfield and static text). When a column is created it is asked using a dialog the column name. The order
 * of the column in the XLS will be the same order of creation, anyway this can be changed using an appropriate action
 * 
 * @author Veaceslav Chicu
 * 
 */
public class XLSPathDataAction extends XLSAction {

	private String path;
	private JRExpression data;
	private boolean repeat = false;

	/**
	 * Create the action with id @JSONAction.PROP_DATA
	 * 
	 * @param part
	 * @param actionName
	 *          the textual description of the action
	 */
	public XLSPathDataAction(IWorkbenchPart part) {
		super(part, JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME, "", "XLS Metadata");
	}

	/**
	 * If an element is already a column than the property is removed from it, otherwise it will became a csw column with
	 * a column name defined by the user
	 */
	@Override
	public void run() {
		if (checkFrameParent()) {
			boolean dialogResult = MessageDialog.openQuestion(UIUtils.getShell(), Messages.JSONPathDataAction_1,
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
			return colDataMap.containsProperty(JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME);
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

		PropertyExpressionsDTO peDTO = (PropertyExpressionsDTO) n.getPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);

		if (path == null) {
			peDTO.removeProperty(JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME, false);
			peDTO.removeProperty(JRXlsAbstractMetadataExporter.PROPERTY_REPEAT_VALUE, false);

			removeDataPropertyExpression(peDTO);
		} else {
			peDTO.setProperty(JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME, path, false);
			if (repeat)
				peDTO.setProperty(JRXlsAbstractMetadataExporter.PROPERTY_REPEAT_VALUE, "true", false); //$NON-NLS-1$
			else
				peDTO.removeProperty(JRXlsAbstractMetadataExporter.PROPERTY_REPEAT_VALUE, false);
		}

		if (data == null)
			removeDataPropertyExpression(peDTO);
		else {
			PropertyExpressionDTO dpe = peDTO.getProperty(JRXlsAbstractMetadataExporter.PROPERTY_DATA, true);
			if (dpe == null){
				peDTO.addProperty(JRXlsAbstractMetadataExporter.PROPERTY_DATA, data.getText(), true);
			} else {
				dpe.setValue(data.getText());
			}
		}

		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(n);
		cmd.setDebugLabel(getText());
		cmd.setPropertyId(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
		cmd.setPropertyValue(peDTO);

		return cmd;
	}

	protected void removeDataPropertyExpression(PropertyExpressionsDTO peDTO) {
		peDTO.removeProperty(JRXlsAbstractMetadataExporter.PROPERTY_DATA, true);
	}
}
