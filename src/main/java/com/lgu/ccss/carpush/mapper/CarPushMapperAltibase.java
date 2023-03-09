package com.lgu.ccss.carpush.mapper;

import com.lgu.ccss.carpush.model.ConnDeviceVO;
import com.lgu.ccss.config.annontation.Slave;

@Slave
public interface CarPushMapperAltibase {

	int pushSessUpdateStatus(ConnDeviceVO connDeviceVo);
}
