/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOperand;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOperandImpl#getCfull <em>Cfull</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOperandImpl#getOra <em>Ora</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnOperandImpl extends MinimalEObjectImpl.Container implements ColumnOperand
{
  /**
   * The cached value of the '{@link #getCfull() <em>Cfull</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCfull()
   * @generated
   * @ordered
   */
  protected ColumnFull cfull;

  /**
   * The default value of the '{@link #getOra() <em>Ora</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOra()
   * @generated
   * @ordered
   */
  protected static final String ORA_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOra() <em>Ora</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOra()
   * @generated
   * @ordered
   */
  protected String ora = ORA_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnOperandImpl()
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
    return SqlPackage.Literals.COLUMN_OPERAND;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnFull getCfull()
  {
    return cfull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCfull(ColumnFull newCfull, NotificationChain msgs)
  {
    ColumnFull oldCfull = cfull;
    cfull = newCfull;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OPERAND__CFULL, oldCfull, newCfull);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCfull(ColumnFull newCfull)
  {
    if (newCfull != cfull)
    {
      NotificationChain msgs = null;
      if (cfull != null)
        msgs = ((InternalEObject)cfull).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OPERAND__CFULL, null, msgs);
      if (newCfull != null)
        msgs = ((InternalEObject)newCfull).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OPERAND__CFULL, null, msgs);
      msgs = basicSetCfull(newCfull, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OPERAND__CFULL, newCfull, newCfull));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOra()
  {
    return ora;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOra(String newOra)
  {
    String oldOra = ora;
    ora = newOra;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OPERAND__ORA, oldOra, ora));
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
      case SqlPackage.COLUMN_OPERAND__CFULL:
        return basicSetCfull(null, msgs);
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
      case SqlPackage.COLUMN_OPERAND__CFULL:
        return getCfull();
      case SqlPackage.COLUMN_OPERAND__ORA:
        return getOra();
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
      case SqlPackage.COLUMN_OPERAND__CFULL:
        setCfull((ColumnFull)newValue);
        return;
      case SqlPackage.COLUMN_OPERAND__ORA:
        setOra((String)newValue);
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
      case SqlPackage.COLUMN_OPERAND__CFULL:
        setCfull((ColumnFull)null);
        return;
      case SqlPackage.COLUMN_OPERAND__ORA:
        setOra(ORA_EDEFAULT);
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
      case SqlPackage.COLUMN_OPERAND__CFULL:
        return cfull != null;
      case SqlPackage.COLUMN_OPERAND__ORA:
        return ORA_EDEFAULT == null ? ora != null : !ORA_EDEFAULT.equals(ora);
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
    result.append(" (ora: ");
    result.append(ora);
    result.append(')');
    return result.toString();
  }

} //ColumnOperandImpl
