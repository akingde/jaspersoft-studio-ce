/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWTextButton;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;

public class WICValueSelector extends AWTextButton {

	public WICValueSelector(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createControl(Composite parent) {
		aw.getTColumn().setLabelEditable(true);
		super.createControl(parent);
	}

	@Override
	protected void createButton(Composite cmp) {
		super.createButton(cmp);
		btn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (aw.getElement() instanceof JRParameter) {
					JRParameter prm = ((JRParameter) aw.getElement());
					String type = prm.getPropertiesMap()
							.getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_TYPE);
					if (type.equals(ICTypes.SINGLE_LOV.name()) || type.equals(ICTypes.MULTI_LOV.name()))
						showLOV(cmp, prm);
					else if (type.equals(ICTypes.SINGLE_QUERY.name()) || type.equals(ICTypes.MULTI_QUERY.name()))
						showQuery(cmp, prm);
				}
			}

		});
	}

	private void showLOV(Composite cmp, JRParameter prm) {
		String v = prm.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_VALUE);
		LovDialog d = new LovDialog(cmp.getShell(), Misc.nvl(v));
		if (d.open() == Dialog.OK) {
			aw.setValue(d.getValue());
			fillValue();
		}
	}

	private void showQuery(Composite cmp, JRParameter prm) {
		String v = prm.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_VALUE);
		Object v1 = aw.getTColumn().getValue1();
		IFilterQuery fq = v1 instanceof IFilterQuery ? (IFilterQuery) v1 : null;
		QueryDialog d = new QueryDialog(cmp.getShell(), Misc.nvl(v), fq);
		if (d.open() == Dialog.OK) {
			aw.setValue(d.getValue());
			fillValue();
		}
	}
}
