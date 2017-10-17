/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.util.JRCloneUtils;

public class ItemDataListDialog extends ATitledDialog implements IExpressionContextSetter {
	private ExpressionContext expContext;
	private List<ItemData> value;
	private TableViewer tviewer;
	private ADescriptor descriptor;
	private EditButton<StandardItemData> bpropEdit;
	private JasperReportsConfiguration jContext;
	private APropertyNode pnode;

	public ItemDataListDialog(Shell parentShell, ADescriptor descriptor, List<ItemData> value,
			AItemDataListPropertyDescriptor pd, APropertyNode pnode) {
		super(parentShell);
		this.value = value;
		this.descriptor = descriptor;
		this.jContext = pnode.getJasperConfiguration();
		this.pnode = pnode;
		setTitle(pd.getDisplayName());
		setDefaultSize(600, 600);
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = new Composite((Composite) super.createDialogArea(parent), SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Table wtable = new Table(cmp, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		wtable.setLayoutData(new GridData(GridData.FILL_BOTH));

		tviewer = new TableViewer(wtable);
		tviewer.setLabelProvider(new ItemLabelProvider(descriptor) {
			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			protected String getColumnText4ItemData(ItemData element, int columnIndex) {
				return getText4ItemData(element);
			}

			@Override
			public String getText4ItemData(ItemData element) {
				return Messages.ItemLabelProvider_7 + " " + (value.indexOf(element) + 1);
			}
		});

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		wtable.setLayout(tlayout);

		tviewer.setContentProvider(new ListContentProvider());
		ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);
		UIUtil.setViewerCellEditingOnDblClick(tviewer);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				List<ItemData> clones = JRCloneUtils.cloneList(value);
				StandardItemData itemData = new StandardItemData();
				clones.add(itemData);
				if (createItemDataDialog(clones, itemData).open() == Dialog.OK)
					return itemData;
				return null;
			}

		});

		bpropEdit = new EditButton<StandardItemData>();
		bpropEdit.createEditButtons(bGroup, tviewer, new IEditElement<StandardItemData>() {

			@Override
			public void editElement(List<StandardItemData> input, int pos) {
				List<ItemData> clones = JRCloneUtils.cloneList(value);
				StandardItemData itemDataClone = (StandardItemData) getStandardItemData(clones);

				if (createItemDataDialog(clones, itemDataClone).open() == Dialog.OK)
					input.set(pos, itemDataClone);
			}
		});
		bpropEdit.editOnDoubleClick();
		new DeleteButton().createDeleteButton(bGroup, tviewer);

		tviewer.setInput(value);
		return cmp;
	}

	private ItemData getStandardItemData(List<ItemData> clones) {
		IStructuredSelection sel = (IStructuredSelection) tviewer.getSelection();
		Object fe = sel.getFirstElement();
		if (fe != null) {
			if (fe instanceof StandardItemData)
				return clones.get(value.indexOf((StandardItemData) fe));
		} else if (!Misc.isNullOrEmpty(value))
			return (StandardItemData) clones.get(0);
		return null;
	}

	protected ItemDataDialog createItemDataDialog(List<ItemData> clones, StandardItemData itemData) {
		ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), Messages.SPItemDataList_6, Messages.SPItemDataList_7,
				clones, itemData, jContext, descriptor, expContext, pnode) {

			@Override
			protected AItemDialog createItemDialog() {
				return ItemDataListDialog.this.createItemDialog();
			}
		};
		return dialog;
	}

	protected AItemDialog createItemDialog() {
		return new TableItemDialog(UIUtils.getShell(), descriptor, jContext, false);
	}

	public List<ItemData> getValue() {
		return value;
	}
}
