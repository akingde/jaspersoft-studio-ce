/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.ColumnAlias;
import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Full</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl#getColAlias <em>Col Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl#getColName <em>Col Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnFullImpl extends ColumnOrAliasImpl implements ColumnFull
{
  /**
   * The cached value of the '{@link #getColAlias() <em>Col Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColAlias()
   * @generated
   * @ordered
   */
  protected ColumnAlias colAlias;

  /**
   * The cached value of the '{@link #getColName() <em>Col Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColName()
   * @generated
   * @ordered
   */
  protected Column colName;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnFullImpl()
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
    return SqlPackage.Literals.COLUMN_FULL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnAlias getColAlias()
  {
    return colAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColAlias(ColumnAlias newColAlias, NotificationChain msgs)
  {
    ColumnAlias oldColAlias = colAlias;
    colAlias = newColAlias;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_FULL__COL_ALIAS, oldColAlias, newColAlias);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColAlias(ColumnAlias newColAlias)
  {
    if (newColAlias != colAlias)
    {
      NotificationChain msgs = null;
      if (colAlias != null)
        msgs = ((InternalEObject)colAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_FULL__COL_ALIAS, null, msgs);
      if (newColAlias != null)
        msgs = ((InternalEObject)newColAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_FULL__COL_ALIAS, null, msgs);
      msgs = basicSetColAlias(newColAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_FULL__COL_ALIAS, newColAlias, newColAlias));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Column getColName()
  {
    return colName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColName(Column newColName, NotificationChain msgs)
  {
    Column oldColName = colName;
    colName = newColName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_FULL__COL_NAME, oldColName, newColName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColName(Column newColName)
  {
    if (newColName != colName)
    {
      NotificationChain msgs = null;
      if (colName != null)
        msgs = ((InternalEObject)colName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_FULL__COL_NAME, null, msgs);
      if (newColName != null)
        msgs = ((InternalEObject)newColName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_FULL__COL_NAME, null, msgs);
      msgs = basicSetColName(newColName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_FULL__COL_NAME, newColName, newColName));
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
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return basicSetColAlias(null, msgs);
      case SqlPackage.COLUMN_FULL__COL_NAME:
        return basicSetColName(null, msgs);
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
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return getColAlias();
      case SqlPackage.COLUMN_FULL__COL_NAME:
        return getColName();
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
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        setColAlias((ColumnAlias)newValue);
        return;
      case SqlPackage.COLUMN_FULL__COL_NAME:
        setColName((Column)newValue);
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
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        setColAlias((ColumnAlias)null);
        return;
      case SqlPackage.COLUMN_FULL__COL_NAME:
        setColName((Column)null);
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
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return colAlias != null;
      case SqlPackage.COLUMN_FULL__COL_NAME:
        return colName != null;
    }
    return super.eIsSet(featureID);
  }

} //ColumnFullImpl
