package com.jaspersoft.studio.property.section.obj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.text.StyleConstants.ColorConstants;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.JREnum;

import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import com.jaspersoft.studio.property.descriptor.color.ColorCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;


public class StylesListSection extends AbstractSection {
	
	private static MStyles stylesClass = null;
	
	private static Color leftStringColor = null;
	
	private HashMap<String,MStyle> styleMaps;
	
	private HashSet<String> ovverridenAttributes;
	
	private MStyle defaultStyle = null;
	
	private	APropertyNode element = null;
	
	private HashMap<String, Object> elementAttributes = null;
	
	private class ElementClickListener implements MouseListener {
		
		private	APropertyNode element;
		
		private String property;
		
		private Object oldValue;
		
		public ElementClickListener(APropertyNode element, String property){
			this.element = element;
			this.property = property;
			oldValue = null;
		}
		
    public void mouseDown(MouseEvent e) {
    }

    public void mouseUp(MouseEvent e) {
    	Object propertyValue = element.getPropertyValue(property);
    	System.out.println(element.getClass()+"."+ property+":"+oldValue +"->"+propertyValue);
    	if (oldValue == null && propertyValue!=null){
    		oldValue = propertyValue;
    		element.setPropertyValue(property, null);
    		((StyledText)e.getSource()).setForeground(org.eclipse.draw2d.ColorConstants.gray);
    	} else if (oldValue != null && propertyValue == null){
    		element.setPropertyValue(property, oldValue);
    		oldValue = null;
    		((StyledText)e.getSource()).setForeground(org.eclipse.draw2d.ColorConstants.black);
    	}
    }

    public void mouseDoubleClick(MouseEvent e) {

    }
  }
	
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
	
	private void strikeStyledText(StyledText valueText){
		StyleRange style1 = new StyleRange();
		style1.strikeout = true;
		style1.start = 0;
		style1.length = valueText.getText().length();
		valueText.setStyleRange(style1);
	}
	
	private StyledText paintColor(Composite parent, Color colorValue, String colorName, GridData gData, boolean addLine){
		StyledText label = new StyledText(parent, SWT.READ_ONLY);
		label.setText(Messages.getString("common_"+colorName));
		label.setLayoutData(gData);
		label.setForeground(leftStringColor);
		label.setEnabled(false);
		label.setEditable(false);
		StyledText valueText = new StyledText(parent, SWT.READ_ONLY);
		String stringValue = "RGB("+colorValue.getRed()+","+colorValue.getGreen()+","+colorValue.getBlue()+")";
		valueText.setText(stringValue);
		valueText.setLayoutData(gData);
		valueText.setEnabled(true);
		valueText.setEditable(false);
		DefaultToolTip toolTip = new DefaultToolTip(valueText);
		toolTip.setBackgroundColor(colorValue);
		toolTip.setText("           ");
		toolTip.setHideDelay(0);
		toolTip.setPopupDelay(0);
		if (addLine){
			strikeStyledText(valueText);
			strikeStyledText(label);
			//label.addPaintListener(new LinePaintListener());
		}
		return valueText;
	}
	
