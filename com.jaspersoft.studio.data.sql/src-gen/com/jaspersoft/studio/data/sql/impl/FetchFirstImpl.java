/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FetchFirst;
import com.jaspersoft.studio.data.sql.IntegerValue;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fetch First</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FetchFirstImpl#getFetchFirst <em>Fetch First</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FetchFirstImpl#getRow <em>Row</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FetchFirstImpl extends MinimalEObjectImpl.Container implements FetchFirst
{
  /**
   * The cached value of the '{@link #getFetchFirst() <em>Fetch First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFetchFirst()
   * @generated
   * @ordered
   */
  protected IntegerValue fetchFirst;

  /**
   * The default value of the '{@link #getRow() <em>Row</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRow()
   * @generated
   * @ordered
   */
  protected static final String ROW_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRow() <em>Row</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRow()
   * @generated
   * @ordered
   */
  protected String row = ROW_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FetchFirstImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SqlPackage.Literals.FETCH_FIRST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerValue getFetchFirst()
  {
    return fetchFirst;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFetchFirst(IntegerValue newFetchFirst, NotificationChain msgs)
  {
    IntegerValue oldFetchFirst = fetchFirst;
    fetchFirst = newFetchFirst;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FETCH_FIRST__FETCH_FIRST, oldFetchFirst, newFetchFirst);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFetchFirst(IntegerValue newFetchFirst)
  {
    if (newFetchFirst != fetchFirst)
    {
      NotificationChain msgs = null;
      if (fetchFirst != null)
        msgs = ((InternalEObject)fetchFirst).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FETCH_FIRST__FETCH_FIRST, null, msgs);
      if (newFetchFirst != null)
        msgs = ((InternalEObject)newFetchFirst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FETCH_FIRST__FETCH_FIRST, null, msgs);
      msgs = basicSetFetchFirst(newFetchFirst, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FETCH_FIRST__FETCH_FIRST, newFetchFirst, newFetchFirst));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRow()
  {
    return row;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRow(String newRow)
  {
    String oldRow = row;
    row = newRow;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FETCH_FIRST__ROW, oldRow, row));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlPackage.FETCH_FIRST__FETCH_FIRST:
        return basicSetFetchFirst(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SqlPackage.FETCH_FIRST__FETCH_FIRST:
        return getFetchFirst();
      case SqlPackage.FETCH_FIRST__ROW:
        return getRow();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.FETCH_FIRST__FETCH_FIRST:
        setFetchFirst((IntegerValue)newValue);
        return;
      case SqlPackage.FETCH_FIRST__ROW:
        setRow((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.FETCH_FIRST__FETCH_FIRST:
        setFetchFirst((IntegerValue)null);
        return;
      case SqlPackage.FETCH_FIRST__ROW:
        setRow(ROW_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.FETCH_FIRST__FETCH_FIRST:
        return fetchFirst != null;
      case SqlPackage.FETCH_FIRST__ROW:
        return ROW_EDEFAULT == null ? row != null : !ROW_EDEFAULT.equals(row);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (row: ");
    result.append(row);
    result.append(')');
    return result.toString();
  }

} //FetchFirstImpl
