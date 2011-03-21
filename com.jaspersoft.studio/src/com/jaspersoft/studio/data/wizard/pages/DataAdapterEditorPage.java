package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class DataAdapterEditorPage extends WizardPage {

	Composite mainContainer = null;
	Composite staticContainer = null;
	Composite customContainer = null;
	DataAdapterEditor dataAdapterEditor = null;
	Composite editorComposite = null;
	private Text textName;
	
	public DataAdapterEditor getDataAdapterEditor() {
		return dataAdapterEditor;
	}
	
	public String getTextValue() {
		return textName.getText();
	}

	/**
	 * Create the wizard.
	 */
	public DataAdapterEditorPage() {
		super("dataAdaptereditorpage");
		setTitle("DataAdapter");
		setDescription("Edit your DataAdapter");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		System.out.println("createControl of DataAdapterEditorPage");
		mainContainer = new Composite(parent, SWT.NONE);
		setControl(mainContainer);
		GridLayout gl_mainContainer = new GridLayout(1, false);
		gl_mainContainer.horizontalSpacing = 0;
		gl_mainContainer.verticalSpacing = 0;
		gl_mainContainer.marginWidth = 0;
		gl_mainContainer.marginHeight = 0;
		mainContainer.setLayout(gl_mainContainer);
		
		staticContainer = new Composite(mainContainer, SWT.NONE);
		GridData gd_staticContainer = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gd_staticContainer.heightHint = 44;
		gd_staticContainer.widthHint = 575;
		staticContainer.setLayoutData(gd_staticContainer);
		GridLayout gl_staticContainer = new GridLayout(2, false);
		gl_staticContainer.marginHeight = 0;
		gl_staticContainer.marginWidth = 0;
		staticContainer.setLayout(gl_staticContainer);
		
		Label lblName = new Label(staticContainer, SWT.NONE);
		lblName.setText("Name");
		
		textName = new Text(staticContainer, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = new Label(staticContainer, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(staticContainer, SWT.NONE);
		new Label(staticContainer, SWT.NONE);
		new Label(staticContainer, SWT.NONE);
		new Label(staticContainer, SWT.NONE);
		
		customContainer = new Composite(mainContainer, SWT.NONE);
		customContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		customContainer.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textName.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				System.out.println("Modifying text " + textName.getText());
				boolean b = true;
				if(textName.getText() == "") b = false;
				setPageComplete(b);
				if (b) { 
					setMessage("");
				}
				else
				{
					setMessage("Please specify a name for this Data Adapter.", ERROR);
				}
			}
		});
		setPageComplete(false);
		setMessage("Please specify a name for this Data Adapter.", ERROR);
		//setErrorMessage("Please specify a name for this Data Adapter.");
	}


	/**
	 * This method guesses the UI to use to edit the data adapter specified
	 * @param newDataAdapter
	 */
	public void setDataAdapter(DataAdapter newDataAdapter) {

		System.out.println("parent container: " + customContainer);
		// ?
		if (newDataAdapter.getEditor() == dataAdapterEditor) return;
		
		// 1. get the DataAdapterEditor
		dataAdapterEditor = newDataAdapter.getEditor();

	  // 2. add the composite from the DataAdapterEditor to the wizard page
		if (editorComposite != null)
		{
			editorComposite.dispose();
		}
		
		editorComposite = dataAdapterEditor.getComposite(customContainer, SWT.NULL);
		
		// 4. set the new dataAdapter to the DataAdapterEditor
		dataAdapterEditor.setDataAdapter(newDataAdapter);
		
		// 5. fill the name if the new data adapter has one
		if (!newDataAdapter.getName().isEmpty())
		{
			setTextValue(newDataAdapter.getName());
		}
		
		// 6. resize the dialog properly
		customContainer.layout();
	}

	public DataAdapter getDataAdapter() {
		DataAdapter da = getDataAdapterEditor().getDataAdapter();
		da.setName(getTextValue());
		return da;
	}
	
	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp(dataAdapterEditor.getHelpContextId());
	}

	public void setTextValue(String string) {
		textName.setText(string);
	}
}
