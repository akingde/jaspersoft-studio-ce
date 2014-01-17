package com.jaspersoft.studio.server.publish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.wizard.resource.page.ResourcePageContent;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PublishUtil {
	public static final String KEY_PUBLISH2JSS_DATA = "PUBLISH2JSS_DATA"; //$NON-NLS-1$

	public static List<MResource> getResources(IProgressMonitor monitor, JasperReportsConfiguration jrConfig) {
		List<MResource> resources = jrConfig.get(KEY_PUBLISH2JSS_DATA, new ArrayList<MResource>());
		jrConfig.put(KEY_PUBLISH2JSS_DATA, resources);
		loadPreferences(monitor, (IFile) jrConfig.get(FileUtils.KEY_FILE), resources);
		return resources;
	}

	public static ResourceDescriptor getMainReport(IProgressMonitor monitor, MReportUnit mrunit, JasperDesign jd) {
		String jrxmln = jd.getProperty(AExporter.PROP_REPORTRESOURCE);
		String unit = mrunit.getValue().getUriString();
		if (jrxmln != null) {
			if (unit != null && jrxmln.startsWith(unit) && jrxmln.length() > unit.length() && jrxmln.substring((unit + WSClientHelper._FILES).length()).indexOf('/') < 0) {
				MServerProfile sp = (MServerProfile) mrunit.getRoot();
				if (sp != null) {
					ResourceDescriptor rd = new ResourceDescriptor();
					rd.setName(jrxmln.substring((unit + WSClientHelper._FILES).length()));
					rd.setLabel(ResourcePageContent.safeChar(rd.getName()));
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
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		mainr.setParentFolder(unit + "_files");
		mainr.setUriString(mainr.getParentFolder() + "/" + mainr.getName());
		return mainr;
	}

	public static void initRUnitName(AMJrxmlContainer runit, JasperDesign jd) {
		if (runit == null || jd == null)
			return;
		initResourceName(jd.getName(), runit.getValue());
	}

	public static void initResourceName(String name, ResourceDescriptor rd) {
		if (Misc.isNullOrEmpty(rd.getName()))
			rd.setName(ResourcePageContent.safeChar(name));
		if (Misc.isNullOrEmpty(rd.getLabel()))
			rd.setLabel(name);
	}

	public static void savePreferences(IFile ifile, List<MResource> files) throws CoreException {
		Map<QualifiedName, String> pmap = ifile.getPersistentProperties();
		for (QualifiedName key : pmap.keySet()) {
			if (key.equals(JrxmlExporter.KEY_REPORT_ISMAIN))
				continue;
			ifile.setPersistentProperty(key, null);
		}
		for (MResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			String prefix = f.getValue().getName();
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"), Boolean.toString(popt.isOverwrite()));

			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".reference"), popt.getPublishMethod().toString());
			if (popt.getReferencedResource() != null)
				ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"), popt.getReferencedResource().getUriString());
			else
				ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"), null);
		}
	}

	public static void savePreferencesNoOverwrite(IFile ifile, MResource f) throws CoreException {
		if (f instanceof MInputControl) {
			String prefix = f.getValue().getName();
			ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"), Boolean.toString(false));
		}
	}

	public static void loadPreferences(IProgressMonitor monitor, IFile ifile, List<MResource> files) {
		for (MResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			String prefix = f.getValue().getName();
			try {
				String ovw = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".overwrite"));
				if (ovw != null)
					popt.setOverwrite(Boolean.parseBoolean(ovw));
				String ref = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".reference"));
				if (ref != null) {
					popt.setPublishMethod(ResourcePublishMethod.valueOf(ref));
					String path = ifile.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, prefix + ".refPATH"));
					if (path != null) {
						ResourceDescriptor rd = new ResourceDescriptor();
						rd.setUriString(path);
						rd.setWsType(f.getValue().getWsType());
						popt.setReferencedResource(WSClientHelper.getResource(monitor, f, rd, FileUtils.createTempFile("tmp", "")));
					} else
						popt.setPublishMethod(ResourcePublishMethod.LOCAL);
				}
			} catch (Exception e) {
				popt.setPublishMethod(ResourcePublishMethod.LOCAL);
				e.printStackTrace();
			}
		}
	}

	public static List<String[]> loadPath(IProgressMonitor monitor, IFile ifile) throws CoreException {
		List<String[]> paths = new ArrayList<String[]>();
		Map<QualifiedName, String> pmap = ifile.getPersistentProperties();
		int substr = "JRSPATH.".length();
		for (QualifiedName key : pmap.keySet()) {
			if (key.getQualifier().equals(Activator.PLUGIN_ID) && key.getLocalName().startsWith("JRSPATH."))
				paths.add(new String[] { key.getLocalName().substring(substr), pmap.get(key) });
		}
		return paths;
	}

	public static void savePath(IFile ifile, MResource mres) throws CoreException {
		String jrs = mres.getWsClient().getServerProfile().getUrl();
		String uri = mres.getValue().getUriString();
		ifile.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, "JRSPATH." + jrs), uri);
	}
}
