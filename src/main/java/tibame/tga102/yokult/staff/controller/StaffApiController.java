package tibame.tga102.yokult.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.staff.service.StaffService;
import tibame.tga102.yokult.staff.vo.Staff;
import tibame.tga102.yokult.staff.vo.StaffResponse;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path = { YokultConstants.STAFF_API })
public class StaffApiController {
	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffResponse staffResponse;

	@PostMapping(path = { "/login" })
	public ResponseEntity<?> login(@RequestBody Staff staff) {
		String jwtToken = staffService.login(staff);
		staffResponse.setMsg(jwtToken);
		if (jwtToken != null) {
			// 201 (Created)
			ResponseEntity<StaffResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(staffResponse);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}
}
