package drivercenter;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrivercallRepository extends PagingAndSortingRepository<Drivercall, Long>{

//	Optional<택시호출> findBy휴대폰번호(String 휴대폰번호);
}