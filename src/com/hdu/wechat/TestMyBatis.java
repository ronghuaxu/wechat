package com.hdu.wechat;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hdu.bean.User;

import hdu.edu.myabitis.util.MyBatisUtil;
import hdu.edu.quesans.mapper.UserMapper;

public class TestMyBatis {

	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	public static void main(String[] args) {

		// testAdd();
		System.out.println("23324");

		getUser();

	}

	public static void testAdd() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User("lisi", new Integer(25));
			userMapper.insertUser(user);
			sqlSession.commit();// ����һ��Ҫ�ύ����Ȼ���ݽ���ȥ���ݿ���
		} finally {
			sqlSession.close();
		}
	}

	public static void getUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = new ArrayList<User>();
			users = userMapper.getUser();
			for (int i = 0; i < users.size(); i++) {

				System.out.println("name: " + users.get(i).getName() + "|age: " + users.get(i).getOpen_id());

			}

		} finally {
			sqlSession.close();
		}
	}
}
