package de.fhws.hablame.chatbotbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.fhws.hablame.chatbotbackend.service.chatbot.ChatbotService;

/**
 * {@link RestController} annotated class which handles the webrequests like GET and POST.
 * A controller has {@link Service} annotated classes injected in his context with {@link Autowired}.
 */
@RestController
public class ChatbotController {
	
	public static final String CONVERSATION = "/conversation";
	public static final String STOPCONVERSATION = "/stopconversation";
	
	@Autowired
	private ChatbotService chatbotService;
	
	//TODO: maybe change the bot response to a jsonobject for better parsing options on the client side?
	@RequestMapping(value=CONVERSATION, method=RequestMethod.POST, 
			produces=MediaType.TEXT_PLAIN_VALUE, consumes=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String conversation(@RequestBody String message) {
		return chatbotService.conversation(message).trim();
	}
	
	@RequestMapping(value=STOPCONVERSATION, method=RequestMethod.GET,
			produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String stopConversation() {
		return chatbotService.stopConversation() == true ? "chatbot stopped" : "chatbot could not be stopped";
	}
}
