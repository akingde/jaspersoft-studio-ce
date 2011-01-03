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
package com.jaspersoft.studio.editor.preview;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRXlsDataSource;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.AsynchronousFilllListener;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.editor.JRPrintEditor;
import com.jaspersoft.studio.editor.preview.actions.ReloadAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.model.datasource.xls.MXLSDataSource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;
import com.jaspersoft.studio.repository.RepositoryManager;
import com.jaspersoft.studio.utils.ErrorUtil;

public class PreviewEditor extends JRPrintEditor {

	protected JasperPrint loadJRObject(InputStream in) throws JRException {
		return getJasperPrint();
	}

	private JasperDesign jasperDesign;

	public void setJasperDesign(JasperDesign jasperDesign) {
		this.jasperDesign = jasperDesign;
	}

	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	private AMDatasource datasource;
	private Map<String, Object> jasperParameter = new Hashtable<String, Object>();

	private Throwable fillError = null;
	private DatasourceComboItem dataSourceWidget;
	private ReloadAction reloadAction;

	public void runReport(final AMDatasource d) {
		if (isNotRunning()) {

			String dsName = ""; //$NON-NLS-1$
			if (d != null) {
				dsName = d.getDisplayText();
				datasource = d;
			} else {
				if (datasource != null)
					dsName = datasource.getDisplayText();
				else {
					unsetReportDocument(Messages.PreviewEditor_no_datasource, true);
					// jasperPrint = new ReportConverter(jasperDesign, false, true).getJasperPrint();
					return;
				}
			}
			int pdresult = askParameters();
			if (pdresult != Window.OK)
				return;

			String jobName = Messages.PreviewEditor_preview_a + ": " + jasperDesign.getName() + Messages.PreviewEditor_preview_b + "[" + dsName + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Job job = new Job(jobName) {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					unsetReportDocument(Messages.PreviewEditor_reloading, false);
					monitor.beginTask(Messages.PreviewEditor_starting, IProgressMonitor.UNKNOWN);
					InputStream io = null;
					fillError = null;
					try {
						IFile file = ((IFileEditorInput) getEditorInput()).getFile();
						Thread.currentThread().setContextClassLoader(
								RepositoryManager.getClassLoader4Project(monitor, file.getProject()));

						setJasperPrint(null);
						AsynchronousFillHandle fh = null;
						JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

						SimpleFileResolver fileResolver = new SimpleFileResolver(Arrays.asList(new File[] {
								new File(file.getParent().getLocationURI()), new File("."), //$NON-NLS-1$
								new File(file.getProject().getLocationURI()) }));
						fileResolver.setResolveAbsolutePath(true);

						jasperParameter.put(JRParameter.REPORT_FILE_RESOLVER, fileResolver);

						if (datasource instanceof MJDBCDataSource) {
							Connection connection = RepositoryManager.establishConnection((MJDBCDataSource) datasource,
									PreviewEditor.this, monitor);
							if (connection != null)
								fh = AsynchronousFillHandle.createHandle(jasperReport, jasperParameter, connection);
							else
								unsetReportDocument(Messages.PreviewEditor_connection_could_not_be_established, true);
						} else {
							JRDataSource jrds = null;
							if (datasource instanceof MEmptyDataSource) {
								jrds = new JREmptyDataSource((Integer) datasource.getPropertyValue(MEmptyDataSource.PROPERTY_SIZE));
							} else if (datasource instanceof AMFileDataSource) {
								io = new FileInputStream((String) datasource.getPropertyValue(MFileDataSource.PROPERTY_FILENAME));
								String df = (String) datasource.getPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT);
								if (df == null || df.trim().equals("")) //$NON-NLS-1$
									df = "yyyy-MM-dd"; //$NON-NLS-1$
								String nf = (String) datasource.getPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT);
								if (nf == null || nf.trim().equals("")) //$NON-NLS-1$
									nf = "#,##0.##"; //$NON-NLS-1$

								if (datasource instanceof MFileDataSource) {
									jrds = RepositoryManager.createFileDataSource(io, (MFileDataSource) datasource);
									((JRCsvDataSource) jrds).setDateFormat(new SimpleDateFormat(df));
									((JRCsvDataSource) jrds).setNumberFormat(new DecimalFormat(nf));
								} else if (datasource instanceof MXLSDataSource) {
									jrds = new JRXlsDataSource(io);
									((JRXlsDataSource) jrds).setDateFormat(new SimpleDateFormat(df));
									((JRXlsDataSource) jrds).setNumberFormat(new DecimalFormat(nf));
								} else if (datasource instanceof MXMLDataSource) {
									jasperParameter.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, df);
									jasperParameter.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, nf);
									TimeZone tz = (TimeZone) datasource.getPropertyValue(MXMLDataSource.PROPERTY_XPATHTIMEZONE);
									if (tz == null)
										try {
											tz = (TimeZone) datasource.getPropertyDefaultValue(MXMLDataSource.PROPERTY_XPATHTIMEZONE);
										} catch (Exception ex) {
											tz = TimeZone.getDefault();
										}
									jasperParameter.put(JRXPathQueryExecuterFactory.XML_TIME_ZONE, tz);
									Locale locale = (Locale) datasource.getPropertyValue(MXMLDataSource.PROPERTY_XPATHLOCALE);
									if (locale == null)
										try {
											locale = (Locale) datasource.getPropertyDefaultValue(MXMLDataSource.PROPERTY_XPATHLOCALE);
										} catch (Exception ex) {
											locale = Locale.getDefault();
										}
									jasperParameter.put(JRXPathQueryExecuterFactory.XML_LOCALE, locale);

									String select = (String) datasource.getPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT);
									if (select != null && !select.trim().endsWith("")) { //$NON-NLS-1$
										jrds = RepositoryManager.createXMLDataSource(PreviewEditor.this, io, select);
									} else {
										jasperParameter.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, JRXmlUtils.parse(io));
									}
								}
							}
							jasperParameter.put(JRParameter.REPORT_LOCALE, Locale.US);

							if (jrds != null) {
								fh = AsynchronousFillHandle.createHandle(jasperReport, jasperParameter, jrds);
							} else
								fh = AsynchronousFillHandle.createHandle(jasperReport, jasperParameter);
						}
						if (fillReport(fh, monitor) == Status.CANCEL_STATUS)
							return Status.CANCEL_STATUS;

						setReportDocument(true);
					} catch (final Throwable e) {
						unsetReportDocument(ErrorUtil.getStackTrace(e), true);
					} finally {
						if (io != null)
							try {
								io.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								setNotRunning(true);
							}
						});
						monitor.done();
						if (datasource != null && datasource instanceof MJDBCDataSource) {
							RepositoryManager.closeConnection((MJDBCDataSource) datasource);
						}
					}
					return Status.OK_STATUS;

				}
			};
			job.setPriority(Job.LONG);
			job.schedule();
		}
	}

	private int askParameters() {
		List<JRDesignParameter> prompts = new ArrayList<JRDesignParameter>();
		List<JRDesignParameter> params = jasperDesign.getParametersList();
		for (JRDesignParameter jdp : params) {
			if (jdp.isForPrompting() && !jdp.isSystemDefined()) {
				prompts.add(jdp);
			}
		}
		if (prompts.isEmpty())
			return Window.OK;
		ParametersDialog pd = new ParametersDialog(getEditorSite().getShell(), prompts, jasperParameter);
		int pdresult = pd.open();
		return pdresult;
	}

	public void setNotRunning(boolean norun) {
		super.setNotRunning(norun);
		dataSourceWidget.refresh(true);
		reloadAction.setEnabled(norun);
	}

	protected void refreshToolbar() {
		super.refreshToolbar();
		IToolBarManager tbManager = getTbManager();

		tbManager.add(new Separator());
		reloadAction = new ReloadAction(this);
		tbManager.appendToGroup("DATASOURCEGROUP", reloadAction); //$NON-NLS-1$
		dataSourceWidget = new DatasourceComboItem(this);
		tbManager.appendToGroup("DATASOURCEGROUP", dataSourceWidget); //$NON-NLS-1$

		tbManager.update(true);
	}

	private IStatus fillReport(AsynchronousFillHandle fh, IProgressMonitor monitor) throws JRException,
			InterruptedException {
		Assert.isTrue(fh != null);
		IProgressMonitor sm = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN,
				SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		try {
			sm.beginTask(Messages.PreviewEditor_fill_report, IProgressMonitor.UNKNOWN);
			org.mozilla.javascript.Context.enter();

			
			fh.addListener(new AsynchronousFilllListener() {

				public void reportFinished(JasperPrint jPrint) {
					setJasperPrint(jPrint);
				}

				public void reportFillError(Throwable t) {
					handleFillException(t);
				}

				public void reportCancelled() {
					unsetReportDocument(Messages.PreviewEditor_report_fill_canceled, true);
				}
			});
			fh.startFill();
			while (true && getJasperPrint() == null && fillError == null) {
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
			org.mozilla.javascript.Context.exit();
		}
		return Status.OK_STATUS;
	}

	private void handleFillException(Throwable t) {
		fillError = t;
	}

}
