package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.board.BoardCreateDto;
import project.homelearn.dto.manager.board.BoardReadDto;
import project.homelearn.dto.manager.board.BoardUpdateDto;
import project.homelearn.entity.manager.ManagerBoard;
import project.homelearn.repository.board.ManagerBoardRepository;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ManagerBoardService {

    private final ManagerBoardRepository managerBoardRepository;

    public boolean createManagerBoard(BoardCreateDto managerBoardDto) {
        try {
            ManagerBoard board = new ManagerBoard();
            board.setTitle(managerBoardDto.getTitle());
            board.setContent(managerBoardDto.getContent());
            board.setEmergency(managerBoardDto.getEmergency());
            managerBoardRepository.save(board);
            System.out.println("board = " + board);
            return true;
        } catch (Exception e) {
            log.error("Error creating manager board", e);
            return false;
        }
    }

    private static List<BoardReadDto> getAllManagerBoards(Page<ManagerBoard> managerBoards) {
        return managerBoards.stream()
                .map(managerBoard -> new BoardReadDto(
                        managerBoard.getId(),
                        managerBoard.getTitle(),
                        managerBoard.getContent(),
                        managerBoard.isEmergency()
                )).toList();
    }

    public Page<BoardReadDto> getManagerBoards(int page, int size) {
        //startPage, endPage, currentPage
        Page<ManagerBoard> managerBoards;
        Pageable pageable = PageRequest.of(page, size);
        try {
            managerBoards = managerBoardRepository.findManagerBoardsBy(pageable);
            List<BoardReadDto> boardReadDtoList = getAllManagerBoards(managerBoards);
            return new PageImpl<>(boardReadDtoList, pageable, managerBoards.getTotalElements());
        } catch (Exception e) {
            log.error("Error getting manager boards", e);
            return Page.empty();
        }
    }

    public boolean deleteManagerBoard(Long id) {
        try {
            managerBoardRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting manager board", e);
            return false;
        }
    }

    public boolean updateManagerBoard(BoardUpdateDto boardUpdateDto, Long id) {
        try {
            ManagerBoard board = managerBoardRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error getting manager board" + id));
            board.setTitle(boardUpdateDto.getTitle());
            board.setContent(boardUpdateDto.getContent());
            board.setEmergency(boardUpdateDto.getEmergency());
            managerBoardRepository.save(board);

            return true;
        } catch (Exception e) {
            log.error("Error updating manager board", e);
            return false;
        }
    }
}
