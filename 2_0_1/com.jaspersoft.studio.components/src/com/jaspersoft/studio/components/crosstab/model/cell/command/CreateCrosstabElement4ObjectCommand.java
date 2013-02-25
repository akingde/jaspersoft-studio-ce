/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.Tag;

public class CreateCrosstabElement4ObjectCommand extends CreateElementCommand {
	protected ANode child;
	protected ANode parent;
	protected JasperDesign jDesign;

	public CreateCrosstabElement4ObjectCommand(ANode child, MCell parent,
			Rectangle location, int index) {
		super(parent, null, location, index);
		jDesign = parent.getJasperDesign();
		this.child = child;
		this.parent = parent;
	}

	@Override
	protected void createObject() {
		try {
			Tag tag = Tag.getExpression(child);

			var = Tag.createVariable(tag, ResetTypeEnum.REPORT, null, jDesign);
			srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name),
					tag.classname);

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
				jDesign.addVariable((JRDesignVariable) var);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (var != null)
			jDesign.removeVariable(var);
	}
}
