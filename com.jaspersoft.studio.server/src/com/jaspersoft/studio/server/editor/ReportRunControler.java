/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.control.VExporter;
import com.jaspersoft.studio.editor.preview.view.control.VSimpleErrorPreview;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.editor.input.VInputControls;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ReportRunControler {
	public static final String FORM_PARAMETERS = "Input Controls"; //$NON-NLS-1$

	private LinkedHashMap<String, APreview> viewmap;
	private ReportUnitEditor pcontainer;
	private VInputControls prmInput;
	private ResourceDescriptor rdrepunit;
	private InputControlsManager icm;
	private String reportUnit;

	public ReportRunControler(ReportUnitEditor pcontainer) {
		this.pcontainer = pcontainer;
	}

	public void setReportUnit(String key) {
		this.reportUnit = key;
		if (viewmap != null && prmInput == null) {
			cli = WSClientHelper.getClient(reportUnit);
			icm = new InputControlsManager(reportUnit);
			ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
					.getDefault().getActiveShell());
			try {
				pm.run(true, true, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							rdrepunit = WSClientHelper
									.getReportUnit(reportUnit);
							List<ResourceDescriptor> list = cli.list(rdrepunit);
							icm.getInputControls(list, cli);

							// TODO search all the repository
							icm.getDefaults(rdrepunit);
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									if (viewmap != null) {
										fillForms();
									}
									runReport();
								}
							});
						} catch (Throwable e) {
							throw new InvocationTargetException(e);
						} finally {
							monitor.done();
						}
					}

				});
			} catch (InvocationTargetException e) {
				UIUtils.showError(e.getCause());
			} catch (InterruptedException e) {
				UIUtils.showError(e);
			}
		} else {
			runReport();
		}
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite,
			JasperReportsConfiguration jContext) {
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VInputControls(composite, jContext)); 
		viewmap.put(ReportControler.FORM_EXPORTER, new VExporter(composite, jContext));
		return viewmap;
	}
	public void viewerChanged(APreview view) {
		VExporter vs = (VExporter) viewmap.get(ReportControler.FORM_EXPORTER);
		vs.setPreferencesPage(view);
	}

	private void fillForms() {
		prmInput = (VInputControls) viewmap.get(FORM_PARAMETERS);
		prmInput.createInputControls(icm);
	}

	private Console c;

	private WSClient cli;

	private Statistics stats;

	public void runReport() {
		VSimpleErrorPreview errorView = pcontainer.getErrorView();
		pcontainer.getRightContainer().switchView(null, errorView);
		errorView
				.setMessage(com.jaspersoft.studio.messages.Messages.ReportControler_generating);

		c = pcontainer.getConsole();
		c.showConsole();
		c.clearConsole();
		pcontainer.setJasperPrint(null, null);

		stats = new Statistics();
		stats.startCount(ReportControler.ST_REPORTEXECUTIONTIME);
		c.addMessage(Messages.ReportRunControler_statsstart);

		pcontainer.setNotRunning(false);

		Job job = new Job(
				com.jaspersoft.studio.messages.Messages.PreviewEditor_preview_a
						+ ": " + reportUnit + com.jaspersoft.studio.messages.Messages.PreviewEditor_preview_b) { //$NON-NLS-1$ 

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					if (!prmInput.checkFieldsFilled())
						return Status.CANCEL_STATUS;

					Map<String, FileContent> files = WSClientHelper
							.runReportUnit(reportUnit, icm.getParameters());
					stats.endCount(ReportControler.ST_REPORTEXECUTIONTIME);
					for (String key : files.keySet()) {
						FileContent fc = (FileContent) files.get(key);
						if (key.equals("jasperPrint")) { //$NON-NLS-1$
							final File f = File.createTempFile(
									"jrprint", ".jrprint"); //$NON-NLS-1$ //$NON-NLS-2$
							f.deleteOnExit();
							f.createNewFile();
							FileOutputStream htmlFile = new FileOutputStream(f);
							htmlFile.write(fc.getData());
							htmlFile.close();
							stats.endCount(ReportControler.ST_REPORTEXECUTIONTIME);
							stats.setValue(ReportControler.ST_REPORTSIZE,
									f.length());
							Object obj = JRLoader.loadObject(f);
							if (obj instanceof JasperPrint) {
								stats.setValue(ReportControler.ST_PAGECOUNT,
										((JasperPrint) obj).getPages().size());
								pcontainer.setJasperPrint(stats,
										(JasperPrint) obj);
							}

							break;
						}
					}
				} catch (Throwable e) {
					ReportControler.showRunReport(c, pcontainer, e);
				} finally {
					monitor.done();
					finishReport();
				}
				return Status.OK_STATUS;
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	public void finishReport() {
		ReportControler.finishCompiledReport(c, prmInput, pcontainer);
	}
}
