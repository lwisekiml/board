package study.board;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import study.board.board.Board;
import study.board.board.BoardRepository;
import study.board.member.Member;
import study.board.member.MemberRepository;
import study.board.member.MemberRole;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
//        memberRepository.save(new Member("kim", "test", "1234"));
//        memberRepository.save(new Member("lee", "qwer", "zxcv"));

//        boardRepository.save(new Board("test", "제목1", "내용1"));
//        boardRepository.save(new Board("test", "제목3", "내용3"));
//        boardRepository.save(new Board("qwer", "제목2", "내용2"));

        memberRepository.save(new Member("loginId", "membername", "member@email.com", passwordEncoder.encode("password"), MemberRole.MEMBER));
        memberRepository.save(new Member("asd", "asd", "asd@email.com", passwordEncoder.encode("asd"), MemberRole.MEMBER));
        memberRepository.save(new Member("admin", "ADMIN", "admin@email.com", passwordEncoder.encode("12345"), MemberRole.ADMIN));

        Member member1 = memberRepository.findByLoginId("loginId").get();
        Member member2 = memberRepository.findByLoginId("asd").get();

        for (int i = 0; i < 2; i++) {
            boardRepository.save(new Board("loginId", "test", "test제목"+i, 1));
        }

        for (int i = 0; i < 2; i++) {
            boardRepository.save(new Board("asd", "qwer", "qwer제목"+i, 1));
        }
    }
}
