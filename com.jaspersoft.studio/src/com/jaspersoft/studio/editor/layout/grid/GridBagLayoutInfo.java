/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
