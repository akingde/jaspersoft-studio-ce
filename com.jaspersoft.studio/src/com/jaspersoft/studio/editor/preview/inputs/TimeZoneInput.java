package com.jaspersoft.studio.editor.preview.inputs;

import java.util.Map;
import java.util.TimeZone;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.WTimeZone;

public class TimeZoneInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (TimeZone.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (TimeZone.class.isAssignableFrom(valueClass)) {
			final WTimeZone txt = new WTimeZone(parent, SWT.DROP_DOWN | SWT.BORDER);
			txt.setBackground(txt.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			txt.setToolTipText(param.getDescription());
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			if (params.get(param.getName()) != null)
				txt.setSelection((TimeZone) params.get(param.getName()));
			txt.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					params.put(param.getName(), txt.getTimeZone());
				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			return true;
		}
		return false;

	}
}
