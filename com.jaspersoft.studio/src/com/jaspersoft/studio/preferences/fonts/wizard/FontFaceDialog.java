package com.jaspersoft.studio.preferences.fonts.wizard;

import net.sf.jasperreports.engine.fonts.SimpleFontFace;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;

public class FontFaceDialog extends Dialog {

	private SimpleFontFace fontFace;

	protected FontFaceDialog(Shell parentShell, SimpleFontFace fontFace) {
		super(parentShell);
		this.fontFace = fontFace;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.FontFaceDialog_dialog_title);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(3, false));

		Text txt = createFileField(cmp, "TrueType", "ttf"); //$NON-NLS-1$ //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getTtf()));
		txt.setToolTipText(Misc.nvl(fontFace.getTtf()));
		txt = createFileField(cmp, "Embedded OpenType", "eof"); //$NON-NLS-1$ //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getEot()));
		txt.setToolTipText(Misc.nvl(fontFace.getEot()));
		txt = createFileField(cmp, "Scalable Vector Graphics", "svg"); //$NON-NLS-1$ //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getSvg()));
		txt.setToolTipText(Misc.nvl(fontFace.getSvg()));
		txt = createFileField(cmp, "Web Open Font Format", "woff"); //$NON-NLS-1$ //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getWoff()));
		txt.setToolTipText(Misc.nvl(fontFace.getWoff()));
		return cmp;
	}

	private Text createFileField(Composite composite, String name, final String type) {
		new Label(composite, SWT.NONE).setText(name + " (." + type + ")"); //$NON-NLS-1$ //$NON-NLS-2$

		final Text txt = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		txt.setLayoutData(gd);

		Button button = new Button(composite, SWT.PUSH);
		button.setText(Messages.FontFamilyPage_browseButton);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
				fd.setText(Messages.FontFamilyPage_browseDialogTitle);
				fd.setFilterExtensions(new String[] { "*." + type + ";*." + type.toUpperCase() }); //$NON-NLS-1$ //$NON-NLS-2$  
				String selected = fd.open();
				if (selected != null) {
					if (type.equals("ttf"))
						fontFace.setTtf(selected);
					else if (type.equals("eof"))
						fontFace.setEot(selected);
					else if (type.equals("svg"))
						fontFace.setSvg(selected);
					else if (type.equals("woff"))
						fontFace.setWoff(selected);
				}
				txt.setText(Misc.nvl(selected));
				txt.setToolTipText(Misc.nvl(selected));
			}
		});
		return txt;
	}

}
