package hdu.edu.quesans.mapper;

import java.util.List;

import com.hdu.bean.User;

public interface UserMapper {
	
	public void insertUser(User user);

	public List<User> getUser();
}
