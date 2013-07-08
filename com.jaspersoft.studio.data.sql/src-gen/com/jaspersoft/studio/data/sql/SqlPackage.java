/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlFactory
 * @model kind="package"
 * @generated
 */
public interface SqlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "sql";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.com.jaspersoft.studio.data.Sql";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "sql";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SqlPackage eINSTANCE = com.jaspersoft.studio.data.sql.impl.SqlPackageImpl.init();

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ModelImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ORDER_BY_ENTRY = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.SelectImpl <em>Select</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.SelectImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getSelect()
   * @generated
   */
  int SELECT = 1;

  /**
   * The feature id for the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__ORDER_BY_ENTRY = MODEL__ORDER_BY_ENTRY;

  /**
   * The feature id for the '<em><b>Select</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__SELECT = MODEL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__COLS = MODEL_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Select</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT_FEATURE_COUNT = MODEL_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnsImpl <em>Columns</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ColumnsImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumns()
   * @generated
   */
  int COLUMNS = 2;

  /**
   * The number of structural features of the '<em>Columns</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMNS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl <em>Column Or Alias</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumnOrAlias()
   * @generated
   */
  int COLUMN_OR_ALIAS = 3;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_OR_ALIAS__ALL_COLS = COLUMNS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Column Or Alias</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_OR_ALIAS_FEATURE_COUNT = COLUMNS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl <em>Column Full</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ColumnFullImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumnFull()
   * @generated
   */
  int COLUMN_FULL = 4;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL__ALL_COLS = COLUMN_OR_ALIAS__ALL_COLS;

  /**
   * The feature id for the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL__COL_ALIAS = COLUMN_OR_ALIAS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Column Full</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL_FEATURE_COUNT = COLUMN_OR_ALIAS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl <em>Db Object Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDbObjectName()
   * @generated
   */
  int DB_OBJECT_NAME = 5;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME__ALL_COLS = COLUMN_FULL__ALL_COLS;

  /**
   * The feature id for the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME__COL_ALIAS = COLUMN_FULL__COL_ALIAS;

  /**
   * The feature id for the '<em><b>Dbname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME__DBNAME = COLUMN_FULL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Db Object Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME_FEATURE_COUNT = COLUMN_FULL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrderByColumnsImpl <em>Order By Columns</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrderByColumnsImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrderByColumns()
   * @generated
   */
  int ORDER_BY_COLUMNS = 6;

  /**
   * The number of structural features of the '<em>Order By Columns</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_BY_COLUMNS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrderByColumnFullImpl <em>Order By Column Full</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrderByColumnFullImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrderByColumnFull()
   * @generated
   */
  int ORDER_BY_COLUMN_FULL = 7;

  /**
   * The feature id for the '<em><b>Col Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_BY_COLUMN_FULL__COL_ORDER = ORDER_BY_COLUMNS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Order By Column Full</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_BY_COLUMN_FULL_FEATURE_COUNT = ORDER_BY_COLUMNS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrSelectImpl <em>Or Select</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrSelectImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrSelect()
   * @generated
   */
  int OR_SELECT = 8;

  /**
   * The feature id for the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_SELECT__ORDER_BY_ENTRY = MODEL__ORDER_BY_ENTRY;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_SELECT__ENTRIES = MODEL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Select</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_SELECT_FEATURE_COUNT = MODEL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrColumnImpl <em>Or Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrColumnImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrColumn()
   * @generated
   */
  int OR_COLUMN = 9;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_COLUMN__ENTRIES = COLUMNS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_COLUMN_FEATURE_COUNT = COLUMNS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ColImpl <em>Col</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ColImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getCol()
   * @generated
   */
  int COL = 10;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL__ALL_COLS = COLUMN_FULL__ALL_COLS;

  /**
   * The feature id for the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL__COL_ALIAS = COLUMN_FULL__COL_ALIAS;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL__ENTRIES = COLUMN_FULL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Col</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL_FEATURE_COUNT = COLUMN_FULL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl <em>Or Order By Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrOrderByColumn()
   * @generated
   */
  int OR_ORDER_BY_COLUMN = 11;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_ORDER_BY_COLUMN__ENTRIES = ORDER_BY_COLUMNS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Order By Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_ORDER_BY_COLUMN_FEATURE_COUNT = ORDER_BY_COLUMNS_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see com.jaspersoft.studio.data.sql.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Model#getOrderByEntry <em>Order By Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Order By Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.Model#getOrderByEntry()
   * @see #getModel()
   * @generated
   */
  EReference getModel_OrderByEntry();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Select <em>Select</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Select</em>'.
   * @see com.jaspersoft.studio.data.sql.Select
   * @generated
   */
  EClass getSelect();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.Select#getSelect <em>Select</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Select</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getSelect()
   * @see #getSelect()
   * @generated
   */
  EAttribute getSelect_Select();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Select#getCols <em>Cols</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cols</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getCols()
   * @see #getSelect()
   * @generated
   */
  EReference getSelect_Cols();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Columns <em>Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Columns</em>'.
   * @see com.jaspersoft.studio.data.sql.Columns
   * @generated
   */
  EClass getColumns();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.ColumnOrAlias <em>Column Or Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column Or Alias</em>'.
   * @see com.jaspersoft.studio.data.sql.ColumnOrAlias
   * @generated
   */
  EClass getColumnOrAlias();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.ColumnOrAlias#getAllCols <em>All Cols</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>All Cols</em>'.
   * @see com.jaspersoft.studio.data.sql.ColumnOrAlias#getAllCols()
   * @see #getColumnOrAlias()
   * @generated
   */
  EAttribute getColumnOrAlias_AllCols();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.ColumnFull <em>Column Full</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column Full</em>'.
   * @see com.jaspersoft.studio.data.sql.ColumnFull
   * @generated
   */
  EClass getColumnFull();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.ColumnFull#getColAlias <em>Col Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col Alias</em>'.
   * @see com.jaspersoft.studio.data.sql.ColumnFull#getColAlias()
   * @see #getColumnFull()
   * @generated
   */
  EReference getColumnFull_ColAlias();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.DbObjectName <em>Db Object Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Db Object Name</em>'.
   * @see com.jaspersoft.studio.data.sql.DbObjectName
   * @generated
   */
  EClass getDbObjectName();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.DbObjectName#getDbname <em>Dbname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dbname</em>'.
   * @see com.jaspersoft.studio.data.sql.DbObjectName#getDbname()
   * @see #getDbObjectName()
   * @generated
   */
  EAttribute getDbObjectName_Dbname();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrderByColumns <em>Order By Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Order By Columns</em>'.
   * @see com.jaspersoft.studio.data.sql.OrderByColumns
   * @generated
   */
  EClass getOrderByColumns();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrderByColumnFull <em>Order By Column Full</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Order By Column Full</em>'.
   * @see com.jaspersoft.studio.data.sql.OrderByColumnFull
   * @generated
   */
  EClass getOrderByColumnFull();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.OrderByColumnFull#getColOrder <em>Col Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col Order</em>'.
   * @see com.jaspersoft.studio.data.sql.OrderByColumnFull#getColOrder()
   * @see #getOrderByColumnFull()
   * @generated
   */
  EReference getOrderByColumnFull_ColOrder();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrSelect <em>Or Select</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Select</em>'.
   * @see com.jaspersoft.studio.data.sql.OrSelect
   * @generated
   */
  EClass getOrSelect();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrSelect#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrSelect#getEntries()
   * @see #getOrSelect()
   * @generated
   */
  EReference getOrSelect_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrColumn <em>Or Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Column</em>'.
   * @see com.jaspersoft.studio.data.sql.OrColumn
   * @generated
   */
  EClass getOrColumn();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrColumn#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrColumn#getEntries()
   * @see #getOrColumn()
   * @generated
   */
  EReference getOrColumn_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Col <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Col</em>'.
   * @see com.jaspersoft.studio.data.sql.Col
   * @generated
   */
  EClass getCol();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.Col#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.Col#getEntries()
   * @see #getCol()
   * @generated
   */
  EReference getCol_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrOrderByColumn <em>Or Order By Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Order By Column</em>'.
   * @see com.jaspersoft.studio.data.sql.OrOrderByColumn
   * @generated
   */
  EClass getOrOrderByColumn();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrOrderByColumn#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrOrderByColumn#getEntries()
   * @see #getOrOrderByColumn()
   * @generated
   */
  EReference getOrOrderByColumn_Entries();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SqlFactory getSqlFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ModelImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Order By Entry</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__ORDER_BY_ENTRY = eINSTANCE.getModel_OrderByEntry();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.SelectImpl <em>Select</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.SelectImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getSelect()
     * @generated
     */
    EClass SELECT = eINSTANCE.getSelect();

    /**
     * The meta object literal for the '<em><b>Select</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELECT__SELECT = eINSTANCE.getSelect_Select();

    /**
     * The meta object literal for the '<em><b>Cols</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECT__COLS = eINSTANCE.getSelect_Cols();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnsImpl <em>Columns</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ColumnsImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumns()
     * @generated
     */
    EClass COLUMNS = eINSTANCE.getColumns();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl <em>Column Or Alias</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumnOrAlias()
     * @generated
     */
    EClass COLUMN_OR_ALIAS = eINSTANCE.getColumnOrAlias();

    /**
     * The meta object literal for the '<em><b>All Cols</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLUMN_OR_ALIAS__ALL_COLS = eINSTANCE.getColumnOrAlias_AllCols();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl <em>Column Full</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ColumnFullImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumnFull()
     * @generated
     */
    EClass COLUMN_FULL = eINSTANCE.getColumnFull();

    /**
     * The meta object literal for the '<em><b>Col Alias</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLUMN_FULL__COL_ALIAS = eINSTANCE.getColumnFull_ColAlias();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl <em>Db Object Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDbObjectName()
     * @generated
     */
    EClass DB_OBJECT_NAME = eINSTANCE.getDbObjectName();

    /**
     * The meta object literal for the '<em><b>Dbname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DB_OBJECT_NAME__DBNAME = eINSTANCE.getDbObjectName_Dbname();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrderByColumnsImpl <em>Order By Columns</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrderByColumnsImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrderByColumns()
     * @generated
     */
    EClass ORDER_BY_COLUMNS = eINSTANCE.getOrderByColumns();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrderByColumnFullImpl <em>Order By Column Full</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrderByColumnFullImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrderByColumnFull()
     * @generated
     */
    EClass ORDER_BY_COLUMN_FULL = eINSTANCE.getOrderByColumnFull();

    /**
     * The meta object literal for the '<em><b>Col Order</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORDER_BY_COLUMN_FULL__COL_ORDER = eINSTANCE.getOrderByColumnFull_ColOrder();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrSelectImpl <em>Or Select</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrSelectImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrSelect()
     * @generated
     */
    EClass OR_SELECT = eINSTANCE.getOrSelect();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_SELECT__ENTRIES = eINSTANCE.getOrSelect_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrColumnImpl <em>Or Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrColumnImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrColumn()
     * @generated
     */
    EClass OR_COLUMN = eINSTANCE.getOrColumn();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_COLUMN__ENTRIES = eINSTANCE.getOrColumn_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ColImpl <em>Col</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ColImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getCol()
     * @generated
     */
    EClass COL = eINSTANCE.getCol();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COL__ENTRIES = eINSTANCE.getCol_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl <em>Or Order By Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrOrderByColumn()
     * @generated
     */
    EClass OR_ORDER_BY_COLUMN = eINSTANCE.getOrOrderByColumn();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_ORDER_BY_COLUMN__ENTRIES = eINSTANCE.getOrOrderByColumn_Entries();

  }

} //SqlPackage
