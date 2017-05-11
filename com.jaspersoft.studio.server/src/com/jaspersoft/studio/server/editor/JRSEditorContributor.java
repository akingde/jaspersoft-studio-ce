/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.editor.IEditorContributor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.server.publish.wizard.SaveConfirmationDialog;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JSSFileRepositoryService;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.RepositoryService;

public class JRSEditorContributor implements IEditorContributor {

	private static final String IS_FROM_SAVE_AS = "isFromSaveAs";

	public void onInitContext(final JasperReportsConfiguration jConfig) {
		JSSFileRepositoryService repService = jConfig.getFileRepositoryService();
		if (repService != null) {
			List<RepositoryService> rservices = repService.getRepositoryServices();
			List<RepositoryService> toDel = new ArrayList<RepositoryService>();
			for (RepositoryService rs : rservices)
				if (rs instanceof JRSRepositoryService) {
					toDel.add(rs);
					FileRepositoryService frs = ((JRSRepositoryService) rs).getFileRepositoryService();
					if (frs != null)
						toDel.add(frs);
				}
			rservices.removeAll(toDel);
			rservices.add(new JRSRepositoryService(repService, jConfig));
		}
	}

	public void onLoad(final JasperDesign jd, final EditorPart editor) {
		if (!(editor instanceof AbstractJRXMLEditor))
			return;
		// String prop = jd.getProperty(AExporter.PROP_SERVERURL);
		// if (prop == null)
		// return;
		AbstractJRXMLEditor jEditor = (AbstractJRXMLEditor) editor;
		onInitContext(jEditor.getJrContext(null));
	}

	public static final String KEY_PUBLISH2JSS = "PUBLISH2JSS";
	public static final String KEY_PUBLISH2JSS_SILENT = "PUBLISH2JSS.SILENT";

	public void onSave(JasperReportsContext jrConfig, IProgressMonitor monitor) {
		String isSaveAs = jrConfig.getProperty(IS_FROM_SAVE_AS);
		if (isSaveAs != null) {
			jrConfig.removeProperty(IS_FROM_SAVE_AS);
			return;
		}
		JasperReportsConfiguration jConfig = (JasperReportsConfiguration) jrConfig;
		JasperDesign jd = jConfig.getJasperDesign();

		String[] prop = getServerURL(jd, (IFile) jrConfig.getValue(FileUtils.KEY_FILE), monitor);
		if (prop == null || prop[0] == null)
			return;
		Boolean doSave = jConfig.getPropertyBoolean(JRSPreferencesPage.PUBLISH_REPORT_TOJRSONSAVE, Boolean.TRUE);
		if (!doSave)
			return;

		MScopedPreferenceStore pStore = (MScopedPreferenceStore) jConfig.getPrefStore();
		pStore.setWithDefault(false);
		String sRun = Misc.nullIfEmpty(pStore.getString(KEY_PUBLISH2JSS));
		String sAllways = Misc.nullIfEmpty(pStore.getString(KEY_PUBLISH2JSS_SILENT));
		pStore.setWithDefault(true);

		boolean run = sRun == null ? true : Boolean.parseBoolean(sRun);
		boolean allways = sAllways == null ? true : Boolean.parseBoolean(sAllways);
		if (allways) {
			SaveConfirmationDialog dialog = new SaveConfirmationDialog(UIUtils.getShell());
			run = (dialog.open() == Dialog.OK);
			pStore.setValue(KEY_PUBLISH2JSS_SILENT, Boolean.toString(!dialog.getAllways()));
		}

		pStore.setValue(KEY_PUBLISH2JSS, Boolean.toString(run));

		// jConfig.put(KEY_PUBLISH2JSS, run);
		// jConfig.put(KEY_PUBLISH2JSS_SILENT, dialog.getAllways());

		if (run) {
			JrxmlPublishAction action = getAction(monitor, jConfig);
			action.setSilent(run);
			action.run();
		}
	}

