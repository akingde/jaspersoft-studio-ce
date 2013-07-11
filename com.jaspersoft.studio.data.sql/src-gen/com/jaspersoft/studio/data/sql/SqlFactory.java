/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlPackage
 * @generated
 */
public interface SqlFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SqlFactory eINSTANCE = com.jaspersoft.studio.data.sql.impl.SqlFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Select</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Select</em>'.
   * @generated
   */
  Select createSelect();

  /**
   * Returns a new object of class '<em>Or Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Column</em>'.
   * @generated
   */
  OrColumn createOrColumn();

  /**
   * Returns a new object of class '<em>Column Or Alias</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Column Or Alias</em>'.
   * @generated
   */
  ColumnOrAlias createColumnOrAlias();

  /**
   * Returns a new object of class '<em>Column Full</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Column Full</em>'.
   * @generated
   */
  ColumnFull createColumnFull();

  /**
   * Returns a new object of class '<em>Or Table</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Table</em>'.
   * @generated
   */
  OrTable createOrTable();

  /**
   * Returns a new object of class '<em>From Table</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>From Table</em>'.
   * @generated
   */
  FromTable createFromTable();

  /**
   * Returns a new object of class '<em>Table Or Alias</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Table Or Alias</em>'.
   * @generated
   */
  TableOrAlias createTableOrAlias();

  /**
   * Returns a new object of class '<em>Table Full</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Table Full</em>'.
   * @generated
   */
  TableFull createTableFull();

  /**
   * Returns a new object of class '<em>Db Object Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Db Object Name</em>'.
   * @generated
   */
  DbObjectName createDbObjectName();

  /**
   * Returns a new object of class '<em>Or Order By Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Order By Column</em>'.
   * @generated
   */
  OrOrderByColumn createOrOrderByColumn();

  /**
   * Returns a new object of class '<em>Order By Column Full</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Order By Column Full</em>'.
   * @generated
   */
  OrderByColumnFull createOrderByColumnFull();

  /**
   * Returns a new object of class '<em>Or Group By Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Group By Column</em>'.
   * @generated
   */
  OrGroupByColumn createOrGroupByColumn();

  /**
   * Returns a new object of class '<em>Or Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Expr</em>'.
   * @generated
   */
  OrExpr createOrExpr();

  /**
   * Returns a new object of class '<em>Full Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Full Expression</em>'.
   * @generated
   */
  FullExpression createFullExpression();

  /**
   * Returns a new object of class '<em>Expr Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expr Group</em>'.
   * @generated
   */
  ExprGroup createExprGroup();

  /**
   * Returns a new object of class '<em>XExpr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XExpr</em>'.
   * @generated
   */
  XExpr createXExpr();

  /**
   * Returns a new object of class '<em>Prms</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Prms</em>'.
   * @generated
   */
  Prms createPrms();

  /**
   * Returns a new object of class '<em>JR Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JR Parameter</em>'.
   * @generated
   */
  JRParameter createJRParameter();

  /**
   * Returns a new object of class '<em>Comparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Comparison</em>'.
   * @generated
   */
  Comparison createComparison();

  /**
   * Returns a new object of class '<em>Like</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Like</em>'.
   * @generated
   */
  Like createLike();

  /**
   * Returns a new object of class '<em>Between</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Between</em>'.
   * @generated
   */
  Between createBetween();

  /**
   * Returns a new object of class '<em>In Oper</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>In Oper</em>'.
   * @generated
   */
  InOper createInOper();

  /**
   * Returns a new object of class '<em>Operand List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operand List</em>'.
   * @generated
   */
  OperandList createOperandList();

  /**
   * Returns a new object of class '<em>Operands</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operands</em>'.
   * @generated
   */
  Operands createOperands();

  /**
   * Returns a new object of class '<em>Operand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operand</em>'.
   * @generated
   */
  Operand createOperand();

  /**
   * Returns a new object of class '<em>POperand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>POperand</em>'.
   * @generated
   */
  POperand createPOperand();

  /**
   * Returns a new object of class '<em>Exp Pperand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Exp Pperand</em>'.
   * @generated
   */
  ExpPperand createExpPperand();

  /**
   * Returns a new object of class '<em>Column Operand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Column Operand</em>'.
   * @generated
   */
  ColumnOperand createColumnOperand();

  /**
   * Returns a new object of class '<em>Scalar Operand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Scalar Operand</em>'.
   * @generated
   */
  ScalarOperand createScalarOperand();

  /**
   * Returns a new object of class '<em>Or Select</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Select</em>'.
   * @generated
   */
  OrSelect createOrSelect();

  /**
   * Returns a new object of class '<em>Col</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Col</em>'.
   * @generated
   */
  Col createCol();

  /**
   * Returns a new object of class '<em>tbls</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>tbls</em>'.
   * @generated
   */
  tbls createtbls();

  /**
   * Returns a new object of class '<em>Op List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Op List</em>'.
   * @generated
   */
  OpList createOpList();

  /**
   * Returns a new object of class '<em>subquery</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>subquery</em>'.
   * @generated
   */
  subquery createsubquery();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SqlPackage getSqlPackage();

} //SqlFactory
