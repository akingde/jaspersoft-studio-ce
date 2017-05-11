/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.copy.PastableProperties;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.CopyElementExpressionProperty;
import com.jaspersoft.studio.model.CopyElementProperty;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.HintsPropertiesList;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyDialog;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class JRPropertyPage extends JSSHelpWizardPage {

	private JRPropertiesMap value;
	private List<PropertyDTO> props = new ArrayList<PropertyDTO>();
	private Map<String, PropertyMetadata> propsMap = new HashMap<String, PropertyMetadata>();

	@Override
	public void dispose() {
		// clear all properties
		JRPropertiesMap v = new JRPropertiesMap();
		for (PropertyDTO p : props)
			if (p.getName() != null && !p.getName().equals("")) //$NON-NLS-1$
				v.setProperty(p.getName(), p.getValue().toString());
		super.dispose();
	}

	public void setValue(JRPropertiesMap value) {
		this.value = value;
		if (table != null)
			fillTable();
	}

	private JasperReportsConfiguration jConfig;
	private Object jrElement;
	private ExpressionContext eContext;

	protected JRPropertyPage(String pageName, JasperReportsConfiguration jConfig, Object jrElement) {
		super(pageName);
		setTitle(Messages.common_properties);
		setDescription(Messages.JRPropertyPage_description);
		this.jConfig = jConfig;
		eContext = new ExpressionContext(jConfig);
		this.jrElement = jrElement;
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_PROPERTIES;
	}

	public void createControl(Composite parent) {
		getShell().addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_RETURN)
					e.doit = false;
			}
		});
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		setControl(composite);

		createButtons(composite);

		txt = new Text(composite, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.setMessage("Search property");
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!Misc.isNullOrEmpty(search) && txt.getText().trim().equalsIgnoreCase(search.trim()))
					return;
				refreshWidgets();
			}
		});
		txt.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				if (!Misc.isNullOrEmpty(search) && txt.getText().trim().equalsIgnoreCase(search.trim()))
					return;
				refreshWidgets();
			}
		});

		createButtonsTable(composite);

		propCmp = new Composite(composite, SWT.NONE);
		propCmpLayout = new StackLayout();
		propCmp.setLayout(propCmpLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.heightHint = 500;
		propCmp.setLayoutData(gd);

		sc = new ScrolledComposite(propCmp, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setAlwaysShowScrollBars(true);

		cmp = new Composite(sc, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cmp.setBackgroundMode(SWT.INHERIT_FORCE);

		sc.setContent(cmp);
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				txt.setFocus();
				createFormWidgets(cmp, sc);
			}
		});

		sc.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				sc.setMinSize(cmp.computeSize(sc.getClientArea().width, SWT.DEFAULT));
			}
		});
		propCmpLayout.topControl = sc;
	}

	private void createFormWidgets(Composite cmp, final ScrolledComposite sc) {
		eds = HintsPropertiesList.getPropertiesMetadata(jrElement, eContext);
		for (PropertyMetadata pm : eds)
			propsMap.put(pm.getName(), pm);
		refreshWidgets();
	}

	private boolean added = true;

	protected void createProperties(String search) {
		String cat = null;
		scmp = cmp;

		for (PropertyDTO pdto : props)
			if (!propsMap.containsKey(pdto.getName())) {
				StandardPropertyMetadata v = new StandardPropertyMetadata();
				v.setName(pdto.getName());
				v.setValueType(String.class.getName());
				propsMap.put(pdto.getName(), v);
				added = true;
			}
		if (added) {
			sortedEDS = new ArrayList<PropertyMetadata>(propsMap.values());
			Collections.sort(sortedEDS, new Comparator<PropertyMetadata>() {
				private NullComparator nc = new NullComparator(true);

				@Override
				public int compare(PropertyMetadata o1, PropertyMetadata o2) {
					int i = nc.compare(o1.getCategory(), o2.getCategory());
					if (i != 0)
						return i;
					if (o1.getCategory() != null && o2.getCategory() != null) {
						i = o1.getCategory().compareTo(o2.getCategory());
						if (i != 0)
							return i;
					}
					return o1.getName().compareTo(o2.getName());
				}
			});
			added = false;
		}

		for (final PropertyMetadata pm : sortedEDS) {
			if (canceled)
				return;
			// if key contains {}
			if (showExisting && !containsProperty(pm.getName()))
				continue;
			String c = pm.getCategory();
			if (c != null && c.indexOf(":") >= 0)
				c = c.substring(c.indexOf(":") + 1);
			if (c == null)
				c = "Miscellaneous";
			if (!Misc.isNullOrEmpty(search) && !(pm.getName().toLowerCase().trim().contains(search)
					|| (pm.getLabel() != null && pm.getLabel().toLowerCase().trim().contains(search))
					|| c.toLowerCase().trim().contains(search)))
				continue;
			if (!StringUtils.equals(c, cat)) {
				cat = c;
				buildSection(c);
			}
			if (scmp == cmp)
				buildSectionComposite();
			final TColumn col = TColumnFactory.getTColumn(pm);
			boolean custom = !eds.contains(propsMap.get(pm.getName()));
			if (custom && !containsProperty(pm.getName()))
				continue;
			col.setLabelEditable(showExisting && custom);
			UIUtils.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					TColumnFactory.addWidget(col, scmp, value, jConfig);
				}
			});
		}
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				if (cmp.getChildren().length == 0) {
					Label lbl = new Label(cmp, SWT.CENTER);
					lbl.setText("Search result is empty");
					GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL);
					gd.horizontalSpan = 2;
					lbl.setLayoutData(gd);
				}
			}
		});
	}

	private boolean refreshing = false;
	private boolean canceled = false;
	private String search;
	private Composite propCmp;
	private StackLayout propCmpLayout;
	private Composite tblCmp;
	private Text txt;
	private ToolBar buttons;
	private ScrolledComposite sc;
	private Composite cmp;
	private boolean showExisting = false;
	private List<PropertyMetadata> eds;
	private List<PropertyMetadata> sortedEDS;

	private void createButtonsTable(Composite parent) {
		buttons = new ToolBar(parent, SWT.FLAT);

		final ToolItem bTbl = new ToolItem(buttons, SWT.PUSH);
		bTbl.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/properties_view.gif"));
		bTbl.addListener(SWT.Selection, new Listener() {
			private boolean table = false;

			@Override
			public void handleEvent(Event event) {
				if (!table) {
					// create table if not created
					if (tblCmp == null)
						createTable(propCmp);
					propCmpLayout.topControl = tblCmp;
					propCmp.layout(true);
					// switch layout
					bTbl.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/ui-scroll-pane-form.png"));
					fillTable();
				} else {
					bTbl.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/properties_view.gif"));
					propCmpLayout.topControl = sc;
					propCmp.layout(true);
					refreshWidgets();
				}
				badd.setEnabled(table);
				bSystem.setEnabled(table);
				txt.setEnabled(table);
				table = !table;
			}

		});
		badd.setToolTipText("Show properties as table or form.");
	}

	private void createButtons(Composite parent) {
		buttons = new ToolBar(parent, SWT.FLAT);

		badd = new ToolItem(buttons, SWT.PUSH);
		badd.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/plus.png"));
		badd.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				PropertyDTO p = new PropertyDTO(getPropertyName(), "NEW_VALUE");
				p.seteContext(eContext);
				p.setJrElement(jrElement);
				JRPropertyDialog dialog = new JRPropertyDialog(UIUtils.getShell());
				dialog.setValue(p);
				if (dialog.open() == Window.OK) {
					value.setProperty(p.getName(), p.getValue());
					props.add(p);
					refreshWidgets();
				}
			}

		});
		badd.setToolTipText("Add property.");

		bSystem = new ToolItem(buttons, SWT.CHECK);
		bSystem.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/jrxml_icon.png"));
		bSystem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				showExisting = bSystem.getSelection();
				refreshWidgets();
			}

		});
		bSystem.setToolTipText("Show only existing properties in the report template.");
		bSystem.setSelection(false);
	}

	private Table table;
	private TableViewer tableViewer;
	private Button editButton;
	private Button deleteButton;

	protected void createTable(Composite parent) {
		tblCmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		tblCmp.setLayout(layout);
		buildTable(tblCmp);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 400;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(tblCmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		// CREATE THE ADD BUTTON

		Button addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				PropertyDTO p = new PropertyDTO(getPropertyName(), "NEW_VALUE");
				p.seteContext(eContext);
				p.setJrElement(jrElement);
				JRPropertyDialog dialog = new JRPropertyDialog(UIUtils.getShell());
				dialog.setValue(p);
				if (dialog.open() == Window.OK) {
					props.add(p);
					tableViewer.refresh();
				}
			}
		});

		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				if (selection.size() > 0) {
					PropertyDTO selectedValue = (PropertyDTO) selection.getFirstElement();
					editElement(selectedValue);
				}
			}
		});
		editButton.setEnabled(false);

		// CREATE THE DELETE BUTTON

		deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				if (selection.size() > 0) {
					PropertyDTO selectedValue = (PropertyDTO) selection.getFirstElement();
					int index = props.indexOf(selectedValue);
					props.remove(index);
					tableViewer.refresh();
				}
			}
		});

		deleteButton.setEnabled(false);

		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}

	private String getPropertyName() {
		String name = "newproperty";
		int i = 0;
		while (nameExist(name)) {
			name = "newproperty_" + i;
			i++;
		}
		return name;
	}

	private boolean nameExist(String name) {
		for (PropertyDTO prm : props)
			if (prm.getName() != null && prm.getName().trim().equals(name))
				return true;
		return false;
	}

	/**
	 * Edit an element opened a dialog to allow to modify it
	 * 
	 * @param edited
	 *          the element to edit, must be not null
	 */
	private void editElement(PropertyDTO edited) {
		PropertyDTO result = edited.clone();
		JRPropertyDialog inputDialog = new JRPropertyDialog(UIUtils.getShell());
		inputDialog.setValue(result);
		if (inputDialog.open() == Dialog.OK) {
			int index = props.indexOf(edited);
			props.set(index, result);
			tableViewer.refresh();
		}
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TPropertyLabelProvider());

		tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					PropertyDTO selectedElement = props.get(selectedIndex);
					editElement(selectedElement);
				}
			}
		});

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectedIndex = table.getSelectionIndex();
				boolean buttonEnabled = selectedIndex != -1;
				editButton.setEnabled(buttonEnabled);
				deleteButton.setEnabled(buttonEnabled);
			}
		});

		tableViewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.JRPropertyPage_value);

		fillTable();
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, true));
		tlayout.addColumnData(new ColumnWeightData(50, true));
		table.setLayout(tlayout);
		// Set the help for the elements
		TableHelpListener.setTableHelp(table);

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
					if (!property.isExpression() && !containsProperty(property.getPropertyName())) {
						props.add(new PropertyDTO(property.getPropertyName(), property.getValue()));
					}
				}
				tableViewer.refresh();
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
					pasteEnabled = copiedProperties != null && !copiedProperties.isEmpty() && canPaste(copiedProperties);
				}
				pasteItem.setEnabled(pasteEnabled);
			}

			@Override
			public void menuHidden(MenuEvent e) {
			}
		});

		table.setMenu(tableMenu);
	}

	private void fillTable() {
		props.clear();
		String[] names = value.getPropertyNames();
		for (int i = 0; i < names.length; i++)
			props.add(new PropertyDTO(names[i], value.getProperty(names[i])));
		tableViewer.setInput(props);
	}

	private boolean containsProperty(String propertyName) {
		for (PropertyDTO property : props) {
			if (property.getName().equals(propertyName))
				return true;
		}
		return false;
	}

	/**
	 * Check if at least one of the copied properties can be pasted on the current element
	 * 
	 * @param copiedProperties
	 *          the copied properties
	 * @return true if at least one of the copied properties can be pasted, false otherwise
	 */
	private boolean canPaste(List<CopyElementExpressionProperty> copiedProperties) {
		for (CopyElementExpressionProperty property : copiedProperties) {
			if (!property.isExpression() && !containsProperty(property.getPropertyName())) {
				return true;
			}
		}
		return false;
	}

	public JRPropertiesMap getValue() {
		return value;
	}

	protected void refreshWidgets() {
		if (refreshing) {
			canceled = true;
			return;
		}
		refreshing = true;

		Job job = new Job("refresh widgets") {

			protected IStatus run(IProgressMonitor monitor) {
				final boolean ex = showExisting;
				try {
					UIUtils.getDisplay().syncExec(new Runnable() {

						@Override
						public void run() {
							search = txt.getText();
							for (Control c : cmp.getChildren()) {
								if (c == txt || c == buttons)
									continue;
								c.dispose();
							}
						}
					});

					canceled = false;
					createProperties(search.trim().toLowerCase());
					if (!canceled) {
						UIUtils.getDisplay().syncExec(new Runnable() {

							@Override
							public void run() {
								sc.setMinSize(cmp.computeSize(sc.getClientArea().width, SWT.DEFAULT));
								cmp.layout(true);
							}
						});
					}
				} finally {
					refreshing = false;
					UIUtils.getDisplay().syncExec(new Runnable() {

						@Override
						public void run() {
							if (!search.equals(txt.getText()) || ex != showExisting)
								refreshWidgets();
						}
					});
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	private Composite scmp;
	private ToolItem badd;
	private ToolItem bSystem;

	private void buildSectionComposite() {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				scmp = new Composite(cmp, SWT.NONE);
				scmp.setLayout(new GridLayout(2, false));
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.horizontalSpan = 2;
				scmp.setLayoutData(gd);
			}
		});
	}

	private void buildSection(final String cat) {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				if (scmp != null) {
					cmp.layout(true);
					sc.setMinSize(cmp.computeSize(sc.getClientArea().width, SWT.DEFAULT));
				}
				scmp = new Composite(cmp, SWT.NONE);
				scmp.setLayout(new GridLayout(2, false));
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.horizontalSpan = 2;
				scmp.setLayoutData(gd);

				Label lbl = new Label(scmp, SWT.NONE);
				String gn = WordUtils.capitalizeFully(cat.replace(".", " "));
				gn = gn.replaceAll("Jasperreports", "JasperReports");
				lbl.setText(gn);
				lbl.setFont(ResourceManager.getBoldFont(lbl.getFont()));
				gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.horizontalSpan = 2;
				lbl.setLayoutData(gd);

				lbl = new Label(scmp, SWT.SEPARATOR | SWT.HORIZONTAL);
				gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.horizontalSpan = 2;
				lbl.setLayoutData(gd);
			}
		});
	}
}
