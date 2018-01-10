/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.customadapters.ConfigurableDataAdapterFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

import net.sf.jasperreports.data.DataAdapter;

public class DataAdaptersListPage extends WizardPage {

	private List<DataAdapterFactory> dataAdapterFactories = null;

	private TableViewer tviewer;
	
	private Table wtable;
	
	/**
	 * Custom content provider that will avoid to show {@link ConfigurableDataAdapterFactory} which
	 * have a {@link DataAdapter} class that can not be resolved
	 */
	private class DataAdapterContentProvider extends ListContentProvider{
		
		@Override
		public Object[] getElements(Object inputElement) {
			List<?> elements = null;
			if (inputElement != null && inputElement instanceof List) {
				elements = new ArrayList<Object>((List<?>)inputElement);
			} else if (inputElement != null && inputElement instanceof Collection){
				elements = new ArrayList<Object>((Collection<?>) inputElement);
			}
			if (elements != null) {
				List<Object> result = new ArrayList<Object>();
				for (Iterator<?> iter = elements.iterator(); iter.hasNext(); ) {
				    Object element = iter.next();
				    if (element instanceof ConfigurableDataAdapterFactory) {
				    	ConfigurableDataAdapterFactory configurableFactory = (ConfigurableDataAdapterFactory)element;
				    	JSSWizard wizard = (JSSWizard)getWizard();
				    	JasperReportsConfiguration jConfig = wizard.getConfig();
				    	if (jConfig == null) {
				    		jConfig = JasperReportsConfiguration.getDefaultInstance();
				    	}
				    	try {
				    		//test if the class can be resolved in the current configuration
				    		configurableFactory.testExistence(jConfig);
				    		result.add(element);
				    	} catch (Exception ex) {
				    	}
				    } else {
				    	result.add(element);
				    }
				}
				return result
						.toArray();
			} else {
				return new Object[0];
			}
		}
	}

	/**
	 * Create the wizard.
	 */
	public DataAdaptersListPage() {
		super("dataAdapterslistpage"); //$NON-NLS-1$
		setTitle(Messages.DataAdaptersListPage_1);
		setDescription(Messages.DataAdaptersListPage_2);
		setPageComplete(false);
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout());

		wtable = new Table(container, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		gd.heightHint = 250;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(false);

		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(wtable, SWT.NONE);
		col[0].setText(Messages.DataAdaptersListPage_3);
		col[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		wtable.setLayout(tlayout);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new DataAdapterContentProvider());
		tviewer.setLabelProvider(new LLabelProvider());
		tviewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (canFlipToNextPage()) {
					getContainer().showPage(getNextPage());
				}
			}
		});
		wtable.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int ind = wtable.getSelectionIndex();
				if (ind >= 0 && ind < dataAdapterFactories.size())
					setMessage(dataAdapterFactories.get(ind).getDescription());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		updateFactoriesList();
		tviewer.setInput(dataAdapterFactories);
		if (dataAdapterFactories.size() > 0) {
			wtable.setSelection(0);
			setPageComplete(wtable.getSelectionCount() > 0);
			setMessage(dataAdapterFactories.get(0).getDescription());
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		tviewer.refresh();
		return (wtable.getSelectionCount() == 1) ? true : false;
	}

	protected void factorySelected(SelectionEvent e) {
		setPageComplete(wtable.getSelectionCount() > 0);
	}

	private void updateFactoriesList() {
		List<DataAdapterFactory> list = new ArrayList<DataAdapterFactory>();
		for (DataAdapterFactory daf : DataAdapterManager.getDataAdapterFactories()) {
			if (!daf.isDeprecated())
				list.add(daf);
		}
		dataAdapterFactories = list;
	}

	public DataAdapterFactory getSelectedFactory() {

		if (dataAdapterFactories == null)
			return null; // Should never be true
		if (wtable.getSelectionIndex() < 0)
			return null; // Should never be true

		return dataAdapterFactories.get(wtable.getSelectionIndex());
	}

	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp("com.jaspersoft.studio.doc.dataAdapters_wizard_list"); //$NON-NLS-1$
	}
}
