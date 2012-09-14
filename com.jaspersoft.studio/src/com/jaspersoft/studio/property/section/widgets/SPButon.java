package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.base.JRBaseFont;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPButon extends ASPropertyWidget {

	/**
	 * The button widget
	 */
	private Button button;
	
	/**
	 * True if it is a decrement button, false otherwise
	 */
	private boolean increment;
	
	/**
	 * The element with the font attribute
	 */
	private APropertyNode fontValue;
	
	/**
	 * The image for the button
	 */
	private Image imageValue;
	
	/**
	 * Percentual factor for the increment\decrement
	 */
	private static Integer factor = 10;
	
	/**
	 * Tooltip message
	 */
	private String message;

	/**
	 * Crate a new button for increment or decrement of the font size
	 * @param parent parent where the button will be painted
	 * @param section section of the element
	 * @param pDescriptor descriptor of the attribute
	 * @param increment True if it is a decrement button, false otherwise
	 * @param fontValue The element with the font attribute
	 */
	public SPButon(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, boolean increment, APropertyNode fontValue){
		super(parent, section, pDescriptor);
		this.increment = increment;
		if (increment) {
			message = Messages.SPButon_Size_Increment.concat(factor.toString());
			imageValue = JaspersoftStudioPlugin.getImage("/icons/resources/edit-size-up.png"); 
		}
		else {
			message = Messages.SPButon_Size_Decrement.concat(factor.toString());
			imageValue = JaspersoftStudioPlugin.getImage("/icons/resources/edit-size-down.png"); 
		}
		this.fontValue = fontValue;
		createComponent(parent);
	}
	
	@Override
	protected void createComponent(Composite parent) {
		if (fontValue != null){
			Button button = new Button(parent, SWT.PUSH);
			button.setImage(imageValue);
			button.addListener(SWT.Selection, new Listener() {
	
				@Override
				public void handleEvent(Event event) {
					Object fontSize = fontValue.getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);
					Integer newValue = 2;
					if (fontSize != null && fontSize.toString().length()>0) 
						newValue = Integer.valueOf(fontSize.toString());
						Integer plus = null;
						if (increment) plus = Math.round((new Float(newValue) / 100)*factor)+1;
						else plus =  Math.round((new Float(newValue) / 100)*-factor)-1;
						if ((newValue+plus)>99) newValue = 99;
						else if ((newValue+plus)>0) newValue += plus;
					section.changeProperty(JRBaseFont.PROPERTY_FONT_SIZE, newValue.toString());
				}
	
			});
			button.setToolTipText(message);
		}
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
	}

	@Override
	public Control getControl() {
		return button;
	}

}
