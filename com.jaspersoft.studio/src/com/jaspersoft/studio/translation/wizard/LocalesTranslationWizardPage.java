/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.translation.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.essiembre.eclipse.rbe.ui.widgets.LocaleSelector;
import com.jaspersoft.studio.ConfigurationPathProvider;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.translation.ImageLocale;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class LocalesTranslationWizardPage extends JSSHelpWizardPage {

	protected LocalesTranslationWizardPage() {
		super("Define the destination and the locales provided");
	}


	public static final String DEFAULT_LOCALE = "[default]";

	private Text filePath;

	private Button addButton;

	private Button removeButton;

	private Table bundleLocalesList;

	private FlagLocaleSelector localeSelector;
	
	private String destinationPath = "";
	
	private List<ImageLocale> selectedLanguages = new ArrayList<ImageLocale>();

	private String getPluginsFolder() {
		String separator =  System.getProperty("file.separator");
		String path = ConfigurationPathProvider.getPath();
		File destination = new File(path).getParentFile();
		destination = new File(destination.getAbsolutePath() + separator + "droping" + separator + "eclipse" + "plugins");
		if (destination.exists()) return destination.getAbsolutePath();
		else return "";
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		createLocalesSection(container);
		
		createPathSection(container);
		
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	private void createPathSection(Composite parent){
		Composite container = new Composite(parent,SWT.NONE);
		container.setLayout(new GridLayout(3,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setText("Destination Path");
		
		filePath = new Text(container, SWT.BORDER);
		filePath.setText(getPluginsFolder());
		filePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filePath.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		Button browseButton = new Button(container, SWT.NONE);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        DirectoryDialog dlg = new DirectoryDialog(UIUtils.getShell());

        // Set the initial filter path according
        // to anything they've selected or typed in
        dlg.setFilterPath(filePath.getText());

        // Change the title bar text
        dlg.setText("Select the destination directory");

        // Customizable message displayed in the dialog
        dlg.setMessage("Directory where the plugins will be placed");

        // Calling open() will open and run the dialog.
        // It will return the selected directory, or
        // null if user cancels
        String dir = dlg.open();
        if (dir != null) {
          // Set the text box to the new selection
        	filePath.setText(dir);
        }
      }
    });
	}
	
	@Override
	public IWizardPage getNextPage() {
		destinationPath = filePath.getText();
		selectedLanguages = new ArrayList<ImageLocale>();
		for(TableItem item : bundleLocalesList.getItems()){
			ImageLocale exportedLocale = new ImageLocale((Locale)item.getData(), item.getImage());
			selectedLanguages.add(exportedLocale);
		}
		return super.getNextPage();
	}
	
	public String getDestinationPath(){
		return destinationPath;
	}
	
	public List<ImageLocale> getSelectedLanguages(){
		return selectedLanguages;
	}
	
	/**
	 * Creates the bottom part of this wizard, which is the locales to add.
	 * 
	 * @param parent
	 *            parent container
	 */
	private void createLocalesSection(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		// Available locales
		createBottomAvailableLocalesComposite(container);

		// Buttons
		createBottomButtonsComposite(container);

		// Selected locales
		createBottomSelectedLocalesComposite(container);
	}
	
	/**
	 * Creates the bottom part of this wizard where selected locales are stored.
	 * 
	 * @param parent
	 *            parent container
	 */
	private void createBottomSelectedLocalesComposite(Composite parent) {

		// Selected locales Group
		Group selectedGroup = new Group(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout = new GridLayout();
		layout.numColumns = 1;
		selectedGroup.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		selectedGroup.setLayoutData(gd);
		selectedGroup.setText("Selected locales"); //$NON-NLS-1$
		bundleLocalesList = new Table(selectedGroup, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		bundleLocalesList.setLayoutData(gd);
		bundleLocalesList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				removeButton.setEnabled(bundleLocalesList.getSelectionIndices().length != 0);
				setAddButtonState();
			}
		});
	}

	/**
	 * Creates the bottom part of this wizard where buttons to add/remove
	 * locales are located.
	 * 
	 * @param parent
	 *            parent container
	 */
	private void createBottomButtonsComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;
		
		addButton = new Button(container, SWT.NULL);
		addButton.setText("Add   -->"); //$NON-NLS-1$
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TableItem item = new TableItem(bundleLocalesList, SWT.NONE);
				item.setText(getSelectedLocaleAsString());
				item.setData(localeSelector.getSelectedLocale());
				item.setImage(localeSelector.getActualImage());
				setAddButtonState();
				dialogChanged();
			}
		});

		removeButton = new Button(container, SWT.NULL);
		removeButton.setText("<-- Remove"); //$NON-NLS-1$
		removeButton.setEnabled(false);
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				bundleLocalesList.remove(bundleLocalesList
						.getSelectionIndices());
				removeButton.setEnabled(false);
				setAddButtonState();
				dialogChanged();
			}
		});
	}

	/**
	 * Ensures that both text fields are set.
	 */
	protected void dialogChanged() {
		String fileName = filePath.getText();
		if (fileName.length() == 0 || !(new File(fileName).exists())) {
			updateStatus("The selected folder dosen't exist", IMessageProvider.ERROR); //$NON-NLS-1$
			return;
		}
		if (bundleLocalesList.getItems().length==0) {
			updateStatus("You must select at least an exported language", IMessageProvider.ERROR);  //$NON-NLS-1$
			return;
		}
		updateStatus(null, IMessageProvider.NONE);
	}
	
	protected void updateStatus(String message, int messageType) {
		setMessage(message, messageType);
		setPageComplete(messageType != IMessageProvider.ERROR);
	}
	
	
	/**
	 * Sets the "add" button state.
	 */
	protected void setAddButtonState() {
		int index = -1;
		for(int i=0; i<bundleLocalesList.getItemCount() && index == -1; i++){
			if (bundleLocalesList.getItem(i).getText().equals(getSelectedLocaleAsString())) index = i;
		}
		addButton.setEnabled(index == -1);
	}
	
	/**
	 * Gets a string representation of selected locale.
	 * 
	 * @return string representation of selected locale
	 */
	public String getSelectedLocaleAsString() {
		Locale selectedLocale = localeSelector.getSelectedLocale();
		if (selectedLocale != null) {
			return selectedLocale.toString();
		}
		return DEFAULT_LOCALE;
	}

	
	private class FlagLocaleSelector extends LocaleSelector{

		private Label flagImage;
		
		private Button changeFlagImage;
		
		private String actualLocaleImage = null;
		
		public FlagLocaleSelector(Composite parent) {
			super(parent);
			selectionGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
			Composite flagComposite = new Composite(selectionGroup, SWT.NONE);
			flagComposite.setLayout(new GridLayout(2,false));
			flagComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1));
			
			flagImage = new Label(flagComposite, SWT.NONE);
			
			changeFlagImage = new Button(flagComposite, SWT.NONE);
			changeFlagImage.setText("Set Flag Icon");
			changeFlagImage.addSelectionListener(new SelectionAdapter(){
				
				@Override
				public void widgetSelected(SelectionEvent e) {
		        FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN);
		        fd.setText("Save");
		        String[] filterExt = { "*.jpg", "*.png", ".gif" };
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null){
		        	try {
		        		Image loadedImage = new Image(null, new FileInputStream(new File(selected)));
		        		int width = loadedImage.getImageData().width;
		        		int height = loadedImage.getImageData().height;
		        		if (width > 16){
		        			int scaleFactor = width/16;
		        			width = width/scaleFactor;
		        			height = height / scaleFactor;
		        		}
		        		if (height > 11){
		        			int scaleFactor = height/11;
		        			width = width/scaleFactor;
		        			height = height / scaleFactor;
		        		}
		        		if (width != loadedImage.getImageData().width || height != loadedImage.getImageData().height){
		        			loadedImage = ImageUtils.resize(loadedImage, width, height);
		        		}
		        		
								flagImage.setImage(loadedImage);
								changeFlagImage.setText("Change flag image");
								flagImage.getParent().layout(true,true);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
		        }
				}
				
			});
			
		}
		
		public void updateImage(Image image, String actualLocale){
			flagImage.setImage(image);
			actualLocaleImage = actualLocale;
			if (image == null) changeFlagImage.setText("Set flag image");
			else changeFlagImage.setText("Change flag image");
			flagImage.getParent().layout(true,true);
		}
		
		public Image getActualImage(){
			return flagImage.getImage();
		}
		
		public String getLangText(){
			return langText.getText();
		}
		
		public String getCountryText(){
			return countryText.getText();
		}
		
		public String getActualLocaleImage(){
			return actualLocaleImage;
		}
		
	}

	/**
	 * Creates the bottom part of this wizard where locales can be chosen or
	 * created
	 * 
	 * @param parent
	 *            parent container
	 */
	private void createBottomAvailableLocalesComposite(Composite parent) {
		localeSelector = new FlagLocaleSelector(parent);
		localeSelector.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		localeSelector.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setAddButtonState();
				
				String newLocaleLang = localeSelector.getLangText();
				String newLocaleCountry = localeSelector.getCountryText();
				String actualLocale = localeSelector.getActualLocaleImage();
				if (!(newLocaleLang.equals(actualLocale) || newLocaleCountry.equals(actualLocale))){
					ImageDescriptor descriptor = JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/flags/"+newLocaleLang+".png");
					actualLocale = newLocaleLang;
					if (descriptor == null) {
						descriptor = JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/flags/"+newLocaleCountry+".png");
						actualLocale = newLocaleCountry;
					}
					if (descriptor == null) localeSelector.updateImage(null, actualLocale);
					else localeSelector.updateImage(new Image(null, descriptor.getImageData()),actualLocale);
				}
			}
		});
	}

}
