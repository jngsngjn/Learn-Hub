package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.vote.Vote;
import project.homelearn.repository.vote.querydsl.VoteRepositoryCustom;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
}