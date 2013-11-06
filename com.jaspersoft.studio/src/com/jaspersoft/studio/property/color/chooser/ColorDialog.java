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
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.color.chooser;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.ImageUtils;

/**
 * An advanced color dialog that offers more functionalities towards the default one,
 * like the transparency. It also provide a system independent way to choose the color. 
 * 
 * @author Orlandin Marco
 *
 */
public class ColorDialog extends Dialog{

	
	private ColorsSelectorWidget colorsSelector;
	
	private String shellTitle = null;
	
	private RGB currentColor = null;
	
	private Composite previewComposite = null;
	
	private Image actualPreviewImage = null;
	
	private Scale alphaSlider;
	
	private int alpha = 255;
	
	private List<Button> radioList = new ArrayList<Button>();
	
	private Spinner hue = null;
	
	private Spinner saturation = null;
	
	private Spinner brightness = null;
	
	private Spinner red = null;
	
	private Spinner green = null;
	
	private Spinner blue = null;
	
	private Text hex = null;
	
	private Boolean modfiedGuard = true;
	
	private Button pickColorButton;

	private Font buttonFont = ResourceManager.getFont("Arial",10,SWT.BOLD);
	
  /** timer interval for checking color */
  private static final int TIMER_INTERVAL = 50;
	
	private class ColorPickerThreadClass implements Runnable {
		
		private Boolean stopThread = false;
		
		public boolean getStop(){
			synchronized (this) {
				return stopThread;
			}
		}
		
		private void setStop(boolean value){
			synchronized (this) {
				stopThread = value;
			}
		}
		
	  @Override
	  public void run() {
	  	if (!getStop()) {
	  		checkColorPicker();
	  		Display.getCurrent().timerExec(TIMER_INTERVAL, this);
	  	}
	  }
	};
	
	private ColorPickerThreadClass colorPickerThread = new ColorPickerThreadClass();
	
	private Listener getColor = new Listener() {
		
		@Override
		public void handleEvent(Event event) {
			if (event.keyCode == SWT.SPACE){
				stopPickerThread();
			}	
		}
	};
	
	private ModifyListener valueModifedListener = new ModifyListener(){
		
		@Override
		public void modifyText(ModifyEvent e) {
			synchronized (modfiedGuard) {
				if (modfiedGuard){
					modfiedGuard = false;
					hue.getParent().getParent().setRedraw(false);
					if (e.widget == hue || e.widget == saturation || e.widget == brightness){
						float h = (float)hue.getSelection();
						float s = (float)saturation.getSelection()/100;
						float b = (float)brightness.getSelection()/100;
						colorsSelector.setSelectedColor(h, s, b, false);
						updateText(h,s,b);
					} else if (e.widget == red || e.widget == green || e.widget == blue){
						RGB rgbColor = new RGB(red.getSelection(),green.getSelection(),blue.getSelection());
						colorsSelector.setSelectedColor(rgbColor, false);
						updateText(rgbColor, rgbColor.getHSB());
					} else if (e.widget == hex){
						RGB rgbColor = hexParser(hex.getText());
						if (rgbColor != null){
							colorsSelector.setSelectedColor(rgbColor, false);
							updateText(rgbColor, rgbColor.getHSB());
						}
					}
					hue.getParent().getParent().setRedraw(true);
					modfiedGuard = true;
				}
			}
		}
		
	};
	
	private RGB hexParser(String text){
		// Convert the text into color only if there are exactly seven chars, a # symbol followed by
		// three pair of hex values
		RGB newColor = null;
		try {
			if (text.startsWith("#") && text.length() == 7) {
				newColor = new RGB(Integer.valueOf(text.substring(1, 3), 16), Integer.valueOf(text.substring(3, 5), 16), Integer.valueOf(text.substring(5, 7), 16));
			} else if (!text.startsWith("#") && text.length() == 6) {
				newColor = new RGB(Integer.valueOf(text.substring(0, 2), 16), Integer.valueOf(text.substring(2, 4), 16), Integer.valueOf(text.substring(4, 6), 16));
			}
		} catch (NumberFormatException ex) {
		} catch (IllegalArgumentException ex) {
		}
		return newColor;
	}
	
	public ColorDialog(Shell parent){
		super(parent);
	}
	
	public ColorDialog(Shell parent, RGB currentColor){
		super(parent);
		this.currentColor = currentColor;
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (shellTitle != null){
			newShell.setText(shellTitle);
		}
	}
	

