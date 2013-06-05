package com.jaspersoft.studio.data.sql.widgets;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;

public abstract class AOperandWidget<T extends AOperand> extends Composite {
	private T value;

	public AOperandWidget(Composite parent, int style, T operand) {
		super(parent, style);
		this.value = operand;
		createWidget(parent);
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

}
