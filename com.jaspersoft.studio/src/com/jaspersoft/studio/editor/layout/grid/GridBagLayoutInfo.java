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
package com.jaspersoft.studio.editor.layout.grid;


/**
 * The {@code GridBagLayoutInfo} is an utility class for
 * {@code GridBagLayoutUtil} layout.
 * It stores align and size for every component within a container.
 * <p>
 */
public class GridBagLayoutInfo {

    int width, height;          /* number of  cells: horizontal and vertical */
    int startx, starty;         /* starting point for layout */
    int minWidth[];             /* largest minWidth in each column */
    int minHeight[];            /* largest minHeight in each row */
    double weightX[];           /* largest weight in each column */
    double weightY[];           /* largest weight in each row */

    /**
     * Creates an instance of GridBagLayoutInfo representing {@code GridBagLayout}
     * grid cells with it's own parameters.
     * @param width the columns
     * @param height the rows
     */
    GridBagLayoutInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
