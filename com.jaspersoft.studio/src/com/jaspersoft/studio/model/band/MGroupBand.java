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
package com.jaspersoft.studio.model.band;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.FooterPositionEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MGroupBand extends MGroup implements IPropertySource {

	private static final Integer CONST_MIN_HEIGHT = new Integer(0);

	public MGroupBand(JRDesignGroup jrDesignGroup) {
		super();
		setValue(jrDesignGroup);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		CheckBoxPropertyDescriptor startNewColumnD = new CheckBoxPropertyDescriptor(
				JRDesignGroup.PROPERTY_START_NEW_COLUMN, Messages.MGroupBand_start_new_column);
		startNewColumnD.setDescription(Messages.MGroupBand_start_new_column_description);
		desc.add(startNewColumnD);

		CheckBoxPropertyDescriptor startNewPageD = new CheckBoxPropertyDescriptor(JRDesignGroup.PROPERTY_START_NEW_PAGE,
				Messages.MGroupBand_start_new_page);
		startNewPageD.setDescription(Messages.MGroupBand_start_new_page_description);
		desc.add(startNewPageD);

		CheckBoxPropertyDescriptor reprintHeaderD = new CheckBoxPropertyDescriptor(
				JRDesignGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE, Messages.MGroupBand_reprint_header_on_each_page);
		reprintHeaderD
				.setDescription(Messages.MGroupBand_reprint_header_on_each_page_description);
		desc.add(reprintHeaderD);

		CheckBoxPropertyDescriptor resetPageNumberD = new CheckBoxPropertyDescriptor(
				JRDesignGroup.PROPERTY_RESET_PAGE_NUMBER, Messages.MGroupBand_reset_page_number);
		resetPageNumberD
				.setDescription(Messages.MGroupBand_reset_page_number_description);
		desc.add(resetPageNumberD);

		CheckBoxPropertyDescriptor keepTogetherD = new CheckBoxPropertyDescriptor(JRDesignGroup.PROPERTY_KEEP_TOGETHER,
				Messages.MGroupBand_keep_together);
		keepTogetherD
				.setDescription(Messages.MGroupBand_keep_together_description);
		desc.add(keepTogetherD);

		ComboBoxPropertyDescriptor footerPositionD = new ComboBoxPropertyDescriptor(JRDesignGroup.PROPERTY_FOOTER_POSITION,
				Messages.MGroupBand_footer_position, EnumHelper.getEnumNames(FooterPositionEnum.values(), NullEnum.NULL));
		footerPositionD
				.setDescription(Messages.MGroupBand_footer_position_description);
		desc.add(footerPositionD);

		IntegerPropertyDescriptor minHeightD = new IntegerPropertyDescriptor(
				JRDesignGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE, Messages.MGroupBand_min_height_to_start_new_page);
		minHeightD
				.setDescription(Messages.MGroupBand_min_height_to_start_new_page_description);
		desc.add(minHeightD);

		defaultsMap.put(JRDesignGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE, CONST_MIN_HEIGHT);
		defaultsMap.put(JRDesignGroup.PROPERTY_FOOTER_POSITION, FooterPositionEnum.NORMAL);
		defaultsMap.put(JRDesignGroup.PROPERTY_KEEP_TOGETHER, Boolean.FALSE);
		defaultsMap.put(JRDesignGroup.PROPERTY_RESET_PAGE_NUMBER, Boolean.FALSE);
		defaultsMap.put(JRDesignGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE, Boolean.FALSE);
		defaultsMap.put(JRDesignGroup.PROPERTY_START_NEW_COLUMN, Boolean.FALSE);
		defaultsMap.put(JRDesignGroup.PROPERTY_START_NEW_PAGE, Boolean.FALSE);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_START_NEW_COLUMN))
			return new Boolean(jrGroup.isStartNewColumn());
		if (id.equals(JRDesignGroup.PROPERTY_START_NEW_PAGE))
			return new Boolean(jrGroup.isStartNewPage());
		if (id.equals(JRDesignGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE))
			return new Boolean(jrGroup.isReprintHeaderOnEachPage());
		if (id.equals(JRDesignGroup.PROPERTY_RESET_PAGE_NUMBER))
			return new Boolean(jrGroup.isResetPageNumber());
		if (id.equals(JRDesignGroup.PROPERTY_KEEP_TOGETHER))
			return new Boolean(jrGroup.isKeepTogether());

		if (id.equals(JRDesignGroup.PROPERTY_FOOTER_POSITION))
			return EnumHelper.getValue(jrGroup.getFooterPositionValue(), 1, true);

		if (id.equals(JRDesignGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE))
			return new Integer(jrGroup.getMinHeightToStartNewPage());

		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_START_NEW_COLUMN))
			jrGroup.setStartNewColumn(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignGroup.PROPERTY_START_NEW_PAGE))
			jrGroup.setStartNewPage(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignGroup.PROPERTY_REPRINT_HEADER_ON_EACH_PAGE))
			jrGroup.setReprintHeaderOnEachPage(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignGroup.PROPERTY_RESET_PAGE_NUMBER))
			jrGroup.setResetPageNumber(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignGroup.PROPERTY_KEEP_TOGETHER))
			jrGroup.setKeepTogether(((Boolean) value).booleanValue());

		else if (id.equals(JasperDesign.PROPERTY_ORIENTATION))
			jrGroup.setFooterPosition((FooterPositionEnum) EnumHelper
					.getSetValue(FooterPositionEnum.values(), value, 1, true));

		else if (id.equals(JRDesignGroup.PROPERTY_MIN_HEIGHT_TO_START_NEW_PAGE))
			jrGroup.setMinHeightToStartNewPage(((Integer) value).intValue());
		else
			super.setPropertyValue(id, value);
	}

	@Override
	public String toString() {
		return getDisplayText();
	}
}
