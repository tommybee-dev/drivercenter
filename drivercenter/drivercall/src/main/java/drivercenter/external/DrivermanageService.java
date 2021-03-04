
package drivercenter.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="drivermanage", url="http://localhost:8082")
@FeignClient(name="drivermanage", url="http://localhost:8082", fallback = DrivermanageServiceFallback.class)
public interface DrivermanageService {

    @RequestMapping(method= RequestMethod.POST, path="/drivermanages")
    public void reqDriverassign(@RequestBody Drivermanage drivermanage);

}