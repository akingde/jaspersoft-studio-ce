/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.TableOrAlias;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Db Object Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl#getTblAlias <em>Tbl Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl#getDbname <em>Dbname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DbObjectNameImpl extends ColumnFullImpl implements DbObjectName
{
  /**
   * The cached value of the '{@link #getTblAlias() <em>Tbl Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTblAlias()
   * @generated
   * @ordered
   */
  protected DbObjectName tblAlias;

  /**
   * The default value of the '{@link #getDbname() <em>Dbname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbname()
   * @generated
   * @ordered
   */
  protected static final String DBNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDbname() <em>Dbname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbname()
   * @generated
   * @ordered
   */
  protected String dbname = DBNAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DbObjectNameImpl()
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
    return SqlPackage.Literals.DB_OBJECT_NAME;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbObjectName getTblAlias()
  {
    return tblAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTblAlias(DbObjectName newTblAlias, NotificationChain msgs)
  {
    DbObjectName oldTblAlias = tblAlias;
    tblAlias = newTblAlias;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.DB_OBJECT_NAME__TBL_ALIAS, oldTblAlias, newTblAlias);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTblAlias(DbObjectName newTblAlias)
  {
    if (newTblAlias != tblAlias)
    {
      NotificationChain msgs = null;
      if (tblAlias != null)
        msgs = ((InternalEObject)tblAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.DB_OBJECT_NAME__TBL_ALIAS, null, msgs);
      if (newTblAlias != null)
        msgs = ((InternalEObject)newTblAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.DB_OBJECT_NAME__TBL_ALIAS, null, msgs);
      msgs = basicSetTblAlias(newTblAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.DB_OBJECT_NAME__TBL_ALIAS, newTblAlias, newTblAlias));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDbname()
  {
    return dbname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDbname(String newDbname)
  {
    String oldDbname = dbname;
    dbname = newDbname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.DB_OBJECT_NAME__DBNAME, oldDbname, dbname));
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
      case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS:
        return basicSetTblAlias(null, msgs);
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
      case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS:
        return getTblAlias();
      case SqlPackage.DB_OBJECT_NAME__DBNAME:
        return getDbname();
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
      case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS:
        setTblAlias((DbObjectName)newValue);
        return;
      case SqlPackage.DB_OBJECT_NAME__DBNAME:
        setDbname((String)newValue);
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
      case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS:
        setTblAlias((DbObjectName)null);
        return;
      case SqlPackage.DB_OBJECT_NAME__DBNAME:
        setDbname(DBNAME_EDEFAULT);
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
      case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS:
        return tblAlias != null;
      case SqlPackage.DB_OBJECT_NAME__DBNAME:
        return DBNAME_EDEFAULT == null ? dbname != null : !DBNAME_EDEFAULT.equals(dbname);
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
    if (baseClass == TableOrAlias.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TableFull.class)
    {
      switch (derivedFeatureID)
      {
        case SqlPackage.DB_OBJECT_NAME__TBL_ALIAS: return SqlPackage.TABLE_FULL__TBL_ALIAS;
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
    if (baseClass == TableOrAlias.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TableFull.class)
    {
      switch (baseFeatureID)
      {
        case SqlPackage.TABLE_FULL__TBL_ALIAS: return SqlPackage.DB_OBJECT_NAME__TBL_ALIAS;
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
    result.append(" (dbname: ");
    result.append(dbname);
    result.append(')');
    return result.toString();
  }

} //DbObjectNameImpl
