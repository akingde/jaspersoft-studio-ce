/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.DataAdapterParameterContributorFactory;
import com.jaspersoft.studio.editor.preview.IParametrable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.datasnapshot.DataSnapshotManager;
import com.jaspersoft.studio.editor.preview.datasnapshot.JSSColumnDataCacheHandler;
import com.jaspersoft.studio.editor.preview.input.BigNumericInput;
import com.jaspersoft.studio.editor.preview.input.BooleanInput;
import com.jaspersoft.studio.editor.preview.input.DateInput;
import com.jaspersoft.studio.editor.preview.input.EnumInput;
import com.jaspersoft.studio.editor.preview.input.FileInput;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ImageInput;
import com.jaspersoft.studio.editor.preview.input.LocaleInput;
import com.jaspersoft.studio.editor.preview.input.PatternInput;
import com.jaspersoft.studio.editor.preview.input.PropertyChangeNotifier;
import com.jaspersoft.studio.editor.preview.input.TextInput;
import com.jaspersoft.studio.editor.preview.input.TimeZoneInput;
import com.jaspersoft.studio.editor.preview.input.URLInput;
import com.jaspersoft.studio.editor.preview.input.array.CollectionInput;
import com.jaspersoft.studio.editor.preview.input.map.MapInput;
import com.jaspersoft.studio.editor.preview.jive.Context;
import com.jaspersoft.studio.editor.preview.jive.JettyUtil;
import com.jaspersoft.studio.editor.preview.stats.RecordCountScriptletFactory;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.editor.preview.view.report.html.ABrowserViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.execution.VirtualizerHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.SimpleReportContext;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.AsynchronousFilllListener;
import net.sf.jasperreports.engine.fill.FillListener;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;

public class ReportController {

	public static final String ST_RECORDCOUNTER = "RECORDCOUNTER"; //$NON-NLS-1$

	public static final String ST_PAGECOUNT = "PAGECOUNT"; //$NON-NLS-1$

	public static final String ST_FILLINGTIME = "FILLINGTIME"; //$NON-NLS-1$

	public static final String ST_COMPILATIONTIME = "COMPILATIONTIME"; //$NON-NLS-1$
	public static final String ST_COMPILATIONTIMESUBREPORT = "COMPILATIONTIMESUBREPORT"; //$NON-NLS-1$

	public static final String ST_REPORTEXECUTIONTIME = "REPORTEXECUTIONTIME"; //$NON-NLS-1$

	public static final String ST_RUNTIMESTAMP = "RUNTIMESTAMP"; //$NON-NLS-1$
	public static final String ST_SNAPSHOT = "SNAPSHOT"; //$NON-NLS-1$

	public static final String FORM_SORTING = "report_configuration_sorting"; //$NON-NLS-1$
	public static final String FORM_BOOKMARKS = "report_configuration_bookmarks"; //$NON-NLS-1$
	public static final String FORM_EXPORTER = "report_configuration_exporterParameters"; //$NON-NLS-1$

	public static final String FORM_REPORT_PARAMETERS = "report_configuration_reportParameters"; //$NON-NLS-1$
	public static final String FORM_PARAMETERS = "report_configuration_inputParameters"; //$NON-NLS-1$

	public static List<IDataInput> inputs = new ArrayList<IDataInput>();

	static {
		inputs.add(new BooleanInput());
		inputs.add(new TextInput());
		inputs.add(new BigNumericInput());
		inputs.add(new DateInput());
		inputs.add(new LocaleInput());
		inputs.add(new FileInput());
		inputs.add(new URLInput());
		inputs.add(new PatternInput());
		inputs.add(new TimeZoneInput());
		inputs.add(new ImageInput());
		inputs.add(new CollectionInput());
		inputs.add(new MapInput());
		inputs.add(new EnumInput());
		List<IDataInput> ict = JaspersoftStudioPlugin.getInputControlTypeManager().getInputControlTypes();
		if (ict != null && !ict.isEmpty())
			inputs.addAll(ict);
	}

	private List<JRParameter> prompts;
	private Map<String, Object> jasperParameters;
	private LinkedHashMap<String, APreview> viewmap;
	private PreviewContainer pcontainer;
	private JasperReportsConfiguration jrContext;

