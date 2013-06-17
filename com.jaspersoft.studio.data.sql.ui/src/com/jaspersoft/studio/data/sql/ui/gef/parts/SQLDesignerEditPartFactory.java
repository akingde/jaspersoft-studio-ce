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
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class SQLDesignerEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof MFrom)
			editPart = new FromEditPart();
		else if (model instanceof MFromTable)
			editPart = new TableEditPart();
		else if (model instanceof TableJoin)
			editPart = new RelationshipPart();
		else if (model instanceof MColumn)
			editPart = new ColumnEditPart();
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

}
