/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.AsynchronousFilllListener;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.input.BigNumericInput;
import com.jaspersoft.studio.editor.preview.input.BooleanInput;
import com.jaspersoft.studio.editor.preview.input.DateInput;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ImageInput;
import com.jaspersoft.studio.editor.preview.input.LocaleInput;
import com.jaspersoft.studio.editor.preview.input.NumericInput;
import com.jaspersoft.studio.editor.preview.input.TextInput;
import com.jaspersoft.studio.editor.preview.input.TimeZoneInput;
import com.jaspersoft.studio.editor.preview.jive.Context;
import com.jaspersoft.studio.editor.preview.jive.JettyUtil;
import com.jaspersoft.studio.editor.preview.jive.SReportServlet;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.preferences.virtualizer.VirtualizerHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.UIUtils;

public class ReportControler {

	public static final String FORM_SORTING = "Sorting Options";

	public static final String FORM_REPORT_PARAMETERS = "Report Parameters";
	public static final String FORM_PARAMETERS = "Input Parameters";

	public static List<IDataInput> inputs = new ArrayList<IDataInput>();
	static {
		inputs.add(new BooleanInput());
		inputs.add(new TextInput());
		inputs.add(new LocaleInput());
		inputs.add(new TimeZoneInput());
		inputs.add(new NumericInput());
		inputs.add(new BigNumericInput());
		inputs.add(new DateInput());
		inputs.add(new ImageInput());
	}

	private List<JRParameter> prompts;
	private Map<String, Object> jasperParameters;
	private JasperDesign jDesign;
	private PropertiesHelper ph;
	private LinkedHashMap<String, APreview> viewmap;
	private PreviewContainer pcontainer;

	public ReportControler(PreviewContainer pcontainer) {
		this.pcontainer = pcontainer;
	}

	public void setJasperDesign(JasperDesign jDesign) {
		this.jDesign = jDesign;
		this.prompts = jDesign.getParametersList();
		setParameters(jDesign);
		if (viewmap != null)
			fillForms();
	}

