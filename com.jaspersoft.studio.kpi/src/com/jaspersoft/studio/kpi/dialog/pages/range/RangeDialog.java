package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;

public class RangeDialog extends Dialog {

	private RangeDefinition modifiedElement;
	
	private Spinner min;
	
	private Spinner max;
	
	private ComboMenuViewer colorCombo;
	
	private ModifyListener modListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			regenerateRange();
		}
	};
	
	private void regenerateRange(){
		modifiedElement = new RangeDefinition(min.getSelection(), max.getSelection(), (String)colorCombo.getSelectionValue());
	}
	
	public RangeDialog(Shell parentShell, RangeDefinition modifiedElement) {
		super(parentShell);
		this.modifiedElement = modifiedElement;
	}
	
	public RangeDialog(Shell parentShell){
		super(parentShell);
		modifiedElement = null;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Range Definition");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
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
				regenerateRange();
			}
		} else {
			regenerateRange();
		}
		
		min.addModifyListener(modListener);
		max.addModifyListener(modListener);
		colorCombo.addSelectionListener(new ComboItemAction() {		
			@Override
			public void exec() {
				regenerateRange();
			}
		});
		
		return container;
	}
	
	public RangeDefinition getDefinition(){
		return modifiedElement;
	}
}