	/**
	 * Return the hexadecimal representation of a color
	 * 
	 * @param color
	 *          The color
	 * @return The color hexadecimal representation
	 */
	private String getHexFromRGB(RGB color) {
		int r = color.red;
		int g = color.green;
		int b = color.blue;
		String s = Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
		return "#" + StringUtils.rightPad(s, 6, "0").toUpperCase(); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private void updateText(){
		updateText(colorsSelector.getSelectedColorRGB(), colorsSelector.getSelectedColorHSB());
	}
	
	private void updateText(RGB color, float[] hsb){
		synchronized (modfiedGuard) {
			modfiedGuard = false;
			hue.getParent().getParent().setRedraw(false);
			red.setSelection(color.red);
			green.setSelection(color.green);
			blue.setSelection(color.blue);
			hex.setText(getHexFromRGB(color));
			int h = Math.round(hsb[0]);
			int s = Math.round(hsb[1]*100);
			int b = Math.round(hsb[2]*100);
			hue.setSelection(h);
			saturation.setSelection(s);
			brightness.setSelection(b);
			hue.getParent().getParent().setRedraw(true);
			updatePreview();
			modfiedGuard = true;
		}
	}
	
	private void updateText(float fh, float fs, float fb){
		synchronized (modfiedGuard) {
			modfiedGuard = false;
			hue.getParent().getParent().setRedraw(false);
			int h = Math.round(fh);
			int s = Math.round(fs*100);
			int b = Math.round(fb*100);
			hue.setSelection(h);
			saturation.setSelection(s);
			brightness.setSelection(b);
			RGB color = new RGB(fh,fs,fb);
			red.setSelection(color.red);
			green.setSelection(color.green);
			blue.setSelection(color.blue);
			hex.setText(getHexFromRGB(color));
			hue.getParent().getParent().setRedraw(true);
			updatePreview();
			modfiedGuard = true;
		}
	}
	
	private void updatePreview(){
		Rectangle rect = previewComposite.getClientArea();
		ImageData imageData=new ImageData(Math.max(1, rect.width),Math.max(1, rect.height),32,new PaletteData(0xFF0000,0xFF00,0xFF));
		Image newImage = new Image(previewComposite.getDisplay(), imageData);
    GC gc = new GC(newImage);
    gc.setAntialias(SWT.ON);
    if (currentColor != null){
    	gc.setAlpha(255);
    	gc.setBackground(colorsSelector.getDisplay().getSystemColor(SWT.COLOR_WHITE));
      gc.fillRectangle(0, 0, imageData.width, imageData.height/2);
      gc.setAlpha(alpha);
      gc.setBackground(ResourceManager.getColor(colorsSelector.getSelectedColorRGB()));
      gc.fillRectangle(0, 0, imageData.width, imageData.height/2);
      gc.setAlpha(255);
      gc.setBackground(ResourceManager.getColor(currentColor));
      gc.fillRectangle(0, imageData.height/2, imageData.width, imageData.height);
    } else {
    	gc.setAlpha(255);
    	gc.setBackground(colorsSelector.getDisplay().getSystemColor(SWT.COLOR_WHITE));
      gc.fillRectangle(0, 0, imageData.width, imageData.height);
      gc.setAlpha(alpha);
      gc.setBackground(ResourceManager.getColor(colorsSelector.getSelectedColorRGB()));
      gc.fillRectangle(0, 0, imageData.width, imageData.height);
    }
    if (actualPreviewImage != null){
    	actualPreviewImage.dispose();
    }
    gc.dispose();
    actualPreviewImage = newImage;
    previewComposite.setBackgroundImage(newImage);
	}
	
	public void setRGB(RGB color){
		if (color != null){
			currentColor = color;
			alpha = 255;
			if (alphaSlider != null) alphaSlider.setSelection(alpha);
			if (previewComposite != null) updateText(currentColor, currentColor.getHSB());
		}
	}
	
	public void setRGB(AlfaRGB color){
		if (color != null){
			currentColor = color.getRgb();
			alpha = color.getAlfa();
			if (alphaSlider != null) alphaSlider.setSelection(alpha);
			if (previewComposite != null) updateText(currentColor, currentColor.getHSB());
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		 Composite container = (Composite) super.createDialogArea(parent);
		 container.setLayout(new GridLayout(2,false));
		 container.setLayoutData(new GridData(GridData.FILL_BOTH));
		 colorsSelector = new ColorsSelectorWidget(container, SWT.NONE, new HueBasedSelector());
		 GridData rectangleData = new GridData(GridData.FILL_BOTH);
		 rectangleData.widthHint = 300;
		 rectangleData.heightHint = 200;
		 colorsSelector.setLayoutData(rectangleData);
		 
		 if (currentColor != null) colorsSelector.setSelectedColor(currentColor,false);
		 else colorsSelector.setSelectedColor(new RGB(255,255,255), false);
		 
		 colorsSelector.addSelectionListener(new SelectionAdapter() {
			 	@Override
				public void widgetSelected(SelectionEvent e) {
			 		updateText();
				}
		 });
		 
		 Composite righSide = new Composite(container, SWT.NONE);
		 righSide.setLayout(new GridLayout(1,false));
		 righSide.setLayoutData(new GridData(GridData.FILL_BOTH));
		 
		 Composite colorPreview = new Composite(righSide, SWT.NONE);
		 colorPreview.setLayout(new GridLayout(2,false));
		 colorPreview.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		 
		 Label newColorLabel = new Label(colorPreview, SWT.NONE);
		 newColorLabel.setText("New color");
		 newColorLabel.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false));
		 new Label(colorPreview, SWT.NONE);
		 
		 previewComposite = new Composite(colorPreview, SWT.BORDER);
		 previewComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		 previewComposite.addControlListener(new ControlAdapter(){
			 @Override
			public void controlResized(ControlEvent e) {
				updatePreview();
			}
		 });
		 
		 createColorPicker(colorPreview);
		 
		 
		 if (currentColor != null){
			 Label oldColorLabel = new Label(colorPreview, SWT.NONE);
			 oldColorLabel.setText("Actual color");
			 oldColorLabel.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		 }
		 GridData previewData = new GridData();
		 previewData.widthHint = 120;
		 previewData.heightHint = 50;
		 previewData.verticalAlignment = SWT.TOP;
		 previewComposite.setLayoutData(previewData);
		 
		 createSlider(righSide);
		 createTextArea(righSide);
		 updateText();
		 return container;
	}
	
