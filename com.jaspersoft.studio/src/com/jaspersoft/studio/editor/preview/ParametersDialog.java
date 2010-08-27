package com.jaspersoft.studio.editor.preview;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ParametersDialog extends FormDialog {
	private List<JRDesignParameter> prompts;
	private Map<String, Object> params;

	public ParametersDialog(Shell shell, List<JRDesignParameter> prompts, Map<String, Object> params) {
		super(shell);
		this.prompts = prompts;
		this.params = params;
	}

	public Map<String, Object> getParameters() {
		return params;
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText("Report parameters");

		FormToolkit toolkit = mform.getToolkit();

		mform.getForm().getBody().setLayout(new GridLayout(2, false));

		for (JRDesignParameter p : prompts) {
			if (!(String.class.isAssignableFrom(p.getValueClass()) || Number.class.isAssignableFrom(p.getValueClass()) || Date.class
					.isAssignableFrom(p.getValueClass())))
				continue;

			toolkit.createLabel(mform.getForm().getBody(), p.getName() + ":", SWT.RIGHT);

			if (p.getValueClass().equals(String.class)) {
				createText(mform, toolkit, p);
			} else if (p.getValueClass().equals(Integer.class)) {
				createNumeric(mform.getForm().getBody(), p, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
			} else if (p.getValueClass().equals(Long.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE, 0, 1, 10);
			} else if (p.getValueClass().equals(Short.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Short.MIN_VALUE, (int) Short.MAX_VALUE, 0, 1, 10);
			} else if (p.getValueClass().equals(BigInteger.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE, 0, 1, 10);
			} else if (p.getValueClass().equals(Byte.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Byte.MIN_VALUE, (int) Byte.MAX_VALUE, 0, 1, 10);
			} else if (p.getValueClass().equals(BigDecimal.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE, 4, 1, 1);
			} else if (p.getValueClass().equals(Float.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE, 4, 1, 1);
			} else if (p.getValueClass().equals(Double.class)) {
				createNumeric(mform.getForm().getBody(), p, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE, 4, 1, 1);
			} else if (p.getValueClass().equals(Date.class)) {
				createDate(mform.getForm().getBody(), p);
			}
		}
	}

	private void createDate(Composite parent, final JRDesignParameter param) {
		if (param.getValueClass().equals(java.sql.Date.class)) {
			final DateTime date = new DateTime(parent, SWT.DATE | SWT.LONG | SWT.BORDER);
			date.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					params.put(
							param.getName(),
							java.sql.Date.valueOf(String.format("%04d", date.getYear()) + "-"
									+ String.format("%02d", date.getMonth()) + "-" + String.format("%02d", date.getDay())));
				}
			});
			if (params.get(param.getName()) != null) {
				Date d = (Date) params.get(param.getName());
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(d.getTime());

				date.setYear(cal.get(Calendar.YEAR));
				date.setMonth(cal.get(Calendar.MONTH) + 1);
				date.setDay(cal.get(Calendar.DATE));
			}
		} else if (param.getValueClass().equals(java.sql.Time.class)) {
			final DateTime time = new DateTime(parent, SWT.TIME | SWT.LONG | SWT.BORDER);
			time.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					params.put(
							param.getName(),
							java.sql.Time.valueOf(String.format("%02d",
									String.format("%02d", time.getHours()) + ":" + String.format("%02d", time.getMinutes()) + ":"
											+ String.format("%02d", time.getSeconds()))));

				}
			});
			if (params.get(param.getName()) != null) {
				Date d = (Date) params.get(param.getName());
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(d.getTime());

				time.setHours(cal.get(Calendar.HOUR));
				time.setMinutes(cal.get(Calendar.MINUTE));
				time.setSeconds(cal.get(Calendar.SECOND));
			}
		} else if (param.getValueClass().equals(java.sql.Timestamp.class)
				|| param.getValueClass().equals(java.util.Date.class)) {
			Composite c = new Composite(parent, SWT.NONE);
			c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout layout = new GridLayout(2, false);
			layout.marginWidth = 0;
			c.setLayout(layout);
			c.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));

			final DateTime date = new DateTime(c, SWT.DATE | SWT.LONG | SWT.BORDER);
			final DateTime time = new DateTime(c, SWT.TIME | SWT.LONG | SWT.BORDER);
			SelectionAdapter listener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					String timestamp = String.format("%04d", date.getYear()) + "-" + String.format("%02d", date.getMonth()) + "-"
							+ String.format("%02d", date.getDay()) + " " + String.format("%02d", time.getHours()) + ":"
							+ String.format("%02d", time.getMinutes()) + ":" + String.format("%02d", time.getSeconds());
					params.put(param.getName(), java.sql.Timestamp.valueOf(timestamp));
				}
			};
			date.addSelectionListener(listener);
			time.addSelectionListener(listener);
			if (params.get(param.getName()) != null) {
				Date d = (Date) params.get(param.getName());
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(d.getTime());

				date.setYear(cal.get(Calendar.YEAR));
				date.setMonth(cal.get(Calendar.MONTH) + 1);
				date.setDay(cal.get(Calendar.DATE));

				time.setHours(cal.get(Calendar.HOUR));
				time.setMinutes(cal.get(Calendar.MINUTE));
				time.setSeconds(cal.get(Calendar.SECOND));
			}
		}

	}

	private void createText(IManagedForm mform, FormToolkit toolkit, final JRDesignParameter param) {
		final Text txt = toolkit.createText(mform.getForm().getBody(), "", SWT.BORDER);
		txt.setToolTipText(param.getDescription());
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				params.put(param.getName(), txt.getText());
			}
		});
		if (params.get(param.getName()) != null)
			txt.setText((String) params.get(param.getName()));
	}

	private Spinner createNumeric(Composite parent, final JRDesignParameter param, int min, int max, int digits,
			int increment, int pageIncrement) {
		final Spinner num = new Spinner(parent, SWT.BORDER);
		num.setToolTipText(param.getDescription());
		Number value = (Number) params.get(param.getName());
		int val = 0;
		if (value != null)
			if (digits == 0)
				val = value.intValue();
			else
				val = (int) (value.doubleValue() * Math.pow(10000, 1));
		num.setValues(val, min, max, digits, increment, pageIncrement);
		num.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Number n = null;
				if (param.getValueClass().equals(Integer.class)) {
					n = new Integer(num.getSelection());
				} else if (param.getValueClass().equals(Long.class)) {
					n = new Long(num.getSelection());
				} else if (param.getValueClass().equals(Short.class)) {
					n = new Short((short) num.getSelection());
				} else if (param.getValueClass().equals(BigInteger.class)) {
					n = new BigInteger(new String("" + num.getSelection()));
				} else if (param.getValueClass().equals(Byte.class)) {
					n = new Byte((byte) num.getSelection());
				} else if (param.getValueClass().equals(BigDecimal.class)) {
					n = new BigDecimal(num.getSelection() / Math.pow(10000, num.getDigits()));
				} else if (param.getValueClass().equals(Float.class)) {
					n = new Float(num.getSelection() / Math.pow(10000, num.getDigits()));
				} else if (param.getValueClass().equals(Double.class)) {
					n = new Double(num.getSelection() / Math.pow(10000, num.getDigits()));
				}
				params.put(param.getName(), n);
			}
		});
		return num;
	}
}
