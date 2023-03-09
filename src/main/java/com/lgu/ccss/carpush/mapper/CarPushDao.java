package com.lgu.ccss.carpush.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.carpush.model.ConnDeviceVO;

@Component
public class CarPushDao {
	private final Logger logger = LoggerFactory.getLogger(CarPushDao.class);
	
	@Value("#{config['server.id']}")
	private String svrId;
	@Value("#{config['catPush.systemId']}")
	private String systemId;
	
	
	
	@Autowired
	private CarPushMapper carPushMapper;

	public boolean carPushUpdateStatus(ConnDeviceVO connDeviceVo) {
//		ConnDeviceVO connDeviceVo = new ConnDeviceVO();
		
		connDeviceVo.setUpdId(systemId);
		
		logger.debug("### update Data : "+connDeviceVo.toString());
		int resultStatus = carPushMapper.carPushUpdateStatus(connDeviceVo);
		logger.debug("result : "+resultStatus);
		if(resultStatus<1) {
			// 실패시 처리
			logger.debug("Status update Fail");
			return false;
		}
		int resultSessionUpdate = carPushMapper.pushSessUpdateStatus(connDeviceVo);
		if(resultSessionUpdate<1) {
			// 실패시 처리
			logger.debug("Session update Fail");
			return false;
		}
		
		return true;
	}	
}
