package com.jaspersoft.studio.property.section.obj;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseBreak;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;


public class StylesListSection extends AbstractSection {
	
	private HashSet<Object> addedElements = new HashSet<Object>();

	private LinkedList <JRStyle> buildStylesGerarchy(APropertyNode element){
		LinkedList <JRStyle> result = new LinkedList <JRStyle>();
		Object styleName = element.getPropertyValue(JRDesignElement.PROPERTY_PARENT_STYLE);
		JRStyle style = element.getJasperDesign().getStylesMap().get(styleName);
		do{
			result.addFirst(style);
			style = style.getStyle();
		} while (style != null);
		return result;
	}
	
	private void paintColor(Composite parent, Color colorValue, String colorName, GridData gData){
		Label label = new Label(parent, SWT.NONE);
		label.setText(colorName);
		label.setLayoutData(gData);
  	final Canvas canvas = new Canvas(parent,SWT.NO_REDRAW_RESIZE);
		final Color styleBackColor = colorValue;
		canvas.setLayoutData(gData);
		canvas.addPaintListener(new PaintListener() { 
        public void paintControl(PaintEvent e) { 
            Rectangle clientArea = canvas.getClientArea(); 
            e.gc.setBackground(styleBackColor); 
         e.gc.fillRectangle(0,0,20,20); 
        } 
    });
	}
	
	private void addLabels(Composite parent, String[] values, GridData data){
	  for(int i=0;i<values.length;i++){
	  	String value = values[i];
			Label label = new Label(parent, SWT.NONE);
			label.setText(value);
			label.setLayoutData(data);
	  }
	}
	
	private void printElementAttribute(Composite parent, APropertyNode element){
		Label label = new Label(parent, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		gridData.heightHint = 20;
		label.setLayoutData(gridData);
		label.setBackground(new Color(null,240,240,240));
		label.setText("Element attributes");
		if (element.getFont() != null){
			//element.getPropertyDescriptor(JRDesignElement.pro)
			addLabels(parent,new String[]{"Font name:",""},gridData);
		}
	}
	
	private void printStyles(LinkedList <JRStyle> styles, Composite parent){
		ListIterator<JRStyle> itr = styles.listIterator();
	  while(itr.hasNext()){
	  	//Composite styleNamePanel = new Composite(parent, SWT.NONE); 
	  	JRStyle style = itr.next();
			Label label = new Label(parent, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.FILL;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalSpan = 2;
			gridData.heightHint = 20;
			label.setLayoutData(gridData);
			label.setBackground(new Color(null,240,240,240));
			label.setText("Interithed From style " + style.getName().toString());
			GridData sameSizeGridData = new GridData();
			sameSizeGridData.verticalAlignment = SWT.TOP;
			sameSizeGridData.heightHint = 30;
			addLabels(parent,new String[]{"Font name",style.getFontName()}, sameSizeGridData);
			paintColor(parent, ModelUtils.getSWTColorFromAWT(style.getForecolor()),"ForegroundColor: ",sameSizeGridData);
			paintColor(parent, ModelUtils.getSWTColorFromAWT(style.getBackcolor()),"Background Color: ",sameSizeGridData);
	  }
		
	}
	
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		APropertyNode element = getElement();
		parent.setLayout(new GridLayout(2,false));
		LinkedList <JRStyle> styles = buildStylesGerarchy(element);
		printElementAttribute(parent,element);
		printStyles(styles,parent);
	}
}
