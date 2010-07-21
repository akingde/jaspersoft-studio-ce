package com.jaspersoft.studio.property.descriptor.properties.dialog;

/**
 * @author Chicu Veaceslav
 *
 */
public class PropertyDTO {
	public PropertyDTO() {
		super();
	}

	public PropertyDTO(String property, String description, String defValue) {
		super();
		this.property = property;
		this.description = description;
		this.defValue = defValue;
	}

	private String property;
	private String description;
	private String defValue;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
}
