package tibame.tga102.yokult.checkout.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.checkout.service.CheckoutService;
import tibame.tga102.yokult.checkout.vo.Checkout;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path=YokultConstants.CHECKOUT_API)
public class CheckoutController {

	@Autowired
	CheckoutService service;
	
	@PostMapping
	public Map<String, String> checkout(@RequestBody Checkout checkout) {
		Map<String, String> respObject = new HashMap<String, String>();
		System.out.println("[controller checkout]" + checkout);
		respObject.put("msg", service.processCheckout(checkout));
		return respObject;
	}
}
