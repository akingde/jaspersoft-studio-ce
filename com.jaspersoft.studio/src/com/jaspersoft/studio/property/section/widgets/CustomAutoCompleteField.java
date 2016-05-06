/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.swt.widgets.Control;

/**
 * A custom re-implementation of the original {@link AutoCompleteField} class.
 * <p>
 * 
 * Added some utility methods that allow to better control the field behavior and status.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CustomAutoCompleteField {

	private SimpleContentProposalProvider proposalProvider;
	private CustomContentProposalAdapter adapter;
	private boolean popupOpened;
	private boolean popupJustClosed;

	/**
	 * Construct an AutoComplete field on the specified control, whose
	 * completions are characterized by the specified array of Strings.
	 * 
	 * @param control
	 *            the control for which autocomplete is desired. May not be
	 *            <code>null</code>.
	 * @param controlContentAdapter
	 *            the <code>IControlContentAdapter</code> used to obtain and
	 *            update the control's contents. May not be <code>null</code>.
	 * @param proposals
	 *            the array of Strings representing valid content proposals for
	 *            the field.
	 */
	public CustomAutoCompleteField(final Control control,
			IControlContentAdapter controlContentAdapter, String[] proposals) {
		proposalProvider = new SimpleContentProposalProvider(proposals);
		proposalProvider.setFiltering(true);
		adapter = new CustomContentProposalAdapter(control, controlContentAdapter,
				proposalProvider, null, null);
		adapter.setPropagateKeys(true);
		adapter
				.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.addContentProposalListener(new IContentProposalListener2() {
			
			@Override
			public void proposalPopupOpened(ContentProposalAdapter adapter) {
				popupOpened=true;
			}
			
			@Override
			public void proposalPopupClosed(ContentProposalAdapter adapter) {
				popupOpened=false; 
				popupJustClosed=true;
			}
		});
	}

	/**
	 * @return <code>true</code> if the popup was closed recently, <code>false</code> otherwise
	 * 
	 * @see #resetPopupJustClosed() clients should take care of resetting the popup closed status
	 */
	public boolean isPopupJustClosed() {
		return popupJustClosed;
	}
	
	/**
	 * Resets to <code>false</code> the flag that indicates that the 
	 * proposal popup was just closed.
	 */
	public void resetPopupJustClosed(){
		popupJustClosed = false;
	}
	
	/**
	 * Set the Strings to be used as content proposals.
	 * 
	 * @param proposals
	 *            the array of Strings to be used as proposals.
	 */
	public void setProposals(String[] proposals) {
		proposalProvider.setProposals(proposals);
	}
	
	/**
	 * @return <code>true</code> if the proposal popup is opened, 
	 * 					<code>false</code> otherwise
	 */
	public boolean isProposalPopupOpen() {
		return popupOpened;
	}
	
	/**
	 * Forces the close of the proposal popup.
	 */
	public void closeProposalPopup() {
		adapter.closeProposalPopup();
	}
	
	/**
	 * Allows to set the boolean flag that determines whether the adapter is enabled.
	 * 
	 * @param enabled
	 *            <code>true</code> if the adapter is enabled and responding
	 *            to user input, <code>false</code> if it is ignoring user
	 *            input.
	 */
	public void setEnabled(boolean enabled) {
		adapter.setEnabled(enabled);
	}
	
	/*
	 * Custom content proposal adapter that allows to invoke the popup close.
	 */
	private class CustomContentProposalAdapter extends ContentProposalAdapter {

		public CustomContentProposalAdapter(Control control, IControlContentAdapter controlContentAdapter,
				IContentProposalProvider proposalProvider, KeyStroke keyStroke, char[] autoActivationCharacters) {
			super(control, controlContentAdapter, proposalProvider, keyStroke, autoActivationCharacters);
		}
		
		@Override
		protected void closeProposalPopup() {
			super.closeProposalPopup();
		}
	}
	
}
