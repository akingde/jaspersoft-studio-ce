/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * Create and execute a layout command on the whole crosstab. The command
 * to layout the crosstab is created and executed at execution time.
 * 
 * 
 * @author Orlandin Marco
 */
public class LazyCrosstabLayoutCommand extends Command{
	
	/**
	 * The inner layout command
	 */
	private JSSCompoundCommand cmd = null;
	
	/**
	 * The container to layout
	 */
	private MCrosstab container;
	
	/**
	 * Create the command
	 * 
	 * @param container the container to layout, if 
	 * null the command can`t not be executed
	 */
	public LazyCrosstabLayoutCommand(MCrosstab container) {
		this.container = container;
	}
	
	@Override
	public boolean canExecute() {
		return container != null;
	}

	@Override
	public void execute() {
		cmd = new JSSCompoundCommand(container);
		createLayoutCommand(cmd);
		if (cmd != null){
			cmd.execute();
		}
	}

	@Override
	public void undo() {
		if (cmd != null){
			cmd.undo();
		}
	}
	
	/**
	 * Add to the passed command the commands to layout all the cells
	 * of the crosstab
	 * 
	 * @param c a not null compound command where all the single commands
	 * to layout a cell are added
	 */
	public void createLayoutCommand(JSSCompoundCommand c){
		if (container == null) return;
		JRDesignCrosstab crosstab = container.getValue();
		for(JRCrosstabRowGroup rowGroup : crosstab.getRowGroups()){
			createRelayoutCommand(rowGroup.getTotalHeader(), c);
			createRelayoutCommand(rowGroup.getHeader(), c);
		}
		for(JRCrosstabColumnGroup colGroup : crosstab.getColumnGroups()){
			createRelayoutCommand(colGroup.getTotalHeader(), c);
			createRelayoutCommand(colGroup.getHeader(), c);
			createRelayoutCommand(colGroup.getCrosstabHeader(), c);
		}
		for(JRCrosstabCell cell : crosstab.getCellsList()){
			createRelayoutCommand(cell, c);
		}
	}
	
	/**
	 * Create a layout command to layout the container passed as parameter
	 * 
	 * @param jrElement the cell contents to layout
	 * @param c a not null compound command where all the single commands
	 * to layout a cell are added
	 */
	private void createRelayoutCommand(JRCellContents jrElement, JSSCompoundCommand c){	
		if (jrElement != null){
			//Search the parent group
			JRElementGroup jrGroup = (JRElementGroup) jrElement;
			//search the size of the parent
			Dimension d = new Dimension();
			d.setSize(new Dimension(jrElement.getWidth(), jrElement.getHeight()));
			
			//get the properties of the parent
			JRPropertiesMap map = jrElement.getPropertiesMap();
			String str = map.getProperty(ILayout.KEY);
			if (str == null){
				str = VerticalRowLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);
			d = LayoutManager.getPaddedSize(jrElement, d);
			c.add(new LayoutCommand(container.getJasperDesign(), jrGroup, parentLayout, d));
		}
	}
	
	/**
	 * Create a layout command to layout the container passed as parameter
	 * 
	 * @param jrElement the cell contents to layout
	 * @param c a not null compound command where all the single commands
	 * to layout a cell are added
	 */
	private void createRelayoutCommand(JRCrosstabCell jrElement, JSSCompoundCommand c){	
		if (jrElement != null) {
			createRelayoutCommand(jrElement.getContents(), c);
		}
	}
	
}
