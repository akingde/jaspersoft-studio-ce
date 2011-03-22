package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.utils.SWTResourceManager;


public class DataAdapterEditorPage extends WizardPage {

	Composite mainContainer = null;
	Composite staticContainer = null;
	Composite customContainer = null;
	DataAdapterEditor dataAdapterEditor = null;
	Composite editorComposite = null;
	private Text textName;
	private String subTitle="";
	
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
		setTitle("Data Adapter");
		setDescription("Edit your Data Adapter");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {

		mainContainer = new Composite(parent, SWT.NONE);
		setControl(mainContainer);
		mainContainer.setLayout(new GridLayout(1, false));
		
		staticContainer = new Composite(mainContainer, SWT.NONE);
		staticContainer.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
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

		
		customContainer = new Composite(mainContainer, SWT.NONE);
		customContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		customContainer.setLayout(new GridLayout(1, false));
		
		textName.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				boolean b = true;
				if(textName.getText() == "") b = false;
				setPageComplete(b);
				if (b) { 
					setDescription(getSubTitle() );
					setMessage( getSubTitle() );
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

		
		// ?
		if (newDataAdapter.getEditor() == dataAdapterEditor) return;
		
		setSubTitle(DataAdapterManager.findFactoryByDataAdapterClass(newDataAdapter.getClass().getName()).getDescription() );
		// 1. get the DataAdapterEditor
		dataAdapterEditor = newDataAdapter.getEditor();

	  // 2. add the composite from the DataAdapterEditor to the wizard page
		if (editorComposite != null)
		{
			editorComposite.dispose();
		}
		
		editorComposite = dataAdapterEditor.getComposite(customContainer, SWT.NULL);
		editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// 4. set the new dataAdapter to the DataAdapterEditor
		dataAdapterEditor.setDataAdapter(newDataAdapter);
		
		// 5. fill the name if the new data adapter has one
		if (!newDataAdapter.getName().isEmpty())
		{
			setTextValue(newDataAdapter.getName());
		}
		
		// 6. resize the dialog properly
		customContainer.layout();

		if (getShell().isVisible()) // If the shell is not yet visible, it will layout the content by itself when displayed.
		{
			Point currentSize = customContainer.getSize();
			Point preferredSize = customContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		
			Point windowSize = getShell().getSize();
			getShell().layout();
			
			getShell()
						.setSize(
								new Point(windowSize.x, windowSize.y + Math.max(0, preferredSize.y - currentSize.y)));
		}
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

	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}
}
