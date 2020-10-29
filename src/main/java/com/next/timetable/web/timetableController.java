package com.next.timetable.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timetable")
public class timetableController {  
	@RequestMapping(value = "/timetableView.edu")
	public String timetableView() throws Exception {
		
		return "timetable/timetableView";
	}
}


















