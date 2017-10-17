/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.TrustStrategy;

import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class JSSTrustStrategy implements TrustStrategy {
	private KeyStore trustStore;

	public JSSTrustStrategy(KeyStore trustStore) {
		this.trustStore = trustStore;
	}

	@Override
	public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		try {
			CertChainValidator.validateKeyChain(chain, trustStore);
			return true;
		} catch (KeyStoreException e) {
			throw new CertificateException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new CertificateException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new CertificateException(e);
		} catch (NoSuchProviderException e) {
			throw new CertificateException(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		} catch (GeneralSecurityException e) {
			throw new CertificateException(e);
		}
		return false;
	}
}
