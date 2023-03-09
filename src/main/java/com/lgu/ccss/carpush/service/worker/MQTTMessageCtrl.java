package com.lgu.ccss.carpush.service.worker;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.carpush.constant.CarPushMqttConst;
import com.lgu.ccss.carpush.mapper.CarPushDao;
import com.lgu.ccss.carpush.model.ConnDeviceVO;
import com.lgu.common.util.DateUtils;

@Component
public class MQTTMessageCtrl {
	private final Logger logger = LoggerFactory.getLogger(MQTTMessageCtrl.class);
	
	@Autowired
	CarPushDao carPushDao;
	
	public void processStatusCheck(String topic,String message) {

		logger.info("Device Topic : "+topic +" | Status : "+message);

		int topicSize = CarPushMqttConst.MQTT_SUB_CLIENT_STATUS_CHK.length()-1;
		String ctn = topic.substring(topicSize);
		logger.info("Device CTN : "+ctn);
		logger.info("Device Message : "+message);
		JSONObject jsonStatus = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			jsonStatus = (JSONObject) parser.parse(message);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ParseException",e);
			return;
		}
		String status = (String) jsonStatus.get(CarPushMqttConst.MQTT_PAYLOAD_KEY_STATUS);
		Long timeStr = Long.parseLong((String) jsonStatus.get(CarPushMqttConst.MQTT_PAYLOAD_KEY_TIMESTAMP));
		
		logger.debug("status : "+status);
		logger.debug("timeStr : "+timeStr);
		Date date = new Date(timeStr*1000L);
		logger.debug("Date : "+date);
		boolean expire = false;
		try {
			expire = DateUtils.isExpireTime(date,CarPushMqttConst.MQTT_NUMBER_ONE);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ParseException",e);
		}
		
		logger.debug("expire : "+expire);
		if(!expire) {
			ConnDeviceVO connDeviceVo = new ConnDeviceVO();
			connDeviceVo.setDeviceCtn(ctn);
			if(status.equals(CarPushMqttConst.MQTT_NUMBER_ZERO)) {
				connDeviceVo.setDevicePushConnStatus(CarPushMqttConst.MQTT_BOOL_NO);
			}else if(status.equals(CarPushMqttConst.MQTT_NUMBER_ONE)){
				connDeviceVo.setDevicePushConnStatus(CarPushMqttConst.MQTT_BOOL_YES);
			}else {
				return;
			}
			carPushDao.carPushUpdateStatus(connDeviceVo);
		}
	}
}
