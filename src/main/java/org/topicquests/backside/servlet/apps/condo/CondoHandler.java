/*
 * Copyright 2015, TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.topicquests.backside.servlet.apps.condo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.topicquests.backside.servlet.ServletEnvironment;
import org.topicquests.backside.servlet.api.ICredentialsMicroformat;
import org.topicquests.backside.servlet.api.IErrorMessages;
import org.topicquests.backside.servlet.apps.BaseHandler;
import org.topicquests.backside.servlet.apps.admin.api.IAdminMicroformat;
import org.topicquests.backside.servlet.apps.condo.api.ICondoAdminModel;
import org.topicquests.backside.servlet.apps.tm.api.IStructuredConversationModel;
import org.topicquests.backside.servlet.apps.tm.api.ITopicMapMicroformat;
import org.topicquests.backside.servlet.apps.tm.api.ITopicMapModel;
import org.topicquests.backside.servlet.apps.usr.api.IUserMicroformat;
import org.topicquests.support.api.IResult;
import org.topicquests.ks.api.ITQCoreOntology;
import org.topicquests.ks.api.ITicket;
import org.topicquests.ks.tm.api.ISubjectProxy;

/**
 * @author park
 *
 */
public class CondoHandler  extends BaseHandler {
	private static CondoHandler instance;
	private ICondoAdminModel model;
	
	/**
	 * 
	 */
	public CondoHandler(ServletEnvironment env, String basePath) {
		super(env,basePath);
		//model = new TopicMapModel(environment);
		instance = this;
	}
	
	/**
	 * Handler is shared
	 * @return
	 */
	public static CondoHandler getInstance() {
		return instance;
	}

	public void handleGet(HttpServletRequest request, HttpServletResponse response, ITicket credentials, JSONObject jsonObject) throws ServletException, IOException {
		JSONObject returnMessage = newJSONObject();
		String message = "", rtoken="";
		String verb = getVerb(jsonObject);
		int code = 0;
		IResult r;
		System.out.println("CondoHandler.handleGet "+verb);
		if (verb.equals(IUserMicroformat.LIST_USERS)) {
			//
		}  else {
			String x = IErrorMessages.BAD_VERB+"-UserServletGet-"+verb;
			environment.logError(x, null);
			throw new ServletException(x);
		}
		returnMessage.put(ICredentialsMicroformat.RESP_TOKEN, rtoken);
		returnMessage.put(ICredentialsMicroformat.RESP_MESSAGE, message);
		super.sendJSON(returnMessage.toJSONString(), code, response);
		returnMessage = null;
	}
	
	
	public void handlePost(HttpServletRequest request, HttpServletResponse response, ITicket credentials, JSONObject jsonObject) throws ServletException, IOException {
		JSONObject returnMessage = newJSONObject();
		JSONObject cargo = (JSONObject)jsonObject.get(ICredentialsMicroformat.CARGO);
		String message = "", rtoken="";
		String verb = getVerb(jsonObject);
		int code = 0;
		IResult r;
		System.out.println("CondoHandler.handlePost "+verb);
		if (verb.equals(ITopicMapMicroformat.PUT_TOPIC)) {
			//
		} else {
			String x = IErrorMessages.BAD_VERB+"-AdminServletPost-"+verb;
			environment.logError(x, null);
			throw new ServletException(x);
		}
		returnMessage.put(ICredentialsMicroformat.RESP_TOKEN, rtoken);
		returnMessage.put(ICredentialsMicroformat.RESP_MESSAGE, message);
		super.sendJSON(returnMessage.toJSONString(), code, response);
		returnMessage = null;	}
	
	public void shutDown() {
		//model.shutDown();
	}

}
