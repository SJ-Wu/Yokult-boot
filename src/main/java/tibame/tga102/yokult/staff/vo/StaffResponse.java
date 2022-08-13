package tibame.tga102.yokult.staff.vo;

import org.springframework.stereotype.Component;

@Component
public class StaffResponse {
	private String msg;
	private Staff staff;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	@Override
	public String toString() {
		return "StaffResponse [msg=" + msg + ", staff=" + staff + "]";
	}
	
}
