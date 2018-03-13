/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class WInputControlPathSelector extends WResourcePathSelector {

	public WInputControlPathSelector(AWidget aw) {
		super(aw);
	}

	private ControlDecoration decorator;

	@Override
	protected void createControl(Composite parent) {
		super.createControl(parent);

		decorator = new ControlDecoration(txt, SWT.CENTER);
		decorator.setDescriptionText(Messages.ICParameterContributor_7);
		decorator.setImage(
				FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
	}

	@Override
	protected void postSelection(ResourceDescriptor rd) {
		String name = getName();
		if (rd != null) {
			if (rd.getName().equals(name))
				aw.setValue(rd.getUriString());
			else
				UIUtils.showWarning(
						"Input Control name must be the same as parameter name to work on Jaspersoft Server.");
		} else
			aw.setValue(null);
	}

	@Override
	protected void fillValue() {
		super.fillValue();
		String p = getText();
		String pname = getName();
		if (!Misc.isNullOrEmpty(p) && pname != null) {
			int ind = p.lastIndexOf('/');
			if (ind >= 0)
				p = p.substring(ind + 1);
			if (!p.equals(pname)) {
				decorator.show();
				return;
			}
		}
		decorator.hide();
	}

	@Override
	protected String[] getCompatibleResources() {
		return new String[] { ResourceMediaType.INPUT_CONTROL_CLIENT_TYPE };
	}

	@Override
	protected boolean isResourceCompatible(AMResource r) {
		String name = getName();
		return name != null && r.getValue().getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)
				&& r.getValue().getName().equals(name);
	}

	@Override
	protected String getName() {
		JRDesignParameter p = null;
		if (aw.getElement() instanceof JRDesignParameter)
			p = (JRDesignParameter) aw.getElement();
		else if (aw.getTColumn().getValue1() instanceof JRDesignParameter)
			p = (JRDesignParameter) aw.getTColumn().getValue1();
		if (p != null)
			return p.getName();
		return null;
	}
}
