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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.AsynchronousFilllListener;
import net.sf.jasperreports.engine.fill.FillListener;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.DataAdapterParameterContributorFactory;
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
import com.jaspersoft.studio.editor.preview.jive.servlet.SReportServlet;
import com.jaspersoft.studio.editor.preview.stats.RecordCountScriptletFactory;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.virtualizer.VirtualizerHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ReportControler {

	public static final String ST_RECORDCOUNTER = "RECORDCOUNTER";

	public static final String ST_PAGECOUNT = "PAGECOUNT";

	public static final String ST_FILLINGTIME = "FILLINGTIME";

	public static final String ST_COMPILATIONTIME = "COMPILATIONTIME";

	public static final String ST_REPORTEXECUTIONTIME = "REPORTEXECUTIONTIME";

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
	private LinkedHashMap<String, APreview> viewmap;
	private PreviewContainer pcontainer;
	private JasperReportsConfiguration jrContext;

	public ReportControler(PreviewContainer pcontainer, JasperReportsConfiguration jrContext) {
		this.pcontainer = pcontainer;
		setJrContext(jrContext);
	}

	public JasperReportsConfiguration getJrContext() {
		return jrContext;
	}

	public void setJrContext(JasperReportsConfiguration jrContext) {
		this.jrContext = jrContext;
		prompts = jrContext.getJasperDesign().getParametersList();
		setParameters();
		if (viewmap != null)
			fillForms();
	}

	private void setParameters() {
		if (jasperParameters == null)
			jasperParameters = new HashMap<String, Object>();
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			List<JRParameter> prm = jrContext.getJasperDesign().getParametersList();
			for (JRParameter p : prm) {
				Object obj = jasperParameters.get(p.getName());
				if (obj != null && obj.getClass().equals(p.getValueClass()))
					map.put(p.getName(), obj);
			}
			jasperParameters = map;
		}
		jrContext.setJRParameters(jasperParameters);
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite) {
		viewmap = new LinkedHashMap<String, APreview>();
		viewmap.put(FORM_PARAMETERS, new VParameters(composite, jrContext));
		viewmap.put(FORM_REPORT_PARAMETERS, new VReportParameters(composite, jrContext));
		viewmap.put(FORM_SORTING, new VSorting(composite, jrContext));

		if (jrContext != null && jrContext.getJasperDesign() != null)
			fillForms();
		return viewmap;
	}

	private void fillForms() {
		prmInput = (VParameters) viewmap.get(FORM_PARAMETERS);
		prmInput.createInputControls(prompts, jasperParameters);

		VReportParameters prmRepInput = (VReportParameters) viewmap.get(FORM_REPORT_PARAMETERS);
		prmRepInput.createInputControls(prompts, jasperParameters);

		VSorting vs = (VSorting) viewmap.get(FORM_SORTING);
		vs.setJasperReports(jrContext.getJasperDesign(), prompts, jasperParameters);
	}

	private Console c;

	public void runReport() {
		c = pcontainer.getConsole();
		c.showConsole();
		c.clearConsole();
		if (pcontainer.getMode().equals(RunStopAction.MODERUN_LOCAL))
			pcontainer.setJasperPrint(null, null);
		// jasperParameters.clear();
		fillError = null;
		stats = new Statistics();
		stats.startCount(ST_REPORTEXECUTIONTIME);
		c.addMessage("Start Report Execution");

		pcontainer.setNotRunning(false);
		runJob(pcontainer);
	}

	public void finishReport(final PreviewContainer pcontainer) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				c.addMessage("Report Execution Finished.");
				pcontainer.setNotRunning(true);
				boolean notprmfiled = !prmInput.checkFieldsFilled();
				if (notprmfiled) {
					c.addMessage("You have some input parameters, that you have to fill first");
					UIUtils.showWarning("You have some input parameters, that you have to fill first");
				}
				pcontainer.showParameters(notprmfiled);
			}
		});
	}

	private RecordCountScriptletFactory scfactory;

	private void runJob(final PreviewContainer pcontainer) {
		fillError = null;
		Job job = new Job(Messages.PreviewEditor_preview_a
				+ ": " + jrContext.getJasperDesign().getName() + Messages.PreviewEditor_preview_b) { //$NON-NLS-1$ 

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(jrContext.getClassLoader());
					final IFile file = ((IFileEditorInput) pcontainer.getEditorInput()).getFile();

					monitor.beginTask(Messages.PreviewEditor_starting, IProgressMonitor.UNKNOWN);

					setupFileRezolver(monitor, file);

					JasperDesign jd = ModelUtils.copyJasperDesign(jrContext.getJasperDesign());

					JasperReport jasperReport = compileJasperDesign(file, jd);

					if (jasperReport != null) {
						if (!prmInput.checkFieldsFilled())
							return Status.CANCEL_STATUS;

						setupDataAdapter(pcontainer);
						if (pcontainer.getMode().equals(RunStopAction.MODERUN_JIVE)) {
							runJive(pcontainer, file, jasperReport);
						} else {
							setupVirtualizer(jd);
							c.addMessage("Filling Report");

							setupRecordCounters();
							// We create the fillHandle to run the report based on the type of data adapter....
							AsynchronousFillHandle fh = AsynchronousFillHandle
									.createHandle(jrContext, jasperReport, jasperParameters);

							if (fillReport(fh, monitor, pcontainer) == Status.CANCEL_STATUS)
								return Status.CANCEL_STATUS;
						}
					}
				} catch (Throwable e) {
					c.addError(e);
				} finally {
					monitor.done();

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
		JettyUtil.startJetty(file.getProject(), jrContext);
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					Map<String, Object> prm = new HashMap<String, Object>();

					prm.put(SReportServlet.PRM_JRPARAMETERS, jasperParameters);
					prm.put(SReportServlet.PRM_JASPERREPORT, jasperReport);

					UUID randomUUID = UUID.randomUUID();
					Context.putContext(randomUUID.toString(), prm);

					String url = JettyUtil.getURL(file, randomUUID.toString(), jrContext);
					pcontainer.getJiveViewer().setURL(url);
				} catch (Exception e) {
					UIUtils.showError(e);
				}
			}
		});
	}

	private JasperReport compileJasperDesign(IFile file, JasperDesign jd) throws CoreException {
		stats.startCount(ST_COMPILATIONTIME);
		c.addMessage("Compiling");
		if (compiler == null) {
			compiler = new JasperReportCompiler();
			compiler.setErrorHandler(new JRErrorHandler(c));
			compiler.setProject(file.getProject());
		}
		JasperReport jasperReport = compiler.compileReport(jrContext, jd);// JasperCompileManager.getInstance(jrContext).compile(jd);
		stats.endCount(ST_COMPILATIONTIME);
		return jasperReport;
	}

	private void setupFileRezolver(IProgressMonitor monitor, IFile file) {
		jasperParameters.put(JRParameter.REPORT_FILE_RESOLVER, jrContext.getFileResolver());
	}

	private void setupVirtualizer(JasperDesign jd) {
		c.addMessage("Setting Virtualizer");
		VirtualizerHelper.setVirtualizer(jd, jrContext, jasperParameters);
	}

	private void setupDataAdapter(final PreviewContainer pcontainer) throws JRException {
		c.addMessage("Setting DataAdapter Connection");
		DataAdapterDescriptor daDesc = pcontainer.getDataAdapterDesc();
		if (daDesc != null)
			jasperParameters.put(DataAdapterParameterContributorFactory.PARAMETER_DATA_ADAPTER, daDesc.getDataAdapter());
	}

	private IStatus fillReport(AsynchronousFillHandle fh, IProgressMonitor monitor, final PreviewContainer pcontainer)
			throws JRException, InterruptedException {
		Assert.isTrue(fh != null);
		IProgressMonitor sm = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN,
				SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		IStatus retstatus = Status.OK_STATUS;
		try {
			sm.beginTask(Messages.PreviewEditor_fill_report, IProgressMonitor.UNKNOWN);
			fh.addFillListener((IJRPrintable) pcontainer.getDefaultViewer());
			fh.addFillListener(new FillListener() {
				private boolean refresh = false;

				@Override
				public void pageUpdated(JasperPrint arg0, int page) {
				}

				@Override
				public void pageGenerated(JasperPrint arg0, final int page) {
					if (page == 0)
						pcontainer.setJasperPrint(stats, arg0);
					if (refresh)
						return;
					refresh = true;
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							stats.endCount(ST_FILLINGTIME);
							stats.setValue(ST_PAGECOUNT, page);
							if (scfactory != null)
								stats.setValue(ST_RECORDCOUNTER, scfactory.getRecordCount());
							stats.endCount(ST_REPORTEXECUTIONTIME);
							c.setStatistics(stats);
							refresh = false;
						}
					});
				}
			});
			fh.addListener(new AsynchronousFilllListener() {

				public void reportFinished(final JasperPrint jPrint) {
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							stats.endCount(ST_FILLINGTIME);
							stats.setValue(ST_PAGECOUNT, jPrint.getPages().size());
							if (scfactory != null)
								stats.setValue(ST_RECORDCOUNTER, scfactory.getRecordCount());
							stats.endCount(ST_REPORTEXECUTIONTIME);
							pcontainer.setJasperPrint(stats, jPrint);
							finished = false;
						}
					});
				}

				public void reportFillError(Throwable t) {
					handleFillException(t);
				}

				public void reportCancelled() {
					c.addMessage(Messages.PreviewEditor_report_fill_canceled);
				}
			});
			stats.startCount(ST_FILLINGTIME);
			fh.startFill();
			finished = true;
			while (finished && fillError == null) {
				if (sm.isCanceled()) {
					fh.cancellFill();
					retstatus = Status.CANCEL_STATUS;
					break;
				}
				Thread.sleep(500);
				sm.worked(10);
			}
			if (fillError != null)
				throw new JRException(fillError);
		} finally {
			sm.done();
		}

		return retstatus;
	}

	private boolean finished = true;
	private Throwable fillError = null;

	private VParameters prmInput;

	private Statistics stats;

	public static final String ST_REPORTSIZE = "REPORTSIZE";

	public static final String ST_EXPORTTIME = "ST_EXPORTTIME";

	private JasperReportCompiler compiler;

	private void handleFillException(Throwable t) {
		fillError = t;
	}

	protected void setupRecordCounters() {
		List<ScriptletFactory> extensions = new ArrayList<ScriptletFactory>();
		scfactory = new RecordCountScriptletFactory();
		extensions.add(scfactory);
		jrContext.setExtensions(ScriptletFactory.class, extensions);
	}
}
