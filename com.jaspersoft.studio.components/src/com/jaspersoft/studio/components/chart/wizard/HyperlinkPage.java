/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard;

import java.text.MessageFormat;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Hyperlink dialog used to edit\remove the content of an MHyperlink element
 * 
 * @author Orlandin Marco
 *
 */
public class HyperlinkPage extends FormDialog {

	/**
	 * Container of all the controls used to edit the hyperlink
	 */
	private HyperLinkPanel controlPanel;
	
	/**
	 * Flag used to know if the current series has an hyperling, in this 
	 * way it is possible to show or hide the delete button, when it is or
	 * not necessary
	 */
	private boolean hasHyperlink = true;
	
	/**
	 * The name of the edited series
	 */
	private String seriesName;
	
	/** 
	 * @param shell
	 * @param hyperLinkNode Hyperlink node to edit, must be an MHyperlink
	 * @param seriesName The name of the edited series
	 * @param hasHyperlink Flag used to know if the current series has an hyperling, in this 
	 * way it is possible to show or hide the delete button, when it is or
	 * not necessary
	 */
	public HyperlinkPage(Shell shell, APropertyNode hyperLinkNode, String seriesName, boolean hasHyperlink) {
		super(shell);
		controlPanel = new HyperLinkPanel(hyperLinkNode);
		this.seriesName = seriesName;
		this.hasHyperlink = hasHyperlink;
	}

	/**
	 * Create an additional delete button after ok and cancel, that can be used to request
	 * the deletion of the hyperlink. The delete button is created only if the current series has an
	 *  hpyerlink to delete. Before to delete the hyperlink ask to the user a confirmation
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		if (hasHyperlink){
			Button deleteButton = createButton(parent, IDialogConstants.ABORT_ID, Messages.HyperlinkDialog_deleteHyperlinkAction, false);
			deleteButton.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e) {
					 if (UIUtils.showConfirmation(Messages.HyperlinkPage_deleteTitle, getDeleteMessage())){
						 setReturnCode(IDialogConstants.ABORT_ID);
						close();
					 }
				}
			});
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.HyperlinkDialog_hyperlinkDialogName);
	}
	
	/**
	 * Set the title and create the controls
	 */
	@Override
	protected void createFormContent(IManagedForm mform) {
		super.createFormContent(mform);
		mform.getForm().setText(getDialogTitle());
		mform.getForm().getBody().setLayout(new GridLayout(1,false));
		controlPanel.createControls(mform.getForm().getBody());
		PlatformUI.getWorkbench().getHelpSystem().setHelp(mform.getForm().getBody(),
															"com.jaspersoft.studio.doc.createHyperlinkSection"); //$NON-NLS-1$
	}
	
	/**
	 * When ok is pressed all the values defined inside the widget are set in 
	 * the edited MHyperlink also
	 */
	@Override
	protected void okPressed() {
		controlPanel.setAllExpressionValues();
		super.okPressed();
	}
	
	/**
	 * Return the edited Mhyperlink
	 *
	 */
	public APropertyNode getElement(){
		return controlPanel.getElement();
	}

	/**
	 * Return the message for a confirmation request done
	 * when the user press the delete button
	 *
	 * @return a not null string
	 */
	protected String getDeleteMessage(){
		return MessageFormat.format(Messages.HyperlinkPage_deleteDescription, new Object[]{seriesName});
	}
	
	/**
	 * Return the dialog title
	 * 
	 * @return a not null string
	 */
	protected String getDialogTitle(){
		return MessageFormat.format(Messages.HyperlinkDialog_hyperlinkDialogTitle, seriesName);
	}
}
