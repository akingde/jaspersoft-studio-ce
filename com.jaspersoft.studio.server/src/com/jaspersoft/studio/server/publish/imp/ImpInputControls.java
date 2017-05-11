/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ic.ICParameterContributor;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.OverwriteEnum;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.types.date.DateRange;
import net.sf.jasperreports.types.date.TimestampRange;

public class ImpInputControls {
	protected JasperReportsConfiguration jrConfig;

	public ImpInputControls(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	public void publish(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			JasperReportsConfiguration jrConfig) throws Exception {
		ResourceDescriptor runit = mrunit.getValue();
		for (JRParameter p : jasper.getParametersList()) {
			if (p.isSystemDefined() || !p.isForPrompting())
				continue;
			ResourceDescriptor rd = MInputControl.createDescriptor(mrunit);
			rd.setName(IDStringValidator.safeChar(Misc.nvl(p.getName())));
			rd.setLabel(p.getName());
			rd.setDescription(p.getDescription());
			rd.setVisible(true);
			rd.setReadOnly(false);
			rd.setMandatory(false);
			rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE, ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
			rd.setParentFolder(runit.getUriString() + "_files");
			rd.setUriString(runit.getUriString() + "_files/" + rd.getName());

			MInputControl mres = (MInputControl) ResourceFactory.getResource(mrunit, rd, -1);

			if (Boolean.class.isAssignableFrom(p.getValueClass())) {
				rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
			} else if (String.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
			} else if (Time.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, (byte) 5);
			} else if (Timestamp.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
			} else if (Date.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
			} else if (TimestampRange.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
			} else if (DateRange.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
			} else if (Number.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
			} else if (Collection.class.isAssignableFrom(p.getValueClass())) {
				rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES);

				ResourceDescriptor dt = new ResourceDescriptor();
				dt.setWsType(ResourceDescriptor.TYPE_LOV);
				dt.setName("lov_" + rd.getName());
				dt.setLabel("lov_" + rd.getName());
				dt.setIsNew(true);
				dt.setParentFolder(rd.getUriString() + "_files");
				dt.setUriString(dt.getParentFolder() + "/" + rd.getName());
				List<ListItem> values = new ArrayList<ListItem>();

				dt.setListOfValues(values);
				rd.getChildren().add(dt);
			} else {
				mrunit.removeChild(mres);
				continue;
			}

			PublishOptions popt = AImpObject.createOptions(jrConfig, null);
			String b = jrConfig.getProperty(JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true");
			if (b.equals("true") && rd.getIsNew())
				popt.setOverwrite(OverwriteEnum.OVERWRITE);
			mres.setPublishOptions(popt);
			PublishUtil.loadPreferences(monitor, (IFile) jrConfig.get(FileUtils.KEY_FILE), mres);
			String icpath = p.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_PATH);
			if (!Misc.isNullOrEmpty(icpath)) {
				rd.setUriString(icpath);
				popt.setPublishMethod(ResourcePublishMethod.REFERENCE);
				popt.setReferencedResource(rd);// WSClientHelper.getResource(monitor,
												// mrunit.getWsClient(), rd,
												// null));
				popt.setOverwrite(OverwriteEnum.IGNORE);
			}

			PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
		}
	}

	public static ResourceDescriptor addType(ResourceDescriptor rd, MInputControl mres, byte type) {
		ResourceDescriptor rdtype = MDataType.createDescriptor(mres);
		String name = "myDatatype";
		rdtype.setName(name);
		rdtype.setLabel(name);
		rdtype.setIsNew(true);
		rdtype.setDataType(type);
		rdtype.setIsReference(false);
		rdtype.setParentFolder(rd.getUriString() + "_files");
		rdtype.setUriString(rdtype.getParentFolder() + "/" + name);

		rd.getChildren().add(rdtype);
		return rdtype;
	}
}
