/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.editor.action.copy.PastableProperties;
import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.CopyElementExpressionProperty;
import com.jaspersoft.studio.model.CopyElementProperty;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionDTO;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionsDTO;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;
import com.jaspersoft.studio.property.descriptor.properties.dialog.TPropertyLabelProvider;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.properties.PropertyMetadata;

public class JRPropertyExpressionPage extends JSSHelpWizardPage {
	private boolean showExpression = true;

	public void setShowExpression(boolean showExpression) {
		this.showExpression = showExpression;
	}

	private final class EditElement implements IEditElement<PropertyDTO> {
		@Override
		public void editElement(List<PropertyDTO> input, int pos) {
			PropertyDTO v = (PropertyDTO) input.get(pos);
			if (v == null)
				return;
			JRPropertyExpressionDialog dialog = new JRPropertyExpressionDialog(UIUtils.getShell());
			// the edited value must be a clone, otherwise changes done in the dialog
			// will be propagated even if the cancel button is pressed
			PropertyDTO editedValue = v.clone();
			dialog.setShowExpression(showExpression);
			dialog.setValue(editedValue);
			if (dialog.open() == Window.OK)
				input.set(pos, editedValue);
		}
	}

	private PropertyExpressionsDTO value;
	private Table table;
	private TableViewer tableViewer;
	private EditButton<PropertyDTO> editButton;

	public PropertyExpressionsDTO getValue() {
		return value;
	}

	public void setValue(PropertyExpressionsDTO value) {
		this.value = value;
		if (table != null)
			fillTable();
	}

