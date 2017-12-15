/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.cell.command;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.ColumnCell;
import com.jaspersoft.studio.components.table.Guide;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.property.IPostSetValue;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * When the table columns or cells changed width or height this generate the 
 * command to layout their contents
 * 
 * @author Orlandin Marco
 *
 */
public class PostSetSizeCell implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop,
			Object newValue, Object oldValue) {
		if (target instanceof MColumn && (prop.equals(StandardBaseColumn.PROPERTY_WIDTH) || prop.equals(DesignCell.PROPERTY_HEIGHT))) {
			MColumn mband = (MColumn) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getLayoutCommands(mband, jDesign, prop);
		}
		return null;
	}

	/**
	 * Crate the commands to layout the cells of the table. If the height changed are layouted only 
	 * the cells on the same line, otherwise all the table si layouted
	 * 
	 * @param mcell the cell or the column that has changed size
	 * @param jDesign the current JasperDesign
	 * @param prop the property changed
	 * @return the command to layout the table, will be empty if both the width and the height are
	 * not changed
	 */
	protected Command getLayoutCommands(MColumn mcell, JasperDesign jDesign, Object prop) {
		MTable mTable = mcell.getMTable();
		JSSCompoundCommand c = new JSSCompoundCommand("Layout Table Cells", mTable);
		if (prop.equals(StandardBaseColumn.PROPERTY_WIDTH)){
			//a width change can affect many columns other then the one resized, so we need to refresh the 
			//layout of the whole table
			JSSCompoundCommand layoutCommands = mTable.getTableManager().getLayoutCommand();
			layoutCommands.setReferenceNodeIfNull(mTable);
			c.add(layoutCommands);
		} else if (prop.equals(DesignCell.PROPERTY_HEIGHT)){
			JRPropertiesHolder[] pholder = new JRPropertiesHolder[3];
			pholder[2] = mTable.getValue();

			TableManager tb = mTable.getTableManager();
			
			ColumnCell cc = tb.getMatrixHelper().getColumnCell(new ColumnCell(mcell.getType(), mcell.getGrName(), mcell.getValue()));
			if (TableManager.isBottomOfTable(cc.type)) {
				Guide guide = cc.getNorth();
				createLayoutCellsCommands(jDesign, pholder, c, guide.getNext());
			} else {
				Guide guide = cc.getSouth();
				createLayoutCellsCommands(jDesign, pholder, c, guide.getPrev());
			}
		}
		return c;
	}

	/**
	 * Create the command to layout the passed cells
	 * 
	 * @param jDesign the current JasperDesign
 	 * @param pholder array of length 3 that contains on the first position the properties of the cell, in the second
 	 * the properties of the column and on the third the properties of the table. The first and second position is 
 	 * set by this method, the third must be already set when passing the array
	 * @param c compound command where the commands are added 
	 * @param cells the cells to layout
	 */
	protected void createLayoutCellsCommands(JasperDesign jDesign, JRPropertiesHolder[] pholder, JSSCompoundCommand c, List<ColumnCell> cells) {
		for (ColumnCell ccell : cells) {
			if (ccell.cell == null)
				continue;
			pholder[0] = ccell.cell;
			pholder[1] = ccell.column;
			ILayout layout = LayoutManager.getLayout(pholder, jDesign, null, new VerticalRowLayout());
			Rectangle r = ccell.getBounds();
			Dimension d = new Dimension(r.width, r.height);
			d = LayoutManager.getPaddedSize(ccell.cell.getLineBox(), d);
			c.add(new LayoutCommand(jDesign, ccell.cell, layout, d));
		}
	}

}
