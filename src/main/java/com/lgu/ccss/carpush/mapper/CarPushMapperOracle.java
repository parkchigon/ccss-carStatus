package com.lgu.ccss.carpush.mapper;

import com.lgu.ccss.carpush.model.ConnDeviceVO;
import com.lgu.ccss.config.annontation.Master;

@Master
public interface CarPushMapperOracle {
	int carPushUpdateStatus(ConnDeviceVO connDeviceVo);
}
