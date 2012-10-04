/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
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
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;

/**
 * This class is used to paint a "Color TextBox", a label where a color is expressed 
 * in hex value, with a representation of that color on the left and a button to select 
 * a color from a SO dependent window. The color can be changed also editing the textual hex value
 * 
 * @author Orlandin Marco
 *
 */
public class ColorStyledText {
	
	/**
	 * The color represented
	 */
	private Color color = null;
	
	/**
	 * The text area
	 */
	private StyledText textArea;
	
	/**
	 * Last valid textual value inserted for the color
	 */
	private String lastValidText;
	
	/**
	 * Listener called when the color is edited with the button or by editing it's textual value
	 */
	private List<ModifyListener> listener;
	
	/**
	 * Flag used to disable the call of the events into listener
	 */
	private boolean raiseEvents = true;
	
	/**
	 * Label where the color is printed
	 */
	private Label colorButton;
	
	/**
	 * Provider to convert a RGB color into an image
	 */
	private ColorLabelProvider provider;
	
	/**
	 * Guard that block the modify event when another is already going
	 */
	private boolean refreshingGuard;
	
	/**
	 * Class that handle the editing of the textual value of the color, if the textual
	 * value is in the expected format the new color will be used and the change will
	 * be notified to the handler, otherwise the old color will be taken
	 * @author Orlandin Marco
	 *
	 */
	private class EditListener implements ModifyListener{
		
		public EditListener(){}

		@Override
		public void modifyText(ModifyEvent e) {
			//Check if there are others modifyEvent going
			if (!refreshingGuard){
				refreshingGuard = true;
				String text = textArea.getText();
				//Convert the text into color only if there are exactly seven chars, a # symbol followed by 
				//three pair of hex values
				Color newColor = null;
				try{
					if (text.startsWith("[") && text.endsWith("]")){
						int index1 = text.indexOf("[")+1; 
						int index2 = text.indexOf(","); 
						int index3 = text.indexOf(",", index2+1); 
						int index4 = text.indexOf("]"); 
						int redCompontent = Integer.valueOf(text.substring(index1, index2));
						int greenCompontent = Integer.valueOf(text.substring(index2+1, index3));
						int blueCompontent = Integer.valueOf(text.substring(index3+1, index4));
						newColor = new Color(null, redCompontent, greenCompontent, blueCompontent);
					} else if (text.startsWith("#") && text.length() == 7){
						newColor =  new Color(null,
			          Integer.valueOf( text.substring( 1, 3 ), 16 ),
			          Integer.valueOf( text.substring( 3, 5 ), 16 ),
			          Integer.valueOf( text.substring( 5, 7 ), 16 ) );
					} else if (!text.startsWith("#") && text.length() == 6){
						newColor =  new Color(null,
			          Integer.valueOf( text.substring( 0, 2 ), 16 ),
			          Integer.valueOf( text.substring( 2, 4 ), 16 ),
			          Integer.valueOf( text.substring( 4, 6 ), 16 ) );
					}
				} catch(NumberFormatException ex){
				} catch(IllegalArgumentException ex){
				}
				//If the color has been changed and the event flag is open then fire the events
				if (newColor != null){
					color = newColor;
					textArea.setText(getHexFromRGB(color));
					colorButton.setImage(provider.getImage(color.getRGB(),18,14));
					if (raiseEvents)
						for(ModifyListener element : listener){
							element.modifyText(e);
						}
				}
				refreshingGuard = false;
			}
		}
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is a number
	 * @param keyCode the key code
	 * @return true if the code is of a number, false otherwise
	 */
	public static boolean isNumber(int keyCode){
		return (keyCode >=48 && keyCode <=57);
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is a character
	 * @param keyCode the key code
	 * @return true if the code is of a character, false otherwise
	 */
	public static boolean isCharachter(int keyCode){
		return (keyCode >=97 && keyCode <=122);
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is alphanumeric
	 * @param keyCode the key code
	 * @return true if the code is of a alphanumeric, false otherwise
	 */
	public static boolean isAlphanumeric(int keyCode){
		return isNumber(keyCode) || isCharachter(keyCode);
	}
	
	
	/**
	 * Construct the element
	 * @param parent the composite where the the element will be placed
	 */
	public ColorStyledText(Composite parent){
		refreshingGuard = false;
		listener = new ArrayList<ModifyListener>();
		provider = new ColorLabelProvider(NullEnum.NULL);
		final Composite paintArea = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(3,false);
		paintArea.setLayout(layout);
		layout.horizontalSpacing = 1;
		layout.verticalSpacing = 0;
		layout.marginHeight = 1;
		layout.marginWidth = 1;
		
		//Paint the preview box
		GridData previewData = new GridData();
		previewData.heightHint = 20;
		previewData.widthHint = 20;
		previewData.verticalAlignment = SWT.CENTER;
		previewData.horizontalAlignment = SWT.RIGHT;
		colorButton = new Label( paintArea, SWT.FILL);
		colorButton.setLayoutData(previewData);
		colorButton.setToolTipText(Messages.ColorStyledText_LineColor_ToolTip);
		colorButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {}
			
			@Override
			public void mouseDown(MouseEvent e) {
				ColorDialog cd = new ColorDialog(colorButton.getDisplay().getActiveShell());
				cd.setText(Messages.common_line_color);
				RGB newColor = cd.open();
				if (newColor != null){
					setColor(newColor,true);
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {}
		});

		//Paint the text area
		GridData textData = new GridData();
		textData.verticalAlignment = SWT.CENTER;
		textData.horizontalAlignment = SWT.LEFT;
		textArea = new StyledText(paintArea, SWT.SINGLE);
		textArea.setLayoutData(textData);
		textArea.setAlignment(SWT.LEFT);
		textArea.addModifyListener(new EditListener());
		
		//Paint the button
		GridData lineColorData = new GridData();
		lineColorData.heightHint = 20;
		lineColorData.widthHint = 35;
		lineColorData.verticalAlignment = SWT.CENTER;
		lineColorData.horizontalAlignment = SWT.RIGHT;
		final Button lineColor = new Button(paintArea, SWT.PUSH | SWT.FILL);
		lineColor.setLayoutData(lineColorData);
		lineColor.setText("..."); 
		lineColor.setToolTipText(Messages.ColorStyledText_LineColor_ToolTip);
		
		//Open the color selection window when the button is pushed
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(colorButton.getDisplay().getActiveShell());
				cd.setText(Messages.common_line_color);
				RGB newColor = cd.open();
				if (newColor != null){
					lineColor.setSelection(false);
					setColor(newColor,true);
				}
			}
		});
	}
	
