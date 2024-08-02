package project.homelearn.controller.teacher.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.vote.VoteCreateDto;
import project.homelearn.service.teacher.VoteService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/votes")
    public ResponseEntity<?> createVote(Principal principal, @RequestBody VoteCreateDto voteCreateDto) {
        String username = principal.getName();
        boolean result = voteService.createVote(voteCreateDto, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/votes/{id}")
    public ResponseEntity<?> deleteVote(@PathVariable("id") Long id) {
        boolean result = voteService.deleteVote(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
