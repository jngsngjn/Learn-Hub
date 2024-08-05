import { useNavigate } from "react-router-dom";
import useGetFetch from "../../hooks/useGetFetch";
import "./StudentLectureList.css";

const StudentLectureList = () => {
  const navigate = useNavigate();

  const { data: subjectVideos, error: subjectVideosError } = useGetFetch(
    "/data/student/mainLecture/lectureList.json",
    []
  );

  const { data: subjectName, error: subjectNameError } = useGetFetch(
    "/data/student/mainpage/subjectCategory.json",
    []
  );

  if (subjectNameError || subjectVideosError) {
    return <div>데이터 로딩에 실패하였습니다.</div>;
  }

  return (
    <div className="subject_lecture_list_body">
      <div className="side_bar">
        <h3>옆 카테고리 컴포넌트 자리</h3>
      </div>
      <div className="subject_lecture_list_main_container">
        <div className="subject_lecture_list_page_title_box">
          <h1 className="subject_lecture_list_page_title">강의 영상</h1>
          <select className="subject_lecture_select_box">
            <option value="all" selected>
              전체
            </option>
            {subjectName.map((el) => (
              <option value={`option_name_${el.id}`} key={el.id}>
                {el.name}
              </option>
            ))}
          </select>
        </div>
        <div className="subject_lecture_list_container">
          {subjectVideos.slice(0, 15).map((el, idx) => (
            <div className="subject_lecture_list_content" key={idx}>
              <img
                className="subject_lecture_list_image"
                alt="과목이미지"
                src={el.links}
              />
              <h1
                className="subject_lecture_list_title"
                onClick={() =>
                  navigate(
                    `/students/${el.subjectName}/lectures/${el.lectureId}`
                  )
                }
              >
                {el.title}
              </h1>
              <p className="subject_lecture_list_description">{el.content}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default StudentLectureList;
