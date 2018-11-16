package de.hdm.itp.server.db;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;


import de.hdm.itp.shared.bo.User;
import de.hdm.itp.server.db.*;

public class TestMapper {

	//@SuppressWarnings("null")
	public static void main(String[] args) {
		
		
		final UserMapper uMapper = UserMapper.userMapper();
		
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
		
		User u = new User();
		u.setId(10000002);
		u.setEmail("test2@test.de");
		u.setFirstname("Nieeels");
		u.setLastname("Kapperer");
		u.setGender("m");
		u.setNickname("Günni");
		System.out.println(s);
		
		
		uMapper.insert(u);
		
		User u2 = new User();
		u2.setId(10000002);
		User u3 = new User();
		u3 = uMapper.findByID(u2);
		System.out.println(u3.toString());
		System.out.println(u3.getFirstname());
			

	}

	
}
