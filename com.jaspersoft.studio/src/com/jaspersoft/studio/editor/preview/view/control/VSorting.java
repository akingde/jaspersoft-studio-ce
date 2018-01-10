/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.inputs.dialog.SortFieldSection;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

public class VSorting extends APreview {

	private SortFieldSection sortField;
	private Composite composite;
	private ScrolledComposite scompo;

	public VSorting(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setAlwaysShowScrollBars(false);

		composite = new Composite(scompo, SWT.NONE);
		composite.setBackground(parent.getBackground());
		GridLayout layout = new GridLayout();
		layout.marginBottom = 10;
		composite.setLayout(layout);
		scompo.setContent(composite);
		
		return scompo;
	}

	@Override
	public Control getControl() {
		refreshControl();
		return super.getControl();
	}

	public SortFieldSection getSortField() {
		if (sortField == null)
			sortField = new SortFieldSection();
		return sortField;
	}

	public void setJasperReports(JasperDesign jDesign, List<JRParameter> prompts, Map<String, Object> params) {
		for (Control c : composite.getChildren())
			c.dispose();

		sortField = getSortField();
		sortField.fillTable(composite, jDesign, prompts, params);
		refreshControl();
	}

	private void refreshControl() {
		composite.pack();
		scompo.setVisible(true);
		scompo.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
		scompo.pack();
		scompo.getParent().layout();
	}
}
