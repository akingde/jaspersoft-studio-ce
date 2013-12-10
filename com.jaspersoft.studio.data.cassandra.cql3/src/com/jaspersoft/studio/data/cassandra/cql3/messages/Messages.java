package com.jaspersoft.studio.data.cassandra.cql3.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.cassandra.cql3.messages.messages"; //$NON-NLS-1$
	public static String CassandraCQL3DataAdapter_labelhostname;
	public static String CassandraCQL3DataAdapter_labelport;
	public static String CassandraCQL3DataAdapter_labelkeyspace;
	public static String CassandraCQL3DataAdapter_labelcassandraVersion;
	public static String CassandraCQL3DataAdapter_labelclustername;
	public static String CassandraCQL3DataAdapterComposite_NotIntegerError;
	public static String CassandraCQL3DataAdapterFactory_Description;
	public static String CassandraCQL3DataAdapterFactory_Label;
	public static String RDDatasourceCassandraCQL3Page_desc;
	public static String RDDatasourceCassandraCQL3Page_tiitle;
	public static String RDDatasourceCassandraCQL3Page_title;
	public static String ResourcePublisher_Description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
