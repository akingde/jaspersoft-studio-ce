/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets;

import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.dialogs.FromTableColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.text2model.ConvertUtil;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class FieldWidget extends AOperandWidget<FieldOperand> {

	private Text txt;

	public FieldWidget(Composite parent, FieldOperand operand, AQueryDesigner designer) {
		super(parent, SWT.BORDER, operand, designer);
	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		final FieldOperand v = getValue();

		txt = new Text(this, SWT.READ_ONLY);
		txt.setText(ConvertUtil.cleanDbNameFull(v.toSQLString()));
		txt.setToolTipText(ConvertUtil.cleanDbNameFull(v.toSQLString()));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		txt.setLayoutData(gd);

		ToolBar buttons = new ToolBar(this, SWT.FLAT);

		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setText("..."); //$NON-NLS-1$
		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				FromTableColumnsDialog dialog = new FromTableColumnsDialog(UIUtils.getShell(), SWT.SINGLE);
				dialog.setSelection(v.getExpression());
				if (dialog.open() == Dialog.OK) {
					Map<MSQLColumn, MFromTable> cmap = dialog.getColumns();
					for (MSQLColumn t : cmap.keySet())
						v.setValue(t, cmap.get(t));
				}
				txt.setText(ConvertUtil.cleanDbNameFull(v.toSQLString()));
				txt.setToolTipText(v.toSQLString());
			}

		});
		button.setToolTipText(com.jaspersoft.studio.data.sql.messages.Messages.FieldWidget_0);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
	}

}
