/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;

public abstract class AVParameters extends APreview {

	protected Composite composite;
	protected ScrolledComposite scompo;
	protected boolean showEmptyParametersWarning = true;

	public AVParameters(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	public void setFocus() {
		for (Control c : composite.getChildren()) {
			if ((c.getStyle() & SWT.NO_FOCUS) == 0) {
				c.setFocus();
				break;
			}
		}
	}

	protected Map<String, IDataInput> incontrols = new HashMap<String, IDataInput>();

	public abstract boolean checkFieldsFilled();

	public void setDirty(boolean dirty) {
		for (IDataInput di : incontrols.values())
			di.setDirty(dirty);
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (!scompo.isDisposed())
			scompo.setEnabled(enabled);
	}

	protected void setScrollbarMinHeight() {
		scompo.setMinHeight(composite.getSize().y + 10);
	}

	protected void createVerticalSeprator(boolean first) {
		if (!first) {
			Label lblsep = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			// lblsep.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			lblsep.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}

	protected void createLabel(Composite sectionClient, IParameter pres, IDataInput in) {
		if (!in.isLabeled()) {
			Label lbl = new Label(sectionClient, SWT.WRAP);
			setupLabel(lbl, pres);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			lbl.setLayoutData(gd);
			UIUtil.setBold(lbl);
		}
	}

	protected void setupLabel(Label lbl, IParameter pres) {
		lbl.setText(pres.getLabel());
		lbl.setToolTipText(getToolTip(pres));
	}

	public String getToolTip(IParameter param) {
		return createToolTip(param);
	}

	public static String createToolTip(IParameter param) {
		String desc = Misc.nvl(param.getDescription());
		Class<?> cl = param.getValueClass();
		if (cl != null)
			desc += "\nThe class type is: " + cl.getCanonicalName();
		return desc;
	}

	@Override
	protected Control createControl(final Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setAlwaysShowScrollBars(false);

		composite = new Composite(scompo, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 2;
		layout.marginRight = 6;
		layout.marginBottom = 20;
		composite.setLayout(layout);
		composite.setBackground(parent.getBackground());
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		scompo.setContent(composite);

		return scompo;
	}
	
	protected void refreshControl() {
		composite.pack();		
		scompo.setVisible(true);
		scompo.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
		scompo.pack();
		scompo.getParent().layout();
	}
	
	@Override
	public Control getControl() {
		refreshControl();
		return super.getControl();
	}
}
