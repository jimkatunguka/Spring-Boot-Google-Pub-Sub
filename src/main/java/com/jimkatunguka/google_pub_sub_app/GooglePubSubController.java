package com.jimkatunguka.google_pub_sub_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jimkatunguka.google_pub_sub_app.GooglePubSubAppApplication.PubsubOutboundGateway;
import com.jimkatunguka.google_pub_sub_app.GooglePubSubAppApplication.PubsubInboundGateway;

@RestController
public class GooglePubSubController {

	@Autowired
	PubsubOutboundGateway messagePublisherGateway;

	@Autowired
	PubsubInboundGateway messageReaderGateway;

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publishMessage(@RequestBody MyAppGCPMessage message) {
		messagePublisherGateway.sendToPubsub(message.toString());
		return "Message published to Google Pub/Sub successfully";
	}

	//end point not supposed to be called. Just created to trigger the log
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String receiveMessage() {
		messageReaderGateway.receiveMessage();
		return "Message received successfully";
	}
}
