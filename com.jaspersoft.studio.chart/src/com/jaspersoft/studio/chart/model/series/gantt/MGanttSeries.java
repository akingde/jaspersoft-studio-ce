package com.jaspersoft.studio.chart.model.series.gantt;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignGanttSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MGanttSeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("ganttseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MGanttSeries() {
		super();
	}

	public MGanttSeries(ANode parent, JRDesignGanttSeries value, int newIndex) {
		super(parent, -1);
		setValue(value);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		JRExpressionPropertyDescriptor endDateExpD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION, Messages.MGanttSeries_end_date_expression);
		endDateExpD.setDescription(Messages.MGanttSeries_end_date_expression_description);
		desc.add(endDateExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION, Messages.MGanttSeries_label_expression);
		lblExprD.setDescription(Messages.MGanttSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION, Messages.MGanttSeries_series_expression);
		seriesExprD.setDescription(Messages.MGanttSeries_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor percExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_PERCENT_EXPRESSION, Messages.MGanttSeries_percent_expression);
		percExprD.setDescription(Messages.MGanttSeries_percent_expression_description);
		desc.add(percExprD);

		JRExpressionPropertyDescriptor startDateExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION, Messages.MGanttSeries_start_date_expression);
		startDateExprD.setDescription(Messages.MGanttSeries_start_date_expression_description);
		desc.add(startDateExprD);

		JRExpressionPropertyDescriptor subtaskExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION, Messages.MGanttSeries_subtask_expression);
		subtaskExprD.setDescription(Messages.MGanttSeries_subtask_expression_description);
		desc.add(subtaskExprD);

		JRExpressionPropertyDescriptor taskExprD = new JRExpressionPropertyDescriptor(
				JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION, Messages.MGanttSeries_task_expression);
		taskExprD.setDescription(Messages.MGanttSeries_task_expression_description);
		desc.add(taskExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignGanttSeries.PROPERTY_ITEM_HYPERLINK,
				Messages.MGanttSeries_item_hyperlink);
		itemHyperLinkD.setDescription(Messages.MGanttSeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);

		defaultsMap.put(JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION, null);
		defaultsMap.put(JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION, null);
		defaultsMap.put(JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION, null);
		defaultsMap.put(JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION, null);
	}

	private MHyperLink mHyperLink;

	private MExpression edExpression;
	private MExpression hExpression;
	private MExpression lExpression;
	private MExpression sdExpression;
	private MExpression stExpression;
	private MExpression tExpression;

	public Object getPropertyValue(Object id) {
		JRDesignGanttSeries jrElement = (JRDesignGanttSeries) getValue();

		if (id.equals(JRDesignGanttSeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION)) {
			if (edExpression == null)
				edExpression = new MExpression(jrElement.getEndDateExpression());
			return edExpression;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (hExpression == null)
				hExpression = new MExpression(jrElement.getLabelExpression());
			return hExpression;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION)) {
			if (lExpression == null)
				lExpression = new MExpression(jrElement.getSeriesExpression());
			return lExpression;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION)) {
			if (sdExpression == null)
				sdExpression = new MExpression(jrElement.getStartDateExpression());
			return sdExpression;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION)) {
			if (stExpression == null)
				stExpression = new MExpression(jrElement.getSubtaskExpression());
			return stExpression;
		}
		if (id.equals(JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION)) {
			if (tExpression == null)
				tExpression = new MExpression(jrElement.getTaskExpression());
			return tExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignGanttSeries jrElement = (JRDesignGanttSeries) getValue();

		if (id.equals(JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION)) {
			if (value instanceof MExpression) {
				edExpression = (MExpression) value;
				JRExpression expression = (JRExpression) edExpression.getValue();
				jrElement.setEndDateExpression(expression);
			}
		} else if (id.equals(JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setSeriesExpression(expression);
			}
		} else if (id.equals(JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION)) {
			if (value instanceof MExpression) {
				sdExpression = (MExpression) value;
				JRExpression expression = (JRExpression) sdExpression.getValue();
				jrElement.setStartDateExpression(expression);
			}
		} else if (id.equals(JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION)) {
			if (value instanceof MExpression) {
				stExpression = (MExpression) value;
				JRExpression expression = (JRExpression) stExpression.getValue();
				jrElement.setSubtaskExpression(expression);
			}
		} else if (id.equals(JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION)) {
			if (value instanceof MExpression) {
				tExpression = (MExpression) value;
				JRExpression expression = (JRExpression) tExpression.getValue();
				jrElement.setTaskExpression(expression);
			}
		}
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

}