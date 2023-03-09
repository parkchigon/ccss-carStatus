package com.lgu.ccss.carpush.service.worker;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.carpush.constant.CarPushMqttConst;
import com.lgu.ccss.common.util.SslUtil;

@Component
public class MQTTConnection implements MqttCallback{
	
	private final Logger logger = LoggerFactory.getLogger(MQTTConnection.class);
	
//	MqttConnectionVO mqttConnVo;
	private MqttAsyncClient client;
	private MqttConnectOptions conOpt;
	
	@Value("#{config['carPush.mqtt.id']}")
	private String mqttId;
	@Value("#{config['carPush.mqtt.pw']}")
	private String mqttPw;
	@Value("#{config['carPush.mqtt.url']}")
	private String mqttUrl;
	@Value("#{config['carPush.mqtt.timeout']}")
	private int timeOut;
	@Value("#{config['carPush.mqtt.crtFilePath']}")
	private String crtFilePath;
	
	@Autowired
	MQTTMessageCtrl mqttMessageCtl;

	public boolean connectionStatus() {
		if(client == null) {
			return false;
		}else {
			return client.isConnected();
		}
	}
	
	public boolean connectionMqtt() {
		logger.debug("MQTT ID : "+mqttId);
		conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);
		
		if(mqttPw != null ) {
			conOpt.setPassword(mqttPw.toCharArray());
    	}
    	if(mqttId != null) {
    		conOpt.setUserName(mqttId);
    	}
    	conOpt.setKeepAliveInterval(0);
    	
    	try {
			conOpt.setSocketFactory(SslUtil.getSocketFactory(crtFilePath, null, null, null));	
			client = new MqttAsyncClient(mqttUrl,mqttId, new MemoryPersistence());
			
			IMqttToken mqttToken;
			mqttToken = client.connect(conOpt);
			mqttToken.waitForCompletion(timeOut);

			client.setCallback(this);
			logger.debug("### ConnectMqtt :"+Thread.currentThread().getName());

			return true;
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("connectionMqtt [ Exception ]",e);
			return false;
		}	
	}
	
	public void setSubscribe() {
		try {
			client.subscribe(CarPushMqttConst.MQTT_SUB_CLIENT_STATUS_CHK, 1);
//			client.subscribe(CarPushMqttConst.MQTT_SUB_CLIENT_STATUS_CHK+"01022330811", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR [setSubscribe]",e);
		}
		logger.info("### Complete Subscribe! : "+CarPushMqttConst.MQTT_SUB_CLIENT_STATUS_CHK);
	}
	
	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		logger.error("ConnectionLost!", cause);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		// TODO Auto-generated method stub
		logger.info("MessageArrived Process START!!");
		String statusJson = new String(message.getPayload());
		logger.info("JSON ### "+statusJson);
		mqttMessageCtl.processStatusCheck(topic, statusJson);
		logger.info("MessageArrived Process END!!");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
