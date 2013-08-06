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
 ******************************************************************************/
package com.jaspersoft.studio.property.infoList;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class SelectableComposite extends ScrolledComposite {

	private List<ElementDescription> items;
	
	private Composite selectedComposite;
	
	private Color unselectedColor = new Color(null, new RGB(255,255,225));
	
	private Color selectedColor = new Color(null, new RGB(51,153,255));
	
	private Font boldFont = new Font(UIUtils.getDisplay(),"Arial", 10, SWT.BOLD );
	
	private SelectionAdapter mouseDoubleClick;

	private MouseListener compositeMouseAction = new MouseListener() {
		
		@Override
		public void mouseUp(MouseEvent e) {}
		
		@Override
		public void mouseDown(MouseEvent e) {
			widgetSelected(e.getSource());
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			widgetSelected(e.getSource());
			if (mouseDoubleClick != null){
				Event baseEvent = new Event();
				baseEvent.data = selectedComposite.getData();
				baseEvent.item = selectedComposite;
				baseEvent.widget = e.widget;
				SelectionEvent event = new SelectionEvent(baseEvent);
				mouseDoubleClick.widgetSelected(event);
			} else widgetSelected(((Control)e.getSource()).getParent());
			setScrolledFocus();
		}
	};
	
	private void widgetSelected(Object source) {
		if (source instanceof Composite){
			if (source != selectedComposite){
				if (selectedComposite != null){
					selectedComposite.setBackground(unselectedColor);
					setChildrenColor(selectedComposite, unselectedColor);
				}
				selectedComposite = (Composite)source;
				selectedComposite.setBackground(selectedColor);
				setChildrenColor(selectedComposite, selectedColor);
				setScrolledFocus();
			}
		} else widgetSelected(((Control)source).getParent());
	}
	
	public SelectableComposite(Composite parent, List<ElementDescription> items) {
		super(parent,  SWT.V_SCROLL);
		this.items = items;
		setLayout(new GridLayout(1, false));
		selectedComposite = null;
		mouseDoubleClick = null;
		this.getVerticalBar().setIncrement(5);
		createItems();
	}
	
	private void setScrolledFocus(){
		this.forceFocus();
	}
	
	private void setChildrenColor(Composite parent, Color color){
		for(Control child : parent.getChildren()){
			child.setBackground(color);
		}
	}
	
	public void SetDoubleClickListener(SelectionAdapter listener){
		this.mouseDoubleClick = listener;
	}
	

	
	public ElementDescription getSelectedElement(){
		if (selectedComposite != null) return (ElementDescription)selectedComposite.getData();
		return null;
	}
	
	private void createItems(){
		final Composite mainComposite = new Composite(this, SWT.NONE);
		GridLayout mainCompLayout = new GridLayout(1,false);
		mainCompLayout.verticalSpacing = 0;
		mainCompLayout.horizontalSpacing = 0;
		mainCompLayout.marginWidth = 0;
		mainCompLayout.marginHeight = 0;
		mainComposite.setLayout(mainCompLayout);
		setContent(mainComposite);
		for(ElementDescription item : items){
			Composite comp = new Composite(mainComposite, SWT.BORDER);
			comp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			comp.setLayout(new GridLayout(1,false));
			comp.setData(item);
			comp.setBackground(unselectedColor);
			
			Label titleLabel = new Label(comp,SWT.NONE);
			titleLabel.setFont(boldFont);
			titleLabel.setText(item.getName());
			titleLabel.addMouseListener(compositeMouseAction);
			
			createDescription(item.getDescription(), comp);
			
			comp.addMouseListener(compositeMouseAction);
			setChildrenColor(comp, unselectedColor);
		}
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				Point size = getSize();
				mainComposite.setSize(mainComposite.computeSize(size.x, SWT.DEFAULT));
			}

		});
		
	}
	
	private void createDescription(String text, Composite comp){
		final StyledText descLabel = new StyledText(comp,SWT.MULTI);
		descLabel.setEditable(false);
		descLabel.setText(text);
		descLabel.addMouseListener(compositeMouseAction);
		descLabel.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				setScrolledFocus();
				descLabel.setSelection(0, 0);
			}
			
		});
	}

	@Override
	public void dispose() {
		super.dispose();
		selectedColor.dispose();
		unselectedColor.dispose();
		boldFont.dispose();
	}
	
}
