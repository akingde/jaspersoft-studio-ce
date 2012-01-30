package com.jaspersoft.studio.server.publish;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.jasperreports.engine.JRParameter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;

public class ImpInputControls {
	public static void publish(MReportUnit mrunit, IProgressMonitor monitor,
			JRParameter param) throws Exception {
		if (param.isSystemDefined() || !param.isForPrompting())
			return;
		ResourceDescriptor runit = mrunit.getValue();

		ResourceDescriptor rd = MInputControl.createDescriptor(mrunit);
		rd.setName(param.getName());
		rd.setLabel(param.getName());
		rd.setDescription(param.getDescription());
		rd.setVisible(true);
		rd.setReadOnly(false);
		rd.setMandatory(false);
		rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE,
				ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
		rd.setParentFolder(runit.getUriString() + "_files");
		rd.setUriString(runit.getUriString() + "_files/" + rd.getName());

		MInputControl mres = (MInputControl) ResourceFactory.getResource(
				mrunit, rd, -1);

		if (Boolean.class.isAssignableFrom(param.getValueClass())) {
			rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
		} else if (String.class.isAssignableFrom(param.getValueClass())) {
			addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
		} else if (Timestamp.class.isAssignableFrom(param.getValueClass())) {
			addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
		} else if (Date.class.isAssignableFrom(param.getValueClass())) {
			addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
		} else if (Number.class.isAssignableFrom(param.getValueClass())) {
			addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
		} else
			return;
		try {
			WSClientHelper.saveResource(mres, monitor, false);
		} catch (Exception e) {
			mres.getValue().setIsNew(false);
			WSClientHelper.saveResource(mres, monitor, false);
		}
	}

	protected static void addType(ResourceDescriptor rd, MInputControl mres,
			byte type) {
		ResourceDescriptor rdtype = MDataType.createDescriptor(mres);
		String name = "myDatatype";
		rdtype.setName(name);
		rdtype.setLabel(name);
		rdtype.setIsNew(true);
		rdtype.setDataType(type);
		rdtype.setUriString(String.format("%s/%s/%s", rd.getParentFolder(),
				rd.getName(), name));

		rd.getChildren().add(rdtype);
	}
}
