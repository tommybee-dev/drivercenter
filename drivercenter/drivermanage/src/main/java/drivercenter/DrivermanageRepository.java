package drivercenter;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrivermanageRepository extends PagingAndSortingRepository<Drivermanage, Long>{

	//Optional<Drivermanage> findBy고객휴대폰번호(String get고객휴대폰번호);


}