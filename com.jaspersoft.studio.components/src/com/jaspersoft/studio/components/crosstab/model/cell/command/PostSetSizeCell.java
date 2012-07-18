package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeCell implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop,
			Object value) {
		if (target instanceof MCell
				&& (prop.equals(JRDesignCrosstabCell.PROPERTY_WIDTH) || prop
						.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT))) {
			MCell mband = (MCell) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign);
		}
		return null;
	}

	public Command getResizeCommand(MCell mcell, JasperDesign jDesign) {
		JRDesignCellContents band = mcell.getValue();
		ILayout layout = LayoutManager.getLayout(mcell.getPropertyHolder(),
				jDesign, null);
		Rectangle r = mcell.getBounds();
		Dimension d = new Dimension(r.width, r.height);
		return new LayoutCommand(band, layout, d);
	}

}
