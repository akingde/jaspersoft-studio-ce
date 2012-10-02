package com.jaspersoft.studio.swt.widgets;




import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

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
	 * Class that handle the painting of the Text area and add to the text a representation of 
	 * the color
	 * @author Orlandin Marco
	 *
	 */
	private class ColorListener implements PaintListener{
		
		public ColorListener(){}
		
    public void paintControl(PaintEvent e) {
    	if (color!=null){
	      e.gc.setBackground(color);
	      e.gc.drawRectangle(1, 1, 13, 13);
	      e.gc.fillRectangle(2,2,12,12);
    	}
    }
	}
	
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
			String text = textArea.getText();
			//Add to the start of the string the symbol #
			if (!text.startsWith("#")){
				text = "#".concat(text);
				textArea.setText(text);
			}
			
			//Convert the text into color only if there are exactly seven chars, a # symbol followed by 
			//three pair of hex values
			if (text.length()==7){
				Color newColor = null;
				try{
					newColor =  new Color(null,
		          Integer.valueOf( text.substring( 1, 3 ), 16 ),
		          Integer.valueOf( text.substring( 3, 5 ), 16 ),
		          Integer.valueOf( text.substring( 5, 7 ), 16 ) );
				} catch(NumberFormatException ex){
					newColor = color;
				}
				
				//If the color has been changed and the event flag is open then fire the events
				if (color != newColor){
					color = newColor;
					if (raiseEvents)
						for(ModifyListener element : listener){
							element.modifyText(e);
						}
				}
			}
		}
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is a number
	 * @param keyCode the key code
	 * @return true if the code is of a number, false otherwise
	 */
	private boolean isNumber(int keyCode){
		return (keyCode >=48 && keyCode <=57);
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is a character
	 * @param keyCode the key code
	 * @return true if the code is of a character, false otherwise
	 */
	private boolean isCharachter(int keyCode){
		return (keyCode >=97 && keyCode <=122);
	}
	
	/**
	 * Check if a keycode, a code associated to a keybord's key, is alphanumeric
	 * @param keyCode the key code
	 * @return true if the code is of a alphanumeric, false otherwise
	 */
	private boolean isAlphanumeric(int keyCode){
		return isNumber(keyCode) || isCharachter(keyCode);
	}
	
	
	/**
	 * Construc the element
	 * @param parent the composite where the the element will be placed
	 */
	public ColorStyledText(Composite parent){
		listener = new ArrayList<ModifyListener>();
		final Composite paintArea = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(2,false);
		paintArea.setLayout(layout);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		GridData data = new GridData();
		data.heightHint = 16;
		data.verticalAlignment = SWT.CENTER;
		data.horizontalAlignment = SWT.CENTER;
		
		GridData textData = new GridData();
		textData.verticalAlignment = SWT.CENTER;
		textData.widthHint = 50;
		textData.heightHint = 16;
		
		textArea = new StyledText(paintArea, SWT.SINGLE);
		textArea.setLayoutData(textData);
		textArea.setLeftMargin(18);
		textArea.setAlignment(SWT.LEFT);
		textArea.addPaintListener(new ColorListener());
		textArea.addModifyListener(new EditListener());
		
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretMoved(CaretEvent event) {
				//The cursor can not move behind the # symbol 
				if (textArea.getCaretOffset() == 0)
					textArea.setCaretOffset(1);
			}
		});
		
		//Mouse listener to avoid that the cursor is placed before the # symbol when the focus is gained
		textArea.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {}
			
			@Override
			public void mouseDown(MouseEvent e) {
				if (textArea.getCaretOffset()== 0)
					textArea.setCaretOffset(1);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		//Handle the text insertion to avoid string too long or invalid
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String actualText = textArea.getText();
				int offset = textArea.getCaretOffset();
				//String too long, restore the old one
				if (textArea.getCaretOffset() == 8)
					textArea.setText(lastValidText);
				//Character valid but string full, the new character will substitute the one after it
				else if (actualText.length()>7 && isAlphanumeric(e.keyCode)){
					lastValidText = actualText.substring(0,textArea.getCaretOffset()).concat(actualText.substring(offset+1));
					textArea.setText(lastValidText);
					textArea.setCaretOffset(offset);
				//The space are not allowed, restore the old text
				} else if (e.keyCode == ' '){
					textArea.setText(lastValidText);
					textArea.setCaretOffset(offset);
				} else if (e.keyCode == SWT.BS){
					//Handle the backspace to avoid the removal of the # symbol
					if (textArea.getCaretOffset()==0){
						textArea.setText(lastValidText);
						textArea.setCaretOffset(1);
					}
					else 
						lastValidText = actualText;
				} else  lastValidText = actualText;
			}
		});
		
		
		final Button lineColor = new Button(paintArea, SWT.PUSH | SWT.FILL);
		lineColor.setLayoutData(data);
		lineColor.setText("...");
		
		//Open the color selection window when the button is pushed
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(paintArea.getShell());
				cd.setText(Messages.common_line_color);
				RGB newColor = cd.open();
				lineColor.setSelection(false);
				setColor(newColor,true);
			}
		});
	}
	
	private String leftPadWithZero(String baseString){
		return StringUtils.leftPad(baseString, 2,"0");
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
       return  "#".concat(s.toUpperCase()); //$NON-NLS-1$ //$NON-NLS-2$
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
