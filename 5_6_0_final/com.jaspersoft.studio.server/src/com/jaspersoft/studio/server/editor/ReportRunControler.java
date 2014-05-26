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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.control.VBookmarks;
import com.jaspersoft.studio.editor.preview.view.control.VExporter;
import com.jaspersoft.studio.editor.preview.view.control.VSimpleErrorPreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.editor.input.VInputControls;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ReportRunControler {
	public static final String FORM_PARAMETERS = "Input Controls"; //$NON-NLS-1$

	private LinkedHashMap<String, APreview> viewmap;
	private ReportUnitEditor pcontainer;
	private VInputControls prmInput;
	private InputControlsManager icm;
	private String reportUnit;

	public ReportRunControler(ReportUnitEditor pcontainer) {
		this.pcontainer = pcontainer;
	}

	public void setReportUnit(String key) {
		this.reportUnit = key;
		if (viewmap != null && prmInput == null) {
			try {
				icm = new InputControlsManager();
				ProgressMonitorDialog pm = new ProgressMonitorDialog(UIUtils.getShell());

				pm.run(true, true, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						try {
							cli = WSClientHelper.getClient(monitor, reportUnit);
							icm.setWsclient(cli);
							icm.initInputControls(cli.initInputControls(reportUnit, monitor));

							// TODO search all the repository
							icm.getDefaults();
							UIUtils.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (viewmap != null)
										fillForms();
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
			} catch (Exception e1) {
				UIUtils.showError(e1);
			}
		} else {
			runReport();
		}
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite, JasperReportsConfiguration jContext) {
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VInputControls(composite, jContext));
		viewmap.put(ReportControler.FORM_BOOKMARKS, new VBookmarks(composite, jContext, pcontainer));
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

	private IConnection cli;

	private Statistics stats;

	public void runReport() {
		VSimpleErrorPreview errorView = pcontainer.getErrorView();
		pcontainer.getRightContainer().switchView(null, errorView);
		errorView.setMessage(com.jaspersoft.studio.messages.Messages.ReportControler_generating);

		c = pcontainer.getConsole();
		c.showConsole();
		c.clearConsole();
		pcontainer.setJasperPrint(null, null);

		stats = new Statistics();
		stats.startCount(ReportControler.ST_REPORTEXECUTIONTIME);
		c.addMessage(Messages.ReportRunControler_statsstart);

		pcontainer.setNotRunning(false);

		Job job = new Job(com.jaspersoft.studio.messages.Messages.PreviewEditor_preview_a + ": " + reportUnit + com.jaspersoft.studio.messages.Messages.PreviewEditor_preview_b) { //$NON-NLS-1$ 

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					prmInput.setReportUnit(icm.getReportUnit());
					if (!prmInput.checkFieldsFilled())
						return Status.CANCEL_STATUS;
					Map<String, Object> prmcopy = new HashMap<String, Object>();
					for (ResourceDescriptor p : icm.getInputControls()) {
						if (p.isVisible() && !p.isReadOnly()) {
							if (p.isMandatory())
								prmcopy.put(p.getName(), icm.getParameters().get(p.getName()));
							else if (icm.getParameters().get(p.getName()) != null)
								prmcopy.put(p.getName(), icm.getParameters().get(p.getName()));
						}
					}

					Map<String, FileContent> files = WSClientHelper.runReportUnit(monitor, reportUnit, prmcopy);
					stats.endCount(ReportControler.ST_REPORTEXECUTIONTIME);
					for (String key : files.keySet()) {
						FileContent fc = (FileContent) files.get(key);
						if (key.equals("jasperPrint")) { //$NON-NLS-1$
							final File f = File.createTempFile(FileExtension.JRPRINT, "." + FileExtension.JRPRINT); //$NON-NLS-1$  
							f.deleteOnExit();
							f.createNewFile();
							FileOutputStream htmlFile = new FileOutputStream(f);
							htmlFile.write(fc.getData());
							htmlFile.close();
							stats.endCount(ReportControler.ST_REPORTEXECUTIONTIME);
							stats.setValue(ReportControler.ST_REPORTSIZE, f.length());
							final Object obj = JRLoader.loadObject(f);
							if (obj instanceof JasperPrint)
								UIUtils.getDisplay().asyncExec(new Runnable() {

									@Override
									public void run() {
										stats.setValue(ReportControler.ST_PAGECOUNT, ((JasperPrint) obj).getPages().size());
										APreview pv = pcontainer.getDefaultViewer();
										if (pv instanceof IJRPrintable)
											try {
												((IJRPrintable) pv).setJRPRint(stats, ((JasperPrint) obj), true);
												VBookmarks vs = (VBookmarks) viewmap.get(ReportControler.FORM_BOOKMARKS);
												vs.setJasperPrint(((JasperPrint) obj));
											} catch (Exception e) {
												e.printStackTrace();
											}
										pcontainer.setJasperPrint(stats, (JasperPrint) obj);
									}
								});
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

	public void resetParametersToDefault() {
		prmInput.setupDefaultValues();
	}
}
