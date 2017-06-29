/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list.commands.element;

import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.Tag;

public class CreateListElement4ObjectCommand extends CreateElementCommand {
	protected ANode child;
	protected ANode parent;
	protected JRDesignDataset jDataset;
	protected JasperDesign jd;

	public CreateListElement4ObjectCommand(ANode child, MList parent,
			Rectangle location, int index) {
		super(parent, null, location, index);
		jd = parent.getJasperDesign();
		JRDatasetRun dr = parent.getList().getDatasetRun();
		if (dr != null) {
			String dbname = dr.getDatasetName();
			if (dbname != null)
				jDataset = (JRDesignDataset) jd.getDatasetMap().get(dbname);
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

	private JRDesignVariable var;

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
