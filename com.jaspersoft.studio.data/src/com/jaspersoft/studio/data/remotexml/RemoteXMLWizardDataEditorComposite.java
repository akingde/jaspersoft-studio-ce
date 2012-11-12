/**
 * 
 */
package com.jaspersoft.studio.data.remotexml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapter;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.xml.XMLWizardDataEditorComposite;

/**
 * Editor composite for the Xpath2 query language.
 * This is supposed to used by {@link RemoteXMLDataAdapterDescriptor}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class RemoteXMLWizardDataEditorComposite extends
		XMLWizardDataEditorComposite {

	public RemoteXMLWizardDataEditorComposite(Composite parent, WizardPage page,
			DataAdapterDescriptor dataAdapterDescriptor) {
		super(parent, page, dataAdapterDescriptor);
	}

	@Override
	public String getQueryLanguage() {
		return "xpath2"; //$NON-NLS-1$
	}

	@Override
	protected Document getXMLDocument(DataAdapterDescriptor da)
			throws SAXException, IOException, ParserConfigurationException {
		String fileName = ((XmlDataAdapter)da.getDataAdapter()).getFileName();
		if(da.getDataAdapter() instanceof RemoteXmlDataAdapter){
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
		}
		else {
			return super.getXMLDocument(da);
		}
	}

}
