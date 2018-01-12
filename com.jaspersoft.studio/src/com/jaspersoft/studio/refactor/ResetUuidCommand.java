/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.refactor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class ResetUuidCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection instanceof IStructuredSelection) {
			Job job = new Job("Reset report UUIDs") {
				protected IStatus run(IProgressMonitor monitor) {
					monitor.beginTask("Reset report UUIDs", IProgressMonitor.UNKNOWN);
					for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
						Object element = it.next();
						if (element instanceof IFile) {
							IFile f = (IFile) element;
							JasperReportsConfiguration jConfig = JasperReportsConfiguration.getDefaultJRConfig(f);
							JasperDesign jd = null;
							InputStream in = null;
							// ok, here should run a job that will load and resave the file
							// what if editor is opened?

							ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
							try {
								Thread.currentThread().setContextClassLoader(jConfig.getClassLoader());

								in = f.getContents();
								jd = new JRXmlLoader(jConfig, JRXmlDigesterFactory.createDigester(jConfig))
										.loadXML(new InputSource(in));
								jConfig.setJasperDesign(jd);

								JRXmlWriter writer = new JRXmlWriter(jConfig);
								writer.setExcludeUuids(true);

								f.setContents(
										new ByteArrayInputStream(
												writer.write(jd, "UTF-8").getBytes(FileUtils.UTF8_ENCODING)),
										IFile.KEEP_HISTORY | IFile.FORCE, monitor);
								f.refreshLocal(0, monitor);
							} catch (UnsupportedEncodingException | CoreException | JRException
									| ParserConfigurationException | SAXException e) {
								UIUtils.showError(e);
							} finally {
								FileUtils.closeStream(in);
								Thread.currentThread().setContextClassLoader(oldCL);
								jConfig.dispose();
							}
						}
					}
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.LONG);
			job.schedule();
		}
		return null;
	}

}
