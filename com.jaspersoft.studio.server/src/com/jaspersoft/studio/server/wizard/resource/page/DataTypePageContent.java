/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.GridDataUtil;
import com.jaspersoft.studio.utils.UIUtil;

public class DataTypePageContent extends APageContent {

	public DataTypePageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DataTypePageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datatype"; //$NON-NLS-1$
	}

	@Override
	public String getName() {
		return Messages.RDDataTypePage_datatype;
	}

	public Control createContent(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		UIUtil.createLabel(container, Messages.RDDataTypePage_datatype);

		Combo ttype = new Combo(container, SWT.BORDER | SWT.READ_ONLY);
		ttype.setItems(new String[] { 
				Messages.RDDataTypePage_text,
				Messages.RDDataTypePage_number, 
				Messages.RDDataTypePage_date,
				Messages.RDDataTypePage_datetime,
				Messages.RDDataTypePage_time});

		final Label tpatternLbl = UIUtil.createLabel(container, Messages.RDDataTypePage_pattern);

		final Text tpattern = new Text(container, SWT.BORDER);
		tpattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Label tminLbl = UIUtil.createLabel(container, Messages.RDDataTypePage_minvalue);

		final Text tmin = new Text(container, SWT.BORDER);
		tmin.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Label emptySpace1 = UIUtil.createLabel(container, ""); //$NON-NLS-1$

		final Button bmin = new Button(container, SWT.CHECK);
		bmin.setText(Messages.RDDataTypePage_strictmin);
		bmin.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Label tmaxLbl = UIUtil.createLabel(container, Messages.RDDataTypePage_maxvalue);

		final Text tmax = new Text(container, SWT.BORDER);
		tmax.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Label emptySpace2 = UIUtil.createLabel(container, ""); //$NON-NLS-1$

		final Button bmax = new Button(container, SWT.CHECK);
		bmax.setText(Messages.RDDataTypePage_strictmax);
		bmax.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bindingContext.bindValue(
				SWTObservables.observeText(tpattern, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "pattern")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(tmin, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "minValue")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(tmax, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "maxValue")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(bmin),
				PojoObservables.observeValue(res.getValue(), "strictMin")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(bmax),
				PojoObservables.observeValue(res.getValue(), "strictMax")); //$NON-NLS-1$
		
		ISWTObservableValue observeDataTypeComboSelection = 
				SWTObservables.observeSingleSelectionIndex(ttype);
		observeDataTypeComboSelection.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				if(event.diff!=null) {
					Object newValue = event.diff.getNewValue();
					boolean isText = false;
					if(newValue instanceof Integer && newValue.equals(0)) {
						// Text has been selected... we should show only pattern
						isText = true;
					}
					toggleControlStatus(tpattern, isText);
					toggleControlStatus(tpatternLbl, isText);
					toggleControlStatus(tmaxLbl, !isText);
					toggleControlStatus(tmax, !isText);
					toggleControlStatus(tminLbl, !isText);
					toggleControlStatus(tmin, !isText);
					toggleControlStatus(emptySpace1, !isText);
					toggleControlStatus(bmax, !isText);
					toggleControlStatus(emptySpace2, !isText);
					toggleControlStatus(bmin, !isText);
					container.layout();
				}
			}
		});
		bindingContext.bindValue(observeDataTypeComboSelection, PojoObservables
				.observeValue(getProxy(res.getValue()), "dataType")); //$NON-NLS-1$
		return container;
	}

	private void toggleControlStatus(Control control, boolean enable){
		control.setEnabled(enable);
		control.setVisible(enable);
		GridDataUtil.gridDataExclude(control, !enable);		
	}
	
	private ShiftProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ShiftProxy proxy = new ShiftProxy();

	class ShiftProxy {
		private ResourceDescriptor rd;
		private final int shift = 1;

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setDataType(int type) {
			rd.setDataType((byte) (type + shift));
		}

		public int getDataType() {
			return rd.getDataType() - shift;
		}
	}
	
	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editDataType";
	}
}
