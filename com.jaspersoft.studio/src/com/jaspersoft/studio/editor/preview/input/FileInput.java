/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.io.File;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.editor.preview.view.control.VParameters;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;

public class FileInput extends ADataInput {
	private Text txt;
	private Button btn;

	public boolean isForType(Class<?> valueClass) {
		return File.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (isForType(param.getValueClass())) {
			final Composite cmp = new Composite(parent, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			cmp.setLayoutData(gd);
			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			cmp.setLayout(layout);

			txt = new Text(cmp, SWT.BORDER);
			txt.setToolTipText(VParameters.createToolTip(param));
			txt.addFocusListener(focusListener);
			txt.addTraverseListener(keyListener);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			txt.setLayoutData(gd);
			setMandatory(param, txt);

			ModifyListener listener = new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					if (!isRefresh) {
						hideError(txt);
						if (param.getValueClass().equals(File.class))
							try {
								File f = new File(txt.getText());
								if (!f.exists())
									setError(txt, "File does not exists");
								updateModel(f);
							} catch (Exception e1) {
								setError(txt, e1.getMessage());
							}
					}
				}
			};
			txt.addModifyListener(listener);

			btn = new Button(cmp, SWT.PUSH);
			btn.setText(Messages.FileInput_selectfile);
			btn.setToolTipText(param.getDescription());
			btn.addFocusListener(focusListener);
			btn.addTraverseListener(keyListener);
			btn.setAlignment(SWT.LEFT);
			btn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(
							Display.getCurrent().getActiveShell(), false, ResourcesPlugin.getWorkspace().getRoot(),
							IResource.FILE);
					if (fd.open() == Dialog.OK) {
						IFile file = (IFile) fd.getFirstResult();
						txt.setText(Misc.nvl(file.getProjectRelativePath().toOSString()));
						updateModel(new File(file.getLocationURI()));
					}
				}

			});
			updateInput();
			setNullable(param, btn);
			setNullable(param, txt);
		}
	}

	private boolean isRefresh = false;

	public void updateInput() {
		Object value = params.get(param.getName());
		isRefresh = true;
		if (value != null && value instanceof String)
			txt.setText((String) value);
		else
			txt.setText(value == null ? "" : value.toString());
		isRefresh = false;

		if (value != null && value instanceof File)
			btn.setToolTipText(((File) value).getAbsolutePath());
		setDecoratorNullable(param);
	}

}
