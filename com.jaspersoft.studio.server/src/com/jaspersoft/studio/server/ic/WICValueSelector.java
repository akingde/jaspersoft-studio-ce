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
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesMap;

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
				JRParameter prm = null;

				if (aw.getElement() instanceof JRParameter)
					prm = ((JRParameter) aw.getElement());
				else if (aw.getTColumn().getValue() instanceof JRParameter)
					prm = (JRParameter) aw.getTColumn().getValue();
				else if (aw.getTColumn().getValue1() instanceof JRParameter)
					prm = (JRParameter) aw.getTColumn().getValue1();
				else if (aw.getElement() instanceof PropertyExpressionsDTO) {
					Object obj = ((PropertyExpressionsDTO) aw.getElement()).getJrElement();
					if (obj instanceof JRParameter)
						prm = (JRParameter) obj;
				}

				if (prm != null) {
					JRPropertiesMap pmap = prm.getPropertiesMap();
					if (aw.getElement() instanceof JRPropertiesMap)
						pmap = (JRPropertiesMap) aw.getElement();
					String type = pmap.getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_TYPE);
					if (aw.getElement() instanceof PropertyExpressionsDTO)
						type = ((PropertyExpressionsDTO) aw.getElement())
								.getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_TYPE, false).getValue();

					if (type != null && (type.equals(ICTypes.SINGLE_LOV.name())
							|| type.equals(ICTypes.SINGLE_LOV_RADIO.name()) || type.equals(ICTypes.MULTI_LOV.name())
							|| type.equals(ICTypes.MULTI_LOV_CHECKBOX.name())))
						showLOV(cmp, prm);
					else if (type != null && (type.equals(ICTypes.SINGLE_QUERY.name())
							|| type.equals(ICTypes.SINGLE_QUERY_RADIO.name()) || type.equals(ICTypes.MULTI_QUERY.name())
							|| type.equals(ICTypes.MULTI_QUERY_CHECKBOX.name())))
						showQuery(cmp, prm);
					else
						showText(cmp, prm);
				}
			}

		});
	}

	private void showText(Composite cmp, JRParameter prm) {
		String v = prm.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_VALUE);
		TextDialog d = new TextDialog(cmp.getShell(), Misc.nvl(v));
		if (d.open() == Dialog.OK) {
			aw.setValue(d.getValue());
			fillValue();
		}
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
