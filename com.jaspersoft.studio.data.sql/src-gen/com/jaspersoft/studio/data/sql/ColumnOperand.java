/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnOperand#getCfull <em>Cfull</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnOperand#getOra <em>Ora</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnOperand()
 * @model
 * @generated
 */
public interface ColumnOperand extends EObject
{
  /**
   * Returns the value of the '<em><b>Cfull</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cfull</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cfull</em>' containment reference.
   * @see #setCfull(ColumnFull)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnOperand_Cfull()
   * @model containment="true"
   * @generated
   */
  ColumnFull getCfull();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnOperand#getCfull <em>Cfull</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cfull</em>' containment reference.
   * @see #getCfull()
   * @generated
   */
  void setCfull(ColumnFull value);

  /**
   * Returns the value of the '<em><b>Ora</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ora</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ora</em>' attribute.
   * @see #setOra(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnOperand_Ora()
   * @model
   * @generated
   */
  String getOra();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnOperand#getOra <em>Ora</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ora</em>' attribute.
   * @see #getOra()
   * @generated
   */
  void setOra(String value);

} // ColumnOperand
