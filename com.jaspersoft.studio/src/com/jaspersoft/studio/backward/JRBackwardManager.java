/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.backward;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.ZipFilter;

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
		final URI fullURI = new URI(def.getResourceURL());
		HttpUtils.setupProxy(exec, fullURI);
		HttpHost proxy = HttpUtils.getUnauthProxy(exec, fullURI);
		Request req = Request.Get(def.getResourceURL());
		if (proxy != null)
			req.viaProxy(proxy);
		Future<Content> future = Async.newInstance().use(exec).execute(req, new ResponseHandler<Content>() {

			@Override
			public Content handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();

				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300)
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				if (entity == null)
					throw new ClientProtocolException("Response contains no content");

				Header[] headers = response.getHeaders("Content-Type");
				for (Header h : headers) {
					if (h.getValue().equals("application/java-archive")) {
						org.apache.commons.io.FileUtils.copyInputStreamToFile(entity.getContent(), new File(toDir, FilenameUtils.getName(fullURI.getPath())));
						return null;
					}
				}

				FileUtils.unZip(entity.getContent(), toDir, monitor, new ZipFilter() {

					@Override
					public boolean isNecessary(String[] pathComponents) {
						if (pathComponents.length > 1) {
							if (BUILD_FOLDER.equals(pathComponents[1]) || BUILD_FOLDER.equals(pathComponents[0]))
								return true;
							if (LIB_FOLDER.equals(pathComponents[1]) || LIB_FOLDER.equals(pathComponents[0]))
								return true;
						}
						return false;
					}
				});

				return null;
			}
		}, new FutureCallback<Content>() {

			public void failed(final Exception ex) {
				ex.printStackTrace();
			}

			public void completed(final Content content) {
			}

			public void cancelled() {
			}

		});
		while (!future.isDone() && !future.isCancelled()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
			if (monitor.isCanceled()) {
				future.cancel(true);
				return;
			}
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
		Collection<File> lf = org.apache.commons.io.FileUtils.listFiles(f, new String[] {"jar", "zip", "jar" }, true);
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
