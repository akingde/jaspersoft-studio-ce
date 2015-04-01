package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.awt.Color;
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

import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;

public class RangeDialog extends Dialog {

	private RangeDefinition modifiedElement;
	
	private Spinner min;
	
	private Spinner max;
	
	private ComboMenuViewer colorCombo;
	
	private ColorLabelProvider colorProvider;
	
	private ModifyListener modListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			regenerateRange();
		}
	};
	
	private void regenerateRange(){
		modifiedElement = new RangeDefinition(min.getSelection(), max.getSelection(), (String)colorCombo.getSelectionValue(), colorCombo.getSelectedItem().getText());
	}
	
	public RangeDialog(Shell parentShell, ColorLabelProvider colorProvider, RangeDefinition modifiedElement) {
		super(parentShell);
		this.modifiedElement = modifiedElement;
		this.colorProvider = colorProvider;
	}
	
	public RangeDialog(Shell parentShell, ColorLabelProvider colorProvider){
		super(parentShell);
		modifiedElement = null;
		this.colorProvider = colorProvider;
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
		
		new Label(container, SWT.NONE).setText("Trend");
		ArrayList<ComboItem> itemsList = new ArrayList<ComboItem>();
		itemsList.add(new ComboItem(RangeDefinition.getNameFromColor(Color.GREEN), true,  colorProvider.getImage(Color.GREEN),0, Color.GREEN, RangeDefinition.getHexColor(Color.GREEN)));
		itemsList.add(new ComboItem(RangeDefinition.getNameFromColor(Color.YELLOW), true, colorProvider.getImage(Color.YELLOW),1, Color.YELLOW, RangeDefinition.getHexColor(Color.YELLOW)));
		itemsList.add(new ComboItem(RangeDefinition.getNameFromColor(Color.RED), true, colorProvider.getImage(Color.RED),2, Color.RED, RangeDefinition.getHexColor(Color.RED)));
		
		//Creating the combo popup
		colorCombo = new ComboMenuViewer(container, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		colorCombo.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		colorCombo.setItems(itemsList);
		colorCombo.select(0);
		
		if (modifiedElement != null){
			min.setSelection(modifiedElement.getMin());
			max.setSelection(modifiedElement.getMax());
			Color editedColor = Color.decode(modifiedElement.getColor());
			if (Color.RED.equals(editedColor)) colorCombo.select(2);
			else if (Color.YELLOW.equals(editedColor)) colorCombo.select(1);
			else {
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
	
	@Override
	public boolean close() {
		colorProvider.dispose();
		return super.close();
	}
	
	public RangeDefinition getDefinition(){
		return modifiedElement;
	}
}
