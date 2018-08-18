/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;

public abstract class AOperandWidget<T extends AOperand> extends Composite {
	protected T value;
	private boolean exludeField = false;
	private Set<Class<? extends AOperand>> menuOperands;
	protected AQueryDesigner designer;
	protected List<AOperand> operands;
	protected int index;

	public AOperandWidget(Composite parent, int style, T operand, AQueryDesigner designer) {
		super(parent, style);
		this.designer = designer;
		this.value = operand;
		createWidget(parent);
	}

	public AQueryDesigner getDesigner() {
		return designer;
	}

	public boolean isMenuOperands(Class<? extends AOperand> op) {
		if (menuOperands != null) {
			return menuOperands.contains(op);
		}
		return true;
	}

	public void setMenuOperands(Set<Class<? extends AOperand>> menuOperands) {
		this.menuOperands = menuOperands;
	}

	public Set<Class<? extends AOperand>> getMenuOperands() {
		return menuOperands;
	}

	public void setExludeField(boolean exludeField) {
		this.exludeField = exludeField;
	}

	public boolean isExludeField() {
		return exludeField;
	}

	protected abstract void createWidget(Composite parent);

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	private Map<String, AOperand> operandMap;

	public void setOperandMap(Map<String, AOperand> opMap) {
		this.operandMap = opMap;
	}

	public Map<String, AOperand> getOperandMap() {
		return operandMap;
	}

	public void setOperands(List<AOperand> operands, int index) {
		this.operands = operands;
		this.index = index;
	}
}
