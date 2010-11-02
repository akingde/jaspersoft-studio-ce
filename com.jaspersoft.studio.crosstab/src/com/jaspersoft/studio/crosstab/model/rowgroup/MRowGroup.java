package com.jaspersoft.studio.crosstab.model.rowgroup;

import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.crosstab.model.MCrosstabGroup;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;

public class MRowGroup extends MCrosstabGroup implements ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("rowgroup");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MRowGroup() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MRowGroup(ANode parent, JRCrosstabRowGroup jfRield, int newIndex) {
		super(parent, jfRield, newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MRowGroups)
			return true;
		return false;
	}

}