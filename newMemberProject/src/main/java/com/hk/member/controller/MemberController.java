package com.hk.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.member.dto.MemberVO;
import com.hk.member.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ServletContext sc;
	
//	1) @Controller Annotation 추가
//	2) URL설정 (Get or Post O 둘다)
//	- get만 지정하는 방법 @GetMapping
//	- post만 지정하는 방법 @PostMapping
//	- 둘 다 지정하는 방법 @RequestMapping
//	/member/list - > Full URL은 http://localhost:9999/member/list
// URL 체계가 : /member /list , /register, /delete , / update
	

	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String memberList(Model model) {
		
		logger.info("멤버리스트를 출력합니다");
		
//		db접속이 필요한가? (db를 접속하려면 [service모듈,repository-DAO, DTO필요]
//		db접속 없이 화면이 필요한가?
//	    db접속도 화면도 필요없나? ->RestFul + Ajax(지금 대상은 아님)

		
//		db접속이 필요.
//		service 모듈 : Repository에 있는 이름과 동일하게 작성. 별도의 트랜잭션 테이블 2개이상 사용하여 연결하는 작업이없음.
//		repository 모듈 : DAO를 직접만들까 ? 
//						mybatis를 사용할까? - 이거로 결정
//						- pom.xml에 관련 패키지를 설정 (spring-jdbc, spring mybatis, mybatis , hikari, datasource등)
//						- root-context.xml에서 db관련 설정
//						- 상세 sql logging을 보려면 별도의 패키지를 설정
//						- Mapper interface도 만들어야하고
//						- Mapper.xml에 sql도 만들어야하고 
		
		// DB JDBC DRIVER  : Ojdbc6 or ojdbc8(oracle 11g xe)
		// spring-jdbc (Spring 버전을 따라감)
		// mybatis-jdbc
		// mybatis
		// hikariCP (jdbc 성능 향상. datasource dbcp2)
		// log4jdbc-log4j2 (SQL실행하게 되면 logJ 에 나오게 해주는 클래스)
		
		
//		DTO : table하고 1:1 Mapping (getter/setter를 편하게 만들게 하기 위해서 lombok이라는 별도의 class 사용 )
		
		//service를 호출List<MemberVO>를 일반변수에는 담을 필요가없다.db에서 읽어온 내용은 jsp에서 출력하기위해.
		//jsp에서 값을 꺼내가려면, sc, session, request라는 곳에 저장을 해두면 꺼낼 수 있는데지금 
        //이 름으로 저장하고 		
		model.addAttribute("members", memberService.memberList());
		return "memberList";//서컨.xml에 있는 설정을 이용하여 파일을 찾아감.
		//"web-inf/views/memberlist.jsp"
		//redirect:/member/list
	}
	
	@RequestMapping(value = "/member/add", method = RequestMethod.GET)
	public String memberAddGet(Model model) {
		return "memberAdd";
		
	}
	
	@RequestMapping(value = "/member/add", method = RequestMethod.POST)
	public String memberAddPost(Model model, MemberVO member) {
		
		int rev = memberService.memberAdd(member);
		logger.info("insert 후에 성공인지 실패인지 알려줌.. ["+rev +"]");
		
		model.addAttribute("name", member.getMname());
		
		return "memberAddDone";
		
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String memberUpdateGet(@RequestParam("mno") int mno, Model model) {
		
		model.addAttribute("member", memberService.memberGetOne(mno));
		
		return "memberUpdateGetOne";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String memberUpdatePost(MemberVO member, Model model) {
		
		memberService.memberUpdate(member);
		model.addAttribute("member", member);
		
		return "memberUpdatePost";
		
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String memberDeleteGet(@RequestParam("mno") int mno, Model model){
		
		model.addAttribute("mno", mno);
		
		return "memberDeleteGet";
		
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String memberDeletePost(@RequestParam("mno") int mno, Model model){
		
		memberService.memberDelete(mno);
		
		return "memberDeletePost";
		
	}
	
	   @RequestMapping(value = "/upload", method = RequestMethod.POST , headers = "content-type=multipart/*")
	  
	   public String upload(@RequestParam("file") MultipartFile multipartFile,Model model) {
	      
	      logger.info("실제 파일이름은 ? " + multipartFile.getOriginalFilename());
	      
	      File targetFile = new File(sc.getRealPath("/resources/fileupload/") + multipartFile.getOriginalFilename());

	      logger.info("파일 저장위치는 :  " + targetFile);
	      try {
	         InputStream fileStream = multipartFile.getInputStream();
	         FileUtils.copyInputStreamToFile(fileStream, targetFile);
	      } catch (IOException e) {
	         FileUtils.deleteQuietly(targetFile);
	         e.printStackTrace();
	      }
	      
	    
	      model.addAttribute("imgSrc", "/resources/fileupload/" + multipartFile.getOriginalFilename());
	      return "upload";
	   }
	
	
	
		
	
	
}
