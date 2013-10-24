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
package com.jaspersoft.studio.compatibility;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.ToolUtilities;

/**
 * On eclipse 3.6 gef has a bug on the library method getSelectionWithoutDependants
 * since it try to cast always to graphical edit part. This was resolved in the next 
 * version but to assure back compatibility and avoid class cast exception we imported it
 * 
 * @author Orlandin Marco
 *
 */
public class ToolUtilitiesCompatibility {
	
	/**
	 * Returns a list containing the top level selected edit parts based on the
	 * passed in list of selection.
	 * 
	 * @param selectedParts
	 *            the complete selection
	 * @return the selection excluding dependants
	 */
	public static List<?> getSelectionWithoutDependants(List<?> selectedParts) {
		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < selectedParts.size(); i++) {
			EditPart editpart = (EditPart) selectedParts.get(i);
			if (!ToolUtilities.isAncestorContainedIn(selectedParts, editpart))
				result.add(editpart);
		}
		return result;
	}

}
