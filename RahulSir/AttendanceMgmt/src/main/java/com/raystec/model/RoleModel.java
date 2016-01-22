package com.raystec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.raystec.bean.RoleBean;
import com.raystec.exception.ApplicationException;
import com.raystec.exception.DatabaseException;
import com.raystec.exception.DuplicateRecordException;
import com.raystec.utility.JDBCDataSource;

/**
 * JDBC Implementation of Role Model
 * 
 * @author Priyank
 * @version 1.0
 * @Copyright (c) Priyank
 */
public class RoleModel {
	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */

	public Integer nextPK() throws DatabaseException {
		log.debug("RoleModel method nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM AT_ROLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("DataBase Exception.. ", e);
			throw new DatabaseException("Exception:Exception getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel method nextPK started");
		return pk + 1;
	}

	/**
	 * Add a Role
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 * 
	 */
	public long add(RoleBean bean) throws ApplicationException,
			DuplicateRecordException {
		log.debug("UserModel method add started");
		Connection conn = null;
		int pk = 0;
		RoleBean duplicateRole = findByName(bean.getName());		
		
		// Check if create Role already exist
		if (duplicateRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + "in Model JDBC");
			conn.setAutoCommit(false);// Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO AT_ROLE VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception" + ex.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel method add ended");
		return pk;
	}

	public RoleBean findByName(String name) throws ApplicationException {
        log.debug("Model findBy EmailId Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM AT_ROLE WHERE NAME=?");
        RoleBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new RoleBean();
                bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting findByName");
        } finally {
            JDBCDataSource.closeConnection(conn);
         }
        log.debug("Model findBy EmailId End");
        return bean;
    }


	
	/**
	 * Delete a Role
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public void delete(RoleBean bean) throws ApplicationException {
		log.debug("RoleModel method delete started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM AT_ROLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();// End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("DataBase Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception ..Exception in delete rollback exception"
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception.. Exception in delete role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete ended");
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public RoleBean findByPK(long pk) throws ApplicationException {
		log.debug("RoleModel method findByPK started");
		StringBuffer sql = new StringBuffer("SELECT * FROM AT_ROLE WHERE ID=?");
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			log.error("DataBase Exception..", e);
			throw new ApplicationException(
					"Exception :Exception in getting findByPk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel method findByPK ended");
		return bean;
	}

	public void update(RoleBean bean) throws ApplicationException,
			DuplicateRecordException {
		log.debug("RoleModel method updated started");
		Connection conn = null;
		RoleBean duplicateRole = findByName(bean.getName());
		// Check if updated Role already exist
		if (duplicateRole != null && duplicateRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Role already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin Transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE AT_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());
			pstmt.executeUpdate();
			conn.commit();// End Transaction
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new ApplicationException(
						"Exception : update RoleBack exception"
								+ e1.getMessage());
			}
			throw new ApplicationException("Exception in updating role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel method update ended");
	}

	/**
	 * Search Role
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException 
	 * @throws DatabaseException
	 */
	public List search(RoleBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException 
	 * 
	 * @throws DatabaseException
	 */

	public List search(RoleBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("RoleModel method search started");
		StringBuffer sql = new StringBuffer("SELECT * FROM AT_ROLE WHERE 1=1");
		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null
					&& bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription()
						+ "%'");
			}
		}
		
	     // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

       ArrayList list=new ArrayList();
        Connection conn=null;
        try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				bean = new RoleBean();
                bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                list.add(bean);                
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("DataBase Exception ..",e);
			throw new ApplicationException("Exception: Exception in Search Role");						
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
        log.debug("RoleModel method search ended");
        return list;
	}
	
	/**
     * Get List of Role
     * 
     * @return list : List of Role
	 * @throws ApplicationException 
     * @throws DatabaseException
     */
	public List list() throws ApplicationException{
		return list(0,0);
	}
	
	  /**
     * Get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
	 * @throws ApplicationException 
     * @throws DatabaseException
     */

	public List list(int pageNo,int pageSize) throws ApplicationException{
		log.debug("RoleModel method list started");
		Connection conn=null;
		RoleBean bean=null;
		StringBuffer sql=new StringBuffer("SELECT * FROM AT_USER");
		 // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list=new ArrayList();
		try {
			conn=JDBCDataSource.getConnection();			
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();			
			while(rs.next()){
				bean=new RoleBean();
				bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                list.add(bean);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Database Exception ..",e);
			throw new ApplicationException("Exception..Exception in getting List of Role");
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel method list ended");
		return list;
	}	
}
