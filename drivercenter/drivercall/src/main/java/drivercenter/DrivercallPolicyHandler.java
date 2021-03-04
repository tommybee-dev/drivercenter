package drivercenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import drivercenter.config.kafka.KafkaProcessor;

@Service
public class DrivercallPolicyHandler {
	@Autowired
	DrivercallRepository drivercallRepository;

	@StreamListener(KafkaProcessor.INPUT)
	public void onStringEventListener(@Payload String eventString) {

	}

	@StreamListener(KafkaProcessor.INPUT)
	public void wheneverDriverassignCompleted_(@Payload DriverassignCompleted driverassignCompleted) {
		System.out.println("##### EVT TYPE[대리기사할당확인됨]  : " + driverassignCompleted.getEventType());
		if (driverassignCompleted.isMe() && driverassignCompleted.getTel() != null) {

//           try {
//               // 원래 데이터가 트랜잭션 커밋되기도 전에 이벤트가 너무 빨리 도달하는 경우를 막기 위함
//               Thread.currentThread().sleep(3000); //  no good. --> pay 가 TX 를 마친 후에만 실행되도록 수정함
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
			System.out.println("##### listener[할당확인됨]  : " + driverassignCompleted.toJson());
			

			// Correlation id 는 '고객휴대폰번호' 임
			if(driverassignCompleted.getId() != null)
				drivercallRepository.findById(Long.valueOf(driverassignCompleted.getId())).ifPresent((drivercall) -> {
					drivercall.setStatus("호출확정");
					drivercallRepository.save(drivercall);
				});
//			택시호출Repository.findBy휴대폰번호(할당확인됨.get고객휴대폰번호()).ifPresent((택시호출) -> {
//				System.out.println("할당확인됨 = " + 할당확인됨.get고객휴대폰번호());
//				택시호출.set호출상태("호출확정");
//				택시호출Repository.save(택시호출);
//			});
		}

//		if (할당확인됨.isMe()) {
//			택시호출 호출 = new 택시호출();
//			호출.set호출상태(할당확인됨.get할당상태());
//			택시호출Repository.save(호출);
//
//			System.out.println("##### listener[할당확인됨]  : " + 할당확인됨.toJson());
//		}
	}

	@StreamListener(KafkaProcessor.INPUT)
	public void wheneverDriverassignCancelled_(@Payload DriverassignCancelled driverassignCancelled) {
		System.out.println("##### EVT TYPE[할당취소됨]  : " + driverassignCancelled.getEventType());
		if (driverassignCancelled.isMe()) {
			System.out.println("##### listener[할당취소됨]  : " + driverassignCancelled.toJson());
			drivercallRepository.findById(Long.valueOf(driverassignCancelled.getId())).ifPresent((drivercall) -> {
				drivercall.setStatus("호출취소");
				drivercallRepository.save(drivercall);
			});
		}
	}

}
