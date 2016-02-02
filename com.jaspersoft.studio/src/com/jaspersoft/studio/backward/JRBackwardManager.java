/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.backward;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;

/**
 * Class that offer the utility methods to build a JRXML with an older version of JasperReports. It is a singleton class
 * 
 * @author Orlandin Marco
 *
 */
public class JRBackwardManager {

	/**
	 * Key of the storage where the older version of jasperreports are saved
	 */
	protected static final String JR_COMPILER_STORAGE = "jrCompilers"; //$NON-NLS-1$

	/**
	 * Folder searched inside a JR zip that must contains the JR jars
	 */
	private static final String BUILD_FOLDER = "dist"; //$NON-NLS-1$

	/**
	 * Folder searched inside a JR zip that must contains the libraries used by JR
	 */
	private static final String LIB_FOLDER = "lib"; //$NON-NLS-1$

	/**
	 * Storage where the older version of jasperreports are saved
	 */
	public static File storage = ConfigurationManager.getStorage(JRBackwardManager.JR_COMPILER_STORAGE);

	/**
	 * Read a resource from the current plugin and save it with a specific name inside a specified folder folder. If a
	 * file was already define inside the specified folder with the same name, then it doesn't do nothing
	 *
	 * @param path
	 *          the path of the resource inside the plugin
	 * @param fileName
	 *          the name of the file that will be created on the specified folder
	 * @param destinationDir
	 *          directory where the file will be saved
	 */
	private static void fetchResource(String path, File destinationDir, String fileName) {
		File dfile = new File(destinationDir, fileName);
		if (dfile.exists())
			return;
		URL url = JaspersoftStudioPlugin.getInstance().getClass().getClassLoader().getResource(path);
		InputStream is = null;
		try {
			is = url.openStream();
			org.apache.commons.io.FileUtils.copyInputStreamToFile(is, dfile);
		} catch (Exception ex) {
			JaspersoftStudioPlugin.getInstance()
					.logError(MessageFormat.format(Messages.JRBackwardManager_dowloadError, new Object[] { path }), ex);
		} finally {
			FileUtils.closeStream(is);
		}
	}

