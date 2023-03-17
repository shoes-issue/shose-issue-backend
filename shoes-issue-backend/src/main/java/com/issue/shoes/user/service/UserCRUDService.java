package com.issue.shoes.user.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.user.dao.UserDao;
import com.issue.shoes.user.vo.User;

@Service
public class UserCRUDService implements UserService {

	@Autowired
	private UserDao userDao;
	private PlatformTransactionManager transactionManager;
	
	public UserCRUDService(PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}

	@Transactional
	@Override
	public String searchUser(String userId) {
		// userId를 사용하여 유저 검색 기능 구현
		return userId;
	}
	
	@Transactional
	@Override
	public User selectUserById(String userId) {
		User user = userDao.selectUserById(userId);
		return user;
	}
	
	@Transactional
	@Override
	public User createUser(User user) throws Exception {
	    userDao.insertUser(user);
	    return user;
	}
	
	@Transactional
	@Override
	public User createUseroauth(User user) throws Exception {
	    userDao.insertUseroauth(user);
	    return user;
	}

	@Transactional
	@Override
	public User updateUser(User user) throws Exception {
		
	    userDao.updateUser(user);
	    
	    uploadImg(user.getProfileImage(), user.getImageUrl());
	    
	    return user;
	}

	private void uploadImg(String profileImage, MultipartFile multipartFile) {
		
		String uploadFolder = "C:\\Users\\aneli\\문서\\shose-issue-frontend\\images\\profile";
		
		File uploadPath = new File(uploadFolder);

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		File saveFile = new File(uploadFolder, profileImage);
		
		try {
			multipartFile.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Transactional
	@Override
	public String deleteUser(String userId) throws Exception {
	    userDao.deleteUser(userId);
	    return userId;
	}
	
	@Transactional
	@Override
	public String deleteUseroauth(String userId) throws Exception {
	    userDao.deleteUseroauth(userId);
	    return userId;
	}

}
