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
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ImportDeclarationPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MReport.
 * 
 * @author Chicu Veaceslav
 */
public class MReport extends APropertyNode implements IGraphicElement, IContainerEditPart {

	private Map<Object, ANode> obj2Node = new HashMap<Object, ANode>();

	public void register(ANode n) {
		if (n.getValue() != null)
			obj2Node.put(n.getValue(), n);
	}

	public void unregister(ANode n) {
		if (n.getValue() != null)
			obj2Node.remove(n.getValue());
	}

	public ANode getNode(Object obj) {
		return obj2Node.get(obj);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("report"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m report.
	 * 
	 * @param parent
	 *          the parent
	 * @param jd
	 *          the jd
	 */
	public MReport(ANode parent, JasperDesign jd) {
		super(parent, -1);
		setValue(jd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		return this;
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

	private MDataset mDataset;

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JasperDesign.PROPERTY_NAME, Messages.MReport_report_name);
		nameD.setDescription(Messages.MReport_report_name_description);
		nameD.setCategory(Messages.common_report);
		desc.add(nameD);

		NClassTypePropertyDescriptor formatFactoryClassD = new NClassTypePropertyDescriptor(
				JasperDesign.PROPERTY_FORMAT_FACTORY_CLASS, Messages.MReport_format_factory_class);
		formatFactoryClassD.setDescription(Messages.MReport_format_factory_class_description);
		desc.add(formatFactoryClassD);

		ImportDeclarationPropertyDescriptor importsD = new ImportDeclarationPropertyDescriptor(
				JasperDesign.PROPERTY_IMPORTS, Messages.MReport_imports);
		importsD.setDescription(Messages.MReport_imports_description);
		desc.add(importsD);

		// main dataset
		PropertyDescriptor datasetD = new PropertyDescriptor(JasperDesign.PROPERTY_MAIN_DATASET,
				Messages.MReport_main_dataset);
		datasetD.setDescription(Messages.MReport_main_dataset_description);
		desc.add(datasetD);

		// -------------------
		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_PAGE_HEIGHT,
				Messages.MReport_page_height);
		heightD.setDescription(Messages.MReport_page_height_description);
		heightD.setCategory(Messages.MReport_report_page_category);
		desc.add(heightD);

		IntegerPropertyDescriptor widthD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_PAGE_WIDTH,
				Messages.MReport_page_width);
		widthD.setDescription(Messages.MReport_page_width_description);
		widthD.setCategory(Messages.MReport_report_page_category);
		desc.add(widthD);

		IntegerPropertyDescriptor rightMarginD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_RIGHT_MARGIN,
				Messages.MReport_right_margin);
		rightMarginD.setDescription(Messages.MReport_right_margin_description);
		rightMarginD.setCategory(Messages.MReport_report_page_category);
		desc.add(rightMarginD);

