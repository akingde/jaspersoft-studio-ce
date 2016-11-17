/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.Tag;

import net.sf.jasperreports.crosstabs.JRCrosstabDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

public class CreateCrosstabElement4ObjectCommand extends CreateElementCommand {
	protected ANode child;
	protected ANode parent;
	protected JRDesignDataset jDataset;
	private JRDesignVariable var;
	private JasperDesign jd;

	public CreateCrosstabElement4ObjectCommand(ANode child, MCell parent,
			Rectangle location, int index) {
		super(parent, null, location, index);
		jd = parent.getJasperDesign();
		jDataset = jd.getMainDesignDataset();
		JRCrosstabDataset d = parent.getMCrosstab().getValue().getDataset();
		JRDesignDatasetRun dr = (JRDesignDatasetRun) d.getDatasetRun();
		if (dr != null) {
			String dbname = dr.getDatasetName();
			if (dbname != null){
				jDataset = (JRDesignDataset) jd.getDatasetMap().get(dbname);
			}
		}
		this.child = child;
		this.parent = parent;
	}

	@Override
	protected void createObject() {
		try {
			Tag tag = Tag.getExpression(child);

			var = Tag.createVariable(tag, ResetTypeEnum.REPORT, null, jDataset);
			srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname, jd);

			jrElement = srcNode.getValue();
			super.createObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute() {
		super.execute();
		try {
			if (var != null)
				jDataset.addVariable((JRDesignVariable) var);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (var != null)
			jDataset.removeVariable(var);
	}
}
