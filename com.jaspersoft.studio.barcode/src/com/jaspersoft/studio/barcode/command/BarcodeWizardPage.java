package com.jaspersoft.studio.barcode.command;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.engine.JRComponentElement;

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
		super("barcodepage");
		setTitle("Barcode Wizard");
		setDescription("JasperReports provide two different components to create barcodes. The first one is Barbecue, the second is Barcode4j, both opensource.\n" +
				"Please, select one type of barcode from a list.");
	}

	@Override
	public void dispose() {
		if (b4jCode != null) {
			if (b4jCode.equals("Codabar"))
				barcode = new MCodabar();
			else if (b4jCode.equals("Code128"))
				barcode = new MCode128();
			else if (b4jCode.equals("Code39"))
				barcode = new MCode39();
			else if (b4jCode.equals("EAN128"))
				barcode = new MCode128();
			else if (b4jCode.equals("EAN13"))
				barcode = new MEAN13();
			else if (b4jCode.equals("EAN8"))
				barcode = new MEAN8();
			else if (b4jCode.equals("PDF417"))
				barcode = new MPDF417();
			else if (b4jCode.equals("DataMatrix"))
				barcode = new MDataMatrix();
			else if (b4jCode.equals("Int2of5"))
				barcode = new MInterleaved2Of5();
			else if (b4jCode.equals("RoyalMailCustomer"))
				barcode = new MRoyalMail();
			else if (b4jCode.equals("UPCA"))
				barcode = new MUPCA();
			else if (b4jCode.equals("UPCE"))
				barcode = new MUPCE();
			else if (b4jCode.equals("USPS"))
				barcode = new MUSPSIntelligent();
			else if (b4jCode.equals("PostNet"))
				barcode = new MPOSTNET();
		} else if (bbcCode != null) {
			barcode = new MBarcodeBarbecue();
			JRComponentElement de = (JRComponentElement) barcode.createJRElement(null);
			StandardBarbecueComponent sbc = (StandardBarbecueComponent) de.getComponent();
			sbc.setType(bbcCode);
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
		lbl.setText("Barbecue types");

		lbl = new Label(composite, SWT.NONE);
		lbl.setText("Barcode4J types");

		final Table table = new Table(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 500;
		gd.widthHint = 300;
		table.setLayoutData(gd);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");

		fillTableBarbecue(table);
		column[0].pack();

		final Table table2 = new Table(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 500;
		gd.widthHint = 300;
		table2.setLayoutData(gd);
		table2.setHeaderVisible(false);
		table2.setLinesVisible(true);

		TableColumn[] column2 = new TableColumn[1];
		column2[0] = new TableColumn(table2, SWT.NONE);
		column2[0].setText("Name");

		fillTableb4j(table2);
		column2[0].pack();

		table2.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				TableItem[] sel = table2.getSelection();
				if (sel != null && sel.length >0) {
					b4jCode = sel[0].getText();
					table.setSelection(-1);
					bbcCode = null;
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

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
				// TODO Auto-generated method stub

			}
		});
	}

	private String bbcCode = null;
	private String b4jCode = null;

	private void fillTableb4j(Table table) {
		table.setRedraw(false);

		String[] items = new String[] { "Codabar", "Code128", "Code39", "EAN128", "EAN13", "EAN8", "PDF417", "PostNet",
				"DataMatrix", "Int2of5", "RoyalMailCustomer", "UPCA", "UPCE", "USPS" };
		for (int i = 0; i < items.length; i++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(items[i]);
			ti.setImage(Activator.getImage("/icons/resources/" + items[i] + ".png"));
		}

		table.setRedraw(true);
	}

	private void fillTableBarbecue(Table table) {
		table.setRedraw(false);

		String[] items = new String[] { "2of7", "3of9", "Bookland", "Codabar", "Code128", "Code128A", "Code128B",
				"Code128C", "Code39", "Code39_extended", "EAN128", "EAN13", "GlobalTradeItemNumber", "Int2of5", "Monarch",
				"NW7", "PDF417", "PostNet", "RandomWeightUPCA", "SCC14ShippingCode", "ShipmentIdentificationNumber", "SSCC18",
				"Std2of5", "UCC128", "UPCA", "USD3", "USD4", "USPS" };
		for (int i = 0; i < items.length; i++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(items[i]);
			ti.setImage(Activator.getImage("/icons/resources/" + items[i] + ".png"));
		}

		table.setRedraw(true);
	}

}
