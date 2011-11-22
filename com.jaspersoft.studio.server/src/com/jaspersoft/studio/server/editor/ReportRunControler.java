package com.jaspersoft.studio.server.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.UIUtils;

public class ReportRunControler {
	public static final String FORM_PARAMETERS = "Input Controls";

	private PropertiesHelper ph;
	private LinkedHashMap<String, APreview> viewmap;
	private ReportUnitEditor pcontainer;
	private VInputControls prmInput;

	public ReportRunControler(ReportUnitEditor pcontainer) {
		this.pcontainer = pcontainer;
	}

	private String reportUnit;

	public void setReportUnit(String key) {
		this.reportUnit = key;
		// this.prompts = jDesign.getParametersList();
		// setParameters(jDesign);
		// if (viewmap != null)
		// fillForms();
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite,
			PropertiesHelper ph) {
		this.ph = ph;
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VInputControls(composite, ph));

		// if (jDesign != null)
		fillForms();
		return viewmap;
	}

	private void fillForms() {
		prmInput = (VInputControls) viewmap.get(FORM_PARAMETERS);
		// prmInput.createInputControls(prompts, jasperParameters);

	}

	private long stime;
	private Console c;

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
						Map<String, Object> files = WSClientHelper
								.runReportUnit(reportUnit);
						for (String key : files.keySet()) {
							FileContent fc = (FileContent) files.get(key);
							if (key.equals("jasperPrint")) {
								final File f = File.createTempFile("jrprint",
										".jrprint");
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
						finishReport();
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

	public void finishReport() {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				long etime = System.currentTimeMillis();
				c.addMessage("end report");
				long ttime = etime - stime;
				c.addMessage(String.format("Total time: %1$.3f s",
						(double) (ttime / 1000)));
				pcontainer.setNotRunning(true);
				if (pcontainer.getJasperPrint() != null)
					c.addMessage(String.format("Number of Pages: %d",
							pcontainer.getJasperPrint().getPages().size()));
			}
		});
	}
}
