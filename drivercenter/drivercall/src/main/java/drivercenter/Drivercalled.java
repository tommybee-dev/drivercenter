package drivercenter;

public class Drivercalled extends AbstractEvent {

    private Long id;

    public Drivercalled(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}