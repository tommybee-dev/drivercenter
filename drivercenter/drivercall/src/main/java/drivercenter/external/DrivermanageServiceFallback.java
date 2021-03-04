package drivercenter.external;

import org.springframework.stereotype.Component;

@Component
public class DrivermanageServiceFallback implements DrivermanageService {
		
	@Override
	public void reqDriverassign(Drivermanage drivermanage) {
		System.out.println("Circuit breaker has been opened. Fallback returned instead. " + drivermanage.getId());
	}

}