	private void createColorPicker(Composite parent){
		 pickColorButton = new Button(parent, SWT.WRAP);
		 pickColorButton.setToolTipText("Press this button to get the color from an element on the screen");
		 pickColorButton.setFont(buttonFont);
		 pickColorButton.setAlignment(SWT.CENTER);
		 GridData buttonData = new GridData();
		 buttonData.widthHint = 50;
		 buttonData.heightHint = 50;
		 buttonData.verticalAlignment = SWT.CENTER;
		 pickColorButton.setLayoutData(buttonData);
	   pickColorButton.setImage(getPickerImage(40));
		 
		 pickColorButton.addSelectionListener(new SelectionAdapter() {
			 @Override
			public void widgetSelected(SelectionEvent e) {
				colorPickerThread.setStop(false);
				Display.getCurrent().timerExec(TIMER_INTERVAL, colorPickerThread);
        Display.getCurrent().addFilter(SWT.KeyDown, getColor);
        pickColorButton.setImage(null);
        pickColorButton.setText("press SPACE to stop the color picking");
        pickColorButton.setEnabled(false);
        pickColorButton.setLayoutData(new GridData(GridData.FILL_BOTH));
        pickColorButton.getParent().layout(true, true);
        getShell().forceFocus();
			}
		 });
	}
	
