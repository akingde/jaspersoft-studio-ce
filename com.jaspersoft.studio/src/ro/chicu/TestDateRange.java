package ro.chicu;

import java.util.Date;

import net.sf.jasperreports.types.date.DateRange;
import net.sf.jasperreports.types.date.DateRangeExpression;

public class TestDateRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new java.lang.Integer(23);

		// new net.sf.jasperreports.types.date.DateRangeBuilder("DAY+11")

		DateRange dr = new net.sf.jasperreports.types.date.DateRangeBuilder("DAY+11").set(Date.class).toDateRange();
		if (dr instanceof DateRangeExpression) {
			String expr = ((DateRangeExpression) dr).getExpression();
			System.out.println(expr);
		}
		System.out.println("Start: " + dr.getStart() + "\n  End: " + dr.getEnd());

		// new net.sf.jasperreports.types.date.DateRangeBuilder("DAY + 10").toDateRange();
	}
}
