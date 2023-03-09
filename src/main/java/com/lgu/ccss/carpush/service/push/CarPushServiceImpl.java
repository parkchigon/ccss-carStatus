package com.lgu.ccss.carpush.service.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgu.ccss.carpush.mapper.CarPushDao;
import com.lgu.ccss.carpush.service.worker.MQTTStatusChecker;

@Service
public class CarPushServiceImpl implements CarPushService{
	private final Logger logger = LoggerFactory.getLogger(CarPushServiceImpl.class);

	@Autowired
	CarPushDao carPushDao;
	@Autowired
	MQTTStatusChecker mqttStatusCheck;

	@Override
	public void doTask() throws Exception {
		// TODO Auto-generated method stub
		logger.info("CarPushService doTask!");

		if(!mqttStatusCheck.connectionCheck()) {
			logger.info("MQTT Start!");
			mqttStatusCheck.startWorker();
		}else {
			logger.info("MQTT Listener is Running~!");
		}		
		
	}
}
