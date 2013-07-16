package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.ui.validator.NotEmptyFileValidator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.messages.Messages;

public abstract class AFileDataAdapterComposite extends ADataAdapterComposite {
	protected Text textFileName;

	public AFileDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
	}

	protected void createFileNameWidgets(Composite parent) {
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText(Messages.XLSXDataAdapterComposite_0);

		textFileName = new Text(parent, SWT.BORDER);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd.horizontalIndent = 8;
		textFileName.setLayoutData(gd);

		Button btnBrowse = new Button(parent, SWT.NONE);
		GridData gd_btnBrowse = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnBrowse.widthHint = 100;
		btnBrowse.setLayoutData(gd_btnBrowse);
		btnBrowse.setText(Messages.XLSXDataAdapterComposite_1);

		/*
		 * UI ELEMENTS LISTENERS
		 */
		// browse and select the Excel file
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault().getActiveShell());
				fd.setFileName(textFileName.getText());
				fd.setFilterExtensions(getFileExtensions()); //$NON-NLS-1$ //$NON-NLS-2$
				String selection = fd.open();
				if (selection != null)
					textFileName.setText(selection);
			}
		});
	}

	protected abstract String[] getFileExtensions();

	protected void doBindFileNameWidget(DataAdapter dataAdapter) {
		Binding binding = bindingContext.bindValue(SWTObservables.observeText(textFileName, SWT.Modify), PojoObservables.observeValue(dataAdapter, "fileName"), //$NON-NLS-1$
				new UpdateValueStrategy().setAfterConvertValidator(new NotEmptyFileValidator()), null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT, null, new ControlDecorationUpdater());
	}
}
