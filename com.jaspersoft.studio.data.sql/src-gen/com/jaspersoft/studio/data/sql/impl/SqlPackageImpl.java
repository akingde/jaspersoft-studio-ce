/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Col;
import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.OrColumn;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.OrSelect;
import com.jaspersoft.studio.data.sql.OrderByColumnFull;
import com.jaspersoft.studio.data.sql.OrderByColumns;
import com.jaspersoft.studio.data.sql.Select;
import com.jaspersoft.studio.data.sql.SqlFactory;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SqlPackageImpl extends EPackageImpl implements SqlPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnOrAliasEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnFullEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dbObjectNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orderByColumnsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orderByColumnFullEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orSelectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orColumnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass colEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orOrderByColumnEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see com.jaspersoft.studio.data.sql.SqlPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private SqlPackageImpl()
  {
    super(eNS_URI, SqlFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link SqlPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static SqlPackage init()
  {
    if (isInited) return (SqlPackage)EPackage.Registry.INSTANCE.getEPackage(SqlPackage.eNS_URI);

    // Obtain or create and register package
    SqlPackageImpl theSqlPackage = (SqlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SqlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SqlPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theSqlPackage.createPackageContents();

    // Initialize created meta-data
    theSqlPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSqlPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(SqlPackage.eNS_URI, theSqlPackage);
    return theSqlPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModel()
  {
    return modelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModel_OrderByEntry()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelect()
  {
    return selectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelect_Select()
  {
    return (EAttribute)selectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelect_Cols()
  {
    return (EReference)selectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumns()
  {
    return columnsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumnOrAlias()
  {
    return columnOrAliasEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColumnOrAlias_AllCols()
  {
    return (EAttribute)columnOrAliasEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumnFull()
  {
    return columnFullEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getColumnFull_ColAlias()
  {
    return (EReference)columnFullEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDbObjectName()
  {
    return dbObjectNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDbObjectName_Dbname()
  {
    return (EAttribute)dbObjectNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrderByColumns()
  {
    return orderByColumnsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrderByColumnFull()
  {
    return orderByColumnFullEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrderByColumnFull_ColOrder()
  {
    return (EReference)orderByColumnFullEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrSelect()
  {
    return orSelectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrSelect_Entries()
  {
    return (EReference)orSelectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrColumn()
  {
    return orColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrColumn_Entries()
  {
    return (EReference)orColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCol()
  {
    return colEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCol_Entries()
  {
    return (EReference)colEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrOrderByColumn()
  {
    return orOrderByColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrOrderByColumn_Entries()
  {
    return (EReference)orOrderByColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlFactory getSqlFactory()
  {
    return (SqlFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelEClass = createEClass(MODEL);
    createEReference(modelEClass, MODEL__ORDER_BY_ENTRY);

    selectEClass = createEClass(SELECT);
    createEAttribute(selectEClass, SELECT__SELECT);
    createEReference(selectEClass, SELECT__COLS);

    columnsEClass = createEClass(COLUMNS);

    columnOrAliasEClass = createEClass(COLUMN_OR_ALIAS);
    createEAttribute(columnOrAliasEClass, COLUMN_OR_ALIAS__ALL_COLS);

    columnFullEClass = createEClass(COLUMN_FULL);
    createEReference(columnFullEClass, COLUMN_FULL__COL_ALIAS);

    dbObjectNameEClass = createEClass(DB_OBJECT_NAME);
    createEAttribute(dbObjectNameEClass, DB_OBJECT_NAME__DBNAME);

    orderByColumnsEClass = createEClass(ORDER_BY_COLUMNS);

    orderByColumnFullEClass = createEClass(ORDER_BY_COLUMN_FULL);
    createEReference(orderByColumnFullEClass, ORDER_BY_COLUMN_FULL__COL_ORDER);

    orSelectEClass = createEClass(OR_SELECT);
    createEReference(orSelectEClass, OR_SELECT__ENTRIES);

    orColumnEClass = createEClass(OR_COLUMN);
    createEReference(orColumnEClass, OR_COLUMN__ENTRIES);

    colEClass = createEClass(COL);
    createEReference(colEClass, COL__ENTRIES);

    orOrderByColumnEClass = createEClass(OR_ORDER_BY_COLUMN);
    createEReference(orOrderByColumnEClass, OR_ORDER_BY_COLUMN__ENTRIES);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    selectEClass.getESuperTypes().add(this.getModel());
    columnOrAliasEClass.getESuperTypes().add(this.getColumns());
    columnFullEClass.getESuperTypes().add(this.getColumnOrAlias());
    dbObjectNameEClass.getESuperTypes().add(this.getColumnFull());
    orderByColumnFullEClass.getESuperTypes().add(this.getOrderByColumns());
    orSelectEClass.getESuperTypes().add(this.getModel());
    orColumnEClass.getESuperTypes().add(this.getColumns());
    colEClass.getESuperTypes().add(this.getColumnFull());
    orOrderByColumnEClass.getESuperTypes().add(this.getOrderByColumns());

    // Initialize classes and features; add operations and parameters
    initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModel_OrderByEntry(), this.getOrderByColumns(), null, "orderByEntry", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selectEClass, Select.class, "Select", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSelect_Select(), ecorePackage.getEString(), "select", null, 0, 1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSelect_Cols(), this.getColumns(), null, "cols", null, 0, 1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(columnsEClass, Columns.class, "Columns", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(columnOrAliasEClass, ColumnOrAlias.class, "ColumnOrAlias", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColumnOrAlias_AllCols(), ecorePackage.getEString(), "allCols", null, 0, 1, ColumnOrAlias.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(columnFullEClass, ColumnFull.class, "ColumnFull", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getColumnFull_ColAlias(), this.getDbObjectName(), null, "colAlias", null, 0, 1, ColumnFull.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dbObjectNameEClass, DbObjectName.class, "DbObjectName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDbObjectName_Dbname(), ecorePackage.getEString(), "dbname", null, 0, 1, DbObjectName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orderByColumnsEClass, OrderByColumns.class, "OrderByColumns", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(orderByColumnFullEClass, OrderByColumnFull.class, "OrderByColumnFull", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrderByColumnFull_ColOrder(), this.getColumnFull(), null, "colOrder", null, 0, 1, OrderByColumnFull.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orSelectEClass, OrSelect.class, "OrSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrSelect_Entries(), this.getSelect(), null, "entries", null, 0, -1, OrSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orColumnEClass, OrColumn.class, "OrColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrColumn_Entries(), this.getColumnOrAlias(), null, "entries", null, 0, -1, OrColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(colEClass, Col.class, "Col", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCol_Entries(), this.getDbObjectName(), null, "entries", null, 0, -1, Col.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orOrderByColumnEClass, OrOrderByColumn.class, "OrOrderByColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrOrderByColumn_Entries(), this.getOrderByColumnFull(), null, "entries", null, 0, -1, OrOrderByColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //SqlPackageImpl
