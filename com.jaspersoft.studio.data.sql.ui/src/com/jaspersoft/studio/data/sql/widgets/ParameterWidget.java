/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.designer.ICQuery;
import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.data.designer.SelectParameterDialog;
import com.jaspersoft.studio.data.jdbc.JDBCFieldsProvider;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ParameterPOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.util.JRClassLoader;

public class ParameterWidget extends AOperandWidget<ParameterPOperand> implements IFilterQuery {

	private Text txt;

	public ParameterWidget(Composite parent, ParameterPOperand operand, AQueryDesigner designer) {
		super(parent, SWT.BORDER, operand, designer);
	}

	@Override
	protected void createWidget(final Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		txt = new Text(this, SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		txt.setLayoutData(gd);

		ToolBar buttons = new ToolBar(this, SWT.FLAT);

		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setText("..."); //$NON-NLS-1$
		button.addListener(SWT.Selection, event -> {
			SelectParameterDialog d = new SelectSQLParameterDialog(parent.getShell(), designer, ParameterWidget.this);
			if (d.open() == Dialog.OK)
				fillValue();
		});
		button.setToolTipText(com.jaspersoft.studio.data.sql.messages.Messages.FieldWidget_0);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		fillValue();
	}

	class SelectSQLParameterDialog extends SelectParameterDialog {
		public SelectSQLParameterDialog(Shell parentShell, AQueryDesigner designer, IFilterQuery fq) {
			super(parentShell, designer, fq);
			if (getValue().getJrParameter() != null)
				pname = getValue().getJrParameter().getName();
		}

		@Override
		protected void commitValues() {
			super.commitValues();
			getValue().setJrParameter(prm);
			fillValue();
		}

		@Override
		protected void cancelPressed() {
			if (getValue().getJrParameter() == null) {
				super.commitValues();
				getValue().setJrParameter(prm);
			}
			super.cancelPressed();
		}

		@Override
		protected boolean isParameterCompatible(JRParameter p) {
			for (AOperand aop : operands) {
				if (aop == ParameterWidget.this.value)
					continue;
				if (aop instanceof FieldOperand) {
					if (((FieldOperand) aop).getMColumn() == null)
						return true;
					String t = ((FieldOperand) aop).getMColumn().getUnformattedTypeName();
					try {
						Class<?> clazz = JRClassLoader.loadClassForName(JDBCFieldsProvider.getJavaType4SQL(t));
						Class<?> pclazz = p.getValueClass();
						if (Number.class.isAssignableFrom(pclazz))
							return Number.class.isAssignableFrom(clazz);
						return pclazz.isAssignableFrom(clazz);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						return false;
					}
				} else if (aop instanceof ScalarOperand<?>
						&& !((ScalarOperand<?>) aop).getType().isAssignableFrom(p.getValueClass()))
					return false;
			}
			return true;
		}

		@Override
		protected String getDefaultParameterType() {
			if (prm != null && isParameterCompatible(prm))
				return prm.getValueClassName();
			for (AOperand aop : operands) {
				if (aop == ParameterWidget.this.value)
					continue;
				if (aop instanceof FieldOperand) {
					String t = ((FieldOperand) aop).getMColumn().getUnformattedTypeName();
					return JDBCFieldsProvider.getJavaType4SQL(t);
				} else if (aop instanceof ScalarOperand<?>)
					return ((ScalarOperand<?>) aop).getType().getCanonicalName();
			}
			return Object.class.getName();
		}
	}

	private void fillValue() {
		JRDesignParameter p = getValue().getJrParameter();
		if (p != null) {
			txt.setText(Misc.nvl(p.getName()));
			txt.setToolTipText(Misc.nvl(p.getName()));
		} else if (getValue().isShowDialog()) {
			UIUtils.getDisplay().asyncExec(() -> {
				SelectParameterDialog d = new SelectSQLParameterDialog(UIUtils.getShell(), designer, this);
				if (d.open() == Dialog.OK)
					fillValue();
			});
		}
	}

	@Override
	public String getFilterQuery() {
		return getFilterQueryObject(getValue().getExpression().getOperands());
	}

	public static String getFilterQueryObject(java.util.List<AOperand> ops) {
		for (AOperand aop : ops) {
			if (aop instanceof FieldOperand) {
				FieldOperand fop = (FieldOperand) aop;
				ICQuery q = new ICQuery();
				q.query = "SELECT * FROM " + fop.getFromTable().toSQLString();
				q.columns = new ArrayList<>();
				q.valueField = fop.toSQLString();
				try {
					return new ObjectMapper().writeValueAsString(q);
				} catch (JsonProcessingException e) {
					UIUtils.showError(e);
				}
			}
		}
		return null;
	}

	@Override
	public String getLanguage() {
		return "sql";
	}

}
