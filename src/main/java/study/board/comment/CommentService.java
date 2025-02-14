package study.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.board.Board;
import study.board.board.dto.BoardDto;
import study.board.board.BoardService;
import study.board.comment.dto.CommentPostEditDto;
import study.board.member.Member;
import study.board.member.MemberRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    @Transactional
    public void create(Long boardId, String loginId, String commentContent) {
        Board board = boardService.findBoard(boardId);
        Comment comment = new Comment(board, loginId, commentContent);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void edit(CommentPostEditDto commentPostEditDto) {
        Comment comment = this.findComment(commentPostEditDto.getCommentId());
        comment.setCommentContent(commentPostEditDto.getCommentContent());
    }

    @Transactional
    public Comment findComment(Long commentId) {
//        return commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post id: "));
    }

    @Transactional
    public BoardDto findBoardDto(Long commentId) throws Exception {
        Long boardId = this.findComment(commentId).getBoard().getId();
        return boardService.findBoardToBoardDto(boardId);
    }
}
