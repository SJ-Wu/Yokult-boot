package tibame.tga102.yokult.nurse.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Schedule {
//db table
	@Id
	@Column(columnDefinition = "DATETIME")
	private Date schedule_date;
	private String morning_shift;
	private String night_shift;
	private String day_off1;
	private String day_off2;

	public Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Date schedule_date) {
		this.schedule_date = schedule_date;
	}

	public String getMorning_shift() {
		return morning_shift;
	}

	public void setMorning_shift(String morning_shift) {
		this.morning_shift = morning_shift;
	}

	public String getNight_shift() {
		return night_shift;
	}

	public void setNight_shift(String night_shift) {
		this.night_shift = night_shift;
	}

	public String getDay_off1() {
		return day_off1;
	}

	public void setDay_off1(String day_off1) {
		this.day_off1 = day_off1;
	}

	public String getDay_off2() {
		return day_off2;
	}

	public void setDay_off2(String day_off2) {
		this.day_off2 = day_off2;
	}

	@Override
	public String toString() {
		return "Schedule [schedule_date=" + schedule_date + ", morning_shift=" + morning_shift + ", night_shift="
				+ night_shift + ", day_off1=" + day_off1 + ", day_off2=" + day_off2 + "]";
	}

}
