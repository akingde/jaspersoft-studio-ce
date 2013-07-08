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
   * Returns a new object of class '<em>Columns</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Columns</em>'.
   * @generated
   */
  Columns createColumns();

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
   * Returns a new object of class '<em>Order By Columns</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Order By Columns</em>'.
   * @generated
   */
  OrderByColumns createOrderByColumns();

  /**
   * Returns a new object of class '<em>Order By Column Full</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Order By Column Full</em>'.
   * @generated
   */
  OrderByColumnFull createOrderByColumnFull();

  /**
   * Returns a new object of class '<em>Group By Columns</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group By Columns</em>'.
   * @generated
   */
  GroupByColumns createGroupByColumns();

  /**
   * Returns a new object of class '<em>Full Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Full Expression</em>'.
   * @generated
   */
  FullExpression createFullExpression();

  /**
   * Returns a new object of class '<em>Comparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Comparison</em>'.
   * @generated
   */
  Comparison createComparison();

  /**
   * Returns a new object of class '<em>Between</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Between</em>'.
   * @generated
   */
  Between createBetween();

  /**
   * Returns a new object of class '<em>In Operator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>In Operator</em>'.
   * @generated
   */
  InOperator createInOperator();

  /**
   * Returns a new object of class '<em>Operand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operand</em>'.
   * @generated
   */
  Operand createOperand();

  /**
   * Returns a new object of class '<em>Or Select</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Select</em>'.
   * @generated
   */
  OrSelect createOrSelect();

  /**
   * Returns a new object of class '<em>Or Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Column</em>'.
   * @generated
   */
  OrColumn createOrColumn();

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
   * Returns a new object of class '<em>Or Order By Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Order By Column</em>'.
   * @generated
   */
  OrOrderByColumn createOrOrderByColumn();

  /**
   * Returns a new object of class '<em>Or Group By Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Group By Column</em>'.
   * @generated
   */
  OrGroupByColumn createOrGroupByColumn();

  /**
   * Returns a new object of class '<em>fexpr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>fexpr</em>'.
   * @generated
   */
  fexpr createfexpr();

  /**
   * Returns a new object of class '<em>expr Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>expr Group</em>'.
   * @generated
   */
  exprGroup createexprGroup();

  /**
   * Returns a new object of class '<em>xexpr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>xexpr</em>'.
   * @generated
   */
  xexpr createxexpr();

  /**
   * Returns a new object of class '<em>inop</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>inop</em>'.
   * @generated
   */
  inop createinop();

  /**
   * Returns a new object of class '<em>xop</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>xop</em>'.
   * @generated
   */
  xop createxop();

  /**
   * Returns a new object of class '<em>operands</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>operands</em>'.
   * @generated
   */
  operands createoperands();

  /**
   * Returns a new object of class '<em>op Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>op Group</em>'.
   * @generated
   */
  opGroup createopGroup();

  /**
   * Returns a new object of class '<em>poperand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>poperand</em>'.
   * @generated
   */
  poperand createpoperand();

  /**
   * Returns a new object of class '<em>expoperand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>expoperand</em>'.
   * @generated
   */
  expoperand createexpoperand();

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
