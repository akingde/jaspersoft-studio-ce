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
package com.jaspersoft.studio.model.group;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.base.JRBaseGroup;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.type.FooterPositionEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class MGroup.
 * 
 * @author Chicu Veaceslav
 */
public class MGroup extends APropertyNode implements ICopyable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("group"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m group.
	 */
	public MGroup() {
		super();
	}

	/**
	 * Instantiates a new m group.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MGroup(ANode parent, JRDesignGroup jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignGroup) getValue()).getName();
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

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private static JSSEnumPropertyDescriptor positionD;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JSSTextPropertyDescriptor nameD = new JSSTextPropertyDescriptor(JRDesignGroup.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MGroup_name_description);
		desc.add(nameD);

		JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignGroup.PROPERTY_EXPRESSION,
				Messages.common_expression);
		expressionD.setDescription(Messages.MGroup_expression_description);
		desc.add(expressionD);

		PixelPropertyDescriptor minhD = new PixelPropertyDescriptor(JRBaseGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE,
				"Min Height To Start New Page");
		minhD.setDescription("Min Height To Start New Page");
		desc.add(minhD);

		CheckBoxPropertyDescriptor stNewColD = new CheckBoxPropertyDescriptor(JRBaseGroup.PROPERTY_START_NEW_COLUMN,
				"Start New Column");
		stNewColD.setDescription("Start new column for new group");
		desc.add(stNewColD);

		CheckBoxPropertyDescriptor stNewPageD = new CheckBoxPropertyDescriptor(JRBaseGroup.PROPERTY_START_NEW_PAGE,
				"Start New Page");
		stNewPageD.setDescription("Start new page for new group");
		desc.add(stNewPageD);

		CheckBoxPropertyDescriptor rPageNumD = new CheckBoxPropertyDescriptor(JRBaseGroup.PROPERTY_RESET_PAGE_NUMBER,
				"Reset Page Number");
		rPageNumD.setDescription("Reset page number.");
		desc.add(rPageNumD);

		CheckBoxPropertyDescriptor rHeadEPD = new CheckBoxPropertyDescriptor(
				JRBaseGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE, "Reprint Header On Each Page");
		rHeadEPD.setDescription("Reprint header on each page.");
		desc.add(rHeadEPD);

		CheckBoxPropertyDescriptor keepToD = new CheckBoxPropertyDescriptor(JRBaseGroup.PROPERTY_KEEP_TOGETHER,
				"Keep Together");
		keepToD.setDescription("Keep together.");
		desc.add(keepToD);

		positionD = new JSSEnumPropertyDescriptor(JRBaseGroup.PROPERTY_FOOTER_POSITION, "Footer Position",
				FooterPositionEnum.class, NullEnum.NOTNULL);
		positionD.setDescription("Footer position.");
		desc.add(positionD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_NAME))
			return jrGroup.getName();
		if (id.equals(JRDesignGroup.PROPERTY_EXPRESSION))
			return ExprUtil.getExpression(jrGroup.getExpression());
		if (id.equals(JRBaseGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE))
			return jrGroup.getMinHeightToStartNewPage();

		if (id.equals(JRBaseGroup.PROPERTY_START_NEW_COLUMN))
			return jrGroup.isStartNewColumn();
		if (id.equals(JRBaseGroup.PROPERTY_START_NEW_PAGE))
			return jrGroup.isStartNewPage();
		if (id.equals(JRBaseGroup.PROPERTY_RESET_PAGE_NUMBER))
			return jrGroup.isResetPageNumber();
		if (id.equals(JRBaseGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE))
			return jrGroup.isReprintHeaderOnEachPage();
		if (id.equals(JRBaseGroup.PROPERTY_KEEP_TOGETHER))
			return jrGroup.isKeepTogether();
		if (id.equals(JRBaseGroup.PROPERTY_FOOTER_POSITION))
			return positionD.getEnumValue(jrGroup.getFooterPositionValue());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_NAME))
			jrGroup.setName((String) value);
		else if (id.equals(JRDesignGroup.PROPERTY_EXPRESSION))
			jrGroup.setExpression(ExprUtil.setValues(jrGroup.getExpression(), value, null));
		else if (id.equals(JRBaseGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE)) {
			int minH = 0;
			if (value != null)
				minH = Math.max(0, (Integer) value);
			jrGroup.setMinHeightToStartNewPage(minH);
		} else if (id.equals(JRDesignGroup.PROPERTY_START_NEW_COLUMN))
			jrGroup.setStartNewColumn((Boolean) value);
		else if (id.equals(JRDesignGroup.PROPERTY_START_NEW_PAGE))
			jrGroup.setStartNewPage((Boolean) value);
		else if (id.equals(JRDesignGroup.PROPERTY_RESET_PAGE_NUMBER))
			jrGroup.setResetPageNumber((Boolean) value);
		else if (id.equals(JRDesignGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE))
			jrGroup.setReprintHeaderOnEachPage((Boolean) value);
		else if (id.equals(JRDesignGroup.PROPERTY_KEEP_TOGETHER))
			jrGroup.setKeepTogether((Boolean) value);
		else if (id.equals(JRDesignGroup.PROPERTY_FOOTER_POSITION))
			jrGroup.setFooterPosition((FooterPositionEnum) positionD.getEnumValue(value));
	}

	/**
	 * Creates the jr group.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design group
	 */
	public static JRDesignGroup createJRGroup(JRDesignDataset jrDataset) {
		JRDesignGroup jrDesignGroup = new JRDesignGroup();
		jrDesignGroup.setName(ModelUtils.getDefaultName(jrDataset.getGroupsMap(), "Group")); //$NON-NLS-1$
		return jrDesignGroup;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MGroups)
			return true;
		return false;
	}

}
