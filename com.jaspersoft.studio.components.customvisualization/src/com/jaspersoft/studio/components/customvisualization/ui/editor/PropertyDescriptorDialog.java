/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;

public class PropertyDescriptorDialog extends ATitledDialog {
	private ItemPropertyDescription<?> descriptor;
	private Text tname;
	private Text tlabel;
	private Text tdesc;
	private Button bMandatory;
	private Combo ctype;
	private String[] types = new String[] { "Text", "Float", "Integer",
			"Double", "Combo", "Color" };

	protected PropertyDescriptorDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Property");
		setDefaultSize(400, 400);
	}

	public ItemPropertyDescription<?> getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(ItemPropertyDescription<?> descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public boolean close() {
		createDescriptor();
		return super.close();
	}

	private void createDescriptor() {
		switch (ctype.getSelectionIndex()) {
		case 0:
			descriptor = new TextPropertyDescription<String>(tname.getText(),
					tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection());
			break;
		case 1:
			descriptor = new TextPropertyDescription<Float>(tname.getText(),
					tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection());
			break;
		case 2:
			descriptor = new TextPropertyDescription<Integer>(tname.getText(),
					tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection());
			break;
		case 3:
			descriptor = new TextPropertyDescription<Double>(tname.getText(),
					tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection());
			break;
		case 4:
			descriptor = new ComboItemPropertyDescription<String>(
					tname.getText(), tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection(), new String[] {});
			break;
		case 5:
			descriptor = new ColorPropertyDescription<String>(tname.getText(),
					tlabel.getText(), tdesc.getText(),
					bMandatory.getSelection());
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent = (Composite) super.createDialogArea(parent);
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(cmp, SWT.NONE).setText("Name");
		tname = new Text(cmp, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText("Label");
		tlabel = new Text(cmp, SWT.BORDER);
		tlabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText("Description");
		tdesc = new Text(cmp, SWT.BORDER);
		tdesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bMandatory = new Button(cmp, SWT.CHECK);
		bMandatory.setText("Mandatory");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		bMandatory.setLayoutData(gd);

		new Label(cmp, SWT.NONE).setText("Type");
		ctype = new Combo(cmp, SWT.READ_ONLY);
		ctype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ctype.setItems(types);
		ctype.select(0);

		fillDescriptor();

		return parent;
	}

	private void fillDescriptor() {
		if (descriptor == null)
			return;
		tname.setText(Misc.nvl(descriptor.getName()));
		tlabel.setText(Misc.nvl(descriptor.getLabel()));
		tdesc.setText(Misc.nvl(descriptor.getDescription()));
		bMandatory.setSelection(descriptor.isMandatory());
		if (descriptor instanceof TextPropertyDescription)
			ctype.select(0);
		else if (descriptor instanceof ColorPropertyDescription<?>)
			ctype.select(5);
		else if (descriptor instanceof ComboItemPropertyDescription<?>)
			ctype.select(4);
	}
}
