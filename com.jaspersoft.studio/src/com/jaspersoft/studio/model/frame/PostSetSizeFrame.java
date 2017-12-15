/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.frame;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeFrame implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MFrame
				&& (prop.equals(JRDesignElement.PROPERTY_HEIGHT) || prop.equals(JRDesignElement.PROPERTY_WIDTH))) {
			MFrame mband = (MFrame) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign);
		}
		return null;
	}

	public Command getResizeCommand(MFrame frame, JasperDesign jDesign) {
		JRDesignFrame jrFrame = frame.getValue();
		Dimension d = new Dimension(jrFrame.getWidth(), jrFrame.getHeight());
		d = LayoutManager.getPaddedSize(jrFrame, d);
		ILayout layout = LayoutManager.getLayout(frame.getPropertyHolder(), jDesign, null);
		return new LayoutCommand(jDesign, jrFrame, layout, d);
	}

}
