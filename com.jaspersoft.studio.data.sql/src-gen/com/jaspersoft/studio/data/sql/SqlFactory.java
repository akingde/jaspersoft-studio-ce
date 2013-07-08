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
   * Returns a new object of class '<em>Or Order By Column</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Order By Column</em>'.
   * @generated
   */
  OrOrderByColumn createOrOrderByColumn();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SqlPackage getSqlPackage();

} //SqlFactory