	@Override
	public void onRename(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor) {
		JasperReportsConfiguration jConfig = (JasperReportsConfiguration) jrConfig;
		JasperDesign jd = jConfig.getJasperDesign();

		if (!replaceURL(AExporter.PROP_REPORTUNIT, jd, oldName, newName))
			replaceURL(AExporter.PROP_REPORTRESOURCE, jd, oldName, newName);
		else
			jd.removeProperty(AExporter.PROP_REPORTRESOURCE);
	}

	@Override
	public void onSaveAs(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor) {
		JasperReportsConfiguration jConfig = (JasperReportsConfiguration) jrConfig;
		JasperDesign jd = jConfig.getJasperDesign();
		jConfig.setProperty(IS_FROM_SAVE_AS, "true");
		if (!replaceURL(AExporter.PROP_REPORTUNIT, jd, oldName, newName))
			replaceURL(AExporter.PROP_REPORTRESOURCE, jd, oldName, newName);
		else
			jd.removeProperty(AExporter.PROP_REPORTRESOURCE);
	}

	private boolean replaceURL(String prop, JasperDesign jd, IFile oldName, IFile newName) {
		String url = jd.getProperty(prop);
		if (url == null)
			return false;
		String old = "/" + oldName.getName();
		String fext = oldName.getFileExtension();
		if (!Misc.isNullOrEmpty(fext))
			old = old.substring(0, oldName.getName().length() - fext.length());
		if (url.endsWith(old)) {
			String n = "/" + newName.getName();
			fext = newName.getFileExtension();
			if (!Misc.isNullOrEmpty(fext))
				n = n.substring(0, newName.getName().length() - fext.length());
			jd.setProperty(prop, url.substring(0, url.length() - old.length()) + n);
			return true;
		}
		return false;
	}

	public static String[] getServerURL(JasperDesign jd, IFile f, IProgressMonitor monitor) {
		if (jd == null)
			return null;
		String[] urls = new String[2];
		urls[0] = jd.getProperty(AExporter.PROP_SERVERURL);
		urls[1] = jd.getProperty(AExporter.PROP_USER);
		if (f != null) {
			try {
				if (Misc.isNullOrEmpty(urls[0]))
					urls[0] = f.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, AExporter.PROP_SERVERURL));
				if (Misc.isNullOrEmpty(urls[1])) {
					List<String[]> paths = PublishUtil.loadPath(monitor, f);
					for (String[] p : paths) {
						if (p[0].startsWith("JRSUSER."))
							urls[1] = p[1];
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return urls;
	}

	protected static JrxmlPublishAction getAction(IProgressMonitor monitor, JasperReportsConfiguration jrConfig) {
		JrxmlPublishAction publishAction = new JrxmlPublishAction(1, monitor);
		publishAction.setJrConfig(jrConfig);
		return publishAction;
	}

	public void onRun(JasperReportsConfiguration jrConfig, JasperReport jr, Map<String, Object> params) {
		// for (JRParameter p : jr.getParameters()) {
		// look if there are JRS built-in parameters, set server value, for
		// this
		// connection
		// cache all of this, preference to do this ?
		// }
	}

	public AContributorAction[] getActions() {
		return new AContributorAction[] { new JrxmlPublishAction() };
	}

	@Override
	public String getTitleToolTip(JasperReportsContext jrConfig, String toolTip) {
		String s = toolTip;
		JasperDesign jd = ((JasperReportsConfiguration) jrConfig).getJasperDesign();
		if (jd != null) {
			String p = jd.getProperty(AExporter.PROP_SERVERURL);
			if (p != null)
				s += "\nServer: " + p;
			p = jd.getProperty(AExporter.PROP_REPORTUNIT);
			if (p != null)
				s += "\nReport Unit: " + p;
			p = jd.getProperty(AExporter.PROP_REPORTRESOURCE);
			if (p != null)
				s += "\nResource name: " + p;
		}
		return s;
	}

}
