package com.jaspersoft.studio.property.section.obj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.JREnum;

import org.apache.commons.lang.StringUtils;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValuesMap;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Class that paint the widget where are shown the attributes of element, those inherited by the it's styles, and the 
 * default values
 * @author Orlandin Marco
 *
 */
public class StylesListSection extends AbstractSection {
	
	/**
	 * Reference to the container of all the styles
	 */
	private static MStyles stylesClass = null;
	
	
	/**
	 * Color for the text that represent the attribute name
	 */
	private static Color leftStringColor = null;
	
	/**
	 * Image show to remove an attribute
	 */
	private static ImageData image = new ImageData(StylesListSection.class.getResourceAsStream("/icons/resources/remove-16.png"));
	
	/**
	 * Map of all the styles, where the name of the style is it's key
	 */
	private HashMap<String,MStyle> styleMaps;
	
	/**
	 * List of attributes overridden by others of an upper level in the hierarchy (the hierarchy is element-styles-default style-default attribute)
	 */
	private HashSet<String> ovverridenAttributes;
	
	/**
	 * Reference to the default style
	 */
	private MStyle defaultStyle = null;
	
	/**
	 * Reference to the element actually displayed
	 */
	private	APropertyNode element = null;
	
	/**
	 * The map of the element own attributes
	 */
	private HashMap<String, Object> elementAttributes = null;
	
	/**
	 * Manager for the events binded to the mouse
	 */
	private IconMouseTracker trackerListener = new IconMouseTracker();
	
	/**
	 * Set to true if a refresh of the widget is needed
	 */
	private boolean forceRefresh = false;

	/**
	 * Class to manage the events of the mouse click, used to remove an attribute from an element or one
	 * of it's styles
	 * @author Orlandin Marco
	 *
	 */
	private class ElementClickListener implements MouseListener {
		
		/**
		 * Element binded to this event
		 */
		private	APropertyNode element;
		
		/**
		 * Property of the element binded to this event
		 */
		private String property;
		
		
		public ElementClickListener(APropertyNode element, String property){
			this.element = element;
			this.property = property;
		}
		
    public void mouseDown(MouseEvent e) {
    }

    /**
     * Set the property of the element binded to this event to null, using the manipulation 
     * commands (so the operation can be undone)
     */
    public void mouseUp(MouseEvent e) {
			CommandStack cs = getEditDomain().getCommandStack();
			CompoundCommand cc = new CompoundCommand("Set " + property);
    	Command c = changeProperty(property, null,element);
   		if (c != null) cc.add(c);
  		if (!cc.getCommands().isEmpty()) {
				cs.execute(cc);
				
				forceRefresh = true;
    		refresh();
  		}
    }

    public void mouseDoubleClick(MouseEvent e) {

    }
  }
	
	/**
	 * Class to manage the events of the mouse move event, as the mouse over and the mouse exit, used 
	 * to show the button to delete an attribute when the mouse pointer is over it
	 * @author Orlandin Marco
	 *
	 */
	private class IconMouseTracker implements MouseTrackListener{

		/**
		 * Last element where the remove button was shown
		 */
		Composite lastElementSelected = null;
		
		/**
		 * Set the last element to null, necessary after a refresh of the widget because the old element were 
		 * deallocated, so this pointer need to be reseted too. 
		 */
		public void refresh(){
			lastElementSelected = null;
		}
		
		/**
		 * Handle the event of the mouse enter on area that show the delete image, eventually 
		 * hide the old one. Change event the mouse cursor to an hand.
		 */
		@Override
		public void mouseEnter(MouseEvent e) {
			Composite parentLayout = null;
			if (e.widget instanceof Composite)
				parentLayout = ((Composite)e.widget);
			else 
				parentLayout = ((Control)e.widget).getParent();
			if (lastElementSelected == null)
				lastElementSelected = parentLayout;
			if (parentLayout.getChildren().length > 1) {
				lastElementSelected.getChildren()[0].setVisible(false);
	   		lastElementSelected = parentLayout;	
				parentLayout.getChildren()[0].setVisible(true);
				parentLayout.getChildren()[0].setCursor(new Cursor(null,SWT.CURSOR_HAND));
			}
		}