	protected JRPropertyExpressionPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_properties);
		setDescription(Messages.JRPropertyPage_description);
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_PROPERTIES;
	}

	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		setControl(composite);

		final CTabFolder tabFolder = new CTabFolder(composite, SWT.BOTTOM);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		tabFolder.setLayoutData(gd);

		buildTable(tabFolder);
		buildForm(tabFolder);

		tabFolder.setSelection(0);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tabFolder.getSelectionIndex() == 0)
					fillTable();
			}
		});
	}

	private void buildForm(final CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Form");

		final ScrolledComposite sc = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		bptab.setControl(sc);

		final Composite cmp = new Composite(sc, SWT.NONE);
		cmp.setLayout(new GridLayout());
		cmp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cmp.setBackgroundMode(SWT.INHERIT_FORCE);

		sc.setContent(cmp);
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				createFormWidgets(cmp, sc);
			}
		});

		sc.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				sc.setMinSize(cmp.computeSize(sc.getClientArea().width, SWT.DEFAULT));
			}
		});
	}

	private void createFormWidgets(Composite cmp, final ScrolledComposite sc) {
		long stime = System.currentTimeMillis();
		List<PropertyMetadata> eds = HintsPropertiesList.getPropertiesMetadata(value.getJrElement(), value.geteContext());

		System.out.println("Read metadata: " + (System.currentTimeMillis() - stime));
		stime = System.currentTimeMillis();

		Collections.sort(eds, new Comparator<PropertyMetadata>() {

			@Override
			public int compare(PropertyMetadata o1, PropertyMetadata o2) {
				if (o1.getCategory() != null)
					return o1.getCategory().compareTo(o2.getCategory());
				return o1.getName().compareTo(o2.getName());
			}
		});
		System.out.println("Sort metadata: " + (System.currentTimeMillis() - stime));
		stime = System.currentTimeMillis();

		String cat = null;
		Composite scmp = cmp;
		for (PropertyMetadata pm : eds) {
			if ((cat == null && pm.getCategory() != null) || (cat != null && !cat.equals(pm.getCategory()))) {
				cat = pm.getCategory();

				Section ec = new Section(cmp, Section.TREE_NODE);
				ec.setText(StringUtils.capitalize(cat.substring(cat.indexOf(":") + 1)));
				ec.setExpanded(true);
				ec.setFont(ResourceManager.getBoldFont(ec.getFont()));

				Label lbl = new Label(ec, SWT.SEPARATOR | SWT.HORIZONTAL);
				ec.setSeparatorControl(lbl);
				ec.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

				ec.addExpansionListener(new ExpansionAdapter() {
					@Override
					public void expansionStateChanged(ExpansionEvent e) {
						sc.setMinSize(sc.getContent().computeSize(SWT.DEFAULT, SWT.DEFAULT));
					}
				});

				scmp = new Composite(ec, SWT.NONE);
				scmp.setLayout(new GridLayout(2, false));
				ec.setClient(scmp);
			}

			TColumnFactory.addWidget(TColumnFactory.getTColumn(pm), scmp, value,
					value.geteContext().getJasperReportsConfiguration());
		}

		System.out.println("Widgets created [" + eds.size() + "]: " + (System.currentTimeMillis() - stime));
		stime = System.currentTimeMillis();
	}

	private void buildTable(final CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Table");

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		bptab.setControl(cmp);
		cmp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cmp.setBackgroundMode(SWT.INHERIT_FORCE);

		table = new Table(cmp, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				editButton.push();
			}
		});
		// set the help for the elements inside the table
		TableHelpListener.setTableHelp(table);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TPropertyLabelProvider());

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.JRPropertyPage_value);

		fillTable();
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, true));
		tlayout.addColumnData(new ColumnWeightData(50, true));
		table.setLayout(tlayout);

		// Crete the popup menu
		createPopoupMenu();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 400;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				int i = 1;
				String name = "newproperty"; //$NON-NLS-1$
				while (getName(input, name, i) == null)
					i++;
				name += "_" + i; //$NON-NLS-1$
				PropertyExpressionDTO v = value instanceof DatasetPropertyExpressionsDTO
						? new DatasetPropertyExpressionDTO(false, name, "NEW_VALUE", null)
						: new PropertyExpressionDTO(false, name, "NEW_VALUE");
				v.seteContext(value.geteContext());
				v.setJrElement(value.getJrElement());
				JRPropertyExpressionDialog dialog = new JRPropertyExpressionDialog(tabFolder.getShell());
				dialog.setShowExpression(showExpression);
				dialog.setValue(v);
				if (dialog.open() == Window.OK)
					return v;
				return null;
			}

			private String getName(List<?> input, String name, int i) {
				name += "_" + i;
				for (Object dto : input) {
					PropertyDTO prm = (PropertyDTO) dto;
					if (prm.getName() != null && prm.getName().trim().equals(name)) {
						return null;
					}
				}
				return name;
			}
		});

		editButton = new EditButton<PropertyDTO>();
		editButton.createEditButtons(bGroup, tableViewer, new EditElement());
		new DeleteButton().createDeleteButton(bGroup, tableViewer);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}

	private void createPopoupMenu() {
		Menu tableMenu = new Menu(table);
		final MenuItem copyItem = new MenuItem(tableMenu, SWT.NONE);
		copyItem.setText(Messages.common_copy);
		copyItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				List<ICopyable> copyList = new ArrayList<ICopyable>();
				for (Object selected : selection.toList()) {
					PropertyDTO prop = (PropertyDTO) selected;
					if (prop.isExpression()) {
						copyList.add(new CopyElementExpressionProperty(prop.getName(), prop.getValue()));
					} else {
						copyList.add(new CopyElementProperty(prop.getName(), prop.getValue()));
					}
				}
				// set the container inside the clipboard
				if (!copyList.isEmpty()) {
					PastableProperties container = new PastableProperties(copyList);
					Clipboard.getDefault().setContents(container);
				}
			}

		});

		final MenuItem pasteItem = new MenuItem(tableMenu, SWT.NONE);
		pasteItem.setText(Messages.common_paste);
		pasteItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				PastableProperties pasteContainer = (PastableProperties) Clipboard.getDefault().getContents();
				List<CopyElementExpressionProperty> copiedProperties = pasteContainer.getCopiedProperties();
				for (CopyElementExpressionProperty property : copiedProperties) {
					if (!value.hasProperty(property.getPropertyName(), property.isExpression())) {
						value.addProperty(property.getPropertyName(), property.getValue(), property.isExpression());
					} else {
						value.setProperty(property.getPropertyName(), property.getValue(), property.isExpression());
					}
				}
				tableViewer.setInput(value.getProperties());
			}

		});

		tableMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuShown(MenuEvent e) {
				copyItem.setEnabled(!tableViewer.getSelection().isEmpty());
				boolean pasteEnabled = false;
				if (Clipboard.getDefault().getContents() instanceof PastableProperties) {
					PastableProperties pasteContainer = (PastableProperties) Clipboard.getDefault().getContents();
					List<CopyElementExpressionProperty> copiedProperties = pasteContainer.getCopiedProperties();
					pasteEnabled = canPaste(copiedProperties);
				}
				pasteItem.setEnabled(pasteEnabled);
			}

			@Override
			public void menuHidden(MenuEvent e) {
			}
		});

		table.setMenu(tableMenu);
	}

	/**
	 * Check if at least one of the copied properties can be pasted on the current element
	 * 
	 * @param copiedProperties
	 *          the copied properties
	 * @return true if at least one of the copied properties can be pasted, false otherwise
	 */
	private boolean canPaste(List<CopyElementExpressionProperty> copiedProperties) {
		return copiedProperties != null && !copiedProperties.isEmpty();
	}

	private void fillTable() {
		tableViewer.setInput(value.getProperties());
	}
}
