
package drivercenter;

public class DriverassignCompleted extends AbstractEvent {

    private Long id;
    private String assignstatus; //호출,호출중,호출확정,호출취소
    private String driverid;
    private String driver;
    private String drivertel;
    
    private String tel;
    private String location;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAssignstatus() {
		return assignstatus;
	}

	public void setAssignstatus(String assignstatus) {
		this.assignstatus = assignstatus;
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

}
