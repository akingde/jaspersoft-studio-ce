/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.pages.Pages;
import com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage;
import com.jaspersoft.studio.statistics.UsageStatisticsIDs;
import com.jaspersoft.studio.utils.Callback;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleReportExportConfiguration;

public abstract class AExportAction extends AReportViewerAction {

	private String[] filterNames;
	private String[] fileExtensions;
	private String defaultFileExtension;
	private String fileName;
	private String filterPath;
	private JasperReportsConfiguration jContext;
	private ExportMenuAction parentMenu;
	public static final String EXPPARAM_INDEX_PAGE = "expparam.index.page"; //$NON-NLS-1$
	public static final String EXPPARAM_OFFSET_Y = "expparam.offset.y"; //$NON-NLS-1$
	public static final String EXPPARAM_OFFSET_X = "expparam.offset.x"; //$NON-NLS-1$

	public AExportAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer);
		this.jContext = jContext;
		this.parentMenu = parentMenu;
	}

	public void setDefaultFileExtension(String defaultFileExtension) {
		this.defaultFileExtension = defaultFileExtension;
	}

	public String getDefaultFileExtension() {
		return defaultFileExtension;
	}

	protected void setFileExtensions() {
	}

	public void setFileExtensions(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public void setFilterNames(String[] filterNames) {
		this.filterNames = filterNames;
	}

	private static String getFileExtension(String fileName) {
		if (fileName != null) {
			int ind = fileName.lastIndexOf('.');
			if (ind != -1)
				return fileName.substring(ind + 1);
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilterPath() {
		return filterPath;
	}

	public void setFilterPath(String filterPath) {
		this.filterPath = filterPath;
	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(UIUtils.getShell(), SWT.SINGLE | SWT.SAVE);
		setFileExtensions();
		if (filterNames != null)
			dialog.setFilterNames(filterNames);
		if (fileExtensions != null)
			dialog.setFilterExtensions(fileExtensions);
		if (filterPath != null)
			dialog.setFilterPath(filterPath);
		if (fileName != null)
			dialog.setFileName(fileName);
		else
			dialog.setFileName(getReportViewer().getReport().getName());

		String filePath = dialog.open();
		if (filePath != null) {
			if (defaultFileExtension != null && fileExtensions != null) {
				String extension = getFileExtension(filePath);
				if (extension != null)
					for (String fe : fileExtensions) {
						if (fe.endsWith(extension)) {
							if (!filePath.toLowerCase().endsWith(defaultFileExtension.toLowerCase()))
								filePath += '.' + defaultFileExtension;
							break;
						}
					}
			}
			try {
				export(new File(filePath), new Callback<File>() {

					@Override
					public void completed(File value) {
						// Log the export statistics of the exported format
						JaspersoftStudioPlugin.getInstance().getUsageManager().audit(AExportAction.this.getClass().getName(),
								UsageStatisticsIDs.CATEGORY_EXPORT_FORMAT);
						if (jContext != null && jContext.getJasperDesign() != null) {
							String reportLanguage = jContext.getJasperDesign().getLanguage();
							if (reportLanguage != null) {
								JaspersoftStudioPlugin.getInstance().getUsageManager().audit(reportLanguage,
										UsageStatisticsIDs.CATEGORY_LANGUAGE);
							}
						}
					}
				});
			} catch (Throwable e) {
				UIUtils.showError(e);
			}
		}
		if (parentMenu != null)
			parentMenu.setDefaultAction(this);
	}

	/**
	 * Method called to execute the export operation. Typically this is called by the run method since it is executing the
	 * action
	 */
	protected void export(final File file, final Callback<File> callback) throws Exception {
		final JasperPrint jrPrint = getReportViewer().getReport();
		if (jrPrint == null || jrPrint.getPages() == null)
			return;
		Job job = new Job(Messages.AExportAction_exportreport) {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				doExport(file, jrPrint, monitor);
				UIUtils.getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						callback.completed(file);
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();
	}

	/**
	 * Method called to preview the report, this is a utility method provided from the action and can be called outside.
	 * Essentially a preview and an export operation are really similar since the preview export into a temp file and show
	 * it. But for this reason it need to do less checks (for file existing for example) and for this reason there are two
	 * separate methods to export or preview the report
	 */
	public void preview(final File file, final JasperPrint jrPrint, final Callback<File> callback) throws Exception {
		if (jrPrint == null || jrPrint.getPages() == null)
			return;
		Job job = new Job(Messages.AExportAction_exportreport) {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				doPreview(file, jrPrint, monitor);
				UIUtils.getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						callback.completed(file);
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();
	}

	/**
	 * see AExportAction:preview(File, JasperPrint, Callback<File>)
	 */
	public void preview(final File file, final Callback<File> callback) throws Exception {
		preview(file, getReportViewer().getReport(), callback);
	}

	@Override
	protected boolean calculateEnabled() {
		return getReportViewer().hasReport();
	}

	protected void exportWithProgress(File file, JRExportProgressMonitor monitor) throws Throwable {
		JRAbstractExporter<?, ?, ?, ?> exporter = getExporter(jContext, monitor, file);
		exporter.setExporterInput(new SimpleExporterInput(getReportViewer().getReport()));

		exporter.exportReport();
	}

	protected void setupReportConfiguration(SimpleReportExportConfiguration conf, JRExportProgressMonitor monitor) {
		conf.setProgressMonitor(monitor);

		String indPage = jContext.getProperty(AExportAction.EXPPARAM_INDEX_PAGE);
		if (Misc.isNullOrEmpty(indPage)) {
			Pages p = new Pages().parseString(indPage);
			if (p.getPage() != null)
				conf.setPageIndex(p.getPage());
			else if (p.getFrom() != null) {
				conf.setStartPageIndex(p.getFrom());
				conf.setEndPageIndex(p.getTo());
			}
		}
		conf.setOffsetX(jContext.getPropertyInteger(AExportAction.EXPPARAM_OFFSET_X));
		conf.setOffsetY(jContext.getPropertyInteger(AExportAction.EXPPARAM_OFFSET_Y));
	}

	protected abstract JRAbstractExporter<?, ?, ?, ?> getExporter(JasperReportsConfiguration jContext,
			JRExportProgressMonitor monitor, File file);

	/**
	 * Called when the destination file already exist, check what to do using the preferences and it can use a static
	 * value chosen by the user before or prompt a question
	 * 
	 * @return true if the export operation should continue and the target file overwritten, false if the operation should
	 *         be aborted
	 */
	protected boolean continueIfFileExist() {
		boolean result = true;
		// Read the preference from the store
		String exporterValueString = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getString(JRExporterPreferencePage.EXPORTER_OVERWRITE);
		JRExporterPreferencePage.OVERWRITE_STATE exporterValue = JRExporterPreferencePage.OVERWRITE_STATE.ASK_EVERYTIME;
		if (exporterValueString != null && !exporterValueString.isEmpty()) {
			exporterValue = JRExporterPreferencePage.OVERWRITE_STATE.valueOf(exporterValueString);
		}
		if (exporterValue.equals(JRExporterPreferencePage.OVERWRITE_STATE.ASK_EVERYTIME)) {
			// Prompt the question to the user
			Boolean[] answers = UIUtils.showConfirmation(Messages.AExportAction_overwriteTitle,
					Messages.AExportAction_overwriteMessage, Messages.AExportAction_overwriteCheckbox);
			// the first element of the array is the decision made on the dialog, the second is the value of the checkbox
			result = answers[0];
			if (answers[1]) {
				// need to remember the decision
				String selectionToRemember = result ? JRExporterPreferencePage.OVERWRITE_STATE.OVERWRITE_TARGET.toString()
						: JRExporterPreferencePage.OVERWRITE_STATE.STOP_OPERATION.toString();
				JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(JRExporterPreferencePage.EXPORTER_OVERWRITE,
						selectionToRemember);
			}
		} else if (exporterValue.equals(JRExporterPreferencePage.OVERWRITE_STATE.STOP_OPERATION)) {
			result = false;
		}
		// the case to continue is already cover by the initialization value of result
		return result;
	}

	/**
	 * Called to proceed to the export of the report. It does some additional check than doPreview, since the export many
	 * times need also to check if or not to overwrite an existing file, instead in the preview it used a temp.
	 * 
	 * @param file
	 *          the destination file
	 * @param jrPrint
	 *          the jrprint to export
	 * @param monitor
	 *          monitor for the operation
	 */
	protected void doExport(File file, JasperPrint jrPrint, final IProgressMonitor monitor) {
		try {
			if (jrPrint != null && jrPrint.getPages() != null) {
				final Integer size = jrPrint.getPages().size();
				monitor.beginTask(Messages.AExportAction_exportreport, size);
				boolean continueOperation = true;
				if (file.exists()) {
					// If the file already exist read the preference option to handle the situation
					continueOperation = continueIfFileExist();
				}
				if (continueOperation) {
					exportWithProgress(file, new JRExportProgressMonitor() {
						private int current = 0;

						@Override
						public void afterPageExport() {
							if (monitor.isCanceled())
								Thread.currentThread().interrupt();
							monitor.worked(1);
							monitor.subTask(
									MessageFormat.format(Messages.PageNumberContributionItem_page, new Integer(current++), size));
						}

					});
				}
			}
		} catch (Throwable e) {
			UIUtils.showError(e);
		} finally {
			monitor.done();
		}
	}

	/**
	 * Called to proceed to the preview of the report. It does some additional check than doPreview, since the export many
	 * times need also to check if or not to overwrite an existing file, instead in the preview it used a temp.
	 * 
	 * @param file
	 *          the destination temp file
	 * @param jrPrint
	 *          the jrprint to export
	 * @param monitor
	 *          monitor for the operation
	 */
	public void doPreview(File file, JasperPrint jrPrint, final IProgressMonitor monitor) {
		try {
			if (jrPrint != null && jrPrint.getPages() != null) {
				final Integer size = jrPrint.getPages().size();
				monitor.beginTask(Messages.AExportAction_exportreport, size);
				exportWithProgress(file, new JRExportProgressMonitor() {
					private int current = 0;

					@Override
					public void afterPageExport() {
						if (monitor.isCanceled())
							Thread.currentThread().interrupt();
						monitor.worked(1);
						monitor
								.subTask(MessageFormat.format(Messages.PageNumberContributionItem_page, new Integer(current++), size));
					}

				});
			}
		} catch (Throwable e) {
			UIUtils.showError(e);
		} finally {
			monitor.done();
		}
	}

}
