/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.json;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.export.JsonMetadataReportConfiguration;

/**
 * 
 * @author Veaceslav Chicu
 * 
 */
public class JSONEscapeMembersAction extends JSONAction {

	public JSONEscapeMembersAction(IWorkbenchPart part) {
		super(part, JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS, Messages.JSONEscapeMembersAction_0);
	}

	@Override
	public void run() {
		execute(createCommand());
		setChecked(ischecked);
	}

	private MReport getSelectedElement() {
		List<Object> textElements = editor.getSelectionCache().getSelectionModelForType(MReport.class);
		if (textElements.isEmpty() || textElements.size() != getSelectedObjects().size())
			return null;
		return (MReport) textElements.get(0);
	}

	@Override
	public boolean isChecked() {
		MReport model = getSelectedElement();
		if (model == null)
			return false;
		boolean escape = JRPropertiesUtil.getInstance(model.getJasperConfiguration()).getBooleanProperty(
				JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS, true);
		JRPropertiesMap colDataMap = (JRPropertiesMap) model.getValue().getPropertiesMap();
		String esc = colDataMap.getProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS);
		if (esc != null)
			escape = Misc.nvl(Boolean.valueOf(esc), escape);
		return escape;
	}

	@Override
	protected boolean calculateEnabled() {
		return !editor.getSelectionCache().getSelectionModelForType(MReport.class).isEmpty();
	}

	/**
	 * Create the commands necessary to transform a textual element into a JSON column or to remove it is it is already a
	 * JSON column
	 * 
	 */
	@Override
	protected Command createCommand() {
		MReport n = getSelectedElement();
		if (n == null)
			return null;

		JRPropertiesMap map = (JRPropertiesMap) n.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		if (map == null)
			map = new JRPropertiesMap();

		if (map.containsProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS))
			map.removeProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS);
		else
			map.setProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS, "true"); //$NON-NLS-1$

		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(n);
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		cmd.setPropertyValue(map);
		cmd.setDebugLabel(getText());

		return cmd;
	}

}
