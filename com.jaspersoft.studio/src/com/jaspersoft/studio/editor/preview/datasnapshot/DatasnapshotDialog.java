/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.datasnapshot;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.SimpleReportContext;

public class DatasnapshotDialog extends ATitledDialog {

	private Button bSnapshot;
	private Text tFile;
	private JasperReportsConfiguration jConfig;
	private Button bFile;
	private Label lfile;

	public DatasnapshotDialog(Shell parentShell, JasperReportsConfiguration jConfig) {
		super(parentShell, false);
		setTitle(Messages.DatasnapshotDialog_0);
		this.jConfig = jConfig;
	}

	@Override
	protected void okPressed() {
		if (canHandleShellCloseEvent()) {
			Map<String, Object> hm = jConfig.getJRParameters();
			SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);
			if (bSnapshot.getSelection()) {
				DataSnapshotManager.setCaching(hm, true);

				reportContext = (SimpleReportContext) hm.computeIfAbsent(JRParameter.REPORT_CONTEXT,
						k -> new SimpleReportContext());
				String fname = (String) jConfig.getMap().get(DataSnapshotManager.SAVE_SNAPSHOT);
				reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);

				if (!Misc.isNullOrEmpty(tFile.getText())) {
					fname = tFile.getText().trim();
					DataCacheHandler cacheHandler = DataSnapshotManager.setDataSnapshot(hm, false);
					if (cacheHandler.getDataSnapshot() != null) {
						Date creationTimestamp = new Date();
						if (cacheHandler instanceof JSSColumnDataCacheHandler)
							creationTimestamp = ((JSSColumnDataCacheHandler) cacheHandler).getCreationTimestamp();
						DataSnapshotManager.saveSnapshot(fname, creationTimestamp, cacheHandler.getDataSnapshot());
					}
					reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
					jConfig.getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
					jConfig.getJasperDesign().setProperty(DataSnapshotManager.SAVE_SNAPSHOT, fname);
				} else {
					DataSnapshotManager.removeSnapshotFile(hm);
					jConfig.getJasperDesign().removeProperty(DataSnapshotManager.SAVE_SNAPSHOT);
				}
			} else {
				DataSnapshotManager.setCaching(hm, false);
				if (reportContext != null) {
					Map<String, Object> pv = reportContext.getParameterValues();
					String fname = (String) pv.get(DataSnapshotManager.SAVE_SNAPSHOT);
					if (fname != null)
						jConfig.getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
					pv.remove(DataSnapshotManager.SAVE_SNAPSHOT);
				}
				DataSnapshotManager.removeSnapshotFile(hm);
				jConfig.getJasperDesign().removeProperty(DataSnapshotManager.SAVE_SNAPSHOT);
			}
			super.okPressed();
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		bSnapshot = new Button(cmp, SWT.CHECK);
		bSnapshot.setText(Messages.DatasnapshotDialog_1);
		bSnapshot.setToolTipText(Messages.DatasnapshotDialog_2);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		bSnapshot.setLayoutData(gd);
		bSnapshot.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				enableFile();
			}
		});

		lfile = new Label(cmp, SWT.NONE);
		lfile.setText(Messages.DatasnapshotDialog_3);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.horizontalIndent = 18;
		gd.verticalIndent = 10;
		lfile.setLayoutData(gd);

		tFile = new Text(cmp, SWT.BORDER);
		tFile.setToolTipText(Messages.DatasnapshotDialog_4);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 400;
		gd.horizontalIndent = 18;
		tFile.setLayoutData(gd);
		tFile.addModifyListener(e -> {
			String tt = tFile.getText();
			if (!tt.isEmpty())
				tt += "\n\n"; //$NON-NLS-1$
			tt += Messages.DatasnapshotDialog_4;
			tFile.setToolTipText(tt);
		});
		tFile.addModifyListener(e -> validate());

		bFile = new Button(cmp, SWT.PUSH);
		bFile.setText("..."); //$NON-NLS-1$
		bFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(bFile.getShell(), SWT.SAVE);
				fd.setText(Messages.PreviewTopToolBarManager_3);
				IFile f = (IFile) jConfig.get(FileUtils.KEY_FILE);
				String sname = FilenameUtils.getBaseName(f.getName()) + ".jrds"; //$NON-NLS-1$
				String folder = f.getParent().getLocation().toOSString();
				fd.setFilterPath(folder);
				fd.setFileName(sname);
				fd.setFilterExtensions(new String[] { "*.jrds", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				// $NON-NLS-2$
				String fname = fd.open();
				if (fname != null) {
					if ((folder + File.separator + sname).equals(fname))
						fname = sname;
					tFile.setText(fname);
				}
			}
		});

		init();

		return cmp;
	}

	private void init() {
		String file = DataSnapshotManager.getSnapshotFile(jConfig);
		bSnapshot.setSelection(DataSnapshotManager.snapshotExists(jConfig.getJRParameters()));
		if (bSnapshot.getSelection())
			tFile.setText(Misc.nvl(file));
		enableFile();
	}

	protected void enableFile() {
		if (!bSnapshot.getSelection())
			tFile.setText(""); //$NON-NLS-1$
		tFile.setEnabled(bSnapshot.getSelection());
		bFile.setEnabled(bSnapshot.getSelection());
		lfile.setForeground(bSnapshot.getSelection() ? null : SWTResourceManager.getColor(SWT.COLOR_GRAY));
	}

	protected void validate() {
		try {
			String t = tFile.getText();
			if (!t.isEmpty()) {
				new File(t).getCanonicalPath();
				setError(null);
			}
		} catch (Exception ex) {
			setError("Invalid file path");
		}
	}

}
