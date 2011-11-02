package com.jaspersoft.studio.server.properties;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class InputControlSection extends ASection {
	private CCombo ctype;
	private Button bmand;
	private Button bread;
	private Button bvisible;

	@Override
	protected void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		AbstractSection.createLabel(parent, getWidgetFactory(), "Type", 120);

		ctype = getWidgetFactory().createCCombo(parent,
				SWT.BORDER | SWT.READ_ONLY);
		ctype.setItems(new String[] { "Boolean", "Single Value",
				"Single Select List of Values",
				"Single Select List of Values (Radio)",
				"Multi Select List of Values",
				"Multi Select List of Values (Checkbox)",
				"Single Select Query", "Single Select Query (Radio)",
				"Multi Select Query", "Multi Select Query (Checkbox)" });

		AbstractSection.createLabel(parent, getWidgetFactory(), "", 120);

		bmand = getWidgetFactory().createButton(parent, "Mandatory", SWT.CHECK);
		bmand.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		AbstractSection.createLabel(parent, getWidgetFactory(), "", 120);

		bread = getWidgetFactory().createButton(parent, "Read Only", SWT.CHECK);
		bread.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		AbstractSection.createLabel(parent, getWidgetFactory(), "", 120);

		bvisible = getWidgetFactory()
				.createButton(parent, "Visible", SWT.CHECK);
		bvisible.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	public void enableFields(boolean enable) {
		ctype.setEnabled(enable);
		bmand.setEnabled(enable);
		bread.setEnabled(enable);
		bvisible.setEnabled(enable);
	}

	@Override
	protected void bind() {
		bindingContext.bindValue(SWTObservables
				.observeSingleSelectionIndex(ctype), PojoObservables
				.observeValue(getProxy(res.getValue()), "controlType"));

		bindingContext.bindValue(SWTObservables.observeSelection(bmand),
				PojoObservables.observeValue(res.getValue(), "mandatory"));
		bindingContext.bindValue(SWTObservables.observeSelection(bread),
				PojoObservables.observeValue(res.getValue(), "readOnly"));
		bindingContext.bindValue(SWTObservables.observeSelection(bvisible),
				PojoObservables.observeValue(res.getValue(), "visible"));
	}

	private ShiftMapProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ShiftMapProxy proxy = new ShiftMapProxy();

	class ShiftMapProxy {
		private ResourceDescriptor rd;
		private final int[] shift = new int[] { 1, 2, 3, 8, 6, 10, 4, 9, 7, 11 };

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setControlType(int type) {
			rd.setControlType((byte) shift[type]);
		}

		public int getControlType() {
			for (int i = 0; i < shift.length; i++)
				if (shift[i] == rd.getControlType())
					return i;
			return -1;
		}
	}
}
