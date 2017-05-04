/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.utils.RDUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

public class PublishUtil {
	public static final String KEY_PUBLISH2JSS_DATA = "PUBLISH2JSS_DATA"; //$NON-NLS-1$

	public static List<AMResource> getResources(AMResource parent, IProgressMonitor monitor,
			JasperReportsConfiguration jrConfig) {
		List<AMResource> resources = (List<AMResource>) jrConfig.get(KEY_PUBLISH2JSS_DATA);
		if (resources == null)
			jrConfig.put(KEY_PUBLISH2JSS_DATA, new ArrayList<AMResource>());
		loadPreferences(parent, monitor, (IFile) jrConfig.get(FileUtils.KEY_FILE), resources);
		return resources;
	}

	public static ResourceDescriptor getMainReport(IProgressMonitor monitor, MReportUnit mrunit, JasperDesign jd) {
		String jrxmln = jd.getProperty(AExporter.PROP_REPORTRESOURCE);
		String unit = mrunit.getValue().getUriString();
		if (jrxmln != null) {
			if (unit != null && jrxmln.startsWith(unit) && jrxmln.length() > unit.length()
					&& jrxmln.substring((unit + WSClientHelper._FILES).length()).indexOf('/') < 0) {
				MServerProfile sp = (MServerProfile) mrunit.getRoot();
				if (sp != null) {
					ResourceDescriptor rd = new ResourceDescriptor();
					rd.setName(jrxmln.substring((unit + WSClientHelper._FILES).length()));
					rd.setLabel(IDStringValidator.safeChar(rd.getName()));
					String d = jd.getProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
					if (!Misc.isNullOrEmpty(d))
						rd.setDescription(d);
					rd.setUriString(jrxmln);
					rd.setParentFolder(unit + "_files");
					rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
					rd.setIsNew(true);
					rd.setWsType(ResourceDescriptor.TYPE_JRXML);
					rd.setIsReference(false);
					rd.setHasData(true);
					try {
						rd = sp.getWsClient(monitor).get(monitor, rd, null);
						rd.setHasData(true);
						if (rd != null)
							return rd;
					} catch (Exception e) {
						rd.setMainReport(true);
						return rd;
					}
				}
			}
		}
		ResourceDescriptor mainr = new ResourceDescriptor();
		mainr.setName(Messages.JrxmlPublishAction_defaultresourcename);
		mainr.setLabel(Messages.JrxmlPublishAction_defaultresourcelabel);
		String d = jd.getProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
		if (!Misc.isNullOrEmpty(d))
			mainr.setDescription(d);
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		mainr.setParentFolder(unit + "_files");
		mainr.setUriString(mainr.getParentFolder() + "/" + mainr.getName());
		return mainr;
	}

	public static void initRUnitName(AMJrxmlContainer runit, JasperDesign jd, JasperReportsConfiguration jConf) {
		if (runit == null || jd == null)
			return;
		String name = getRUnitNAme(jd, jConf);
		initResourceName(name, runit.getValue());
		if (Misc.isNullOrEmpty(runit.getValue().getDescription())) {
			String d = jd.getProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
			if (!Misc.isNullOrEmpty(d))
				runit.getValue().setDescription(d);
			if (runit instanceof MReportUnit) {
				d = jd.getProperty(AExporter.COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION);
				if (!Misc.isNullOrEmpty(d))
					runit.getValue().setDescription(d);
			}
		}
	}

	public static String getRUnitNAme(JasperDesign jd, JasperReportsConfiguration jConf) {
		String name = jd.getName();
		IFile f = (IFile) jConf.get(FileUtils.KEY_FILE);
		if (f != null) {
			name = f.getName();
			if (!Misc.isNullOrEmpty(f.getFileExtension()))
				name = name.substring(0, name.length() - f.getFileExtension().length() - 1);
		}
		return name;
	}

	public static void setChild(ResourceDescriptor rd, ResourceDescriptor child) {
		List<ResourceDescriptor> children = rd.getChildren();
		for (int i = 0; i < children.size(); i++) {
			ResourceDescriptor r = children.get(i);
			if (r.isMainReport() && child.isMainReport()) {
				child.setName(r.getName());
				child.setLabel(r.getLabel());
				child.setDescription(r.getDescription());
				child.setUriString(r.getUriString());
				children.set(i, child);
				return;
			}
			if ((child.getUriString() == null && r.getUriString() == null && child.getWsType().equals(r.getWsType()))
					|| (r.getUriString() != null && r.getUriString().equals(child.getUriString()))) {
				if (r.isMainReport())
					child.setMainReport(true);
				children.set(i, child);
				return;
			}
		}
		children.add(child);
	}

