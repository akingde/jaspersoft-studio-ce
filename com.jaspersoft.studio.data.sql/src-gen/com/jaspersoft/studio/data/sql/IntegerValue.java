/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.IntegerValue#getInteger <em>Integer</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getIntegerValue()
 * @model
 * @generated
 */
public interface IntegerValue extends EObject
{
  /**
   * Returns the value of the '<em><b>Integer</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Integer</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Integer</em>' attribute.
   * @see #setInteger(int)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getIntegerValue_Integer()
   * @model
   * @generated
   */
  int getInteger();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.IntegerValue#getInteger <em>Integer</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Integer</em>' attribute.
   * @see #getInteger()
   * @generated
   */
  void setInteger(int value);

} // IntegerValue
