package project.homelearn.entity.board.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.user.User;

@Getter @Setter
@MappedSuperclass
public abstract class CommentBaseEntity extends BaseEntity {

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /**
     * AI가 자동 답글을 등록할 수 있기 때문에 nullable : true
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}