/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.layout;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.jaspersoft.studio.properties.Activator;
import com.jaspersoft.studio.properties.preferences.PropertiesPreferencePage;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/**
 * Reworked version of a {@link ColumnLayout} to provide a different logic to
 * calculate the number of columns.
 * 
 * @author Orlandin Marco
 *
 */
public class DynamicColumnLayout extends Layout {

	/**
	 * Horizontal spacing between columns (default is 5).
	 */
	public int horizontalSpacing = 5;

	/**
	 * Vertical spacing between controls (default is 5).
	 */
	public int verticalSpacing = 5;

	/**
	 * Top margin (default is 5).
	 */
	public int topMargin = 5;

	/**
	 * Left margin (default is 5).
	 */
	public int leftMargin = 5;
	/**
	 * Bottom margin (default is 5).
	 */
	public int bottomMargin = 5;

	/**
	 * Right margin (default is 5).
	 */
	public int rightMargin = 5;

	/**
	 * The page where the controls are shown
	 */
	private TabbedPropertySheetPage page;

	/**
	 * Minimum size for a column
	 */
	public int landscapeColumnsSize = 350;

	/**
	 * Flag used to force the layout to have always a single column
	 */
	protected boolean forceSingleColumn = false;

	/**
	 * Creates a new instance of the column layout.
	 * 
	 * @param page
	 *            the property page where this layout is used, must be not null
	 */
	public DynamicColumnLayout(TabbedPropertySheetPage page) {
		Assert.isNotNull(page);
		this.page = page;

		// Check if the flag to force a single column is enabled
		final IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		forceSingleColumn = prefStore.getBoolean(PropertiesPreferencePage.SINGLE_COLUMN_ID);
		// add a preference listener to refresh the flag when the preference option
		// change
		prefStore.addPropertyChangeListener(new IPropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (DynamicColumnLayout.this.page.getTabbedPropertyComposite().isDisposed()) {
					prefStore.removePropertyChangeListener(this);
				} else if (event.getProperty().equals(PropertiesPreferencePage.SINGLE_COLUMN_ID)) {
					forceSingleColumn = prefStore.getBoolean(PropertiesPreferencePage.SINGLE_COLUMN_ID);
					DynamicColumnLayout.this.page.getTabbedPropertyComposite().layout(true, true);
				}
			}
		});
	}

	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		return computeSize(composite, wHint, hHint);
	}

	private Point computeSize(Composite parent, int wHint, int hHint) {
		Control[] children = parent.getChildren();
		int cwidth = 0;
		int cheight = 0;
		Point[] sizes = new Point[children.length];

		int ncolumns = calculateColumnsNumber();
		for (int i = 0; i < children.length; i++) {
			sizes[i] = computeControlSize(children[i], SWT.DEFAULT);
			cwidth = Math.max(cwidth, sizes[i].x);
			cheight += sizes[i].y;
		}
		int perColHeight = computeColumnHeight(ncolumns, sizes, cheight, verticalSpacing);
		int colHeight = 0;
		int[] heights = new int[ncolumns];
		int ncol = 0;

		boolean fillIn = false;

		for (int i = 0; i < sizes.length; i++) {
			int childHeight = sizes[i].y;
			if (i > 0 && colHeight + childHeight > perColHeight) {
				heights[ncol] = colHeight;
				ncol++;
				if (ncol == ncolumns || fillIn) {
					// overflow - start filling in
					fillIn = true;
					ncol = findShortestColumn(heights);
				}
				colHeight = heights[ncol];
			}
			if (colHeight > 0)
				colHeight += verticalSpacing;
			colHeight += childHeight;
		}
		heights[ncol] = Math.max(heights[ncol], colHeight);

		Point size = new Point(0, 0);
		for (int i = 0; i < ncolumns; i++) {
			size.y = Math.max(size.y, heights[i]);
		}
		size.x = cwidth * ncolumns + (ncolumns - 1) * horizontalSpacing;
		size.x += leftMargin + rightMargin;
		// System.out.println("ColumnLayout: whint="+wHint+", size.x="+size.x);
		size.y += topMargin + bottomMargin;
		return size;
	}

	private Point computeControlSize(Control c, int suggestedWidth) {
		return c.computeSize(suggestedWidth, SWT.DEFAULT);
	}

	private int findShortestColumn(int[] heights) {
		int result = 0;
		int height = Integer.MAX_VALUE;
		for (int i = 0; i < heights.length; i++) {
			if (height > heights[i]) {
				height = heights[i];
				result = i;
			}
		}
		return result;
	}

	/**
	 * Calculate the number of columns, if the flag is enabled this is always one.
	 * It is also one if the properties area is in portrait proportions and if there
	 * is no space for more then one column, otherwise the number of columns is
	 * calculated like the int division between the current width and the minimum
	 * width for a column
	 * 
	 * @return an int >= 1 that indicate the number of columns
	 */
	protected int calculateColumnsNumber() {
		if (forceSingleColumn)
			return 1;
		else {
			Rectangle layoutArea = page.getTabbedPropertyComposite().getPropertiesArea();
			if (layoutArea.height >= layoutArea.width || layoutArea.width < landscapeColumnsSize) {
				// portrait mode or size for only one column
				return 1;
			} else {
				return layoutArea.width / landscapeColumnsSize;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite,
	 * boolean)
	 */
	protected void layout(Composite parent, boolean flushCache) {
		Control[] children = parent.getChildren();
		Rectangle carea = parent.getClientArea();
		int cwidth = 0;
		int cheight = 0;
		Point[] sizes = new Point[children.length];
		int ncolumns = calculateColumnsNumber();
		for (int i = 0; i < children.length; i++) {
			sizes[i] = computeControlSize(children[i], SWT.DEFAULT);
			cwidth = Math.max(cwidth, sizes[i].x);
			cheight += sizes[i].y;
		}

		int realWidth = (carea.width - leftMargin - rightMargin + horizontalSpacing) / ncolumns - horizontalSpacing;

		int fillWidth = Math.max(cwidth, realWidth);
		int perColHeight = computeColumnHeight(ncolumns, sizes, cheight, verticalSpacing);

		int colHeight = 0;
		int[] heights = new int[ncolumns];
		int ncol = 0;
		int x = leftMargin;
		boolean fillIn = false;

		for (int i = 0; i < sizes.length; i++) {
			Control child = children[i];
			Point csize = sizes[i];
			int childWidth = fillWidth;

			if (i > 0 && colHeight + csize.y > perColHeight) {
				heights[ncol] = colHeight;
				if (fillIn || ncol == ncolumns - 1) {
					// overflow - start filling in
					fillIn = true;
					ncol = findShortestColumn(heights);

					x = leftMargin + ncol * (fillWidth + horizontalSpacing);

				} else {
					ncol++;
					x += fillWidth + horizontalSpacing;
				}
				colHeight = heights[ncol];
			}
			if (colHeight > 0)
				colHeight += verticalSpacing;

			child.setBounds(x, topMargin + colHeight, childWidth, csize.y);

			colHeight += csize.y;
		}
	}

	private int computeColumnHeight(int ncolumns, Point[] sizes, int totalHeight, int verticalMargin) {
		int averageHeight = (totalHeight + sizes.length * verticalMargin) / ncolumns;
		int requiredHeight = computeActualHeight(ncolumns, sizes, averageHeight, verticalMargin);
		if (averageHeight == requiredHeight) {
			return requiredHeight;
		}
		// Try making the columns shorter, repeat up to 10 times, usually one or two
		// iterations will be sufficient
		for (int i = 0; i < 10; i++) {
			int candidateHeight = computeActualHeight(ncolumns, sizes, requiredHeight - 1, verticalMargin);
			if (candidateHeight >= requiredHeight) {
				return requiredHeight;
			}
			requiredHeight = candidateHeight;
		}
		return requiredHeight;
	}

	private static int computeActualHeight(int ncolumns, Point[] sizes, int candidateHeight, int verticalMargin) {
		int colHeight = 0;
		int maxHeight = 0;
		int column = 1;
		for (int i = 0; i < sizes.length; i++) {
			int childHeight = sizes[i].y;
			if (i > 0 && column < ncolumns && colHeight + childHeight + verticalMargin > candidateHeight) {
				maxHeight = Math.max(colHeight, maxHeight);
				column++;
				colHeight = 0;
			}
			if (colHeight > 0)
				colHeight += verticalMargin;
			colHeight += childHeight;
		}
		maxHeight = Math.max(colHeight, maxHeight);
		return maxHeight;
	}
}