	private String leftPadWithZero(String baseString){
		return StringUtils.leftPad(baseString, 2,"0"); //$NON-NLS-1$
	}
	
	/**
	 * Return the hexadecimal representation of a color 
	 * @param color The color 
	 * @return The color hexadecimal representation
	 */
	 private String getHexFromRGB(Color color)  
   {  
       int r = color.getRed();  
       int g = color.getGreen();  
       int b = color.getBlue();  
       String s = leftPadWithZero(Integer.toHexString(r)) + leftPadWithZero(Integer.toHexString(g)) +  
      		 					leftPadWithZero(Integer.toHexString(b));
       return  "#".concat(s.toUpperCase()); //$NON-NLS-1$ 
   }
	 
	 /**
	  * Set the color of element, either it's representation and it's textual value
	  * @param newColor the new color 
	  * @param callListener true to call the edit listener after the editing
	  */
	 protected void setColor(Color newColor, boolean callListener){
		 raiseEvents = callListener;
		 color = newColor;
		 lastValidText = getHexFromRGB(newColor);
		 textArea.setText(lastValidText);
		 raiseEvents = true;		 
	 }
	 
	 /**
	  * Set the color of element, either it's representation and it's textual value
	  * @param newColor the new color 
	  * @param callListener true to call the edit listener after the editing
	  */
	 public void setColor(RGB newColor, boolean callListener){
		 raiseEvents = callListener;
		 color = new Color(null,newColor);
		 lastValidText = getHexFromRGB(color);
		 textArea.setText(lastValidText);
		 raiseEvents = true;
	 }
	 
	 
	 /**
	  * Set the color of element, either it's representation and it's textual value. this method
	  * dosen't call the edit listeners
	  * @param newColor the new color 
	  */
	 public void setColor(Color newColor){
		 setColor(newColor,false);
	 }

	 
	 /**
	  * Set the color of element, either it's representation and it's textual value. this method
	  * dosen't call the edit listeners
	  * @param newColor the new color 
	  */
	 public void setColor(RGB newColor){
		 setColor(newColor,false);
	 }
	
	 /**
	  * Add a new listener for the editing of the color
	  * @param listener the new listener
	  */
	 public void addListener(ModifyListener listener){
		 this.listener.add(listener);
	 }
	 
	 /**
	  * Return the actual color
	  * @return the color in RGB format
	  */
	 public RGB getColor(){
		 return color.getRGB();
	 }
	
}
