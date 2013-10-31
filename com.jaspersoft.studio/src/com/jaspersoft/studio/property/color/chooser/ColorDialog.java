package com.jaspersoft.studio.property.color.chooser;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.utils.AlfaRGB;

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
	
	/*private Text l = null;
	
	private Text a = null;
	
	private Text b = null;
	
	private Text c = null;
	
	private Text m = null;
	
	private Text y = null;
	
	private Text k = null;*/
	
	private Text hex = null;
	
	private Boolean modfiedGuard = true;
	
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
		 colorPreview.setLayout(new GridLayout(1,false));
		 Label newColorLabel = new Label(colorPreview, SWT.NONE);
		 newColorLabel.setText("New color");
		 newColorLabel.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false));
		 previewComposite = new Composite(colorPreview, SWT.BORDER);
		 previewComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		 if (currentColor != null){
			 Label oldColorLabel = new Label(colorPreview, SWT.NONE);
			 oldColorLabel.setText("Actual color");
			 oldColorLabel.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		 }
		 GridData previewData = new GridData();
		 previewData.widthHint = 90;
		 previewData.heightHint = 100;
		 previewData.verticalAlignment = SWT.TOP;
		 colorPreview.setLayoutData(previewData);
		 
		 createSlider(righSide);
		 createTextArea(righSide);
		 //UIUtils.resizeAndCenterShell(getShell(),350,300);		 
		 updateText();
		 return container;
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
		alphaText.setSelection(255);
		
		
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
		
		hue = createRadio(leftPart, "H:", "�", new HueBasedSelector(), true, 0, 360);
		saturation = createRadio(leftPart, "S:", "%", new SaturationBasedSelector(), false, 0, 100);
		brightness = createRadio(leftPart, "B:", "%", new BrightnessBasedSelector(), false, 0, 100);
		red = createRadio(rightPart, "R:", " ", new RedBasedSelector(), false, 0, 255);
		green = createRadio(rightPart, "G:", " ", new GreenBasedSelector(), false, 0, 255);
		blue = createRadio(rightPart, "B:", " ", new BluBasedSelector(), false, 0, 255);
		hex = createText(leftPart, "#", "");
		

		
		/*l = createRadio(rightPart, "L:", "", new LBasedSelector(), false);
		a = createText(rightPart, "a:", "");
		b = createText(rightPart, "b:", "");
		
		c = createText(rightPart, "C:", "%");
		m = createText(rightPart, "M:", "%");
		y = createText(rightPart, "Y:", "%");
		k = createText(rightPart, "K:", "%");*/
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
		if (returnCode == Dialog.CANCEL) return null;
		else return new AlfaRGB(colorsSelector.getSelectedColorRGB(), alpha);
	}
	
	
	public RGB openRGB(){
		int returnCode = super.open();
		actualPreviewImage.dispose();
		if (returnCode == Dialog.CANCEL) return null;
		else return colorsSelector.getSelectedColorRGB();
	}
	
	
	public void setText(String title){
		shellTitle = title;
	}
	
}
