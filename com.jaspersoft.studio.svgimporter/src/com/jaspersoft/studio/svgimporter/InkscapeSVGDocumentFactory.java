/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter;

import java.net.URL;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVG12DOMImplementation;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.SVG12CSSEngine;
import org.apache.batik.css.engine.SVGCSSEngine;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.css.parser.ExtendedParser;
import org.apache.batik.dom.AbstractStylableDocument;
import org.apache.batik.util.ParsedURL;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.DOMImplementation;

import com.jaspersoft.studio.svgimporter.InkscapeExtenderParserWrapper;

/**
 * Inkscape when converting a PDF into SVG add some internal tags in the style attribute
 * of an element. this tags start with a minus (-) and this broke the parsing on the 
 * batik attribute parser. For this reason we implement a custom parser to remove these
 * attributes before parsing them. This parser wrap the original one and avoid to let arrive
 * to it attributes in the inkscape format.
 * This class build the {@link InkscapeExtenderParserWrapper} and store it in the {@link CSSEngine}
 * used to parse the attributes. We provide two implementation for different version, since this is
 * also done in the superclass
 * 
 * @author Orlandin Marco
 *
 */
public class InkscapeSVGDocumentFactory extends SAXSVGDocumentFactory {

	private static final DOMImplementation CUSTOM_IMPL = new SVGDOMImplementation() {

		private static final long serialVersionUID = -7069310932788052399L;

		public CSSEngine createCSSEngine(AbstractStylableDocument doc, CSSContext ctx, ExtendedParser ep, ValueManager[] vms, ShorthandManager[] sms) {
			//build the custom parser
			ExtendedParser parser = new InkscapeExtenderParserWrapper(ep);
			ParsedURL durl = ((SVGOMDocument) doc).getParsedURL();
			CSSEngine result = new SVGCSSEngine(doc, durl, parser, vms, sms, ctx);
			URL url = SVGDOMImplementation.class.getResource("resources/UserAgentStyleSheet.css")	;
			if (url != null) {
				ParsedURL purl = new ParsedURL(url);
				InputSource is = new InputSource(purl.toString());
				result.setUserAgentStyleSheet(result.parseStyleSheet(is, purl, "all"));
			}

			return result;
		}

	};

	private static final DOMImplementation CUSTOM_IMPL12 = new SVG12DOMImplementation() {

		private static final long serialVersionUID = -5812245836223691346L;

		public CSSEngine createCSSEngine(AbstractStylableDocument doc, CSSContext ctx, ExtendedParser ep, ValueManager[] vms, ShorthandManager[] sms) {
			//build the custom parser
			ExtendedParser parser = new InkscapeExtenderParserWrapper(ep);
			ParsedURL durl = ((SVGOMDocument) doc).getParsedURL();
			CSSEngine result = new SVG12CSSEngine(doc, durl, parser, vms, sms, ctx);

			URL url = SVG12DOMImplementation.class.getResource("resources/UserAgentStyleSheet.css");
			if (url != null) {
				ParsedURL purl = new ParsedURL(url);
				InputSource is = new InputSource(purl.toString());
				result.setUserAgentStyleSheet(result.parseStyleSheet(is, purl, "all"));
			}

			return result;
		}

	};

	public InkscapeSVGDocumentFactory(String parser) {
		super(parser);
		implementation = CUSTOM_IMPL;
	}

	public InkscapeSVGDocumentFactory(String parser, boolean dd) {
		super(parser, dd);
		implementation = CUSTOM_IMPL;
	}

	/**
	 * Return our custom dom implementation that will build a {@link CSSEngine} with inside the 
	 * {@link InkscapeExtenderParserWrapper}
	 */
	public DOMImplementation getDOMImplementation(String ver) {
		if (ver == null || ver.length() == 0 || ver.equals("1.0") || ver.equals("1.1")) {
			return CUSTOM_IMPL;
		} else if (ver.equals("1.2")) {
			return CUSTOM_IMPL12;
		}
		throw new RuntimeException("Unsupport SVG version '" + ver + "'");
	}

}