	public ReportController(PreviewContainer pcontainer, JasperReportsConfiguration jrContext) {
		this.pcontainer = pcontainer;
		setJrContext(jrContext);
	}

	public JasperReportsConfiguration getJrContext() {
		return jrContext;
	}

	public void setJrContext(JasperReportsConfiguration jrContext) {
		this.jrContext = jrContext;
		if (jrContext.getJasperDesign() != null)
			prompts = jrContext.getJasperDesign().getParametersList();
		setParameters();
		// ExpressionUtil.initBuiltInParameters(jrContext);
		// if (viewmap != null)
		// fillForms();
	}

	public void resetParametersToDefault() {
		ExpressionUtil.initBuiltInParameters(jrContext, jasperReport);
		Set<String> toDel = new HashSet<String>();
		for (String key : jasperParameters.keySet())
			if (jasperParameters.get(key) == null)
				toDel.add(key);
		for (String key : toDel)
			jasperParameters.remove(key);

		prmInput.update();
		prmRepInput.update();
		//
		//
		// prmInput.setupDefaultValues();
		// prmRepInput.setupDefaultValues();
	}

	private void setParameters() {
		jasperParameters = resetParameters(jasperParameters, jrContext);
	}

	public static Map<String, Object> resetParameters(Map<String, Object> jasperParameters,
			JasperReportsConfiguration jrContext) {
		jasperParameters = jrContext.getJRParameters();
		if (jasperParameters == null) {
			jasperParameters = new HashMap<String, Object>();
			setDefaultParameterValues(jasperParameters, jrContext);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			if (jrContext.getJasperDesign() != null) {
				List<JRParameter> prm = jrContext.getJasperDesign().getParametersList();
				for (JRParameter p : prm) {
					Object obj = jasperParameters.get(p.getName());
					if (p.getName().endsWith(JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX))
						continue;
					if (p.getName().equals(JRParameter.REPORT_DATA_SOURCE))
						continue;
					if (p.getName().equals(JRParameter.REPORT_CONNECTION))
						continue;
					if (p.getName().startsWith("XML_") || p.getName().startsWith("MONDRIAN_") //$NON-NLS-1$
							|| p.getName().startsWith("XLSX_") || p.getName().startsWith("XLS_") //$NON-NLS-1$
							|| p.getName().startsWith("JSON_") || p.getName().startsWith("HIBERNATE_") //$NON-NLS-1$
							|| p.getName().startsWith("JPA_") || p.getName().startsWith("CSV_") //$NON-NLS-1$
							|| p.getName().contains("csv.source") || p.getName().startsWith("XMLA_")) //$NON-NLS-1$
						continue;
					try {
						if (obj != null && p.getValueClass().isAssignableFrom(obj.getClass()) && p.isForPrompting()) {
							map.put(p.getName(), obj);
						}
					} catch (Exception e) {
					}
				}
			}
			jasperParameters.clear();
			jasperParameters.putAll(map);
		}
		jasperParameters.remove(JRParameter.REPORT_MAX_COUNT);
		jrContext.setJRParameters(jasperParameters);
		return jasperParameters;
	}

	private static void setDefaultParameterValues(Map<String, Object> jasperParameters,
			JasperReportsConfiguration jrContext) {
		jasperParameters.remove(JRDesignParameter.IS_IGNORE_PAGINATION);
		jasperParameters.remove(JRDesignParameter.REPORT_MAX_COUNT);
		jasperParameters.remove(JRDesignParameter.REPORT_LOCALE);
		jasperParameters.remove(JRDesignParameter.REPORT_TIME_ZONE);
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite) {
		viewmap = new LinkedHashMap<String, APreview>();
		//pass the parameter tabs the shared property change notifier
		PropertyChangeNotifier sharedNotifier = new PropertyChangeNotifier();
		viewmap.put(FORM_PARAMETERS, new VParameters(composite, jrContext, sharedNotifier));
		viewmap.put(FORM_REPORT_PARAMETERS, new VReportParameters(composite, jrContext, sharedNotifier));
		viewmap.put(FORM_SORTING, new VSorting(composite, jrContext));
		viewmap.put(FORM_BOOKMARKS, new VBookmarks(composite, jrContext, pcontainer));
		viewmap.put(FORM_EXPORTER, new VExporter(composite, jrContext));
		return viewmap;
	}

