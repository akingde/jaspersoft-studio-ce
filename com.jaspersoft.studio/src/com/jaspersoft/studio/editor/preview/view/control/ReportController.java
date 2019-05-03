/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.jaspersoft.studio.editor.preview.stats.RecordCountScriptletFactory;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.execution.VirtualizerHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
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
import net.sf.jasperreports.engine.fill.JasperReportSource;
import net.sf.jasperreports.engine.fill.SimpleJasperReportSource;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.repo.SimpleRepositoryResourceContext;

public class ReportController {

	public static final String ST_RECORDCOUNTER = "RECORDCOUNTER"; //$NON-NLS-1$

	public static final String ST_PAGECOUNT = "PAGECOUNT"; //$NON-NLS-1$

	public static final String ST_FILLINGTIME = "FILLINGTIME"; //$NON-NLS-1$

	public static final String ST_COMPILATIONTIME = "COMPILATIONTIME"; //$NON-NLS-1$
	public static final String ST_COMPILATIONTIMESUBREPORT = "COMPILATIONTIMESUBREPORT"; //$NON-NLS-1$

	public static final String ST_REPORTEXECUTIONTIME = "REPORTEXECUTIONTIME"; //$NON-NLS-1$

	public static final String ST_RUNTIMESTAMP = "RUNTIMESTAMP"; //$NON-NLS-1$
	public static final String ST_SNAPSHOT = "SNAPSHOT"; //$NON-NLS-1$
	public static final String ST_SNAPSHOT_FILE = "SNAPSHOT_FILE"; //$NON-NLS-1$

	public static final String FORM_SORTING = "report_configuration_sorting"; //$NON-NLS-1$
	public static final String FORM_BOOKMARKS = "report_configuration_bookmarks"; //$NON-NLS-1$
	public static final String FORM_EXPORTER = "report_configuration_exporterParameters"; //$NON-NLS-1$

	public static final String FORM_REPORT_PARAMETERS = "report_configuration_reportParameters"; //$NON-NLS-1$
	public static final String FORM_PARAMETERS = "report_configuration_inputParameters"; //$NON-NLS-1$

	public static final List<IDataInput> inputs = new ArrayList<>();

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
	private static Map<String, IReportRunner> runners;

	public static Map<String, IReportRunner> getRunners() {
		if (runners == null) {
			runners = new HashMap<>();
			for (IReportRunner r : JaspersoftStudioPlugin.getExtensionManager().getReportRunners())
				runners.put(r.getID(), r);
		}
		return runners;
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
	}

	public void resetParametersToDefault() {
		try {
			Map<String, Object> prms = jrContext.getJRParameters();
			if (prms == null) {
				prms = new HashMap<>();
				jrContext.setJRParameters(prms);
			}
			for (JRParameter p : jrContext.getJasperDesign().getParameters()) {
				if (p.isSystemDefined())
					continue;
				prms.remove(p.getName());
			}
			ExpressionUtil.initBuiltInParameters(jrContext, jasperReport);
		} catch (Throwable e) {
			c.showConsole();
			c.clearConsole();
			c.addError(e, jrContext.getJasperDesign());
		}
		Set<String> toDel = new HashSet<>();
		for (Map.Entry<String, Object> entry : jasperParameters.entrySet())
			if (entry.getValue() == null)
				toDel.add(entry.getKey());
		for (String key : toDel)
			jasperParameters.remove(key);

		prmInput.update();
		prmRepInput.update();
	}

	private void setParameters() {
		jasperParameters = resetParameters(jasperParameters, jrContext);
	}

	public static Map<String, Object> resetParameters(Map<String, Object> jrPrms,
			JasperReportsConfiguration jrContext) {
		Map<String, Object> existing = jrContext.getJRParameters();
		if (jrPrms == null) {
			jrPrms = new HashMap<>();
			if (existing != null)
				jrPrms.putAll(jrContext.getJRParameters());
			setDefaultParameterValues(jrPrms, jrContext);
		} else {
			if (existing != null)
				jrPrms.putAll(jrContext.getJRParameters());
			Map<String, Object> map = new HashMap<>();
			if (jrContext.getJasperDesign() != null) {
				List<JRParameter> prm = jrContext.getJasperDesign().getParametersList();
				for (JRParameter p : prm) {
					Object obj = jrPrms.get(p.getName());
					if (keepParameter(p.getName()))
						continue;
					try {
						if (obj != null && p.getValueClass().isAssignableFrom(obj.getClass()) && p.isForPrompting()) {
							map.put(p.getName(), obj);
						}
					} catch (Exception e) {
					}
				}
			}
			jrPrms.clear();
			jrPrms.putAll(map);
		}
		jrPrms.remove(JRParameter.REPORT_MAX_COUNT);
		jrContext.setJRParameters(jrPrms);
		return jrPrms;
	}

