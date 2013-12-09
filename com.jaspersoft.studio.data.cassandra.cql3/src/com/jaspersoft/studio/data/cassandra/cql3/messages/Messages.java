package com.jaspersoft.studio.data.cassandra.cql3.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.cassandra.cql3.messages.messages"; //$NON-NLS-1$
	public static String CassandraCQL3DataAdapterComposite_labelhostname;
	public static String CassandraCQL3DataAdapterComposite_labelport;
	public static String CassandraCQL3DataAdapterComposite_labelkeyspace;
	public static String CassandraCQL3DataAdapterComposite_labelcassandraVersion;
	public static String CassandraCQL3DataAdapterComposite_labelclustername;
	public static String RDDatasourceCassandraCQL3Page_desc;
	public static String RDDatasourceCassandraCQL3Page_tiitle;
	public static String RDDatasourceCassandraCQL3Page_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
