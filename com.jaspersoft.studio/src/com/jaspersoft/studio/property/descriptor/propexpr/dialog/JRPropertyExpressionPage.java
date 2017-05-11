/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

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
import org.eclipse.jface.viewers.ColumnWeightData;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.CopyElementExpressionProperty;
import com.jaspersoft.studio.model.CopyElementProperty;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionDTO;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionsDTO;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
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
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

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

	private void createButtons(Composite parent) {
		buttons = new ToolBar(parent, SWT.FLAT);

		badd = new ToolItem(buttons, SWT.PUSH);
		badd.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/plus.png"));
		badd.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				PropertyExpressionDTO v = value instanceof DatasetPropertyExpressionsDTO
						? new DatasetPropertyExpressionDTO(false, "property.name", "value", null)
						: new PropertyExpressionDTO(false, "property.name", "value");
				v.seteContext(value.geteContext());
				v.setJrElement(value.getJrElement());
				JRPropertyExpressionDialog dialog = new JRPropertyExpressionDialog(UIUtils.getShell());
				dialog.setShowExpression(showExpression);
				dialog.setValue(v);
				if (dialog.open() == Window.OK) {
					value.addProperty(v.getName(), v.getValue(), v.isExpression());
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
						buildTable(propCmp);
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
		bTbl.setToolTipText("Show properties as table or form.");
	}

	private boolean showExisting = false;
	private Text txt;
	private ToolBar buttons;
	private ScrolledComposite sc;
	private Composite cmp;
	private List<PropertyMetadata> eds;
	private List<PropertyMetadata> sortedEDS;
	private Map<String, PropertyMetadata> props = new HashMap<String, PropertyMetadata>();

	private void createFormWidgets(Composite cmp, final ScrolledComposite sc) {
		eds = HintsPropertiesList.getPropertiesMetadata(value.getJrElement(), value.geteContext());
		for (PropertyMetadata pm : eds)
			props.put(pm.getName(), pm);
		refreshWidgets();
	}

	private boolean added = true;

	protected void createProperties(String search) {
		String cat = null;
		scmp = cmp;

		for (PropertyExpressionDTO pdto : value.getProperties())
			if (!props.containsKey(pdto.getName())) {
				StandardPropertyMetadata v = new StandardPropertyMetadata();
				v.setName(pdto.getName());
				v.setValueType(String.class.getName());
				props.put(pdto.getName(), v);
				added = true;
			}
		if (added) {
			sortedEDS = new ArrayList<PropertyMetadata>(props.values());
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
			if (cmp.isDisposed())
				return;
			// if key contains {}
			if (showExisting && !value.hasProperty(pm.getName()))
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
			boolean custom = !eds.contains(props.get(pm.getName()));
			if (custom && !value.hasProperty(pm.getName()))
				continue;
			col.setLabelEditable(custom);// showExisting && custom);
			UIUtils.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					TColumnFactory.addWidget(col, scmp, value, value.geteContext().getJasperReportsConfiguration());
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

	private Composite scmp;

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
				if (cmp.isDisposed())
					return;
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

	private void buildTable(Composite parent) {
		tblCmp = new Composite(parent, SWT.NONE);
		tblCmp.setLayout(new GridLayout(2, false));
		tblCmp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tblCmp.setBackgroundMode(SWT.INHERIT_FORCE);

		table = new Table(tblCmp, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);
		table.addMouseListener(new MouseAdapter() {

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

		Composite bGroup = new Composite(tblCmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				int i = 1;
				String name = "newproperty"; //$NON-NLS-1$
				while (getName(input, name, i) == null)
					i++;
				name += "_" + i; //$NON-NLS-1$
				String defValue = "NEW_VALUE";
				PropertyExpressionDTO v = value instanceof DatasetPropertyExpressionsDTO
						? new DatasetPropertyExpressionDTO(false, name, defValue, null)
						: new PropertyExpressionDTO(false, name, defValue);
				v.seteContext(value.geteContext());
				v.setJrElement(value.getJrElement());
				JRPropertyExpressionDialog dialog = new JRPropertyExpressionDialog(propCmp.getShell());
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
		tableViewer.refresh(true);
	}

	private boolean refreshing = false;
	private boolean canceled = false;
	private String search;
	private Composite propCmp;
	private StackLayout propCmpLayout;
	private Composite tblCmp;
	private ToolItem badd;
	private ToolItem bSystem;

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
								if (cmp.isDisposed())
									return;
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
							if (txt.isDisposed())
								return;
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
}