	private static boolean keepParameter(String name) {
		return (name.endsWith(JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX)
				|| name.equals(JRParameter.REPORT_DATA_SOURCE) || name.equals(JRParameter.REPORT_CONNECTION)
				|| name.startsWith("XML_") || name.startsWith("MONDRIAN_") //$NON-NLS-1$
				|| name.startsWith("XLSX_") || name.startsWith("XLS_") //$NON-NLS-1$
				|| name.startsWith("JSON_") || name.startsWith("HIBERNATE_") //$NON-NLS-1$
				|| name.startsWith("JPA_") || name.startsWith("CSV_") //$NON-NLS-1$
				|| name.contains("csv.source") || name.startsWith("XMLA_")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static void setDefaultParameterValues(Map<String, Object> jasperParameters,
			JasperReportsConfiguration jrContext) {
		jasperParameters.remove(JRDesignParameter.IS_IGNORE_PAGINATION);
		jasperParameters.remove(JRDesignParameter.REPORT_MAX_COUNT);
		jasperParameters.remove(JRDesignParameter.REPORT_LOCALE);
		jasperParameters.remove(JRDesignParameter.REPORT_TIME_ZONE);
	}

	public LinkedHashMap<String, APreview> createControls(Composite composite) {
		viewmap = new LinkedHashMap<>();
		// pass the parameter tabs the shared property change notifier
		PropertyChangeNotifier sharedNotifier = new PropertyChangeNotifier();
		VParameters vprm = new VParameters(composite, jrContext, sharedNotifier);
		vprm.setPcontainer(pcontainer);
		viewmap.put(FORM_PARAMETERS, vprm);
		VReportParameters vrprm = new VReportParameters(composite, jrContext, sharedNotifier);
		vrprm.setPcontainer(pcontainer);
		viewmap.put(FORM_REPORT_PARAMETERS, vrprm);
		viewmap.put(FORM_SORTING, new VSorting(composite, jrContext));
		viewmap.put(FORM_BOOKMARKS, new VBookmarks(composite, jrContext, pcontainer));
		viewmap.put(FORM_EXPORTER, new VExporter(composite, jrContext));

		return viewmap;
	}

	private VReportParameters prmRepInput;

	private void fillForms() {
		UIUtils.getDisplay().syncExec(() -> {
			prmInput = (VParameters) viewmap.get(FORM_PARAMETERS);
			prmInput.createInputControls(prompts, jasperParameters);
		});
		prmInput.setDirty(false);

		UIUtils.getDisplay().asyncExec(() -> {
			prmRepInput = (VReportParameters) viewmap.get(FORM_REPORT_PARAMETERS);
			prmRepInput.createInputControls(prompts, jasperParameters);
			prmRepInput.setDirty(false);

			VSorting vs = (VSorting) viewmap.get(FORM_SORTING);
			vs.setJasperReports(jrContext.getJasperDesign(), prompts, jasperParameters);
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
		UIUtils.getDisplay().asyncExec(() -> {
			pcontainer.setNotRunning(true);
			if (pcontainer.getSite() instanceof MultiPageEditorSite) {
				MultiPageEditorPart mpe = ((MultiPageEditorSite) pcontainer.getSite()).getMultiPageEditor();
				IEditorPart[] editors = mpe.findEditors(mpe.getEditorInput());
				if (editors != null && editors.length > 0) {
					// Dialog, if not ..., it's not clear for the user that
					// error happened
					UIUtils.showInformation(Messages.ReportControler_compilationerrors);

					mpe.setActiveEditor(editors[0]);
				}
			}
		});
	}

	public static void finishCompiledReport(final Console c, final AVParameters prmInput,
			final PreviewJRPrint pcontainer) {
		UIUtils.getDisplay().syncExec(() -> {
			if (!(pcontainer instanceof PreviewContainer
					&& runners.get(((PreviewContainer) pcontainer).getMode()) != null))
				c.addMessage(Messages.ReportControler_msg_reportfinished);
			pcontainer.setNotRunning(true);
			boolean notprmfiled = prmInput != null && !prmInput.checkFieldsFilled();
			if (notprmfiled) {
				c.addMessage(Messages.ReportControler_msg_fillparameters);
				VSimpleErrorPreview errorView = showErrorView(pcontainer);
				errorView.setMessage(Messages.ReportControler_msg_fillparameters);
				prmInput.setFocus();
				prmInput.setDirty(true);
			}
			if (pcontainer.isHideParameters() && pcontainer instanceof IParametrable)
				((IParametrable) pcontainer).showParameters(notprmfiled);
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
							Map<String, Object> oldm = new HashMap<>(jrContext.getJRParameters());
							try {
								ExpressionUtil.initBuiltInParameters(jrContext, jasperReport);
							} catch (Throwable e) {
								c.showConsole();
								c.clearConsole();
								c.addError(e, jrContext.getJasperDesign());
							}
							Map<String, Object> pmap = jrContext.getJRParameters();
							for (Map.Entry<String, Object> entry : oldm.entrySet())
								if (pmap.containsKey(entry.getKey()) || keepParameter(entry.getKey()))
									pmap.put(entry.getKey(), entry.getValue());
							if (viewmap != null)
								fillForms();
						}
						pcontainer.setRunDirty(false);
						c.startMessage(Messages.PreviewEditor_starting);
						if (!prmInput.checkFieldsFilled())
							return Status.CANCEL_STATUS;
						IReportRunner runner = runners.get(pcontainer.getMode());
						if (runner == null)
							setupDataAdapter(pcontainer);

						// remove parameters that are not for prompting
						Set<String> toRemove = new HashSet<>();
						for (String key : jasperParameters.keySet()) {
							JRParameter p = jd.getParametersMap().get(key);
							if (p != null && !p.isSystemDefined() && !p.isForPrompting())
								toRemove.add(p.getName());
						}
						for (String key : toRemove)
							jasperParameters.remove(key);

						if (runner != null) {
							runner.run(pcontainer, file, jasperReport, jrContext, jasperParameters, monitor);
							return Status.OK_STATUS;
						} else {
							setupVirtualizer(jd);
							setupRecordCounters();
							setupDataSnapshot();

							c.startMessage(Messages.ReportControler_msg_fillreports);

							JaspersoftStudioPlugin.getExtensionManager().onRun(jrContext, jasperReport,
									jasperParameters);

							// We create the fillHandle to run the report based
							// on the type of data adapter....
							String projectPath = file.getProject().getLocation().toFile().getAbsolutePath();
							String reportPath = file.getParent().getLocation().toFile().getAbsolutePath();
							SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(reportPath);
							JasperReportSource jrSource = SimpleJasperReportSource.from(jasperReport, projectPath,
									context);
							AsynchronousFillHandle fh = AsynchronousFillHandle.createHandle(jrContext, jrSource,
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

	private JasperReport compileJasperDesign(IFile file, JasperDesign jd, IProgressMonitor monitor)
			throws CoreException {
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
			jrContext.getPropertyChangeSupport().addPropertyChangeListener(evt -> compiler.clean());
		} else
			((JRErrorHandler) compiler.getErrorHandler()).reset();
		JasperReport jr = compiler.compileReport(jrContext, jd, monitor);
		stats.endCount(ST_COMPILATIONTIME);
		if (((JRErrorHandler) compiler.getErrorHandler()).hasErrors()) {
			UIUtils.getDisplay().syncExec(() -> {
				VSimpleErrorPreview errorView = showErrorView(pcontainer);
				errorView.setMessage(Messages.ReportControler_compilationerrors);
			});
		}
		doneMessage();
		return jr;
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
			jasperParameters.put(DataAdapterParameterContributorFactory.PARAMETER_DATA_ADAPTER,
					daDesc.getDataAdapter());
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
		private boolean viewerset = false;

		@Override
		public void pageUpdated(JasperPrint arg0, int page) {
			// do nothing
		}

		@Override
		public void pageGenerated(final JasperPrint arg0, final int page) {
			this.jrPrint = arg0;
			if (page == 0) {
				UIUtils.getDisplay().syncExec(() -> {
					pcontainer.getRightContainer().switchView(stats, pcontainer.getDefaultViewer());
					viewerset = true;
				});
			}
			if (refresh)
				return;
			refresh = true;
			UIUtils.getDisplay().asyncExec(() -> {
				stats.endCount(ST_FILLINGTIME);
				stats.setValue(ST_PAGECOUNT, page);
				if (scfactory != null)
					stats.setValue(ST_RECORDCOUNTER, scfactory.getRecordCount());
				stats.endCount(ST_REPORTEXECUTIONTIME);
				c.setStatistics(stats);
				refresh = false;
			});
		}

		public void reportFinished(final JasperPrint jPrint) {
			if (!viewerset)
				UIUtils.getDisplay().syncExec(
						() -> pcontainer.getRightContainer().switchView(stats, pcontainer.getDefaultViewer()));
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
		scfactory = new RecordCountScriptletFactory();
		jrContext.setExtensions(ScriptletFactory.class, Collections.singletonList(scfactory));
	}

	private void finishUpdateViewer(final PreviewContainer pcontainer, final JasperPrint jPrint) {
		UIUtils.getDisplay().asyncExec(() -> {
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
		});
	}

	protected APreview getDefaultViewer() {
		return pcontainer.getDefaultViewer();
	}

	protected void setupDataSnapshot() {
		Date creationTimestamp = new Date();
		ReportContext rc = (ReportContext) jasperParameters.get(JRParameter.REPORT_CONTEXT);
		if (rc instanceof SimpleReportContext) {
			DataCacheHandler dch = (DataCacheHandler) rc
					.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
			String msg = "No";
			if (dch != null && dch.getDataSnapshot() != null) {
				msg = "Yes";
				if (dch instanceof JSSColumnDataCacheHandler)
					creationTimestamp = ((JSSColumnDataCacheHandler) dch).getCreationTimestamp();
				if (dch.isSnapshotPopulated())
					jasperParameters.remove(DataAdapterParameterContributorFactory.PARAMETER_DATA_ADAPTER);
			}
			if (rc.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT) != null)
				stats.setValue(ST_SNAPSHOT_FILE, rc.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT));
			stats.setValue(ST_SNAPSHOT, msg);
		} else if (jasperReport.getProperty(DataSnapshotManager.SAVE_SNAPSHOT) != null) {
			String fname = jasperReport.getProperty(DataSnapshotManager.SAVE_SNAPSHOT);
			if (!Misc.isNullOrEmpty(fname)) {
				InputStream in = null;
				try {
					in = RepositoryUtil.getInstance(jrContext).getInputStreamFromLocation(fname);
					if (in != null) {
						DataSnapshotManager.setCaching(jrContext.getJRParameters(), true);
						DataSnapshotManager.loadSnapshot(jrContext, fname);
						setupDataSnapshot();
					}
				} catch (JRException e) {
				} finally {
					FileUtils.closeStream(in);
				}
			}
		}
		stats.setValue(ST_RUNTIMESTAMP, creationTimestamp.toString());
	}

	public static void showRunReport(Console c, final PreviewJRPrint pcontainer, final Throwable e) {
		showRunReport(c, pcontainer, e, null);
	}

	public static void showRunReport(Console c, final PreviewJRPrint pcontainer, final Throwable e,
			final JasperDesign design) {
		c.addError(e, design);
		UIUtils.getDisplay().syncExec(() -> {
			VSimpleErrorPreview errorView = showErrorView(pcontainer);
			errorView.setMessage(Messages.ReportControler_generatingerror);
		});
	}

	protected static VSimpleErrorPreview showErrorView(PreviewJRPrint pcontainer) {
		VSimpleErrorPreview errorView = pcontainer.getErrorView();
		pcontainer.getRightContainer().switchView(null, errorView);
		return errorView;
	}
}
