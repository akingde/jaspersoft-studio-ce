/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.utils.UIUtils;

public class RDInputControlPage extends AResourcePage {

	public RDInputControlPage(ANode parent, MInputControl resource) {
		super(Messages.RDInputControlPage_id, parent, resource);
		setTitle(Messages.RDInputControlPage_title);
		setDescription(Messages.RDInputControlPage_desc);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(tabFolder);
	}

	protected void createDatasourceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDInputControlPage_inputcontroltableitem);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		new Label(composite, SWT.NONE);

		Composite cmp = new Composite(composite, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmp.setLayout(new RowLayout());
		cmp.setBackground(parent.getBackground());

		Button bmand = new Button(cmp, SWT.CHECK);
		bmand.setText(Messages.RDInputControlPage_mandatory);

		Button bread = new Button(cmp, SWT.CHECK);
		bread.setText(Messages.RDInputControlPage_readonly);

		Button bvisible = new Button(cmp, SWT.CHECK);
		bvisible.setText(Messages.RDInputControlPage_visible);

		UIUtils.createLabel(composite, Messages.RDInputControlPage_type);

		final Combo ctype = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		ctype.setItems(new String[] { "Boolean", "Single Value", //$NON-NLS-1$ //$NON-NLS-2$
				Messages.RDInputControlPage_singlselectlistofvalues,
				Messages.RDInputControlPage_singleselectlovradio,
				Messages.RDInputControlPage_multiselectlov,
				Messages.RDInputControlPage_multiselectlovradio,
				Messages.RDInputControlPage_singlselectquery, Messages.RDInputControlPage_singleselectqueryradio,
				Messages.RDInputControlPage_multiselectquery, Messages.RDInputControlPage_multiselectquerycheckbox });

		stackComposite = new Composite(composite, SWT.NONE);
		final StackLayout stackLayout = new StackLayout();
		stackComposite.setLayout(stackLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		stackComposite.setLayoutData(gd);

		cvalue = new Composite(stackComposite, SWT.NONE);
		createSingleValue(stackComposite);
		createLOV(stackComposite);
		createQuery(stackComposite);

		ctype.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleTypeChanged(ctype, stackLayout);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		bindingContext.bindValue(SWTObservables
				.observeSingleSelectionIndex(ctype), PojoObservables
				.observeValue(getProxy(res.getValue()), "controlType")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeSelection(bmand),
				PojoObservables.observeValue(res.getValue(), "mandatory")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(bread),
				PojoObservables.observeValue(res.getValue(), "readOnly")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(bvisible),
				PojoObservables.observeValue(res.getValue(), "visible")); //$NON-NLS-1$

		handleTypeChanged(ctype, stackLayout);
	}

	protected void handleTypeChanged(Combo ctype, StackLayout stackLayout) {
		int s = ctype.getSelectionIndex();
		if (s < 1)
			stackLayout.topControl = cvalue;
		else if (s < 2)
			stackLayout.topControl = csinglevalue;
		else if (s < 6)
			stackLayout.topControl = clov;
		else
			stackLayout.topControl = cquery;
		stackComposite.layout();
	}

	private Composite stackComposite;
	private Composite cvalue;
	private Group clov;
	private Group csinglevalue;
	private TabFolder cquery;

	protected void createSingleValue(Composite composite) {
		csinglevalue = new Group(composite, SWT.NONE);
		csinglevalue.setText(Messages.RDInputControlPage_datatype);
		csinglevalue.setLayout(new GridLayout(3, false));

		new SelectorDataType().createControls(csinglevalue, parent, res);
	}

	protected void createLOV(Composite composite) {
		clov = new Group(composite, SWT.NONE);
		clov.setText(Messages.RDInputControlPage_lov);
		clov.setLayout(new GridLayout(3, false));

		new SelectorLov().createControls(clov, parent, res);
	}

	protected void createQuery(Composite composite) {
		cquery = new TabFolder(composite, SWT.NONE);

		TabItem item = new TabItem(cquery, SWT.NONE);
		item.setText(Messages.RDInputControlPage_queryresource);

		Composite cmp = new Composite(cquery, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		item.setControl(cmp);

		new SelectorQuery().createControls(cmp, parent, res);

		item = new TabItem(cquery, SWT.NONE);
		item.setText(Messages.RDInputControlPage_valueandvisiblecolumns);

		cmp = new Composite(cquery, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		item.setControl(cmp);

		UIUtils.createLabel(cmp, Messages.RDInputControlPage_valuecolumn);

		Text tvalue = new Text(cmp, SWT.BORDER);
		tvalue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		cmp = new Composite(cmp, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		qvct = new QueryVisibleColumnsTable(cmp, res.getValue());

		bindingContext.bindValue(
				SWTObservables.observeText(tvalue, SWT.Modify), PojoObservables
						.observeValue(res.getValue(), "queryValueColumn")); //$NON-NLS-1$
	}

	@Override
	public void dispose() {
		qvct.dispose();
		super.dispose();
	}

	private ShiftMapProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ShiftMapProxy proxy = new ShiftMapProxy();
	private QueryVisibleColumnsTable qvct;

	class ShiftMapProxy {
		private ResourceDescriptor rd;
		private final int[] shift = new int[] {
				ResourceDescriptor.IC_TYPE_BOOLEAN,
				ResourceDescriptor.IC_TYPE_SINGLE_VALUE,
				ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES,
				ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO,
				ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES,
				ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX,
				ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY,
				ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO,
				ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY,
				ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX };

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setControlType(int type) {
			rd.setControlType((byte) shift[type]);
		}

		public int getControlType() {
			for (int i = 0; i < shift.length; i++)
				if (shift[i] == rd.getControlType())
					return i;
			return -1;
		}
	}
}
