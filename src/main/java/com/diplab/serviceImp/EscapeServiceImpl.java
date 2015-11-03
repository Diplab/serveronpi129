package com.diplab.serviceImp;

import com.diplab.device.RpiElectricLockController;
import com.diplab.service.EscapeService;

public class EscapeServiceImpl implements EscapeService {

	@Override
	public void unlock() {
		RpiElectricLockController.on();
		return;
	}

	@Override
	public void lock() {
		RpiElectricLockController.off();
		return;
	}

}
