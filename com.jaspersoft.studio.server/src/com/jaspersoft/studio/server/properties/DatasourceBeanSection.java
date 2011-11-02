package com.jaspersoft.studio.server.properties;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.property.section.AbstractSection;

public class DatasourceBeanSection extends ASection {
	private Text tname;
	private Text tmethod;

	@Override
	protected void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		AbstractSection.createLabel(parent, getWidgetFactory(), "Bean Name",
				120);

		tname = getWidgetFactory().createText(parent, "", SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		AbstractSection.createLabel(parent, getWidgetFactory(), "Bean Method",
				120);

		tmethod = getWidgetFactory().createText(parent, "", SWT.BORDER);
		tmethod.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	public void enableFields(boolean enable) {
		tname.setEditable(enable);
		tmethod.setEditable(enable);
	}

	@Override
	protected void bind() {
		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "beanName"));
		bindingContext.bindValue(
				SWTObservables.observeText(tmethod, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "beanMethod"));
	}

}
