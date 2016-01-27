/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.SimpleJRXMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * A button that when clicked open the edit query dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPQueryLink<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	private FormText editQueryLink = null;

	/**
	 * The query of the report
	 */
	private MQuery mquery;

	/**
	 * The main dataset of the report
	 */
	private MDataset mdataset;
	
	private Composite container;
	
	private String unformattedText;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param linkText
	 *          text on the button
	 */
	public SPQueryLink(Composite parent, AbstractSection section, T pDescriptor, String linkText) {
		super(parent, section, pDescriptor);
		createLink(parent, linkText);
	}

	@Override
	protected void createComponent(Composite parent) {
	}

  private int getWidthInChars() {
    GC gc = null;
    if (editQueryLink != null) {
    	gc = new GC(editQueryLink);
    } else {
    	gc = new GC(container);
    }
    Point extent = gc.textExtent(unformattedText);//$NON-NLS-1$
    gc.dispose();
    return extent.x;
  }
	
	/**
	 * Build the button
	 * 
	 * @param parent
	 *          composite where is placed
	 * @param buttonText
	 *          text on the button
	 */
	protected void createLink(Composite parent, String linkText) {
		unformattedText = new String(linkText);
		if (!linkText.contains("<form>")){
			linkText = "<form><p><a href=\"\">" + linkText + "</a></p></form>"; 
		}
		container = new Composite(parent, SWT.NONE){
			@Override
			public Point computeSize(int wHint, int hHint, boolean changed) {
				Point size = super.computeSize(wHint, hHint, changed);
				size.x = getWidthInChars() + 10;
				return size;
			}
			
		};
		GridLayout containerLayout = new GridLayout(1, false);
		containerLayout.horizontalSpacing = 0;
		containerLayout.verticalSpacing = 0;
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		container.setLayout(containerLayout);
	
		editQueryLink = new FormText(container, SWT.NONE);
		editQueryLink.setText(linkText, true, false);
		editQueryLink.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				event.doit = false;
			}
		});
		
		editQueryLink.setWhitespaceNormalized(true);
		editQueryLink.addHyperlinkListener(new HyperlinkAdapter() {

			@Override
			public void linkActivated(HyperlinkEvent e) {
				container.forceFocus();
				if (SelectionHelper.getActiveJRXMLEditor() instanceof SimpleJRXMLEditor){
					UIUtils.showInformation(Messages.SPQueryButton_editorNotSuppoertedMessage);
				} else {
					PatternEditor wizard = new PatternEditor();
					Object queryText = mquery.getPropertyValue(JRDesignQuery.PROPERTY_TEXT);
					wizard.setValue(queryText != null ? queryText.toString() : ""); //$NON-NLS-1$
					new DatasetDialog(editQueryLink.getShell(), mdataset, mquery.getJasperConfiguration(), section
							.getEditDomain().getCommandStack()).open();
				}
				editQueryLink.redraw();
			}
		});
			
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = getWidthInChars();
		editQueryLink.setLayoutData(data);
		container.layout();
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		if (pnode instanceof MDataset)
			mdataset = (MDataset) pnode;
		else if (pnode instanceof MReport)
			mdataset = (MDataset) pnode.getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
		mquery = (MQuery) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
	}

	@Override
	public Control getControl() {
		return container;
	}

}
