package de.hdm.itp.server.db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.SimpleReport;
import de.hdm.itp.server.EditorAdministrationImpl;
import de.hdm.itp.server.ReportGeneratorImpl;
import de.hdm.itp.server.db.*;
import de.hdm.itp.shared.bo.*;

public class TestMapper {

	// @SuppressWarnings("null")
	public static void main(String[] args) {

		final PostMapper pMapper = PostMapper.postMapper();

		final CommentMapper cMapper = CommentMapper.commentMapper();

		final UserMapper uMapper = UserMapper.userMapper();
		
		final LikeMapper lMapper = LikeMapper.likeMapper();

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);

		User u = new User();
		Comment c = new Comment();
		u.setId(10000001);
		
		Vector<Like> result = new Vector<Like>();
		result = lMapper.findAllByUID(u);
		for (Like l : result) {
			System.out.println(l.getId());
			System.out.println(l.getOwnerId());
			System.out.println(l.getPostId());
			System.out.println(l.getCreateDate());
			
			
		}
		
		
		
		/*
		 * User u = new User(); u.setId(10000002); u.setEmail("test2@test.de");
		 * u.setFirstname("Nieeels"); u.setLastname("Kapperer"); u.setGender("m");
		 * u.setNickname("Günni");
		 * 
		 * System.out.println(s);
		 * 
		 * 
		 * //uMapper.insert(u);
		 * 
		 * User u2 = new User(); u2.setId(10000002); User u3 = new User(); u3 =
		 * uMapper.findByID(u2); System.out.println(u3.toString());
		 * System.out.println(u3.getFirstname());
		 */

		//ReportGeneratorImpl report = new ReportGeneratorImpl();

		// ReportAdministrationImpl();
		//report.init();

		/*
		 * User u6 = new User(); u6.setId(10000001);
		 * 
		 * SimpleReport r = report.createAllPostsFromUserReport(u6);
		 * 
		 * System.out.println(r);
		 * 
		 */

		/*
		 * final LikeMapper lMapper = LikeMapper.likeMapper();
		 * 
		 * 
		 * Vector<Like> result = lMapper.findAllByUID(u); for(Like l : result) {
		 * System.out.println(l.getOwnerId()); System.out.println(l.getCreateDate());
		 * 
		 * 
		 * }
		 * 
		 */

		
//		java.sql.Timestamp createdate = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
//		java.sql.Timestamp moddate = java.sql.Timestamp.valueOf("2020-09-23 10:10:10.0");
//
//		c.setCreateDate(createdate);
//		c.setModDate(moddate);
//		
//		System.out.println(ts);
//
//		Vector<Comment> result = cMapper.findAllByUIDandDate(u, c);
//		for (Comment c1 : result) {
//			System.out.println(c1.getPostId());
//			System.out.println(c1.getOwnerId());
//			System.out.println(c1.getModDate());
//
//		}

		/*
		 * Vector<Post> result = pMapper.findAllByUID(u); for(Post p : result) {
		 * System.out.println(p.getContent()); System.out.println(p.getCreateDate()); }
		 * 
		 * //report.getAllPostsOfUser(u6)
		 */
	}

}
