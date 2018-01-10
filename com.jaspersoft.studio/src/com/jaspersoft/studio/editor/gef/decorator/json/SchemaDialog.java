/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.json;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.export.JsonMetadataReportConfiguration;

/**
 * 
 * @author Veaceslav Chicu
 * 
 */
public class SchemaDialog extends ATitledDialog {

	private Text tPath;
	private MReport mtext;

	private String path;

	/**
	 * Build the dialog with a title and an initial value for the text field
	 * 
	 * @param parentShell
	 * @param mtext
	 */
	public SchemaDialog(Shell parentShell, MReport mtext) {
		super(parentShell);
		this.mtext = mtext;
		setTitle(Messages.SchemaDialog_0);
		JasperDesign v = mtext.getValue();
		path = v.getPropertiesMap().getProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_JSON_SCHEMA);
	}

	public String getName() {
		if (Misc.isNullOrEmpty(path))
			return null;
		return path;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite cmp = new Composite(area, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(cmp, SWT.NONE).setText(Messages.SchemaDialog_1);

		tPath = new Text(cmp, SWT.BORDER);
		tPath.setText(Misc.nvl(path));
		tPath.setToolTipText(Misc.nvl(path));
		GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gd.widthHint = 400;
		gd.horizontalSpan = 1;
		tPath.setLayoutData(gd);
		tPath.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				path = tPath.getText();
				validate(path);
			}

		});

		Button browse = new Button(cmp, SWT.PUSH);
		browse.setText("..."); //$NON-NLS-1$
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(UIUtils.getShell());
				fd.setFilterExtensions(new String[] { "*.json", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String selection = fd.open();
				if (selection != null) {
					path = selection;
					tPath.setText(selection);
					tPath.setToolTipText(selection);
				}
			}
		});

		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				validate(path);
			}
		});

		return area;
	}

	private void validate(String path) {
		getButton(IDialogConstants.OK_ID).setEnabled(true);
		setError(null);
		if (Misc.isNullOrEmpty(path)) {
			JRPropertiesMap map = mtext.getValue().getPropertiesMap();
			if (map != null && map.getProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_JSON_SCHEMA) != null)
				setError(Messages.SchemaDialog_5);
			else {
				setError(Messages.SchemaDialog_6);
				getButton(IDialogConstants.OK_ID).setEnabled(false);
			}
			return;
		}
	}

}
