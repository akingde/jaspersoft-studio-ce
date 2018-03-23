/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.jaspersoft.studio.messages.Messages;

/**
 * Special Combo that allow to define a read only combo without using
 * the style bit. This was done because a standard read only combo has
 * some limitation, for example it doesn't allow to set the background or
 * set the text inside it if the text is not in the elements set. So
 * this combo will use a standard combo with the listener to make it read only.
 * It offers also the method to place the combo in error state, this will change
 * the background and the tooltip to help the user identify the error. This
 * can also be used as a simply combo
 * 
 * @author Orlandin Marco
 *
 */
public class CustomReadOnlyCombo extends Combo{

		/**
		 * Flag to know if the combo is in error state
		 */
		private boolean hasError = false;
		
		/**
		 * Message when the combo is in error state
		 */
		private String errorTooltip = Messages.ErrorCombo_errorMessage;
		
		/**
		 * The original tooltip of the combo
		 */
		private String originalTooltip = null;
		
		/**
		 * The combo original background
		 */
		private Color originalBackground;
		
		/**
		 * Flag to know if the combo is read only 
		 */
		private boolean isReadOnly = false;
		
		/**
		 * The verify listener doesn't allow to provide values
		 * (by typing or copy/pasting) values outside the items set
		 */
		private VerifyListener verifyListener = new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				if (isReadOnly) {
					if  (e.character == SWT.DEL) {
						setText("");
					} else {
						for(String item : getItems()) {
							if (item.equals(e.text)) {
								e.doit = true;
								return;
							}
						}
					}
					e.doit = false;
				}
			}
		};
		
		/**
		 * Create the combo, by default it is not read only
		 * 
		 * @param parent the parent of the combo
		 */
		public CustomReadOnlyCombo(Composite parent) {
			super(parent, SWT.NONE);
			//this listener avoid to scroll the content with the mouse wheel
			//when the control is not focused (this happen on windows)
			addListener(SWT.MouseVerticalWheel, new Listener()
			{
				@Override
		        public void handleEvent(Event arg0)
		        {
		            if (!isFocusControl()) {
		            	arg0.doit = false;
		            }

		        }
			});
			//this listener block to type in the combo if it is in read only state
			addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					if (isReadOnly) e.doit = false;
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					//allow only the traverse keyboard event, to change value with arrow
					//and mouse wheel
					if (isReadOnly && !(e.keyCode !=16777218 || e.keyCode !=16777217)) {
						e.doit = false;
					}
				}
			});
			addVerifyListener(verifyListener);
			//this listener force the cursor to the first position if the combo
			//is read only 
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					if (isReadOnly) setSelection(new Point(0, 0));
					
				}
			});
		}
		
		/**
		 * Set the text inside the combo, this skip any verification
		 * of the value, so using this method you can write inside it 
		 * a value that is not in the values set
		 */
		@Override
		public void setText(String string) {	
			removeVerifyListener(verifyListener);
			super.setText(string);
			addVerifyListener(verifyListener);
		}
		
		/**
		 * Doses't trigger the verify listener when selecting
		 */
		@Override
		public void select(int index) {
			removeVerifyListener(verifyListener);
			super.select(index);
			addVerifyListener(verifyListener);
		}
		
		/**
		 * Doses't trigger the verify listener when setting the items
		 */
		@Override
		public void setItems(String... items) {
			removeVerifyListener(verifyListener);
			super.setItems(items);
			addVerifyListener(verifyListener);
		}
		
		/**
		 * Set or remove the combo from the error state.
		 * The error state change the background and the tooltip of
		 * the combo
		 * 
		 * @param value true if the combo is in error state, false otherwise
		 */
		public void setError(boolean value) {
			if (value != hasError) {
				hasError = value;
				if (value) {
					if (errorTooltip != null) {
						super.setToolTipText(errorTooltip);
					}
					super.setBackground(ColorConstants.red);
				} else {
					super.setToolTipText(originalTooltip);
					super.setBackground(originalBackground);
				}
			}
		}
		
		/**
		 * Set the tooltip text, it is displayed only if 
		 * the combo is not in error state
		 */
		@Override
		public void setToolTipText(String string) {
			if (!hasError) super.setToolTipText(string);
			originalTooltip = string;
		}
		
		/**
		 * Set the background, it is displayed only if 
		 * the combo is not in error state
		 */
		@Override
		public void setBackground(Color color) {
			if (hasError) super.setBackground(color);
			originalBackground = color;
		}
		
		/**
		 * Set the combo as readonly or not
		 * @param isReadOnly true if it is readonly, false otherwise
		 */
		public void setReadOnly(boolean isReadOnly) {
			this.isReadOnly = isReadOnly;
		}
		
		@Override
		protected void checkSubclass() {
		}
		
	}