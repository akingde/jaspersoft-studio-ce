package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.UIUtils;

public abstract class ADataInput implements IDataInput {
	protected Map<String, Object> params;
	protected IParameter param;
	private PropertyChangeSupport pcsupport = new PropertyChangeSupport(this);

	public void addChangeListener(PropertyChangeListener listener) {
		pcsupport.addPropertyChangeListener(listener);
	}

	public void removeChangeListener(PropertyChangeListener listener) {
		pcsupport.removePropertyChangeListener(listener);
	}

	public void dispose() {
		for (PropertyChangeListener pcl : pcsupport.getPropertyChangeListeners())
			pcsupport.removePropertyChangeListener(pcl);
	}

	public IDataInput getInstance() {
		try {
			return this.getClass().newInstance();
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return null;
	}

	public IParameter getParameter() {
		return param;
	}

	public void createInput(Composite parent, IParameter param, Map<String, Object> params) {
		this.params = params;
		this.param = param;
	}

	public final void updateModel(Object value) {
		updateParameterMap(value);
		pcsupport.firePropertyChange(param.getName(), null, value);
	}

	protected void updateParameterMap(Object value) {
		params.put(param.getName(), value);
	}

	public boolean isLabeled() {
		return false;
	}

	public static void setMandatory(IParameter param, Control num) {
		if (param.isMandatory()) {
			ControlDecoration controlDecoration = new ControlDecoration(num, SWT.LEFT | SWT.TOP);
			controlDecoration.setDescriptionText("this field is mandatory");
			controlDecoration.setImage(FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_REQUIRED).getImage());
		}
	}
}