		/**
		 * Handle the event of the mouse exit the area that show the delete image, hide the image
		 */
		@Override
		public void mouseExit(MouseEvent e) {
			Composite parentLayout = null;
			if (e.widget instanceof Composite)
				parentLayout = ((Composite)e.widget);
			else 
				parentLayout = ((Control)e.widget).getParent();
    	if (parentLayout.getChildren().length>1)
    		parentLayout.getChildren()[0].setVisible(false);
		}

		@Override
		public void mouseHover(MouseEvent e) {}
		
	}
	
	/**
	 * Build the hierarchy of styles of an element
	 * @param element Element from which the styles list will be generated
	 * @return A list of MStyle, where the first is the style assigned to the element, the second is 
	 * the style assigned to the first item of the list and so on
	 */
	private LinkedList <MStyle> buildStylesGerarchy(APropertyNode element){
		LinkedList <MStyle> result = new LinkedList <MStyle>();
		Object styleName = element.getPropertyValue(JRDesignElement.PROPERTY_PARENT_STYLE);
		JRStyle style = element.getJasperDesign().getStylesMap().get(styleName);
		if (style != null){
			do{
				result.addLast(styleMaps.get(style.getName()));
				style = style.getStyle();
			} while (style != null);
		}
		return result;
	}
	
	/**
	 * Add to a styledtext a new style to made the text with a middle black line (strike trought)
	 * @param valueText StyledText widget where the new style will be applied
	 */
	private void strikeStyledText(StyledText valueText){
		StyleRange style1 = new StyleRange();
		style1.strikeout = true;
		style1.start = 0;
		style1.length = valueText.getText().length();
		valueText.setStyleRange(style1);
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
       String s = Integer.toHexString(r) + Integer.toHexString(g) +  
                        Integer.toHexString(b);
       return  "#"+StringUtils.rightPad(s, 6, "0").toUpperCase();
   }
	
