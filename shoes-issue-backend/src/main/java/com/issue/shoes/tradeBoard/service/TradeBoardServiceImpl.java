package com.issue.shoes.tradeBoard.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.dao.TradeBoardDao;
import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;

@Service
public class TradeBoardServiceImpl implements TradeBoardService{

	@Autowired
	private TradeBoardDao dao;
	
	private final PlatformTransactionManager transactionManager;
	
	public TradeBoardServiceImpl(PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}
	
	@Override
	public List<TradeBoard> searchAllTradeBoard() {
		
		List<TradeBoard> list = dao.searchAllTradeBoard();
		
		for (TradeBoard tradeBoard : list) {
			tradeBoard.setTradeImage(
				"../../../images/tradeBoard/" + tradeBoard.getTradeImage());
		}
		
		return list;
	}

	@Override
	public List<TradeBoard> selectTradeTitle(String keyword) {
		
		List<TradeBoard> list = dao.selectTradeTitle(keyword);
		
		return list;
	}

	@Override
	public List<TradeBoard> insertTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile) {
		
		UUID boardUuid = UUID.randomUUID();
		tradeBoard.setTradeId(boardUuid.toString());
		tradeBoard.setTradeStatus("예약 가능");
		tradeBoard.setTradeLike(0);
		tradeBoard.setTradePrice(0);
		//작성자 아이디
		tradeBoard.setUserId("user2");
		
		String uploadFileName = "";
		
		
		String insertString = uploadFile[0].getOriginalFilename();
		uploadFileName = insertString.substring(uploadFileName.lastIndexOf("\\")+1);
		
		UUID imgUuid = UUID.randomUUID();
		uploadFileName = imgUuid.toString() + "_" + uploadFileName;
		
		tradeBoard.setTradeImage(uploadFileName);
		
		TransactionStatus txStatus =
		           transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		int result = 0;
		List<TradeBoard> list = null;
		
		try {
			result = dao.insertTradeBoard(tradeBoard);
			
			boolean success = false;
			if (result == 1) {
				success = uploadImg(uploadFile, imgUuid);
			}else {
				transactionManager.rollback(txStatus);
			}
			
			if (success) {
				list = searchAllTradeBoard();
				transactionManager.commit(txStatus);
			}else {
				transactionManager.rollback(txStatus);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			transactionManager.rollback(txStatus);
		}

		return list;
	}
	
	private boolean uploadImg(MultipartFile[] uploadFile, UUID imgUuid) {
		
		boolean success = true;
		
		String uploadFolder = "C:/Users/KOSA/git/shose-issue-frontend/images/tradeBoard";
		
		File uploadPath = new File(uploadFolder);
		
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		String uploadFileName = uploadFile[0].getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
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
		
		TransactionStatus txStatus =
		           transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		TradeBoardDetail boardDetail = null;
		try {
			boardDetail = dao.selectTradeBoardDetail(tradeId);
			TradeBoardDetail user = dao.selecTradeBoardUser(userId);
			boardDetail.setNickName(user.getNickName());
			boardDetail.setPoint(user.getPoint());
			boardDetail.setTradeImage("../../../images/tradeBoard/"+boardDetail.getTradeImage());
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
		}
		
		return boardDetail;
	}
	
	
	
}
