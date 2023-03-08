package com.issue.shoes._transactionSample;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TestController {
	
	@Autowired
	private TestDao dao;
	
	private final PlatformTransactionManager transactionManager;
	
	public TestController(PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		 TransactionStatus txStatus =
		            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			dao.testInsert("sample");
			transactionManager.commit(txStatus);
			System.out.println("commit 성공!");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			System.out.println("rollback 성공!");
		}
		
		return "home";
	}
	
}
