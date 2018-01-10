/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;

/**
 * List field editor to edit the JSS properties. The properties are shown as key and value inside an editable table
 * 
 * @author Orlandin Marco
 * 
 */
public class PropertyListFieldEditor extends FieldEditor {

	/**
	 * The table widget
	 */
	protected Table table;

	/**
	 * The viewer for the table
	 */
	protected TableViewer viewer;

	/**
	 * The input of the viewer
	 */
	protected List<Pair> input;

	/**
	 * The button box containing the Add, Remove, edit and clone buttons
	 */
	private Composite buttonBox;

	/**
	 * The Add button.
	 */
	protected Button addButton;

	/**
	 * The Duplicate button.
	 */
	protected Button duplicateButton;

	/**
	 * The Remove button.
	 */
	protected Button removeButton;

	/**
	 * The edit button
	 */
	protected Button editButton;

	/**
	 * The selection listener.
	 */
	protected SelectionListener selectionListener;

	/**
	 * The columns width
	 */
	private final int[] columnWidths;

	/**
	 * The columns names
	 */
	private final String[] columnNames;

	/**
	 * Simple container to store a key and a value String. Used to provide content to the table
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	static public class Pair {

		/**
		 * A string representing a key, shown on the left column of the table.
		 */
		private String key;

		/**
		 * The value of the key, shown on the right column of the table
		 */
		private String value;

