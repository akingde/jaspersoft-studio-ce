package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;

public class RangeWizardPage extends WizardPage {

	private RangeDefinition modifiedElement;
	
	private Spinner min;
	
	private Spinner max;
	
	private ComboMenuViewer colorCombo;
	
	public RangeWizardPage(RangeDefinition modifiedElement) {
		super("rangePage");
		this.modifiedElement = modifiedElement;
		if (modifiedElement != null){
			setTitle("Range Editing");
			setMessage("In this page you can edit the selected range");
		} else {
			setTitle("Range Creation");
			setMessage("In this page you can create a new range");
		}
	}	
	
	public RangeDefinition regenerateRange(){
		return new RangeDefinition(min.getSelection(), max.getSelection(), (String)colorCombo.getSelectionValue());
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(container, SWT.NONE).setText("From");
		min = new Spinner(container, SWT.BORDER);
		min.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		min.setMaximum(Integer.MAX_VALUE);
		min.setMinimum(Integer.MIN_VALUE);
		
		new Label(container, SWT.NONE).setText("To");
		max = new Spinner(container, SWT.BORDER);
		max.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		max.setMaximum(Integer.MAX_VALUE);
		max.setMinimum(Integer.MIN_VALUE);
		
		new Label(container, SWT.NONE).setText("Type");
		ArrayList<ComboItem> itemsList = new ArrayList<ComboItem>();
		int index = 0;
		for(String names : RangeDefinition.getNames()){
			itemsList.add(new ComboItem(MessagesByKeys.getString(names), true,  null, index, names, names));
			index++;
		}
		
		//Creating the combo popup
		colorCombo = new ComboMenuViewer(container, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		colorCombo.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		colorCombo.setItems(itemsList);
		colorCombo.select(0);
		
		if (modifiedElement != null){
			min.setSelection(modifiedElement.getMin());
			max.setSelection(modifiedElement.getMax());
			String name = modifiedElement.getName();
			int nameIndex = RangeDefinition.getNames().indexOf(name);
			if (nameIndex != -1){
				colorCombo.select(nameIndex);
			} else {
				colorCombo.select(0);
			}
		}
		setControl(container);
	}
}
