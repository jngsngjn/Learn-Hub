import "./StudentAssignment.css";

const StudentAssignment = () => {
  return (
    <div className="student_subject_body">
      <div className="side_bar">
        <h3>옆 카테고리 컴포넌트 자리</h3>
      </div>
      <div className="student_subject_main_container">
        <h1 className="student_subject_page_title">과제</h1>
        <div className="current_proceeding_subject_container">
          <div className="current_proceeding_subject_title_box">
            <h3 className="current_proceeding_subject_box_name">
              진행 중인 과제
            </h3>
            <div className="controll_box_with_both_side">
              <i class="bi bi-chevron-left Right_and_left_button"></i>
              <i class="bi bi-chevron-right Right_and_left_button"></i>
            </div>
          </div>
          {/* 진행중인 과제 */}
          <div className="current_proceeding_subject_contents_container">
            <div className="current_proceeding_subject_contents_title_box">
              <h3 className="current_proceeding_subject_contents_title">
                Spring으로 게시판 생성
              </h3>
              <span className="go_to_proceeding_subject_page">
                자세히 보기 ⟩
              </span>
            </div>
            <p className="current_proceeding_subject_contents">
              스프링과 그래들 설정하고 게시판 만들어오세요
            </p>
            <div className="current_proceeding_subject_contents_additional_data_box">
              <p className="">
                <span className="current_proceeding_subject_contents_deadline geen_text">
                  2024.08.21
                </span>
                &nbsp;까지
              </p>
              <p className="current_proceeding_subject_contents_Participants">
                <span className="current_proceeding_subject_contents_Participants_count geen_text">
                  28명
                </span>
                &nbsp; 제출
              </p>
            </div>
          </div>
        </div>
        {/* 마감된 과제 */}
        <div className="closed_subject_container">
          <div className="closed_subject_title_box">
            <h3 className="closed_subject_box_name">진행 중인 과제</h3>
            <div className="controll_box_with_both_side">
              <i class="bi bi-chevron-left Right_and_left_button"></i>
              <i class="bi bi-chevron-right Right_and_left_button"></i>
            </div>
          </div>
          {/* 마감된 과제 리스트 */}
          <div className="closed_subject_contents_container">
            <div className="closed_subject_contents_title_box">
              <h3 className="closed_subject_contents_title">
                Spring으로 게시판 생성
              </h3>
              <span className="go_to_closed_subject_page">자세히 보기 ⟩</span>
            </div>
            <p className="closed_subject_contents">
              스프링과 그래들 설정하고 게시판 만들어오세요
            </p>
            <div className="closed_subject_contents_additional_data_box">
              <p className="">
                <span className="closed_subject_contents_deadline geen_text">
                  2024.08.21
                </span>
                &nbsp;까지
              </p>
              <p className="closed_subject_contents_Participants">
                <span className="closed_subject_contents_Participants_count geen_text">
                  28명
                </span>
                &nbsp; 제출
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentAssignment;
