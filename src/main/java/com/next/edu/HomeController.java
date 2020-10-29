
package com.next.edu;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.next.sitenotice.service.ISitenoticeService;
import com.next.sitenotice.vo.SitenoticeVO;
import com.next.sitequestion.vo.SitequestionSearchVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	ISitenoticeService sitenoticeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,  SitequestionSearchVO sitequestionSearchVO) {
		List<SitenoticeVO> sitenoticeVOList = sitenoticeService.getSitenoticeVOList(sitequestionSearchVO);
		model.addAttribute("sitenoticeVOList", sitenoticeVOList);
		System.out.println("aaa");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
