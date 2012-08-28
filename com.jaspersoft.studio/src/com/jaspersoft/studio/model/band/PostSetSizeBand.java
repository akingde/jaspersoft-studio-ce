/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.band;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.editor.gef.parts.band.BandResizeTracker;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeBand implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object value) {
		if (target instanceof MBand && prop.equals(JRDesignBand.PROPERTY_HEIGHT)) {
			MBand mband = (MBand) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getBandResizeCommand(mband, jDesign);
		}
		if (target instanceof MReport
				&& (prop.equals(JasperDesign.PROPERTY_PAGE_WIDTH) || prop.equals(JasperDesign.PROPERTY_LEFT_MARGIN) || prop
						.equals(JasperDesign.PROPERTY_RIGHT_MARGIN))) {
			MReport mrep = (MReport) target;
			JasperDesign jDesign = mrep.getJasperDesign();
			CompoundCommand c = new CompoundCommand();
			for (INode n : mrep.getChildren()) {
				if (n instanceof MBand && n.getValue() != null)
					c.add(getBandResizeCommand((MBand) n, jDesign));
			}
			if (!c.isEmpty())
				return c;
		}
		return null;
	}

	public Command getBandResizeCommand(MBand mband, JasperDesign jDesign) {
		JRDesignBand band = mband.getValue();
		int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
		//Check if the size is valid
		int maxHeight = BandResizeTracker.getMaxBandHeight(band, jDesign);
		if (band.getHeight()>maxHeight){
			band.setHeight(maxHeight-1);
		}
		Dimension d = new Dimension(w, band.getHeight());
		ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { band }, jDesign, null);
		return new LayoutCommand(band, layout, d);
	}

}
