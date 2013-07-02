/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.ColumnAlias;
import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.GroupByColumnFull;
import com.jaspersoft.studio.data.sql.GroupByColumns;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.Table;
import com.jaspersoft.studio.data.sql.TableAlias;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.TableOrAlias;
import com.jaspersoft.studio.data.sql.Tables;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Full</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl#getGroupByColumn <em>Group By Column</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl#getColAlias <em>Col Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl#getColName <em>Col Name</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl#getTblAlias <em>Tbl Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.TableFullImpl#getTbl <em>Tbl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableFullImpl extends OrderByColumnFullImpl implements TableFull
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
   * The cached value of the '{@link #getTblAlias() <em>Tbl Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTblAlias()
   * @generated
   * @ordered
   */
  protected TableAlias tblAlias;

  /**
   * The cached value of the '{@link #getTbl() <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTbl()
   * @generated
   * @ordered
   */
  protected Table tbl;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TableFullImpl()
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
    return SqlPackage.Literals.TABLE_FULL;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__GROUP_BY_COLUMN, oldGroupByColumn, newGroupByColumn);
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
        msgs = ((InternalEObject)groupByColumn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__GROUP_BY_COLUMN, null, msgs);
      if (newGroupByColumn != null)
        msgs = ((InternalEObject)newGroupByColumn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__GROUP_BY_COLUMN, null, msgs);
      msgs = basicSetGroupByColumn(newGroupByColumn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__GROUP_BY_COLUMN, newGroupByColumn, newGroupByColumn));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__COL_ALIAS, oldColAlias, newColAlias);
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
        msgs = ((InternalEObject)colAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__COL_ALIAS, null, msgs);
      if (newColAlias != null)
        msgs = ((InternalEObject)newColAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__COL_ALIAS, null, msgs);
      msgs = basicSetColAlias(newColAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__COL_ALIAS, newColAlias, newColAlias));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__COL_NAME, oldColName, newColName);
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
        msgs = ((InternalEObject)colName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__COL_NAME, null, msgs);
      if (newColName != null)
        msgs = ((InternalEObject)newColName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__COL_NAME, null, msgs);
      msgs = basicSetColName(newColName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__COL_NAME, newColName, newColName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableAlias getTblAlias()
  {
    return tblAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTblAlias(TableAlias newTblAlias, NotificationChain msgs)
  {
    TableAlias oldTblAlias = tblAlias;
    tblAlias = newTblAlias;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__TBL_ALIAS, oldTblAlias, newTblAlias);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTblAlias(TableAlias newTblAlias)
  {
    if (newTblAlias != tblAlias)
    {
      NotificationChain msgs = null;
      if (tblAlias != null)
        msgs = ((InternalEObject)tblAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__TBL_ALIAS, null, msgs);
      if (newTblAlias != null)
        msgs = ((InternalEObject)newTblAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__TBL_ALIAS, null, msgs);
      msgs = basicSetTblAlias(newTblAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__TBL_ALIAS, newTblAlias, newTblAlias));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Table getTbl()
  {
    return tbl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTbl(Table newTbl, NotificationChain msgs)
  {
    Table oldTbl = tbl;
    tbl = newTbl;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__TBL, oldTbl, newTbl);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTbl(Table newTbl)
  {
    if (newTbl != tbl)
    {
      NotificationChain msgs = null;
      if (tbl != null)
        msgs = ((InternalEObject)tbl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__TBL, null, msgs);
      if (newTbl != null)
        msgs = ((InternalEObject)newTbl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE_FULL__TBL, null, msgs);
      msgs = basicSetTbl(newTbl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE_FULL__TBL, newTbl, newTbl));
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
      case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN:
        return basicSetGroupByColumn(null, msgs);
      case SqlPackage.TABLE_FULL__COL_ALIAS:
        return basicSetColAlias(null, msgs);
      case SqlPackage.TABLE_FULL__COL_NAME:
        return basicSetColName(null, msgs);
      case SqlPackage.TABLE_FULL__TBL_ALIAS:
        return basicSetTblAlias(null, msgs);
      case SqlPackage.TABLE_FULL__TBL:
        return basicSetTbl(null, msgs);
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
      case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN:
        return getGroupByColumn();
      case SqlPackage.TABLE_FULL__COL_ALIAS:
        return getColAlias();
      case SqlPackage.TABLE_FULL__COL_NAME:
        return getColName();
      case SqlPackage.TABLE_FULL__TBL_ALIAS:
        return getTblAlias();
      case SqlPackage.TABLE_FULL__TBL:
        return getTbl();
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
      case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN:
        setGroupByColumn((Column)newValue);
        return;
      case SqlPackage.TABLE_FULL__COL_ALIAS:
        setColAlias((ColumnAlias)newValue);
        return;
      case SqlPackage.TABLE_FULL__COL_NAME:
        setColName((Column)newValue);
        return;
      case SqlPackage.TABLE_FULL__TBL_ALIAS:
        setTblAlias((TableAlias)newValue);
        return;
      case SqlPackage.TABLE_FULL__TBL:
        setTbl((Table)newValue);
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
      case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN:
        setGroupByColumn((Column)null);
        return;
      case SqlPackage.TABLE_FULL__COL_ALIAS:
        setColAlias((ColumnAlias)null);
        return;
      case SqlPackage.TABLE_FULL__COL_NAME:
        setColName((Column)null);
        return;
      case SqlPackage.TABLE_FULL__TBL_ALIAS:
        setTblAlias((TableAlias)null);
        return;
      case SqlPackage.TABLE_FULL__TBL:
        setTbl((Table)null);
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
      case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN:
        return groupByColumn != null;
      case SqlPackage.TABLE_FULL__COL_ALIAS:
        return colAlias != null;
      case SqlPackage.TABLE_FULL__COL_NAME:
        return colName != null;
      case SqlPackage.TABLE_FULL__TBL_ALIAS:
        return tblAlias != null;
      case SqlPackage.TABLE_FULL__TBL:
        return tbl != null;
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
    if (baseClass == GroupByColumnFull.class)
    {
      switch (derivedFeatureID)
      {
        case SqlPackage.TABLE_FULL__GROUP_BY_COLUMN: return SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN;
        default: return -1;
      }
    }
    if (baseClass == Columns.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ColumnOrAlias.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ColumnFull.class)
    {
      switch (derivedFeatureID)
      {
        case SqlPackage.TABLE_FULL__COL_ALIAS: return SqlPackage.COLUMN_FULL__COL_ALIAS;
        case SqlPackage.TABLE_FULL__COL_NAME: return SqlPackage.COLUMN_FULL__COL_NAME;
        default: return -1;
      }
    }
    if (baseClass == Tables.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TableOrAlias.class)
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
    if (baseClass == GroupByColumns.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == GroupByColumnFull.class)
    {
      switch (baseFeatureID)
      {
        case SqlPackage.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN: return SqlPackage.TABLE_FULL__GROUP_BY_COLUMN;
        default: return -1;
      }
    }
    if (baseClass == Columns.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ColumnOrAlias.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ColumnFull.class)
    {
      switch (baseFeatureID)
      {
        case SqlPackage.COLUMN_FULL__COL_ALIAS: return SqlPackage.TABLE_FULL__COL_ALIAS;
        case SqlPackage.COLUMN_FULL__COL_NAME: return SqlPackage.TABLE_FULL__COL_NAME;
        default: return -1;
      }
    }
    if (baseClass == Tables.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TableOrAlias.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //TableFullImpl
