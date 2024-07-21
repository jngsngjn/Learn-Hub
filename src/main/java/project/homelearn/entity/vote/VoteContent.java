package project.homelearn.entity.vote;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "vote_content")
public class VoteContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    @Column(nullable = false)
    private String content;

    @Column(name = "vote_count", nullable = false)
    private int voteCount = 0;

    @OneToMany(mappedBy = "voteContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentVote> studentVotes = new ArrayList<>();
}