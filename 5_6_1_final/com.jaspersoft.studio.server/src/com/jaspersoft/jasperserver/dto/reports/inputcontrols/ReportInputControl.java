/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.jasperserver.dto.reports.inputcontrols;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.jaspersoft.jasperserver.dto.common.validations.DateTimeFormatValidationRule;
import com.jaspersoft.jasperserver.dto.common.validations.MandatoryValidationRule;
import com.jaspersoft.jasperserver.dto.common.validations.RangeValidationRule;
import com.jaspersoft.jasperserver.dto.common.validations.RegexpValidationRule;
import com.jaspersoft.jasperserver.dto.common.validations.ValidationRule;

/**
 * @author akasych
 * @version $Id: ReportInputControl.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement
public class ReportInputControl implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private String type;
	private String uri;
	private String label;
	private Boolean mandatory;
	private Boolean readOnly;
	private Boolean visible;
	private List<String> masterDependencies = new ArrayList<String>();
	private List<String> slaveDependencies = new ArrayList<String>();
	private List<ValidationRule<?>> validationRules;
	private InputControlState state;

	public InputControlState getState() {
		return state;
	}

	public ReportInputControl setState(InputControlState state) {
		this.state = state;
		return this;
	}

	@XmlElementWrapper(name = "validationRules")
	@XmlElements({ @XmlElement(name = "rangeValidationRule", type = RangeValidationRule.class), @XmlElement(name = "regexpValidationRule", type = RegexpValidationRule.class),
			@XmlElement(name = "dateTimeFormatValidationRule", type = DateTimeFormatValidationRule.class), @XmlElement(name = "mandatoryValidationRule", type = MandatoryValidationRule.class) })
	public List<ValidationRule<?>> getValidationRules() {
		return validationRules;
	}

	public ReportInputControl setValidationRules(List<ValidationRule<?>> validationRules) {
		this.validationRules = validationRules;
		return this;
	}

	public String getType() {
		return type;
	}

	public ReportInputControl setType(String type) {
		this.type = type;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ReportInputControl setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getUri() {
		return uri;
	}

	public ReportInputControl setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public ReportInputControl setLabel(String label) {
		this.label = label;
		return this;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public ReportInputControl setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
		return this;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public ReportInputControl setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public Boolean getVisible() {
		return visible;
	}

	public ReportInputControl setVisible(Boolean visible) {
		this.visible = visible;
		return this;
	}

	@XmlElementWrapper(name = "masterDependencies")
	@XmlElement(name = "controlId")
	public List<String> getMasterDependencies() {
		return masterDependencies;
	}

	@XmlElementWrapper(name = "slaveDependencies")
	@XmlElement(name = "controlId")
	public List<String> getSlaveDependencies() {
		return slaveDependencies;
	}

	public String getId() {
		return id;
	}

	public ReportInputControl setId(String id) {
		this.id = id;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ReportInputControl))
			return false;

		ReportInputControl that = (ReportInputControl) o;

		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (!id.equals(that.id))
			return false;
		if (label != null ? !label.equals(that.label) : that.label != null)
			return false;
		if (mandatory != null ? !mandatory.equals(that.mandatory) : that.mandatory != null)
			return false;
		if (masterDependencies != null ? !masterDependencies.equals(that.masterDependencies) : that.masterDependencies != null)
			return false;
		if (readOnly != null ? !readOnly.equals(that.readOnly) : that.readOnly != null)
			return false;
		if (slaveDependencies != null ? !slaveDependencies.equals(that.slaveDependencies) : that.slaveDependencies != null)
			return false;
		if (state != null ? !state.equals(that.state) : that.state != null)
			return false;
		if (!type.equals(that.type))
			return false;
		if (!uri.equals(that.uri))
			return false;
		if (validationRules != null ? !validationRules.equals(that.validationRules) : that.validationRules != null)
			return false;
		if (visible != null ? !visible.equals(that.visible) : that.visible != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + type.hashCode();
		result = 31 * result + uri.hashCode();
		result = 31 * result + (label != null ? label.hashCode() : 0);
		result = 31 * result + (mandatory != null ? mandatory.hashCode() : 0);
		result = 31 * result + (readOnly != null ? readOnly.hashCode() : 0);
		result = 31 * result + (visible != null ? visible.hashCode() : 0);
		result = 31 * result + (masterDependencies != null ? masterDependencies.hashCode() : 0);
		result = 31 * result + (slaveDependencies != null ? slaveDependencies.hashCode() : 0);
		result = 31 * result + (validationRules != null ? validationRules.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		return result;
	}
}