	private void setParameters(JasperDesign jDesign) {
		if (jasperParameters == null)
			jasperParameters = new HashMap<String, Object>();
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			List<JRParameter> prm = jDesign.getParametersList();
			for (JRParameter p : prm) {
				Object obj = jasperParameters.get(p.getName());
				if (obj != null && obj.getClass().equals(p.getValueClass()))
					map.put(p.getName(), obj);
			}
			jasperParameters = map;
		}
	}

	public JasperDesign getjDesign() {
		return jDesign;
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite, PropertiesHelper ph) {
		this.ph = ph;
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VParameters(composite, ph));
		viewmap.put(FORM_REPORT_PARAMETERS, new VReportParameters(composite, ph));
		viewmap.put(FORM_SORTING, new VSorting(composite, ph));

		if (jDesign != null)
			fillForms();
		return viewmap;
	}

	private long stime;

	private void fillForms() {
		prmInput = (VParameters) viewmap.get(FORM_PARAMETERS);
		prmInput.createInputControls(prompts, jasperParameters);

		VReportParameters prmRepInput = (VReportParameters) viewmap.get(FORM_REPORT_PARAMETERS);
		prmRepInput.createInputControls(prompts, jasperParameters);

		VSorting vs = (VSorting) viewmap.get(FORM_SORTING);
		vs.setJasperReports(jDesign, prompts, jasperParameters);
	}

	private Console c;

	public void runReport() {
		c = pcontainer.getConsole();
		c.clearConsole();
		if (pcontainer.getMode().equals(RunStopAction.MODERUN_LOCAL))
			pcontainer.setJasperPrint(null);
		fillError = null;

		stime = System.currentTimeMillis();
		c.addMessage("Start");

		pcontainer.setNotRunning(false);
		runJob(pcontainer);
	}

	public void finishReport(final PreviewContainer pcontainer) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				long etime = System.currentTimeMillis();
				c.addMessage("end report");
				long ttime = etime - stime;
				c.addMessage(String.format("Total time: %1$.3f s", (double) (ttime / 1000)));
				pcontainer.setNotRunning(true);
				boolean notprmfiled = !prmInput.checkFieldsFilled();
				if (notprmfiled) {
					c.addMessage("You have some input parameters, that you have to fill first");
					UIUtils.showWarning("You have some input parameters, that you have to fill first");
				}
				pcontainer.showParameters(notprmfiled);
				if (pcontainer.getJasperPrint() != null)
					c.addMessage(String.format("Number of Pages: %d", pcontainer.getJasperPrint().getPages().size()));
			}
		});
	}

	private void runJob(final PreviewContainer pcontainer) {
		fillError = null;
		Job job = new Job(Messages.PreviewEditor_preview_a + ": " + jDesign.getName() + Messages.PreviewEditor_preview_b) { //$NON-NLS-1$ 
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				DataAdapterService dataAdapterService = null;
				ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
				try {
					final IFile file = ((IFileEditorInput) pcontainer.getEditorInput()).getFile();

					monitor.beginTask(Messages.PreviewEditor_starting, IProgressMonitor.UNKNOWN);

					setupFileRezolver(monitor, file);

					JasperDesign jd = copyJasperDesign();

					setupProperties(jd);

					JasperReport jasperReport = compileJasperDesign(jd);
					if (jasperReport != null) {
						c.addMessage("Compilation successful");

						if (!prmInput.checkFieldsFilled())
							return Status.CANCEL_STATUS;

						if (pcontainer.getMode().equals(RunStopAction.MODERUN_JIVE)) {
							setupDataAdapter(pcontainer);
							runJive(pcontainer, file, jasperReport);
						} else {
							setupVirtualizer(jd, ph);

							dataAdapterService = setupDataAdapter(pcontainer);

							c.addMessage("Start report execution");
							// We create the fillHandle to run the report based on the type of data adapter....
							AsynchronousFillHandle fh = AsynchronousFillHandle.createHandle(jasperReport, jasperParameters);

							if (fillReport(fh, monitor, pcontainer) == Status.CANCEL_STATUS)
								return Status.CANCEL_STATUS;

							dataAdapterService.dispose();
						}
					}
				} catch (final Throwable e) {
					c.addError(e);
				} finally {
					monitor.done();

					// Allow the data adapter to cleanup its state
					if (dataAdapterService != null)
						dataAdapterService.dispose();
					finishReport(pcontainer);
					Thread.currentThread().setContextClassLoader(oldLoader);
				}
				return Status.OK_STATUS;
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	protected void runJive(final PreviewContainer pcontainer, final IFile file, final JasperReport jasperReport) {
		JettyUtil.startJetty(file.getProject());
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					Map<String, Object> prm = new HashMap<String, Object>();
					DataAdapter dataAdapter = pcontainer.getDataAdapterDesc().getDataAdapter();
					DataAdapterService dataAdapterService = DataAdapterServiceUtil.getDataAdapterService(dataAdapter);

					prm.put(SReportServlet.PRM_JRPARAMETERS, jasperParameters);
					prm.put(SReportServlet.PRM_JASPERREPORT, jasperReport);

					Context.putContext(SReportServlet.PRM_JSSContext, prm);

					String url = JettyUtil.getURL(file);
					pcontainer.getJiveViewer().setURL(url);
				} catch (Exception e) {
					UIUtils.showError(e);
				}
			}
		});
	}

	private JasperReport compileJasperDesign(JasperDesign jd) throws JRException {
		c.addMessage("Start compiling");
		JasperReport jasperReport = JasperCompileManager.compileReport(jd);
		return jasperReport;
	}

	private void setupProperties(JasperDesign jd) {
		ph.setProperties(jd);
	}

	private JasperDesign copyJasperDesign() throws JRException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRSaver.saveObject(jDesign, out);
		JasperDesign jd = (JasperDesign) JRLoader.loadObject(new ByteArrayInputStream(out.toByteArray()));
		return jd;
	}

	private void setupFileRezolver(IProgressMonitor monitor, IFile file) {
		SimpleFileResolver fileResolver = SelectionHelper.setClassLoader(file, monitor);
		jasperParameters.put(JRParameter.REPORT_FILE_RESOLVER, fileResolver);
	}

	private void setupVirtualizer(JasperDesign jd, PropertiesHelper ps) {
		c.addMessage("Setting virtualizer");
		VirtualizerHelper.setVirtualizer(jd, ps, jasperParameters);
	}

	private DataAdapterService setupDataAdapter(final PreviewContainer pcontainer) throws JRException {
		c.addMessage("Setting connection");
		DataAdapter dataAdapter = pcontainer.getDataAdapterDesc().getDataAdapter();
		jasperParameters.remove(JRParameter.REPORT_CONNECTION);
		jasperParameters.remove(JRParameter.REPORT_DATA_SOURCE);
		// We let the data adapter to contribute its parameters.
		DataAdapterService dataAdapterService = DataAdapterServiceUtil.getDataAdapterService(dataAdapter);
		dataAdapterService.contributeParameters(jasperParameters);
		return dataAdapterService;
	}

	private IStatus fillReport(AsynchronousFillHandle fh, IProgressMonitor monitor, final PreviewContainer pcontainer)
			throws JRException, InterruptedException {
		Assert.isTrue(fh != null);
		IProgressMonitor sm = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN,
				SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		try {
			sm.beginTask(Messages.PreviewEditor_fill_report, IProgressMonitor.UNKNOWN);

			fh.addListener(new AsynchronousFilllListener() {

				public void reportFinished(JasperPrint jPrint) {
					pcontainer.setJasperPrint(jPrint);
				}

				public void reportFillError(Throwable t) {
					handleFillException(t);
				}

				public void reportCancelled() {
					c.addMessage(Messages.PreviewEditor_report_fill_canceled);
				}
			});
			fh.startFill();
			while (true && pcontainer.getJasperPrint() == null && fillError == null) {
				if (sm.isCanceled()) {
					fh.cancellFill();
					return Status.CANCEL_STATUS;
				}
				Thread.sleep(500);
				sm.worked(10);
			}
			if (fillError != null)
				throw new JRException(fillError);

		} finally {
			sm.done();
		}
		return Status.OK_STATUS;
	}

	private Throwable fillError = null;

	private VParameters prmInput;

	private void handleFillException(Throwable t) {
		fillError = t;
	}
}
