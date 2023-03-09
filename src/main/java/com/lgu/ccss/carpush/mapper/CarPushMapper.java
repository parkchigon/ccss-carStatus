package com.lgu.ccss.carpush.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.carpush.model.ConnDeviceVO;

@Component
public class CarPushMapper {
	
	@Autowired
	CarPushMapperOracle carPsuhMapperOracle;
	@Autowired
	CarPushMapperAltibase carPsuhMapperAltibase;

	public int carPushUpdateStatus(ConnDeviceVO connDeviceVo) {
		// TODO Auto-generated method stub
		return carPsuhMapperOracle.carPushUpdateStatus(connDeviceVo);
	}
	public int pushSessUpdateStatus(ConnDeviceVO connDeviceVo) {
		// TODO Auto-generated method stub
		return carPsuhMapperAltibase.pushSessUpdateStatus(connDeviceVo);
	}
	
}
