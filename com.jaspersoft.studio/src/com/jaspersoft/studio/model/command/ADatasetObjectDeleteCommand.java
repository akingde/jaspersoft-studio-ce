package com.jaspersoft.studio.model.command;

import java.text.MessageFormat;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ADatasetObjectDeleteCommand extends Command {
	/** The jr dataset. */
	protected JRDesignDataset jrDataset;

	/** The element position. */
	protected int elementPosition = 0;
	protected JasperDesign jd;
	protected JasperReportsConfiguration jContext;

	protected Boolean canceled;
	protected String objectName;

	protected boolean checkExpressions() {
		if (canceled != null)
			return false;

		JRExpressionCollector reportCollector = JRExpressionCollector.collector(jContext, jd);
		JRExpressionCollector datasetCollector = reportCollector.getCollector(jrDataset);
		List<JRExpression> datasetExpressions = datasetCollector.getExpressions();
		for (JRExpression expr : datasetExpressions) {
			String s = expr.getText();
			if (s != null && s.length() > 4 && s.contains(objectName)) {
				if (UIUtils.showConfirmation(Messages.ADatasetObjectDeleteCommand_confirmationtitle,
						MessageFormat.format(Messages.ADatasetObjectDeleteCommand_confirmationquestion, objectName)))
					break;
				else {
					canceled = Boolean.TRUE;
					return false;
				}
			}
		}
		canceled = Boolean.FALSE;
		return true;
	}
}
