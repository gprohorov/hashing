package edu.chaos.hashing.controller.web;
/*
@author   george
@project   hashing
@class WEBController
@version  1.0.0
@since 31.01.2021 - 17.31
*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WEBController {
	@GetMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}

	@GetMapping("/random")
	public ModelAndView random(){
		return new ModelAndView("random");
	}

	@GetMapping("/avalanche")
	public ModelAndView avalanche(){
		return new ModelAndView("avalanche");
	}
}
