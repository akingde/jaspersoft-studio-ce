/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.Pair;

/**
 * Static method that can be used to add or remove groups from the crosstabs
 */
public class CrosstabUtil {
	
	public static JRDesignCrosstabRowGroup createRowGroup(JasperDesign jasperDesign, JRDesignCrosstab jrCrosstab,String name, CrosstabTotalPositionEnum total) {
		
		int width = 60;
		for(JRCrosstabRowGroup group : jrCrosstab.getRowGroups()){
			width = group.getWidth();
			break;
		}
		
		JRDesignCrosstabRowGroup jrGroup = new JRDesignCrosstabRowGroup();
		jrGroup.setTotalPosition(total);
		jrGroup.setName(ModelUtils.getDefaultName(jrCrosstab, name));
		jrGroup.setWidth(width);
		
		

		JRDesignExpression exp = new JRDesignExpression();
		exp.setText(""); //$NON-NLS-1$
		exp.setValueClass(String.class);
		JRDesignCrosstabBucket bucket = new JRDesignCrosstabBucket();
		bucket.setExpression(exp);
		jrGroup.setBucket(bucket);

		JRDesignCellContents headerCell = new JRDesignCellContents();
		jrGroup.setHeader(headerCell);

		exp = new JRDesignExpression();
		exp.setText("$V{" + jrGroup.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

		JRDesignTextField tf = (JRDesignTextField) new MTextField()
				.createJRElement(jasperDesign);
		tf.setX(0);
		tf.setY(0);
		tf.setWidth(jrGroup.getWidth());
		tf.setHeight(20);
		if ("Crosstab Data Text" != null && jasperDesign.getStylesMap().containsKey("Crosstab Data Text")) { //$NON-NLS-1$ //$NON-NLS-2$
			tf.setStyle((JRStyle) jasperDesign.getStylesMap().get(
					"Crosstab Data Text")); //$NON-NLS-1$
		}
		tf.setExpression(exp);

		headerCell.addElement(tf); // NOI18N
		JRDesignCellContents totalCell = new JRDesignCellContents();
		JRDesignStaticText stext = new JRDesignStaticText();
		stext.setX(0);
		stext.setY(0);
		stext.setWidth(jrGroup.getWidth());
		stext.setHeight(20);
		stext.setText(Messages.common_total + " " + jrGroup.getName()); //$NON-NLS-1$
		totalCell.addElement(stext);
		jrGroup.setTotalHeader(totalCell);
		return jrGroup;
	}

	/**
	 * Add a row groups to a crosstab in a specific index. Also support as parameter an hash map with the cell needed for the group.
	 * If the cell that should be created are found inside this map then it get them from it, otherwise they will be created.
	 * This is useful for undo of a cancel operation, to restore all the old cells insted to create new ones.
	 */
	public static void addRowGroup(JRDesignCrosstab jrCross, JRDesignCrosstabRowGroup jrRowGr, int index, Map<String, JRCrosstabCell> cellsToRestore) throws JRException {
		JRCrosstabRowGroup lastGroup = null; 
		if (!jrCross.getRowGroupsList().isEmpty()){
			lastGroup = jrCross.getRowGroupsList().get(jrCross.getRowGroupsList().size()-1);
		}
		
		if (index >= 0 && index < jrCross.getRowGroupsList().size())
			jrCross.addRowGroup(index, jrRowGr);
		else
			jrCross.addRowGroup(jrRowGr);

		if (!jrCross.getCellsMap().containsKey(new Pair<String, String>(null, null))) {
			JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
			dT.setColumnTotalGroup(null);
			dT.setRowTotalGroup(null);
			jrCross.addCell(dT);
			dT.setHeight(20);
			dT.setWidth(jrRowGr.getWidth());
		}

		if (cellsToRestore.containsKey(null)){
			JRCrosstabCell dT = cellsToRestore.get(null);
			jrCross.addCell((JRDesignCrosstabCell)dT);
		} else {
			JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
			dT.setRowTotalGroup(jrRowGr.getName());
			jrCross.addCell(dT);
			dT.setHeight(20);
			if (lastGroup != null){
				Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), dT.getColumnTotalGroup());
				JRCrosstabCell cell = jrCross.getCellsMap().get(key);
				dT.setWidth(cell.getWidth());
			} else {
				dT.setWidth(jrRowGr.getWidth());
			}
		}
		
		List<JRCrosstabColumnGroup> columns = jrCross.getColumnGroupsList();
		if (columns != null)
			for (JRCrosstabColumnGroup c : columns) {
				if (cellsToRestore.containsKey(c.getName())){
					JRDesignCrosstabCell cell = (JRDesignCrosstabCell)cellsToRestore.get(c.getName());
					jrCross.addCell(cell);
				} else {
					JRDesignCrosstabCell cell = new JRDesignCrosstabCell();
					cell.setRowTotalGroup(jrRowGr.getName());
					cell.setColumnTotalGroup(c.getName());
					jrCross.addCell(cell);
					cell.setHeight(c.getHeight());
					
					if (lastGroup != null){
						Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), c.getName());
						JRCrosstabCell otherCell = jrCross.getCellsMap().get(key);
						cell.setWidth(otherCell.getWidth());
					} else {
						cell.setWidth(jrRowGr.getWidth());
					}
				}
			}

		jrCross.preprocess();
	}
	
	public static void addRowGroup(JRDesignCrosstab jrCross, JRDesignCrosstabRowGroup jrRowGr, int index) throws JRException {
		JRCrosstabRowGroup lastGroup = null; 
		if (!jrCross.getRowGroupsList().isEmpty()){
			lastGroup = jrCross.getRowGroupsList().get(jrCross.getRowGroupsList().size()-1);
		}
		
		if (index >= 0 && index < jrCross.getRowGroupsList().size())
			jrCross.addRowGroup(index, jrRowGr);
		else
			jrCross.addRowGroup(jrRowGr);

		if (!jrCross.getCellsMap().containsKey(new Pair<String, String>(null, null))) {
			JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
			dT.setColumnTotalGroup(null);
			dT.setRowTotalGroup(null);
			jrCross.addCell(dT);
			dT.setHeight(20);
			dT.setWidth(jrRowGr.getWidth());
		}

		JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
		dT.setRowTotalGroup(jrRowGr.getName());
		jrCross.addCell(dT);
		dT.setHeight(20);
		if (lastGroup != null){
			Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), dT.getColumnTotalGroup());
			JRCrosstabCell cell = jrCross.getCellsMap().get(key);
			dT.setWidth(cell.getWidth());
		} else {
			dT.setWidth(jrRowGr.getWidth());
		}
		
		List<JRCrosstabColumnGroup> columns = jrCross.getColumnGroupsList();
		if (columns != null)
			for (JRCrosstabColumnGroup c : columns) {
				JRDesignCrosstabCell cell = new JRDesignCrosstabCell();
				cell.setRowTotalGroup(jrRowGr.getName());
				cell.setColumnTotalGroup(c.getName());
				jrCross.addCell(cell);
				cell.setHeight(c.getHeight());
				
				if (lastGroup != null){
					Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), c.getName());
					JRCrosstabCell otherCell = jrCross.getCellsMap().get(key);
					cell.setWidth(otherCell.getWidth());
				} else {
					cell.setWidth(jrRowGr.getWidth());
				}
			}

		jrCross.preprocess();
	}

}
