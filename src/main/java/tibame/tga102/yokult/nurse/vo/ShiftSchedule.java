package tibame.tga102.yokult.nurse.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "shift_schedule")
@Entity
public class ShiftSchedule {
//原生
	@Id
	@Column(name = "row_num")
	private Integer rowNum;
	@Column(name = "schedule_date", columnDefinition = "DATETIME")
	private Date scheduleDate;
	@Column(name = "staff_id")
	private String staffId;
	@Column(name = "shiftschedule_type_of_leave")
	private String shiftScheduleTypeOfLeave;
	@Column(name = "shiftschedule_morningshift_nightshift")
	private String shiftScheduleMorningshiftNightshift;

	public Integer getRow() {
		return rowNum;
	}

	public void setRow(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getShiftScheduleTypeOfLeave() {
		return shiftScheduleTypeOfLeave;
	}

	public void setShiftScheduleTypeOfLeave(String shiftScheduleTypeOfLeave) {
		this.shiftScheduleTypeOfLeave = shiftScheduleTypeOfLeave;
	}

	public String getShiftScheduleMorningshiftNightshift() {
		return shiftScheduleMorningshiftNightshift;
	}

	public void setShiftScheduleMorningshiftNightshift(String shiftScheduleMorningshiftNightshift) {
		this.shiftScheduleMorningshiftNightshift = shiftScheduleMorningshiftNightshift;
	}

	@Override
	public String toString() {
		return "ShiftSchedule [row=" + rowNum + ", scheduleDate=" + scheduleDate + ", staffId=" + staffId
				+ ", shiftScheduleTypeOfLeave=" + shiftScheduleTypeOfLeave + ", shiftScheduleMorningshiftNightshift="
				+ shiftScheduleMorningshiftNightshift + "]";
	}

}
