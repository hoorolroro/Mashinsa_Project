package com.spring.masinsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.masinsa.dto.MemberDTO;
import com.spring.masinsa.response.Message;
import com.spring.masinsa.response.Status;
import com.spring.masinsa.service.MemberServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin({"*"})
@RestController
public class MemberController {
	
	@Autowired
	MemberServiceImpl memberService;
	
	@ApiOperation(value = "1번 - 회원 데이터 DB에 저장")
	@PostMapping("/member/new-member")
	//params : memberId, nickName, sex, ageGroup, birthDate  : all are optional
	//memberDTO를 받아서 DB에 저장
	public ResponseEntity<?> saveMember(@RequestBody MemberDTO memberDTO)
	{
		System.out.println("memberDTO : " + memberDTO);
		MemberDTO member = memberService.saveMember(memberDTO);
		if (member != null) {
			Message msg = new Message(Status.OK, "회원가입 성공", member);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		Message msg = new Message(Status.OK, "회원가입 실패", memberDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}


	@ApiOperation(value = "3번 - memberId를 통해 회원 정보 조회")
	@GetMapping("/member")
	public ResponseEntity<MemberDTO> getMask(@RequestParam Long memberId) {
		MemberDTO memberDTO = memberService.getMember(memberId); 
		return new ResponseEntity<MemberDTO>(memberDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "2번 - memberId를 통해 회원 탈퇴")
	@PutMapping("/member/delete")
	public ResponseEntity<?> deleteMember(@RequestParam Long memberId) {
		MemberDTO memberDTO = memberService.deleteMember(memberId);
		if (memberDTO != null) {
			Message msg = new Message(Status.OK, "회원 탈퇴 완료", memberDTO);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		Message msg = new Message(Status.OK, "회원 탈퇴 실패", memberDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
		}
}
