package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.test.app.entity.Hotel;
import com.test.app.config.MyBatisUtil;

@Repository
public class HotelMapper {

	public void saveHotel(Hotel hotel) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.insert("insertHotel", hotel);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public void updateHotel(Hotel hotel) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.update("updateHotel", hotel);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public void deleteHotel(String hotelId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			session.delete("deleteHotel", hotelId);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public List<Hotel> getAllHotels() {
		List<Hotel> hlist = new ArrayList<Hotel>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			hlist = session.selectList("getAllHotels");
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return hlist;
	}

	public Hotel getHotelById(String hotelId) {
		Hotel htl = new Hotel();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			htl = session.selectOne("findById", hotelId);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return htl;
	}
}
