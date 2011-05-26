package com.jaspersoft.studio.compatibility.dialog;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.preferences.StudioPreferencePage;

public class VersionDialog extends Dialog {

	public VersionDialog(Shell parent, int style, String version) {
		super(parent, style);
		this.version = version;
	}

	public VersionDialog(Shell parent, String version) {
		super(parent);
		this.version = version;
	}

	private String[][] versions = JRXmlWriterHelper.getVersions();

	private String[] getItems() {
		String[] r = new String[versions.length];
		for (int i = 0; i < versions.length; i++)
			r[i] = versions[i][0];
		return r;
	}

	private int getVersionIndex() {
		for (int i = 0; i < versions.length; i++)
			if (versions[i][1].equals(version))
				return i;
		return 0;
	}

	private String version;

	public String open(final IProject project) {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("JRXML Version");

		shell.setLayout(new GridLayout(2, false));
		Label label = new Label(shell, SWT.WRAP);
		label.setText("Use the latest JasperReports version to be sure all formatting and content is saved correctly.\n\n"
				+ "Warning! If you don't use the last version, you risk to lose part of the report content.\n ");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		label = new Label(shell, SWT.NONE);
		label.setText("Version");

		final Combo c = new Combo(shell, SWT.SINGLE | SWT.READ_ONLY);
		c.setItems(getItems());
		c.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int ind = c.getSelectionIndex();
				if (ind >= 0 && ind < versions.length)
					version = versions[ind][1];
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		c.select(getVersionIndex());

		final Button b = new Button(shell, SWT.CHECK);
		b.setText("Don't ask me again");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ScopedPreferenceStore overlayStore = new ScopedPreferenceStore(new ProjectScope(project),
						JaspersoftStudioPlugin.getUniqueIdentifier());
				overlayStore.putValue(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, Boolean.toString(b.getSelection()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("OK");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		gd.widthHint = 100;
		buttonOK.setLayoutData(gd);
		buttonOK.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return version;
	}

}
