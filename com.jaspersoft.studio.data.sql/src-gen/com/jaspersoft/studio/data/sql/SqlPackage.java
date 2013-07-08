/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
   * The feature id for the '<em><b>Tbl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__TBL = MODEL_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Where Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__WHERE_EXPRESSION = MODEL_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Group By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__GROUP_BY_ENTRY = MODEL_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Having Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT__HAVING_ENTRY = MODEL_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Select</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECT_FEATURE_COUNT = MODEL_FEATURE_COUNT + 6;

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
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL__SCALAR = COLUMN_OR_ALIAS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL__COL_ALIAS = COLUMN_OR_ALIAS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Column Full</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FULL_FEATURE_COUNT = COLUMN_OR_ALIAS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrTableImpl <em>Or Table</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrTableImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrTable()
   * @generated
   */
  int OR_TABLE = 5;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_TABLE__ENTRIES = 0;

  /**
   * The number of structural features of the '<em>Or Table</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_TABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl <em>From Table</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.FromTableImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getFromTable()
   * @generated
   */
  int FROM_TABLE = 6;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE__ENTRIES = OR_TABLE__ENTRIES;

  /**
   * The feature id for the '<em><b>Table</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE__TABLE = OR_TABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Join</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE__JOIN = OR_TABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>On Table</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE__ON_TABLE = OR_TABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Join Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE__JOIN_EXPR = OR_TABLE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>From Table</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FROM_TABLE_FEATURE_COUNT = OR_TABLE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.TableOrAliasImpl <em>Table Or Alias</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.TableOrAliasImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getTableOrAlias()
   * @generated
   */
  int TABLE_OR_ALIAS = 7;

  /**
   * The feature id for the '<em><b>Tfull</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_OR_ALIAS__TFULL = 0;

  /**
   * The feature id for the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_OR_ALIAS__ALIAS = 1;

  /**
   * The feature id for the '<em><b>Tbl Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_OR_ALIAS__TBL_ALIAS = 2;

  /**
   * The number of structural features of the '<em>Table Or Alias</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_OR_ALIAS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl <em>Table Full</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.TableFullImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getTableFull()
   * @generated
   */
  int TABLE_FULL = 8;

  /**
   * The number of structural features of the '<em>Table Full</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_FULL_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl <em>Db Object Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDbObjectName()
   * @generated
   */
  int DB_OBJECT_NAME = 9;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME__ALL_COLS = COLUMN_FULL__ALL_COLS;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_OBJECT_NAME__SCALAR = COLUMN_FULL__SCALAR;

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
  int ORDER_BY_COLUMNS = 10;

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
  int ORDER_BY_COLUMN_FULL = 11;

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
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnsImpl <em>Group By Columns</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.GroupByColumnsImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getGroupByColumns()
   * @generated
   */
  int GROUP_BY_COLUMNS = 12;

  /**
   * The number of structural features of the '<em>Group By Columns</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_BY_COLUMNS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl <em>Full Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.FullExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getFullExpression()
   * @generated
   */
  int FULL_EXPRESSION = 13;

  /**
   * The feature id for the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION__OP1 = 0;

  /**
   * The feature id for the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION__IN = 1;

  /**
   * The feature id for the '<em><b>Between</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION__BETWEEN = 2;

  /**
   * The feature id for the '<em><b>Like</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION__LIKE = 3;

  /**
   * The feature id for the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION__COMP = 4;

  /**
   * The number of structural features of the '<em>Full Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_EXPRESSION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ComparisonImpl <em>Comparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ComparisonImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getComparison()
   * @generated
   */
  int COMPARISON = 14;

  /**
   * The feature id for the '<em><b>Op2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON__OP2 = 0;

  /**
   * The number of structural features of the '<em>Comparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.BetweenImpl <em>Between</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.BetweenImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBetween()
   * @generated
   */
  int BETWEEN = 15;

  /**
   * The feature id for the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BETWEEN__OP1 = 0;

  /**
   * The feature id for the '<em><b>Op2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BETWEEN__OP2 = 1;

  /**
   * The number of structural features of the '<em>Between</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BETWEEN_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.InOperatorImpl <em>In Operator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.InOperatorImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getInOperator()
   * @generated
   */
  int IN_OPERATOR = 16;

  /**
   * The number of structural features of the '<em>In Operator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IN_OPERATOR_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OperandImpl <em>Operand</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OperandImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOperand()
   * @generated
   */
  int OPERAND = 17;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERAND__SCALAR = 0;

  /**
   * The number of structural features of the '<em>Operand</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERAND_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrSelectImpl <em>Or Select</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrSelectImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrSelect()
   * @generated
   */
  int OR_SELECT = 18;

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
  int OR_COLUMN = 19;

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
  int COL = 20;

  /**
   * The feature id for the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL__ALL_COLS = COLUMN_FULL__ALL_COLS;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COL__SCALAR = COLUMN_FULL__SCALAR;

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
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.tblsImpl <em>tbls</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.tblsImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#gettbls()
   * @generated
   */
  int TBLS = 21;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TBLS__ENTRIES = TABLE_FULL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>tbls</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TBLS_FEATURE_COUNT = TABLE_FULL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl <em>Or Order By Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrOrderByColumnImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrOrderByColumn()
   * @generated
   */
  int OR_ORDER_BY_COLUMN = 22;

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
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrGroupByColumnImpl <em>Or Group By Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrGroupByColumnImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrGroupByColumn()
   * @generated
   */
  int OR_GROUP_BY_COLUMN = 23;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_BY_COLUMN__ENTRIES = GROUP_BY_COLUMNS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Group By Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_BY_COLUMN_FEATURE_COUNT = GROUP_BY_COLUMNS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.fexprImpl <em>fexpr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.fexprImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getfexpr()
   * @generated
   */
  int FEXPR = 24;

  /**
   * The feature id for the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__OP1 = FULL_EXPRESSION__OP1;

  /**
   * The feature id for the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__IN = FULL_EXPRESSION__IN;

  /**
   * The feature id for the '<em><b>Between</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__BETWEEN = FULL_EXPRESSION__BETWEEN;

  /**
   * The feature id for the '<em><b>Like</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__LIKE = FULL_EXPRESSION__LIKE;

  /**
   * The feature id for the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__COMP = FULL_EXPRESSION__COMP;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR__ENTRIES = FULL_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>fexpr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEXPR_FEATURE_COUNT = FULL_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.exprGroupImpl <em>expr Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.exprGroupImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getexprGroup()
   * @generated
   */
  int EXPR_GROUP = 25;

  /**
   * The feature id for the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__OP1 = FULL_EXPRESSION__OP1;

  /**
   * The feature id for the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__IN = FULL_EXPRESSION__IN;

  /**
   * The feature id for the '<em><b>Between</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__BETWEEN = FULL_EXPRESSION__BETWEEN;

  /**
   * The feature id for the '<em><b>Like</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__LIKE = FULL_EXPRESSION__LIKE;

  /**
   * The feature id for the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__COMP = FULL_EXPRESSION__COMP;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP__EXPR = FULL_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>expr Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_GROUP_FEATURE_COUNT = FULL_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.xexprImpl <em>xexpr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.xexprImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getxexpr()
   * @generated
   */
  int XEXPR = 26;

  /**
   * The feature id for the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR__OP1 = FULL_EXPRESSION__OP1;

  /**
   * The feature id for the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR__IN = FULL_EXPRESSION__IN;

  /**
   * The feature id for the '<em><b>Between</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR__BETWEEN = FULL_EXPRESSION__BETWEEN;

  /**
   * The feature id for the '<em><b>Like</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR__LIKE = FULL_EXPRESSION__LIKE;

  /**
   * The feature id for the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR__COMP = FULL_EXPRESSION__COMP;

  /**
   * The number of structural features of the '<em>xexpr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XEXPR_FEATURE_COUNT = FULL_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.inopImpl <em>inop</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.inopImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getinop()
   * @generated
   */
  int INOP = 27;

  /**
   * The feature id for the '<em><b>Subquery</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INOP__SUBQUERY = IN_OPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>inop</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INOP_FEATURE_COUNT = IN_OPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.xopImpl <em>xop</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.xopImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getxop()
   * @generated
   */
  int XOP = 28;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOP__ENTRIES = IN_OPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>xop</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOP_FEATURE_COUNT = IN_OPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.operandsImpl <em>operands</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.operandsImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getoperands()
   * @generated
   */
  int OPERANDS = 29;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERANDS__SCALAR = OPERAND__SCALAR;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERANDS__ENTRIES = OPERAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>operands</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERANDS_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.opGroupImpl <em>op Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.opGroupImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getopGroup()
   * @generated
   */
  int OP_GROUP = 30;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OP_GROUP__SCALAR = OPERAND__SCALAR;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OP_GROUP__OP = OPERAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>op Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OP_GROUP_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.poperandImpl <em>poperand</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.poperandImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getpoperand()
   * @generated
   */
  int POPERAND = 31;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POPERAND__SCALAR = OPERAND__SCALAR;

  /**
   * The number of structural features of the '<em>poperand</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POPERAND_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.expoperandImpl <em>expoperand</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.expoperandImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getexpoperand()
   * @generated
   */
  int EXPOPERAND = 32;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPOPERAND__SCALAR = OPERAND__SCALAR;

  /**
   * The number of structural features of the '<em>expoperand</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPOPERAND_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.subqueryImpl <em>subquery</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.subqueryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getsubquery()
   * @generated
   */
  int SUBQUERY = 33;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBQUERY__SCALAR = OPERAND__SCALAR;

  /**
   * The feature id for the '<em><b>Sel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBQUERY__SEL = OPERAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>subquery</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBQUERY_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.JoinType <em>Join Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.JoinType
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getJoinType()
   * @generated
   */
  int JOIN_TYPE = 34;


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
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Select#getTbl <em>Tbl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tbl</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getTbl()
   * @see #getSelect()
   * @generated
   */
  EReference getSelect_Tbl();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Select#getWhereExpression <em>Where Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Where Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getWhereExpression()
   * @see #getSelect()
   * @generated
   */
  EReference getSelect_WhereExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Select#getGroupByEntry <em>Group By Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Group By Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getGroupByEntry()
   * @see #getSelect()
   * @generated
   */
  EReference getSelect_GroupByEntry();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Select#getHavingEntry <em>Having Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Having Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.Select#getHavingEntry()
   * @see #getSelect()
   * @generated
   */
  EReference getSelect_HavingEntry();

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
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrTable <em>Or Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Table</em>'.
   * @see com.jaspersoft.studio.data.sql.OrTable
   * @generated
   */
  EClass getOrTable();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrTable#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrTable#getEntries()
   * @see #getOrTable()
   * @generated
   */
  EReference getOrTable_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.FromTable <em>From Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>From Table</em>'.
   * @see com.jaspersoft.studio.data.sql.FromTable
   * @generated
   */
  EClass getFromTable();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FromTable#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Table</em>'.
   * @see com.jaspersoft.studio.data.sql.FromTable#getTable()
   * @see #getFromTable()
   * @generated
   */
  EReference getFromTable_Table();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.FromTable#getJoin <em>Join</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Join</em>'.
   * @see com.jaspersoft.studio.data.sql.FromTable#getJoin()
   * @see #getFromTable()
   * @generated
   */
  EAttribute getFromTable_Join();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FromTable#getOnTable <em>On Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>On Table</em>'.
   * @see com.jaspersoft.studio.data.sql.FromTable#getOnTable()
   * @see #getFromTable()
   * @generated
   */
  EReference getFromTable_OnTable();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FromTable#getJoinExpr <em>Join Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Join Expr</em>'.
   * @see com.jaspersoft.studio.data.sql.FromTable#getJoinExpr()
   * @see #getFromTable()
   * @generated
   */
  EReference getFromTable_JoinExpr();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.TableOrAlias <em>Table Or Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table Or Alias</em>'.
   * @see com.jaspersoft.studio.data.sql.TableOrAlias
   * @generated
   */
  EClass getTableOrAlias();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.TableOrAlias#getTfull <em>Tfull</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tfull</em>'.
   * @see com.jaspersoft.studio.data.sql.TableOrAlias#getTfull()
   * @see #getTableOrAlias()
   * @generated
   */
  EReference getTableOrAlias_Tfull();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.TableOrAlias#getAlias <em>Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Alias</em>'.
   * @see com.jaspersoft.studio.data.sql.TableOrAlias#getAlias()
   * @see #getTableOrAlias()
   * @generated
   */
  EAttribute getTableOrAlias_Alias();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.TableOrAlias#getTblAlias <em>Tbl Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tbl Alias</em>'.
   * @see com.jaspersoft.studio.data.sql.TableOrAlias#getTblAlias()
   * @see #getTableOrAlias()
   * @generated
   */
  EReference getTableOrAlias_TblAlias();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.TableFull <em>Table Full</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table Full</em>'.
   * @see com.jaspersoft.studio.data.sql.TableFull
   * @generated
   */
  EClass getTableFull();

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
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.GroupByColumns <em>Group By Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group By Columns</em>'.
   * @see com.jaspersoft.studio.data.sql.GroupByColumns
   * @generated
   */
  EClass getGroupByColumns();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.FullExpression <em>Full Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Full Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression
   * @generated
   */
  EClass getFullExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FullExpression#getOp1 <em>Op1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op1</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression#getOp1()
   * @see #getFullExpression()
   * @generated
   */
  EReference getFullExpression_Op1();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FullExpression#getIn <em>In</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>In</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression#getIn()
   * @see #getFullExpression()
   * @generated
   */
  EReference getFullExpression_In();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FullExpression#getBetween <em>Between</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Between</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression#getBetween()
   * @see #getFullExpression()
   * @generated
   */
  EReference getFullExpression_Between();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.FullExpression#getLike <em>Like</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Like</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression#getLike()
   * @see #getFullExpression()
   * @generated
   */
  EAttribute getFullExpression_Like();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.FullExpression#getComp <em>Comp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comp</em>'.
   * @see com.jaspersoft.studio.data.sql.FullExpression#getComp()
   * @see #getFullExpression()
   * @generated
   */
  EReference getFullExpression_Comp();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Comparison <em>Comparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Comparison</em>'.
   * @see com.jaspersoft.studio.data.sql.Comparison
   * @generated
   */
  EClass getComparison();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Comparison#getOp2 <em>Op2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op2</em>'.
   * @see com.jaspersoft.studio.data.sql.Comparison#getOp2()
   * @see #getComparison()
   * @generated
   */
  EReference getComparison_Op2();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Between <em>Between</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Between</em>'.
   * @see com.jaspersoft.studio.data.sql.Between
   * @generated
   */
  EClass getBetween();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Between#getOp1 <em>Op1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op1</em>'.
   * @see com.jaspersoft.studio.data.sql.Between#getOp1()
   * @see #getBetween()
   * @generated
   */
  EReference getBetween_Op1();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Between#getOp2 <em>Op2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op2</em>'.
   * @see com.jaspersoft.studio.data.sql.Between#getOp2()
   * @see #getBetween()
   * @generated
   */
  EReference getBetween_Op2();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.InOperator <em>In Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>In Operator</em>'.
   * @see com.jaspersoft.studio.data.sql.InOperator
   * @generated
   */
  EClass getInOperator();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Operand <em>Operand</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operand</em>'.
   * @see com.jaspersoft.studio.data.sql.Operand
   * @generated
   */
  EClass getOperand();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.Operand#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scalar</em>'.
   * @see com.jaspersoft.studio.data.sql.Operand#getScalar()
   * @see #getOperand()
   * @generated
   */
  EAttribute getOperand_Scalar();

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
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.tbls <em>tbls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>tbls</em>'.
   * @see com.jaspersoft.studio.data.sql.tbls
   * @generated
   */
  EClass gettbls();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.tbls#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.tbls#getEntries()
   * @see #gettbls()
   * @generated
   */
  EReference gettbls_Entries();

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
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrGroupByColumn <em>Or Group By Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Group By Column</em>'.
   * @see com.jaspersoft.studio.data.sql.OrGroupByColumn
   * @generated
   */
  EClass getOrGroupByColumn();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrGroupByColumn#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrGroupByColumn#getEntries()
   * @see #getOrGroupByColumn()
   * @generated
   */
  EReference getOrGroupByColumn_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.fexpr <em>fexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>fexpr</em>'.
   * @see com.jaspersoft.studio.data.sql.fexpr
   * @generated
   */
  EClass getfexpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.fexpr#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.fexpr#getEntries()
   * @see #getfexpr()
   * @generated
   */
  EReference getfexpr_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.exprGroup <em>expr Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expr Group</em>'.
   * @see com.jaspersoft.studio.data.sql.exprGroup
   * @generated
   */
  EClass getexprGroup();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.exprGroup#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.jaspersoft.studio.data.sql.exprGroup#getExpr()
   * @see #getexprGroup()
   * @generated
   */
  EReference getexprGroup_Expr();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.xexpr <em>xexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>xexpr</em>'.
   * @see com.jaspersoft.studio.data.sql.xexpr
   * @generated
   */
  EClass getxexpr();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.inop <em>inop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>inop</em>'.
   * @see com.jaspersoft.studio.data.sql.inop
   * @generated
   */
  EClass getinop();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.inop#getSubquery <em>Subquery</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Subquery</em>'.
   * @see com.jaspersoft.studio.data.sql.inop#getSubquery()
   * @see #getinop()
   * @generated
   */
  EReference getinop_Subquery();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.xop <em>xop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>xop</em>'.
   * @see com.jaspersoft.studio.data.sql.xop
   * @generated
   */
  EClass getxop();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.xop#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.xop#getEntries()
   * @see #getxop()
   * @generated
   */
  EReference getxop_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.operands <em>operands</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>operands</em>'.
   * @see com.jaspersoft.studio.data.sql.operands
   * @generated
   */
  EClass getoperands();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.operands#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.operands#getEntries()
   * @see #getoperands()
   * @generated
   */
  EReference getoperands_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.opGroup <em>op Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>op Group</em>'.
   * @see com.jaspersoft.studio.data.sql.opGroup
   * @generated
   */
  EClass getopGroup();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.opGroup#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see com.jaspersoft.studio.data.sql.opGroup#getOp()
   * @see #getopGroup()
   * @generated
   */
  EReference getopGroup_Op();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.poperand <em>poperand</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>poperand</em>'.
   * @see com.jaspersoft.studio.data.sql.poperand
   * @generated
   */
  EClass getpoperand();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.expoperand <em>expoperand</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expoperand</em>'.
   * @see com.jaspersoft.studio.data.sql.expoperand
   * @generated
   */
  EClass getexpoperand();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.subquery <em>subquery</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>subquery</em>'.
   * @see com.jaspersoft.studio.data.sql.subquery
   * @generated
   */
  EClass getsubquery();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.subquery#getSel <em>Sel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sel</em>'.
   * @see com.jaspersoft.studio.data.sql.subquery#getSel()
   * @see #getsubquery()
   * @generated
   */
  EReference getsubquery_Sel();

  /**
   * Returns the meta object for enum '{@link com.jaspersoft.studio.data.sql.JoinType <em>Join Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Join Type</em>'.
   * @see com.jaspersoft.studio.data.sql.JoinType
   * @generated
   */
  EEnum getJoinType();

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
     * The meta object literal for the '<em><b>Tbl</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECT__TBL = eINSTANCE.getSelect_Tbl();

    /**
     * The meta object literal for the '<em><b>Where Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECT__WHERE_EXPRESSION = eINSTANCE.getSelect_WhereExpression();

    /**
     * The meta object literal for the '<em><b>Group By Entry</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECT__GROUP_BY_ENTRY = eINSTANCE.getSelect_GroupByEntry();

    /**
     * The meta object literal for the '<em><b>Having Entry</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECT__HAVING_ENTRY = eINSTANCE.getSelect_HavingEntry();

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
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrTableImpl <em>Or Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrTableImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrTable()
     * @generated
     */
    EClass OR_TABLE = eINSTANCE.getOrTable();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_TABLE__ENTRIES = eINSTANCE.getOrTable_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl <em>From Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.FromTableImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getFromTable()
     * @generated
     */
    EClass FROM_TABLE = eINSTANCE.getFromTable();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FROM_TABLE__TABLE = eINSTANCE.getFromTable_Table();

    /**
     * The meta object literal for the '<em><b>Join</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FROM_TABLE__JOIN = eINSTANCE.getFromTable_Join();

    /**
     * The meta object literal for the '<em><b>On Table</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FROM_TABLE__ON_TABLE = eINSTANCE.getFromTable_OnTable();

    /**
     * The meta object literal for the '<em><b>Join Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FROM_TABLE__JOIN_EXPR = eINSTANCE.getFromTable_JoinExpr();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.TableOrAliasImpl <em>Table Or Alias</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.TableOrAliasImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getTableOrAlias()
     * @generated
     */
    EClass TABLE_OR_ALIAS = eINSTANCE.getTableOrAlias();

    /**
     * The meta object literal for the '<em><b>Tfull</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TABLE_OR_ALIAS__TFULL = eINSTANCE.getTableOrAlias_Tfull();

    /**
     * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TABLE_OR_ALIAS__ALIAS = eINSTANCE.getTableOrAlias_Alias();

    /**
     * The meta object literal for the '<em><b>Tbl Alias</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TABLE_OR_ALIAS__TBL_ALIAS = eINSTANCE.getTableOrAlias_TblAlias();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl <em>Table Full</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.TableFullImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getTableFull()
     * @generated
     */
    EClass TABLE_FULL = eINSTANCE.getTableFull();

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
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnsImpl <em>Group By Columns</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.GroupByColumnsImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getGroupByColumns()
     * @generated
     */
    EClass GROUP_BY_COLUMNS = eINSTANCE.getGroupByColumns();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl <em>Full Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.FullExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getFullExpression()
     * @generated
     */
    EClass FULL_EXPRESSION = eINSTANCE.getFullExpression();

    /**
     * The meta object literal for the '<em><b>Op1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULL_EXPRESSION__OP1 = eINSTANCE.getFullExpression_Op1();

    /**
     * The meta object literal for the '<em><b>In</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULL_EXPRESSION__IN = eINSTANCE.getFullExpression_In();

    /**
     * The meta object literal for the '<em><b>Between</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULL_EXPRESSION__BETWEEN = eINSTANCE.getFullExpression_Between();

    /**
     * The meta object literal for the '<em><b>Like</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FULL_EXPRESSION__LIKE = eINSTANCE.getFullExpression_Like();

    /**
     * The meta object literal for the '<em><b>Comp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULL_EXPRESSION__COMP = eINSTANCE.getFullExpression_Comp();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ComparisonImpl <em>Comparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ComparisonImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getComparison()
     * @generated
     */
    EClass COMPARISON = eINSTANCE.getComparison();

    /**
     * The meta object literal for the '<em><b>Op2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPARISON__OP2 = eINSTANCE.getComparison_Op2();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.BetweenImpl <em>Between</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.BetweenImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBetween()
     * @generated
     */
    EClass BETWEEN = eINSTANCE.getBetween();

    /**
     * The meta object literal for the '<em><b>Op1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BETWEEN__OP1 = eINSTANCE.getBetween_Op1();

    /**
     * The meta object literal for the '<em><b>Op2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BETWEEN__OP2 = eINSTANCE.getBetween_Op2();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.InOperatorImpl <em>In Operator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.InOperatorImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getInOperator()
     * @generated
     */
    EClass IN_OPERATOR = eINSTANCE.getInOperator();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OperandImpl <em>Operand</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OperandImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOperand()
     * @generated
     */
    EClass OPERAND = eINSTANCE.getOperand();

    /**
     * The meta object literal for the '<em><b>Scalar</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPERAND__SCALAR = eINSTANCE.getOperand_Scalar();

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
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.tblsImpl <em>tbls</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.tblsImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#gettbls()
     * @generated
     */
    EClass TBLS = eINSTANCE.gettbls();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TBLS__ENTRIES = eINSTANCE.gettbls_Entries();

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

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrGroupByColumnImpl <em>Or Group By Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrGroupByColumnImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrGroupByColumn()
     * @generated
     */
    EClass OR_GROUP_BY_COLUMN = eINSTANCE.getOrGroupByColumn();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_GROUP_BY_COLUMN__ENTRIES = eINSTANCE.getOrGroupByColumn_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.fexprImpl <em>fexpr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.fexprImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getfexpr()
     * @generated
     */
    EClass FEXPR = eINSTANCE.getfexpr();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEXPR__ENTRIES = eINSTANCE.getfexpr_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.exprGroupImpl <em>expr Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.exprGroupImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getexprGroup()
     * @generated
     */
    EClass EXPR_GROUP = eINSTANCE.getexprGroup();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPR_GROUP__EXPR = eINSTANCE.getexprGroup_Expr();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.xexprImpl <em>xexpr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.xexprImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getxexpr()
     * @generated
     */
    EClass XEXPR = eINSTANCE.getxexpr();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.inopImpl <em>inop</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.inopImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getinop()
     * @generated
     */
    EClass INOP = eINSTANCE.getinop();

    /**
     * The meta object literal for the '<em><b>Subquery</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INOP__SUBQUERY = eINSTANCE.getinop_Subquery();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.xopImpl <em>xop</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.xopImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getxop()
     * @generated
     */
    EClass XOP = eINSTANCE.getxop();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XOP__ENTRIES = eINSTANCE.getxop_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.operandsImpl <em>operands</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.operandsImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getoperands()
     * @generated
     */
    EClass OPERANDS = eINSTANCE.getoperands();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERANDS__ENTRIES = eINSTANCE.getoperands_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.opGroupImpl <em>op Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.opGroupImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getopGroup()
     * @generated
     */
    EClass OP_GROUP = eINSTANCE.getopGroup();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OP_GROUP__OP = eINSTANCE.getopGroup_Op();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.poperandImpl <em>poperand</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.poperandImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getpoperand()
     * @generated
     */
    EClass POPERAND = eINSTANCE.getpoperand();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.expoperandImpl <em>expoperand</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.expoperandImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getexpoperand()
     * @generated
     */
    EClass EXPOPERAND = eINSTANCE.getexpoperand();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.subqueryImpl <em>subquery</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.subqueryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getsubquery()
     * @generated
     */
    EClass SUBQUERY = eINSTANCE.getsubquery();

    /**
     * The meta object literal for the '<em><b>Sel</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SUBQUERY__SEL = eINSTANCE.getsubquery_Sel();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.JoinType <em>Join Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.JoinType
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getJoinType()
     * @generated
     */
    EEnum JOIN_TYPE = eINSTANCE.getJoinType();

  }

} //SqlPackage
