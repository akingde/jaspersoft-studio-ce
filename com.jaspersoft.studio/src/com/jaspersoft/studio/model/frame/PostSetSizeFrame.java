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
	public Command postSetValue(IPropertySource target, Object prop, Object value) {
		if (target instanceof MFrame
				&& (prop.equals(JRDesignElement.PROPERTY_HEIGHT) || prop.equals(JRDesignElement.PROPERTY_WIDTH))) {
			MFrame mband = (MFrame) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign);
		}
		return null;
	}

	public Command getResizeCommand(MFrame mband, JasperDesign jDesign) {
		JRDesignFrame band = mband.getValue();
		Dimension d = new Dimension(band.getWidth(), band.getHeight());
		ILayout layout = LayoutManager.getLayout(mband.getPropertyHolder(), jDesign, null);
		return new LayoutCommand(band, layout, d);
	}

}
