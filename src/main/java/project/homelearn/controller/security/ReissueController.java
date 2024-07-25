package project.homelearn.controller.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.jwt.ReissueService;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final ReissueService reissueService;

    // 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String result = reissueService.checkRefreshToken(request);

        if (result == null) {
            return new ResponseEntity<>("Refresh token is null", HttpStatus.BAD_REQUEST); // 400
        }

        if (result.equals("expired")) {
            return new ResponseEntity<>("Refresh token is expired", HttpStatus.BAD_REQUEST);
        }

        if (result.equals("invalid")) {
            return new ResponseEntity<>("Refresh token is invalid", HttpStatus.BAD_REQUEST);
        }

        String refreshToken = result.split(" ")[1];
        reissueService.reissueRefreshToken(refreshToken, response);

        return new ResponseEntity<>(HttpStatus.OK); // 200
    }
}