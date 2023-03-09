package com.lgu.ccss.carpush.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lgu.ccss.carpush.service.push.CarPushService;

@Service
public class CarPushScheduler {
	private final Logger logger = LoggerFactory.getLogger(CarPushScheduler.class);
	
	@Autowired
	private CarPushService carPushService;

	@Scheduled(fixedRateString  = "${delay.time}")
	public void startWork() {
		try {
			logger.info("###### START CAR PUSH STATUSCHK DAEMON #####");

			carPushService.doTask();

		} catch (Exception e) {
			logger.error("{}", e);

		} finally {
			logger.info("###### END CAR PUSH STATUSCHK DAEMON #####");
		}
	}
}
