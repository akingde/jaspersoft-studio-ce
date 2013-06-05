package com.jaspersoft.studio.data.sql.widgets;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ParameterOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class Factory {
	public static final String OPERANDWIDGET = "operandwidget";
	public static final String OPERAND = "operand";
	public static final String OPERANDS = "OPERANDS";
	public static final String OPERANDS_INDEX = "OPERANDS_INDEX";

	public static Control createWidget(Composite parent, List<AOperand> operands, int index) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new FillLayout());

		AOperand op = null;
		if (index >= 0 && index < operands.size())
			op = operands.get(index);
		else
			op = new ScalarOperand();

		AOperandWidget<?> w = createWidget(cmp, op);
		createWidgetMenu(w, operands, index);
		return cmp;
	}

	protected static void createWidgetMenu(AOperandWidget<?> w, List<AOperand> operands, int index) {
		for (Control c : w.getChildren()) {
			Menu popupMenu = new Menu(c);
			buildMenu(popupMenu, w, operands, index);
			c.setMenu(popupMenu);
		}
	}

	private static <T extends AOperand> AOperandWidget<?> createWidget(Composite parent, T operand) {
		AOperandWidget<T> w = null;
		if (operand instanceof FieldOperand)
			return new FieldWidget(parent, (FieldOperand) operand);
		if (operand instanceof ParameterOperand)
			return new ParameterWidget(parent, (ParameterOperand) operand);
		if (operand instanceof ScalarOperand)
			return new StringWidget(parent, (ScalarOperand) operand);
		return w;
	}

	public static void buildMenu(Menu pMenu, AOperandWidget<?> w, List<AOperand> operands, int index) {
		Map<String, AOperand> opMap = buildMap(w);
		Menu newMenu = null;
		for (String key : opMap.keySet()) {
			MenuItem mi1 = null;
			AOperand aOperand = opMap.get(key);
			if (aOperand instanceof ScalarOperand) {
				if (newMenu == null) {
					MenuItem cmi = new MenuItem(pMenu, SWT.CASCADE);
					cmi.setText("Scalar Value");

					newMenu = new Menu(pMenu);
					cmi.setMenu(newMenu);
				}
				mi1 = new MenuItem(newMenu, SWT.CHECK);
			} else
				mi1 = new MenuItem(pMenu, SWT.CHECK);

			mi1.setText(key);
			mi1.setData(OPERAND, aOperand);
			setupMenuItem(mi1, w, operands, index);
			if (w.getValue() == aOperand)
				mi1.setSelection(true);
		}
	}

	private static void setupMenuItem(MenuItem mi1, AOperandWidget<?> w, List<AOperand> operands, int index) {
		mi1.setData(OPERANDWIDGET, w);
		mi1.setData(OPERANDS, operands);
		mi1.setData(OPERANDS_INDEX, index);
		mi1.addSelectionListener(slistener);
	}

	private static SelectionAdapter slistener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MenuItem mi = (MenuItem) e.getSource();
			AOperandWidget<?> w = (AOperandWidget<?>) mi.getData(OPERANDWIDGET);
			AOperand op = (AOperand) mi.getData(OPERAND);
			List<AOperand> operands = (List<AOperand>) mi.getData(OPERANDS);
			int index = (Integer) mi.getData(OPERANDS_INDEX);
			Composite parent = w.getParent();
			for (Control c : parent.getChildren())
				c.dispose();
			operands.set(index, op);

			AOperandWidget<?> neww = createWidget(parent, operands.get(index));
			neww.setOperandMap(w.getOperandMap());
			createWidgetMenu(neww, operands, index);
			parent.layout(true);
		}
	};

	public static Map<String, AOperand> buildMap(AOperandWidget<?> w) {
		Map<String, AOperand> opMap = w.getOperandMap();
		if (opMap == null) {
			opMap = new LinkedHashMap<String, AOperand>();
			opMap.put("Parameter", getOperand(w, new ParameterOperand()));
			opMap.put("Database Field", getOperand(w, new FieldOperand(null)));
			opMap.put("String", getOperand(w, new ScalarOperand()));
			opMap.put("Number", getOperand(w, new ScalarOperand()));
			opMap.put("Date", getOperand(w, new ScalarOperand()));
			opMap.put("Time", getOperand(w, new ScalarOperand()));
			opMap.put("Timestamp", getOperand(w, new ScalarOperand()));
			w.setOperandMap(opMap);
		}
		return opMap;
	}

	private static AOperand getOperand(AOperandWidget<?> w, AOperand operand) {
		if (w.getValue().getClass().isInstance(operand))
			return w.getValue();
		return operand;
	}
}
