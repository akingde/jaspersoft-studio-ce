package com.jaspersoft.studio.community.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.sf.jasperreports.eclipse.util.BundleCommonUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.internal.core.EnvironmentUtils;
import org.eclipse.wb.internal.core.utils.IOUtils2;
import org.eclipse.wb.internal.core.utils.platform.PlatformInfo;
import org.eclipse.wb.internal.core.utils.platform.PluginUtilities;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.community.zip.ZipEntry;
import com.jaspersoft.studio.utils.BrandingInfo;
import com.jaspersoft.studio.utils.UIUtils;


/**
 * Generic utility methods for this plug-in.
 * 
 * <b>NOTE</b>: some methods and apis in this class have been adapted from existing code in the
 * <code>org.eclipse.wb.core</code> project belonging to <i>WindowsBuilder</i> plugin.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CommunityAPIUtils {
	// Constants
	private static final String CR = "\n";
	private static final String JSS_PREFS_RELATIVE_LOCATION = 
			".metadata/.plugins/org.eclipse.core.runtime/.settings/com.jaspersoft.studio.prefs";
	private static final String JSS_LOG_RELATIVE_LOCATION = ".metadata/.log";
	
	/**
	 * Creates a ZIP file using the specified zip entries.
	 * 
	 * @param zipEntries the list of entries that will end up in the final zip file
	 * @return the zip file reference
	 * @throws CommunityAPIException
	 */
	public static File createZipFile(List<ZipEntry> zipEntries) throws CommunityAPIException {
		String tmpDirectory = System.getProperty("java.io.tmpdir");
		String zipFileLocation = tmpDirectory;
		if(!(tmpDirectory.endsWith("/") || tmpDirectory.endsWith("\\"))){
			zipFileLocation += System.getProperty("file.separator");
		}
		zipFileLocation += "issueDetails.zip";
		
		try {
			// create byte buffer
			byte[] buffer = new byte[1024];
			// create object of FileOutputStream
			FileOutputStream fout = new FileOutputStream(zipFileLocation);
			// create object of ZipOutputStream from FileOutputStream
			ZipOutputStream zout = new ZipOutputStream(fout);
			
			for(ZipEntry ze : zipEntries){
				//create object of FileInputStream for source file
			    FileInputStream fin = new FileInputStream(ze.getLocation());
			    zout.putNextEntry(new java.util.zip.ZipEntry(ze.getLocation()));
				// After creating entry in the zip file, actually write the file.
				int length;
				while ((length = fin.read(buffer)) > 0) {
					zout.write(buffer, 0, length);
				}
				//close the zip entry and related InputStream
				zout.closeEntry();
			    fin.close();
			}
			//close the ZipOutputStream
			zout.close();
		} catch (IOException e) {
			throw new CommunityAPIException("Unable to create the zip file that should be attached to the issue.", e);
		}
		return new File(zipFileLocation);
	}
	
	/**
	 * @return the location of Jaspersoft Studio preferences file
	 */
	public static String getJaspersoftStudioPrefsLocation(){
		return BundleCommonUtils.getWorkspaceLocation() + "/" + JSS_PREFS_RELATIVE_LOCATION;
	}
	
	/**
	 * @return the location of Jaspersoft Studio log file
	 */
	public static String getJaspersoftStudioLogFileLocation(){
		return BundleCommonUtils.getWorkspaceLocation() + "/" + JSS_LOG_RELATIVE_LOCATION;
	}
	
	
	/**
	 * @return software and hardware info.
	 */
	public static String getHardwareSoftwareInfo() {
		BrandingInfo currBranding = JaspersoftStudioPlugin.getInstance().getBrandingInformation();
		String c = "";
		c += "Product Name: " + currBranding.getProductName() + CR;
		c += "Product Version: " + currBranding.getProductVersion() + CR;
		c += "Installation Path: " + getInstallationPath(currBranding.getProductMainBundleID()) + CR;
		c += "Eclipse Version: " + PlatformInfo.getEclipseVersion().toString()
				+ CR;
		c += "Eclipse Build Name: " + PlatformInfo.getEclipseBuildName() + CR;
		c += "Eclipse Build ID: " + PlatformInfo.getEclipseBuildId() + CR;
		c += "IDE Name: " + PlatformInfo.getIDEName() + CR;
		c += "IDE Version: " + PlatformInfo.getIDEVersionString() + CR;
		c += "IDE NL: " + PlatformInfo.getIDENL() + CR;
		c += "Eclipse Commands: "
				+ StringUtils.replaceChars(
						getSystemProperty("eclipse.commands"), "\n\r", " ")
				+ CR;
		c += "Eclipse VM: " + getSystemProperty("eclipse.vm") + CR;
		c += "Eclipse VM Args: " + getSystemProperty("eclipse.vmargs") + CR;
		c += "OS Name: " + getSystemProperty("os.name") + CR;
		c += "OS Arch: " + getSystemProperty("os.arch") + CR;
		c += "OS Version: " + getSystemProperty("os.version") + CR;
		String linuxDescription = getLinuxDescription();
		if (!StringUtils.isEmpty(linuxDescription)) {
			c += "Linux Description: " + linuxDescription + CR;
		}
		String m_mozillaResult = tryCreateMozilla();
		if (!StringUtils.isEmpty(m_mozillaResult)) {
			c += "Browser Creation Result: " + m_mozillaResult + CR;
		}
		Runtime runtime = Runtime.getRuntime();
		c += "Available Processors: " + runtime.availableProcessors() + CR;
		c += "Memory Max: " + runtime.maxMemory() + CR;
		c += "Memory Total: " + runtime.totalMemory() + CR;
		c += "Memory Free: " + runtime.freeMemory() + CR;
		c += "Java Vendor: " + getSystemProperty("java.vendor") + CR;
		c += "Java Version: " + getSystemProperty("java.version") + CR;
		c += "Java Library Path: " + getSystemProperty("java.library.path") + CR;
		return c;
	}

	/*
	 * Returns the installation path of the running product.
	 */
	private static String getInstallationPath(String pluginID) {
		URL installUrl = PluginUtilities.getInstallUrl(pluginID);
		String installationPath = "Unknown";
		try {
			if (installUrl != null) {
				installationPath = FileLocator.toFileURL(installUrl).getPath();
				if (installationPath.length() > 3
						&& installationPath.charAt(0) == '/'
						&& installationPath.charAt(2) == ':') {
					installationPath = installationPath.substring(1);
				}
			}
		} catch (Throwable e) {
		}
		return installationPath;
	}
	
	/**
	 * Get system property and return empty string if no such property.
	 * 
	 * @param prop
	 *            the property name.
	 */
	private static String getSystemProperty(String prop) {
		String propValue = System.getProperty(prop);
		return propValue == null ? "" : propValue;
	}

	/**
	 * Returns the contents of '/etc/lsb-release' (and/or others).
	 */
	private static String getLinuxDescription() {
		StringBuilder result = new StringBuilder();
		if (EnvironmentUtils.IS_LINUX) {
			String[] files = new String[] { "/etc/lsb-release",
					"/etc/lsb_release", "/etc/system-release",
					"/etc/fedora-release", "/etc/SuSE-release",
					"/etc/redhat-release", "/etc/release",
					"/proc/version_signature", "/proc/version", "/etc/issue", };
			for (int i = 0; i < files.length; i++) {
				File file = new File(files[i]);
				if (file.exists() && file.canRead()) {
					try {
						String version = IOUtils2.readString(file).trim();
						if (version != null && result.indexOf(version) == -1) {
							result.append(version);
							result.append("\n");
						}
					} catch (Throwable e) {
						// just ignore
					}
				}
			}
		}
		return result.toString();
	}

	private static String tryCreateMozilla() {
		if (EnvironmentUtils.IS_LINUX) {
			boolean oldDebug = Device.DEBUG;
			Device.DEBUG = true;
			PrintStream oldOut = System.out;
			Shell shell = null;
			PrintStream newOut = null;
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				newOut = new PrintStream(baos);
				// replace the out since the Mozilla output debug results into
				// stdout.
				System.setOut(newOut);
				shell = new Shell();
				try {
					new Browser(shell, SWT.NONE);
				} catch (Throwable e) {
					UIUtils.showError(e);
				}
				return baos.toString();
			} catch (Throwable e1) {
				// ignore
			} finally {
				if (shell != null) {
					shell.dispose();
				}
				System.setOut(oldOut);
				IOUtils.closeQuietly(newOut);
				Device.DEBUG = oldDebug;
			}
		}
		return "";
	}
}
