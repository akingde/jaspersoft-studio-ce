/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.barcode.command;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.barcode.Activator;
import com.jaspersoft.studio.barcode.messages.Messages;
import com.jaspersoft.studio.barcode.model.MBarcode;
import com.jaspersoft.studio.barcode.model.MBarcodeBarbecue;
import com.jaspersoft.studio.barcode.model.barcode4j.MCodabar;
import com.jaspersoft.studio.barcode.model.barcode4j.MCode128;
import com.jaspersoft.studio.barcode.model.barcode4j.MCode39;
import com.jaspersoft.studio.barcode.model.barcode4j.MDataMatrix;
import com.jaspersoft.studio.barcode.model.barcode4j.MEAN13;
import com.jaspersoft.studio.barcode.model.barcode4j.MEAN8;
import com.jaspersoft.studio.barcode.model.barcode4j.MInterleaved2Of5;
import com.jaspersoft.studio.barcode.model.barcode4j.MPDF417;
import com.jaspersoft.studio.barcode.model.barcode4j.MPOSTNET;
import com.jaspersoft.studio.barcode.model.barcode4j.MRoyalMail;
import com.jaspersoft.studio.barcode.model.barcode4j.MUPCA;
import com.jaspersoft.studio.barcode.model.barcode4j.MUPCE;
import com.jaspersoft.studio.barcode.model.barcode4j.MUSPSIntelligent;

public class BarcodeWizardPage extends WizardPage {
	private MBarcode barcode = new MCodabar();

	public MBarcode getBarcode() {
		return barcode;
	}

	protected BarcodeWizardPage() {
		super("barcodepage"); //$NON-NLS-1$
		setTitle(Messages.common_barcode_wizard);
		setDescription(Messages.BarcodeWizardPage_barcode_wizard_description_a
				+ Messages.BarcodeWizardPage_barcode_wizard_description_b);
	}

	@Override
	public void dispose() {
		if (b4jCode != null) {
			if (b4jCode.equals("Codabar")) //$NON-NLS-1$
				barcode = new MCodabar();
			else if (b4jCode.equals("Code128")) //$NON-NLS-1$
				barcode = new MCode128();
			else if (b4jCode.equals("Code39")) //$NON-NLS-1$
				barcode = new MCode39();
			else if (b4jCode.equals("EAN128")) //$NON-NLS-1$
				barcode = new MCode128();
			else if (b4jCode.equals("EAN13")) //$NON-NLS-1$
				barcode = new MEAN13();
			else if (b4jCode.equals("EAN8")) //$NON-NLS-1$
				barcode = new MEAN8();
			else if (b4jCode.equals("PDF417")) //$NON-NLS-1$
				barcode = new MPDF417();
			else if (b4jCode.equals("DataMatrix")) //$NON-NLS-1$
				barcode = new MDataMatrix();
			else if (b4jCode.equals("Int2of5")) //$NON-NLS-1$
				barcode = new MInterleaved2Of5();
			else if (b4jCode.equals("RoyalMailCustomer")) //$NON-NLS-1$
				barcode = new MRoyalMail();
			else if (b4jCode.equals("UPCA")) //$NON-NLS-1$
				barcode = new MUPCA();
			else if (b4jCode.equals("UPCE")) //$NON-NLS-1$
				barcode = new MUPCE();
			else if (b4jCode.equals("USPS")) //$NON-NLS-1$
				barcode = new MUSPSIntelligent();
			else if (b4jCode.equals("PostNet")) //$NON-NLS-1$
				barcode = new MPOSTNET();
		} else if (bbcCode != null) {
			barcode = new MBarcodeBarbecue();
			JRDesignComponentElement de = (JRDesignComponentElement) barcode.createJRElement(null);
			StandardBarbecueComponent sbc = (StandardBarbecueComponent) de.getComponent();
			sbc.setType(bbcCode);
			barcode = new MBarcodeBarbecue(null, de, -1);
		}
		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.BarcodeWizardPage_barbecue_types);

		lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.BarcodeWizardPage_barcode4j_types);

		final Table table = new Table(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 500;
		gd.widthHint = 250;
		table.setLayoutData(gd);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.BarcodeWizardPage_name);

		column[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		table.setLayout(tlayout);

		fillTableBarbecue(table);

		final Table table2 = new Table(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 500;
		gd.widthHint = 250;
		table2.setLayoutData(gd);
		table2.setHeaderVisible(false);
		table2.setLinesVisible(true);

		TableColumn[] column2 = new TableColumn[1];
		column2[0] = new TableColumn(table2, SWT.NONE);
		column2[0].setText(Messages.BarcodeWizardPage_name);

		column2[0].pack();

		tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		table.setLayout(tlayout);

		fillTableb4j(table2);

		table2.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				TableItem[] sel = table2.getSelection();
				if (sel != null && sel.length > 0) {
					b4jCode = sel[0].getText();
					table.setSelection(-1);
					bbcCode = null;
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				TableItem[] sel = table.getSelection();
				if (sel != null && sel.length > 0) {
					bbcCode = sel[0].getText();
					table2.setSelection(-1);
					b4jCode = null;
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private String bbcCode = null;
	private String b4jCode = null;

	private void fillTableb4j(Table table) {
		table.setRedraw(false);

		String[] items = new String[] { "Codabar", "Code128", "Code39", "EAN128", "EAN13", "EAN8", "PDF417", "PostNet", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				"DataMatrix", "Int2of5", "RoyalMailCustomer", "UPCA", "UPCE", "USPS" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		for (int i = 0; i < items.length; i++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(items[i]);
			ti.setImage(Activator.getImage("/icons/resources/" + items[i] + ".png")); //$NON-NLS-1$ //$NON-NLS-2$
		}

		table.setRedraw(true);
	}

	private void fillTableBarbecue(Table table) {
		table.setRedraw(false);

		String[] items = new String[] { "2of7", "3of9", "Bookland", "Codabar", "Code128", "Code128A", "Code128B", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				"Code128C", "Code39", "Code39_extended", "EAN128", "EAN13", "GlobalTradeItemNumber", "Int2of5", "Monarch", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				"NW7", "PDF417", "PostNet", "RandomWeightUPCA", "SCC14ShippingCode", "ShipmentIdentificationNumber", "SSCC18", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				"Std2of5", "UCC128", "UPCA", "USD3", "USD4", "USPS" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		for (int i = 0; i < items.length; i++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(items[i]);
			ti.setImage(Activator.getImage("/icons/resources/" + items[i] + ".png")); //$NON-NLS-1$ //$NON-NLS-2$
		}

		table.setRedraw(true);
	}

}
