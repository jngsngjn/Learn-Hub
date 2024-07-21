package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.vote.VoteContent;

public interface VoteContentRepository extends JpaRepository<VoteContent, Long> {
}