	private VReportParameters prmRepInput;

	private void fillForms() {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				prmInput = (VParameters) viewmap.get(FORM_PARAMETERS);
				prmInput.createInputControls(prompts, jasperParameters);
			}
		});
		prmInput.setDirty(false);

		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				prmRepInput = (VReportParameters) viewmap.get(FORM_REPORT_PARAMETERS);
				prmRepInput.createInputControls(prompts, jasperParameters);
				prmRepInput.setDirty(false);

				VSorting vs = (VSorting) viewmap.get(FORM_SORTING);
				vs.setJasperReports(jrContext.getJasperDesign(), prompts, jasperParameters);
			}
		});

	}

	public void viewerChanged(APreview view) {
		VExporter vs = (VExporter) viewmap.get(FORM_EXPORTER);
		vs.setPreferencesPage(view);
	}

	private Console c;

	public void runReport() {
		VSimpleErrorPreview errorView = showErrorView(pcontainer);
		errorView.setMessage(Messages.ReportControler_generating);
		c = pcontainer.getConsole();

		c.showConsole();
		c.clearConsole();
		if (pcontainer.getMode().equals(RunStopAction.MODERUN_LOCAL))
			pcontainer.setJasperPrint(null, null);
		// jasperParameters.clear();
		fillError = null;
		jasperReport = null;
		stats = new Statistics();
		stats.startCount(ST_REPORTEXECUTIONTIME);
		c.addMessage(Messages.ReportControler_stats_start);

		pcontainer.setNotRunning(false);
		runJob(pcontainer);
	}

	public void finishReport(final PreviewContainer pcontainer) {
		if (compiler != null && ((JRErrorHandler) compiler.getErrorHandler()).hasErrors())
			finishNotCompiledReport();
		else
			finishCompiledReport(c, prmInput, pcontainer);
	}

	private void finishNotCompiledReport() {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			public void run() {
				pcontainer.setNotRunning(true);
				if (pcontainer.getSite() instanceof MultiPageEditorSite) {
					MultiPageEditorPart mpe = ((MultiPageEditorSite) pcontainer.getSite()).getMultiPageEditor();
					IEditorPart[] editors = mpe.findEditors(mpe.getEditorInput());
					if (editors != null && editors.length > 0) {
						// Dialog, if not ..., it's not clear for the user that error happened
						UIUtils.showInformation(Messages.ReportControler_compilationerrors);

						mpe.setActiveEditor(editors[0]);
					}
				}
			}
		});
	}

	public static void finishCompiledReport(final Console c, final AVParameters prmInput,
			final PreviewJRPrint pcontainer) {
		UIUtils.getDisplay().syncExec(new Runnable() {

			public void run() {
				c.addMessage(Messages.ReportControler_msg_reportfinished);
				pcontainer.setNotRunning(true);
				boolean notprmfiled = prmInput != null && !prmInput.checkFieldsFilled();
				if (notprmfiled) {
					c.addMessage(Messages.ReportControler_msg_fillparameters);
					VSimpleErrorPreview errorView = showErrorView(pcontainer);
					errorView.setMessage(Messages.ReportControler_msg_fillparameters);
					// UIUtils.showWarning(Messages.ReportControler_msg_fillparameters);
					prmInput.setFocus();
					prmInput.setDirty(true);
				}
				if (pcontainer.isHideParameters() && pcontainer instanceof IParametrable)
					((IParametrable) pcontainer).showParameters(notprmfiled);
			}
		});
		DataSnapshotManager.saveSnapshotIfExists(pcontainer.getJrContext().getJRParameters());
	}

	private JasperReport jasperReport;
	private RecordCountScriptletFactory scfactory;

	private void runJob(final PreviewContainer pcontainer) {
		fillError = null;
		Job job = new Job(Messages.PreviewEditor_preview_a + ": " + jrContext.getJasperDesign().getName() //$NON-NLS-1$
				+ Messages.PreviewEditor_preview_b) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
				JasperDesign jd = null;
				try {
					Thread.currentThread().setContextClassLoader(jrContext.getClassLoader());

					// setParameters();

					IEditorInput editorInput = pcontainer.getEditorInput();
					IFile file = null;
					if (editorInput instanceof IFileEditorInput) {
						file = ((IFileEditorInput) editorInput).getFile();
					}
					monitor.beginTask("Form initialisation", IProgressMonitor.UNKNOWN);

					jd = jrContext.getJasperDesign();

					if (jasperReport == null)
						jasperReport = compileJasperDesign(file, jd, monitor);
					if (jasperReport != null) {
						if (pcontainer.isRunDirty() || prmInput == null) {
							ExpressionUtil.initBuiltInParameters(jrContext, jasperReport);
							if (viewmap != null)
								fillForms();
						}
						pcontainer.setRunDirty(false);
						c.startMessage(Messages.PreviewEditor_starting);
						if (!prmInput.checkFieldsFilled())
							return Status.CANCEL_STATUS;

						setupDataAdapter(pcontainer);

						// remove parameters that are not for prompting
						Set<String> toRemove = new HashSet<String>();
						for (String key : jasperParameters.keySet()) {
							JRParameter p = jd.getParametersMap().get(key);
							if (p != null && !p.isSystemDefined() && !p.isForPrompting())
								toRemove.add(p.getName());
						}
						for (String key : toRemove)
							jasperParameters.remove(key);

						if (pcontainer.getMode().equals(RunStopAction.MODERUN_JIVE)) {
							runJive(pcontainer, file, jasperReport);
						} else {
							setupVirtualizer(jd);
							c.startMessage(Messages.ReportControler_msg_fillreports);

							setupRecordCounters();
							JaspersoftStudioPlugin.getExtensionManager().onRun(jrContext, jasperReport, jasperParameters);

							setupDataSnapshot();
							// We create the fillHandle to run the report based on the type of data adapter....
							AsynchronousFillHandle fh = AsynchronousFillHandle.createHandle(jrContext, jasperReport,
									new HashMap<String, Object>(jasperParameters));

							if (fillReport(fh, monitor, pcontainer) == Status.CANCEL_STATUS) {
								cancelMessage();
								return Status.CANCEL_STATUS;
							}
							doneMessage();
						}
					}
				} catch (final Throwable e) {
					errorMessage();
					showRunReport(c, pcontainer, e, jd);
				} finally {
					Thread.currentThread().setContextClassLoader(oldLoader);
					monitor.done();
					finishReport(pcontainer);
				}
				return Status.OK_STATUS;
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	protected void runJive(final PreviewContainer pcontainer, final IFile file, final JasperReport jasperReport) {
		JettyUtil.startJetty(file.getProject(), jrContext);
		UIUtils.getDisplay().syncExec(new Runnable() {

			public void run() {
				try {
					Map<String, Object> prm = new HashMap<String, Object>();

					prm.put(JettyUtil.PRM_JRPARAMETERS, jasperParameters);
					prm.put(JettyUtil.PRM_JASPERREPORT, jasperReport);

					UUID randomUUID = UUID.randomUUID();
					Context.putContext(randomUUID.toString(), prm);

					String url = JettyUtil.getURL(file, randomUUID.toString(), jrContext);
					ABrowserViewer jiveViewer = pcontainer.getJiveViewer();
					jiveViewer.setURL(url);
					pcontainer.getRightContainer().switchView(null, jiveViewer);

				} catch (Throwable e) {
					UIUtils.showError(e);
				}
			}
		});
	}

	private JasperReport compileJasperDesign(IFile file, JasperDesign jd, IProgressMonitor monitor) throws CoreException {
		// stats.startCount(ST_COMPILATIONTIMESUBREPORT);
		// CompileAction.doRun(jrContext, monitor, false);
		// stats.endCount(ST_COMPILATIONTIMESUBREPORT);
		stats.startCount(ST_COMPILATIONTIME);
		c.startMessage(Messages.ReportControler_msg_compiling);
		if (compiler == null) {
			compiler = new JasperReportCompiler();
			compiler.setErrorHandler(new JRMarkerErrorHandler(c, file));
			IProject project = (IProject) jrContext.get(FileUtils.KEY_IPROJECT);
			if (file != null) {
				project = file.getProject();
			}
			compiler.setProject(project);
			jrContext.getPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					compiler.clean();
				}
			});
		} else
			((JRErrorHandler) compiler.getErrorHandler()).reset();
		JasperReport jasperReport = compiler.compileReport(jrContext, jd);// JasperCompileManager.getInstance(jrContext).compile(jd);
		stats.endCount(ST_COMPILATIONTIME);
		if (((JRErrorHandler) compiler.getErrorHandler()).hasErrors()) {
			UIUtils.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					VSimpleErrorPreview errorView = showErrorView(pcontainer);
					errorView.setMessage(Messages.ReportControler_compilationerrors);
				}
			});
		}
		doneMessage();
		return jasperReport;
	}

	public void cancelMessage() {
		c.addMessage(Messages.ReportControler_CANCELED);
	}

	public void doneMessage() {
		c.addMessage(Messages.ReportControler_done);
	}

	public void errorMessage() {
		c.addMessage(Messages.ReportControler_error);
	}

	private void setupVirtualizer(JasperDesign jd) {
		c.startMessage(Messages.ReportControler_msg_setvirtualizer);
		VirtualizerHelper.setVirtualizer(jd, jrContext, jasperParameters);
		doneMessage();
	}

	private void setupDataAdapter(final PreviewContainer pcontainer) throws JRException {
		c.startMessage(Messages.ReportControler_msg_setdataadapter);
		DataAdapterDescriptor daDesc = pcontainer.getDataAdapterDesc();
		if (daDesc != null)
			jasperParameters.put(DataAdapterParameterContributorFactory.PARAMETER_DATA_ADAPTER, daDesc.getDataAdapter());
		doneMessage();
	}

	public void stop() {
		if (pmonitor != null)
			pmonitor.setCanceled(true);
	}

	private IStatus fillReport(final AsynchronousFillHandle fh, IProgressMonitor monitor,
			final PreviewContainer pcontainer) throws Throwable {
		Assert.isTrue(fh != null);
		pmonitor = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN,
				SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		IStatus retstatus = Status.OK_STATUS;
		try {
			pmonitor.beginTask(Messages.PreviewEditor_fill_report, IProgressMonitor.UNKNOWN);
			fh.addFillListener((IJRPrintable) getDefaultViewer());
			PageGenerationListener pgListener = new PageGenerationListener();
			fh.addFillListener(pgListener);
			fh.addListener(pgListener);
			stats.startCount(ST_FILLINGTIME);
			fh.startFill();
			finished = true;
			while (finished && fillError == null) {
				if (pmonitor.isCanceled()) {
					fh.cancellFill();
					retstatus = Status.CANCEL_STATUS;
					break;
				}
				Thread.sleep(500);
				pmonitor.worked(10);
			}
			if (fillError != null)
				throw new JRException(fillError);
		} catch (OutOfMemoryError e) {
			pcontainer.setJasperPrint(stats, null);
			throw e;
		} catch (Throwable e) {
			handleFillException(e);
			throw e;
		} finally {
			pmonitor.done();
		}

		return retstatus;
	}

	class PageGenerationListener implements AsynchronousFilllListener, FillListener {
		JasperPrint jrPrint;
		private boolean refresh = false;

		@Override
		public void pageUpdated(JasperPrint arg0, int page) {
		}

		@Override
		public void pageGenerated(final JasperPrint arg0, final int page) {
			this.jrPrint = arg0;
			if (page == 0) {
				UIUtils.getDisplay().syncExec(new Runnable() {
					public void run() {
						pcontainer.getRightContainer().switchView(stats, pcontainer.getDefaultViewer());
					}
				});
			}
			// pcontainer.setJasperPrint(stats, arg0);
			if (refresh)
				return;
			refresh = true;
			UIUtils.getDisplay().asyncExec(new Runnable() {

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

		public void reportFinished(final JasperPrint jPrint) {
			this.jrPrint = jPrint;
			finishUpdateViewer(pcontainer, jPrint);
		}

		public void reportFillError(Throwable t) {
			handleFillException(t);
		}

		public void reportCancelled() {
			if (jrPrint != null)
				finishUpdateViewer(pcontainer, jrPrint);
			c.addMessage(Messages.PreviewEditor_report_fill_canceled);
		}
	}

	private boolean finished = true;
	private Throwable fillError = null;

	private VParameters prmInput;

	private Statistics stats;

	public static final String ST_REPORTSIZE = "REPORTSIZE"; //$NON-NLS-1$

	public static final String ST_EXPORTTIME = "ST_EXPORTTIME"; //$NON-NLS-1$

	private JasperReportCompiler compiler;

	private IProgressMonitor pmonitor;

	private void handleFillException(Throwable t) {
		fillError = t;
	}

	protected void setupRecordCounters() {
		jrContext.setExtensions(ScriptletFactory.class, Collections.singletonList(new RecordCountScriptletFactory()));
	}

	private void finishUpdateViewer(final PreviewContainer pcontainer, final JasperPrint jPrint) {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				stats.endCount(ST_FILLINGTIME);
				if (jPrint != null)
					stats.setValue(ST_PAGECOUNT, jPrint.getPages().size());
				if (scfactory != null)
					stats.setValue(ST_RECORDCOUNTER, scfactory.getRecordCount());
				stats.endCount(ST_REPORTEXECUTIONTIME);
				APreview pv = getDefaultViewer();
				if (pv instanceof IJRPrintable)
					try {
						((IJRPrintable) pv).setJRPRint(stats, jPrint, true);
						VBookmarks vs = (VBookmarks) viewmap.get(FORM_BOOKMARKS);
						vs.setJasperPrint(jPrint);
					} catch (Exception e) {
						e.printStackTrace();
					}

				pcontainer.setJasperPrint(stats, jPrint);
				c.setStatistics(stats);
				finished = false;
			}
		});
	}

	protected APreview getDefaultViewer() {
		APreview pv = pcontainer.getDefaultViewer();
		return pv;
	}

	protected void setupDataSnapshot() {
		Date creationTimestamp = new Date();
		ReportContext rc = (ReportContext) jasperParameters.get(JRParameter.REPORT_CONTEXT);
		if (rc != null && rc instanceof SimpleReportContext) {
			DataCacheHandler dch = (DataCacheHandler) rc.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
			String msg = "No";
			if (dch != null && dch.getDataSnapshot() != null) {
				msg = "Yes";
				if (dch instanceof JSSColumnDataCacheHandler)
					creationTimestamp = ((JSSColumnDataCacheHandler) dch).getCreationTimestamp();
			}
			if (rc.containsParameter(DataSnapshotManager.SAVE_SNAPSHOT))
				msg += "   Data Snapshot Path: " + rc.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT);
			stats.setValue(ST_SNAPSHOT, msg);
		}
		stats.setValue(ST_RUNTIMESTAMP, creationTimestamp.toString());
	}

	public static void showRunReport(Console c, final PreviewJRPrint pcontainer, final Throwable e) {
		showRunReport(c, pcontainer, e, null);
	}

	public static void showRunReport(Console c, final PreviewJRPrint pcontainer, final Throwable e,
			final JasperDesign design) {
		c.addError(e, design);
		UIUtils.getDisplay().syncExec(new Runnable() {

			public void run() {
				VSimpleErrorPreview errorView = showErrorView(pcontainer);
				errorView.setMessage(Messages.ReportControler_generatingerror);
			}
		});
	}

	protected static VSimpleErrorPreview showErrorView(PreviewJRPrint pcontainer) {
		VSimpleErrorPreview errorView = pcontainer.getErrorView();
		pcontainer.getRightContainer().switchView(null, errorView);
		return errorView;
	}

	// private void refreshRightView() {
	// pcontainer.switchRightView(pcontainer.getDefaultViewer(), stats, pcontainer.getRightContainer());
	// }
}
