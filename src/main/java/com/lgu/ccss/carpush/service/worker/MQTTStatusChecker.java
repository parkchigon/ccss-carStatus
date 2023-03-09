package com.lgu.ccss.carpush.service.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQTTStatusChecker {
	
	@Autowired
	MQTTConnection mqttConnection;
	
	public void startWorker() {
		// TODO Auto-generated method stub
		mqttConnection.connectionMqtt();
		mqttConnection.setSubscribe();
	}
	
	public boolean connectionCheck() {
		return mqttConnection.connectionStatus();
	}
}
