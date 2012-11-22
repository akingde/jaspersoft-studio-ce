/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table.part.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.components.table.part.TableCellEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.AContainerMoveEditPolicy;

/*
 * The Class BandMoveEditPolicy.
 * 
 * @author Chicu Veaceslav
 */
public class TableCellMoveEditPolicy extends AContainerMoveEditPolicy {

	protected Command getResizeCommand(ChangeBoundsRequest request) {
		return CreateResize.createResizeCommand(request,
				(TableCellEditPart) getHost());
	}

}
