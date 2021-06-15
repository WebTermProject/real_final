package service;


import javax.servlet.http.HttpSession;

import domain.Member;
import persistence.MemberRepository;

public class MemberService {
	private final static MemberRepository memberRepository = MemberRepository.getInstacne();
	public MemberService() {
		
	}
	//회원가입
	public static int join(Member member) {
		int num = memberRepository.memberJoin(member);
		if( num != 1   ) {
			failJoin();
		}
		return num;
	 
	}
	private static void failJoin(){   
       throw new IllegalArgumentException("회원가입 실패");
    }
	
	//로그인
	public static int login(String userID, String userPW) throws Exception {
		
		memberRepository.userCheck(userID, userPW);
		
		return memberRepository.userCheck(userID, userPW);
    }

}
