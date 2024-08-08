import "./StudentSubjectBoardDetail.css";
import { useEffect, useState } from "react";
import useGetFetch from "../../hooks/useGetFetch";
import { useNavigate } from "react-router-dom";
import LectureVideo from "../../components/Lectures/LectureVideo";

const StudentSubjectBoardDetail = () => {
  const navigate = useNavigate();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const { data: subjectBoards, error: subjectBoardsError } = useGetFetch(
    "/data/student/mainLecture/subjectBoard.json",
    []
  );

  if (subjectBoardsError) {
    return <div>데이터를 불러오는데 실패하였습니다.</div>;
  }

  return (
    <div className="subject_board_detail_main_container">
      <LectureVideo width="1120px" height="600px" />
      <div className="lecutre_type_container">
        <div className="lecture_description_box">
          <h1 className="lecture_type_name">JAVA</h1>
          <p className="lecture_type_description">설명</p>
        </div>
      </div>
      <h2 className="student_subject_board_page_title">과목 게시판</h2>
      {/* 여기부터 과목 게시글 내용 */}
      <div className="student_subject_board_title_box">
        <span className="student_subject_board_title">1주차 수업 자료.zip</span>
        <span
          className="student_subject_board_view_count"
          style={{ fontSize: "28px" }}
        >
          <i className="bi bi-eye student_subject_board_view_count_icon"></i>
          132
        </span>
      </div>
      <div className="student_subject_board_body_container">
        <a
          href="/assignment_files/1주차 수업자료.zip"
          download="1주차 수업 자료.zip"
        >
          <p className="subject_board_download_file_name">
            1주차 수업 자료.zip
          </p>
        </a>
        <p className="student_subject_board_content">
          1주차 수업 자료입니다. 미리 다운로드 받으세요! 수업 때 참고 자료로
          사용 예정!
        </p>
        <p className="student_subject_board_content_image">
          여기가 에디터 글이 들어올 곳? 아니면 이미지인가? 모르겠네ㅔ^^
        </p>
      </div>
      {/* 과목 게시판 리스트들 */}
      <div className="subject_board_main_body_container">
        <h3 className="subject_board_main_title">과목 게시판</h3>
        <table className="subject_board_list_table">
          <tr className="subject_board_table_tab_names">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
          {subjectBoards.slice(0, 5).map((el, idx) => (
            <tr className="writed_subject_board_lists" key={idx}>
              <td>{idx + 1}</td>
              <td
                className="writed_subject_board_title_one"
                style={{ cursor: "pointer" }}
                onClick={() =>
                  navigate(`/students/subjectBoardDetail/${el.id}`)
                }
              >
                {el.title}
              </td>
              <td>{el.writer}</td>
              <td>{el.writeDate}</td>
              <td>{el.viewCount}</td>
            </tr>
          ))}
        </table>
      </div>
    </div>
  );
};

export default StudentSubjectBoardDetail;
