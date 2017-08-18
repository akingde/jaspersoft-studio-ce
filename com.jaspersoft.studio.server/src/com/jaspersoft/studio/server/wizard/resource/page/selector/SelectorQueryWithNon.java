/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;

public class SelectorQueryWithNon extends SelectorQuery {
	private Button brNon;

	@Override
	protected void createLocal(Composite parent) {
		super.createLocal(parent);

		brNon = new Button(parent, SWT.RADIO);
		brNon.setText(Messages.SelectorQueryWithNon_0);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brNon.setLayoutData(gd);
		brNon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceDescriptor rd = getResourceDescriptor(res.getValue());
				if (rd != null)
					res.getValue().getChildren().remove(rd);
				setEnabled(2);
				firePageComplete();
			}
		});
	}

	@Override
	protected void init() {
		ResourceDescriptor rd = getResourceDescriptor(resRD);
		if (rd == null)
			setEnabled(2);
		else
			setEnabled(isReference(rd) ? 0 : 1);
	}

	@Override
	public void setEnabled(int pos) {
		brNon.setSelection(false);
		super.setEnabled(pos);
		if (pos == 2 && brNon != null && !bRef.getSelection() && !bLoc.getSelection())
			brNon.setSelection(true);
	}

	@Override
	public boolean isPageComplete() {
		if (brRepo.getSelection())
			return valid;
		return true;
	}

	public void setResource(ANode parent, AMResource res) {
		this.res = res;
		this.resRD = res.getValue();
		this.parent = parent;
		init();
	}
}
