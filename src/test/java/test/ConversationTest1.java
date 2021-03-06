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
package test;

import org.topicquests.backside.servlet.ServletEnvironment;
import org.topicquests.backside.servlet.apps.tm.api.IStructuredConversationModel;
import org.topicquests.backside.servlet.api.ISecurity;
import org.topicquests.backside.servlet.apps.usr.api.IUserModel;
import org.topicquests.support.api.IResult;
import org.topicquests.ks.TicketPojo;
import org.topicquests.ks.api.ITQCoreOntology;
import org.topicquests.ks.api.ITQDataProvider;
import org.topicquests.ks.api.ITicket;
import org.topicquests.ks.tm.api.INodeTypes;
import org.topicquests.ks.tm.api.ISubjectProxy;
import org.topicquests.ks.tm.api.ISubjectProxyModel;

/**
 * @author jackpark
 *
 */
public class ConversationTest1 {
	private ServletEnvironment environment;
	private IUserModel model;
	private ITQDataProvider topicMap;
	private IStructuredConversationModel conversationModel;
	private ITicket credentials;
	private final String
		MAP_LOC			= "MyFirstMap",
		QUESTION_LOC	= "MyFirstQuestion",
		ANSWER_LOC		= "MyFirstAnswer";

	/**
	 *
	 */
	public ConversationTest1(ServletEnvironment env) {
		environment = env;
		model = environment.getUserModel();
		IResult r = model.insertUser("joe@example.com", "joe", "b921a18c-5677-4ea6-b076-f6f11dec3e9f", "joe!", "Joe Joe", "", ISecurity.USER_ROLE, "", "", true);
		topicMap = environment.getTopicMapEnvironment().getDatabase();
		conversationModel = environment.getConversationModel();
		credentials = new TicketPojo(ITQCoreOntology.SYSTEM_USER);
		runTest();
	}

	/**
	 * <p>Steps:<br/>
	 * <li>Create a Map node</li>
	 * <li>Create a Question node with map as parent</li>
	 * <li>Createan Answer node with Question as parent</li>
	 * </p>
	 */
	private void runTest() {
		IResult r = conversationModel.newConversationNode(INodeTypes.CONVERSATION_MAP_TYPE, null, null,
				MAP_LOC, "My first map", null, "en", "", "b921a18c-5677-4ea6-b076-f6f11dec3e9f", false);
		ISubjectProxy sp = (ISubjectProxy)r.getResultObject();
		System.out.println("AAA "+r.getErrorString()+" | "+sp.toJSONString());
		if (r.hasError()) System.exit(1);
		r = conversationModel.newConversationNode(INodeTypes.ISSUE_TYPE, MAP_LOC, MAP_LOC,
				QUESTION_LOC, "Why is the sky blue?", null, "en", "", "b921a18c-5677-4ea6-b076-f6f11dec3e9f", false);
		sp = (ISubjectProxy)r.getResultObject();
		System.out.println("BBB"+r.getErrorString()+" | "+sp.toJSONString());
		if (r.hasError()) System.exit(1);
		r = conversationModel.newConversationNode(INodeTypes.POSITION_TYPE, QUESTION_LOC, MAP_LOC,
				ANSWER_LOC, "Nobody really knows.", null, "en", "", "b921a18c-5677-4ea6-b076-f6f11dec3e9f", false);
		sp = (ISubjectProxy)r.getResultObject();
		System.out.println("CCC"+r.getErrorString()+" | "+sp.toJSONString());
		if (r.hasError()) System.exit(1);
	}

}
