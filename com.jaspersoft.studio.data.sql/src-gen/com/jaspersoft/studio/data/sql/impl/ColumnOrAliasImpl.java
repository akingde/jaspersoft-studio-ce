/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Or Alias</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl#getCfull <em>Cfull</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl#getColAlias <em>Col Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl#getAllCols <em>All Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnOrAliasImpl extends OrColumnImpl implements ColumnOrAlias
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
   * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected static final String ALIAS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected String alias = ALIAS_EDEFAULT;

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
   * The default value of the '{@link #getAllCols() <em>All Cols</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllCols()
   * @generated
   * @ordered
   */
  protected static final String ALL_COLS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAllCols() <em>All Cols</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllCols()
   * @generated
   * @ordered
   */
  protected String allCols = ALL_COLS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnOrAliasImpl()
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
    return SqlPackage.Literals.COLUMN_OR_ALIAS;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__CFULL, oldCfull, newCfull);
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
        msgs = ((InternalEObject)cfull).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OR_ALIAS__CFULL, null, msgs);
      if (newCfull != null)
        msgs = ((InternalEObject)newCfull).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OR_ALIAS__CFULL, null, msgs);
      msgs = basicSetCfull(newCfull, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__CFULL, newCfull, newCfull));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAlias()
  {
    return alias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAlias(String newAlias)
  {
    String oldAlias = alias;
    alias = newAlias;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__ALIAS, oldAlias, alias));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS, oldColAlias, newColAlias);
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
        msgs = ((InternalEObject)colAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS, null, msgs);
      if (newColAlias != null)
        msgs = ((InternalEObject)newColAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS, null, msgs);
      msgs = basicSetColAlias(newColAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS, newColAlias, newColAlias));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAllCols()
  {
    return allCols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAllCols(String newAllCols)
  {
    String oldAllCols = allCols;
    allCols = newAllCols;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__ALL_COLS, oldAllCols, allCols));
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
      case SqlPackage.COLUMN_OR_ALIAS__CFULL:
        return basicSetCfull(null, msgs);
      case SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS:
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
      case SqlPackage.COLUMN_OR_ALIAS__CFULL:
        return getCfull();
      case SqlPackage.COLUMN_OR_ALIAS__ALIAS:
        return getAlias();
      case SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS:
        return getColAlias();
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        return getAllCols();
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
      case SqlPackage.COLUMN_OR_ALIAS__CFULL:
        setCfull((ColumnFull)newValue);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__ALIAS:
        setAlias((String)newValue);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS:
        setColAlias((DbObjectName)newValue);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        setAllCols((String)newValue);
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
      case SqlPackage.COLUMN_OR_ALIAS__CFULL:
        setCfull((ColumnFull)null);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__ALIAS:
        setAlias(ALIAS_EDEFAULT);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS:
        setColAlias((DbObjectName)null);
        return;
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        setAllCols(ALL_COLS_EDEFAULT);
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
      case SqlPackage.COLUMN_OR_ALIAS__CFULL:
        return cfull != null;
      case SqlPackage.COLUMN_OR_ALIAS__ALIAS:
        return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
      case SqlPackage.COLUMN_OR_ALIAS__COL_ALIAS:
        return colAlias != null;
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        return ALL_COLS_EDEFAULT == null ? allCols != null : !ALL_COLS_EDEFAULT.equals(allCols);
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
    result.append(" (alias: ");
    result.append(alias);
    result.append(", allCols: ");
    result.append(allCols);
    result.append(')');
    return result.toString();
  }

} //ColumnOrAliasImpl
