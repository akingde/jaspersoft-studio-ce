/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.widgets.Composite;
/*
 * A J2DGraphicalEditorWithFlyoutPalette is a GraphicalEditorWithFlyoutPalette but a
 * J2DScrollingGraphicalViewer is created instead of a ScrollingGraphicalViewer.
 * <p>
 * Note that this code is the exact duplicate of the modifications made inside
 * J2DGraphicalEditor. The alternative was to derive this class from J2DGraphicalEditor
 * and duplicate all the GraphicalEditorWithPalette specific code. Duplicating only one
 * method makes us less dependent on the ancestors implementation.
 * </p>
 * 
 * @author Christophe Avare
 * @version $Revision: 1.1.2.1 $
 */
public abstract class J2DGraphicalEditorWithFlyoutPalette extends GraphicalEditorWithFlyoutPalette {
	
	/**
	 * Instantiates a new j2 d graphical editor with flyout palette.
	 */
	public J2DGraphicalEditorWithFlyoutPalette() {
		super();
	}

	/**
	 * Creates the GraphicalViewer on the specified <code>Composite</code>. A
	 * J2DScrollingGraphicalViewer is internally created.
	 * 
	 * @param parent
	 *            The parent composite
	 */
	protected void createGraphicalViewer(Composite parent) {
		System.out.println("Creating graphical viewer!!1"); //$NON-NLS-1$
		System.out.flush();
		GraphicalViewer viewer = new J2DScrollingGraphicalViewer();
		viewer.createControl(parent);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}
}
