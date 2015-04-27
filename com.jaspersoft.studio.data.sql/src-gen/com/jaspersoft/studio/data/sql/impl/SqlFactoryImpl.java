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
      SqlFactory theSqlFactory = (SqlFactory)EPackage.Registry.INSTANCE.getEFactory(SqlPackage.eNS_URI);
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
      case SqlPackage.FETCH_FIRST: return createFetchFirst();
      case SqlPackage.OFFSET: return createOffset();
      case SqlPackage.LIMIT: return createLimit();
      case SqlPackage.SELECT_QUERY: return createSelectQuery();
      case SqlPackage.SELECT_SUB_SET: return createSelectSubSet();
      case SqlPackage.SELECT: return createSelect();
      case SqlPackage.OR_COLUMN: return createOrColumn();
      case SqlPackage.COLUMN_OR_ALIAS: return createColumnOrAlias();
      case SqlPackage.COLUMN_FULL: return createColumnFull();
      case SqlPackage.OR_TABLE: return createOrTable();
      case SqlPackage.FROM_TABLE: return createFromTable();
      case SqlPackage.FROM_TABLE_JOIN: return createFromTableJoin();
      case SqlPackage.TABLE_OR_ALIAS: return createTableOrAlias();
      case SqlPackage.PIVOT_TABLE: return createPivotTable();
      case SqlPackage.PIVOT_FUNCTIONS: return createPivotFunctions();
      case SqlPackage.PIVOT_FUNCTION: return createPivotFunction();
      case SqlPackage.PIVOT_IN_CLAUSE: return createPivotInClause();
      case SqlPackage.UNPIVOT_TABLE: return createUnpivotTable();
      case SqlPackage.UNPIVOT_IN_CLAUSE: return createUnpivotInClause();
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARGS: return createUnpivotInClauseArgs();
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG: return createUnpivotInClauseArg();
      case SqlPackage.PIVOT_FOR_CLAUSE: return createPivotForClause();
      case SqlPackage.PIVOT_COLUMNS: return createPivotColumns();
      case SqlPackage.PIVOTS: return createPivots();
      case SqlPackage.PIVOT_COL: return createPivotCol();
      case SqlPackage.TABLE_FULL: return createTableFull();
      case SqlPackage.DB_OBJECT_NAME_ALL: return createDbObjectNameAll();
      case SqlPackage.DB_OBJECT_NAME: return createDbObjectName();
      case SqlPackage.OR_ORDER_BY_COLUMN: return createOrOrderByColumn();
      case SqlPackage.ORDER_BY_COLUMN_FULL: return createOrderByColumnFull();
      case SqlPackage.OR_GROUP_BY_COLUMN: return createOrGroupByColumn();
      case SqlPackage.GROUP_BY_COLUMN_FULL: return createGroupByColumnFull();
      case SqlPackage.OR_EXPR: return createOrExpr();
      case SqlPackage.FULL_EXPRESSION: return createFullExpression();
      case SqlPackage.EXPR_GROUP: return createExprGroup();
      case SqlPackage.XEXPR: return createXExpr();
      case SqlPackage.PRMS: return createPrms();
      case SqlPackage.JR_PARAMETER: return createJRParameter();
      case SqlPackage.COMPARISON: return createComparison();
      case SqlPackage.LIKE: return createLike();
      case SqlPackage.LIKE_OPERAND: return createLikeOperand();
      case SqlPackage.BETWEEN: return createBetween();
      case SqlPackage.IN_OPER: return createInOper();
      case SqlPackage.EXISTS_OPER: return createExistsOper();
      case SqlPackage.OPERAND_LIST_GROUP: return createOperandListGroup();
      case SqlPackage.OPERAND_LIST: return createOperandList();
      case SqlPackage.OPERANDS: return createOperands();
      case SqlPackage.OPERAND: return createOperand();
      case SqlPackage.OP_FUNCTION: return createOpFunction();
      case SqlPackage.FUNCTION_EXTRACT: return createFunctionExtract();
      case SqlPackage.FUNCTION_ANALYTICAL: return createFunctionAnalytical();
      case SqlPackage.ANALYTIC_CLAUSE: return createAnalyticClause();
      case SqlPackage.WINDOWING_CLAUSE: return createWindowingClause();
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN: return createWindowingClauseBetween();
      case SqlPackage.WINDOWING_CLAUSE_OPERAND_FOLLOWING: return createWindowingClauseOperandFollowing();
      case SqlPackage.WINDOWING_CLAUSE_OPERAND_PRECEDING: return createWindowingClauseOperandPreceding();
      case SqlPackage.ORDER_BY_CLAUSE: return createOrderByClause();
      case SqlPackage.ORDER_BY_CLAUSE_ARGS: return createOrderByClauseArgs();
      case SqlPackage.ORDER_BY_CLAUSE_ARG: return createOrderByClauseArg();
      case SqlPackage.QUERY_PARTITION_CLAUSE: return createQueryPartitionClause();
      case SqlPackage.ANALYTIC_EXPR_ARGS: return createAnalyticExprArgs();
      case SqlPackage.ANALYTIC_EXPR_ARG: return createAnalyticExprArg();
      case SqlPackage.OP_FUNCTION_ARG: return createOpFunctionArg();
      case SqlPackage.OP_FUNCTION_ARG_OPERAND: return createOpFunctionArgOperand();
      case SqlPackage.OP_FUNCTION_CAST: return createOpFunctionCast();
      case SqlPackage.OP_FUNCTION_ARG_AGREGATE: return createOpFunctionArgAgregate();
      case SqlPackage.POPERAND: return createPOperand();
      case SqlPackage.EXP_OPERAND: return createExpOperand();
      case SqlPackage.COLUMN_OPERAND: return createColumnOperand();
      case SqlPackage.SUB_QUERY_OPERAND: return createSubQueryOperand();
      case SqlPackage.SCALAR_OPERAND: return createScalarOperand();
      case SqlPackage.SQL_CASE_OPERAND: return createSQLCaseOperand();
      case SqlPackage.SQL_CASE_WHENS: return createSQLCaseWhens();
      case SqlPackage.SQL_CASE_WHEN: return createSqlCaseWhen();
      case SqlPackage.INTEGER_VALUE: return createIntegerValue();
      case SqlPackage.COL: return createCol();
      case SqlPackage.UNIPIVOT_IN_CLAUSE: return createUnipivotInClause();
      case SqlPackage.UICARGS: return createuicargs();
      case SqlPackage.PVCS: return createpvcs();
      case SqlPackage.PCOLS: return createpcols();
      case SqlPackage.TBLS: return createtbls();
      case SqlPackage.OP_LIST: return createOpList();
      case SqlPackage.PLUS: return createPlus();
      case SqlPackage.MINUS: return createMinus();
      case SqlPackage.STAR: return createStar();
      case SqlPackage.DIV: return createDiv();
      case SqlPackage.CONCAT: return createConcat();
      case SqlPackage.OBC_ARGS: return createOBCArgs();
      case SqlPackage.AEXP_ARGS: return createAExpArgs();
      case SqlPackage.OP_FLIST: return createOpFList();
      case SqlPackage.WHEN_LIST: return createWhenList();
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
      case SqlPackage.EXTRACT_VALUES:
        return createEXTRACT_VALUESFromString(eDataType, initialValue);
      case SqlPackage.XFUNCTION:
        return createXFunctionFromString(eDataType, initialValue);
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
      case SqlPackage.EXTRACT_VALUES:
        return convertEXTRACT_VALUESToString(eDataType, instanceValue);
      case SqlPackage.XFUNCTION:
        return convertXFunctionToString(eDataType, instanceValue);
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
  public FetchFirst createFetchFirst()
  {
    FetchFirstImpl fetchFirst = new FetchFirstImpl();
    return fetchFirst;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Offset createOffset()
  {
    OffsetImpl offset = new OffsetImpl();
    return offset;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Limit createLimit()
  {
    LimitImpl limit = new LimitImpl();
    return limit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelectQuery createSelectQuery()
  {
    SelectQueryImpl selectQuery = new SelectQueryImpl();
    return selectQuery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelectSubSet createSelectSubSet()
  {
    SelectSubSetImpl selectSubSet = new SelectSubSetImpl();
    return selectSubSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Select createSelect()
  {
    SelectImpl select = new SelectImpl();
    return select;
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
  public FromTable createFromTable()
  {
    FromTableImpl fromTable = new FromTableImpl();
    return fromTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FromTableJoin createFromTableJoin()
  {
    FromTableJoinImpl fromTableJoin = new FromTableJoinImpl();
    return fromTableJoin;
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
  public PivotTable createPivotTable()
  {
    PivotTableImpl pivotTable = new PivotTableImpl();
    return pivotTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotFunctions createPivotFunctions()
  {
    PivotFunctionsImpl pivotFunctions = new PivotFunctionsImpl();
    return pivotFunctions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotFunction createPivotFunction()
  {
    PivotFunctionImpl pivotFunction = new PivotFunctionImpl();
    return pivotFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotInClause createPivotInClause()
  {
    PivotInClauseImpl pivotInClause = new PivotInClauseImpl();
    return pivotInClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotTable createUnpivotTable()
  {
    UnpivotTableImpl unpivotTable = new UnpivotTableImpl();
    return unpivotTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotInClause createUnpivotInClause()
  {
    UnpivotInClauseImpl unpivotInClause = new UnpivotInClauseImpl();
    return unpivotInClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotInClauseArgs createUnpivotInClauseArgs()
  {
    UnpivotInClauseArgsImpl unpivotInClauseArgs = new UnpivotInClauseArgsImpl();
    return unpivotInClauseArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotInClauseArg createUnpivotInClauseArg()
  {
    UnpivotInClauseArgImpl unpivotInClauseArg = new UnpivotInClauseArgImpl();
    return unpivotInClauseArg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotForClause createPivotForClause()
  {
    PivotForClauseImpl pivotForClause = new PivotForClauseImpl();
    return pivotForClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotColumns createPivotColumns()
  {
    PivotColumnsImpl pivotColumns = new PivotColumnsImpl();
    return pivotColumns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Pivots createPivots()
  {
    PivotsImpl pivots = new PivotsImpl();
    return pivots;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotCol createPivotCol()
  {
    PivotColImpl pivotCol = new PivotColImpl();
    return pivotCol;
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
  public DbObjectNameAll createDbObjectNameAll()
  {
    DbObjectNameAllImpl dbObjectNameAll = new DbObjectNameAllImpl();
    return dbObjectNameAll;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbObjectName createDbObjectName()
  {
    DbObjectNameImpl dbObjectName = new DbObjectNameImpl();
    return dbObjectName;
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
  public OrExpr createOrExpr()
  {
    OrExprImpl orExpr = new OrExprImpl();
    return orExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullExpression createFullExpression()
  {
    FullExpressionImpl fullExpression = new FullExpressionImpl();
    return fullExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExprGroup createExprGroup()
  {
    ExprGroupImpl exprGroup = new ExprGroupImpl();
    return exprGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpr createXExpr()
  {
    XExprImpl xExpr = new XExprImpl();
    return xExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Prms createPrms()
  {
    PrmsImpl prms = new PrmsImpl();
    return prms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JRParameter createJRParameter()
  {
    JRParameterImpl jrParameter = new JRParameterImpl();
    return jrParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Comparison createComparison()
  {
    ComparisonImpl comparison = new ComparisonImpl();
    return comparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Like createLike()
  {
    LikeImpl like = new LikeImpl();
    return like;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LikeOperand createLikeOperand()
  {
    LikeOperandImpl likeOperand = new LikeOperandImpl();
    return likeOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Between createBetween()
  {
    BetweenImpl between = new BetweenImpl();
    return between;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InOper createInOper()
  {
    InOperImpl inOper = new InOperImpl();
    return inOper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExistsOper createExistsOper()
  {
    ExistsOperImpl existsOper = new ExistsOperImpl();
    return existsOper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperandListGroup createOperandListGroup()
  {
    OperandListGroupImpl operandListGroup = new OperandListGroupImpl();
    return operandListGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperandList createOperandList()
  {
    OperandListImpl operandList = new OperandListImpl();
    return operandList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operands createOperands()
  {
    OperandsImpl operands = new OperandsImpl();
    return operands;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operand createOperand()
  {
    OperandImpl operand = new OperandImpl();
    return operand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunction createOpFunction()
  {
    OpFunctionImpl opFunction = new OpFunctionImpl();
    return opFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionExtract createFunctionExtract()
  {
    FunctionExtractImpl functionExtract = new FunctionExtractImpl();
    return functionExtract;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionAnalytical createFunctionAnalytical()
  {
    FunctionAnalyticalImpl functionAnalytical = new FunctionAnalyticalImpl();
    return functionAnalytical;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticClause createAnalyticClause()
  {
    AnalyticClauseImpl analyticClause = new AnalyticClauseImpl();
    return analyticClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClause createWindowingClause()
  {
    WindowingClauseImpl windowingClause = new WindowingClauseImpl();
    return windowingClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClauseBetween createWindowingClauseBetween()
  {
    WindowingClauseBetweenImpl windowingClauseBetween = new WindowingClauseBetweenImpl();
    return windowingClauseBetween;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClauseOperandFollowing createWindowingClauseOperandFollowing()
  {
    WindowingClauseOperandFollowingImpl windowingClauseOperandFollowing = new WindowingClauseOperandFollowingImpl();
    return windowingClauseOperandFollowing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClauseOperandPreceding createWindowingClauseOperandPreceding()
  {
    WindowingClauseOperandPrecedingImpl windowingClauseOperandPreceding = new WindowingClauseOperandPrecedingImpl();
    return windowingClauseOperandPreceding;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByClause createOrderByClause()
  {
    OrderByClauseImpl orderByClause = new OrderByClauseImpl();
    return orderByClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByClauseArgs createOrderByClauseArgs()
  {
    OrderByClauseArgsImpl orderByClauseArgs = new OrderByClauseArgsImpl();
    return orderByClauseArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByClauseArg createOrderByClauseArg()
  {
    OrderByClauseArgImpl orderByClauseArg = new OrderByClauseArgImpl();
    return orderByClauseArg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QueryPartitionClause createQueryPartitionClause()
  {
    QueryPartitionClauseImpl queryPartitionClause = new QueryPartitionClauseImpl();
    return queryPartitionClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticExprArgs createAnalyticExprArgs()
  {
    AnalyticExprArgsImpl analyticExprArgs = new AnalyticExprArgsImpl();
    return analyticExprArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticExprArg createAnalyticExprArg()
  {
    AnalyticExprArgImpl analyticExprArg = new AnalyticExprArgImpl();
    return analyticExprArg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunctionArg createOpFunctionArg()
  {
    OpFunctionArgImpl opFunctionArg = new OpFunctionArgImpl();
    return opFunctionArg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunctionArgOperand createOpFunctionArgOperand()
  {
    OpFunctionArgOperandImpl opFunctionArgOperand = new OpFunctionArgOperandImpl();
    return opFunctionArgOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunctionCast createOpFunctionCast()
  {
    OpFunctionCastImpl opFunctionCast = new OpFunctionCastImpl();
    return opFunctionCast;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunctionArgAgregate createOpFunctionArgAgregate()
  {
    OpFunctionArgAgregateImpl opFunctionArgAgregate = new OpFunctionArgAgregateImpl();
    return opFunctionArgAgregate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public POperand createPOperand()
  {
    POperandImpl pOperand = new POperandImpl();
    return pOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpOperand createExpOperand()
  {
    ExpOperandImpl expOperand = new ExpOperandImpl();
    return expOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnOperand createColumnOperand()
  {
    ColumnOperandImpl columnOperand = new ColumnOperandImpl();
    return columnOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubQueryOperand createSubQueryOperand()
  {
    SubQueryOperandImpl subQueryOperand = new SubQueryOperandImpl();
    return subQueryOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScalarOperand createScalarOperand()
  {
    ScalarOperandImpl scalarOperand = new ScalarOperandImpl();
    return scalarOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQLCaseOperand createSQLCaseOperand()
  {
    SQLCaseOperandImpl sqlCaseOperand = new SQLCaseOperandImpl();
    return sqlCaseOperand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQLCaseWhens createSQLCaseWhens()
  {
    SQLCaseWhensImpl sqlCaseWhens = new SQLCaseWhensImpl();
    return sqlCaseWhens;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlCaseWhen createSqlCaseWhen()
  {
    SqlCaseWhenImpl sqlCaseWhen = new SqlCaseWhenImpl();
    return sqlCaseWhen;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerValue createIntegerValue()
  {
    IntegerValueImpl integerValue = new IntegerValueImpl();
    return integerValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Col createCol()
  {
    ColImpl col = new ColImpl();
    return col;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnipivotInClause createUnipivotInClause()
  {
    UnipivotInClauseImpl unipivotInClause = new UnipivotInClauseImpl();
    return unipivotInClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uicargs createuicargs()
  {
    uicargsImpl uicargs = new uicargsImpl();
    return uicargs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public pvcs createpvcs()
  {
    pvcsImpl pvcs = new pvcsImpl();
    return pvcs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public pcols createpcols()
  {
    pcolsImpl pcols = new pcolsImpl();
    return pcols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public tbls createtbls()
  {
    tblsImpl tbls = new tblsImpl();
    return tbls;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpList createOpList()
  {
    OpListImpl opList = new OpListImpl();
    return opList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Plus createPlus()
  {
    PlusImpl plus = new PlusImpl();
    return plus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Minus createMinus()
  {
    MinusImpl minus = new MinusImpl();
    return minus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Star createStar()
  {
    StarImpl star = new StarImpl();
    return star;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Div createDiv()
  {
    DivImpl div = new DivImpl();
    return div;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Concat createConcat()
  {
    ConcatImpl concat = new ConcatImpl();
    return concat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OBCArgs createOBCArgs()
  {
    OBCArgsImpl obcArgs = new OBCArgsImpl();
    return obcArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AExpArgs createAExpArgs()
  {
    AExpArgsImpl aExpArgs = new AExpArgsImpl();
    return aExpArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFList createOpFList()
  {
    OpFListImpl opFList = new OpFListImpl();
    return opFList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhenList createWhenList()
  {
    WhenListImpl whenList = new WhenListImpl();
    return whenList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EXTRACT_VALUES createEXTRACT_VALUESFromString(EDataType eDataType, String initialValue)
  {
    EXTRACT_VALUES result = EXTRACT_VALUES.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertEXTRACT_VALUESToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XFunction createXFunctionFromString(EDataType eDataType, String initialValue)
  {
    XFunction result = XFunction.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertXFunctionToString(EDataType eDataType, Object instanceValue)
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
