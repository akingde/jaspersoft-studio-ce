package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NameComposite extends ADataAdapterComposite {
	private DataAdapterDescriptor dataAdapterDesc = null;
	private Text textName;

	public NameComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");

		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(textName, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "name"));
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		return dataAdapterDesc;
	}

}