	public static void initResourceName(String name, ResourceDescriptor rd) {
		if (Misc.isNullOrEmpty(rd.getName()))
			rd.setName(IDStringValidator.safeChar(name));
		if (Misc.isNullOrEmpty(rd.getLabel()))
			rd.setLabel(name);
	}

	public static void savePreferences(IFile ifile, List<AMResource> files) throws CoreException {
		Map<QualifiedName, String> pmap = ifile.getPersistentProperties();
		for (QualifiedName key : pmap.keySet()) {
			if (key.equals(JrxmlExporter.KEY_REPORT_ISMAIN))
				continue;
			ifile.setPersistentProperty(key, null);
		}
		for (AMResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			String prefix = f.getValue().getName();

			OverwriteEnum ovw = popt.getOverwrite();
			if (ovw == null)
				ovw = OverwriteEnum.IGNORE;
			else if (ovw.equals(OverwriteEnum.IGNORE))
				;// ovw = OverwriteEnum.OVERWRITE;
			else if (ovw.equals(OverwriteEnum.OVERWRITE))
				ovw = OverwriteEnum.IGNORE;
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"), ovw.getValue());

			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".reference"),
					popt.getPublishMethod().toString());
			if (popt.getReferencedResource() != null)
				ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"),
						popt.getReferencedResource().getUriString());
			else
				ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"), null);
		}
	}

	public static void savePreferencesNoOverwrite(IFile ifile, AMResource f) throws CoreException {
		if (f instanceof MInputControl) {
			String prefix = f.getValue().getName();
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"),
					Boolean.toString(false));
		}
	}

	public static void loadPreferences(AMResource parent, IProgressMonitor monitor, IFile ifile,
			List<AMResource> files) {
		if (parent == null || parent.getValue() == null || parent.getValue().getIsNew())
			return;
		for (AMResource f : files)
			loadPreferences(monitor, ifile, f);
	}

	public static boolean loadPreferences(IProgressMonitor monitor, IFile ifile, AMResource f) {
		boolean exists = false;
		PublishOptions popt = f.getPublishOptions();
		String prefix = f.getValue().getName();
		try {
			String ovw = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"));
			if (ovw != null) {
				exists = true;
				popt.setOverwrite(OverwriteEnum.getByValue(ovw));
			}
			String ref = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".reference"));
			if (ref != null) {
				exists = true;
				popt.setPublishMethod(ResourcePublishMethod.valueOf(ref));
				String path = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"));
				if (path != null) {
					ResourceDescriptor rd = new ResourceDescriptor();
					rd.setParentFolder(RDUtil.getParentFolder(path));
					rd.setUriString(path);
					rd.setWsType(f.getValue().getWsType());
					popt.setReferencedResource(
							WSClientHelper.getResource(monitor, f, rd, FileUtils.createTempFile("tmp", "")));
				} else
					popt.setPublishMethod(ResourcePublishMethod.LOCAL);
			}
		} catch (Exception e) {
			popt.setPublishMethod(ResourcePublishMethod.LOCAL);
			e.printStackTrace();
		}
		return exists;
	}

	public static List<String[]> loadPath(IProgressMonitor monitor, IFile ifile) throws CoreException {
		List<String[]> paths = new ArrayList<String[]>();
		Map<QualifiedName, String> pmap = ifile.getPersistentProperties();
		int substr = "JRSPATH.".length();
		for (QualifiedName key : pmap.keySet()) {
			String lname = key.getLocalName();
			if (key.getQualifier().equals(Activator.PLUGIN_ID) && lname.startsWith("JRSPATH."))
				paths.add(new String[] { lname.substring(substr), pmap.get(key) });
			if (key.getQualifier().equals(Activator.PLUGIN_ID) && lname.startsWith("JRSUSER."))
				paths.add(new String[] { lname, pmap.get(key) });
		}
		return paths;
	}

	public static void savePath(IFile ifile, AMResource mres) throws CoreException {
		ServerProfile sp = mres.getWsClient().getServerProfile();
		String jrs;
		try {
			jrs = sp.getUrl();
			String user = sp.getUser();
			if (!Misc.isNullOrEmpty(sp.getOrganisation()))
				user += "|" + sp.getOrganisation();
			String uri = mres.getValue().getUriString();
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, "JRSPATH." + jrs), uri);
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, "JRSUSER." + user), user);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
}
