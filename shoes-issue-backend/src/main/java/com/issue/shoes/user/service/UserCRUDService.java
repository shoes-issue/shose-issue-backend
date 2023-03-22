package com.issue.shoes.user.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.communityBoard.vo.CommunityBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.user.dao.UserDao;
import com.issue.shoes.user.vo.User;

@Service
public class UserCRUDService implements UserService {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserDao userDao;
	private PlatformTransactionManager transactionManager;
	
	public UserCRUDService(PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}

	@Transactional
	@Override
	public List<Object> tradeBoardWrite(PageNation page) {
		
		page.setStartPage(page.getPage());//페이지 처음 번호 vo 넣음
		page.setUserId(page.getUserId());
		log.debug(page.getUserId());
		
		List<Object> totalList = new ArrayList<>();
		
		List<TradeBoard> list = null;
		
		try {
			list = userDao.searchMypageAllTradeBoard(page);
		
			int totalBoard = userDao.countTradeBoard();
			
			int totalPage = 0;
			//나머지 게시글들
			int remainBoard = totalBoard%10;
			
			page.setRemainBoardCount(remainBoard);
			
			if ((totalBoard%10) > 0 && totalBoard > 10) {
				totalPage = totalBoard/10 + 1;
			}else {
				totalPage = 1;
			}
			
			//1페이지일 경우 이전 페이지 없애기 위한 용도
			if (page.getPage() == 1) {
				page.setPrev(false);
			}
			
			ArrayList<Integer> buttonCount = new ArrayList<Integer>();
			
			if (totalPage <= 5) {				
				for (int i = 1; i <= totalPage; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}else if ((totalPage - page.getPage()) < 5 ){
				
				for (int i = totalPage-4; i <= totalPage; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}else {				
				for (int i = page.getPage(); i <= page.getPage()+4; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}
			
			page.setButtonCount(buttonCount);
			
			if (buttonCount.size() <= 5) {
				page.setPrev(false);
				page.setNext(false);
			}
			
			for (TradeBoard tradeBoard : list) {
				tradeBoard.setTradeImage("/images/tradeBoard/" + tradeBoard.getTradeImage());
				
				if (tradeBoard.getTradeStatus().equals("예약 가능")) {				
					tradeBoard.setStatusStyle("font-size: larger; color: blue");
				}else if (tradeBoard.getTradeStatus().equals("거래중")) {
					tradeBoard.setStatusStyle("font-size: larger; color: #ffc107");
				} else {
					tradeBoard.setStatusStyle("font-size: larger; color: red");				
				}
			}
			
			totalList.add(list);
			totalList.add(page);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalList;
	}
	
	@Transactional
	@Override
	public List<Object> tradeBoardLike(PageNation page) {
		
		page.setStartPage(page.getPage());//페이지 처음 번호 vo 넣음
		page.setUserId(page.getUserId());
		log.debug(page.getUserId());
		
		List<Object> totalList = new ArrayList<>();
		
		List<TradeBoard> list = null;
		
		try {
			list = userDao.searchMypagelikeTradeBoard(page);
		
			int totalBoard = userDao.countTradeBoard();
			
			int totalPage = 0;
			//나머지 게시글들
			int remainBoard = totalBoard%10;
			
			page.setRemainBoardCount(remainBoard);
			
			if ((totalBoard%10) > 0 && totalBoard > 10) {
				totalPage = totalBoard/10 + 1;
			}else {
				totalPage = 1;
			}
			
			//1페이지일 경우 이전 페이지 없애기 위한 용도
			if (page.getPage() == 1) {
				page.setPrev(false);
			}
			
			ArrayList<Integer> buttonCount = new ArrayList<Integer>();
			
			if (totalPage <= 5) {				
				for (int i = 1; i <= totalPage; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}else if ((totalPage - page.getPage()) < 5 ){
				
				for (int i = totalPage-4; i <= totalPage; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}else {				
				for (int i = page.getPage(); i <= page.getPage()+4; i++) {
					if(i == totalPage) {
						buttonCount.add(i);
						page.setNext(false);
						break;
					}else {
						buttonCount.add(i);
					}				
				}
			}
			
			page.setButtonCount(buttonCount);
			
			if (buttonCount.size() <= 5) {
				page.setPrev(false);
				page.setNext(false);
			}
			
			for (TradeBoard tradeBoard : list) {
				tradeBoard.setTradeImage("/images/tradeBoard/" + tradeBoard.getTradeImage());
				
				if (tradeBoard.getTradeStatus().equals("예약 가능")) {				
					tradeBoard.setStatusStyle("font-size: larger; color: blue");
				}else if (tradeBoard.getTradeStatus().equals("거래중")) {
					tradeBoard.setStatusStyle("font-size: larger; color: #ffc107");
				} else {
					tradeBoard.setStatusStyle("font-size: larger; color: red");				
				}
			}
			
			totalList.add(list);
			totalList.add(page);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalList;
	}
	
	@Transactional
	@Override
	public String searchUser(String userId) {
		// userId를 사용하여 유저 검색 기능 구현
		return userId;
	}
	
	@Transactional
	@Override
	public boolean idDuplicate(String userId) {
	    User user = userDao.selectByUserId(userId);
	    return user!= null;
	}
	
	@Transactional
	@Override
	public User selectUserById(String userId) {
		User user = userDao.selectByUserId(userId);
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
	public User createPreference(User user) throws Exception {
	    userDao.insertPreference(user);
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
		
		String uploadFolder = "C:\\Users\\aneli\\문서\\shoes-issue-frontend-vuetify\\public\\images\\profile";
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
	
	
	@Transactional
	@Override
	public List<CommunityBoard> selectcommunityAll(String userId) {
		
		List<CommunityBoard> user = userDao.selectcommunityAll(userId);
		
		return user;
	}


}