		IntegerPropertyDescriptor leftMarginD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_LEFT_MARGIN,
				Messages.MReport_left_margin);
		leftMarginD.setDescription(Messages.MReport_left_margin_description);
		leftMarginD.setCategory(Messages.MReport_report_page_category);
		desc.add(leftMarginD);

		IntegerPropertyDescriptor topMarginD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_TOP_MARGIN,
				Messages.MReport_top_margin);
		topMarginD.setDescription(Messages.MReport_top_margin_description);
		topMarginD.setCategory(Messages.MReport_report_page_category);
		desc.add(topMarginD);

		IntegerPropertyDescriptor bottomMarginD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_BOTTOM_MARGIN,
				Messages.MReport_bottom_margin);
		bottomMarginD.setDescription(Messages.MReport_bottom_margin_description);
		bottomMarginD.setCategory(Messages.MReport_report_page_category);
		desc.add(bottomMarginD);

		IntegerPropertyDescriptor columnCountD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_COLUMN_COUNT,
				Messages.MReport_column_count);
		columnCountD.setDescription(Messages.MReport_column_count_description);
		columnCountD.setCategory(Messages.MReport_columns_category);
		desc.add(columnCountD);

		IntegerPropertyDescriptor columnWidthD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_COLUMN_WIDTH,
				Messages.MReport_column_width);
		columnWidthD.setDescription(Messages.MReport_column_width_description);
		columnWidthD.setCategory(Messages.MReport_columns_category);
		desc.add(columnWidthD);

		IntegerPropertyDescriptor columnSpaceD = new IntegerPropertyDescriptor(JasperDesign.PROPERTY_COLUMN_SPACING,
				Messages.MReport_column_space);
		columnSpaceD.setDescription(Messages.MReport_column_space_description);
		columnSpaceD.setCategory(Messages.MReport_columns_category);
		desc.add(columnSpaceD);

		RWComboBoxPropertyDescriptor languageD = new RWComboBoxPropertyDescriptor(JasperDesign.PROPERTY_LANGUAGE,
				Messages.common_language, new String[] { "", "Java", "Groovy", "JavaScript", "bsh" }, NullEnum.NOTNULL); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		languageD.setDescription(Messages.MReport_language_description);
		languageD.setCategory(Messages.common_report);
		desc.add(languageD);

		ComboBoxPropertyDescriptor orientationD = new ComboBoxPropertyDescriptor(JasperDesign.PROPERTY_ORIENTATION,
				Messages.MReport_page_orientation, EnumHelper.getEnumNames(OrientationEnum.values(), NullEnum.NOTNULL));
		orientationD.setDescription(Messages.MReport_page_orientation_description);
		orientationD.setCategory(Messages.MReport_report_page_category);
		desc.add(orientationD);

		ComboBoxPropertyDescriptor printOrderD = new ComboBoxPropertyDescriptor(JasperDesign.PROPERTY_PRINT_ORDER,
				Messages.MReport_print_order, EnumHelper.getEnumNames(PrintOrderEnum.values(), NullEnum.NULL));
		printOrderD.setDescription(Messages.MReport_print_order_description);
		printOrderD.setCategory(Messages.MReport_columns_category);
		desc.add(printOrderD);

		ComboBoxPropertyDescriptor whenNoDataD = new ComboBoxPropertyDescriptor(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE,
				Messages.MReport_when_no_data_type, EnumHelper.getEnumNames(WhenNoDataTypeEnum.values(), NullEnum.NULL));
		whenNoDataD.setDescription(Messages.MReport_when_no_data_type_description);
		whenNoDataD.setCategory(Messages.common_report);
		desc.add(whenNoDataD);

		// checkboxes
		CheckBoxPropertyDescriptor titleNewPageD = new CheckBoxPropertyDescriptor(JasperDesign.PROPERTY_TITLE_NEW_PAGE,
				Messages.MReport_title_on_a_new_page);
		titleNewPageD.setDescription(Messages.MReport_title_on_a_new_page_description);
		desc.add(titleNewPageD);

		CheckBoxPropertyDescriptor summaryNewPageD = new CheckBoxPropertyDescriptor(JasperDesign.PROPERTY_SUMMARY_NEW_PAGE,
				Messages.MReport_summary_on_a_new_page);
		summaryNewPageD.setDescription(Messages.MReport_summary_on_a_new_page_description);
		desc.add(summaryNewPageD);

		CheckBoxPropertyDescriptor summaryWHFD = new CheckBoxPropertyDescriptor(
				JasperDesign.PROPERTY_SUMMARY_WITH_PAGE_HEADER_AND_FOOTER, Messages.MReport_summary_with_page_header_and_footer);
		summaryWHFD.setDescription(Messages.MReport_summary_with_page_header_and_footer_description);
		desc.add(summaryWHFD);

		CheckBoxPropertyDescriptor floatColumnFooterD = new CheckBoxPropertyDescriptor(
				JasperDesign.PROPERTY_FLOAT_COLUMN_FOOTER, Messages.MReport_float_column_footer);
		floatColumnFooterD.setDescription(Messages.MReport_float_column_footer_description);
		desc.add(floatColumnFooterD);

		CheckBoxPropertyDescriptor ignorePaginationD = new CheckBoxPropertyDescriptor(
				JasperDesign.PROPERTY_IGNORE_PAGINATION, Messages.MReport_ignore_pagination);
		ignorePaginationD.setDescription(Messages.MReport_ignore_pagination_description);
		desc.add(ignorePaginationD);

		defaultsMap.put(JasperDesign.PROPERTY_PAGE_WIDTH, new Integer(595));
		defaultsMap.put(JasperDesign.PROPERTY_PAGE_HEIGHT, new Integer(842));
		defaultsMap.put(JasperDesign.PROPERTY_TOP_MARGIN, new Integer(30));
		defaultsMap.put(JasperDesign.PROPERTY_BOTTOM_MARGIN, new Integer(30));
		defaultsMap.put(JasperDesign.PROPERTY_LEFT_MARGIN, new Integer(20));
		defaultsMap.put(JasperDesign.PROPERTY_RIGHT_MARGIN, new Integer(20));

		defaultsMap.put(JasperDesign.PROPERTY_LANGUAGE, "Java"); //$NON-NLS-1$

		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_COUNT, new Integer(1));
		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_WIDTH, new Integer(555));
		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_SPACING, new Integer(0));
		defaultsMap.put(JasperDesign.PROPERTY_ORIENTATION, EnumHelper.getValue(OrientationEnum.PORTRAIT, 1, false));
		defaultsMap.put(JasperDesign.PROPERTY_PRINT_ORDER, EnumHelper.getValue(PrintOrderEnum.VERTICAL, 1, true));
		defaultsMap.put(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE, EnumHelper.getValue(WhenNoDataTypeEnum.NO_PAGES, 1, true));
		defaultsMap.put(JasperDesign.PROPERTY_TITLE_NEW_PAGE, Boolean.FALSE);
		defaultsMap.put(JasperDesign.PROPERTY_SUMMARY_NEW_PAGE, Boolean.FALSE);
		defaultsMap.put(JasperDesign.PROPERTY_SUMMARY_WITH_PAGE_HEADER_AND_FOOTER, Boolean.FALSE);
		defaultsMap.put(JasperDesign.PROPERTY_FLOAT_COLUMN_FOOTER, Boolean.FALSE);
		defaultsMap.put(JasperDesign.PROPERTY_IGNORE_PAGINATION, Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JasperDesign jrDesign = (JasperDesign) getValue();
		if (id.equals(JasperDesign.PROPERTY_NAME))
			return jrDesign.getName();
		if (id.equals(JasperDesign.PROPERTY_FORMAT_FACTORY_CLASS))
			return jrDesign.getFormatFactoryClass();
		if (id.equals(JasperDesign.PROPERTY_IMPORTS)) {
			String res = ""; //$NON-NLS-1$
			String[] imports = jrDesign.getImports();
			if (imports != null) {
				int lenght = imports.length;
				for (int i = 0; i < lenght; i++) {
					res += imports[i] + ";"; //$NON-NLS-1$
				}
			}
			return res;
		}

		if (id.equals(JasperDesign.PROPERTY_MAIN_DATASET)) {
			if (mDataset == null) {
				mDataset = new MDataset((JRDesignDataset) jrDesign.getMainDataset());
				setChildListener(mDataset);
			}
			return mDataset;
		}

		if (id.equals(JasperDesign.PROPERTY_PAGE_HEIGHT))
			return new Integer(jrDesign.getPageHeight());
		if (id.equals(JasperDesign.PROPERTY_PAGE_WIDTH))
			return new Integer(jrDesign.getPageWidth());
		if (id.equals(JasperDesign.PROPERTY_RIGHT_MARGIN))
			return new Integer(jrDesign.getRightMargin());
		if (id.equals(JasperDesign.PROPERTY_LEFT_MARGIN))
			return new Integer(jrDesign.getLeftMargin());
		if (id.equals(JasperDesign.PROPERTY_TOP_MARGIN))
			return new Integer(jrDesign.getTopMargin());
		if (id.equals(JasperDesign.PROPERTY_BOTTOM_MARGIN))
			return new Integer(jrDesign.getBottomMargin());
		if (id.equals(JasperDesign.PROPERTY_COLUMN_COUNT))
			return new Integer(jrDesign.getColumnCount());
		if (id.equals(JasperDesign.PROPERTY_COLUMN_SPACING))
			return new Integer(jrDesign.getColumnSpacing());
		if (id.equals(JasperDesign.PROPERTY_COLUMN_WIDTH))
			return new Integer(jrDesign.getColumnWidth());

		if (id.equals(JasperDesign.PROPERTY_LANGUAGE))
			return jrDesign.getLanguage();

		if (id.equals(JasperDesign.PROPERTY_ORIENTATION))
			return EnumHelper.getValue(jrDesign.getOrientationValue(), 1, false);
		if (id.equals(JasperDesign.PROPERTY_PRINT_ORDER))
			return EnumHelper.getValue(jrDesign.getPrintOrderValue(), 1, true);
		if (id.equals(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE))
			return EnumHelper.getValue(jrDesign.getWhenNoDataTypeValue(), 1, true);

		if (id.equals(JasperDesign.PROPERTY_TITLE_NEW_PAGE))
			return new Boolean(jrDesign.isTitleNewPage());
		if (id.equals(JasperDesign.PROPERTY_SUMMARY_NEW_PAGE))
			return new Boolean(jrDesign.isSummaryNewPage());
		if (id.equals(JasperDesign.PROPERTY_SUMMARY_WITH_PAGE_HEADER_AND_FOOTER))
			return new Boolean(jrDesign.isSummaryWithPageHeaderAndFooter());
		if (id.equals(JasperDesign.PROPERTY_FLOAT_COLUMN_FOOTER))
			return new Boolean(jrDesign.isFloatColumnFooter());
		if (id.equals(JasperDesign.PROPERTY_IGNORE_PAGINATION))
			return new Boolean(jrDesign.isIgnorePagination());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JasperDesign jrDesign = (JasperDesign) getValue();
		if (id.equals(JasperDesign.PROPERTY_NAME))
			jrDesign.setName((String) value);
		else if (id.equals(JasperDesign.PROPERTY_FORMAT_FACTORY_CLASS))
			jrDesign.setFormatFactoryClass((String) value);
		else if (id.equals(JasperDesign.PROPERTY_IMPORTS)) {
			String[] imports = jrDesign.getImports();
			if (imports != null) {
				int lenght = imports.length;
				for (int i = 0; i < lenght; i++) {
					jrDesign.removeImport(imports[i]);
				}
			}
			if (value != null && value instanceof String) {
				StringTokenizer st = new StringTokenizer((String) value, ";"); //$NON-NLS-1$
				while (st.hasMoreTokens()) {
					jrDesign.addImport(st.nextToken());
				}
			}
		}

		else if (id.equals(JasperDesign.PROPERTY_LANGUAGE))
			jrDesign.setLanguage(value == null ? null : ((String) value).toLowerCase());

		else if (id.equals(JasperDesign.PROPERTY_PAGE_HEIGHT))
			jrDesign.setPageHeight(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_PAGE_WIDTH))
			jrDesign.setPageWidth(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_RIGHT_MARGIN))
			jrDesign.setRightMargin(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_LEFT_MARGIN))
			jrDesign.setLeftMargin(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_TOP_MARGIN))
			jrDesign.setTopMargin(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_BOTTOM_MARGIN))
			jrDesign.setBottomMargin(((Integer) value).intValue());

		else if (id.equals(JasperDesign.PROPERTY_COLUMN_COUNT))
			jrDesign.setColumnCount(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_COLUMN_SPACING))
			jrDesign.setColumnSpacing(((Integer) value).intValue());
		else if (id.equals(JasperDesign.PROPERTY_COLUMN_WIDTH))
			jrDesign.setColumnWidth(((Integer) value).intValue());
		// -- enums
		else if (id.equals(JasperDesign.PROPERTY_ORIENTATION))
			jrDesign.setOrientation((OrientationEnum) EnumHelper.getSetValue(OrientationEnum.values(), value, 1, false));
		else if (id.equals(JasperDesign.PROPERTY_PRINT_ORDER))
			jrDesign.setPrintOrder((PrintOrderEnum) EnumHelper.getSetValue(PrintOrderEnum.values(), value, 1, true));
		else if (id.equals(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE))
			jrDesign.setWhenNoDataType((WhenNoDataTypeEnum) EnumHelper.getSetValue(WhenNoDataTypeEnum.values(), value, 1,
					true));
		// -- booleans
		else if (id.equals(JasperDesign.PROPERTY_TITLE_NEW_PAGE))
			jrDesign.setTitleNewPage(((Boolean) value).booleanValue());
		else if (id.equals(JasperDesign.PROPERTY_SUMMARY_NEW_PAGE))
			jrDesign.setSummaryNewPage(((Boolean) value).booleanValue());
		else if (id.equals(JasperDesign.PROPERTY_SUMMARY_WITH_PAGE_HEADER_AND_FOOTER))
			jrDesign.setSummaryWithPageHeaderAndFooter(((Boolean) value).booleanValue());
		else if (id.equals(JasperDesign.PROPERTY_FLOAT_COLUMN_FOOTER))
			jrDesign.setFloatColumnFooter(((Boolean) value).booleanValue());
		else if (id.equals(JasperDesign.PROPERTY_IGNORE_PAGINATION))
			jrDesign.setIgnorePagination(((Boolean) value).booleanValue());
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
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JasperDesign) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultHeight()
	 */
	public int getDefaultHeight() {
		return 800;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultWidth()
	 */
	public int getDefaultWidth() {
		return 800;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JasperDesign.PROPERTY_DATASETS)) {
			handleDatasourceChanged(evt);
		} else if (evt.getPropertyName().equals(JasperDesign.PROPERTY_TITLE)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_PAGE_HEADER)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_COLUMN_HEADER)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_COLUMN_FOOTER)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_PAGE_FOOTER)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_LAST_PAGE_FOOTER)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_SUMMARY)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_NO_DATA)
				|| evt.getPropertyName().equals(JasperDesign.PROPERTY_BACKGROUND)) {
			handleBandChanged(evt);
		} else if (evt.getPropertyName().equals(JRDesignSection.PROPERTY_BANDS)) {
			handleDetailBandChanged(evt);
		} else if (evt.getPropertyName().equals(JRDesignDataset.PROPERTY_GROUPS)) {
			handleGroupChanged(evt);
		}
		super.propertyChange(evt);
	}

	/**
	 * Handle datasource changed.
	 * 
	 * @param evt
	 *          the evt
	 */
	private void handleDatasourceChanged(PropertyChangeEvent evt) {
		if (evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -1;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex() + 5;
				}
				if (evt.getNewValue() instanceof JRDesignDataset) {

					// add the node to this parent
					JRDesignDataset jrDataset = (JRDesignDataset) evt.getNewValue();
					if (this.findElement(jrDataset) == -1) {
						ANode n = ReportFactory.createNode(this, jrDataset, newIndex);
						ReportFactory.createDataset(n, jrDataset, true);
					}
				}
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				// delete
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						removeChild((ANode) n);
						break;
					}
				}
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}
		}
	}

	/**
	 * Handle band changed.
	 * 
	 * @param evt
	 *          the evt
	 */
	private void handleBandChanged(PropertyChangeEvent evt) {
		for (Iterator<?> it = getChildren().iterator(); it.hasNext();) {
			ANode node = (ANode) it.next();
			if (node instanceof MBand) {
				MBand mBand = (MBand) node;
				if (evt.getPropertyName().equals(mBand.getBandType().getName())) {
					mBand.setValue(evt.getNewValue());
					if (evt.getNewValue() != null)
						ReportFactory.createElementsForBand(mBand, ((JRDesignBand) evt.getNewValue()).getChildren());
					else
						mBand.removeChildren();
					mBand.propertyChange(new PropertyChangeEvent(mBand, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
					break;
				}
			}
		}
	}

	/**
	 * Handle detail band changed.
	 * 
	 * @param evt
	 *          the evt
	 */
	private void handleDetailBandChanged(PropertyChangeEvent evt) {
		MBand firstBand = null;
		MBand lastBand = null;
		int lastIndex = 0;
		JRDesignSection source = (JRDesignSection) evt.getSource();
		JROrigin sourceOrigin = source.getOrigin();
		String groupName = sourceOrigin.getGroupName();
		for (Iterator<?> it = getChildren().iterator(); it.hasNext();) {
			ANode node = (ANode) it.next();
			if (node instanceof MBand) {
				MBand mBand = (MBand) node;

				if ((mBand instanceof MBandGroupHeader && groupName != null
						&& sourceOrigin.getBandTypeValue().equals(BandTypeEnum.GROUP_HEADER) && groupName
						.equals(((MBandGroupHeader) mBand).getJrGroup().getName()))

						|| (mBand instanceof MBandGroupFooter && groupName != null
								&& sourceOrigin.getBandTypeValue().equals(BandTypeEnum.GROUP_FOOTER) && groupName
								.equals(((MBandGroupFooter) mBand).getJrGroup().getName()))

						|| (sourceOrigin.getBandTypeValue().equals(BandTypeEnum.DETAIL) && BandTypeEnum.DETAIL.equals(mBand
								.getBandType()))) {
					if (firstBand == null)
						firstBand = mBand;
					lastBand = mBand;
				} else if (firstBand != null)
					break;
			}
			lastIndex++;
		}
		if (evt.getNewValue() != null) {
			// new value
			if (firstBand != null && firstBand.equals(lastBand) && firstBand.getValue() == null) {
				firstBand.setValue(evt.getNewValue());
			} else {
				int index = lastIndex;
				if (evt instanceof CollectionElementAddedEvent) {
					index = getChildren().indexOf(firstBand) + ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				if (firstBand instanceof MBandGroupHeader) {
					firstBand = new MBandGroupHeader(this, ((MBandGroupHeader) firstBand).getJrGroup(),
							(JRBand) evt.getNewValue(), index);
				} else if (firstBand instanceof MBandGroupFooter) {
					firstBand = new MBandGroupFooter(this, ((MBandGroupFooter) firstBand).getJrGroup(),
							(JRBand) evt.getNewValue(), index);
				} else
					firstBand = (MBand) ReportFactory.createNode(this, evt.getNewValue(), index);
			}
			ReportFactory.createElementsForBand(firstBand, ((JRDesignBand) evt.getNewValue()).getChildren());
			firstBand.propertyChange(new PropertyChangeEvent(firstBand, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
		} else {
			// delete
			if (firstBand != null && firstBand.equals(lastBand)) {
				firstBand.setValue(evt.getNewValue());
				firstBand.removeChildren();
				firstBand.propertyChange(new PropertyChangeEvent(firstBand, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
			} else {
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						firstBand = (MBand) n;
						removeChild(firstBand);
						break;
					}
				}
			}
		}
	}

	/**
	 * Handle group changed.
	 * 
	 * @param evt
	 *          the evt
	 */
	private void handleGroupChanged(PropertyChangeEvent evt) {
		if (evt.getOldValue() != null && evt.getNewValue() == null) { // delete
			JRDesignGroup group = (JRDesignGroup) evt.getOldValue();
			removeGroupListener(group);
			List<ANode> dNodes = new ArrayList<ANode>();
			for (INode node : getChildren()) {
				if (node instanceof MBandGroupHeader) {
					MBandGroupHeader band = (MBandGroupHeader) node;
					if (band.getJrGroup().equals(group))
						dNodes.add(band);
				} else if (node instanceof MBandGroupFooter) {
					MBandGroupFooter band = (MBandGroupFooter) node;
					if (band.getJrGroup().equals(group))
						dNodes.add(band);
				}
			}
			for (ANode n : dNodes) {
				removeChild(n);
			}
		} else if (evt instanceof CollectionElementAddedEvent && evt.getNewValue() != null && evt.getOldValue() == null) {
			JRDesignGroup group = (JRDesignGroup) evt.getNewValue();
			for (INode n : getChildren()) {
				if (n instanceof MBandGroupHeader && ((MBandGroupHeader) n).getJrGroup() == group)
					return;
				if (n instanceof MBandGroupFooter && ((MBandGroupFooter) n).getJrGroup() == group)
					return;
			}

			// find the right position to put the band
			addGroupListener(group);
			JRDesignGroup gr = null;
			int grPosition = ((JRDesignDataset) getJasperDesign().getMainDataset()).getGroupsList().size()
					- ((CollectionElementAddedEvent) evt).getAddedIndex();
			int grCount = 0;
			int position = 0;
			for (INode node : getChildren()) {
				if (node instanceof MBandGroupHeader) {
					MBandGroupHeader band = (MBandGroupHeader) node;
					if (gr == null || !gr.equals(band.getJrGroup())) {
						gr = band.getJrGroup();
						grCount++;
						if (grCount < grPosition) // ok, we are after the group
							break;
					}
					// ok, I'm now just create in the right position the bands
				} else if (node instanceof MBand && ((MBand) node).getBandType().equals(BandTypeEnum.DETAIL))
					break;
				position++;
			}
			if (group.getGroupHeaderSection() != null) {
				List<?> grhBands = ((JRDesignSection) group.getGroupHeaderSection()).getBandsList();
				if (grhBands != null) {
					if (grhBands.isEmpty()) {
						MBand b = new MBandGroupHeader(this, group, null, position);
						b.propertyChange(new PropertyChangeEvent(b, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
					} else {
						int j = 0;
						for (Iterator<?> it = grhBands.iterator(); it.hasNext(); j++) {
							JRDesignBand jrDB = (JRDesignBand) it.next();
							MBandGroupHeader b = new MBandGroupHeader(this, group, jrDB, position + j);
							ReportFactory.createElementsForBand(b, jrDB.getChildren());
							b.propertyChange(new PropertyChangeEvent(b, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
						}
					}
				}
			}
			grCount = 0;
			position = getChildren().size();
			gr = null;
			// ADD FOOTER
			for (ListIterator<INode> it = getChildren().listIterator(getChildren().size()); it.hasPrevious();) {
				INode node = it.previous();
				if (node instanceof MBandGroupFooter) {
					MBandGroupFooter band = (MBandGroupFooter) node;
					if (gr == null || !gr.equals(band.getJrGroup())) {
						gr = band.getJrGroup();
						grCount++;
						if (grCount < grPosition) // ok, we are after the group
							break;
					}
					// ok, I'm now just create in the right position the bands
				} else if (node instanceof MBand && ((MBand) node).getBandType().equals(BandTypeEnum.DETAIL))
					break;
				position--;
			}
			if (group.getGroupFooterSection() != null) {
				List<?> grhBands = ((JRDesignSection) group.getGroupFooterSection()).getBandsList();
				if (grhBands != null) {
					if (grhBands.isEmpty()) {
						MBand b = new MBandGroupFooter(this, group, null, position);
						b.propertyChange(new PropertyChangeEvent(b, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
					} else {
						int j = 0;
						for (Iterator<?> it = grhBands.iterator(); it.hasNext(); j++) {
							JRDesignBand jrDB = (JRDesignBand) it.next();
							MBandGroupFooter b = new MBandGroupFooter(this, group, jrDB, position + j);
							ReportFactory.createElementsForBand(b, jrDB.getChildren());
							b.propertyChange(new PropertyChangeEvent(b, "VALUE", evt.getOldValue(), evt.getNewValue())); //$NON-NLS-1$
						}
					}
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			((JRDesignSection) ((JasperDesign) getValue()).getDetailSection()).getEventSupport()
					.removePropertyChangeListener(this);
			List<?> groups = ((JRDesignDataset) ((JasperDesign) getValue()).getMainDataset()).getGroupsList();
			for (Object obj : groups)
				removeGroupListener((JRDesignGroup) obj);
			mDataset.setValue(null);
		} else if (value != null) {
			List<?> groups = ((JRDesignDataset) ((JasperDesign) value).getMainDataset()).getGroupsList();
			for (Object obj : groups)
				addGroupListener((JRDesignGroup) obj);
			((JRDesignSection) ((JasperDesign) value).getDetailSection()).getEventSupport().addPropertyChangeListener(this);
			mDataset = null;
		}
		super.setValue(value);
	}

	/**
	 * Adds the group listener.
	 * 
	 * @param gr
	 *          the gr
	 */
	private void addGroupListener(JRDesignGroup gr) {
		((JRDesignSection) gr.getGroupFooterSection()).getEventSupport().addPropertyChangeListener(this);
		((JRDesignSection) gr.getGroupHeaderSection()).getEventSupport().addPropertyChangeListener(this);
	}

	/**
	 * Removes the group listener.
	 * 
	 * @param gr
	 *          the gr
	 */
	private void removeGroupListener(JRDesignGroup gr) {
		((JRDesignSection) gr.getGroupFooterSection()).getEventSupport().removePropertyChangeListener(this);
		((JRDesignSection) gr.getGroupHeaderSection()).getEventSupport().removePropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getBounds()
	 */
	public Rectangle getBounds() {
		JasperDesign jd = getJasperDesign();
		return new Rectangle(jd.getLeftMargin(), jd.getTopMargin(), jd.getPageWidth() - jd.getLeftMargin()
				- jd.getRightMargin(), jd.getPageHeight() - jd.getTopMargin() - jd.getBottomMargin());
	}

}
