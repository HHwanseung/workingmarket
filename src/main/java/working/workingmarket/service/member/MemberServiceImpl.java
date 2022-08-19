package working.workingmarket.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import working.workingmarket.dto.member.MemberInfoDto;
import working.workingmarket.dto.member.MemberSignUpDto;
import working.workingmarket.dto.member.MemberUpdateDto;
import working.workingmarket.entity.member.Member;
import working.workingmarket.repository.member.MemberRepository;
import working.workingmarket.util.security.SecurityUtil;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberSignUpDto.toEntity();
        member.addUserAuthority();
        member.encodePassword(passwordEncoder);

        if (memberRepository.findByUsername(memberSignUpDto.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        memberRepository.save(member);
    }

    @Override
    public void update(MemberUpdateDto memberUpdateDto) throws Exception {
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        memberUpdateDto.getAge().ifPresent(member::updateAge);
        memberUpdateDto.getName().ifPresent(member::updateName);
        memberUpdateDto.getNickName().ifPresent(member::updateNickName);
    }

    @Override
    public void updatePassword(String checkPassword, String toBePassword) throws Exception {
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        if (!member.matchPassword(passwordEncoder, checkPassword) ) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(passwordEncoder, toBePassword);

    }

    @Override
    public void withdraw(String checkPassword) throws Exception {
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다."));

        if (!member.matchPassword(passwordEncoder, checkPassword) ) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(member);

    }

    @Override
    public MemberInfoDto getInfo(Long id) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        Member findMember = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }


}
