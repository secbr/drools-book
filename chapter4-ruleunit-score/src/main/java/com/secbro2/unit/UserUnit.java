package com.secbro2.unit;

import com.secbro2.entity.User;
import com.secbro2.service.BlackListService;
import com.secbro2.service.MessageService;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

/**
 * @author sec
 * @version 1.0
 * @date 2023/3/29
 **/
public class UserUnit implements RuleUnitData {

	private final DataStore<User> users;

	private BlackListService blackListService;

	private MessageService messageService;

	public UserUnit() {
		this(DataSource.createStore());
	}

	public UserUnit(DataStore<User> users) {
		this.users = users;
	}

	public DataStore<User> getUsers() {
		return users;
	}

	public BlackListService getBlackListService() {
		return blackListService;
	}


	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setBlackListService(BlackListService blackListService) {
		this.blackListService = blackListService;
	}
}
