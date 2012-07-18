package com.jaspersoft.studio.components.table.model.cell.command;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeCell implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop,
			Object value) {
		if (target instanceof MColumn
				&& (prop.equals(StandardBaseColumn.PROPERTY_WIDTH) || prop
						.equals(DesignCell.PROPERTY_HEIGHT))) {
			MCell mband = (MCell) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign);
		}
		return null;
	}

	public Command getResizeCommand(MCell mcell, JasperDesign jDesign) {
		DesignCell band = mcell.getCell();
		ILayout layout = LayoutManager.getLayout(mcell.getPropertyHolder(),
				jDesign, null);
		Rectangle r = mcell.getBounds();
		Dimension d = new Dimension(r.width, r.height);
		return new LayoutCommand(band, layout, d);
	}

}
