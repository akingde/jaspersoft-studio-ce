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
		cli = WSClientHelper.getClient(reportUnit);
		icm = new InputControlsManager(reportUnit);
		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());
		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						rdrepunit = WSClientHelper.getReportUnit(reportUnit);
						List<ResourceDescriptor> list = cli.list(rdrepunit);
						icm.getInputControls(list, cli);
						icm.getDefaults(rdrepunit);
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (viewmap != null && prmInput == null)
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

	private long stime;
	private Console c;

	private WSClient cli;

	public void runReport() {
		c = pcontainer.getConsole();
		c.clearConsole();
		pcontainer.setJasperPrint(null);

		stime = System.currentTimeMillis();
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
							Map<String, Object> files = WSClientHelper
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
										pcontainer
												.setJasperPrint((JasperPrint) obj);

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
				long etime = System.currentTimeMillis();
				c.addMessage("end report");
				long ttime = etime - stime;
				c.addMessage(String.format("Total time: %1$.3f s",
						(double) (ttime / 1000)));
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
