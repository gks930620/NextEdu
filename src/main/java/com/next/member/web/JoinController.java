
package com.next.member.web;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;


import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;
import javax.validation.Valid;
import javax.validation.groups.Default;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.next.member.vo.MemberVO;
import com.next.common.util.CookieUtils;
import com.next.common.valid.RegistType;
import com.next.common.vo.ResultMessageVO;
import com.next.member.service.IMemberService;
import com.next.member.service.MailSendService;

@Controller
@RequestMapping("/join")
public class JoinController {
	//CKSdnmse53fq6vYT7lC0aBrdLZZxihmxtVcSgaqjop3WtdbXtucjztpHwhDdlZ5QXY5%2FYzK%2FsMNCktQoHcqZ%2FQ%3D%3D
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	IMemberService memberService; 
	 @Inject
	  private MailSendService mss;
	
	@RequestMapping(value = "/joinInsert.edu")
	public String joinInsertGet(ModelMap modelMap,@ModelAttribute("memberVO")  MemberVO memberVO) throws Exception {
		

		 return "join/joinInsert";
	}

	
	
	@RequestMapping(value = "/joinInsertMessage.edu")
	public String joinInsertMessage(ModelMap modelMap, @ModelAttribute("memberVO") @Validated({Default.class, RegistType.class})MemberVO memberVO, BindingResult bindingResult ) throws Exception {
		int cnt = memberService.idCheck(memberVO);
		if (cnt == 1) {
			return "join/joinInsert";
		}
		if (bindingResult.hasErrors()) {
			return "join/joinInsert";
		}
	
		//memberVO.getMemTli()
		
		
		ResultMessageVO resultMessageVO=new ResultMessageVO();
		try {
			memberService.insertMemberVO(memberVO);
			resultMessageVO.setResult(true).setTitle("가입완료").setMessage("회원가입을 축하합니다").setUrl("/login/login.wow")	.setUrlTitle("로그인");
			modelMap.addAttribute("resultMessageVO",resultMessageVO);
			return "join/joinInsertMessage";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMessageVO.setResult(true).setTitle("회원정보 등록 실패").setMessage("이미 동일한 회원이 존재합니다 다시 시도해주세요").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
			return "join/joinInsertMessage";
		}
	}
	
	
	
	
	@RequestMapping(value="/school.edu")
	public String school(ModelMap modelMap,@ModelAttribute("memberVO")  MemberVO memberVO) throws Exception{

		return "join/school";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/idCheck.edu")
	public int idCheck(MemberVO memberVO) throws Exception{
		if (memberVO.getMemId().equals("") ||memberVO.getMemId()==null) {
			return 2;
		}
		int cnt=memberService.idCheck(memberVO);
		System.out.println(cnt+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return cnt;
	}
	
	// email보내는거
	@ResponseBody
	@RequestMapping("/signUp.edu")
	public String signUp(@RequestParam(value = "email") String email,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 임의의 authKey 생성 & 이메일 발송
		
		
		
		String authKey = mss.sendAuthMail(email,req.getServerName(),req.getServerPort());
		
		return authKey;

	}
	
	 
	
	//email에서 링크 클릭했을 때 
//	@RequestMapping("/signUpConfirm.edu")
//    public String signUpConfirm(@RequestParam(value="email") String email,@RequestParam(value="authKey") String authKey,HttpServletResponse response) throws Exception{
//		Cookie cookie = CookieUtils.createCookie("authKey", authKey,"",180);
//		response.addCookie(cookie);
//		
//		
//     return "join/emailChecked";
//
// 	}
	
	
	//저장눌렀을떄
		@ResponseBody
		@RequestMapping("/signUpEmailRandomCode.edu")
	    public String signUpEmailRandomCode(HttpServletRequest request) throws Exception{
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			Cookie[] cookies=request.getCookies();
			Cookie cookie2 = null;
			for(Cookie ck : cookies) {
				if(ck.getName().equals("authKey")) {
					cookie2=ck;
					break;
				}
			}
		if(cookie2==null) {
			return "";
		}
		
	     return cookie2.getValue();
	 	}
	
	
	@ResponseBody
	@RequestMapping(value="/schoolData.edu" ,produces = "application/json;charset=UTF-8")
	public String schoolData(@RequestParam(value="schoolNm",required=false) String schoolNm) throws Exception{
		StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_elesch_mskul_lc_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=CKSdnmse53fq6vYT7lC0aBrdLZZxihmxtVcSgaqjop3WtdbXtucjztpHwhDdlZ5QXY5%2FYzK%2FsMNCktQoHcqZ%2FQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/  //xml하면 뒤에 되는데 json하면 뒤에 파라미터 안먹음
        urlBuilder.append("&" + URLEncoder.encode("schoolNm","UTF-8") + "=" + URLEncoder.encode(schoolNm, "UTF-8")); /*학교명*/     
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        String xmlJSONObjString = xmlJSONObj.toString();
      
        
        System.out.println(xmlJSONObjString);
		return xmlJSONObjString;
	}
	
	
	

}
