/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;

public class PatternPage extends WizardPage implements PropertyChangeListener {

	private String value;
	private List list;
	private Map<String, APattern> map;
	private Text patternText;
	private Label sampleLabel;
	private APattern pattern;
	private Label descriptionLabel;
	private StackLayout stackLayout;
	private Composite configComposite;

	public String getValue() {
		return value;
	}

	/**
	 * Displays the help
	 */
	public void performHelp() {
	}

	public void setValue(String value) {
		this.value = value;
	}

	public APattern getPattern() {
		if (pattern == null)
			pattern = new CustomPattern(configComposite, value, null, null);
		return pattern;
	}

	protected PatternPage(String pageName) {
		super(pageName);
		setTitle(Messages.PatternPage_format_pattern);
		setDescription(Messages.PatternPage_description);

	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		list = new List(composite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.heightHint = 200;
		gd.widthHint = 100;
		list.setLayoutData(gd);

		Group patternGroup = new Group(composite, SWT.NONE);
		patternGroup.setText(" " + Messages.common_pattern + " ");
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 200;
		patternGroup.setLayoutData(gd);
		patternGroup.setLayout(new GridLayout(1, true));

		patternText = new Text(patternGroup, SWT.SINGLE | SWT.BORDER);
		patternText.setText(getValue() != null ? getValue() : ""); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		patternText.setLayoutData(gd);

		sampleLabel = new Label(patternGroup, SWT.BORDER | SWT.FLAT | SWT.CENTER);
		sampleLabel.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		sampleLabel.setLayoutData(gd);

		configComposite = new Composite(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 200;
		gd.heightHint = 200;
		configComposite.setLayoutData(gd);
		stackLayout = new StackLayout();
		configComposite.setLayout(stackLayout);

		createPatterns(configComposite);
		for (String key : map.keySet())
			list.add(key);

		descriptionLabel = new Label(composite, SWT.WRAP);
		gd = new GridData(GridData.FILL);
		gd.horizontalSpan = 2;
		gd.widthHint = 300;
		gd.heightHint = 30;
		descriptionLabel.setLayoutData(gd);

		list.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String[] sel = list.getSelection();
				if (sel.length > 0) {
					APattern p = map.get(sel[0]);

					descriptionLabel.setText(p.getDescription() != null ? p.getDescription() : ""); //$NON-NLS-1$
					descriptionLabel.pack();

					stackLayout.topControl = p.getControl();
					configComposite.layout();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				e.getSource();
			}
		});
		patternText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				APattern pa = getPattern();
				pa.setPattern(patternText.getText());
				processFormat(pa);
			}
		});
	}

	private boolean datePatterns = true;
	private boolean numberPatterns = true;

	public boolean isDatePatterns() {
		return datePatterns;
	}

	public void setDatePatterns(boolean datePatterns) {
		this.datePatterns = datePatterns;
	}

	public boolean isNumberPatterns() {
		return numberPatterns;
	}

	public void setNumberPatterns(boolean numberPatterns) {
		this.numberPatterns = numberPatterns;
	}

	public void createPatterns(Composite parent) {
		map = new HashMap<String, APattern>();
		createDatePatterns(parent);

		createNumberPatterns(parent);

		for (String key : map.keySet()) {
			map.get(key).getPropertyChangeSupport().addPropertyChangeListener(this);
		}
	}

	private void createNumberPatterns(Composite parent) {
		if (numberPatterns) {
			map.put(Messages.PatternPage_number, new NumericPattern(parent));
			map.put(Messages.PatternPage_currency, new CurrencyPattern(parent));
			map.put(Messages.common_percentage, new PercentagePattern(parent));
			map.put(Messages.PatternPage_scientific, new ScientificPattern(parent));
		}
	}

	private void createDatePatterns(Composite parent) {
		if (datePatterns) {
			map.put(Messages.PatternPage_date, new DatePattern(parent));
			map.put(Messages.common_time, new TimePattern(parent));
		}
	}

	private void processFormat(APattern p) {
		try {
			if (p.getFormatter() != null && p.getSample() != null) {

				setErrorMessage(null);
				Format formatter = p.getFormatter();
				if (formatter instanceof SimpleDateFormat) {
					((SimpleDateFormat) formatter).applyPattern(p.getPattern());
				} else if (formatter instanceof DecimalFormat) {
					((DecimalFormat) formatter).applyPattern(p.getPattern());
				}
				sampleLabel.setText(formatter.format(p.getSample()));
				setValue(p.getPattern());
			}
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		APattern p = (APattern) evt.getSource();
		APattern pa = getPattern();
		pa.setPattern(p.getPattern());
		pa.setFormatter(p.getFormatter());
		pa.setSample(p.getSample());
		patternText.setText(p.getPattern());
		processFormat(p);

	}
}
