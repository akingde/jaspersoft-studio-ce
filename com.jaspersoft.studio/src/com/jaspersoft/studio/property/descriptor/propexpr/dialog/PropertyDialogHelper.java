/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.dataset.fields.PropertiesDialog;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.properties.PropertyMetadata;

public class PropertyDialogHelper {
	public static boolean showPropertyDialog(PropertyExpressionDTO dto, JRParameter p, JasperReportsConfiguration jConf) {
		Dialog d = null;
		PropertyMetadata pm = DatasetUtil.getPmap(jConf).get(dto.getName());
		if (pm != null) {
			List<TColumn> tcols = new ArrayList<TColumn>();
			TColumn tColumn = TColumnFactory.getTColumn(pm);
			tColumn.setValue(jConf);
			tcols.add(tColumn);
			d = new PropertiesDialog<JRParameter>(UIUtils.getShell(), p, tcols, pm.getLabel(), jConf);
		} else {
			d = new JRPropertyExpressionDialog(UIUtils.getShell());
			((JRPropertyExpressionDialog) d).setShowPropertyName(true);
			((JRPropertyExpressionDialog) d).setShowExpression(false);
			((JRPropertyExpressionDialog) d).setValue(dto);
		}
		return d.open() == Window.OK;
	}
}
