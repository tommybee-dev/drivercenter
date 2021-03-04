
package drivercenter;

public class DrivercallCancelled extends AbstractEvent {

    private Long id;
    private String assignstatus; //호출취소
    private String tel;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