		/**
		 * Create a new container for a key and a value
		 * 
		 */
		public Pair(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			Pair compared = (Pair) obj;
			return key.equals(compared.getKey()) && value.equals(compared.getValue());
		}
	}

	/**
	 * Label provider for the Pair class. Simply return the key of the pair for the left column and the value for the
	 * right one
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class PairLableProvider extends LabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (columnIndex == 0) {
				return ((Pair) element).getKey();
			} else if (columnIndex == 1) {
				return ((Pair) element).getValue();
			}
			return null;
		}

	}

	/**
	 * Dialog to edit or add a new properties
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	protected class PEditDialog extends PersistentLocationDialog {

		/**
		 * The name of the parameter
		 */
		private String pname;

		/**
		 * The value of the parameter
		 */
		private String pvalue;

		/**
		 * Build the dialog with empty value, constructor used to create a new property
		 * 
		 * @param parentShell
		 *          the shell to open the dialog
		 */
		protected PEditDialog(Shell parentShell) {
			this(parentShell, null, null);
		}

		/**
		 * Open the dialog with prefilled name\value fileds, used to edit an existing parameter
		 * 
		 * @param parentShell
		 *          the shell to create the dialog
		 * @param pname
		 *          the name of the edited parameter
		 * @param pvalue
		 *          the value of the edit parameter
		 */
		protected PEditDialog(Shell parentShell, String pname, String pvalue) {
			super(parentShell);
			this.pname = pname;
			this.pvalue = pvalue;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite composite = (Composite) super.createDialogArea(parent);
			composite.setLayout(new GridLayout(2, false));
			Label label = new Label(composite, SWT.NONE);
			label.setText(Messages.PropertyListFieldEditor_newPropertyName);

			final Text text = new Text(composite, SWT.BORDER);
			text.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
			text.setText(Misc.nvl(pname, "net.sf.jasperreports.")); //$NON-NLS-1$
			text.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					pname = text.getText();
				}
			});

			label = new Label(composite, SWT.NONE);
			label.setText(Messages.PropertyListFieldEditor_newPropertyValue);

			final Text tname = new Text(composite, SWT.BORDER);
			tname.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
			tname.setText(Misc.nvl(pvalue, Messages.PropertyListFieldEditor_exampleValue));
			tname.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					pvalue = tname.getText();
				}
			});
			applyDialogFont(composite);
			return composite;
		}

		@Override
		protected boolean isResizable() {
			return true;
		}

		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setSize(500, 200);
			newShell.setText(Messages.PropertyListFieldEditor_newPropertyTitle);
		}

		/**
		 * Return the current value of the parameter name
		 * 
		 * @return a not null string
		 */
		public String getPName() {
			return this.pname;
		}

		/**
		 * Return the current value of the parameter value
		 * 
		 * @return a not null string
		 */
		public String getPValue() {
			return this.pvalue;
		}

	}

	/**
	 * Build the field editor with a table with two column, one with the key and the other one with the value. The column
	 * name, number and width are fixed inside the constructor
	 * 
	 * @param name
	 *          the name of the preference this field editor works on
	 * @param text
	 *          the label text of the field editor
	 * @param parent
	 *          the parent composite
	 */
	public PropertyListFieldEditor(String name, String text, Composite parent) {
		init(name, text);
		this.columnNames = new String[] { Messages.PropertyListFieldEditor_propertyLabel,
				Messages.PropertyListFieldEditor_valueLabel };
		this.columnWidths = new int[] { 300, 300 };
		createControl(parent);
	}

	/**
	 * Open the dialog to input a new parameter and return the result when it's closed
	 * 
	 * @return the value of the parameter if it is closed with ok, null if it is closed with cancel
	 */
	protected Pair getNewInputObject() {
		PEditDialog dialog = new PEditDialog(UIUtils.getShell());
		if (dialog.open() == Window.OK) {
			return new Pair(dialog.getPName(), dialog.getPValue());
		}
		return null;
	}

	/**
	 * Store the values of the parameters inside the configuration
	 */
	protected void doStore() {
		storeProperties(input, getPreferenceStore());
	}

	public static void storeProperties(List<Pair> in, IPreferenceStore store) {
		Properties props = new Properties();
		for (Pair item : in) {
			String key = item.key;
			String value = item.value;
			props.setProperty(key, value);
			if (key.equals("net.sf.jasperreports.default.font.name")) //$NON-NLS-1$
				JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).setProperty(key, value);
			else if (key.equals("net.sf.jasperreports.default.font.size")) //$NON-NLS-1$
				JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).setProperty(key, value);
		}
		store.setValue(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES, FileUtils.getPropertyAsString(props));
	}

	/**
	 * Load the values of the parameters from the configuration
	 */
	protected void doLoad() {
		if (getTable() != null) {
			Properties props = null;
			try {
				input = loadProperties(getPreferenceStore());
				viewer.setInput(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Add an help listener to the table
			TableHelpListener.setTableHelp(getTable());
		}
	}

	public static List<Pair> loadProperties(IPreferenceStore store) throws IOException {
		Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
		List<String> keys = new ArrayList<String>();
		for (Object key : props.keySet())
			keys.add((String) key);
		Collections.sort(keys);

		List<Pair> input = new ArrayList<Pair>();
		for (String key : keys) {
			String value = props.getProperty(key);
			input.add(new Pair((String) key, value));
		}
		return input;
	}

	/**
	 * Load the value of the default parameters from the configuration
	 */
	protected void doLoadDefault() {
		if (getTable() != null) {
			getTable().removeAll();
			try {
				Properties props = FileUtils
						.load(getPreferenceStore().getDefaultString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
				List<String> keys = new ArrayList<String>();
				for (Object key : props.keySet())
					keys.add((String) key);
				Collections.sort(keys);

				input = new ArrayList<Pair>();
				for (String key : keys) {
					String value = props.getProperty(key);
					input.add(new Pair((String) key, value));
					viewer.setInput(input);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, ContextHelpIDs.PREFERENCES_PROPERTIES);
	}

	/**
	 * Create the selection listener for the table
	 */
	public void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == addButton) {
					addPressed();
				} else if (widget == duplicateButton) {
					duplicatePressed();
				} else if (widget == removeButton) {
					removePressed();
				} else if (widget == editButton) {
					editPressed();
				} else if (widget == table) {
					selectionChanged();
				}
			}
		};
	}

	/**
	 * Action executed when the edit button is pressed, open the dialog to edit the parameter and then
	 */
	protected void editPressed() {
		StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		if (!selection.isEmpty()) {
			Pair element = (Pair) selection.getFirstElement();
			int selIdx = input.indexOf(element);
			element = input.get(selIdx);
			String pname = element.getKey();
			String pvalue = element.getValue();
			PEditDialog dialog = new PEditDialog(UIUtils.getShell(), pname, pvalue);
			if (dialog.open() == Window.OK) {
				String newPName = dialog.getPName();
				String newPValue = dialog.getPValue();
				if (!pname.equals(newPName)) {
					// ensure no duplicates
					for (int i = 0; i < table.getItemCount(); i++) {
						if (i != selIdx && newPName.equals(table.getItem(i).getText(0))) {
							MessageDialog.openError(UIUtils.getShell(), Messages.PropertyListFieldEditor_ErrTitle,
									Messages.PropertyListFieldEditor_ErrMsg);
							return;
						}
					}
				}
				element.setKey(newPName);
				element.setValue(newPValue);
				viewer.refresh();
			}
		}
	}

	/**
	 * Enable or disable the table buttons accordingly to the current selection
	 */
	protected void selectionChanged() {
		int index = table.getSelectionIndex();
		int size = table.getItemCount();
		if (duplicateButton != null)
			duplicateButton.setEnabled(index >= 0);
		if (removeButton != null)
			removeButton.setEnabled(index >= 0);
		boolean isMultiSelection = table.getSelectionCount() > 1;
		if (editButton != null)
			editButton.setEnabled(!isMultiSelection && size >= 1 && index >= 0 && index < size);
	}

	protected void createButtons(Composite box) {
		addButton = createPushButton(box, Messages.common_add);
		duplicateButton = createPushButton(box, Messages.PropertyListFieldEditor_duplicateButton);
		removeButton = createPushButton(box, Messages.common_delete);
		editButton = createPushButton(box, Messages.common_edit);
	}

	/**
	 * Return the current table
	 * 
	 * @return return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Return the column names
	 * 
	 * @return array of names for each column
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * Return the Add button.
	 * 
	 * @return the button
	 */
	protected Button getAddButton() {
		return addButton;
	}

	/**
	 * Return the Duplicate button.
	 * 
	 * @return the button
	 */
	protected Button getDuplicateButton() {
		return duplicateButton;
	}

	/**
	 * Return the Remove button.
	 * 
	 * @return the button
	 */
	protected Button getRemoveButton() {
		return removeButton;
	}

	/**
	 * Helper method to create a push button.
	 * 
	 * @param parent
	 *          the parent control
	 * @param key
	 *          the resource name used to supply the button's label text
	 * @return Button
	 */
	protected Button createPushButton(Composite parent, String key) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(key);
		button.setFont(parent.getFont());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		int widthHint = convertHorizontalDLUsToPixels(button, IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		button.setLayoutData(data);
		button.addSelectionListener(getSelectionListener());
		return button;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		((GridData) control.getLayoutData()).horizontalSpan = numColumns;
		((GridData) table.getLayoutData()).horizontalSpan = numColumns - 1;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		gridData.minimumWidth = 500;
		gridData.heightHint = 500;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(2, false));

		table = getTableControl(composite);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = numColumns - 1;
		gd.grabExcessHorizontalSpace = true;
		for (int width : columnWidths) {
			gd.widthHint += width;
		}
		table.setLayoutData(gd);

		buttonBox = getButtonBoxControl(composite);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		buttonBox.setLayoutData(gd);
	}

	/**
	 * Returns this field editor's button box containing the Add, Remove, Up, and Down button.
	 * 
	 * @param parent
	 *          the parent control
	 * @return the button box
	 */
	public Composite getButtonBoxControl(Composite parent) {
		if (buttonBox == null) {
			buttonBox = new Composite(parent, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			buttonBox.setLayout(layout);
			createButtons(buttonBox);
			buttonBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					addButton = null;
					duplicateButton = null;
					removeButton = null;
					buttonBox = null;
				}
			});

		} else {
			checkParent(buttonBox, parent);
		}

		selectionChanged();
		return buttonBox;
	}

	/**
	 * Returns this field editor's table control.
	 * 
	 * @param parent
	 *          the parent control
	 * @return the table control
	 */
	public Table getTableControl(Composite parent) {
		if (table == null) {
			table = new Table(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
			table.setFont(parent.getFont());
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.addSelectionListener(getSelectionListener());
			int index = 0;
			for (String columnName : columnNames) {
				TableColumn tableColumn = new TableColumn(table, SWT.LEAD);
				tableColumn.setText(columnName);
				tableColumn.setWidth(columnWidths[index]);
				index++;
			}
			if (columnNames.length > 0) {
				TableLayout layout = new TableLayout();
				if (columnNames.length > 1) {
					for (int i = 0; i < (columnNames.length - 1); i++) {
						layout.addColumnData(new ColumnWeightData(0, columnWidths[i], false));

					}
				}
				layout.addColumnData(new ColumnWeightData(100, columnWidths[columnNames.length - 1], true));
				table.setLayout(layout);
			}
			attachCellEditors();
			viewer = new TableViewer(table);
			viewer.setLabelProvider(new PairLableProvider());
			viewer.setContentProvider(new ListContentProvider());

			viewer.addDoubleClickListener(new IDoubleClickListener() {

				@Override
				public void doubleClick(DoubleClickEvent event) {
					editPressed();
				}
			});
		}
		return table;
	}

	/**
	 * Attach the cell editor on doubleclick to the table. The table must be created before to call this method.
	 */
	protected void attachCellEditors() {
		final TableEditor editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = table.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = table.getTopIndex();
				final Pair selectedItem = (Pair) ((StructuredSelection) viewer.getSelection()).getFirstElement();
				while (index < table.getItemCount()) {
					boolean visible = false;
					final TableItem selectedCell = table.getItem(index);
					for (int i = 0; i < table.getColumnCount(); i++) {
						Rectangle rect = selectedCell.getBounds(i);
						if (rect.contains(pt)) {
							final int column = i;
							final Text text = new Text(table, SWT.NONE);
							Listener textListener = new Listener() {
								public void handleEvent(final Event e) {
									switch (e.type) {
									case SWT.FocusOut:
										if (column == 0)
											selectedItem.setKey(text.getText());
										else
											selectedItem.setValue(text.getText());
										text.dispose();
										viewer.refresh();
										break;
									case SWT.Traverse:
										switch (e.detail) {
										case SWT.TRAVERSE_RETURN:
											if (column == 0)
												selectedItem.setKey(text.getText());
											else
												selectedItem.setValue(text.getText());
											text.dispose();
											viewer.refresh();
											// FALL THROUGH
										case SWT.TRAVERSE_ESCAPE:
											text.dispose();
											e.doit = false;
										}
										break;
									}
								}
							};
							text.addListener(SWT.FocusOut, textListener);
							text.addListener(SWT.Traverse, textListener);
							editor.setEditor(text, selectedCell, i);
							text.setText(selectedCell.getText(i));
							text.selectAll();
							text.setFocus();
							return;
						}
						if (!visible && rect.intersects(clientArea)) {
							visible = true;
						}
					}
					if (!visible)
						return;
					index++;
				}
			}
		});
	}

	/**
	 * Return the number of columns in the table
	 */
	public int getNumberOfControls() {
		return columnNames.length;
	}

	/**
	 * Returns this field editor's selection listener. The listener is created if necessary.
	 * 
	 * @return the selection listener
	 */
	protected SelectionListener getSelectionListener() {
		if (selectionListener == null) {
			createSelectionListener();
		}
		return selectionListener;
	}

	/**
	 * Returns this field editor's shell.
	 * <p>
	 * This method is internal to the framework; subclassers should not call this method.
	 * </p>
	 * 
	 * @return the shell
	 */
	protected Shell getShell() {
		if (addButton == null) {
			return null;
		}
		return addButton.getShell();
	}

	/**
	 * Notifies that the Add button has been pressed.
	 */
	protected void addPressed() {
		setPresentsDefaultValue(false);
		Pair newInputObject = getNewInputObject();
		if (newInputObject != null) {
			input.add(newInputObject);
			viewer.refresh();
		}
	}

	/**
	 * Notifies that the Add button has been pressed.
	 */
	protected void duplicatePressed() {
		setPresentsDefaultValue(false);
		StructuredSelection sel = (StructuredSelection) viewer.getSelection();
		for (Object obj : sel.toList()) {
			Pair selectedElement = (Pair) obj;
			int index = input.indexOf(obj);
			if (index >= 0) {
				Pair newElement = new Pair(selectedElement.getKey() + "_copy", selectedElement.getValue());
				if (index < (input.size() - 1)) {
					input.add(index + 1, newElement);
				} else {
					input.add(newElement);
				}
				viewer.refresh();
			}
		}

		selectionChanged();
	}

	/**
	 * Notifies that the Remove button has been pressed.
	 */
	protected void removePressed() {
		setPresentsDefaultValue(false);
		StructuredSelection sel = (StructuredSelection) viewer.getSelection();
		for (Object obj : sel.toList()) {
			input.remove(obj);
		}
		viewer.refresh();
		selectionChanged();
	}

	/**
	 * If not null set the focus on the table control
	 */
	public void setFocus() {
		if (table != null) {
			table.setFocus();
		}
	}

	/**
	 * enable or disable all the controls where the user can interact. If the table wasn't created yet then it is also
	 * created as child of the passed composite parameter
	 * 
	 * @param enabled
	 *          true if the controls must be enabled, false otherwise
	 * 
	 * @param parent
	 *          the parent of the table if it need to be created
	 */
	public void setEnabled(boolean enabled, Composite parent) {
		super.setEnabled(enabled, parent);
		getTableControl(parent).setEnabled(enabled);
		if (addButton != null)
			addButton.setEnabled(enabled);
		if (duplicateButton != null)
			duplicateButton.setEnabled(enabled);
		if (removeButton != null)
			removeButton.setEnabled(enabled);
		if (editButton != null)
			editButton.setEnabled(enabled);
	}
}
