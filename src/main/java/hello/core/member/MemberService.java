package hello.core.member;

//MemberService 역할
public interface MemberService {

    void join(Member member); //회원가입
    Member findMember(Long memberId); //회원조회

}
