package com.jaspersoft.studio.property.color.chooser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class ColorsSelectorWidget extends Composite {

	private Image oldImage = null;
	
	private Image oldHueImage = null;
	
	private Point circlePosition = new Point(0, 0);
	
	private RGB selectedColor = null;
	
	private Composite colorComposite;
	
	private Composite slider;
	
	private int sliderPosition = 0;
	
	private IWidgetGovernor governor;
	
	private int arrowWidth = 5;
	
	private Color borderColor = getDisplay().getSystemColor(SWT.COLOR_WIDGET_BORDER);
	
	private ImageData cachedSlider = null;
	
	private ImageData cachedPad = null;
	
	private List<SelectionListener> selListeners = new ArrayList<SelectionListener>();
	
	public ColorsSelectorWidget(Composite parent, int style, IWidgetGovernor governor) {
		super(parent, style);
		this.governor = governor;
		setLayout(new GridLayout(2,false));
		
		colorComposite = new Composite(this, SWT.BORDER);
		colorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		slider = new Composite(this, SWT.NONE);
		GridData rangeData = new GridData(SWT.LEFT, SWT.FILL, false, true);
		rangeData.widthHint = 30;
		slider.setLayoutData(rangeData);
		
		colorComposite.addListener(SWT.Resize, new Listener() {
      public void handleEvent(Event event) {
      	cachedPad = null;
      	paintPad();
      }
    });
		
		colorComposite.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				cachedSlider = null;
				updatePosition(e);
				paintPad();
				paintSlider();
      	callSelectionListeners();
			}

		});
		
		colorComposite.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if ((e.stateMask & SWT.BUTTON1) != 0){
					cachedSlider = null;
					updatePosition(e);
					paintPad();
					paintSlider();
	      	callSelectionListeners();
				}
			}
		});
		
		slider.addListener(SWT.Resize, new Listener() {
      public void handleEvent(Event event) {
      	cachedSlider = null;
      	paintSlider();
      }
    });
		
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				cachedPad = null;
				updateSlider(e);
				paintPad();
      	paintSlider();
      	callSelectionListeners();
			}
		});
		
		slider.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if ((e.stateMask & SWT.BUTTON1) != 0){
					cachedPad = null;
					updateSlider(e);
					paintPad();
	      	paintSlider();
	      	callSelectionListeners();
				}
			}
		});
	}
	
	private void callSelectionListeners(){
		Event e = new Event();
		e.widget = this;
		SelectionEvent event = new SelectionEvent(e);
		event.data = selectedColor;
		for(SelectionListener listener : selListeners){
			listener.widgetSelected(event);
		}
	}
	
	private void updatePosition(MouseEvent e){
		Rectangle rect = colorComposite.getClientArea();
		int x = Math.max(0, e.x);
		x = Math.min(x, rect.width-1);
		int y = Math.max(0, e.y);
		y = Math.min(y, rect.height-1);
		circlePosition = new Point(x, y);
	}
	
	private void updateSlider(MouseEvent e){
		Rectangle rect = slider.getClientArea();
		int y = Math.max(0, e.y);
		y = Math.min(y, rect.height);
		sliderPosition = y;
	}
	
	private int getAbsoluteXFromRelative(int relativeX){
		Rectangle rect = colorComposite.getClientArea();
		if (rect.width == 0) return 0;
		int padWidth = governor.getPadMaxX() - governor.getPadMinX();
		return (relativeX * (rect.width-1))/padWidth;
	}
	
	private int getAbsoluteYFromRelative(int relativeY){
		Rectangle rect = colorComposite.getClientArea();
		if (rect.height == 0) return 0;
		int padHeight = governor.getPadMaxY() - governor.getPadMinY();
		return (relativeY * (rect.height-1))/padHeight;
	}
	
	private int getAbsoluteSliderFromRelative(int relativeSlider){
		Rectangle rect = slider.getClientArea();
		if (rect.height == 0) return 0;
		int sliderHeight = governor.getSliderMax() - governor.getSliderMin();
		return (relativeSlider * (rect.height-1))/sliderHeight;
	}

	public void setGovernor(IWidgetGovernor newGovernor){
		this.governor = newGovernor;
  	cachedSlider = null;
  	cachedPad = null;
		paintPad();
  	paintSlider();
  	callSelectionListeners();
	}
	
	private ImageData createSliderImage(Rectangle rect){
	  int padWidth = governor.getPadMaxX() - governor.getPadMinX();
    int padHeight = governor.getPadMaxY() - governor.getPadMinY();
  	float padX = ((float)(circlePosition.x*padWidth))/(getPadWidth()-1);
  	float padY = ((float)(circlePosition.y*padHeight))/(getPadHeight()-1);
		ImageData image = new ImageData(Math.max(1, rect.width),Math.max(1, rect.height),32,new PaletteData(0xFF0000,0xFF00,0xFF));
		int sliderHeight = governor.getSliderMax() - governor.getSliderMin();
		
		for(int y = 0; y<image.height; y++){
			float actaulSlider = ((float)sliderHeight / getSliderHeight()) * y;
			RGB actualHueColor = governor.getSliderColor(Math.round(padX), Math.round(padY), Math.round(actaulSlider));
			for(int x = 0; x<image.width; x++){
				if (x > arrowWidth && x<image.width-arrowWidth)
					image.setPixel(x,y,image.palette.getPixel(actualHueColor));
				else 
					image.setPixel(x,y,image.palette.getPixel(getBackground().getRGB()));
			}
		}
		return image;
	}
	
	private ImageData createPadImage(Rectangle rect){
    ImageData imageData=new ImageData(Math.max(1, rect.width),Math.max(1, rect.height),32,new PaletteData(0xFF0000,0xFF00,0xFF));
    int padWidth = governor.getPadMaxX() - governor.getPadMinX();
    int padHeight = governor.getPadMaxY() - governor.getPadMinY();
    //Calculate the actual slider
    int slider = Math.round(((float)governor.getSliderMax() / getSliderHeight())*sliderPosition);
    for(int i=0; i<imageData.width; i++){
    	for (int j=0; j<imageData.height; j++){
      	float padX = ((float)(i*padWidth))/(imageData.width-1);
      	float padY = ((float)(j*padHeight))/(imageData.height-1);
    		imageData.setPixel(i,j,imageData.palette.getPixel(governor.getPadColor(Math.round(padX), Math.round(padY), slider)));
    	}
    }
    
    return imageData;
	}
	
	public void setSelectedColor(RGB color, boolean callListener){
		if (color != null){
			int relativeSlide = governor.getSlider(color);
			int newSliderPosition = getAbsoluteSliderFromRelative(relativeSlide);
			if (sliderPosition != newSliderPosition){
				sliderPosition = newSliderPosition;
				cachedPad = null;
			}
		}
	  if (color != null){
			int relativeX = governor.getX(color);
			int relativeY = governor.getY(color);
			Point newCirclePosition = new Point(getAbsoluteXFromRelative(relativeX), getAbsoluteYFromRelative(relativeY));
			if (circlePosition != newCirclePosition){
				circlePosition = new Point(getAbsoluteXFromRelative(relativeX), getAbsoluteYFromRelative(relativeY));
				cachedSlider = null;
			}
		}
	  RGB oldSelectedColor = selectedColor;
  	paintSlider();
		paintPad();
		if (selectedColor == null || selectedColor.equals(oldSelectedColor))
			selectedColor = color;
  	if (callListener) callSelectionListeners();
	}
	
	private int getSliderHeight(){
		if (cachedSlider != null) return cachedSlider.height;
  	Rectangle sliderRect = slider.getClientArea();
  	return sliderRect.height;
	}
	
	private int getPadWidth(){
		if (cachedPad != null) return cachedPad.width;
		Rectangle padRect = colorComposite.getClientArea();
		return padRect.width;
	}
	
	private int getPadHeight(){
		if (cachedPad != null) return cachedPad.height;
		Rectangle padRect = colorComposite.getClientArea();
		return padRect.height;
	}
	
	private void paintSlider(){

		Rectangle rect = slider.getClientArea();
		if (rect.height == 0) return;
		if (cachedSlider == null) cachedSlider = createSliderImage(rect);
		
		ImageData imageData = (ImageData)cachedSlider.clone();
		Image newImage = new Image(getDisplay(), imageData);
    GC gc = new GC(newImage);
    gc.setAntialias(SWT.ON);
    gc.setForeground(borderColor);
    gc.drawRectangle(arrowWidth, 0, imageData.width-arrowWidth*2, imageData.height-1);
    
    gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
    gc.fillPolygon(new int[]{0, sliderPosition-3, arrowWidth,sliderPosition,0,sliderPosition+3});
    gc.fillPolygon(new int[]{imageData.width, sliderPosition-3, imageData.width-arrowWidth,sliderPosition,imageData.width,sliderPosition+3});
    if (oldHueImage != null){
    	oldHueImage.dispose();
    }
    oldHueImage = newImage;
    gc.dispose();
    slider.setBackgroundImage(newImage);
	}
	
	private void paintPad(){
		Rectangle rect = colorComposite.getClientArea();
		
		if (rect.height == 0 || rect.width == 0) return;
	  
		if (cachedPad == null) cachedPad = createPadImage(rect);
    Image newImage =  new Image(getDisplay(), (ImageData)cachedPad.clone());
    GC gc = new GC(newImage);
    if (oldImage != null){
      oldImage.dispose();
    }
    oldImage = newImage;
    if (circlePosition != null){
      int pixelValue = newImage.getImageData().getPixel(circlePosition.x, circlePosition.y);
      PaletteData palette =  newImage.getImageData().palette;
      selectedColor = palette.getRGB(pixelValue);
    	gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	gc.setAntialias(SWT.ON);
    	gc.drawOval(circlePosition.x-2, circlePosition.y-2, 8, 8);
    	gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
    	gc.drawOval(circlePosition.x-1, circlePosition.y-1, 6, 6);
    }
    gc.dispose();
    colorComposite.setBackgroundImage(newImage);
	}
	
	public RGB getSelectedColor(){
		return selectedColor;
	}
	
	public void setSlider(int slider){
		this.sliderPosition = slider;
		paintPad();
  	paintSlider();
	}
	
	public void addSelectionListener(SelectionListener listener){
		selListeners.add(listener);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (oldImage != null && !oldImage.isDisposed()){
			oldImage.dispose();
		}
		if (oldHueImage != null && !oldHueImage.isDisposed()){
			oldHueImage.dispose();
		}
	}

}
