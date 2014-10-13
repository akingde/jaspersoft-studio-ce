/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or Group By Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OrGroupByColumn#getEntries <em>Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrGroupByColumn()
 * @model
 * @generated
 */
public interface OrGroupByColumn extends EObject
{
  /**
   * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
   * The list contents are of type {@link com.jaspersoft.studio.data.sql.GroupByColumnFull}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entries</em>' containment reference list.
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrGroupByColumn_Entries()
   * @model containment="true"
   * @generated
   */
  EList<GroupByColumnFull> getEntries();

} // OrGroupByColumn
