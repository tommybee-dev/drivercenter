package drivercenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import drivercenter.config.kafka.KafkaProcessor;

@Service
public class DrivermanagePolicyHandler {
	@Autowired
	DrivermanageRepository drivermanageRepository;

	@StreamListener(KafkaProcessor.INPUT)
	public void onStringEventListener(@Payload String eventString) {

	}

	@StreamListener(KafkaProcessor.INPUT)
	public void wheneverDrivercallCancelled_(@Payload DrivercallCancelled drivercallCancelled) {
		System.out.println("##### EVT TYPE[호출취소됨]  : " + drivercallCancelled.getEventType());
		if (drivercallCancelled.isMe()) {
			System.out.println("##### listener  : " + drivercallCancelled.toJson());

			if (drivercallCancelled.getId() != null)
				// Correlation id 는 '고객휴대폰번호' 임
				drivermanageRepository.findById(Long.valueOf(drivercallCancelled.getId())).ifPresent((택시관리) -> {
					택시관리.setStatus("대리기사호출취소");
					drivermanageRepository.save(택시관리);
				});
		}
	}

	@StreamListener(KafkaProcessor.INPUT)
	public void wheneverDrivermanageAssigned_(@Payload DrivermanageAssigned drivermanageAssigned) {
		System.out.println("##### EVT TYPE[택시할당요청됨]  : " + drivermanageAssigned.getEventType());
		if (drivermanageAssigned.isMe()) {
			System.out.println("##### listener[할당확인됨]  : " + drivermanageAssigned.toJson());

			if (drivermanageAssigned.getId() != null)
				// Correlation id 는 '고객휴대폰번호' 임
				drivermanageRepository.findById(Long.valueOf(drivermanageAssigned.getId())).ifPresent((txManage) -> {
					txManage.setStatus(drivermanageAssigned.getStatus());
					drivermanageRepository.save(txManage);
				});

//        	택시관리Repository.findBy고객휴대폰번호(택시할당요청됨.get고객휴대폰번호()).ifPresent((택시관리) -> {
//				System.out.println("택시할당요청됨 = " + 택시관리.get고객휴대폰번호());
//				택시관리.set호출상태(택시할당요청됨.get호출상태());
//				택시관리Repository.save(택시관리);
//			});
//            택시관리 관리 = new 택시관리();
//            관리.set호출상태(할당확인됨.get호출상태());
//            관리.set택시기사이름(할당확인됨.get택시기사이름());
//            관리.set택시기사전화번호(할당확인됨.get택시기사전화번호());
//            관리.set택시번호(할당확인됨.get택시번호());
//            택시관리Repository.save(관리);
		}
	}

//    @StreamListener(KafkaProcessor.INPUT)
//    public void whenever택시할당확인됨_(@Payload 할당확인됨 할당확인됨){
//    	System.out.println("##### EVT TYPE[할당확인됨]  : " + 할당확인됨.getEventType());
//        if(할당확인됨.isMe()){
//            System.out.println("##### listener  : " + 할당확인됨.toJson());
//            택시관리 관리 = new 택시관리();
//            관리.set호출상태(할당확인됨.get할당상태());
//            관리.set택시기사이름(할당확인됨.get택시기사이름());
//            관리.set택시기사전화번호(할당확인됨.get택시기사전화번호());
//            관리.set택시번호(할당확인됨.get택시번호());
//            택시관리Repository.save(관리);
//        }
//    }

}
