/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.IPostSetValue;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

public class PostSetSizeBand implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MBand && prop.equals(JRDesignBand.PROPERTY_HEIGHT)) {
			MBand mband = (MBand) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getBandResizeCommand(mband, jDesign, (String) prop);
		}
		if (target instanceof MReport
				&& (prop.equals(JasperDesign.PROPERTY_PAGE_WIDTH) || prop.equals(JasperDesign.PROPERTY_LEFT_MARGIN) || prop
						.equals(JasperDesign.PROPERTY_RIGHT_MARGIN))) {
			MReport mrep = (MReport) target;
			JasperDesign jDesign = mrep.getJasperDesign();
			JSSCompoundCommand c = new JSSCompoundCommand(mrep);
			for (INode n : mrep.getChildren()) {
				if (n instanceof MBand && n.getValue() != null)
					c.add(getBandResizeCommand((MBand) n, jDesign, (String) prop));
			}
			if (!c.isEmpty())
				return c;
		}
		return null;
	}

	public Command getBandResizeCommand(MBand mband, JasperDesign jDesign, String property) {
		CompoundCommand cmd = null;
		JRDesignBand band = mband.getValue();
		int w = Math.max(0, jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin());
		// Check if the size is valid
		if (property.equals(JRDesignBand.PROPERTY_HEIGHT)) {
			int maxHeight = ModelUtils.getMaxBandHeight(band, jDesign);
			if (band.getHeight() > maxHeight) {
				cmd = new CompoundCommand();

				SetValueCommand c = new SetValueCommand();
				c.setTarget(mband);
				c.setPropertyId(JRDesignBand.PROPERTY_HEIGHT);
				c.setPropertyValue(Math.max(0, maxHeight));

				cmd.add(c);
			}
		}
		Dimension d = new Dimension(w, band.getHeight());
		ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { band }, jDesign, null);
		if (cmd != null) {
			cmd.add(new LayoutCommand(jDesign, band, layout, d));
			return cmd;
		}
		return new LayoutCommand(jDesign, band, layout, d);

	}

}
