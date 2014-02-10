/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import com.jaspersoft.studio.preferences.editor.pages.Pages;
import com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AbstractExportAction extends AReportViewerAction {

	private String[] filterNames;

	private String[] fileExtensions;

	private String defaultFileExtension;

	private String fileName;

	private String filterPath;
	private JasperReportsConfiguration jContext;
	private ExportMenuAction parentMenu;

	public AbstractExportAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer);
		this.jContext = jContext;
		this.parentMenu = parentMenu;
	}

	protected void setFileExtensions() {

	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SINGLE | SWT.SAVE);

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
			dialog.setFileName(getReportViewer().getDocument().getName());

		String filePath = dialog.open();
		if (filePath != null) {
			if (defaultFileExtension != null && fileExtensions != null) {
				String extension = getFileExtension(filePath);

				boolean fix = true;

				if (extension != null) {
					int i = 0;
					for (i = 0; i < fileExtensions.length; i++) {
						if (fileExtensions[i].endsWith(extension)) {
							fix = false;
							break;
						}
					}
				}

				if (fix) {
					filePath += '.' + defaultFileExtension;
				}
			}

			final File file = new File(filePath);

			try {
				export(file);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		if (parentMenu != null)
			parentMenu.setDefaultAction(this);
	}

	public void export(final File file) throws Exception {
		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());

		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						if (getReportViewer().getDocument() != null && getReportViewer().getDocument().getPages() != null) {
							int totalPages = getReportViewer().getDocument().getPages().size();
							monitor.beginTask("Export Report", totalPages);
							exportWithProgress(file, new ProgressMonitorAdapter(monitor, totalPages));
						}
					} catch (Throwable e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException e) {
			if (pm.getReturnCode() != ProgressMonitorDialog.CANCEL) {
				throw e;
			}
		} catch (InterruptedException e) {
			if (pm.getReturnCode() != ProgressMonitorDialog.CANCEL) {
				throw e;
			}
		} finally {
			if (pm.getReturnCode() == ProgressMonitorDialog.CANCEL) {
				file.delete();
			}
		}
	}

	public void setDefaultFileExtension(String defaultFileExtension) {
		this.defaultFileExtension = defaultFileExtension;
	}

	public String getDefaultFileExtension() {
		return defaultFileExtension;
	}

	public void setFileExtensions(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public void setFilterNames(String[] filterNames) {
		this.filterNames = filterNames;
	}

	private static String getFileExtension(String fileName) {
		if (fileName != null) {
			int dotIndex = fileName.lastIndexOf('.');
			if (dotIndex != -1)
				return fileName.substring(dotIndex + 1);
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
	protected boolean calculateEnabled() {
		return getReportViewer().hasDocument();
	}

	protected void exportWithProgress(File file, JRExportProgressMonitor monitor) throws Throwable {
		JRAbstractExporter exporter = getExporter(jContext);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, getReportViewer().getDocument());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
		exporter.setParameter(JRExporterParameter.PROGRESS_MONITOR, monitor);

		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
				jContext.getProperty(JRExporterParameter.PROPERTY_CHARACTER_ENCODING));

		exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS,
				jContext.getPropertyBoolean(JRExporterParameter.PROPERTY_IGNORE_PAGE_MARGINS));
		exporter.setParameter(JRExporterParameter.PARAMETERS_OVERRIDE_REPORT_HINTS,
				jContext.getPropertyBoolean(JRExporterParameter.PROPERTY_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS));

		exporter.setParameter(JRExporterParameter.OFFSET_X,
				jContext.getPropertyInteger(JRExporterPreferencePage.EXPPARAM_OFFSET_X));
		exporter.setParameter(JRExporterParameter.OFFSET_Y,
				jContext.getPropertyInteger(JRExporterPreferencePage.EXPPARAM_OFFSET_X));

		String indPage = jContext.getProperty(JRExporterPreferencePage.EXPPARAM_INDEX_PAGE, "all");
		Pages p = new Pages().parseString(indPage);

		if (p.getPage() != null)
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, p.getPage());
		else if (p.getFrom() != null) {
			exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, p.getFrom());
			exporter.setParameter(JRExporterParameter.END_PAGE_INDEX, p.getTo());
		}

		exporter.exportReport();
	}

	protected abstract JRAbstractExporter getExporter(JasperReportsConfiguration jContext);

}

class ProgressMonitorAdapter implements JRExportProgressMonitor {

	private IProgressMonitor monitor;

	private int totalPages;

	private int currentPage = 1;

	ProgressMonitorAdapter(IProgressMonitor monitor, int totalPages) {
		this.monitor = monitor;
		this.totalPages = totalPages;
		updateSubtask();
	}

	/**
	 * @see net.sf.jasperreports.engine.export.JRExportProgressMonitor#afterPageExport()
	 */
	public void afterPageExport() {
		monitor.worked(1);
		if (++currentPage <= totalPages) {
			updateSubtask();
		}
		if (monitor.isCanceled()) {
			Thread.currentThread().interrupt();
		}
	}

	private void updateSubtask() {
		monitor.subTask(MessageFormat.format("Page {0} of {1}", new Object[] { new Integer(currentPage),
				new Integer(totalPages) }));
	}
}
