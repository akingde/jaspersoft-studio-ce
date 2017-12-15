/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoinDetail;
import com.jaspersoft.studio.model.MRoot;

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class SQLDesignerEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof MRoot)
			editPart = new QueryEditPart();
		else if (model instanceof MFrom)
			editPart = new FromEditPart();
		else if (model instanceof MFromTable)
			editPart = new TableEditPart();
		else if (model instanceof TableJoin)
			editPart = new RelationshipPart();
		else if (model instanceof TableJoinDetail)
			editPart = new RelationshipDetailPart();
		else if (model instanceof MSQLColumn)
			editPart = new ColumnEditPart();
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

}
