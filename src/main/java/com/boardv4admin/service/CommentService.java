package com.boardv4admin.service;

import com.boardv4admin.domain.Comment;
import com.boardv4admin.domain.Member;
import com.boardv4admin.dto.comment.CommentResponse;
import com.boardv4admin.dto.comment.CommentWriteRequest;
import com.boardv4admin.mapper.CommentMapper;
import com.boardv4admin.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final MemberService memberService;

    /*
        댓글을 화면에 보여 주려면, 작성자의 '이름'이 필요함
        최초 구현 시에는 모든 comment 객체 내의 memberId를 기반으로 member를 조회하고,
        해당 member의 이름을 DTO에 포함해서 Response 객체를 만들었음
        (댓글 가져오기 쿼리 1회, 각 댓글마다 작성자 이름 찾기 쿼리 n회)
        --
        개선 1) 동일한 사용자가 같은 게시글에 댓글을 중복해서 달수도 있기에,
        먼저 댓글들에서 등장하는 모든 사용자 id를 Set으로 찾아내고,
        해당 사용자들의 id : name Map 을 쿼리로 받아온다음, Response를 생성할때 해당 map을 사용해서 Response를 생성하는 방법
        (댓글 가져오기 쿼리 1회, 사용자 이름 Map 반환 쿼리 1회)
        --
        개선 2) 위 방법보다 차라리 Repository에서 바로 DTO를 구성하는게 낫겠다는 판단이 들어 해당 방법으로 개선 (쿼리 1회)
        entity 확장? -> 다른 쿼리에서도 항상 join을 하는 등 불편이 생길 우려가 있어 일단 기존 방식 유지
     */
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findResponseListByPostId(postId);
    }

    public void write(String username, CommentWriteRequest writeDTO) {
        Member member = memberService.getMemberByUsername(username);

        Comment comment = commentMapper.toEntity(member.getId(), writeDTO);
        commentRepository.insert(comment);
    }


    public void deleteById(Long commentId) {
        commentRepository.delete(commentId);
    }
}
