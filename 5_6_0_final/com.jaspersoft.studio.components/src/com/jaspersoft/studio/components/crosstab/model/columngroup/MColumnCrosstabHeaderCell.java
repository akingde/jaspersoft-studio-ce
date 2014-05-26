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
package com.jaspersoft.studio.components.crosstab.model.columngroup;

import java.beans.PropertyChangeEvent;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class MColumnCrosstabHeaderCell extends MCell {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MColumnCrosstabHeaderCell() {
		super();
	}

	public MColumnCrosstabHeaderCell(ANode parent, JRCellContents jfRield, int index) {
		super(parent, jfRield, Messages.MCrosstabHeaderCell_crosstab_header, index);
	}

	@Override
	public Color getForeground() {
		if (getValue() == null)
			return ColorConstants.lightGray;
		return ColorConstants.black;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignCrosstabColumnGroup.PROPERTY_CROSSTAB_HEADER)) {
			for (INode n : getChildren()) {
				if (n instanceof MColumnCrosstabHeaderCell) {
					n.setValue(evt.getNewValue());
					break;
				}
			}
		}
		super.propertyChange(evt);
	}
}
