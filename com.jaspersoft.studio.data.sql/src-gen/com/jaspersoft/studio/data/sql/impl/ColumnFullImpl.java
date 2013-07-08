/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.GroupByColumns;
import com.jaspersoft.studio.data.sql.Operand;
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
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnFullImpl#getColAlias <em>Col Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnFullImpl extends ColumnOrAliasImpl implements ColumnFull
{
  /**
   * The default value of the '{@link #getScalar() <em>Scalar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected static final String SCALAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getScalar() <em>Scalar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected String scalar = SCALAR_EDEFAULT;

  /**
   * The cached value of the '{@link #getColAlias() <em>Col Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColAlias()
   * @generated
   * @ordered
   */
  protected DbObjectName colAlias;

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
  public String getScalar()
  {
    return scalar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScalar(String newScalar)
  {
    String oldScalar = scalar;
    scalar = newScalar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_FULL__SCALAR, oldScalar, scalar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbObjectName getColAlias()
  {
    return colAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColAlias(DbObjectName newColAlias, NotificationChain msgs)
  {
    DbObjectName oldColAlias = colAlias;
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
  public void setColAlias(DbObjectName newColAlias)
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
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return basicSetColAlias(null, msgs);
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
      case SqlPackage.COLUMN_FULL__SCALAR:
        return getScalar();
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return getColAlias();
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
      case SqlPackage.COLUMN_FULL__SCALAR:
        setScalar((String)newValue);
        return;
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        setColAlias((DbObjectName)newValue);
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
      case SqlPackage.COLUMN_FULL__SCALAR:
        setScalar(SCALAR_EDEFAULT);
        return;
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        setColAlias((DbObjectName)null);
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
      case SqlPackage.COLUMN_FULL__SCALAR:
        return SCALAR_EDEFAULT == null ? scalar != null : !SCALAR_EDEFAULT.equals(scalar);
      case SqlPackage.COLUMN_FULL__COL_ALIAS:
        return colAlias != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == GroupByColumns.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Operand.class)
    {
      switch (derivedFeatureID)
      {
        case SqlPackage.COLUMN_FULL__SCALAR: return SqlPackage.OPERAND__SCALAR;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == GroupByColumns.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Operand.class)
    {
      switch (baseFeatureID)
      {
        case SqlPackage.OPERAND__SCALAR: return SqlPackage.COLUMN_FULL__SCALAR;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (scalar: ");
    result.append(scalar);
    result.append(')');
    return result.toString();
  }

} //ColumnFullImpl
