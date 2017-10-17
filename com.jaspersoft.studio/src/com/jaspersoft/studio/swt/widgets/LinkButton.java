/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.internal.forms.widgets.FormTextModel;
import org.eclipse.ui.internal.forms.widgets.Paragraph;

/**
 * A widget that can be used to create a clickable link. The link can be formatted
 * or not and selection listeners can be added to do actions when the link is clicked.
 * The size of the swt element is calculated to contains the largest paragraph of the link in 
 * width and to contains all the paragraph in height
 * 
 * @author Orlandin Marco
 *
 */
@SuppressWarnings("restriction")
public class LinkButton extends Composite {

	/**
	 * Widget where the link is shown
	 */
	private FormText editQueryLink = null;

	/**
	 * The text of the longest paragraph
	 */
	private String unformattedText;

	/**
	 * Selection listeners called when the link is clicked
	 */
	private List<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();
	
	/**
	 * Create the widget
	 * 
	 * @param parent parent of the widget
	 * @param text the text to show. Can be formatted like defined for the {@link FormText} links,
	 * or simply plain text. In case of plain text all the text will be clickable
	 */
	public LinkButton(Composite parent, String text) {
		super(parent, SWT.NONE);
		createLink(this, text);
	}

	/**
	 * Calculate the width in chars of the longest paragraph
	 * 
	 * @return the width of the longest paragraph
	 */
  private int getWidthInChars() {
    GC gc = null;
    if (editQueryLink != null) {
    	gc = new GC(editQueryLink);
    } else {
    	gc = new GC(this);
    }
    Point extent = gc.textExtent(unformattedText);//$NON-NLS-1$
    gc.dispose();
    return extent.x;
  }
  
  /**
   * Return the textual content of the longest paragraph
   * 
   * @param model the model that contains all the paragraphs
   * @return a not null string that is the longest paragraph
   */
	private String getLongerParagraph(FormTextModel model){
  	String currentLongest = "";
  	for(Paragraph paragraph : model.getParagraphs()){
  		String text = paragraph.getAccessibleText();
  		if (text.length() > currentLongest.length()){
  			currentLongest = text;
  		}
  	}
  	return currentLongest;
  }
	
	/**
	 * The size of the swt element is calculated to contains the largest paragraph of the link in 
	 * width and to contains all the paragraph in height
	 */
	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		Point size = super.computeSize(wHint, hHint, changed);
		size.x = getWidthInChars() + 10;
		return editQueryLink.computeSize(size.x, SWT.DEFAULT);
	}
	
	/**
	 * Build the link
	 * 
	 * @param parent composite where the link is placed
	 * @param linkText text on the link
	 */
	protected void createLink(Composite parent, String linkText) {
		linkText = linkText.trim();
		if (!linkText.startsWith("<form>")){
			unformattedText = new String(linkText);
			StringBuilder formattedText = new StringBuilder();
			formattedText.append("<form><p><a href=\"\">");
			formattedText.append(linkText);
			formattedText.append("</a></p></form>");
			linkText = formattedText.toString();
		} else {
			FormTextModel formTextModel = new FormTextModel();
			formTextModel.parseTaggedText(linkText, false);
			unformattedText = getLongerParagraph(formTextModel);
		}

		GridLayout containerLayout = new GridLayout(1, false);
		containerLayout.horizontalSpacing = 0;
		containerLayout.verticalSpacing = 0;
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		super.setLayout(containerLayout);
	
		editQueryLink = new FormText(this, SWT.NONE);
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
				LinkButton.this.forceFocus();
				Event baseEvent = new Event();
				baseEvent.widget = LinkButton.this;
				baseEvent.data = e;
				SelectionEvent selectionEvent = new SelectionEvent(baseEvent);
				for(SelectionListener listener : selectionListeners){
					listener.widgetSelected(selectionEvent);
				}
				editQueryLink.redraw();
			}
		});
		
		//Avoid the link selection effect when clicking on the plain text area
		editQueryLink.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDown(MouseEvent e) {
				LinkButton.this.forceFocus();
				editQueryLink.redraw();
			}
			
		});
			
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = getWidthInChars();
		editQueryLink.setLayoutData(data);
	}

	/**
	 * Set the text of the link 
	 * 
	 * @param text The text shown on the link, can be formatted (as specified for {@link FormText}) 
	 * or simply plain text. In case of plain text all the text will be clickable.
	 */
	public void setText(String text){
		text = text.trim();
		if (!text.startsWith("<form>")){
			unformattedText = new String(text);
			StringBuilder formattedText = new StringBuilder();
			formattedText.append("<form><p><a href=\"\">");
			formattedText.append(text);
			formattedText.append("</a></p></form>");
			text = formattedText.toString();
		} else {
			FormTextModel formTextModel = new FormTextModel();
			formTextModel.parseTaggedText(text, false);
			unformattedText = getLongerParagraph(formTextModel);
		}
		editQueryLink.setText(text, true, false);
	}
	
	/**
	 * Add a selection listener called when the link is clicked.
	 * 
	 * @param listener a selection listener, to be called must be not null
	 * and not added before
	 */
	public void addSelectionListener(SelectionListener listener){
		if (listener != null && !selectionListeners.contains(listener)){
			selectionListeners.add(listener);
		}
	}
	
	@Override
	public void setToolTipText(String string) {
		super.setToolTipText(string);
		editQueryLink.setToolTipText(string);
	}
	
	@Override
	public void setLayout(Layout layout) {
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		editQueryLink.setEnabled(enabled);
	}
}
