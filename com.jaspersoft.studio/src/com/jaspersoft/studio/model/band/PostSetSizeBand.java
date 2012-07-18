package com.jaspersoft.studio.model.band;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.views.properties.IPropertySource;

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
		Dimension d = new Dimension(w, band.getHeight());
		ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { band }, jDesign, null);
		return new LayoutCommand(band, layout, d);
	}

}
