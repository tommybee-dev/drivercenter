package drivercenter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="Driverassign_table")
public class Driverassign {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status; //호출,호출중,호출확정,호출취소
    private String driverid;
    private String driver;
    private String drivertel;
    
    @PrePersist
    public void onPrePersist(){
    	System.out.println("==============대리기사할당================");


        //할당확인됨 할당확인됨 = new 할당확인됨();
        //BeanUtils.copyProperties(this, 할당확인됨);
        //할당확인됨.publishAfterCommit();


        //할당취소됨 할당취소됨 = new 할당취소됨();
        //BeanUtils.copyProperties(this, 할당취소됨);
        //할당취소됨.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDriverid() {
		return driverid;
	}


	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getDrivertel() {
		return drivertel;
	}


	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}



}
