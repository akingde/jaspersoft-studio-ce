/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

public class QueryDesigner extends AQueryDesigner {
	private boolean refresh = false;

	public final class QueryListener implements ModifyListener {

		public void modifyText(ModifyEvent e) {
			if (!refresh) {
				refresh = true;
				((JRDesignQuery) jDataset.getQuery()).setText(control.getText());
				refresh = false;
			}
		}
	}

	protected StyledText control;
	private Composite tbCompo;
	private Button btn;
	protected UndoRedoImpl undoHandlrer;

	public QueryDesigner() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#getControl()
	 */
	public Control getControl() {
		return control;
	}

	public Control getToolbarControl() {
		return tbCompo;
	}

	public Control createControl(Composite parent) {
		control = new StyledText(parent, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		control.addModifyListener(new QueryListener());
		control.setFont(FontUtils.getEditorsFont(jConfig));
		undoHandlrer = new UndoRedoImpl(control);
		return control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(JasperDesign jDesign, JRDataset jDataset, JasperReportsConfiguration jConfig) {
		super.setQuery(jDesign, jDataset, jConfig);
		refresh = true;
		updateQueryText(jDataset.getQuery().getText());
		undoHandlrer.clearStack();
		refresh = false;
	}

	protected void updateQueryText(String txt) {
		control.setText(txt);
	}

	public void dispose() {
	}

	public Control createToolbar(Composite parent) {
		tbCompo = new Composite(parent, SWT.RIGHT_TO_LEFT);
		tbCompo.setBackgroundMode(SWT.INHERIT_FORCE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		tbCompo.setLayout(layout);

		btn = new Button(tbCompo, SWT.PUSH);
		btn.setText(Messages.QueryDesigner_readFieldsButton);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				container.doGetFields();
			}

		});
		setFieldProviderEnabled(true);
		return tbCompo;
	}

	public void setDataAdapter(DataAdapterDescriptor da) {
		boolean enable = (da instanceof IFieldsProvider && ((IFieldsProvider) da).supportsGetFieldsOperation(container
				.getjConfig()));
		setFieldProviderEnabled(enable);
	}

	protected void setFieldProviderEnabled(boolean enable) {
		btn.setEnabled(enable);
	}

	@Override
	public String getContextHelpId() {
		return ContextHelpIDs.WIZARD_QUERY_DIALOG;
	}
}
