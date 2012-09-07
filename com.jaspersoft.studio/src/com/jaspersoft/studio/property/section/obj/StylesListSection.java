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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

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


public class StylesListSection extends AbstractSection {
	
	private static MStyles stylesClass = null;
	
	private static Color leftStringColor = null;
	
	private static ImageData image = new ImageData(StylesListSection.class.getResourceAsStream("/icons/resources/remove-16.png"));
	
	private HashMap<String,MStyle> styleMaps;
	
	private HashSet<String> ovverridenAttributes;
	
	private MStyle defaultStyle = null;
	
	private	APropertyNode element = null;
	
	private HashMap<String, Object> elementAttributes = null;
	
	private IconMouseTracker trackerListener = new IconMouseTracker();

	private boolean needRefresh = false;
	
	private class ElementClickListener implements MouseListener {
		
		private	APropertyNode element;
		
		private String property;
		
		
		public ElementClickListener(APropertyNode element, String property){
			this.element = element;
			this.property = property;
		}
		
    public void mouseDown(MouseEvent e) {
    }

    public void mouseUp(MouseEvent e) {
    	Object propertyValue = element.getPropertyValue(property);
    	System.out.println(element.getClass()+"."+ property+":"+propertyValue  +"-> null");
			CommandStack cs = getEditDomain().getCommandStack();
			CompoundCommand cc = new CompoundCommand("Set " + property);
    	Command c = changeProperty(property, null,element);
   		if (c != null) cc.add(c);
  		if (!cc.getCommands().isEmpty()) {
				cs.execute(cc);
				System.out.println("new value " +  element.getPropertyValue(property));
    		needRefresh = true;
    		refresh();
  		}
    }

    public void mouseDoubleClick(MouseEvent e) {

    }
  }
	
	private class MouseHoverListener implements Listener {

     public void handleEvent(Event e) {
     	 System.out.println("puzza");
     	 Composite parentLayout = ((Composite)e.widget);
    	 if (parentLayout.getChildren().length>1){
    		 parentLayout.getChildren()[0].setVisible(true);
    	 }
     }
   }
	
	private class MouseExitListener implements Listener {

    public void handleEvent(Event e) {
    	Composite parentLayout = ((Composite)e.widget);
      for (Control control : parentLayout.getChildren()) {
        if (control == e.item || parentLayout == e.item)
            return;
      }
   	 System.out.println("out");
   	 if (parentLayout.getChildren().length>1){
   		parentLayout.getChildren()[0].setVisible(false);
   	 }
    }
  }
	
	private class IconMouseTracker implements MouseTrackListener{

		Composite lastElementSelected = null;
		
		public void refresh(){
			lastElementSelected = null;
		}
		
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
			}
		}

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
	
	private Control paintColor(Composite parent, Color colorValue, String colorName, GridData gData, boolean addLine){
		String stringValue = "RGB("+colorValue.getRed()+","+colorValue.getGreen()+","+colorValue.getBlue()+")";
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
		DefaultToolTip toolTip = new DefaultToolTip(valueComp);
		toolTip.setBackgroundColor(colorValue);
		toolTip.setText("           ");
		toolTip.setHideDelay(0);
		toolTip.setPopupDelay(0);
		return imageLabel;
	}
	
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
	
	
	private Control paintCheckBox(Composite parent, String name, boolean checked, GridData gData, boolean addLine){
		String stringValue = Messages.getString("common_boolean_"+checked);
		return printLabels(parent, name,stringValue, gData, addLine);
	}
	
	private void AddListener(Control element){
//		element.addMouseListener(new ElementClickListener(actualElement,name));
//		element.getParent().addListener(SWT.MouseEnter, new MouseHoverListener());
//		element.getParent().addListener(SWT.MouseExit, new MouseExitListener());
		Composite parent = element.getParent();
		parent.addMouseTrackListener(trackerListener);
		for(Control son : parent.getChildren()){
			son.addMouseTrackListener(trackerListener);
		}
	}
	

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
		sameSizeGridData.widthHint = 200;
		elementAttributes = element.getStylesDescriptors();
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
	
	@Override
	public void refresh() {
		isRefreshing = true;
		if (getElement() != element || needRefresh){
			trackerListener.refresh();
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
			needRefresh = false;
			parent.layout();
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
