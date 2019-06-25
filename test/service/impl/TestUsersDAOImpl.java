package service.impl;

import org.junit.Test;
import org.junit.Assert;

import entity.Users;
import service.UsersDAO;

public class TestUsersDAOImpl {
	
	@Test
	public void testUsersLogin() {
		Users u = new Users(1, "zhangsan", "123456");
		UsersDAO udao = new UsersDAOImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
	}
}
