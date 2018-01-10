/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.editor.preview.actions.export.AExportAction;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.editor.preview.input.PropertyChangeNotifier;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.preferences.editor.pages.Pages;
import com.jaspersoft.studio.utils.NumberValidator;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;

public class VReportParameters extends VParameters {

	private Button btPage;
	private Spinner page;
	private Button btRange;
	private Spinner pageFrom;
	private Spinner pageTo;
	private Button btAll;
	private Pages pages;

	public VReportParameters(Composite parent, JasperReportsConfiguration jContext, PropertyChangeNotifier propertyChangeNotifier) {
		super(parent, jContext, propertyChangeNotifier);
		isSystem = true;
	}

	@Override
	protected boolean isParameterToShow(JRParameter p) {
		return p.isSystemDefined() && !p.getName().equals(JRParameter.REPORT_TEMPLATES)
				&& !p.getName().equals(JRParameter.SORT_FIELDS) && !p.getName().equals(JRParameter.REPORT_PARAMETERS_MAP);
	}

	@Override
	protected void setupLabel(Label lbl, IParameter pres) {
		lbl.setText(MessagesByKeys.getString(pres.getLabel()));
	}

	@Override
	protected void createInputControls(List<JRParameter> prompts, Map<String, Object> params) {
		super.createInputControls(prompts, params);

		createVerticalSeprator(false);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);

		new Label(cmp, SWT.NONE).setText(Messages.JRExporterPreferencePage_16);
		final Text tOx = new Text(cmp, SWT.BORDER);
		tOx.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tOx.setText(Misc.nvl(jContext.getProperty(AExportAction.EXPPARAM_OFFSET_X)));
		tOx.addVerifyListener(new NumberValidator(0, Integer.MAX_VALUE, Integer.class));
		tOx.addModifyListener(new OffsetModifyListener(tOx, AExportAction.EXPPARAM_OFFSET_X));

		new Label(cmp, SWT.NONE).setText(Messages.JRExporterPreferencePage_17);
		final Text tOy = new Text(cmp, SWT.BORDER);
		tOy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tOy.setText(Misc.nvl(jContext.getProperty(AExportAction.EXPPARAM_OFFSET_Y)));
		tOy.addVerifyListener(new NumberValidator(0, Integer.MAX_VALUE, Integer.class));
		tOy.addModifyListener(new OffsetModifyListener(tOy, AExportAction.EXPPARAM_OFFSET_Y));

		Group container = new Group(cmp, SWT.NONE);
		container.setText(Messages.PagesFieldEditor_pageToExportBox);
		container.setLayout(new GridLayout(5, false));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		container.setLayoutData(gd);

		btAll = new Button(container, SWT.RADIO);
		btAll.setText(Messages.PagesFieldEditor_allPages);
		gd = new GridData();
		gd.horizontalSpan = 5;
		btAll.setLayoutData(gd);
		btAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handlePageChange();
			}
		});

		createPage(container);

		createRange(container);

		String ip = jContext.getProperty(AExportAction.EXPPARAM_INDEX_PAGE);
		pages = new Pages();
		pages.parseString(ip);
		if (pages.getPage() != null) {
			btPage.setSelection(true);
			page.setSelection(pages.getPage());
			page.setEnabled(true);
		} else if (pages.getFrom() != null) {
			btRange.setSelection(true);

			pageFrom.setSelection(pages.getFrom());
			pageFrom.setEnabled(true);
			pageTo.setSelection(pages.getTo());
			pageTo.setEnabled(true);
		} else
			btAll.setSelection(true);
		
		refreshControl();
	}

	private void createPage(Group container) {
		btPage = new Button(container, SWT.RADIO);
		btPage.setText(Messages.PagesFieldEditor_precisePages);

		page = new Spinner(container, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 4;
		page.setLayoutData(gd);
		page.setEnabled(false);
		page.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		page.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				handlePageChange();
			}
		});

		btPage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				page.setEnabled(btPage.getSelection());
			}
		});
	}

	private void createRange(Group container) {
		btRange = new Button(container, SWT.RADIO);
		btRange.setText(Messages.PagesFieldEditor_fromToPages);

		pageFrom = new Spinner(container, SWT.BORDER);
		pageFrom.setEnabled(false);
		pageFrom.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		pageFrom.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				handlePageChange();
			}
		});

		Label lbl = new Label(container, SWT.NONE);
		lbl.setText(Messages.PagesFieldEditor_to);

		pageTo = new Spinner(container, SWT.BORDER);
		pageTo.setEnabled(false);
		pageTo.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		pageTo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				handlePageChange();
			}
		});

		btRange.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pageFrom.setEnabled(btRange.getSelection());
				pageTo.setEnabled(btRange.getSelection());
			}

		});
	}

	private void handlePageChange() {
		ScopedPreferenceStore ps = jContext.getPrefStore();
		String value = getProperty();
		if (value.equals("all")) {
			jContext.removeProperty(AExportAction.EXPPARAM_INDEX_PAGE);
			ps.setValue(AExportAction.EXPPARAM_INDEX_PAGE,
					ps.getDefaultInt(AExportAction.EXPPARAM_INDEX_PAGE));
		} else {
			jContext.setProperty(AExportAction.EXPPARAM_INDEX_PAGE, value);
			ps.setValue(AExportAction.EXPPARAM_INDEX_PAGE, value);
		}
		try {
			ps.save();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private String getProperty() {
		if (btAll.getSelection())
			return "all"; //$NON-NLS-1$
		if (btPage.getSelection())
			return Integer.toString(page.getSelection());
		int from = pageFrom.getSelection();
		int to = pageTo.getSelection();
		if (to < from)
			to = from;
		return Integer.toString(from) + ";" + Integer.toString(to); //$NON-NLS-1$
	}
	
	class OffsetModifyListener implements ModifyListener {
		private Text txt;
		private String property;
		private ScopedPreferenceStore ps;

		public OffsetModifyListener(Text txt, String property) {
			this.txt = txt;
			this.property = property;
			this.ps = jContext.getPrefStore();
		}

		@Override
		public void modifyText(ModifyEvent e) {
			String v = txt.getText();
			if (Misc.isNullOrEmpty(v)) {
				jContext.removeProperty(property);
				ps.setValue(property, ps.getDefaultInt(property));
			} else {
				jContext.setProperty(property, v);
				ps.setValue(property, Integer.parseInt(v));
			}
			try {
				ps.save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