	/**
	 * Paint on the main widget the fields to show the value of an attribute color. the color will be expressed in textual
	 * form, as RGB values, but with a tooltip that show the visually the color
	 * @param parent Reference to the widget composite
	 * @param colorValue Value of the color 
	 * @param colorName Name of the attribute
	 * @param gData Grid data for the layout
	 * @param addLine true if a stroke line is needed
	 * @return The button where the click handle will be added
	 */
	private Control paintColor(Composite parent, Color colorValue, String colorName, GridData gData, boolean addLine){
		String stringValue = getHexFromRGB(colorValue);//"RGB("+colorValue.getRed()+","+colorValue.getGreen()+","+colorValue.getBlue()+")";
		Composite nameComp = new Composite(parent, SWT.NONE);
		nameComp.setLayout(new RowLayout());
		nameComp.setLayoutData(gData);
		
 	 	Label imageLabel = new Label(nameComp, SWT.NONE);
		imageLabel.setImage(new Image(null, image));
 	 	imageLabel.setVisible(false);
		
		StyledText label = new StyledText(nameComp, SWT.NONE);
 		label.setText(Messages.getString("common_"+colorName)); //$NON-NLS-1$
  	label.setForeground(leftStringColor);
		label.setEditable(false);
		label.setEnabled(false);
		
		Composite valueComp = new Composite(parent, SWT.NONE);
		valueComp.setLayout(new RowLayout());
		valueComp.setLayoutData(gData);
		StyledText valueText = new StyledText(valueComp, SWT.NONE);
		valueText.setText(stringValue);
		valueText.setEditable(false);
		valueText.setEnabled(false);
		if (addLine){
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		
		//Add the tool tip on the composite under the element, it works anyway because the element is disabled
		DefaultToolTip toolTip = new DefaultToolTip(valueComp);
		toolTip.setBackgroundColor(colorValue);
		toolTip.setText("           ");
		toolTip.setHideDelay(0);
		toolTip.setPopupDelay(0);
		return imageLabel;
	}
	
	/**
	 * Paint a couple of string as two label
	 * @param parent Reference to the widget composite
	 * @param name The name of the attribute
	 * @param value The value of the attribute
	 * @param gData Grid data for the layout
	 * @param addLine true if a stroke line is needed
	 * @return The button where the click handle will be added
	 */
	private Control printLabels(Composite parent, String name, String value, GridData gData, boolean addLine){
		Composite valueComp = new Composite(parent, SWT.NONE);
		valueComp.setLayout(new RowLayout());
		valueComp.setLayoutData(gData);
		
 	 	Label imageLabel = new Label(valueComp, SWT.NONE);
		imageLabel.setImage(new Image(null, image));
 	 	imageLabel.setVisible(false);

 	 
		StyledText label = new StyledText(valueComp, SWT.NONE);
 		label.setText(Messages.getString("common_"+name)); //$NON-NLS-1$
  	label.setForeground(leftStringColor);
		label.setEditable(false);
		label.setEnabled(false);
		
		
		StyledText valueText = new StyledText(parent, SWT.NONE);
		valueText.setText(value);
		valueText.setEditable(false);
		valueText.setEnabled(false);
		if (addLine){
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		return imageLabel;
	}
	
	
	/**
	 * Paint a boolean attribute, it's value is expressed as text
	 * @param parent Reference to the widget composite
	 * @param name The name of the attribute
	 * @param checked true if the attribute has value true, false otherwise
	 * @param gData Grid data for the layout
	 * @param addLine true if a stoke line is needed
	 * @return The button where the click handle will be added
	 */
	private Control paintCheckBox(Composite parent, String name, boolean checked, GridData gData, boolean addLine){
		String stringValue = Messages.getString("common_boolean_"+checked);
		return printLabels(parent, name,stringValue, gData, addLine);
	}
	
	/**
	 * Add the mouse move listener to an element, it's father and it's brothers
	 * @param element 
	 */
	private void AddListener(Control element){
		Composite parent = element.getParent();
		parent.addMouseTrackListener(trackerListener);
		for(Control son : parent.getChildren()){
			son.addMouseTrackListener(trackerListener);
		}
	}
	
	/**
	 * Print a generic object attribute with the appropriate widgets on the main composite
	 * @param keyPrefix optional prefix to the key represented by the name
	 * @param name Name and key of the attribute
	 * @param value value of the attribute
	 * @param parent main widget
	 * @param gData grid layout data
	 * @param printLine True if the printed attribute need a strike thought line
	 * @param actualElement element that contain the attribute
	 * @param addListener true if to the element will be added the listener for the mouse move\click
	 */
	private void printObject(String keyPrefix, String name, Object value, Composite parent, GridData gData, boolean printLine, APropertyNode actualElement, boolean addListener){
		if (value instanceof Color){
			Color valImage = (Color)value;
			Control label = paintColor(parent,valImage, keyPrefix+name, gData, printLine);
			if (addListener) {
				AddListener(label);
				label.addMouseListener(new ElementClickListener(actualElement,name));
			}
		} else if (value instanceof java.awt.Color){
			java.awt.Color valImage = (java.awt.Color)value;
			Control label = paintColor(parent, ModelUtils.getSWTColorFromAWT(valImage), keyPrefix+name, gData, printLine);
			if (addListener) {
				AddListener(label);
				label.addMouseListener(new ElementClickListener(actualElement,name));
			}
		} else if (value instanceof JREnum){
			JREnum enumValue = (JREnum) value;
			Control label = printLabels(parent, keyPrefix+name, enumValue.getName(), gData, printLine);
			if (addListener) {
				AddListener(label);
				label.addMouseListener(new ElementClickListener(actualElement,name));
			}
		} else if (value instanceof Boolean){
			Control label = paintCheckBox(parent,keyPrefix+name,(Boolean)value, gData, printLine);
			if (addListener) {
				AddListener(label);
				label.addMouseListener(new ElementClickListener(actualElement,name));
			}
		} else if (value instanceof MLinePen){
			MLinePen lineValue = (MLinePen) value;
			//I need to pass a new context for the linepen because it's a composite value, so in the main hashmap i have only the
			//complex value, not all it's fields
			printStyleAttribute(parent, lineValue, null, keyPrefix+name+"_", ((MLinePen)elementAttributes.get(name)).getStylesDescriptors());
		} else {
			Control label = printLabels(parent, keyPrefix+name, value.toString(), gData, printLine);
			if (addListener) {
				AddListener(label);
				label.addMouseListener(new ElementClickListener(actualElement,name));
			}
		}
	}
	
	
	/**
	 * Print a title label, gray that take a whole line
	 * @param parent composite of the main widget
	 * @param value text to put into the label
	 */
	private void printTitle(Composite parent, String value){
		Label label = new Label(parent, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		gridData.widthHint = SWT.FILL;
		gridData.heightHint = 20;
		label.setLayoutData(gridData);
		label.setBackground(new Color(null,240,240,240));
		label.setText(" "+value);
	}
	
	/**
	 * Print the attributes of an element store in the elementAttributes hash map
	 * @param parent composite of the main widget
	 * @param element The selected element
	 * @param titleValue The title to print for this element
	 */
	private void printElementAttribute(Composite parent, APropertyNode element, String titleValue){
		if (titleValue != null){
			printTitle(parent,titleValue);
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 200;
		Iterator<String> it=elementAttributes.keySet().iterator();
	  while (it.hasNext()) {
	  	String key = it.next();
	  	Object elementAttribute = elementAttributes.get(key);
	  	if (elementAttribute!=null){
	  		printObject("",key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(key),element,true);
	  		ovverridenAttributes.add(key);
	  	}
	  }
	}
	
	/**
	 * Print the attributes that belong to a styles
	 * @param parent composite of the main widget
	 * @param element The selected element
	 * @param titleValue The title to print for this element
	 * @param keyPrefix optional prefix of the attribute key
	 * @param localElementAttributes Map that contains the attribute of the element associated with this style
	 */
	private void printStyleAttribute(Composite parent, APropertyNode element, String titleValue, String keyPrefix, HashMap<String, Object> localElementAttributes){
		if (titleValue != null){
			printTitle(parent,titleValue);
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 200;
		HashMap<String, Object> properties = element.getStylesDescriptors();
		Iterator<String> it=properties.keySet().iterator();
	  while (it.hasNext()) {
	  	String key = it.next();
	  	Object elementAttribute = properties.get(key);
	  	if (elementAttribute!=null && localElementAttributes.containsKey(key)){
	  		printObject(keyPrefix, key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(keyPrefix + key),element,true);
	  		ovverridenAttributes.add(keyPrefix +key);
	  	}
	  }
	}
	
	/**
	 * Print the attributes of all the styles of the element
	 * @param styles List of styles
	 * @param parent composite of the main widget
	 */
	private void printStyles(LinkedList <MStyle> styles, Composite parent){
		ListIterator<MStyle> itr = styles.listIterator();
		boolean hasDefaultStyleInGerarchy = false;
	  while(itr.hasNext()){
	  	MStyle style = itr.next();
	  	printStyleAttribute(parent,style,Messages.StylesSectionList_Inherited_From_Style + style.getPropertyValue(JRDesignStyle.PROPERTY_NAME),"", elementAttributes);
			if (style == defaultStyle) hasDefaultStyleInGerarchy = true;
	  }
	  if (!hasDefaultStyleInGerarchy && defaultStyle != null && defaultStyle != element)
	  	printStyleAttribute(parent,defaultStyle,"Inherited from the deafult style " + defaultStyle.getPropertyValue(JRDesignStyle.PROPERTY_NAME),"",elementAttributes);
	}
	
	/**
	 * Initialize the map of the styles
	 */
	private void initStyleMaps(){
		styleMaps = new HashMap<String, MStyle>();
		ovverridenAttributes = new HashSet<String>();
		List<INode> list = stylesClass.getChildren();
		Iterator<INode> it = list.iterator();
		while(it.hasNext()){
			MStyle element = (MStyle)it.next();
			styleMaps.put(element.getPropertyValue(JRDesignStyle.PROPERTY_NAME).toString(), element);
			if ((Boolean)element.getPropertyValue(JRDesignStyle.PROPERTY_DEFAULT)) defaultStyle = element;
		}
	}
	
	/**
	 * Print the default values of an element, the aren't editable
	 * @param parent composite of the main widget
	 * @param defaultValues map of the default values
	 */
	private void printDefaultValues(Composite parent, Map<String,Object> defaultValues){
		printTitle(parent,"Default attributes");
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 200;
		Iterator<String> it=defaultValues.keySet().iterator();
		while (it.hasNext()) {
		  	String key = it.next();
		  	Object elementAttribute = defaultValues.get(key);
		  	if (elementAttribute != null && elementAttributes.containsKey(key)){
		  		printObject("",key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(key),element,false);
		  	}
		}
	}
	
	private boolean CheckDifferences(HashMap<String, Object> element1Attributes, HashMap<String, Object> element2Attributes){
		boolean areEquals = true;
		Iterator<String> it=element2Attributes.keySet().iterator();
		while(areEquals && it.hasNext()){
			String key = it.next();
			Object actualValue = element2Attributes.get(key);
			Object prevValue = element1Attributes.get(key);
			//I have one new key
			if (!element1Attributes.containsKey(key)) areEquals = false;
			else {
				if (actualValue == null)
					//case: The new value is null but the old one not
					areEquals = prevValue == null;
				else  areEquals = actualValue.equals(prevValue);
			}
		}
		return areEquals;
	}
	
	/**
	 * Check if the element attributes, or the attributes from one of it's styles are changed and in that case it update the
	 * the widget
	 * @return True if the widget should be updated, false otherwise
	 */
	private boolean checkRefresh(){
		HashMap<String, Object> actualAttributes = element.getStylesDescriptors();
		boolean areEquals = true;
		if (getElement() != element || forceRefresh) areEquals = false;
		//	if (areEquals)
			//The element are not obviously different, check if all it's attributes are equals
		//	areEquals = CheckDifferences(elementAttributes, actualAttributes);
		elementAttributes = actualAttributes;
		forceRefresh = false;
		return true;//!areEquals;
	}
	
	/**
	 * Refresh the style widget deleting the old component and recreating the updated ones
	 */
	@Override
	public void refresh() {
		isRefreshing = true;
		if (checkRefresh()){
			trackerListener.refresh();
			element = getElement();
			//Dispose the old widgets
			 for (Control kid : parent.getChildren()) {
         kid.dispose();
       }
			GridLayout layout = new GridLayout(2,false);
			layout.marginWidth=0;
			parent.setLayout(layout);
			initStyleMaps();
			LinkedList <MStyle> styles = buildStylesGerarchy(element);
			printElementAttribute(parent,element,Messages.StylesSectionList_Element_Attributes);
			printStyles(styles,parent);
			printDefaultValues(parent,DefaultValuesMap.getPropertiesByType(element.getClass()));
			ovverridenAttributes = null;
			styleMaps = null;
			parent.layout();
		}
		isRefreshing = false;
	}
	
	/**
	 * Initialize the styles widget
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		element = getElement();
		if (stylesClass == null){
			List<INode> children = element.getRoot().getChildren();
			Iterator<INode> it = children.iterator();
			while(it.hasNext() && stylesClass == null){
				INode childElement = it.next();
				if (childElement instanceof MStyles)
					stylesClass = (MStyles) childElement;
			}
			leftStringColor = new Color(null,42,96,213);
		}
		initStyleMaps();
		GridLayout layout = new GridLayout(2,false);
		layout.marginWidth=0;
		parent.setLayout(layout);
		initStyleMaps();
		LinkedList<MStyle> styles = buildStylesGerarchy(element);
		elementAttributes = element.getStylesDescriptors();
		printElementAttribute(parent,element,Messages.StylesSectionList_Element_Attributes);
		printStyles(styles,parent);
		printDefaultValues(parent,DefaultValuesMap.getPropertiesByType(element.getClass()));
		ovverridenAttributes = null;
		styleMaps = null;
	}
}
