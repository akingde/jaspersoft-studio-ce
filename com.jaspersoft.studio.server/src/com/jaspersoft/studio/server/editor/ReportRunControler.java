/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.control.Statistics;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.editor.input.VInputControls;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.UIUtils;

public class ReportRunControler {
	public static final String FORM_PARAMETERS = "Input Controls";

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
			PropertiesHelper ph) {
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VInputControls(composite, ph));

		return viewmap;
	}

	private void fillForms() {
		prmInput = (VInputControls) viewmap.get(FORM_PARAMETERS);
		prmInput.createInputControls(icm);
	}

	private Console c;

	private WSClient cli;

	private Statistics stats;

	public void runReport() {
		c = pcontainer.getConsole();
		c.clearConsole();
		pcontainer.setJasperPrint(null, null);

		stats = new Statistics();
		stats.startCount(ReportControler.ST_REPORTEXECUTIONTIME);
		c.addMessage("Start");

		pcontainer.setNotRunning(false);

		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());
		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						if (prmInput.checkFieldsFilled()) {
							Map<String, FileContent> files = WSClientHelper
									.runReportUnit(reportUnit,
											icm.getParameters());
							for (String key : files.keySet()) {
								FileContent fc = (FileContent) files.get(key);
								if (key.equals("jasperPrint")) {
									final File f = File.createTempFile(
											"jrprint", ".jrprint");
									f.deleteOnExit();
									f.createNewFile();
									FileOutputStream htmlFile = new FileOutputStream(
											f);
									htmlFile.write(fc.getData());
									htmlFile.close();

									Object obj = JRLoader.loadObject(f);
									if (obj instanceof JasperPrint)
										pcontainer.setJasperPrint(stats,
												(JasperPrint) obj);

									break;
								}
							}
						}
					} catch (Throwable e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
						finishReport();
					}
				}

			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}

	public void finishReport() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				c.addMessage("end report");
				stats.endCount(ReportControler.ST_REPORTEXECUTIONTIME);
				c.addMessage(String.format(
						"Total time: %1$.3f s",
						(double) (stats
								.getDuration(ReportControler.ST_REPORTEXECUTIONTIME) / 1000)));
				pcontainer.setNotRunning(true);

				boolean notprmfiled = !prmInput.checkFieldsFilled();
				if (notprmfiled) {
					c.addMessage("You have some input parameters, that you have to fill first");
					UIUtils.showWarning("You have some input parameters, that you have to fill first");
				}
				pcontainer.showParameters(notprmfiled);
				if (pcontainer.getJasperPrint() != null)
					c.addMessage(String.format("Number of Pages: %d",
							pcontainer.getJasperPrint().getPages().size()));
			}
		});
	}
}
