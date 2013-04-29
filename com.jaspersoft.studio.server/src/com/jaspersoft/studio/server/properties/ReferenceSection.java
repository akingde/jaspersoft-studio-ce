/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.properties;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;

public class ReferenceSection extends ASection {

	private Text trefuri;
	private Button bbrowse;

	@Override
	protected void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {

		AbstractSection.createLabel(parent, getWidgetFactory(),
				"Referenced Descriptor", -1);

		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setBackground(parent.getBackground());

		trefuri = getWidgetFactory().createText(cmp, "",
				SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bbrowse = new Button(cmp, SWT.PUSH);
		bbrowse.setText("...");
		bbrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				RepositoryDialog rd = new RepositoryDialog(shell, 
						ServerManager.getMServerProfileCopy((MServerProfile)res.getRoot())) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return !(r instanceof MFolder);
					}

				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						res.getValue().setReferenceUri(
								rs.getValue().getUriString());
						bindingContext.updateTargets();
					}
				}
			}

		});
	}

	@Override
	public void enableFields(boolean enable) {
		trefuri.setEditable(enable);
		bbrowse.setEnabled(enable);
	}

	@Override
	protected void bind() {
		bindingContext.bindValue(SWTObservables.observeText(trefuri, SWT.NONE),
				PojoObservables.observeValue(res.getValue(), "referenceUri"));
	}

}
