import { useNavigate } from "react-router-dom";
import "./StudentLecture.css";
import useGetFetch from "../../hooks/useGetFetch";

const StudentLecture = () => {
  const navigate = useNavigate();

  function getYoutubeEmbedUrl(url) {
    const videoId = url.split("v=")[1]?.split("&")[0];
    return `https://www.youtube.com/embed/${videoId}`;
  }

  const {
    data: mainLectures,
    loading: mainLecturesLoading,
    error: mainLecturesError,
  } = useGetFetch("/data/student/mainLecture/mainLecture.json", "");

  if (mainLecturesLoading) {
    return <div>Loading...</div>;
  }

  if (mainLecturesError) {
    return <div>Error loading lectures</div>;
  }

  return (
    <div className="student_lecture_container">
      <div className="side_bar">
        <h3>옆 카테고리 컴포넌트 자리</h3>
      </div>
      <div className="main_container">
        <div className="lecutre_type_container">
          <img
            className="lecture_type_image"
            alt="과목이미지"
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSv_al5pqaDdDHWONxiCA-B1mjpNdbA8fEe8g&s"
          />
          <div className="lecture_description_box">
            <h1 className="lecture_type_name">JAVA</h1>
            <p className="lecture_type_description">아따 대충 자바요</p>
          </div>
        </div>
        {/* 게시판  */}
        <div className="board_container">
          <div className="subject_board_container">
            <div className="board_title_box">
              <h3 className="board_title">과목 게시판</h3>
              <span className="go_to_show_more_page">더보기 ⟩</span>
            </div>
            <div className="subject_list_container">
              <div className="subject_list">
                <div className="subject_title_box">
                  <h4 className="subject_title">1주차 수업 자로</h4>
                  <span className="subject_write_date">2024.08.01</span>
                </div>
                <div className="subject_content_box">
                  <span className="subject_text_content">
                    1주차 수업 자료입니다.
                  </span>
                  <span className="subject_file_name">1주차 수업자료.zip</span>
                </div>
              </div>
            </div>
          </div>
          <div className="inquiry_board_container">
            <div className="board_title_box">
              <h3 className="board_title">질문 게시판</h3>
              <span className="go_to_show_more_page">더보기 ⟩</span>
            </div>
            <div className="inquiry_list_container">
              <div className="inquiry_list">
                <div className="inquiry_title_box">
                  <div className="inquiry_type">질문</div>
                  <h4 className="inquiry_list_title">1주차 수업 자로</h4>
                  <span className="inquiry_write_date">2024.08.01</span>
                </div>
                <div className="inquiry_content_box">
                  <span className="inquiry_text_content">
                    1주차 수업 자료입니다.
                  </span>
                  <span className="inquiry_file_name">1주차 수업자료.zip</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="lecture_list_container">
          <div className="lecture_title_box">
            <h3 className="lecture_list_title">강의영상</h3>
            <div className="button_box">
              <button className="left_button non_style_button">⟨</button>
              <button className="right_button non_style_button">⟩</button>
            </div>
          </div>
          <div className="lecture_video_container">
            {mainLectures &&
              mainLectures.lectures &&
              mainLectures.lectures.map((el, idx) => (
                <div className="lecture_video" key={idx}>
                  <iframe
                    width="100%"
                    height="100%"
                    src={getYoutubeEmbedUrl(el.links)}
                    frameBorder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                    title={el.title}
                  ></iframe>
                </div>
              ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentLecture;
