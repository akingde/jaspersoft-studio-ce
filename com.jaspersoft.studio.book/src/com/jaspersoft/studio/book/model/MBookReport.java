/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.ImportDeclarationPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

public class MBookReport extends MReport {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private IPropertyDescriptor[] descriptors;

	public MBookReport(ANode parent, JasperReportsConfiguration jConfig) {
		super(parent, jConfig);
	}

	/**
	 * Handle group changed.
	 * 
	 * @param evt
	 *            the evt
	 */
	@Override
	protected void handleGroupChanged(PropertyChangeEvent evt) {
		if (evt.getOldValue() != null && evt.getNewValue() == null) { // delete
			JRDesignGroup group = (JRDesignGroup) evt.getOldValue();
			removeGroupListener(group);
			List<ANode> dNodes = new ArrayList<ANode>();
			for (INode node : getChildren()) {
				if (node instanceof MGroupReportPartContainer) {
					MGroupReportPartContainer band = (MGroupReportPartContainer) node;
					if (group.equals(band.getJrgroup()))
						dNodes.add(band);
				}
			}
			for (ANode n : dNodes) {
				removeChild(n);
			}
		} else if (evt instanceof CollectionElementAddedEvent && evt.getNewValue() != null && evt.getOldValue() == null) {
			JRDesignGroup group = (JRDesignGroup) evt.getNewValue();
			for (INode n : getChildren()) {
				if (n instanceof MGroupReportPartContainer && ((MGroupReportPartContainer) n).getJrgroup() == group)
					return;
			}

			boolean mainDataset = !getJasperDesign().getDatasetMap()
					.containsKey(((JRDataset) evt.getSource()).getName());
			if (mainDataset) {
				// find the right position where to put the band, considering
				// the position of where the group is
				// it's important to consider the position of the group because
				// in the creation it is always the last
				// but if there is an undo operation this could not be true
				int groupIndex = getJasperDesign().getGroupsList().indexOf(group);
				addGroupListener(group);
				int headerPosition = -1;
				for (INode node : getChildren()) {
					headerPosition++;
					if (node instanceof MReportPartContainer) {
						headerPosition += groupIndex;
						break;
					}
				}

				int footerPosition = getChildren().size() - groupIndex + 1;

				MGroupReportPartContainer mHeader = new MGroupReportPartContainer(this, group.getGroupHeaderSection(), headerPosition);
				mHeader.setJRGroup(group);
				createParts(group.getGroupHeaderSection(), mHeader);

				MGroupReportPartContainer mFooter = new MGroupReportPartContainer(this, group.getGroupFooterSection(), footerPosition);
				mFooter.setJRGroup(group);
				createParts(group.getGroupFooterSection(), mFooter);
			}
		}
	}