	private StyledText printLabels(Composite parent, String name, String value, GridData gData, boolean addLine){
  	StyledText label = new StyledText(parent, SWT.NONE);
 		label.setText(Messages.getString("common_"+name)); //$NON-NLS-1$
  	label.setForeground(leftStringColor);
		label.setLayoutData(gData);
		label.setEditable(false);
		label.setEnabled(false);
		StyledText valueText = new StyledText(parent, SWT.NONE);
		valueText.setText(value);
		valueText.setEditable(false);
		if (addLine){
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		return valueText;
	}
	
	
	private StyledText paintCheckBox(Composite parent, String name, boolean checked, GridData gData, boolean addLine){
		StyledText label = new StyledText(parent, SWT.NONE);
		label.setText(name);
		label.setLayoutData(gData);
		label.setForeground(leftStringColor);
		label.setEditable(false);
		label.setEnabled(false);
		StyledText valueText = new StyledText(parent, SWT.NONE);
		String stringValue = Messages.getString("common_boolean_"+checked);
		valueText.setText(stringValue);
		valueText.setEditable(false);
		valueText.setEnabled(false);
		if (addLine){
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		return valueText;
	}
	

	private void printObject(String keyPrefix, String name, Object value, Composite parent, GridData gData, boolean printLine, APropertyNode actualElement){
		if (value instanceof Color){
			Color valImage = (Color)value;
			paintColor(parent,valImage, keyPrefix+name, gData, printLine).addMouseListener(new ElementClickListener(actualElement,name));
		} else if (value instanceof java.awt.Color){
			java.awt.Color valImage = (java.awt.Color)value;
			paintColor(parent, ModelUtils.getSWTColorFromAWT(valImage), keyPrefix+name, gData, printLine).addMouseListener(new ElementClickListener(actualElement,name));
		} else if (value instanceof JREnum){
			JREnum enumValue = (JREnum) value;
			printLabels(parent, keyPrefix+name, enumValue.getName(), gData, printLine).addMouseListener(new ElementClickListener(actualElement,name));
		} else if (value instanceof Boolean){
			paintCheckBox(parent,keyPrefix+name,(Boolean)value, gData, printLine).addMouseListener(new ElementClickListener(actualElement,name));
		} else if (value instanceof MLinePen){
			MLinePen lineValue = (MLinePen) value;
			//I need to pass a new context for the linepen because it's a composite value, so in the main hashmap i have only the
			//complex value, not all it's fields
			printStyleAttribute(parent, lineValue, null, keyPrefix+name+"_", ((MLinePen)elementAttributes.get(name)).getStylesDescriptors());
		} else {
			printLabels(parent, keyPrefix+name, value.toString(), gData, printLine).addMouseListener(new ElementClickListener(actualElement,name));
			//System.out.println(value.getClass().toString());
		}
	}
	
	
	
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
		label.setText(value);
	}
	
	private void printElementAttribute(Composite parent, APropertyNode element, String titleValue){
		if (titleValue != null){
			printTitle(parent,titleValue);
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 150;
		elementAttributes = element.getStylesDescriptors();
		Iterator<String> it=elementAttributes.keySet().iterator();
	  while (it.hasNext()) {
	  	String key = it.next();
	  	Object elementAttribute = elementAttributes.get(key);
	  	if (elementAttribute!=null){
	  		printObject("",key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(key),element);
	  		ovverridenAttributes.add(key);
	  	}
	  }
	}
	
	private void printStyleAttribute(Composite parent, APropertyNode element, String titleValue, String keyPrefix, HashMap<String, Object> localElementAttributes){
		if (titleValue != null){
			printTitle(parent,titleValue);
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 150;
		HashMap<String, Object> properties = element.getStylesDescriptors();
		Iterator<String> it=properties.keySet().iterator();
	  while (it.hasNext()) {
	  	String key = it.next();
	  	Object elementAttribute = properties.get(key);
	  	if (elementAttribute!=null && localElementAttributes.containsKey(key)){
	  		printObject(keyPrefix, key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(keyPrefix + key),element);
	  		ovverridenAttributes.add(keyPrefix +key);
	  	}
	  }
	}
	
	
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
	
	private void printDefaultValues(Composite parent, Map<String,Object> defaultValues){
		printTitle(parent,"Default attributes");
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = 20;
		sameSizeGridData.widthHint = 150;
		Iterator<String> it=defaultValues.keySet().iterator();
		while (it.hasNext()) {
		  	String key = it.next();
		  	Object elementAttribute = defaultValues.get(key);
		  	if (elementAttribute != null && elementAttributes.containsKey(key)){
		  		printObject("",key, elementAttribute, parent,sameSizeGridData, ovverridenAttributes.contains(key),element);
		  	}
		}
	}
	
	@Override
	public void refresh() {
		isRefreshing = true;
		if (getElement() != element){
			element = getElement();
			initStyleMaps();
			//Dispose the old widgets
			 for (Control kid : parent.getChildren()) {
         kid.dispose();
       }
			GridLayout layout = new GridLayout(2,false);
			parent.setLayout(layout);
			LinkedList <MStyle> styles = buildStylesGerarchy(element);
			printElementAttribute(parent,element,Messages.StylesSectionList_Element_Attributes);
			printStyles(styles,parent);
			if (element.getDefaultsMap() == null) element.getPropertyDescriptors();
			printDefaultValues(parent,element.getDefaultsMap());
			elementAttributes = null;
			ovverridenAttributes = null;
			styleMaps = null;
		}
		isRefreshing = false;
	}
	
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
		parent.setLayout(layout);
		LinkedList <MStyle> styles = buildStylesGerarchy(element);
		printElementAttribute(parent,element,Messages.StylesSectionList_Element_Attributes);
		printStyles(styles,parent);
		//if (element.getDefaultsMap() == null) element.getPropertyDescriptors();
		printDefaultValues(parent,DefaultValuesMap.getPropertiesByType(element.getClass()));
		elementAttributes = null;
		ovverridenAttributes = null;
		styleMaps = null;
	}
}
