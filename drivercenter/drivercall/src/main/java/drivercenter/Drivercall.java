package drivercenter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import drivercenter.external.Drivermanage;
import drivercenter.external.DrivermanageService;

@Entity
@Table(name="Drivercall_table")
public class Drivercall {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String tel;
    private String location;
    private String status; //호출,호출중,호출확정,호출취소
    private Integer cost;
    
	
    @PostPersist
    public void onPostPersist(){
//        택시호출요청됨 택시호출요청됨 = new 택시호출요청됨();
//        BeanUtils.copyProperties(this, 택시호출요청됨);
//        택시호출요청됨.publishAfterCommit();
    	
    	System.out.println("휴대폰번호 " + getTel());
        System.out.println("호출위치 " + getLocation());
        System.out.println("호출상태 " + getStatus());
        System.out.println("예상요금 " + getCost());
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.   	
    	if(getTel() != null)
		{
    		System.out.println("SEND###############################" + getId());
			Drivermanage drivermanage = new Drivermanage();
			drivermanage.setId(getId());
			drivermanage.setOrderId(String.valueOf(getId()));
			drivermanage.setTel(getTel());
	        if(getLocation()!=null) 
	        	drivermanage.setLocation(getLocation());
	        if(getStatus()!=null) 
	        	drivermanage.setStatus(getStatus());
	        if(getCost()!=null) 
	        	drivermanage.setCost(getCost());
	        
	        // mappings goes here
	        TaxicallApplication.applicationContext.getBean(DrivermanageService.class)
	        	.reqDriverassign(drivermanage);
		}

    }

	@PreRemove
	public void onPreRemove(){
		DrivercallCancelled drivercallCancelled = new DrivercallCancelled();
		BeanUtils.copyProperties(this, drivercallCancelled);
		drivercallCancelled.publishAfterCommit();

		//Following code causes dependency to external APIs
		// it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

		//택시관리 택시관리 = new 택시관리();
		// mappings goes here
		//택시관리.setId(getId());
		//택시관리.setOrderId(String.valueOf(getId()));
		//택시관리.set호출상태("호출취소");
		//택시관리.set고객휴대폰번호(get휴대폰번호());
		
		// mappings goes here
		//TaxicallApplication.applicationContext.getBean(택시관리Service.class).택시할당요청(택시관리);
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}


}