	private void createParts(JRSection section, MReportPartContainer parent) {
		if (section == null || section.getParts() == null)
			return;
		for (JRPart part : section.getParts()) {
			new MReportPart(parent, part, -1);
		}
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (ExpressionContext.class.equals(adapter)) {
			return ExpressionEditorSupportUtil.getReportExpressionContext();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {

		ImportDeclarationPropertyDescriptor importsD = new ImportDeclarationPropertyDescriptor(
				JasperDesign.PROPERTY_IMPORTS, Messages.MReport_imports);
		importsD.setDescription(Messages.MReport_imports_description);
		desc.add(importsD);
		importsD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#import")); //$NON-NLS-1$

		JSSTextPropertyDescriptor nameD = new JSSTextPropertyDescriptor(JasperDesign.PROPERTY_NAME,
				Messages.MReport_report_name);
		nameD.setDescription(Messages.MReport_report_name_description);
		nameD.setCategory(Messages.common_report);
		desc.add(nameD);

		// main dataset
		PropertyDescriptor datasetD = new PropertyDescriptor(JasperDesign.PROPERTY_MAIN_DATASET,
				Messages.MReport_main_dataset);
		datasetD.setDescription(Messages.MReport_main_dataset_description);
		desc.add(datasetD);

		// -------------------
		PixelPropertyDescriptor heightD = new PixelPropertyDescriptor(JasperDesign.PROPERTY_PAGE_HEIGHT,
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
				Messages.common_language, ModelUtils.getDefaultReportLanguages(), NullEnum.NOTNULL, false);
		languageD.setDescription(Messages.MReport_language_description);
		languageD.setCategory(Messages.common_report);
		desc.add(languageD);

		NamedEnumPropertyDescriptor<OrientationEnum> orientationD = new NamedEnumPropertyDescriptor<OrientationEnum>(
				JasperDesign.PROPERTY_ORIENTATION, Messages.MReport_page_orientation, OrientationEnum.LANDSCAPE,
				NullEnum.NOTNULL) {
			@Override
			public ASPropertyWidget<NamedEnumPropertyDescriptor<OrientationEnum>> createWidget(Composite parent,
					AbstractSection section) {
				Image[] images = new Image[] {
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/portrait16.png"), //$NON-NLS-1$
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/landscape16.png") }; //$NON-NLS-1$
				return new SPToolBarEnum<NamedEnumPropertyDescriptor<OrientationEnum>>(parent, section, this, images);
			}
		};
		orientationD.setDescription(Messages.MReport_page_orientation_description);
		orientationD.setCategory(Messages.MReport_report_page_category);
		desc.add(orientationD);

		NamedEnumPropertyDescriptor<PrintOrderEnum> printOrderD = new NamedEnumPropertyDescriptor<PrintOrderEnum>(
				JasperDesign.PROPERTY_PRINT_ORDER, Messages.MReport_print_order, PrintOrderEnum.HORIZONTAL,
				NullEnum.NULL);
		printOrderD.setDescription(Messages.MReport_print_order_description);
		printOrderD.setCategory(Messages.MReport_columns_category);
		desc.add(printOrderD);

		NamedEnumPropertyDescriptor<WhenNoDataTypeEnum> whenNoDataD = new NamedEnumPropertyDescriptor<WhenNoDataTypeEnum>(
				JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE, Messages.MReport_when_no_data_type,
				WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL, NullEnum.NULL);
		whenNoDataD.setDescription(Messages.MReport_when_no_data_type_description);
		whenNoDataD.setCategory(Messages.common_report);
		desc.add(whenNoDataD);

		JPropertiesPropertyDescriptor propertiesMapD = new JPropertiesPropertyDescriptor(MGraphicElement.PROPERTY_MAP,
				Messages.common_properties, getJasperConfiguration(), getValue());
		propertiesMapD.setDescription(Messages.common_properties);
		desc.add(propertiesMapD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#jasperReport"); //$NON-NLS-1$
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(JasperDesign.PROPERTY_PAGE_WIDTH, new DefaultValue(new Integer(595), false));
		defaultsMap.put(JasperDesign.PROPERTY_PAGE_HEIGHT, new DefaultValue(new Integer(842), false));
		defaultsMap.put(JasperDesign.PROPERTY_TOP_MARGIN, new DefaultValue(new Integer(30), false));
		defaultsMap.put(JasperDesign.PROPERTY_BOTTOM_MARGIN, new DefaultValue(new Integer(30), false));
		defaultsMap.put(JasperDesign.PROPERTY_LEFT_MARGIN, new DefaultValue(new Integer(20), false));
		defaultsMap.put(JasperDesign.PROPERTY_RIGHT_MARGIN, new DefaultValue(new Integer(20), false));
		defaultsMap.put(JasperDesign.PROPERTY_LANGUAGE, new DefaultValue("Java", false)); //$NON-NLS-1$
		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_COUNT, new DefaultValue(new Integer(1), false));
		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_WIDTH, new DefaultValue(new Integer(555), false));
		defaultsMap.put(JasperDesign.PROPERTY_COLUMN_SPACING, new DefaultValue(new Integer(0), false));

		int orientationValue = NamedEnumPropertyDescriptor.getIntValue(OrientationEnum.PORTRAIT, NullEnum.NOTNULL,
				OrientationEnum.PORTRAIT);
		defaultsMap.put(JasperDesign.PROPERTY_ORIENTATION, new DefaultValue(orientationValue, false));

		int printOrderValue = NamedEnumPropertyDescriptor.getIntValue(PrintOrderEnum.VERTICAL, NullEnum.NULL,
				PrintOrderEnum.VERTICAL);
		defaultsMap.put(JasperDesign.PROPERTY_PRINT_ORDER, new DefaultValue(printOrderValue, true));

		int whenNoDataValue = NamedEnumPropertyDescriptor.getIntValue(WhenNoDataTypeEnum.NO_PAGES, NullEnum.NULL,
				WhenNoDataTypeEnum.NO_PAGES);
		defaultsMap.put(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE, new DefaultValue(whenNoDataValue, true));

		return defaultsMap;
	}

}
