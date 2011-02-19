/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.wizards.group;

import java.util.ArrayList;

import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.ModelUtils;

public class WizardBandGroupPage extends WizardPage {
	private MGroup group;
	private JasperDesign jrDesign;
	private Text grName;
	private java.util.List<JRDesignField> fList;
	private java.util.List<JRDesignVariable> vList;

	public void setGroup(MGroup group) {
		this.group = group;
		JRDesignGroup ct = (JRDesignGroup) group.getValue();
		if (ct == null)
			group.setValue(new JRDesignGroup());
	}

	public MGroup getGroup() {
		return group;
	}

	public WizardBandGroupPage(JasperDesign jrDesign) {
		super("grouppage"); //$NON-NLS-1$
		setTitle(Messages.common_group);
		setDescription(Messages.WizardBandGroupPage_description);
		this.jrDesign = jrDesign;
		fList = new ArrayList<JRDesignField>(jrDesign.getFieldsList());
		vList = new ArrayList<JRDesignVariable>();
		for (int i = 0; i < jrDesign.getVariablesList().size(); i++) {
			JRDesignVariable v = (JRDesignVariable) jrDesign.getVariablesList().get(i);
			if (!v.isSystemDefined())
				vList.add(v);

		}
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.common_group_name);

		grName = new Text(composite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		grName.setLayoutData(gd);
		grName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (jrDesign.getMainDesignDataset().getGroupsMap().get(grName.getText()) != null)
					setErrorMessage(Messages.WizardBandGroupPage_error_message_unique_name);
				else {
					setErrorMessage(null);
					setMessage(getDescription());
					group.setPropertyValue(JRDesignGroup.PROPERTY_NAME, grName.getText());
				}
			}
		});
		grName.setText(ModelUtils.getDefaultName(jrDesign.getMainDesignDataset().getGroupsMap(),
				Messages.common_group));

		Group expgroup = new Group(composite, SWT.BORDER);
		expgroup.setText(Messages.WizardBandGroupPage_group_by_following_report_object+":");
		layout = new GridLayout();
		layout.numColumns = 2;
		expgroup.setLayout(layout);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		expgroup.setLayoutData(gd);

		lbl = new Label(expgroup, SWT.NONE);
		lbl.setText(Messages.common_fields);

		lbl = new Label(expgroup, SWT.NONE);
		lbl.setText(Messages.common_variables);

		final List fields = new List(expgroup, SWT.BORDER | SWT.V_SCROLL);
		fields.setLayoutData(new GridData(GridData.FILL_BOTH));

		final List variables = new List(expgroup, SWT.BORDER | SWT.V_SCROLL);
		variables.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button addField = new Button(expgroup, SWT.PUSH);
		addField.setText(Messages.WizardBandGroupPage_field_text);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		addField.setLayoutData(gd);

		Composite expCompo = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		expCompo.setLayout(layout);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		expCompo.setLayoutData(gd);

		lbl = new Label(expCompo, SWT.NONE);
		lbl.setText(Messages.WizardBandGroupPage_group_by_following_expression);
		gd = new GridData();
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		final Text dsExpr = new Text(expCompo, SWT.BORDER | SWT.MULTI);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 60;
		dsExpr.setLayoutData(gd);

		final Button dsExprDialog = new Button(expCompo, SWT.PUSH);
		dsExprDialog.setText("..."); //$NON-NLS-1$
		dsExprDialog.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		dsExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();

				MExpression mexp = (MExpression) group.getPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION);
				if (mexp == null || mexp.getValue() == null) {
					JRDesignExpression jrExpression = new JRDesignExpression();
					jrExpression.setValueClassName(String.class.getName());
					mexp = new MExpression(jrExpression);
				}

				wizard.setValue(mexp);
				WizardDialog dialog = new WizardDialog(dsExprDialog.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mexp = wizard.getValue();
					group.setPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION, mexp.getValue());

					dsExpr.setText((String) mexp.getPropertyValue(JRDesignExpression.PROPERTY_TEXT));
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		dsExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				MExpression mexp = (MExpression) group.getPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION);
				if (mexp == null || mexp.getValue() == null) {
					JRDesignExpression jrExpression = new JRDesignExpression();
					jrExpression.setValueClassName(String.class.getName());
					if (mexp == null)
						mexp = new MExpression(jrExpression);
					else
						mexp.setValue(jrExpression);
				}
				mexp.setPropertyValue(JRDesignExpression.PROPERTY_TEXT, dsExpr.getText());
				group.setPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION, mexp);
			}
		});

		addField.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int sel = fields.getSelectionIndex();
				if (sel >= 0 && sel < fList.size()) {
					JRDesignField f = fList.get(sel);
					String expression = "$F{" + f.getName() + "}";//$NON-NLS-1$ //$NON-NLS-2$
					MExpression mexp = (MExpression) group.getPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION);
					if (mexp == null || mexp.getValue() == null) {
						JRDesignExpression jrExpression = new JRDesignExpression();
						jrExpression.setValueClassName(f.getValueClassName());
						jrExpression.setText(expression);
						if (mexp == null)
							mexp = new MExpression(jrExpression);
						else
							mexp.setValue(jrExpression);
					}
					group.setPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION, mexp);
					dsExpr.setText(expression);
				}
				sel = variables.getSelectionIndex();
				if (sel >= 0 && sel < fList.size()) {
					JRDesignVariable v = vList.get(sel);
					String expression = "$V{" + v.getName() + "}";//$NON-NLS-1$ //$NON-NLS-2$
					MExpression mexp = (MExpression) group.getPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION);
					if (mexp == null || mexp.getValue() == null) {
						JRDesignExpression jrExpression = new JRDesignExpression();
						jrExpression.setValueClassName(v.getValueClassName());
						jrExpression.setText(expression);
						if (mexp == null)
							mexp = new MExpression(jrExpression);
						else
							mexp.setValue(jrExpression);
					}
					group.setPropertyValue(JRDesignGroup.PROPERTY_EXPRESSION, mexp);
					dsExpr.setText(expression);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		for (JRDesignField f : fList) {
			fields.add(f.getName());
		}
		for (JRDesignVariable v : vList) {
			variables.add(v.getName());
		}
		fields.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (fields.getSelectionIndex() >= 0)
					variables.setSelection(-1);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		variables.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (variables.getSelectionIndex() >= 0)
					fields.setSelection(-1);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}
}
