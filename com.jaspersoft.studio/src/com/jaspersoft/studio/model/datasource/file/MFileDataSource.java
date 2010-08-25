package com.jaspersoft.studio.model.datasource.file;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MFileDataSource extends AMFileDataSource {
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("datasourceJDBC");
		return iconDescriptor;
	}

	public MFileDataSource() {
		super(null, -1);
	}

	public MFileDataSource(ANode parent, int index) {
		super(parent, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	@Override
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

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		NTextPropertyDescriptor recordDelimiter = new NTextPropertyDescriptor(PROPERTY_RECORDDELIMITER, "Record Delimiter");
		desc.add(recordDelimiter);

		NTextPropertyDescriptor columnDelimiter = new NTextPropertyDescriptor(PROPERTY_COLUMNDELIMITER, "Field Delimiter");
		desc.add(columnDelimiter);

		NTextPropertyDescriptor columnNames = new NTextPropertyDescriptor(PROPERTY_COLUMNNAMES, "Column Names");
		desc.add(columnNames);

		CheckBoxPropertyDescriptor firstRowHeaderD = new CheckBoxPropertyDescriptor(PROPERTY_FIRSTROWASHEADER,
				"First Row As Header");
		firstRowHeaderD.setDescription("First row as header");
		desc.add(firstRowHeaderD);

		defaultsMap.put(PROPERTY_COLUMNDELIMITER, ',');
		defaultsMap.put(PROPERTY_RECORDDELIMITER, "\\r\\n");
		defaultsMap.put(PROPERTY_FIRSTROWASHEADER, false);
	}

	public static final String PROPERTY_RECORDDELIMITER = "PROPERTY_RECORDDELIMITER";
	protected String recorddelimiter;

	public static final String PROPERTY_COLUMNDELIMITER = "PROPERTY_COLUMNDELIMITER";
	protected char columndelimiter;

	public static final String PROPERTY_FIRSTROWASHEADER = "PROPERTY_FIRSTROWASHEADER";
	protected boolean firstRowHeader;

	public static final String PROPERTY_COLUMNNAMES = "PROPERTY_COLUMNNAMES";
	protected String columnnames;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_RECORDDELIMITER))
			return recorddelimiter;
		if (id.equals(PROPERTY_COLUMNNAMES))
			return columnnames;
		if (id.equals(PROPERTY_COLUMNDELIMITER))
			return columndelimiter;
		if (id.equals(PROPERTY_FIRSTROWASHEADER))
			return firstRowHeader;
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_RECORDDELIMITER))
			recorddelimiter = (String) value;
		if (id.equals(PROPERTY_COLUMNNAMES))
			columnnames = (String) value;
		else if (id.equals(PROPERTY_COLUMNDELIMITER))
			columndelimiter = (Character) value;
		else if (id.equals(PROPERTY_FIRSTROWASHEADER))
			firstRowHeader = (Boolean) value;
		super.setPropertyValue(id, value);
	}
}
