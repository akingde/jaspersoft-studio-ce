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

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.data.cache.DataSnapshot;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.SimpleReportContext;
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

	public static boolean snapshotExists(Map<String, Object> parameters) {
		ReportContext context = (ReportContext) parameters.get(JRParameter.REPORT_CONTEXT);
		return context != null && context.containsParameter(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER)
				&& context.containsParameter(DataSnapshotManager.SAVE_SNAPSHOT);
	}

	public static void saveSnapshot(final String fname, final Date creationTimestamp, final DataSnapshot snapshot) {
		Job job = new Job("Saving snapshot to: " + fname) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Saving data snapshot to: " + fname, IProgressMonitor.UNKNOWN);
				try {
					// should save it to IFile?
					new JssDataSnapshot(creationTimestamp, snapshot);

					JRSaver.saveObject(new JssDataSnapshot(creationTimestamp, snapshot), new File(fname));
				} catch (JRException e) {
					UIUtils.showError(e);
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	public static final String SAVE_SNAPSHOT = "SAVESNAPSHOT";
}
