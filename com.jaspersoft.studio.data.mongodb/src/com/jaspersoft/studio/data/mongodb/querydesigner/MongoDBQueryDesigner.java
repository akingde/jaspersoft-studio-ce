/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb.querydesigner;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.QueryDesigner;

/**
 * Simple query designer for HQL (hibernate) that provides syntax highlighting.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MongoDBQueryDesigner extends QueryDesigner {
	/* Main control of the designer */
	protected Composite control;
	/* Text area where enter the query */
	protected StyledText queryTextArea;
	private MongoDBLineStyler lineStyler = new MongoDBLineStyler();

	public Control getControl() {
		return control;
	}

	public Control createControl(Composite parent) {
		control = new Composite(parent, SWT.NONE);
		GridLayout controlGl = new GridLayout(1, true);
		controlGl.marginWidth = 0;
		controlGl.marginHeight = 0;
		control.setLayout(controlGl);

		queryTextArea = new StyledText(control, SWT.BORDER);
		queryTextArea.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				queryTextAreaModified();
			}
		});
		queryTextArea.addLineStyleListener(lineStyler);
		queryTextArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		return control;
	}

	protected void queryTextAreaModified() {
		// keep the query info updated
		((JRDesignQuery) jDataset.getQuery()).setText(queryTextArea.getText());
	}

	@Override
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		super.setQuery(jDesign, jDataset);
		queryTextArea.setText(jDataset.getQuery().getText());
	}

}
