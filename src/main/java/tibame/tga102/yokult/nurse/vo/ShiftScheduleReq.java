package tibame.tga102.yokult.nurse.vo;

import java.util.List;

public class ShiftScheduleReq {
//畫面呈現
	private String staffId;
	private List<String> eventId;	
	private String annual;
	private String personal;
	private String official;
	
	public String getAnnual() {
		return annual;
	}

	public void setAnnual(String annual) {
		this.annual = annual;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getOfficial() {
		return official;
	}

	public void setOfficial(String official) {
		this.official = official;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public List<String> getEventId() {
		return eventId;
	}

	public void setEventId(List<String> eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "ShiftScheduleReq [staffId=" + staffId + ", eventId=" + eventId + ", annual=" + annual + ", personal="
				+ personal + ", official=" + official + "]";
	}

	

}
