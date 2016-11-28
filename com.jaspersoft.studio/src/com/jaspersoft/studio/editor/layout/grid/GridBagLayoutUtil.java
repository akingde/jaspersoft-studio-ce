/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;

/**
 * Calculate the disposition of a set of JRElements for a JSSGridLayout
 * 
 * @author Orlandin Marco
 *
 */
public class GridBagLayoutUtil {

    static final int EMPIRICMULTIPLIER = 2;
    /**
     * This field is no longer used to reserve arrays and kept for backward
     * compatibility. Previously, this was
     * the maximum number of grid positions (both horizontal and
     * vertical) that could be laid out by the grid bag layout.
     * Current implementation doesn't impose any limits
     * on the size of a grid.
     */
    protected static final int MAXGRIDSIZE = 512;

    /**
     * The smallest grid that can be laid out by the grid bag layout.
     */
    protected static final int MINSIZE = 1;
    /**
     * The preferred grid size that can be laid out by the grid bag layout.
     */
    protected static final int PREFERREDSIZE = 2;

    /**
     * This hashtable maintains the association between
     * a JRElement and its gridbag constraints.
     */
    protected Hashtable<JRElement,GridBagConstraints> comptable;

    /**
     * This field holds a gridbag constraints instance
     * containing the default values, so if a component
     * does not have gridbag constraints associated with
     * it, then the component will be assigned a
     * copy of the <code>defaultConstraints</code>.
     */
    protected GridBagConstraints defaultConstraints;

    /**
     * This field holds the layout information
     * for the gridbag.  The information in this field
     * is based on the most recent validation of the
     * gridbag.
     * If <code>layoutInfo</code> is <code>null</code>
     * this indicates that there are no components in
     * the gridbag or if there are components, they have
     * not yet been validated.
     */
    protected GridBagLayoutInfo layoutInfo;

    /**
     * This field holds the overrides to the column minimum
     * width.  If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the minimum columns
     * widths have been calculated.
     * If columnWidths has more elements than the number of
     * columns, columns are added to the gridbag to match
     * the number of elements in columnWidth.
     */
    public int columnWidths[];

    /**
     * This field holds the overrides to the row minimum
     * heights.  If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the minimum row
     * heights have been calculated.
     * If <code>rowHeights</code> has more elements than the number of
     * rows, rows are added to the gridbag to match
     * the number of elements in <code>rowHeights</code>.
     */
    public int rowHeights[];

    /**
     * This field holds the overrides to the column weights.
     * If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the columns
     * weights have been calculated.
     * If <code>columnWeights[i]</code> &gt; weight for column i, then
     * column i is assigned the weight in <code>columnWeights[i]</code>.
     * If <code>columnWeights</code> has more elements than the number
     * of columns, the excess elements are ignored - they do
     * not cause more columns to be created.
     */
    public double columnWeights[];

    /**
     * This field holds the overrides to the row weights.
     * If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the rows
     * weights have been calculated.
     * If <code>rowWeights[i]</code> &gt; weight for row i, then
     * row i is assigned the weight in <code>rowWeights[i]</code>.
     * If <code>rowWeights</code> has more elements than the number
     * of rows, the excess elements are ignored - they do
     * not cause more rows to be created.
     */
    public double rowWeights[];

    /**
     * Creates a grid bag layout manager.
     */
    public GridBagLayoutUtil () {
        comptable = new Hashtable<JRElement,GridBagConstraints>();
        defaultConstraints = new GridBagConstraints();
    }

    /**
     * Sets the constraints for the specified component in this layout.
     * @param       comp the component to be modified
     * @param       constraints the constraints to be applied
     */
    public void setConstraints(JRElement comp) {

			GridBagConstraints constraint = new GridBagConstraints();
			Object prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_X);
			constraint.gridx = parsePosition(prop);
			//limit value too big
			if (constraint.gridx > JSSGridBagUIProvider.MAX_COL_NUMBER){
				constraint.gridx = JSSGridBagUIProvider.MAX_COL_NUMBER;
			}
			
			prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_Y);
			constraint.gridy = parsePosition(prop);
			//limit value too big
			if (constraint.gridy > JSSGridBagUIProvider.MAX_ROW_NUMBER){
				constraint.gridy = JSSGridBagUIProvider.MAX_ROW_NUMBER;
			}
			
			prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_ROWSPAN);
			constraint.gridheight = parseSpan(prop);
			//limit value too big
			if (constraint.gridheight > JSSGridBagUIProvider.MAX_ROW_NUMBER){
				constraint.gridheight = JSSGridBagUIProvider.MAX_ROW_NUMBER;
			}
			
			prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_COLSPAN);
			constraint.gridwidth = parseSpan(prop);
			//limit value too big
			if (constraint.gridwidth > JSSGridBagUIProvider.MAX_COL_NUMBER){
				constraint.gridwidth = JSSGridBagUIProvider.MAX_COL_NUMBER;
			}
			
			prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_IS_FIXED);
			boolean value = prop != null ? Boolean.parseBoolean(prop.toString()) : false;		
			if (value){
				constraint.isFixedSeize = true;
				constraint.fill = GridBagConstraints.NONE;
				constraint.weightx = 0;
				constraint.weighty = 0;
				constraint.anchor = GridBagConstraints.NORTHWEST;
			} else {
				constraint.isFixedSeize = false;
				constraint.fill = GridBagConstraints.BOTH;
				prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_ROW);
				constraint.weighty = parseWeight(prop);
				prop = comp.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_COLUMN);
				constraint.weightx = parseWeight(prop);
			}

    	comptable.put(comp, constraint);
    }
    
    /**
     * Parse the weight property and return a valid value to
     * be used on the layout. If the property contains a valid value
     * (double >= 0) then it is returned, otherwise it uses the default value
     * 1.0
     * 
     * @param prop the value of the property
     * @return a valid weight value
     */
    protected double parseWeight(Object prop){
  		if(prop != null){
  			String value = prop.toString().trim();
  			try{
  				double result = Double.parseDouble(value);
  				if (result >= 0d){
  					return result;
  				}
  			} catch (Exception ex){
  			}
  		}
  		return 1.0d;
  	}
  	
    /**
     * Parse the span property and return a valid value to
     * be used on the layout. If the property contains a valid value
     * (int >= 0) then it is returned, otherwise it uses the default value
     * 1
     * 
     * @param prop the value of the property
     * @return a valid span value
     */
  	protected int parseSpan(Object prop){
  		if(prop != null){
  			String value = prop.toString().trim();
  			try{
  				int result = Integer.parseInt(value);
  				if (result >= 1){
  					return result;
  				}
  			} catch (Exception ex){
  			}
  		}
  		return 1;
  	}
  	
    /**
     * Parse the position property and return a valid value to
     * be used on the layout. If the property contains a valid value
     * (int >= 0) then it is returned, otherwise it uses the default value
     * GridBagConstraints.RELATIVE
     * 
     * @param prop the value of the property
     * @return a valid weight position
     */
  	protected int parsePosition(Object prop){
  		if(prop != null){
  			String value = prop.toString().trim();
  			try{
					int result = Integer.parseInt(value);
					if (result >= 0){
						return result;
					}
				} catch (Exception ex){
				}
  		}
  		return GridBagConstraints.RELATIVE;
  	}

    /**
     * Gets the constraints for the specified component.  A copy of
     * the actual <code>GridBagConstraints</code> object is returned.
     * @param       comp the component to be queried
     * @return      the constraint for the specified component in this
     *                  grid bag layout; a copy of the actual constraint
     *                  object is returned
     */
    public GridBagConstraints getConstraints(JRElement comp) {
        GridBagConstraints constraints = comptable.get(comp);
        if (constraints == null) {
            setConstraints(comp);
            constraints = comptable.get(comp);
        }
        return (GridBagConstraints)constraints.clone();
    }

    /**
     * Retrieves the constraints for the specified component.
     * The return value is not a copy, but is the actual
     * <code>GridBagConstraints</code> object used by the layout mechanism.
     * <p>
     * If <code>comp</code> is not in the <code>GridBagLayout</code>,
     * a set of default <code>GridBagConstraints</code> are returned.
     * A <code>comp</code> value of <code>null</code> is invalid
     * and returns <code>null</code>.
     *
     * @param       comp the component to be queried
     * @return      the constraints for the specified component
     */
    protected GridBagConstraints lookupConstraints(JRElement comp) {
        GridBagConstraints constraints = comptable.get(comp);
        if (constraints == null) {
            setConstraints(comp);
            constraints = comptable.get(comp);
        }
        return constraints;
    }

    /**
     * Removes the constraints for the specified component in this layout
     * @param       comp the component to be modified
     */
    private void removeConstraints(JRElement comp) {
        comptable.remove(comp);
    }

    /**
     * Determines column widths and row heights for the layout grid.
     * <p>
     * Most applications do not call this method directly.
     * @return     an array of two arrays, containing the widths
     *                       of the layout columns and
     *                       the heights of the layout rows
     */
    public int [][] getLayoutDimensions () {
        if (layoutInfo == null)
            return new int[2][0];

        int dim[][] = new int [2][];
        dim[0] = new int[layoutInfo.width];
        dim[1] = new int[layoutInfo.height];

        System.arraycopy(layoutInfo.minWidth, 0, dim[0], 0, layoutInfo.width);
        System.arraycopy(layoutInfo.minHeight, 0, dim[1], 0, layoutInfo.height);

        return dim;
    }

    /**
     * Determines the weights of the layout grid's columns and rows.
     * Weights are used to calculate how much a given column or row
     * stretches beyond its preferred size, if the layout has extra
     * room to fill.
     * <p>
     * Most applications do not call this method directly.
     * @return      an array of two arrays, representing the
     *                    horizontal weights of the layout columns
     *                    and the vertical weights of the layout rows
     */
    public double [][] getLayoutWeights () {
        if (layoutInfo == null)
            return new double[2][0];

        double weights[][] = new double [2][];
        weights[0] = new double[layoutInfo.width];
        weights[1] = new double[layoutInfo.height];

        System.arraycopy(layoutInfo.weightX, 0, weights[0], 0, layoutInfo.width);
        System.arraycopy(layoutInfo.weightY, 0, weights[1], 0, layoutInfo.height);

        return weights;
    }

    /**
     * Determines which cell in the layout grid contains the point
     * specified by <code>(x,&nbsp;y)</code>. Each cell is identified
     * by its column index (ranging from 0 to the number of columns
     * minus 1) and its row index (ranging from 0 to the number of
     * rows minus 1).
     * <p>
     * If the <code>(x,&nbsp;y)</code> point lies
     * outside the grid, the following rules are used.
     * The column index is returned as zero if <code>x</code> lies to the
     * left of the layout for a left-to-right container or to the right of
     * the layout for a right-to-left container.  The column index is returned
     * as the number of columns if <code>x</code> lies
     * to the right of the layout in a left-to-right container or to the left
     * in a right-to-left container.
     * The row index is returned as zero if <code>y</code> lies above the
     * layout, and as the number of rows if <code>y</code> lies
     * below the layout.  The orientation of a container is determined by its
     * <code>ComponentOrientation</code> property.
     * @param      x    the <i>x</i> coordinate of a point
     * @param      y    the <i>y</i> coordinate of a point
     * @return     an ordered pair of indexes that indicate which cell
     *             in the layout grid contains the point
     *             (<i>x</i>,&nbsp;<i>y</i>).
     */
    public Point location(int x, int y) {
        Point loc = new Point(0,0);
        int i, d;

        if (layoutInfo == null)
            return loc;

        d = layoutInfo.startx;
        for (i=0; i<layoutInfo.width; i++) {
          d += layoutInfo.minWidth[i];
          if (d > x)
              break;
        }
        loc.x = i;

        d = layoutInfo.starty;
        for (i=0; i<layoutInfo.height; i++) {
            d += layoutInfo.minHeight[i];
            if (d > y)
                break;
        }
        loc.y = i;

        return loc;
    }

    /**
     * Removes the specified component from this layout.
     * <p>
     * Most applications do not call this method directly.
     * @param    comp   the component to be removed.
     */
    public void removeLayoutComponent(JRElement comp) {
        removeConstraints(comp);
    }

    /**
     * Lays out the specified set of elements using the grid bag layout.
     * The elements are not moved but it is returned a map where for each element is contained
     * the position and size of the element itslef.

     * @param parentSize the size of the parent
     * @param children the children to layout
     */
    public Map<JRElement, org.eclipse.draw2d.geometry.Rectangle> layoutContainer(Dimension parentSize, JRElement[] children) {
        return arrangeGrid(parentSize, children);
    }
    
    /**
     * Lays out the specified set of elements using the grid bag layout.
     * The elements are not moved but it is returned a map where for each element is contained
     * the position and size of the element itslef.

     * @param parentSize the size of the parent
     * @param children the children to layout
     * @return a map where containing as keys each of the child passed, and as values hte position of the elements
     * according to the layout
     */
    public Map<JRElement, org.eclipse.draw2d.geometry.Rectangle> layoutContainer(org.eclipse.draw2d.geometry.Dimension parentSize, JRElement[] children) {
    	Dimension dim = new Dimension(parentSize.width, parentSize.height);
    	return arrangeGrid(dim, children);
    }

    /**
     * Calculate maximum array sizes to allocate arrays without ensureCapacity
     * we may use preCalculated sizes in whole class because of upper estimation of
     * maximumArrayXIndex and maximumArrayYIndex.
     * 
     * @param children the children to layout
     */
    private long[]  preInitMaximumArraySizes(JRElement[] child){
        GridBagConstraints constraints;
        int curX, curY;
        int curWidth, curHeight;
        int preMaximumArrayXIndex = 0;
        int preMaximumArrayYIndex = 0;
        long [] returnArray = new long[2];

        for (int compId = 0 ; compId < child.length ; compId++) {
            JRElement comp = child[compId];

            constraints = lookupConstraints(comp);
            curX = constraints.gridx;
            curY = constraints.gridy;
            curWidth = constraints.gridwidth;
            curHeight = constraints.gridheight;

            // -1==RELATIVE, means that column|row equals to previously added component,
            // since each next Component with gridx|gridy == RELATIVE starts from
            // previous position, so we should start from previous component which
            // already used in maximumArray[X|Y]Index calculation. We could just increase
            // maximum by 1 to handle situation when component with gridx=-1 was added.
            if (curX < 0){
                curX = ++preMaximumArrayYIndex;
            }
            if (curY < 0){
                curY = ++preMaximumArrayXIndex;
            }
            // gridwidth|gridheight may be equal to RELATIVE (-1) or REMAINDER (0)
            // in any case using 1 instead of 0 or -1 should be sufficient to for
            // correct maximumArraySizes calculation
            if (curWidth <= 0){
                curWidth = 1;
            }
            if (curHeight <= 0){
                curHeight = 1;
            }

            preMaximumArrayXIndex = Math.max(curY + curHeight, preMaximumArrayXIndex);
            preMaximumArrayYIndex = Math.max(curX + curWidth, preMaximumArrayYIndex);
        } 
        returnArray[0] = preMaximumArrayXIndex;
        returnArray[1] = preMaximumArrayYIndex;
        return returnArray;
    } 

    /**
     * Fills in an instance of <code>GridBagLayoutInfo</code> for the
     * current set of managed children. This requires three passes through the
     * set of children:
     *
     * <ol>
     * <li>Figure out the dimensions of the layout grid.
     * <li>Determine which cells the components occupy.
     * <li>Distribute the weights and min sizes among the rows/columns.
     * </ol>
     *
     * This also caches the minsizes for all the children when they are
     * first encountered (so subsequent loops don't need to ask again).
     * <p>
     * This method should only be used internally by
     * <code>GridBagLayout</code>.
     *
     * @param parentSize the size of the parent
     * @param children the children to layout
     * @return the <code>GridBagLayoutInfo</code> for the set of children
     */
    protected GridBagLayoutInfo getLayoutInfo(Dimension parentSize, JRElement[] child){
      GridBagLayoutInfo r;
      GridBagConstraints constraints;

      int layoutWidth, layoutHeight;
      int []xMaxArray;
      int []yMaxArray;
      int compindex, i, k, px, py, pixels_diff, nextSize;
      int curX = 0; // constraints.gridx
      int curY = 0; // constraints.gridy
      int curWidth = 1;  // constraints.gridwidth
      int curHeight = 1;  // constraints.gridheight
      int curRow, curCol;
      double weight_diff, weight;
      int maximumArrayXIndex = 0;
      int maximumArrayYIndex = 0;


      /*
       * Pass #1
       *
       * Figure out the dimensions of the layout grid (use a value of 1 for
       * zero or negative widths and heights).
       */

      layoutWidth = layoutHeight = 0;
      curRow = curCol = -1;
      long [] arraySizes = preInitMaximumArraySizes(child);

      /* fix for 4623196.
       * If user try to create a very big grid we can
       * get NegativeArraySizeException because of integer value
       * overflow (EMPIRICMULTIPLIER*gridSize might be more then Integer.MAX_VALUE).
       * We need to detect this situation and try to create a
       * grid with Integer.MAX_VALUE size instead.
       */
      maximumArrayXIndex = (EMPIRICMULTIPLIER * arraySizes[0] > Integer.MAX_VALUE )? Integer.MAX_VALUE : EMPIRICMULTIPLIER*(int)arraySizes[0];
      maximumArrayYIndex = (EMPIRICMULTIPLIER * arraySizes[1] > Integer.MAX_VALUE )? Integer.MAX_VALUE : EMPIRICMULTIPLIER*(int)arraySizes[1];

      if (rowHeights != null){
          maximumArrayXIndex = Math.max(maximumArrayXIndex, rowHeights.length);
      }
      if (columnWidths != null){
          maximumArrayYIndex = Math.max(maximumArrayYIndex, columnWidths.length);
      }

      xMaxArray = new int[maximumArrayXIndex];
      yMaxArray = new int[maximumArrayYIndex];

      for (compindex = 0 ; compindex < child.length ; compindex++) {
          JRElement comp = child[compindex];
          constraints = lookupConstraints(comp);

          curX = constraints.gridx;
          curY = constraints.gridy;
          curWidth = constraints.gridwidth;
          if (curWidth <= 0)
              curWidth = 1;
          curHeight = constraints.gridheight;
          if (curHeight <= 0)
              curHeight = 1;

          /* If x or y is negative, then use relative positioning: */
          if (curX < 0 && curY < 0) {
              if (curRow >= 0)
                  curY = curRow;
              else if (curCol >= 0)
                  curX = curCol;
              else
                  curY = 0;
          }
          if (curX < 0) {
              px = 0;
              for (i = curY; i < (curY + curHeight); i++) {
                  px = Math.max(px, xMaxArray[i]);
              }

              curX = px - curX - 1;
              if(curX < 0)
                  curX = 0;
          }
          else if (curY < 0) {
              py = 0;
              for (i = curX; i < (curX + curWidth); i++) {
                  py = Math.max(py, yMaxArray[i]);
              }
              curY = py - curY - 1;
              if(curY < 0)
                  curY = 0;
          }
          curRow = curY;

          px = curX + curWidth;
          if (layoutWidth < px) {
              layoutWidth = px;
          }
          py = curY + curHeight;
          if (layoutHeight < py) {
              layoutHeight = py;
          }

          /* Adjust xMaxArray and yMaxArray */
          for (i = curX; i < (curX + curWidth); i++) {
              yMaxArray[i] =py;
          }
          for (i = curY; i < (curY + curHeight); i++) {
              xMaxArray[i] = px;
          }

          if (constraints.isFixedSeize){
          	constraints.minWidth = comp.getWidth();
          	constraints.minHeight = comp.getHeight();
          } else {
	          constraints.minWidth = 0;
	          constraints.minHeight = 0;
          }
      } //for (components) loop


      /*
       * Apply minimum row/column dimensions
       */
      if (columnWidths != null && layoutWidth < columnWidths.length)
          layoutWidth = columnWidths.length;
      if (rowHeights != null && layoutHeight < rowHeights.length)
          layoutHeight = rowHeights.length;

      r = new GridBagLayoutInfo(layoutWidth, layoutHeight);

      /*
       * Pass #2
       *
       * Negative values for gridX are filled in with the current x value.
       * Negative values for gridY are filled in with the current y value.
       * Negative or zero values for gridWidth and gridHeight end the current
       *  row or column, respectively.
       */

      curRow = curCol = -1;

      Arrays.fill(xMaxArray, 0);
      Arrays.fill(yMaxArray, 0);

      for (compindex = 0 ; compindex < child.length ; compindex++) {
          JRElement comp = child[compindex];

          constraints = lookupConstraints(comp);

          curX = constraints.gridx;
          curY = constraints.gridy;
          curWidth = constraints.gridwidth;
          curHeight = constraints.gridheight;

          /* If x or y is negative, then use relative positioning: */
          if (curX < 0 && curY < 0) {
              if(curRow >= 0)
                  curY = curRow;
              else if(curCol >= 0)
                  curX = curCol;
              else
                  curY = 0;
          }

          if (curX < 0) {
              if (curHeight <= 0) {
                  curHeight += r.height - curY;
                  if (curHeight < 1)
                      curHeight = 1;
              }

              px = 0;
              for (i = curY; i < (curY + curHeight); i++)
                  px = Math.max(px, xMaxArray[i]);

              curX = px - curX - 1;
              if(curX < 0)
                  curX = 0;
          }
          else if (curY < 0) {
              if (curWidth <= 0) {
                  curWidth += r.width - curX;
                  if (curWidth < 1)
                      curWidth = 1;
              }

              py = 0;
              for (i = curX; i < (curX + curWidth); i++){
                  py = Math.max(py, yMaxArray[i]);
              }

              curY = py - curY - 1;
              if(curY < 0)
                  curY = 0;
          }
          curRow = curY;

          if (curWidth <= 0) {
              curWidth += r.width - curX;
              if (curWidth < 1)
                  curWidth = 1;
          }

          if (curHeight <= 0) {
              curHeight += r.height - curY;
              if (curHeight < 1)
                  curHeight = 1;
          }

          px = curX + curWidth;
          py = curY + curHeight;

          for (i = curX; i < (curX + curWidth); i++) { yMaxArray[i] = py; }
          for (i = curY; i < (curY + curHeight); i++) { xMaxArray[i] = px; }

          /* Assign the new values to the gridbag slave */
          constraints.tempX = curX;
          constraints.tempY = curY;
          constraints.tempWidth = curWidth;
          constraints.tempHeight = curHeight;
      }

      r.weightX = new double[maximumArrayYIndex];
      r.weightY = new double[maximumArrayXIndex];
      r.minWidth = new int[maximumArrayYIndex];
      r.minHeight = new int[maximumArrayXIndex];


      /*
       * Apply minimum row/column dimensions and weights
       */
      if (columnWidths != null)
          System.arraycopy(columnWidths, 0, r.minWidth, 0, columnWidths.length);
      if (rowHeights != null)
          System.arraycopy(rowHeights, 0, r.minHeight, 0, rowHeights.length);
      if (columnWeights != null)
          System.arraycopy(columnWeights, 0, r.weightX, 0,  Math.min(r.weightX.length, columnWeights.length));
      if (rowWeights != null)
          System.arraycopy(rowWeights, 0, r.weightY, 0,  Math.min(r.weightY.length, rowWeights.length));

      /*
       * Pass #3
       *
       * Distribute the minimun widths and weights:
       */

      nextSize = Integer.MAX_VALUE;

      for (i = 1;
           i != Integer.MAX_VALUE;
           i = nextSize, nextSize = Integer.MAX_VALUE) {
          for (compindex = 0 ; compindex < child.length ; compindex++) {
              JRElement comp = child[compindex];
              constraints = lookupConstraints(comp);

              if (constraints.tempWidth == i) {
                  px = constraints.tempX + constraints.tempWidth; /* right column */

                  /*
                   * Figure out if we should use this slave\'s weight.  If the weight
                   * is less than the total weight spanned by the width of the cell,
                   * then discard the weight.  Otherwise split the difference
                   * according to the existing weights.
                   */

                  weight_diff = constraints.weightx;
                  for (k = constraints.tempX; k < px; k++)
                      weight_diff -= r.weightX[k];
                  if (weight_diff > 0.0) {
                      weight = 0.0;
                      for (k = constraints.tempX; k < px; k++)
                          weight += r.weightX[k];
                      for (k = constraints.tempX; weight > 0.0 && k < px; k++) {
                          double wt = r.weightX[k];
                          double dx = (wt * weight_diff) / weight;
                          r.weightX[k] += dx;
                          weight_diff -= dx;
                          weight -= wt;
                      }
                      /* Assign the remainder to the rightmost cell */
                      r.weightX[px-1] += weight_diff;
                  }

                  /*
                   * Calculate the minWidth array values.
                   * First, figure out how wide the current slave needs to be.
                   * Then, see if it will fit within the current minWidth values.
                   * If it will not fit, add the difference according to the
                   * weightX array.
                   */

                  pixels_diff =
                      constraints.minWidth + constraints.ipadx +
                      constraints.insets.left + constraints.insets.right;

                  for (k = constraints.tempX; k < px; k++)
                      pixels_diff -= r.minWidth[k];
                  if (pixels_diff > 0) {
                      weight = 0.0;
                      for (k = constraints.tempX; k < px; k++)
                          weight += r.weightX[k];
                      for (k = constraints.tempX; weight > 0.0 && k < px; k++) {
                          double wt = r.weightX[k];
                          int dx = (int)((wt * ((double)pixels_diff)) / weight);
                          r.minWidth[k] += dx;
                          pixels_diff -= dx;
                          weight -= wt;
                      }
                      /* Any leftovers go into the rightmost cell */
                      r.minWidth[px-1] += pixels_diff;
                  }
              }
              else if (constraints.tempWidth > i && constraints.tempWidth < nextSize)
                  nextSize = constraints.tempWidth;


              if (constraints.tempHeight == i) {
                  py = constraints.tempY + constraints.tempHeight; /* bottom row */

                  /*
                   * Figure out if we should use this slave's weight.  If the weight
                   * is less than the total weight spanned by the height of the cell,
                   * then discard the weight.  Otherwise split it the difference
                   * according to the existing weights.
                   */

                  weight_diff = constraints.weighty;
                  for (k = constraints.tempY; k < py; k++)
                      weight_diff -= r.weightY[k];
                  if (weight_diff > 0.0) {
                      weight = 0.0;
                      for (k = constraints.tempY; k < py; k++)
                          weight += r.weightY[k];
                      for (k = constraints.tempY; weight > 0.0 && k < py; k++) {
                          double wt = r.weightY[k];
                          double dy = (wt * weight_diff) / weight;
                          r.weightY[k] += dy;
                          weight_diff -= dy;
                          weight -= wt;
                      }
                      /* Assign the remainder to the bottom cell */
                      r.weightY[py-1] += weight_diff;
                  }

                  /*
                   * Calculate the minHeight array values.
                   * First, figure out how tall the current slave needs to be.
                   * Then, see if it will fit within the current minHeight values.
                   * If it will not fit, add the difference according to the
                   * weightY array.
                   */

                  pixels_diff =
                      constraints.minHeight + constraints.ipady +
                      constraints.insets.top +
                      constraints.insets.bottom;
                  for (k = constraints.tempY; k < py; k++)
                      pixels_diff -= r.minHeight[k];
                  if (pixels_diff > 0) {
                      weight = 0.0;
                      for (k = constraints.tempY; k < py; k++)
                          weight += r.weightY[k];
                      for (k = constraints.tempY; weight > 0.0 && k < py; k++) {
                          double wt = r.weightY[k];
                          int dy = (int)((wt * ((double)pixels_diff)) / weight);
                          r.minHeight[k] += dy;
                          pixels_diff -= dy;
                          weight -= wt;
                      }
                      /* Any leftovers go into the bottom cell */
                      r.minHeight[py-1] += pixels_diff;
                  }
              }
              else if (constraints.tempHeight > i &&
                       constraints.tempHeight < nextSize)
                  nextSize = constraints.tempHeight;
          }
      }
      return r;

    } 

    /**
     * Figures out the minimum size of the
     * master based on the information from <code>getLayoutInfo</code>.
     * This method should only be used internally by
     * <code>GridBagLayout</code>.
     *
     * @param parentDimension the size of the parent
     * @param info the layout info for this parent
     * @return a <code>Dimension</code> object containing the
     *   minimum size
     */
    protected Dimension getMinSize(Dimension parentDimension, GridBagLayoutInfo info) {
      Dimension d = new Dimension();
      int i, t;
      
      t = 0;
      for(i = 0; i < info.width; i++)
          t += info.minWidth[i];
      d.width = t;

      t = 0;
      for(i = 0; i < info.height; i++)
          t += info.minHeight[i];
      d.height = t;

      return d;
    }

    /**
     * Lays out the specified set of elements using the grid bag layout.
     * The elements are not moved but it is returned a map where for each element is contained
     * the position and size of the element itself.
     * 
     * @param parentSize the size of the parent
     * @param children the children to layout
     * @return a map where containing as keys each of the child passed, and as values the position of the elements
     * according to the layout.
     */
    protected HashMap<JRElement, org.eclipse.draw2d.geometry.Rectangle> arrangeGrid(Dimension parentSize, JRElement[] components) {
        int compindex;
        GridBagConstraints constraints;
        Rectangle r = new Rectangle();
        double weight;
        GridBagLayoutInfo info;
        HashMap<JRElement, org.eclipse.draw2d.geometry.Rectangle> result = new HashMap<JRElement, org.eclipse.draw2d.geometry.Rectangle>();
        /*
         * If the parent has no slaves anymore, then don't do anything
         * at all:  just leave the parent's size as-is.
         */
        if (components.length == 0 &&
            (columnWidths == null || columnWidths.length == 0) &&
            (rowHeights == null || rowHeights.length == 0)) {
            return result;
        }

        /*
         * Pass #1: scan all the slaves to figure out the total amount
         * of space needed.
         */

        info = getLayoutInfo(parentSize, components);
        Dimension d = getMinSize(parentSize, info);

        layoutInfo = info;
        r.width = d.width;
        r.height = d.height;


        /*
         * If the current dimensions of the window don't match the desired
         * dimensions, then adjust the minWidth and minHeight arrays
         * according to the weights.
         */

        int diffw = parentSize.width - r.width;
        if (diffw != 0) {
	    		//compute the weight only on not fixed columns and distribute the excising space
	    		//only among them
	        weight = 0.0;
	    		HashSet<Integer> notFixedSizeCols = new HashSet<Integer>();
	    		for (int i = 0; i < info.width; i++) {
	    			if (info.minWidth[i] == 0d){
	    				notFixedSizeCols.add(i);
	    				weight += info.weightX[i];
	    			}
	    		}  
	        if (weight > 0.0) {
            for (int i: notFixedSizeCols) {
              int dx = (int)(( ((double)diffw) * info.weightX[i]) / weight);
              info.minWidth[i] += dx;
              r.width += dx;
              if (info.minWidth[i] < 0) {
                  r.width -= info.minWidth[i];
                  info.minWidth[i] = 0;
              }
            }
	        }
	        diffw = parentSize.width - r.width;
	        
	        //Distribute the excising space between the not fixed elements
	        if (diffw > 0 && !notFixedSizeCols.isEmpty()){
	        	while(diffw != 0){
	        		for (int i: notFixedSizeCols) {
	              info.minWidth[i]++;
	              r.width++;
	              diffw--;
	              if (diffw == 0){
	              	break;
	              }
	            }
	        	}
	        }
        }	else {
        	diffw = 0;
        }

        int diffh = parentSize.height - r.height;
        if (diffh != 0) {
	    		//compute the weight only on not fixed columns and distribute the excising space
	    		//only among them
	      	weight = 0.0;
	    		HashSet<Integer> notFixedSizeCols = new HashSet<Integer>();
	    		for (int i = 0; i < info.height; i++) {
	    			if (info.minHeight[i] == 0d){
	    				notFixedSizeCols.add(i);
	    				weight += info.weightY[i];
	    			}
	    		}
	        if (weight > 0.0) {
	        	 for (int i: notFixedSizeCols) {
	                int dy = (int)(( ((double)diffh) * info.weightY[i]) / weight);
	                info.minHeight[i] += dy;
	                r.height += dy;
	                if (info.minHeight[i] < 0) {
	                    r.height -= info.minHeight[i];
	                    info.minHeight[i] = 0;
	                }
	            }
	        }
	      	diffh = parentSize.height - r.height;
	      	
	        //Distribute the excising space between the not fixed elements
	        if (diffh > 0 && !notFixedSizeCols.isEmpty()){
	        	while(diffh != 0){
	        		for (int i: notFixedSizeCols) {
	              info.minHeight[i]++;
	              r.height++;
	              diffh--;
	              if (diffh == 0){
	              	break;
	              }
	            }
	        	}
	        }
        } else {
            diffh = 0;
        }

        //info.startx = diffw/2;
        //info.starty = diffh/2;

        /*
         * Since the element with not fixed size fill the space and
         * the element with fixed size start always from left upper corner
         * then the starting point is always 0,0
         */
        info.startx = 0;
        info.starty = 0;
        
        /*
         * Now do the actual layout of the slaves using the layout information
         * that has been collected.
         */
        for (compindex = 0 ; compindex < components.length ; compindex++) {
            JRElement comp = components[compindex];

            constraints = lookupConstraints(comp);

            r.x = info.startx;
            for(int i = 0; i < constraints.tempX; i++)
                r.x += info.minWidth[i];
            
            r.y = info.starty;
            for(int i = 0; i < constraints.tempY; i++)
                r.y += info.minHeight[i];

            r.width = 0;
            for(int i = constraints.tempX;
                i < (constraints.tempX + constraints.tempWidth);
                i++) {
                r.width += info.minWidth[i];
            }

            r.height = 0;
            for(int i = constraints.tempY;
                i < (constraints.tempY + constraints.tempHeight);
                i++) {
                r.height += info.minHeight[i];
            }


            adjustForGravity(constraints, r);

            if (r.x < 0) {
                r.width += r.x;
                r.x = 0;
            }

            if (r.y < 0) {
                r.height += r.y;
                r.y = 0;
            }

            /*
             * If the window is too small to be interesting then
             * unmap it.  Otherwise configure it and then make sure
             * it's mapped.
             */
            if ((r.width <= 0) || (r.height <= 0)) {
            	result.put(comp, new  org.eclipse.draw2d.geometry.Rectangle(0,0,0,0));
            }
            else {
                if (comp.getX() != r.x || comp.getY() != r.y ||
                    comp.getWidth() != r.width || comp.getHeight() != r.height) {
                  	result.put(comp, new  org.eclipse.draw2d.geometry.Rectangle(r.x, r.y, r.width, r.height));
                }
            }
        }
        return result;
    }
    
    /**
     * Adjusts the x, y, width, and height fields to the correct
     * values depending on the constraint geometry and pads.
     * This method should only be used internally by
     * <code>GridBagLayout</code>.
     *
     * @param constraints the constraints to be applied
     * @param r the <code>Rectangle</code> to be adjusted
     */
    protected void adjustForGravity(GridBagConstraints constraints,
                                    Rectangle r) {
        int diffx, diffy;

        r.x += constraints.insets.left;
        r.width -= (constraints.insets.left + constraints.insets.right);
        r.y += constraints.insets.top;
        r.height -= (constraints.insets.top + constraints.insets.bottom);

        diffx = 0;
        if ((constraints.fill != GridBagConstraints.HORIZONTAL &&
             constraints.fill != GridBagConstraints.BOTH)
            && (r.width > (constraints.minWidth + constraints.ipadx))) {
            diffx = r.width - (constraints.minWidth + constraints.ipadx);
            r.width = constraints.minWidth + constraints.ipadx;
        }

        diffy = 0;
        if ((constraints.fill != GridBagConstraints.VERTICAL &&
             constraints.fill != GridBagConstraints.BOTH)
            && (r.height > (constraints.minHeight + constraints.ipady))) {
            diffy = r.height - (constraints.minHeight + constraints.ipady);
            r.height = constraints.minHeight + constraints.ipady;
        }

        switch (constraints.anchor) {
          case GridBagConstraints.CENTER:
              r.x += diffx/2;
              r.y += diffy/2;
              break;
          case GridBagConstraints.PAGE_START:
          case GridBagConstraints.NORTH:
              r.x += diffx/2;
              break;
          case GridBagConstraints.NORTHEAST:
              r.x += diffx;
              break;
          case GridBagConstraints.EAST:
              r.x += diffx;
              r.y += diffy/2;
              break;
          case GridBagConstraints.SOUTHEAST:
              r.x += diffx;
              r.y += diffy;
              break;
          case GridBagConstraints.PAGE_END:
          case GridBagConstraints.SOUTH:
              r.x += diffx/2;
              r.y += diffy;
              break;
          case GridBagConstraints.SOUTHWEST:
              r.y += diffy;
              break;
          case GridBagConstraints.WEST:
              r.y += diffy/2;
              break;
          case GridBagConstraints.NORTHWEST:
              break;
          case GridBagConstraints.LINE_START:
            r.y += diffy/2;
            break;
          case GridBagConstraints.LINE_END:
            r.y += diffy/2;
            break;
          default:
              throw new IllegalArgumentException("illegal anchor value");
        }
    }

}
