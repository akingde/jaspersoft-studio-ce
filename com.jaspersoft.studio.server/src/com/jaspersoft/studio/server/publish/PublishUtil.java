package com.jaspersoft.studio.server.publish;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PublishUtil {
	public static final String KEY_PUBLISH2JSS_DATA = "PUBLISH2JSS_DATA"; //$NON-NLS-1$

	public static List<MResource> getResources(JasperReportsConfiguration jrConfig) {
		List<MResource> resources = jrConfig.get(KEY_PUBLISH2JSS_DATA, new ArrayList<MResource>());
		jrConfig.put(KEY_PUBLISH2JSS_DATA, resources);
		return resources;
	}

	public static ResourceDescriptor getMainReport(MReportUnit mrunit, JasperDesign jd) {
		String jrxmln = jd.getProperty(JrxmlExporter.PROP_REPORTRESOURCE);
		if (jrxmln != null) {
			String unit = mrunit.getValue().getUriString() + "_files/"; //$NON-NLS-1$
			if (unit != null && jrxmln.startsWith(unit) && jrxmln.length() > unit.length() && jrxmln.substring(unit.length()).indexOf('/') < 0) {
				MServerProfile sp = (MServerProfile) mrunit.getRoot();
				if (sp != null) {
					ResourceDescriptor rd = new ResourceDescriptor();
					rd.setName(jrxmln.substring(unit.length()));
					rd.setLabel(jrxmln.substring(unit.length()));
					rd.setUriString(jrxmln);
					rd.setParentFolder(unit + "_files/" + rd.getName()); //$NON-NLS-1$
					rd.setIsNew(true);
					rd.setWsType(ResourceDescriptor.TYPE_JRXML);
					rd.setIsReference(false);
					rd.setHasData(true);
					try {
						rd = sp.getWsClient().get(rd, null);
						rd.setHasData(true);
						if (rd != null)
							return rd;
					} catch (Exception e) {
						e.printStackTrace();// maybe ask something?
						return rd;
					}
				}
			}
		}
		ResourceDescriptor mainr = new ResourceDescriptor();
		mainr.setName(Messages.JrxmlPublishAction_defaultresourcename);
		mainr.setLabel(Messages.JrxmlPublishAction_defaultresourcelabel);
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		return mainr;
	}

	public static void initRUnitName(MReportUnit runit, JasperDesign jd) {
		if (runit == null || jd == null)
			return;
		ResourceDescriptor rd = runit.getValue();
		initRunitName(jd, rd);
	}

	public static void initRunitName(JasperDesign jd, ResourceDescriptor rd) {
		if (Misc.isNullOrEmpty(rd.getName()))
			rd.setName(jd.getName().replace(" ", ""));
		if (Misc.isNullOrEmpty(rd.getLabel()))
			rd.setLabel(jd.getName());
	}
}
