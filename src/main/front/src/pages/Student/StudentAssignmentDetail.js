import { useNavigate } from "react-router-dom";
import "./StudentAssignmentDetail.css";
import { useEffect } from "react";
import useGetFetch from "../../hooks/useGetFetch";

const StudentAssignmentDetail = () => {
  const navigate = useNavigate();

  // useEffect(() => {
  //   window.scrollTo(0, 0);
  // }, []);

  const { data: subjectBoards, error: subjectBoardsError } = useGetFetch(
    "/data/student/mainLecture/subjectBoard.json",
    []
  );

  if (subjectBoardsError) {
    return <div>데이터를 불러오는데 실패하였습니다.</div>;
  }

  return (
    <div className="student_assignment_detail_container">
      <div className="side_bar">
        <h3>사이드 바</h3>
      </div>
      <div className="assignment_detail_main_container">
        <h2 className="student_assignment_board_page_title">과제</h2>
        <div className="student_assignment_board_content_box">
          <div className="student_assignment_board_title_box">
            <span className="student_assignment_title">
              Spring으로 게시판 생성
            </span>
            <span className="assignment_download_file_name">
              1주차 수업 자료.zip
            </span>
          </div>
          <div className="student_assignment_board_content">
            Spring MVC로 CRUD를 고려하여 게시판 생성
          </div>
          <div className="student_assignment_board_info_box">
            <div className="important_notice_box">
              <span className="student_assignment_deadline"> 2024-08-26</span>
              &nbsp;까지 &nbsp;<b>|</b>&nbsp;
              <span>
                미제출&nbsp;
                <span className="student_assignment_participants_count">
                  3명
                </span>
              </span>
            </div>
            <p class="student_assignment_board_writed_date">
              2024-07-01 &nbsp; <span style={{ color: "black" }}>작성</span>
            </p>
          </div>
        </div>
        <div className="student_assignment_participants_list_container"></div>
        {/* 과목 게시판 리스트들 */}
        <div className="student_assignment_participants_container">
          제출 &nbsp;
          <span className="tudent_assignment_participants_count">28명</span>
        </div>
        <table className="subject_board_list_table">
          <tr className="subject_board_table_tab_names">
            <th className="submitted_number"> 1</th>
            <th className="submitted_name">동재완</th>
            <th className="submitted_date">2024-08-01</th>
            <th className="submitted_show_more_submitted_info">+</th>
          </tr>
        </table>
      </div>
    </div>
  );
};

export default StudentAssignmentDetail;
