package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.impl.OrTableImpl;
import com.jaspersoft.studio.data.sql.impl.SelectImpl;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.model.MRoot;

public class Text2Model {

	public static void text2model(final MRoot root, XtextDocument doc) {
		System.out.println("convert the model");
		doc.readOnly(new IUnitOfWork<String, XtextResource>() {
			public String exec(XtextResource resource) {
				EList<?> list = resource.getContents();
				if (list != null && !list.isEmpty()) {
					for (Object obj : list) {
						if (obj instanceof SelectImpl) {
							SelectImpl sel = (SelectImpl) obj;
							sel.getCols();
							convertTables(root, sel.getTbl());

							sel.getWhereExpression();
							sel.getGroupByEntry();
							sel.getHavingEntry();
							sel.getOrderByEntry();
						}
					}
				}
				return "";
			}

		});
	}

	private static void convertTables(MRoot root, OrTable tbls) {
		if (tbls == null)
			return;
		if (tbls instanceof OrTableImpl) {
			MFrom mfrom = Util.getKeyword(root, MFrom.class);
			for (FromTable ftbl : tbls.getEntries())
				doTables(mfrom, ftbl);
		} else if (tbls instanceof FromTable)
			doTables(Util.getKeyword(root, MFrom.class), (FromTable) tbls);
	}

	private static void doTables(MFrom mfrom, FromTable ftbl) {
		TableOrAlias t = ftbl.getTable();
		t.getTblAlias();
		t.getTfull();
		t.getAlias();
	}
}
