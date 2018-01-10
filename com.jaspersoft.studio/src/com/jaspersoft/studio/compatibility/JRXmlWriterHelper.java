/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.compatibility;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateFormatUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.JRVersionPreferencesPages;
import com.jaspersoft.studio.compatibility.dialog.VersionDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.xml.JRXmlBaseWriter;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

/*
 * 
 * @author gtoffoli
 */
public class JRXmlWriterHelper {

	private static final Set<String> writers = new HashSet<String>();

	static {
		for (Field f : JRConstants.class.getFields()) {
			if (f.getName().startsWith(Messages.JRXmlWriterHelper_0))
				try {
					writers.add((String) f.get(null));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		writers.add(VersionConstants.VERSION_5_1_0);
		writers.add(VersionConstants.VERSION_5_2_0);
		writers.add(VersionConstants.VERSION_5_5_1);
	}

	public static String[][] getVersions() {
		List<String> sl = new ArrayList<String>(writers);
		Collections.sort(sl);
		Collections.reverse(sl);
		String[][] r = new String[sl.size() + 1][2];
		r[0] = new String[] { Messages.JRXmlWriterHelper_1, "last" }; //$NON-NLS-2$
		int i = 1;
		for (String key : sl) {
			r[i][0] = "JasperReports " + key.replace('_', '.'); //$NON-NLS-1$
			if (i == 1)
				r[i][0] += Messages.JRXmlWriterHelper_4;
			r[i][1] = key;
			i++;
		}
		return r;
	}

	public static Set<String> getVersionsSet() {
		return writers;
	}

	public static String writeReport(JasperReportsConfiguration jrContext, JRReport report, IFile file, boolean showDialog)
			throws Exception {

		return writeReport(jrContext, report, fixencoding(FileUtils.UTF8_ENCODING), getVersion(file, jrContext, showDialog));
	}

	public static String writeReport(JasperReportsContext jrContext, JRReport report, String version) throws Exception {
		return writeReport(jrContext, report, fixencoding(FileUtils.UTF8_ENCODING), version); //$NON-NLS-1$
	}

	public static String writeReport(JasperReportsContext jrContext, JRReport report, String encoding, String version)
			throws Exception {
		if (report == null)
			return null;
		encoding = fixencoding(encoding);
		if (!writers.contains(version))
			version = LAST_VERSION;
		boolean disposeContext = false;
		if (jrContext == null) {
			jrContext = JasperReportsConfiguration.getDefaultJRConfig();
			disposeContext = true;
		}
		jrContext.removeProperty(JRXmlBaseWriter.PROPERTY_REPORT_VERSION);
		if (writers.contains(version))
			jrContext.setProperty(JRXmlBaseWriter.PROPERTY_REPORT_VERSION, version);
		String xml = null;
		try {
			// jrContext.setProperty("net.sf.jasperreports.components.table.version", version);
			xml = new JRXmlWriter(jrContext).write(report, encoding);
			// request Bug 37455 - [Case #48613] Simple jrxml timestamp on Save or Save As
			// + community bug #3936 over-aggressive time stamp
			String timestamp = ""; //$NON-NLS-1$
			if (jrContext instanceof JasperReportsConfiguration) {
				if (((JasperReportsConfiguration) jrContext)
						.getPropertyBoolean(JRVersionPreferencesPages.JSS_TIMESTAMP_ONSAVE, false)) {
					timestamp = "<!-- " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()) + " -->\n"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			// Replace with a more meaningful JR version
			if (version.equals(LAST_VERSION)) {
				version = getInstalledJasperReportsVersion();
			}
			// Get JSS bundle version
			String jssPluginVersion = JaspersoftStudioPlugin.getInstance().getBundle().getVersion().toString();
			String jrVersionTxt = Messages.JRXmlWriterHelper_9 + version + " "; //$NON-NLS-2$
			xml = xml
					.replaceFirst(
							"<jasperReport ", "<!-- Created with Jaspersoft Studio version " + jssPluginVersion + jrVersionTxt + " -->\n" + timestamp + "<jasperReport "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} finally {
			if (disposeContext)
				((JasperReportsConfiguration) jrContext).dispose();
		}
		return xml;
	}

	public static String fixencoding(String encoding) {
		return FileUtils.UTF8_ENCODING; 
		// String tmp = EncodingMap.getJava2IANAMapping(encoding);
		// if (tmp != null)
		// return tmp;
		// tmp = EncodingMap.getJava2IANAMapping(encoding.toUpperCase());
		// if (tmp != null)
		// return tmp;
		// tmp = EncodingMap.getJava2IANAMapping(encoding.toLowerCase());
		// if (tmp != null)
		// return tmp;
		// return encoding;
	}

	public static String getVersion(IResource resource, JasperReportsConfiguration jContext, boolean showDialog) {
		String version = jContext.getProperty(JRVersionPreferencesPages.JSS_COMPATIBILITY_VERSION, LAST_VERSION);
		if (showDialog && jContext.getPropertyBoolean(JRVersionPreferencesPages.JSS_COMPATIBILITY_SHOW_DIALOG, false)) {
			VersionDialog dialog = new VersionDialog(Display.getDefault().getActiveShell(), version, resource);
			if (dialog.open() == Dialog.OK) {
				version = dialog.getVersion();
				try {
					ScopedPreferenceStore pstore = JaspersoftStudioPlugin.getInstance().getPreferenceStore(resource,JaspersoftStudioPlugin.getUniqueIdentifier());
					pstore.setValue(JRVersionPreferencesPages.JSS_COMPATIBILITY_VERSION, version);
					
					if (dialog.isHideNext()){
						//need to use put value, with setValue since false it is the default will remove the value from
						//the project store and this will cause the get to look on upper level. Instead the put aways set
						//the value inside the store
						pstore.putValue(JRVersionPreferencesPages.JSS_COMPATIBILITY_SHOW_DIALOG, Boolean.FALSE.toString());
					}
					pstore.save();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return version;
	}

	public static final String LAST_VERSION = "last"; //$NON-NLS-1$

	public static String getInstalledJasperReportsVersion() {
		try {
			return net.sf.jasperreports.engine.JasperCompileManager.class.getPackage().getImplementationVersion();
		} catch (Throwable nex) {
			return LAST_VERSION;
		}
	}

	/**
	 * Checks if the compatible version specified in the Jaspersoft Studio preference page is greater, or even equal, than
	 * the one passed as parameter.
	 * 
	 * @param jconfig
	 *          the JasperReports context
	 * @param compareVersion
	 *          the version to compare with
	 * @param equalToo
	 *          flag that specifies if also equal version is accepted
	 * @return <code>true</code> if compatible version is greater (or equal), <code>false</code> otherwise
	 */
	public static boolean isCompatibleVersionGreater(JasperReportsConfiguration jconfig, String compareVersion,
			boolean equalToo) {
		Assert.isNotNull(jconfig);
		Assert.isNotNull(compareVersion);
		boolean verified = false;
		if (equalToo) {
			verified = getCompatibleVersion(jconfig).compareTo(compareVersion) >= 0;
		} else {
			verified = getCompatibleVersion(jconfig).compareTo(compareVersion) > 0;
		}
		return verified;
	}

	/**
	 * Checks if the compatible version specified in the Jaspersoft Studio preference page is minor, or even equal, than
	 * the one passed as parameter.
	 * 
	 * @param jconfig
	 *          the JasperReports context
	 * @param compareVersion
	 *          the version to compare with
	 * @param equalToo
	 *          flag that specifies if also equal version is accepted
	 * @return <code>true</code> if compatible version is minor (or equal), <code>false</code> otherwise
	 */
	public static boolean isCompatibleVersionMinor(JasperReportsConfiguration jconfig, String compareVersion,
			boolean equalToo) {
		Assert.isNotNull(jconfig);
		Assert.isNotNull(compareVersion);
		boolean verified = false;
		if (equalToo) {
			verified = getCompatibleVersion(jconfig).compareTo(compareVersion) <= 0;
		} else {
			verified = getCompatibleVersion(jconfig).compareTo(compareVersion) < 0;
		}
		return verified;
	}

	/**
	 * Checks if the compatible version specified in the Jaspersoft Studio preference page is equal to the one passed as
	 * parameter.
	 * 
	 * @param jconfig
	 *          the JasperReports context
	 * @param compareVersion
	 *          the version to compare with
	 * @param equalToo
	 *          flag that specifies if also equal version is accepted
	 * @return <code>true</code> if compatible version is equal, <code>false</code> otherwise
	 */
	public static boolean isCompatibleVersionEqual(JasperReportsConfiguration jconfig, String compareVersion) {
		Assert.isNotNull(jconfig);
		Assert.isNotNull(compareVersion);
		return getCompatibleVersion(jconfig).compareTo(compareVersion) == 0;
	}

	/**
	 * Reads the information about the compatible version of JasperReports to be used.
	 * 
	 * @param jconfig
	 *          the JasperReports context
	 * @return the compatible version
	 */
	private static String getCompatibleVersion(JasperReportsConfiguration jconfig) {
		// assume last version as safe fall-back
		String ver = Misc.nvl(jconfig.getProperty(JRVersionPreferencesPages.JSS_COMPATIBILITY_VERSION), LAST_VERSION);
		if (LAST_VERSION.equals(ver)) {
			return net.sf.jasperreports.engine.JasperCompileManager.class.getPackage().getImplementationVersion();
		} else {
			return ver;
		}
	}
}
