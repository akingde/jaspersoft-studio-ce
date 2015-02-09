/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pivot Functions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotFunctions#getAbc <em>Abc</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotFunctions()
 * @model
 * @generated
 */
public interface PivotFunctions extends EObject
{
  /**
   * Returns the value of the '<em><b>Abc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Abc</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abc</em>' attribute.
   * @see #setAbc(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotFunctions_Abc()
   * @model
   * @generated
   */
  String getAbc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotFunctions#getAbc <em>Abc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abc</em>' attribute.
   * @see #getAbc()
   * @generated
   */
  void setAbc(String value);

} // PivotFunctions
