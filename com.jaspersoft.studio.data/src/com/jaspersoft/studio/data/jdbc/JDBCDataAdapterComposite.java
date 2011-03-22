package com.jaspersoft.studio.data.jdbc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.SWTResourceManager;

public class JDBCDataAdapterComposite extends Composite {
	private JDBCDataAdapter dataAdapter = null;
	private Text textJDBCUrl;
	private Text textServerAddress;
	private Text textDatabase;
	private Text textUsername;
	private Text textPassword;
	
	private List listJars;
	private Button btnRemoveJar;
	private Button btnSavePassword;
	private Button btnWizard;
	private ComboViewer comboJDBCDriver;
	private Label lblMessage;
	
	private ClassLoader driverClassLoader = null;
	
	private JDBCDriverDefinition currentdriver = null;
	
	private static JDBCDriverDefinition[] definitions = new JDBCDriverDefinition[]{
			new JDBCDriverDefinition("Cloudscape","COM.cloudscape.JDBCDriver","jdbc:cloudscape:/{1}"),
			new JDBCDriverDefinition("IBM DB2","COM.ibm.db2.jdbc.app.DB2Driver","jdbc:db2:{0}/{1}"),
			new JDBCDriverDefinition("inetdae7","com.inet.tds.TdsDriver","jdbc:inetdae7:{0}:1433/{1}"),
			new JDBCDriverDefinition("Informix","com.informix.jdbc.IfxDriver","jdbc:informix-sqli://{0}:informixserver={1}"),
			new JDBCDriverDefinition("Ingres","com.ingres.jdbc.IngresDriver","jdbc:ingres://{0}:II7/{1}"),
			new JDBCDriverDefinition("HSQLDB (file)","org.hsqldb.jdbcDriver","jdbc:hsqldb:[PATH_TO_DB_FILES]/{1}"),
			new JDBCDriverDefinition("HSQLDB (server)","org.hsqldb.jdbcDriver","jdbc:hsqldb:hsql://{0}"),
			new JDBCDriverDefinition("JDBC-ODBC Bridge","sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:{1}","DSNAME"),
			new JDBCDriverDefinition("JDBC-ODBC Bridge","com.ms.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:{1}","DSNAME"),
			new JDBCDriverDefinition("MS SQLServer","com.internetcds.jdbc.tds.Driver","jdbc:freetds:sqlserver://{0}/{1}"),
			new JDBCDriverDefinition("MS SQLServer (2000)","com.microsoft.jdbc.sqlserver.SQLServerDriver","jdbc:microsoft:sqlserver://{0}:1433;DatabaseName={1}"),
			new JDBCDriverDefinition("MS SQLServer (2005)","com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://{0}:1433;databaseName={1}"),
			new JDBCDriverDefinition("MS SQLServer","net.sourceforge.jtds.jdbc.Driver","jdbc:jtds:sqlserver://{0}/{1}"),
			new JDBCDriverDefinition("MS SQLServer","com.merant.datadirect.jdbc.sqlserver.SQLServerDriver","jdbc:sqlserver://{0}:1433/{1}"),
			new JDBCDriverDefinition("MySQL","org.gjt.mm.mysql.Driver","jdbc:mysql://{0}/{1}"),
			new JDBCDriverDefinition("MySQL","com.mysql.jdbc.Driver","jdbc:mysql://{0}/{1}"),
			new JDBCDriverDefinition("Oracle","oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@{0}:1521:{1}"),
			new JDBCDriverDefinition("PostgreSQL","org.postgresql.Driver","jdbc:postgresql://{0}:5432/{1}"),
			new JDBCDriverDefinition("Sybase","com.sybase.jdbc2.jdbc.SybDriver","jdbc:sybase:Tds:{0}:2638/{1}"),
			new JDBCDriverDefinition("Vertica","com.vertica.Driver","jdbc:vertica://{0}:5433/{1}")
	};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public JDBCDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("JDBC Driver");
		
