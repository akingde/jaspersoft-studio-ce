/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.datasnapshot;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.data.cache.DataSnapshot;
import net.sf.jasperreports.data.cache.PopulatedSnapshotCacheHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.SimpleReportContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

public class DataSnapshotManager {

	public static void setCaching(Map<String, Object> parameters, boolean on) {
		if (on)
			setDataSnapshot(parameters, on);
		else
			parameters.remove(JRParameter.REPORT_CONTEXT);
	}

	public static DataCacheHandler setDataSnapshot(Map<String, Object> parameters, boolean reset) {
		return setDataSnapshot(parameters, null, reset);
	}

	public static DataCacheHandler setDataSnapshot(Map<String, Object> parameters, DataCacheHandler cacheHandler,
			boolean reset) {
		SimpleReportContext reportContext = (SimpleReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		if (reportContext == null) {
			reportContext = new SimpleReportContext();
			parameters.put(JRParameter.REPORT_CONTEXT, reportContext);
		}
		if (reset)
			reportContext.getParameterValues().remove(SAVE_SNAPSHOT);
		if (cacheHandler != null)
			reportContext.setParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER, cacheHandler);
		else
			cacheHandler = (DataCacheHandler) reportContext
					.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
		if (cacheHandler == null || reset) {
			cacheHandler = new JSSColumnDataCacheHandler();
			reportContext.setParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER, cacheHandler);
		}
		return cacheHandler;
	}

	public static void saveSnapshotIfExists(Map<String, Object> parameters) {
		if (parameters == null)
			return;
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		if (context != null && context.containsParameter(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER)
				&& context.containsParameter(DataSnapshotManager.SAVE_SNAPSHOT)) {
			DataCacheHandler ch = (DataCacheHandler) context
					.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
			if (ch != null && ch.isSnapshotPopulated()) {
				String path = (String) context.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT);
				Date creationTimestamp = new Date();
				if (ch instanceof JSSColumnDataCacheHandler)
					creationTimestamp = ((JSSColumnDataCacheHandler) ch).getCreationTimestamp();
				DataSnapshotManager.saveSnapshot(path, creationTimestamp, ch.getDataSnapshot());
			}
		}
	}

	public static boolean isPopulatedSnapshotExists(Map<String, Object> parameters) {
		if (parameters == null)
			return false;
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		if (context != null && context.containsParameter(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER)
				&& context.containsParameter(DataSnapshotManager.SAVE_SNAPSHOT)) {
			DataCacheHandler ch = (DataCacheHandler) context
					.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
			if (ch != null && ch.isSnapshotPopulated())
				return true;
		}
		return false;
	}

	public static boolean snapshotExists(Map<String, Object> parameters) {
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		return context != null && context.containsParameter(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER)
				&& context.containsParameter(DataSnapshotManager.SAVE_SNAPSHOT);
	}

	public static boolean snapshotFileExists(Map<String, Object> parameters) {
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		return snapshotExists(parameters) && context.getParameterValue(SAVE_SNAPSHOT) != null;
	}

	public static String getSnapshotFile(JasperReportsConfiguration jConfig) {
		ReportContext rc = (ReportContext) jConfig.getJRParameters().get(JRParameter.REPORT_CONTEXT);
		if (rc instanceof SimpleReportContext) {
			if (rc.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT) != null)
				return (String) rc.getParameterValue(DataSnapshotManager.SAVE_SNAPSHOT);
		} else if (jConfig.getProperty(DataSnapshotManager.SAVE_SNAPSHOT) != null)
			return jConfig.getProperty(DataSnapshotManager.SAVE_SNAPSHOT);
		return null;
	}

	public static void removeSnapshotFile(Map<String, Object> parameters) {
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		context.setParameterValue(SAVE_SNAPSHOT, null);
	}

	public static void saveSnapshot(final String fname, final Date creationTimestamp, final DataSnapshot snapshot) {
		Job job = new Job("Saving snapshot to: " + fname) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Saving data snapshot to: " + fname, IProgressMonitor.UNKNOWN);
				try {
					// should save it to IFile?
					new JssDataSnapshot(creationTimestamp, snapshot);
					if (!Misc.isNullOrEmpty(fname))
						JRSaver.saveObject(new JssDataSnapshot(creationTimestamp, snapshot), new File(fname));
				} catch (JRException e) {
					UIUtils.showError(e);
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	public static void loadSnapshot(JasperReportsConfiguration jConfig, String fname) throws JRException {
		Map<String, Object> hm = jConfig.getJRParameters();
		Object obj = JRLoader.loadObject(new File(fname));
		if (obj instanceof JssDataSnapshot) {
			JssDataSnapshot snapshot = (JssDataSnapshot) obj;
			DataSnapshotManager.setDataSnapshot(hm,
					new JSSColumnDataCacheHandler(((JssDataSnapshot) snapshot).getSnapshot(),
							((JssDataSnapshot) snapshot).getCreationTimestamp()),
					false);
		} else if (obj instanceof DataSnapshot)
			DataSnapshotManager.setDataSnapshot(hm, new PopulatedSnapshotCacheHandler((DataSnapshot) obj), false);
		SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);
		reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
		jConfig.getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
	}

	public static final String SAVE_SNAPSHOT = "net.sf.jasperreports.datasnapshot.SAVESNAPSHOT";
}
