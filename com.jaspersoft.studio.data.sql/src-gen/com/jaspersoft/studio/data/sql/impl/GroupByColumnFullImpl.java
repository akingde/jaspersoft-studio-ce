/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.GroupByColumnFull;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group By Column Full</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnFullImpl#getGroupByColumn <em>Group By Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupByColumnFullImpl extends GroupByColumnsImpl implements GroupByColumnFull
{
  /**
   * The cached value of the '{@link #getGroupByColumn() <em>Group By Column</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupByColumn()
   * @generated
   * @ordered
   */
  protected Column groupByColumn;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupByColumnFullImpl()
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
    return SqlPackage.Literals.GROUP_BY_COLUMN_FULL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Column getGroupByColumn()
  {
    return groupByColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroupByColumn(Column newGroupByColumn, NotificationChain msgs)
  {
    Column oldGroupByColumn = groupByColumn;
    groupByColumn = newGroupByColumn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN, oldGroupByColumn, newGroupByColumn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupByColumn(Column newGroupByColumn)
  {
    if (newGroupByColumn != groupByColumn)
    {
      NotificationChain msgs = null;
      if (groupByColumn != null)
        msgs = ((InternalEObject)groupByColumn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN, null, msgs);
      if (newGroupByColumn != null)
        msgs = ((InternalEObject)newGroupByColumn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN, null, msgs);
      msgs = basicSetGroupByColumn(newGroupByColumn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN, newGroupByColumn, newGroupByColumn));
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN:
        return basicSetGroupByColumn(null, msgs);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN:
        return getGroupByColumn();
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN:
        setGroupByColumn((Column)newValue);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN:
        setGroupByColumn((Column)null);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN:
        return groupByColumn != null;
    }
    return super.eIsSet(featureID);
  }

} //GroupByColumnFullImpl