		comboJDBCDriver = new ComboViewer(this, SWT.NONE);
		Combo combo = comboJDBCDriver.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		combo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				driverChanged();
			}
		});
		
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("JDBC Url");
		
		textJDBCUrl = new Text(this, SWT.BORDER);
		textJDBCUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Group grpJdbc = new Group(this, SWT.NONE);
		grpJdbc.setLayout(new GridLayout(3, false));
		grpJdbc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		grpJdbc.setText("JDBC URL Wizard");
		
		Label lblServerAddress = new Label(grpJdbc, SWT.NONE);
		lblServerAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblServerAddress.setText("Server Address");
		
		textServerAddress = new Text(grpJdbc, SWT.BORDER);
		textServerAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpJdbc, SWT.NONE);
		
		Label lblDatabase = new Label(grpJdbc, SWT.NONE);
		lblDatabase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDatabase.setText("Database");
		
		textDatabase = new Text(grpJdbc, SWT.BORDER);
		textDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnWizard = new Button(grpJdbc, SWT.NONE);
		btnWizard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				btnWizardActionPerformed(e);
				
			}
		});
		btnWizard.setText("Wizard");
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username");
		
		textUsername = new Text(this, SWT.BORDER);
		GridData gd_textUsername = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textUsername.widthHint = 200;
		textUsername.setLayoutData(gd_textUsername);
		new Label(this, SWT.NONE);
		
		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password");
		
		textPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		GridData gd_textPassword = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textPassword.widthHint = 200;
		textPassword.setLayoutData(gd_textPassword);
		
		btnSavePassword = new Button(this, SWT.CHECK);
		btnSavePassword.setText("Save Password");
		new Label(this, SWT.NONE);
		
		Label lblAttentionPasswordsAre = new Label(this, SWT.NONE);
		lblAttentionPasswordsAre.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblAttentionPasswordsAre.setText("Attention! Passwords are saved in clear text");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblNewLabel_2.setText("Driver Jars");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginHeight = 0;
		gl_composite.marginBottom = 5;
		gl_composite.verticalSpacing = 0;
		composite.setLayout(gl_composite);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_composite.widthHint = 350;
		gd_composite.heightHint = 150;
		composite.setLayoutData(gd_composite);
		
		lblMessage = new Label(this, SWT.NONE);
		lblMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		
		listJars = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		
		listJars.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				listJarsSelectionChanged(e);
				
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				listJarsSelectionChanged(e);
				
			}
		});
		
		listJars.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		Button btnAddJar = new Button(composite, SWT.NONE);
		btnAddJar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnAddJarActionPerfomed(e);
			}
		});
		btnAddJar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddJar.setText("Add");
		
		btnRemoveJar = new Button(composite, SWT.NONE);
		btnRemoveJar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnRemoveJarActionPerfomed(e);
			}
		});
		btnRemoveJar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnRemoveJar.setText("Remove");
		btnRemoveJar.setEnabled(false);
		
		// add the drivers....
		for (int i=0; i<definitions.length; ++i)
		{
			comboJDBCDriver.add(definitions[i]);
		}
		
		comboJDBCDriver.addSelectionChangedListener(new ISelectionChangedListener() {

		    public void selectionChanged(SelectionChangedEvent event) {
		        // Handle selection changed event here
		        ISelection selection = event.getSelection();
		        
		        if (comboJDBCDriver.getCombo().getSelectionIndex() >= 0)
		        {
		        	currentdriver = definitions[comboJDBCDriver.getCombo().getSelectionIndex()];
		        	comboJDBCDriver.getCombo().setText(currentdriver.getDriverName());
		        	btnWizard.setEnabled(true);
		        	btnWizardActionPerformed(null);
		        }
		        else
		        {
		        	btnWizard.setEnabled(false);
			        currentdriver=null;
		        }
		        
		        driverChanged();
		    }
		    
		});
	}

	/**
	 * @param e
	 */
	protected void btnWizardActionPerformed(SelectionEvent e) {
		if (currentdriver != null)
		{
			textJDBCUrl.setText(currentdriver.getUrl(textServerAddress.getText(), textDatabase.getText()));
		}
	}

	/**
	 * This method is called when the text in the driver combobox changes...
	 */
	protected void driverChanged() {
		
		String driverName = comboJDBCDriver.getCombo().getText();
		
		if (currentdriver != null && currentdriver.getDriverName().equals(driverName)) 
		{
			// All ok, nothing apparently changed...
			btnWizard.setEnabled(true);
		}
		else
		{
			// If the driver is a valid driver, select the current definition....
			boolean found = false;
			if (driverName.length() > 0)
			{
				for (JDBCDriverDefinition d : definitions)
				{
					if (d.getDriverName().equals(driverName))
					{
						btnWizard.setEnabled(true);
						currentdriver=d;
						found=true;
						break;
					}
				}
				
				if (!found)
				{
					currentdriver = null;
					btnWizard.setEnabled(false);
				}
			}
		}
		
		if (driverName.length() == 0)
		{
			lblMessage.setVisible(true);
        	lblMessage.setForeground(SWTResourceManager.getColor(204, 51, 51));
        	lblMessage.setText("No JDBC driver specified.");
		}
		else
		{
			// Try to see if the class driver can be found in the classpath...
			try {
				
	            Class.forName(driverName, false, getDriverClassLoader());
	            lblMessage.setForeground( SWTResourceManager.getColor(SWT.COLOR_BLACK) );
	            lblMessage.setVisible(false);
	            lblMessage.setText("Driver found in the classpath");
	        } catch (Throwable ex)
	        {	
	        	lblMessage.setVisible(true);
	        	lblMessage.setForeground(SWTResourceManager.getColor(204, 51, 51));
	        	lblMessage.setText("The driver is not in the classpath, please provide the correct driver jar(s)");
	        }
		}
	}
	
	/**
	 * Collects the provided jar files, and creates an URLClassLoader...
	 * @return
	 */
	protected ClassLoader getDriverClassLoader()
	{
		if (driverClassLoader == null)
		{
			// Now check if the driver is in the classpath or not....
			java.util.List<URL> paths = new ArrayList<URL>();
			
			for (String s : dataAdapter.getClasspathPaths())
			{
				File f = new File(s);
				if (f.exists())
				{
					try {
						paths.add( f.toURI().toURL());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
					}
				}
			}
			driverClassLoader =  new URLClassLoader(paths.toArray(new URL[paths.size()]) );
		}
		return driverClassLoader;
		
	}

	protected void btnRemoveJarActionPerfomed(SelectionEvent e)
	{
		int[] selectedItems = listJars.getSelectionIndices();
	    Arrays.sort(selectedItems);
	    for (int index = selectedItems.length-1; index>=0; --index)
	    {
	    	listJars.remove(index);
	    }
	    listJarsSelectionChanged(null);
	    driverClassLoader=null;
    	driverChanged();
	}
	
	
	protected void btnAddJarActionPerfomed(SelectionEvent e)
	{
		
		FileDialog dialog = new FileDialog(this.getShell(), SWT.OPEN | SWT.MULTI);
	    dialog.setFilterNames(new String[] { "JAR Files", "All Files (*.*)" });
	    dialog.setFilterExtensions(new String[] { "*.jar", "*.*" });
	    //dialog.setFilterPath("");
	    // dialog.setFileName("");
	    
	    if (dialog.open() != null)
	    {
	    	String dir = dialog.getFilterPath();
	    	
	    	String[] jars = dialog.getFileNames();
	    	
	    	for (String s : jars)
	    	{
	    		listJars.add((new File(dir, s)).getPath());
	    	}
	    	listJarsSelectionChanged(null);
	    	driverClassLoader=null;
	    	driverChanged();
	    }
	}
	
	protected void listJarsSelectionChanged(SelectionEvent e)
	{
		int[] selectedItems = listJars.getSelectionIndices();
		btnRemoveJar.setEnabled( selectedItems.length > 0);
	}

	
	/**
	 * Set the DataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(JDBCDataAdapter editingDataAdapter) {
		dataAdapter = editingDataAdapter;
		
		String driverName = Misc.nvl(dataAdapter.getJDBCDriver(), "");
		comboJDBCDriver.getCombo().setText(driverName);
		textUsername.setText(Misc.nvl(dataAdapter.getUsername(),""));
		textPassword.setText(Misc.nvl(dataAdapter.getPassword(),""));
		textJDBCUrl.setText(Misc.nvl(dataAdapter.getUrl(),""));
		textServerAddress.setText(Misc.nvl(dataAdapter.getServerAddress(),""));
		textDatabase.setText(Misc.nvl(dataAdapter.getDatabase(),""));
		btnSavePassword.setSelection( dataAdapter.isSavePassword() );
		
		listJars.removeAll();
		for (String s : dataAdapter.getClasspathPaths())
		{
			listJars.add(s);
		}
		
		driverChanged(); //Fire a driver change event...
	}
	
	public DataAdapter getDataAdapter() {
		if(dataAdapter == null){
			dataAdapter = new JDBCDataAdapter();
		}
		dataAdapter.setJDBCDriver( comboJDBCDriver.getCombo().getText());
		dataAdapter.setUsername( textUsername.getText());
		dataAdapter.setPassword( textPassword.getText());
		dataAdapter.setUrl( textJDBCUrl.getText());
		dataAdapter.setDatabase( textDatabase.getText());
		dataAdapter.setServerAddress( textServerAddress.getText());
		dataAdapter.setSavePassword( btnSavePassword.getSelection() );
		dataAdapter.getClasspathPaths().clear();
		for (int i=0; i<listJars.getItemCount(); ++i)
		{
			dataAdapter.getClasspathPaths().add(listJars.getItem(i));
		}
		
		return dataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
