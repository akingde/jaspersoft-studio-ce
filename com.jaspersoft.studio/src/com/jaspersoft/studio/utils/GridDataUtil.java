/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;

/**
 * A utility class to simplify the work with 
 * &lt;code>org.eclipse.swt.layout.GridData&lt;/code> objects.
 * <p>
 * 
 * <b>NOTE</b>: Original code from org.eclipse.hyades.ui.internal.util.GridDataUtil class.
 * 
 * @author marcelop
 * @since 0.0.1
 */
public class GridDataUtil
{
	/**
	 * Creates a grid data object that occupies vertical and horizontal space.
	 * @return GridData
	 */
	public static GridData createFill()
	{
		GridData gd= new GridData();
		gd.horizontalAlignment= GridData.FILL;
		gd.grabExcessHorizontalSpace= true;
		gd.verticalAlignment= GridData.FILL;
		gd.grabExcessVerticalSpace= true;
		return gd;
	}
	
	/**
	 * Creates a grid data object that occupies horizontal space.
	 * @return GridData
	 */
	public static GridData createHorizontalFill()
	{
		GridData gd= new GridData();
		gd.horizontalAlignment= GridData.FILL;
		gd.grabExcessHorizontalSpace= true;
		return gd;
	}
	
	/**
	 * Creates a grid data object that occupies vertical space.
	 * @return GridData
	 */
	public static GridData createVerticalFill()
	{
		GridData gd= new GridData();
		gd.verticalAlignment= GridData.FILL;
		gd.grabExcessVerticalSpace= true;
		return gd;
	}

	/**
	 * Returns the grid data's style.
	 * @param gridData
	 * @return int
	 */	
	public static int getStyle(GridData gridData)
	{
		if(gridData == null)
			return 0;
		
		int style = 0;
		
		switch(gridData.verticalAlignment)
		{
			case GridData.BEGINNING:
				style |= GridData.VERTICAL_ALIGN_BEGINNING;
				break;
			case GridData.CENTER:
				style |= GridData.VERTICAL_ALIGN_CENTER;
				break;
			case GridData.FILL:
				style |= GridData.VERTICAL_ALIGN_FILL;
				break;
			case GridData.END:
				style |= GridData.VERTICAL_ALIGN_END;
				break;
		}

		switch(gridData.horizontalAlignment)
		{
			case GridData.BEGINNING:
				style |= GridData.HORIZONTAL_ALIGN_BEGINNING;
				break;
			case GridData.CENTER:
				style |= GridData.HORIZONTAL_ALIGN_CENTER;
				break;
			case GridData.FILL:
				style |= GridData.HORIZONTAL_ALIGN_FILL;
				break;
			case GridData.END:
				style |= GridData.HORIZONTAL_ALIGN_END;
				break;
		}
		
		if(gridData.grabExcessVerticalSpace)
			style |= GridData.GRAB_VERTICAL;

		if(gridData.grabExcessHorizontalSpace)
			style |= GridData.GRAB_HORIZONTAL;
			
		return style;
	}
	
	/**
	 * Clones a grid data.
	 * @param gridData
	 * @return GridData
	 */
	public static GridData clone(GridData gridData)
	{
		GridData clone = new GridData(getStyle(gridData));
		
		clone.heightHint = gridData.heightHint;
		clone.horizontalIndent = gridData.horizontalIndent;
		clone.horizontalSpan = gridData.horizontalSpan;
		clone.verticalSpan = gridData.verticalSpan;
		clone.widthHint = gridData.widthHint;
		
		return clone;
	}
	
	/**
	 * Applies the exclude griddata information to the specified control.
	 */
	public static void gridDataExclude(Control control, boolean exclude){
		Assert.isNotNull(control);
		if (control.getLayoutData() == null)
			return;
		Assert.isTrue(control.getLayoutData() instanceof GridData);
		((GridData) control.getLayoutData()).exclude = exclude;
	}
	
	/**
	 * Sets the specified layout data to the control related to the action item.
	 * 
	 * @param actionItem the action item 
	 * @param gd the griddata instance to set
	 */
	public static void setLayoutDataForItem(ActionContributionItem actionItem,GridData gd) {
		Assert.isNotNull(actionItem);
		Assert.isNotNull(gd);
		if(actionItem.getWidget() instanceof Control) {
			((Control)actionItem.getWidget()).setLayoutData(gd);
		}
	}
	
	/**
	 * Sets the GridData exclude flag for the specified control.
	 * When specifying to exclude/include a control also the enablement and visibility
	 * are updated accordingly.
	 * <p>
	 * 
	 * NOTE: no operation is performed on controls which does not have a layout data of
	 * type GridData associated.
	 * 
	 * @param control the control
	 * @param exclude the exclude flag to set
	 */
	public static void setGridDataExcludeAndVisibility(Control control, boolean exclude){
		Assert.isNotNull(control);
		Object layoutData = control.getLayoutData();
		if(layoutData instanceof GridData) {
			control.setEnabled(!exclude);
			control.setVisible(!exclude);
			((GridData)control.getLayoutData()).exclude = exclude;
		}
	}
}