	private void createSlider(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		new Label(container, SWT.NONE).setText("Transparency");
		alphaSlider = new Scale(container, SWT.HORIZONTAL);
		alphaSlider.setMaximum(0);
		alphaSlider.setMaximum(255);
		alphaSlider.setSelection(alpha);
		alphaSlider.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final Spinner alphaText = new Spinner(container, SWT.BORDER);
		alphaText.setMinimum(0);
		alphaText.setMaximum(255);
		alphaText.setSelection(alpha);
		
		
		alphaSlider.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				alphaText.setSelection(alphaSlider.getSelection());
				alpha = alphaSlider.getSelection();
				updatePreview();
			}
		});
		
		alphaText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				alphaSlider.setSelection(alphaText.getSelection());
				alpha = alphaSlider.getSelection();
				updatePreview();
			}
		});
	}
	
	private void createTextArea(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite leftPart = new Composite(container, SWT.NONE);
		leftPart.setLayout(new GridLayout(3,false));
		leftPart.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite rightPart = new Composite(container, SWT.NONE);
		rightPart.setLayout(new GridLayout(3,false));
		rightPart.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		hue = createRadio(leftPart, "H:", "\u030A", new HueBasedSelector(), true, 0, 360);
		saturation = createRadio(leftPart, "S:", "%", new SaturationBasedSelector(), false, 0, 100);
		brightness = createRadio(leftPart, "B:", "%", new BrightnessBasedSelector(), false, 0, 100);
		red = createRadio(rightPart, "R:", " ", new RedBasedSelector(), false, 0, 255);
		green = createRadio(rightPart, "G:", " ", new GreenBasedSelector(), false, 0, 255);
		blue = createRadio(rightPart, "B:", " ", new BluBasedSelector(), false, 0, 255);
		hex = createText(leftPart, "#", "");
	}
	
	private Text createText(Composite parent, String title, String suffix){
		new Label(parent, SWT.NONE).setText(title);
		Text actualText = new Text(parent, SWT.BORDER);
		actualText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (suffix != null) new Label(parent, SWT.NONE).setText(suffix);
		else new Label(parent, SWT.NONE);
		actualText.addModifyListener(valueModifedListener);
		return actualText;
	}

	
	private Spinner createRadio(Composite parent, String title, String suffix, IWidgetGovernor governor, boolean defaultEnabled, int min, int max){
		final Button radio = new Button(parent, SWT.RADIO);
		radioList.add(radio);
		radio.setText(title);
		radio.setData(governor);
		radio.setSelection(defaultEnabled);
		radio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (radio.getSelection()){
					disableAllRadioExceptOne(radio);
					colorsSelector.setGovernor((IWidgetGovernor)radio.getData());
				}
			}
		
		});
		Spinner actualText = new Spinner(parent, SWT.BORDER);
		actualText.setMinimum(min);
		actualText.setMaximum(max);
		actualText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (suffix != null) new Label(parent, SWT.NONE).setText(suffix);
		else new Label(parent, SWT.NONE);
		actualText.addModifyListener(valueModifedListener);
		return actualText;
	}
	
	private void disableAllRadioExceptOne(Button excludedRadio){
		for(Button radio : radioList){
			if (radio.getSelection() && radio != excludedRadio){
				radio.setSelection(false);
			}
		}
	}
	
	public int getAlpha(){
		return alpha;
	}
	
	public AlfaRGB openAlfaRGB(){
		int returnCode = super.open();
		actualPreviewImage.dispose();
		stopPickerThread();
		if (returnCode == Dialog.CANCEL) return null;
		else return new AlfaRGB(colorsSelector.getSelectedColorRGB(), alpha);
	}
	
	public RGB openRGB(){
		//When a simple RGB is requested hide the alpha control
		Composite alphaContainer = alphaSlider.getParent();
		alphaContainer.setVisible(false);
		GridData data = (GridData)alphaContainer.getLayoutData();
		data.exclude = true;
		alphaContainer.getParent().layout();
		
		int returnCode = super.open();
		actualPreviewImage.dispose();
    stopPickerThread();
		if (returnCode == Dialog.CANCEL) return null;
		else return colorsSelector.getSelectedColorRGB();
	}
	
	private boolean checkControlFocused(Control[] controls){
		for(Control control : controls){
			if (control.isFocusControl()) return true;
			if (control instanceof Composite){
				Composite comp = (Composite)control;
				boolean childrenFocused = checkControlFocused(comp.getChildren());
				if (childrenFocused) return true;
			}
		}
		return false;
	}
	
  private void checkColorPicker() {
		if (getShell().isFocusControl() || checkControlFocused(getShell().getChildren())){
	  	Robot robot;
			try {
				robot = new Robot();
				Point pos = Display.getCurrent().getCursorLocation();
				java.awt.Color color = robot.getPixelColor(pos.x, pos.y);
				RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
				colorsSelector.setSelectedColor(rgbColor, false);
				updateText(rgbColor, rgbColor.getHSB());
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
  }
  
  private Image getPickerImage(int size){
  	String key = "pickerIcon"+String.valueOf(size);
  	Image result = ResourceManager.getImage(key);
  	if (result == null){
  		result = ImageUtils.resize(ResourceManager.getPluginImage(JaspersoftStudioPlugin.PLUGIN_ID, "/icons/resources/picker.png"), size, size);
  		ResourceManager.addImage(key, result);
  	}
  	return result;
  }
  
  private void stopPickerThread(){
		colorPickerThread.setStop(true);
    Display.getCurrent().removeFilter(SWT.KeyDown, getColor);
    if (!pickColorButton.isDisposed()){
	    pickColorButton.setText("");
	    pickColorButton.setEnabled(true);
			GridData buttonData = new GridData();
			buttonData.widthHint = 50;
		  buttonData.heightHint = 50;
	    pickColorButton.setImage(getPickerImage(40));
			pickColorButton.setLayoutData(buttonData);
			pickColorButton.getParent().layout(true, true);
    }
	}
	
	public void setText(String title){
		shellTitle = title;
	}
	
}
