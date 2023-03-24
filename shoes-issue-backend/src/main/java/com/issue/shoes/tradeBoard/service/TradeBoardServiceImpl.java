package com.issue.shoes.tradeBoard.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.message.dao.MessageDaoImpl;
import com.issue.shoes.message.vo.Message;
import com.issue.shoes.tradeBoard.dao.TradeBoardDao;
import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.RendingInfo;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

@Service
@PropertySource("classpath:/config/imageDirectory.properties")
public class TradeBoardServiceImpl implements TradeBoardService {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	private TradeBoardDao dao;
	
	@Autowired
	MessageDaoImpl messageDao;
	
	@Value("${directory}")
	private String directory;

	private final PlatformTransactionManager transactionManager;

	public TradeBoardServiceImpl(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public List<Object> searchAllTradeBoard(PageNation page) {
		
		page.setStartPage(page.getPage());//페이지 처음 번호 vo 넣음
		
		List<Object> totalList = new ArrayList<>();
		
		List<TradeBoard> list = null;
		
		try {
			list = dao.searchAllTradeBoard(page);
		
			int totalBoard = dao.countTradeBoard();
			
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

	@Override
	public List<Object> selectTradeTitle(PageNation page) {
		
		page.setStartPage(page.getPage());//페이지 처음 번호 vo 넣음
		
		List<Object> totalList = new ArrayList<>();
		
		List<TradeBoard> list = null;
		try {
			list = dao.selectTradeTitle(page);
			
			int totalBoard = dao.countTradeBoardTitle(page);
			
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

	@Override
	public List<Object> insertTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile) {

		UUID boardUuid = UUID.randomUUID();
		tradeBoard.setTradeId(boardUuid.toString());
		tradeBoard.setTradeStatus("예약 가능");
		tradeBoard.setTradeLike(0);

		String uploadFileName = "";

		String insertString = uploadFile[0].getOriginalFilename();
		uploadFileName = insertString.substring(uploadFileName.lastIndexOf("\\") + 1);

		UUID imgUuid = UUID.randomUUID();
		uploadFileName = imgUuid.toString() + "_" + uploadFileName;

		tradeBoard.setTradeImage(uploadFileName);

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		int result = 0;
		List<Object> list = null;

		try {
			result = dao.insertTradeBoard(tradeBoard);

			boolean success = false;
			if (result == 1) {
				success = uploadImg(uploadFile, imgUuid);
			} else {
				transactionManager.rollback(txStatus);
			}

			if (success) {
				PageNation page = new PageNation();
				list = searchAllTradeBoard(page);
				transactionManager.commit(txStatus);
			}

		} catch (Exception e) {
			e.printStackTrace();

			transactionManager.rollback(txStatus);
		}

		return list;
	}

	private boolean uploadImg(MultipartFile[] uploadFile, UUID imgUuid) {

		boolean success = true;

		String uploadFolder = directory;

		File uploadPath = new File(uploadFolder);

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		String uploadFileName = uploadFile[0].getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
		uploadFileName = imgUuid.toString() + "_" + uploadFileName;

		File saveFile = new File(uploadFolder, uploadFileName);

		try {
			uploadFile[0].transferTo(saveFile);
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public TradeBoardDetail selectTradeBoardDetail(String tradeId, String userId) {

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		TradeBoardDetail boardDetail = null;
		try {
			boardDetail = dao.selectTradeBoardDetail(tradeId);
			TradeBoardDetail user = dao.selecTradeBoardUser(userId);
			boardDetail.setNickName(user.getNickName());
			boardDetail.setPoint(user.getPoint());
			boardDetail.setUserId(userId);
			boardDetail.setTradeImage("/images/tradeBoard/" + boardDetail.getTradeImage());

			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}

		return boardDetail;
	}

	@Override
	public UpdateContent selectUpdateContent(String tradeId) {

		UpdateContent contents = dao.selectUpdateContent(tradeId);

		if (contents.getTradeImage() != null) {
			contents.setTradeImage("/images/tradeBoard/" + contents.getTradeImage());
		}

		return contents;
	}

	@Override
	public List<Object> updateTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile) {

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		List<Object> list = null;
		
		//수정날짜
		LocalDateTime date = LocalDateTime.now();
		String sysDate = date.toString().replace("T", " ").substring(0,19);
		try {
			if (uploadFile == null) {
				tradeBoard.setTradeUpdateDate(sysDate);
				dao.updateTradeBoard(tradeBoard);
				PageNation page = new PageNation();
				list = searchAllTradeBoard(page);
			} else {
				String uploadFileName = "";
				String insertString = uploadFile[0].getOriginalFilename();
				uploadFileName = insertString.substring(uploadFileName.lastIndexOf("\\") + 1);

				UUID imgUuid = UUID.randomUUID();
				uploadFileName = imgUuid.toString() + "_" + uploadFileName;
				boolean result = deleteAndUploadImg(tradeBoard.getTradeImage(), uploadFile, imgUuid);
				
				
				if (result) {
					
					tradeBoard.setTradeUpdateDate(sysDate);
					tradeBoard.setTradeImage(uploadFileName);
					
					dao.updateTradeBoardIncludeImg(tradeBoard);
					
					PageNation page = new PageNation();
					list = searchAllTradeBoard(page);
					
					transactionManager.commit(txStatus);
				}
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}

		return list;
	}

	private boolean deleteAndUploadImg(String tradeImage, MultipartFile[] uploadFile, UUID imgUuid) {
		
		boolean result = false;
		
		String root = directory.replace("/images/tradeBoard", "");
		tradeImage = root + tradeImage;
		try {
			File file = new File(tradeImage);
			
			if( file.exists() ){
				result = file.delete(); //삭제
				result = uploadImg(uploadFile, imgUuid); //업로드
	    	}else{
	    		result = uploadImg(uploadFile, imgUuid); 
	    		log.debug("파일이 존재하지 않습니다.");
	    	}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Object> deleteTradeBoard(String tradeId, String tradeImage) {
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		//삭제날짜
		LocalDateTime date = LocalDateTime.now();
		String sysDate = date.toString().replace("T", " ").substring(0,19);
		
		List<Object> list = null;
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("tradeId", tradeId);
			map.put("sysDate", sysDate);
			
			int result = dao.deleteDateUpdate(map);
			
			if (result == 1) {
				String root = directory.replace("/images/tradeBoard", "");
				tradeImage = root + tradeImage;
				File file = new File(tradeImage);
				
				if( file.exists() ){
					file.delete();
				}
			}
			
			PageNation page = new PageNation();
			list = searchAllTradeBoard(page);
			
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int clickLike(TradeBoardLike like) {
		
		int result = 0;
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		String clickUser = dao.selectClickUser(like);
		System.out.println(clickUser);
		
		
		try {
		if (clickUser == null) {
			
			dao.insertLike(like);
			result = 1;
		} else {
			dao.deletLike(like);
		}
		transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String updateStatus(String tradeId, String writerId) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tradeId", tradeId);
		map.put("tradeStatus", "거래중");
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		String returnString = "";
		try {
			int result = dao.updateStatus(map);
			
			if (result == 1) {
				//좋아요 누른 사람들에게 쪽지 보내는 부분
				List<String> likePeople = dao.selectClickLikePeople(tradeId);
				
				Message message = new Message();
				message.setMessageTitle("상품 예약 알림");
				message.setMessageContents("회원님의 관심목록 상품이 예약상태가 되었습니다.");
				
				message.setMessageOpenstatus(false);
				message.setMessageSender(writerId); //보내는 사람
				
				for (String person : likePeople) {
					UUID uuid = UUID.randomUUID();
					message.setMessageId(uuid.toString());//UUID
					message.setMessageReceiver(person); //좋아요 누른 사람				
					messageDao.create(message);
				}
				
				returnString = "거래중";
				transactionManager.commit(txStatus);
			}else {
				transactionManager.rollback(txStatus);			
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);			
			e.printStackTrace();
		}
		
		return returnString;
	}

	@Override
	public String updateStatusCancel(String tradeId) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tradeId", tradeId);
		map.put("tradeStatus", "예약 가능");
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		String returnString = "";
		
		try {
			int result = dao.updateStatus(map);
			
			if (result == 1) {
				
				returnString = "예약 가능";
				transactionManager.commit(txStatus);
			}else {
				transactionManager.rollback(txStatus);			
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);			
			e.printStackTrace();
		}
		return returnString;
		
	}

	@Override
	public String updateStatusComplete(String tradeId, String writerId) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tradeId", tradeId);
		map.put("tradeStatus", "판매 완료");

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		String returnString = "";

		try {
			int result = dao.updateStatus(map);

			if (result == 1) {
				//좋아요 누른 사람들에게 쪽지 보내는 부분
				List<String> likePeople = dao.selectClickLikePeople(tradeId);
				
				Message message = new Message();
				message.setMessageTitle("상품 판매완료 알림");
				message.setMessageContents("회원님의 관심목록 상품이 모두 판매었습니다.");
				
				message.setMessageOpenstatus(false);
				message.setMessageSender(writerId); //보내는 사람
				
				for (String person : likePeople) {
					UUID uuid = UUID.randomUUID();
					message.setMessageId(uuid.toString());//UUID
					message.setMessageReceiver(person); //좋아요 누른 사람				
					messageDao.create(message);
				}
				
				returnString = "판매 완료";
				transactionManager.commit(txStatus);
			} else {
				transactionManager.rollback(txStatus);
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}
		return returnString;
	}

	@Override
	public List<RendingInfo> selectRendingBody() {
		
		List<RendingInfo> list = dao.selectRendingBody();
		
		for (RendingInfo rendingInfo : list) {
			rendingInfo.setTradeImage("/images/tradeBoard/" + rendingInfo.getTradeImage());
		}
		
		return list;
	}

	@Override
	public String selectSenderNickName(String userId) {
		
		String nickName = dao.selectSenderNickName(userId);
		
		return nickName;
	}

	@Override
	public int selectFalseMessage(String userId) {
		
		int result = 0;
		
		String receivedMessage = dao.selectReceiverdMessage(userId);
		
		if (receivedMessage != null) {
			result = dao.selectFalseMessage(userId);
		}
		
		
		return result;
	}
	
	

}
