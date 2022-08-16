package tibame.tga102.yokult.nurse.vo;

import java.util.Date;

public class ResultSchedule {
//畫面呈現欄位
	private Date schedule_date;
	private String workStaff;
	private String dayOffstaff1;
	private String dayOffstaff2;
	private String apm;

	public Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Date schedule_date) {
		this.schedule_date = schedule_date;
	}

	public String getWorkStaff() {
		return workStaff;
	}

	public void setWorkStaff(String workStaff) {
		this.workStaff = workStaff;
	}

	public String getDayOffstaff1() {
		return dayOffstaff1;
	}

	public void setDayOffstaff1(String dayOffstaff1) {
		this.dayOffstaff1 = dayOffstaff1;
	}

	public String getDayOffstaff2() {
		return dayOffstaff2;
	}

	public void setDayOffstaff2(String dayOffstaff2) {
		this.dayOffstaff2 = dayOffstaff2;
	}

	public String getApm() {
		return apm;
	}

	public void setApm(String apm) {
		this.apm = apm;
	}

	@Override
	public String toString() {
		return "ResultSchedule [schedule_date=" + schedule_date + ", workStaff=" + workStaff + ", dayOffstaff1="
				+ dayOffstaff1 + ", dayOffstaff2=" + dayOffstaff2 + ", apm=" + apm + "]";
	}

}
