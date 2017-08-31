/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.PivotCol;
import com.jaspersoft.studio.data.sql.PivotColumns;
import com.jaspersoft.studio.data.sql.PivotFunction;
import com.jaspersoft.studio.data.sql.Pivots;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.UsingCols;
import com.jaspersoft.studio.data.sql.WithColumns;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Db Object Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl#getEntries <em>Entries</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl#getDbname <em>Dbname</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DbObjectNameImpl extends ColumnFullImpl implements DbObjectName
{
  /**
   * The cached value of the '{@link #getEntries() <em>Entries</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntries()
   * @generated
   * @ordered
   */
  protected EList<DbObjectName> entries;

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
  public EList<DbObjectName> getEntries()
  {
    if (entries == null)
    {
      entries = new EObjectContainmentEList<DbObjectName>(DbObjectName.class, this, SqlPackage.DB_OBJECT_NAME__ENTRIES);
    }
    return entries;
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
      case SqlPackage.DB_OBJECT_NAME__ENTRIES:
        return ((InternalEList<?>)getEntries()).basicRemove(otherEnd, msgs);
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
      case SqlPackage.DB_OBJECT_NAME__ENTRIES:
        return getEntries();
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
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.DB_OBJECT_NAME__ENTRIES:
        getEntries().clear();
        getEntries().addAll((Collection<? extends DbObjectName>)newValue);
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
      case SqlPackage.DB_OBJECT_NAME__ENTRIES:
        getEntries().clear();
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
      case SqlPackage.DB_OBJECT_NAME__ENTRIES:
        return entries != null && !entries.isEmpty();
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
    if (baseClass == WithColumns.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UsingCols.class)
    {
      switch (derivedFeatureID)
      {
        case SqlPackage.DB_OBJECT_NAME__ENTRIES: return SqlPackage.USING_COLS__ENTRIES;
        default: return -1;
      }
    }
    if (baseClass == PivotFunction.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PivotColumns.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Pivots.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PivotCol.class)
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
    if (baseClass == WithColumns.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UsingCols.class)
    {
      switch (baseFeatureID)
      {
        case SqlPackage.USING_COLS__ENTRIES: return SqlPackage.DB_OBJECT_NAME__ENTRIES;
        default: return -1;
      }
    }
    if (baseClass == PivotFunction.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PivotColumns.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Pivots.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PivotCol.class)
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
