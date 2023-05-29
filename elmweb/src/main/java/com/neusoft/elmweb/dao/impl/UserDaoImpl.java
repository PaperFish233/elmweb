package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.elmweb.dao.UserDao;
import com.neusoft.elmweb.po.User;
import com.neusoft.elmweb.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@Override
	public User getUserByIdByPass(String userid, String password) {
		String sql = "select * from user where userid = ? and password = ?";
		conn = DBUtil.getConnection();
		User user = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getInt("userSex"));
				user.setUserImg(rs.getString("userImg"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstm);
			DBUtil.close();
		}
		
		return user;
	}

	@Override
	public int saveUser(User user) {
		int result = 0;
        String sql = "insert into user values(?,?,?,?,?,1)";
        try {
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getUserId());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getUserName());
            pstm.setInt(4, user.getUserSex());
            pstm.setString(5, user.getUserImg());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(pstm);
            DBUtil.close();
        }
        return result;
	}

	@Override
	public User getUserById(String userId) {
		User userResult = null;
        String sql = "select * from user where userId=?";
        try {
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userId);
            rs = pstm.executeQuery();
            if(rs.next()) {
                userResult = new User();
                userResult.setUserId(rs.getString("userId"));
                userResult.setPassword(rs.getString("password"));
                userResult.setUserName(rs.getString("userName"));
                userResult.setUserSex(rs.getInt("userSex"));
                userResult.setUserImg(rs.getString("userImg"));
                userResult.setDelTag(rs.getInt("delTag"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs, pstm);
            DBUtil.close();
        }
        return userResult;
	}

}
