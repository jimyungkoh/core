package hello.core.member;

//MemberService 구현체
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;
    // 구현객체(MemoryMemberRepository) 없으면 NullPointException 터짐

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    /*테스트 용도*/
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
