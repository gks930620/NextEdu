package com.next.chat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.next.classmember.service.IClassmemberService;
import com.next.classmember.vo.ClassmemberVO;
import com.next.login.vo.UserVO;
import com.next.member.service.IMemberService;
import com.next.member.vo.MemberVO;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Inject
	private ChatRoomRepository chatRoomRepository;
	
	@Inject
	IMemberService memberService;
	
		
		@GetMapping("/disconnect.edu")
	    public void disconnect(@RequestParam String roomId,Model model){
	       
			chatRoomRepository.deleteRoomById(roomId);    //roomId는 학교ID,학년,반
			
	    }

	    @RequestMapping(value="/roomView.edu")
	    public String roomView(@RequestParam String roomId, Model model,HttpServletRequest request, HttpSession session) throws Exception{
	    	ChatRoomForm form = new ChatRoomForm();
	    	UserVO user = (UserVO) session.getAttribute("USER_INFO");
	    	MemberVO memberVO= memberService.getMemberVO(user.getUserId());
	    	System.out.println(memberVO);
	    	if(memberVO.getMemRole().equals("TEACHER")) {
	    		form.setName(memberVO.getMemScnm()+memberVO.getMemGrade()+"학년"+memberVO.getMemClass()+"반");
	    		chatRoomRepository.createChatRoom(form.getName(),roomId);
	    	}
	    	
	    	ChatRoom room =chatRoomRepository.findRoomById(roomId);    //roomId는 학교ID,학년,반
	    	
	    	if(room==null) {
	    	
	    		
	    		
	    		return "redirect:/classhome/classhome.edu";
	    	}
    		model.addAttribute("room",room);   //이 떄 room에는 webSocketSeession과 handleMessage메소드가있다
	    	model.addAttribute("memberVO",memberVO);
	        return "chat/roomView";
	    }

	  
	    
	    @RequestMapping(value="/roomRTC.edu")
	    public String roomRTC(@RequestParam(required = false)String roomId) {	   
	    	
	    	
	    	return "chat/roomRTC";
	    }
	    
	  
	    @RequestMapping("/roomRTCBC.edu")
	    public String roomRTCBC() {
	    	return "chat/roomRTCBC";
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
//	    /* 채팅방생성(Insert) */
//	    @GetMapping("/roomInsert.edu")
//	    public String roomInsert(Model model){
//	        ChatRoomForm form = new ChatRoomForm();
//	        model.addAttribute("form",form);
//	        return "chat/roomInsert";
//	    }
//	    
//	    /* 채팅방생성 (InsertMessage)*/
//	    @RequestMapping("/roomInsertMessage.edu")
//	    public String roomInsertMessage(ChatRoomForm form){
//	        chatRoomRepository.createChatRoom(form.getName(),"aa");
//	        return "redirect:/chat/roomList.edu";
//	    }
	    
	    
	    
	    
	    
}
