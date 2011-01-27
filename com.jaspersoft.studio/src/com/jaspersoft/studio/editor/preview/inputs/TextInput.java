package com.jaspersoft.studio.editor.preview.inputs;

import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class TextInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (String.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (isForType(valueClass)) {
			final Text txt = new Text(parent, SWT.BORDER);
			txt.setToolTipText(param.getDescription());
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			txt.addModifyListener(new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					params.put(param.getName(), txt.getText());
				}
			});
			if (params.get(param.getName()) != null)
				txt.setText((String) params.get(param.getName()));
			return true;
		}
		return false;
	}
}
