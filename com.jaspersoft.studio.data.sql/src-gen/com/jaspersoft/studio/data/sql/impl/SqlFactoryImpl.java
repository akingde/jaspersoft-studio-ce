/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SqlFactoryImpl extends EFactoryImpl implements SqlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SqlFactory init()
  {
    try
    {
      SqlFactory theSqlFactory = (SqlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.com.jaspersoft.studio.data.Sql"); 
      if (theSqlFactory != null)
      {
        return theSqlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SqlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SqlPackage.MODEL: return createModel();
      case SqlPackage.ORDER_BY_COLUMNS: return createOrderByColumns();
      case SqlPackage.ORDER_BY_COLUMN_FULL: return createOrderByColumnFull();
      case SqlPackage.GROUP_BY_COLUMNS: return createGroupByColumns();
      case SqlPackage.GROUP_BY_COLUMN_FULL: return createGroupByColumnFull();
      case SqlPackage.COLUMNS: return createColumns();
      case SqlPackage.COLUMN_OR_ALIAS: return createColumnOrAlias();
      case SqlPackage.COLUMN_FULL: return createColumnFull();
      case SqlPackage.COLUMN_ALIAS: return createColumnAlias();
      case SqlPackage.COLUMN: return createColumn();
      case SqlPackage.TABLES: return createTables();
      case SqlPackage.TABLE_OR_ALIAS: return createTableOrAlias();
      case SqlPackage.TABLE_FULL: return createTableFull();
      case SqlPackage.TABLE: return createTable();
      case SqlPackage.TABLE_ALIAS: return createTableAlias();
      case SqlPackage.SCHEMA: return createSchema();
      case SqlPackage.DATABASE: return createDatabase();
      case SqlPackage.WHERE_ENTRY: return createWhereEntry();
      case SqlPackage.HAVING_ENTRY: return createHavingEntry();
      case SqlPackage.EXPRESSION_WHERE_ENTRY: return createExpressionWhereEntry();
      case SqlPackage.SINGLE_EXPRESSION_WHERE_ENTRY: return createSingleExpressionWhereEntry();
      case SqlPackage.EXPRESSION: return createExpression();
      case SqlPackage.REPLACABLE_VALUE: return createReplacableValue();
      case SqlPackage.DOUBLE_EXPRESSION: return createDoubleExpression();
      case SqlPackage.LONG_EXPRESSION: return createLongExpression();
      case SqlPackage.STRING_EXPRESSION: return createStringExpression();
      case SqlPackage.NULL_EXPRESSION: return createNullExpression();
      case SqlPackage.DATE_EXPRESSION: return createDateExpression();
      case SqlPackage.BOOLEAN_EXPRESSION: return createBooleanExpression();
      case SqlPackage.MULTI_EXPRESSION_WHERE_ENTRY: return createMultiExpressionWhereEntry();
      case SqlPackage.ARRAY_EXPRESSION: return createArrayExpression();
      case SqlPackage.DOUBLE_ARRAY_EXPRESSION: return createDoubleArrayExpression();
      case SqlPackage.LONG_ARRAY_EXPRESSION: return createLongArrayExpression();
      case SqlPackage.STRING_ARRAY_EXPRESSION: return createStringArrayExpression();
      case SqlPackage.NULL_ARRAY_EXPRESSION: return createNullArrayExpression();
      case SqlPackage.DATE_ARRAY_EXPRESSION: return createDateArrayExpression();
      case SqlPackage.BOOLEAN_ARRAY_EXPRESSION: return createBooleanArrayExpression();
      case SqlPackage.OR_ORDER_BY_COLUMN: return createOrOrderByColumn();
      case SqlPackage.OR_GROUP_BY_COLUMN: return createOrGroupByColumn();
      case SqlPackage.OR_COLUMN: return createOrColumn();
      case SqlPackage.OR_TABLE: return createOrTable();
      case SqlPackage.OR_WHERE_ENTRY: return createOrWhereEntry();
      case SqlPackage.AND_WHERE_ENTRY: return createAndWhereEntry();
      case SqlPackage.OR_HAVING_ENTRY: return createOrHavingEntry();
      case SqlPackage.AND_HAVING_ENTRY: return createAndHavingEntry();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SqlPackage.ARRAY_OPERATOR:
        return createArrayOperatorFromString(eDataType, initialValue);
      case SqlPackage.OPERATOR:
        return createOperatorFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SqlPackage.ARRAY_OPERATOR:
        return convertArrayOperatorToString(eDataType, instanceValue);
      case SqlPackage.OPERATOR:
        return convertOperatorToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByColumns createOrderByColumns()
  {
    OrderByColumnsImpl orderByColumns = new OrderByColumnsImpl();
    return orderByColumns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByColumnFull createOrderByColumnFull()
  {
    OrderByColumnFullImpl orderByColumnFull = new OrderByColumnFullImpl();
    return orderByColumnFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupByColumns createGroupByColumns()
  {
    GroupByColumnsImpl groupByColumns = new GroupByColumnsImpl();
    return groupByColumns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupByColumnFull createGroupByColumnFull()
  {
    GroupByColumnFullImpl groupByColumnFull = new GroupByColumnFullImpl();
    return groupByColumnFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Columns createColumns()
  {
    ColumnsImpl columns = new ColumnsImpl();
    return columns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnOrAlias createColumnOrAlias()
  {
    ColumnOrAliasImpl columnOrAlias = new ColumnOrAliasImpl();
    return columnOrAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnFull createColumnFull()
  {
    ColumnFullImpl columnFull = new ColumnFullImpl();
    return columnFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnAlias createColumnAlias()
  {
    ColumnAliasImpl columnAlias = new ColumnAliasImpl();
    return columnAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Column createColumn()
  {
    ColumnImpl column = new ColumnImpl();
    return column;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tables createTables()
  {
    TablesImpl tables = new TablesImpl();
    return tables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableOrAlias createTableOrAlias()
  {
    TableOrAliasImpl tableOrAlias = new TableOrAliasImpl();
    return tableOrAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableFull createTableFull()
  {
    TableFullImpl tableFull = new TableFullImpl();
    return tableFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Table createTable()
  {
    TableImpl table = new TableImpl();
    return table;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableAlias createTableAlias()
  {
    TableAliasImpl tableAlias = new TableAliasImpl();
    return tableAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Schema createSchema()
  {
    SchemaImpl schema = new SchemaImpl();
    return schema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Database createDatabase()
  {
    DatabaseImpl database = new DatabaseImpl();
    return database;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhereEntry createWhereEntry()
  {
    WhereEntryImpl whereEntry = new WhereEntryImpl();
    return whereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HavingEntry createHavingEntry()
  {
    HavingEntryImpl havingEntry = new HavingEntryImpl();
    return havingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpressionWhereEntry createExpressionWhereEntry()
  {
    ExpressionWhereEntryImpl expressionWhereEntry = new ExpressionWhereEntryImpl();
    return expressionWhereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SingleExpressionWhereEntry createSingleExpressionWhereEntry()
  {
    SingleExpressionWhereEntryImpl singleExpressionWhereEntry = new SingleExpressionWhereEntryImpl();
    return singleExpressionWhereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReplacableValue createReplacableValue()
  {
    ReplacableValueImpl replacableValue = new ReplacableValueImpl();
    return replacableValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoubleExpression createDoubleExpression()
  {
    DoubleExpressionImpl doubleExpression = new DoubleExpressionImpl();
    return doubleExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LongExpression createLongExpression()
  {
    LongExpressionImpl longExpression = new LongExpressionImpl();
    return longExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringExpression createStringExpression()
  {
    StringExpressionImpl stringExpression = new StringExpressionImpl();
    return stringExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NullExpression createNullExpression()
  {
    NullExpressionImpl nullExpression = new NullExpressionImpl();
    return nullExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DateExpression createDateExpression()
  {
    DateExpressionImpl dateExpression = new DateExpressionImpl();
    return dateExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanExpression createBooleanExpression()
  {
    BooleanExpressionImpl booleanExpression = new BooleanExpressionImpl();
    return booleanExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultiExpressionWhereEntry createMultiExpressionWhereEntry()
  {
    MultiExpressionWhereEntryImpl multiExpressionWhereEntry = new MultiExpressionWhereEntryImpl();
    return multiExpressionWhereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayExpression createArrayExpression()
  {
    ArrayExpressionImpl arrayExpression = new ArrayExpressionImpl();
    return arrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoubleArrayExpression createDoubleArrayExpression()
  {
    DoubleArrayExpressionImpl doubleArrayExpression = new DoubleArrayExpressionImpl();
    return doubleArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LongArrayExpression createLongArrayExpression()
  {
    LongArrayExpressionImpl longArrayExpression = new LongArrayExpressionImpl();
    return longArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringArrayExpression createStringArrayExpression()
  {
    StringArrayExpressionImpl stringArrayExpression = new StringArrayExpressionImpl();
    return stringArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NullArrayExpression createNullArrayExpression()
  {
    NullArrayExpressionImpl nullArrayExpression = new NullArrayExpressionImpl();
    return nullArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DateArrayExpression createDateArrayExpression()
  {
    DateArrayExpressionImpl dateArrayExpression = new DateArrayExpressionImpl();
    return dateArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanArrayExpression createBooleanArrayExpression()
  {
    BooleanArrayExpressionImpl booleanArrayExpression = new BooleanArrayExpressionImpl();
    return booleanArrayExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrOrderByColumn createOrOrderByColumn()
  {
    OrOrderByColumnImpl orOrderByColumn = new OrOrderByColumnImpl();
    return orOrderByColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrGroupByColumn createOrGroupByColumn()
  {
    OrGroupByColumnImpl orGroupByColumn = new OrGroupByColumnImpl();
    return orGroupByColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrColumn createOrColumn()
  {
    OrColumnImpl orColumn = new OrColumnImpl();
    return orColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrTable createOrTable()
  {
    OrTableImpl orTable = new OrTableImpl();
    return orTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrWhereEntry createOrWhereEntry()
  {
    OrWhereEntryImpl orWhereEntry = new OrWhereEntryImpl();
    return orWhereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndWhereEntry createAndWhereEntry()
  {
    AndWhereEntryImpl andWhereEntry = new AndWhereEntryImpl();
    return andWhereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrHavingEntry createOrHavingEntry()
  {
    OrHavingEntryImpl orHavingEntry = new OrHavingEntryImpl();
    return orHavingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndHavingEntry createAndHavingEntry()
  {
    AndHavingEntryImpl andHavingEntry = new AndHavingEntryImpl();
    return andHavingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayOperator createArrayOperatorFromString(EDataType eDataType, String initialValue)
  {
    ArrayOperator result = ArrayOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertArrayOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operator createOperatorFromString(EDataType eDataType, String initialValue)
  {
    Operator result = Operator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlPackage getSqlPackage()
  {
    return (SqlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SqlPackage getPackage()
  {
    return SqlPackage.eINSTANCE;
  }

} //SqlFactoryImpl
