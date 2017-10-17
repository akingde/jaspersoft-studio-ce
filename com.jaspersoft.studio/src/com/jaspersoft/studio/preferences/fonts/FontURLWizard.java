/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

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
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.ZipFilter;
import net.sf.jasperreports.engine.fonts.FontFamily;

public class FontURLWizard extends Wizard {
	private FontURLPage page0;
	private List<FontFamily> fontFamilies = new ArrayList<FontFamily>();

	public FontURLWizard(List<FontFamily> fontFamilies) {
		super();
		setWindowTitle("Fonts");
		setNeedsProgressMonitor(true);
		this.fontFamilies = fontFamilies;
	}

	public List<FontFamily> getFonts() {
		return fontFamilies;
	}

	@Override
	public void addPages() {
		page0 = new FontURLPage();
		addPage(page0);
	}

	public static File storage = ConfigurationManager.getStorage("fonts");

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.JRRuntimeURLWizard_1, IProgressMonitor.UNKNOWN);
					try {
						File path = null;
						try {
							path = FileUtils.createTempDir("jssfonts");
							fetch(path, monitor, page0.getValue());
							monitor.setTaskName("create destination directory");
							File destDir = new File(storage, "fonts");
							monitor.setTaskName("move to destingation directory " + destDir);
							if (destDir.exists())
								for (File f : path.listFiles()) {
									try {
										File df = new File(destDir, f.getName());
										if (df.exists())
											df.delete();
										org.apache.commons.io.FileUtils.moveFileToDirectory(f, destDir, true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							else
								org.apache.commons.io.FileUtils.moveDirectory(path, destDir);
							FontImporter.analyzeFolder(fontFamilies, destDir, monitor);
						} catch (Exception e) {
							if (path != null)
								path.delete();
							throw e;
						}
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
				}

				private void fetch(final File toDir, final IProgressMonitor monitor, String url) throws Exception {
					Executor exec = Executor.newInstance();
					URI fullURI = new URI(url);
					HttpUtils.setupProxy(exec, fullURI);
					HttpHost proxy = HttpUtils.getUnauthProxy(exec, fullURI);
					Request req = Request.Get(url);
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

							FileUtils.unZip(entity.getContent(), toDir, monitor, new ZipFilter() {

								@Override
								public boolean isNecessary(String[] pathComponents) {
									String fname = pathComponents[pathComponents.length - 1].toLowerCase();
									if (fname.endsWith(".ttf") || fname.endsWith(".otf") || fname.endsWith(".svg")
											|| fname.endsWith(".eot") || fname.endsWith(".woff"))
										return true;
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
			});
		} catch (InvocationTargetException e) {
			page0.setErrorMessage(e.getCause().getMessage());
			return false;
		} catch (InterruptedException e) {
			page0.setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}

}