	/**
	 * Download the zip of a JR from the server and save it on the temp folder
	 * 
	 * @param def
	 *          the definition of the jr to download, containing the url to the server
	 * @param monitor
	 *          the monitor to execute the operation. It update the taskname with the abount of MB downloaded
	 * @return the file on the temp folder, zip extension (but depends from the url), containing the JR build of the
	 *         requested version
	 * @throws Exception
	 *           launched when something goes wrong with the download
	 */
	public static void fetchJR(JRDefinition def, final File toDir, final IProgressMonitor monitor) throws Exception {
		Executor exec = Executor.newInstance();
		URI fullURI = new URI(def.getResourceURL());
		HttpUtils.setupProxy(exec, fullURI);
		HttpHost proxy = HttpUtils.getUnauthProxy(exec, fullURI);
		Request req = Request.Get(def.getResourceURL());
		if (proxy != null)
			req.viaProxy(proxy);
		req.execute().handleResponse(new ResponseHandler<Boolean>() {

			@Override
			public Boolean handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();

				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300)
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				if (entity == null)
					throw new ClientProtocolException("Response contains no content");

				unZip(entity.getContent(), toDir, monitor);

				return true;
			}
		});
	}

	/**
	 * Return if the file is necessary, so if in the first or second level is inside the folder dist or lib
	 * 
	 * @param pathComponents
	 *          the complete path of a file inside the zip. Each segment is a folder and the last one is the file
	 * @return true if this file of the zip must be unpacked inside the storage, false otherwise
	 */
	private static boolean isNecessary(String[] pathComponents) {
		if (pathComponents.length > 1) {
			if (BUILD_FOLDER.equals(pathComponents[1]) || BUILD_FOLDER.equals(pathComponents[0]))
				return true;
			if (LIB_FOLDER.equals(pathComponents[1]) || LIB_FOLDER.equals(pathComponents[0]))
				return true;
		}
		return false;
	}

	/**
	 * Unzip a JR package downloaded from the server, but only the dist and folder are unzipped and mixed togheter in the
	 * destination
	 * 
	 * @param zipFile
	 *          file to unzip
	 * @param outputFolder
	 *          folder where the unzipped files must be placed
	 * @param monitor
	 *          monitor for the operation, can be cancelled
	 * @throws IOException
	 */
	public static void unZip(InputStream in, File outputFolder, IProgressMonitor monitor) throws IOException {
		byte[] buffer = new byte[1024];
		ZipInputStream zis = null;
		try {
			if (!outputFolder.exists())
				outputFolder.mkdir();
			// get the zip file content
			zis = new ZipInputStream(in);
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			monitor.setTaskName(Messages.JRBackwardManager_extracting);
			if (ze == null)
				throw new IOException("Invalid Zip");
			while (ze != null && !monitor.isCanceled()) {
				if (!ze.isDirectory()) {
					String[] pathComponents = ze.getName().split("/"); //$NON-NLS-1$
					if (isNecessary(pathComponents)) {
						File newFile = new File(outputFolder, pathComponents[pathComponents.length - 1]);
						monitor.setTaskName(Messages.JRBackwardManager_extracting + " " + newFile.getName());
						File pfile = newFile.getParentFile();
						if (!pfile.exists())
							pfile.mkdirs();
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(newFile);
							int len;
							while ((len = zis.read(buffer)) > 0 && !monitor.isCanceled())
								fos.write(buffer, 0, len);
						} finally {
							FileUtils.closeStream(fos);
						}
					}
				}
				ze = zis.getNextEntry();
			}
		} catch (IOException ex) {
			org.apache.commons.io.FileUtils.deleteDirectory(outputFolder);
			throw ex;
		} finally {
			FileUtils.closeStream(zis);
		}
	}

	/**
	 * Delete a JR version from the storage, if it is saved inside
	 * 
	 * @param def
	 *          the definition that contains the version of the JR to delete from the storage, must be not null
	 * @throws IOException
	 */
	public static void deleteJR(JRDefinition def) throws IOException {
		File jrPath = new File(def.getResourceURL());
		if (jrPath.exists())
			org.apache.commons.io.FileUtils.deleteDirectory(jrPath);
	}

	public static boolean download(JRDefinition d, IProgressMonitor monitor) throws Exception {
		File path = null;
		try {
			path = FileUtils.createTempDir("jrkit");
			fetchJR(d, path, monitor);
			d.setVersion(verify(path.getAbsolutePath()));
			File destDir = new File(storage, d.getVersion());
			d.setResourceURL(destDir.getAbsolutePath());
			if (destDir.exists()) {
				// add i at the end?
				if (UIUtils.showConfirmation("Confirmation",
						"This version already exists , do you want to replace existing one?")) {
					org.apache.commons.io.FileUtils.deleteDirectory(destDir);
				} else {
					org.apache.commons.io.FileUtils.deleteDirectory(path);
					return false;
				}
			}
			org.apache.commons.io.FileUtils.moveDirectory(path, destDir);
			fetchResource("com/jaspersoft/studio/backward/resources/JRToolKit.jar", destDir, "JRToolKit.jar");
		} catch (Exception e) {
			if (path != null)
				path.delete();
			throw e;
		}
		return true;
	}

	public static String verify(String path) throws Exception {
		if (!path.endsWith("/"))
			path += "/";
		if (Misc.isNullOrEmpty(path))
			throw new Exception("Paths is empty.");
		File f = new File(path);
		if (!f.exists())
			throw new Exception("Path does not exists.");
		if (!f.isDirectory())
			throw new Exception("Path is not a directory.");
		Collection<File> lf = org.apache.commons.io.FileUtils.listFiles(f, new String[] { "zip", "jar" }, true);
		List<File> files = new ArrayList<File>();
		if (lf != null)
			files.addAll(lf);
		files.add(f);
		URL[] urls = org.apache.commons.io.FileUtils.toURLs(files.toArray(new File[files.size()]));
		ClassLoader cl = new URLClassLoader(urls);
		try {
			Class<?> c = cl.loadClass("net.sf.jasperreports.engine.JasperCompileManager");
			return c.getPackage().getImplementationVersion();
		} catch (ClassNotFoundException e) {
			throw new Exception("There is no JRCompiler");
		}
	}
}
