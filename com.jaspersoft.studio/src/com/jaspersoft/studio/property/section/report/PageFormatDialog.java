package com.jaspersoft.studio.property.section.report;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.property.section.report.util.PageSize;
import com.jaspersoft.studio.property.section.report.util.UnitsWidget;
import com.jaspersoft.studio.property.section.report.util.ValueUnitsWidget;

final class PageFormatDialog extends FormDialog {

	PageFormatDialog(Shell shell) {
		super(shell);
		shell.setText("Page Format");
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText("Page Format");

		FormToolkit toolkit = mform.getToolkit();

		mform.getForm().getBody().setLayout(new GridLayout(2, false));

		createPageSize(mform, toolkit);

		createThumbnail(mform);

		createOrientation(mform, toolkit);

		createMargins(mform, toolkit);

		createColumns(mform, toolkit);
	}

	private void createColumns(IManagedForm mform, FormToolkit toolkit) {
		Group bright = new Group(mform.getForm().getBody(), SWT.NONE);
		bright.setText("Columns");
		bright.setBackground(mform.getForm().getBody().getBackground());
		bright.setLayoutData(new GridData(GridData.FILL_BOTH));
		bright.setLayout(new GridLayout(3, false));

		toolkit.createLabel(bright, "Columns");
		Spinner cols = new Spinner(bright, SWT.BORDER);
		cols.setValues(1, 0, Integer.MAX_VALUE, 0, 1, 10);
		cols.setToolTipText("Columns");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		cols.setLayoutData(gd);

		new ValueUnitsWidget().createComponent(bright, "Column Width", "Column Width");

		new ValueUnitsWidget().createComponent(bright, "Space", "Space between columns");
	}

	private void createMargins(IManagedForm mform, FormToolkit toolkit) {
		Group bleft = new Group(mform.getForm().getBody(), SWT.NONE);
		bleft.setText("Margins");
		bleft.setBackground(mform.getForm().getBody().getBackground());
		bleft.setLayoutData(new GridData(GridData.FILL_BOTH));
		bleft.setLayout(new GridLayout(3, false));

		new ValueUnitsWidget().createComponent(bleft, "Top", "Top margin");

		new ValueUnitsWidget().createComponent(bleft, "Bottom", "Bottom margin");

		new ValueUnitsWidget().createComponent(bleft, "Left", "Left margin");

		new ValueUnitsWidget().createComponent(bleft, "Right", "Right margin");
	}

	private void createOrientation(IManagedForm mform, FormToolkit toolkit) {
		Group mleft = new Group(mform.getForm().getBody(), SWT.NONE);
		mleft.setText("Page Orientation");
		mleft.setBackground(mform.getForm().getBody().getBackground());
		mleft.setLayoutData(new GridData(GridData.FILL_BOTH));
		mleft.setLayout(new GridLayout());
		toolkit.createButton(mleft, "Portrait", SWT.RADIO);
		toolkit.createButton(mleft, "Landscape", SWT.RADIO);
	}

	private void createThumbnail(IManagedForm mform) {
		Composite tright = new Composite(mform.getForm().getBody(), SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		tright.setLayoutData(gd);
		tright.setBackground(mform.getForm().getBody().getBackground());

	}

	private void createPageSize(IManagedForm mform, FormToolkit toolkit) {
		Composite tleft = new Composite(mform.getForm().getBody(), SWT.NONE);
		tleft.setLayout(new GridLayout(3, false));
		tleft.setBackground(mform.getForm().getBody().getBackground());
		tleft.setLayoutData(new GridData(GridData.FILL_BOTH));

		toolkit.createLabel(tleft, "Format");
		CCombo fc = new CCombo(tleft, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		fc.setItems(PageSize.getFormats());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		fc.setLayoutData(gd);

		new ValueUnitsWidget().createComponent(tleft, "Width", "Page Width");

		new ValueUnitsWidget().createComponent(tleft, "Height", "Page Height");

		new UnitsWidget().createComponent(tleft, "Units", "Units", 2);
	}

}