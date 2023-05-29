package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.dao.DeliveryAddressDao;
import com.neusoft.elmweb.po.DeliveryAddress;
import com.neusoft.elmweb.util.DBUtil;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		List<DeliveryAddress> list = new ArrayList<>();
        String sql = "select * from deliveryAddress where userId=? order by daId";
        con = DBUtil.getConnection();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while(rs.next()) {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setDaId(rs.getInt("daId"));
                deliveryAddress.setContactName(rs.getString("contactName"));
                deliveryAddress.setContactSex(rs.getInt("contactSex"));
                deliveryAddress.setContactTel(rs.getString("contactTel"));
                deliveryAddress.setAddress(rs.getString("address"));
                deliveryAddress.setUserId(rs.getString("userId"));
                list.add(deliveryAddress);
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs, pst);
            DBUtil.close();
        }
        return list;
	}

	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
        String sql = "insert into deliveryAddress values(null,?,?,?,?,?)";
        con = DBUtil.getConnection();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, deliveryAddress.getContactName());
            pst.setInt(2, deliveryAddress.getContactSex());
            pst.setString(3, deliveryAddress.getContactTel());
            pst.setString(4, deliveryAddress.getAddress());
            pst.setString(5, deliveryAddress.getUserId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(pst);
            DBUtil.close();
        }
        return result;
	}

	@Override
	public int removeDeliveryAddress(Integer daId) {
		int result = 0;
        String sql = "delete from deliveryAddress where daId=?";
        con = DBUtil.getConnection();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, daId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(pst);
            DBUtil.close();
        }
        return result;
	}

	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		DeliveryAddress deliveryAddress = null;
        String sql = "select * from deliveryAddress where daId=?";
        con = DBUtil.getConnection();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, daId);
            rs = pst.executeQuery();
            if(rs.next()) {
                deliveryAddress = new DeliveryAddress();
                deliveryAddress.setDaId(rs.getInt("daId"));
                deliveryAddress.setContactName(rs.getString("contactName"));
                deliveryAddress.setContactSex(rs.getInt("contactSex"));
                deliveryAddress.setContactTel(rs.getString("contactTel"));
                deliveryAddress.setAddress(rs.getString("address"));
                deliveryAddress.setUserId(rs.getString("userId"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs, pst);
            DBUtil.close();
        }
        return deliveryAddress;
	}

	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
        String sql = "update deliveryAddress set contactName=?,contactSex=?,contactTel=?,address=?,userId=? where daId=?";
        con = DBUtil.getConnection();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, deliveryAddress.getContactName());
            pst.setInt(2, deliveryAddress.getContactSex());
            pst.setString(3, deliveryAddress.getContactTel());
            pst.setString(4, deliveryAddress.getAddress());
            pst.setString(5, deliveryAddress.getUserId());
            pst.setInt(6, deliveryAddress.getDaId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(pst);
            DBUtil.close();
        }
        return result;
	}

}
