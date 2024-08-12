// import { useEffect, useState } from "react";
// import "./StudentFreeBoardDetail.css";
// import useAxiosGet from "../../hooks/useAxiosGet";
// import { useParams } from "react-router-dom";

// const StudentFreeBoardDetail = ({ username }) => {
//   const { boardId } = useParams();
//   console.log(boardId);
//   const {
//     data: freeboardDetail,
//     loading,
//     error,
//   } = useAxiosGet(`/students/freeboard/${boardId}`);
//   const [text, setText] = useState("");
//   const maxLength = 500;

//   const [isModalOpen, setIsModalOpen] = useState(false);
//   const [formData, setFormData] = useState({
//     title: "",
//     content: "",
//     file: null,
//   });
//   const [selectedFileName, setSelectedFileName] = useState("");

//   const handleCheckTextCount = (e) => {
//     const textarea = e.target;
//     textarea.style.height = "auto";
//     textarea.style.height = textarea.scrollHeight + "px";

//     const curText = e.target.value;
//     if (curText.length <= maxLength) {
//       setText(curText);
//     }
//   };

//   const openModal = () => setIsModalOpen(true);
//   const closeModal = () => setIsModalOpen(false);

//   useEffect(() => {
//     window.scrollTo(0, 0);
//   }, []);

//   return (
//     <div className="main_container">
//       <div className="student_freeboard_detail_page_title_box">
//         <h1 className="student_freeboard_detail_page_title">자유 게시판</h1>
//         <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
//         <div className="student_freeboard_detail_box">
//           <button className="student_freeboard_detail">수정</button>
//           <hr className="devide_button_border" />
//           <button className="student_freeboard_detail">삭제</button>
//           <hr className="devide_button_border" />
//           <button className="student_freeboard_detail">스크랩</button>
//         </div>
//       </div>
//       {/* 조회한 자유 게시판 글 */}
//       <div className="freeboard_detail_container">
//         <h1 className="freeboard_detail_title">다들 수업진도 어떻게 생각해?</h1>
//         <p className="freeboard_detail_content">
//           나는 조금 빠르다고 생각하는데 다들 ㅃㅏ르다고 생각하는 사람 얼마나
//           되는지 궁금해서!
//         </p>
//         <div className="freeboard_detail_info_box">
//           <div className="freeboard_watcher_info_box">
//             <i class="bi bi-eye"></i>&nbsp;
//             <span className="freeboard_view_count">146</span>&nbsp;&nbsp;
//             <i class="bi bi-star"></i>&nbsp;
//             <span className="freeboard_view_like_count">192</span>
//           </div>
//           <div className="freeboard_writer_info_box">
//             <sapn className="freeboard_writer_name">김수정</sapn> | &nbsp;
//             <sapn className="freeboard_writer_date">2024-08-04</sapn> 작성
//           </div>
//         </div>
//       </div>
//       {/* 댓글 리스트 */}
//       <div className="freeboard_writer_comment_box">
//         <i class="bi bi-chat"></i> &nbsp;
//         <span className="freeboard_comment_count">30</span>
//       </div>
//       <div className="freeboard_comment_list_container">
//         <div className="freeboard_comment_write_form">
//           <div className="freeboard_user_info_box">
//             <img
//               src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_270/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg"
//               className="freeboard_user_profile"
//               alt=""
//             />
//             &nbsp;
//             <span className="freeboard_user_name">김승민</span>
//           </div>
//           <textarea
//             className="freeboard_current_write_textarea"
//             value={text}
//             onChange={handleCheckTextCount}
//             maxLength={maxLength}
//           ></textarea>
//           <div className="freeboard_current_write_setting_box">
//             <div className="textarea_letter_count_box">
//               <span className="textarea_letter_count">{text.length}</span>
//               &nbsp;/&nbsp;
//               {maxLength}
//             </div>
//             <button className="student_submit_btn">답변 등록</button>
//           </div>
//         </div>
//       </div>
//       {/* 댓글 리스트들 */}
//       <div className="student_freeboard_comment_list">
//         <div className="freeboard_written_user_info_box">
//           <div className="freeboard_user_info_box">
//             <img
//               src="https://i.namu.wiki/i/LlmtrVfgkA1KbtazOm8fjmX0dtbFQ3yGHfQbVK4OVfhq9oslyT-OLrflPDgcyZTSqBUKYbjAkAuP8F9c124TGg.webp"
//               className="freeboard_user_profile"
//               alt=""
//             />
//             &nbsp;
//             <span className="freeboard_user_name">김승민</span>
//           </div>
//           <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
//           <div className="freeboard_comment_modify_box">
//             <button className="student_freeboard_detail">수정</button>
//             <hr className="devide_button_border" />
//             <button className="student_freeboard_detail">삭제</button>
//           </div>
//         </div>
//         <textarea className="freeboard_write_textarea" readOnly>
//           sdfdsfdsfdsfdfdsfsdf
//         </textarea>
//         <div className="freeboard_writedate_box">
//           <span className="freeboard_written_date">2024-08-08 15:33</span>
//         </div>
//       </div>
//       {/* 대댓글 리스트 */}
//       <div className="freeboard_recoment_list">
//         <div className="freeboard_recoment_box">
//           <div className="freeboard_written_user_info_box">
//             <div className="freeboard_user_info_box">
//               <img
//                 src="https://i.namu.wiki/i/LlmtrVfgkA1KbtazOm8fjmX0dtbFQ3yGHfQbVK4OVfhq9oslyT-OLrflPDgcyZTSqBUKYbjAkAuP8F9c124TGg.webp"
//                 className="freeboard_user_profile"
//                 alt=""
//               />
//               &nbsp;
//               <span className="freeboard_user_name">정성진</span>
//             </div>
//             <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
//             <div className="freeboard_recomment_modify_box">
//               <button className="student_freeboard_detail">수정</button>
//               <hr className="devide_button_border" />
//               <button className="student_freeboard_detail">삭제</button>
//             </div>
//           </div>
//           <textarea className="freeboard_write_textarea" readOnly>
//             sdfdsfdsfdsfdfdsfsdf
//           </textarea>
//           <div className="freeboard_writedate_box">
//             <span className="freeboard_written_date">2024-08-08 15:33</span>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default StudentFreeBoardDetail;
///////////////////////////////////////////위에는 기존 스타일 확인용

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./StudentFreeBoardDetail.css";
import useAxiosGet from "../../hooks/useAxiosGet";
import StudentPatchModal from "../../components/Modal/StudentModal/StudentPatchModal";
import useAxiosPost from "../../hooks/useAxiosPost";
import useAxiosDelete from "../../hooks/useAxiosDelete";
import axios from "axios";
import { config } from "react-transition-group";

const StudentFreeBoardDetail = ({ username }) => {
  const { boardId } = useParams();

  // GET 요청
  const { data: freeboardDetail, loading } = useAxiosGet(
    `/students/boards/${boardId}`
  );

  // Post 요청
  const { postRequest } = useAxiosPost(`/students/boards/${boardId}/comments`);

  // Delete 요청
  const { deleteRequest } = useAxiosDelete(`/students/boards/${boardId}`);

  //
  const [formData, setFormData] = useState({
    title: "",
    content: "",
    file: null,
    username: username,
  });

  const [error, setError] = useState(null);
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => setIsMenuOpen(!isMenuOpen);

  const [text, setText] = useState("");
  const maxLength = 500;

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedFileName, setSelectedFileName] = useState("");

  useEffect(() => {
    if (freeboardDetail) {
      setFormData({
        title: freeboardDetail.title || "",
        content: freeboardDetail.content || "",
        file: freeboardDetail.filename || "",
      });
    }
  }, [freeboardDetail]);

  const handleCheckTextCount = (e) => {
    const textarea = e.target;
    textarea.style.height = "auto";
    textarea.style.height = textarea.scrollHeight + "px";

    const curText = e.target.value;
    if (curText.length <= maxLength) {
      setText(curText);
    }
  };

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "file" && files.length > 0) {
      setFormData((prevState) => ({
        ...prevState,
        [name]: files[0],
      }));
    } else {
      setFormData((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    }
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedFileName(file.name);
      handleChange(e);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("모달 데이터 : ", formData);
    closeModal();
  };

  const handleDelete = () => {
    if (window.confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
      deleteRequest()
        .then(() => {
          alert("게시물이 삭제되었습니다.");
        })
        .catch((err) => {
          console.error("게시물 삭제 실패:", err);
          alert("게시물 삭제에 실패했습니다.");
        });
    }
  };

  const handleCommentSubmit = () => {
    const commentData = { content: text };
    postRequest(commentData);
  };

  const formatTime = (timeString) => {
    const date = new Date(timeString);
    return date.toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
    });
  };

  const splitDate = (writeDate) => {
    if (writeDate) {
      return writeDate.split("T")[0];
    }
    return "날짜 불러오기 실패";
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="main_container">
      <div className="student_freeboard_detail_page_title_box">
        <h1 className="student_freeboard_detail_page_title">자유 게시판</h1>
        {freeboardDetail?.username === username && (
          <>
            <i
              className="bi bi-three-dots-vertical freeboard_three_dot"
              onClick={toggleMenu}
            ></i>
            {isMenuOpen && (
              <div className="student_freeboard_detail_box">
                <button
                  className="student_freeboard_detail"
                  onClick={() => openModal}
                >
                  수정
                </button>
                <hr className="devide_button_border" />
                <button
                  className="student_freeboard_detail"
                  onClick={() => handleDelete()}
                >
                  삭제
                </button>
                <hr className="devide_button_border" />
                <button className="student_freeboard_detail">스크랩</button>
              </div>
            )}
          </>
        )}
      </div>
      {/* 조회한 자유 게시판 글 */}
      <div className="freeboard_detail_container">
        <h1 className="freeboard_detail_title">{freeboardDetail?.title}</h1>
        <p className="freeboard_detail_content">{freeboardDetail?.content}</p>
        <div className="freeboard_detail_info_box">
          <div className="freeboard_watcher_info_box">
            <i className="bi bi-eye"></i>&nbsp;
            <span className="freeboard_view_count">
              {freeboardDetail?.viewCount}
            </span>
            &nbsp;&nbsp;
            <i className="bi bi-star"></i>&nbsp;
            <span className="freeboard_view_like_count">
              {freeboardDetail?.likeCount}
            </span>
          </div>
          <div className="freeboard_writer_info_box">
            <span className="freeboard_writer_name">
              {freeboardDetail?.author}&nbsp;
            </span>
            | &nbsp;
            <span className="freeboard_writer_date">
              {splitDate(freeboardDetail?.createTime)} &nbsp;
            </span>
            작성
          </div>
        </div>
      </div>
      {/* 댓글 리스트 */}
      <div className="freeboard_writer_comment_box">
        <i className="bi bi-chat"></i> &nbsp;
        <span className="freeboard_comment_count">
          {freeboardDetail?.commentCount}
        </span>
      </div>
      {/* 댓글 작성 및 목록 */}
      <div className="freeboard_comment_list_container">
        <div className="freeboard_comment_write_form">
          <div className="freeboard_user_info_box">
            <img
              src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_270/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg"
              className="freeboard_user_profile"
              alt=""
            />
            &nbsp;&nbsp;&nbsp;
            <span className="freeboard_user_name">{username}</span>
          </div>
          <textarea
            className="freeboard_current_write_textarea"
            value={text}
            onChange={handleCheckTextCount}
            maxLength={maxLength}
          ></textarea>
          <div className="freeboard_current_write_setting_box">
            <div className="textarea_letter_count_box">
              <span className="textarea_letter_count">{text.length}</span>
              &nbsp;/&nbsp;
              {maxLength}
            </div>
            <button
              className="student_submit_btn"
              onClick={handleCommentSubmit}
            >
              답변 등록
            </button>
          </div>
        </div>
      </div>
      {/* 기존 댓글 목록 */}
      {freeboardDetail?.comments &&
        freeboardDetail?.comments.map((comment, index) => (
          <div key={index} className="student_freeboard_comment_list">
            <div className="freeboard_written_user_info_box">
              <div className="freeboard_user_info_box">
                <img
                  src={comment.userProfileImage}
                  className="freeboard_user_profile"
                  alt=""
                />
                &nbsp;
                <span className="freeboard_user_name">{comment.username}</span>
              </div>
              <i className="bi bi-three-dots-vertical freeboard_three_dot"></i>
              <div className="freeboard_comment_modify_box">
                <button className="student_freeboard_detail">수정</button>
                <hr className="devide_button_border" />
                <button className="student_freeboard_detail">삭제</button>
              </div>
            </div>
            <textarea className="freeboard_write_textarea" readOnly>
              {comment.text}
            </textarea>
            <div className="freeboard_writedate_box">
              <span className="freeboard_written_date">{comment?.date}</span>
            </div>
          </div>
        ))}
      {/* 대댓글 목록 */}
      {freeboardDetail?.recomments &&
        freeboardDetail?.recomments.map((recomment, index) => (
          <div key={index} className="freeboard_recoment_list">
            <div className="freeboard_recoment_box">
              <div className="freeboard_written_user_info_box">
                <div className="freeboard_user_info_box">
                  <img
                    src={recomment?.userProfileImage}
                    className="freeboard_user_profile"
                    alt=""
                  />
                  &nbsp;
                  <span className="freeboard_user_name">
                    {recomment?.username}
                  </span>
                </div>
                <i className="bi bi-three-dots-vertical freeboard_three_dot"></i>
                <div className="freeboard_recomment_modify_box">
                  <button className="student_freeboard_detail">수정</button>
                  <hr className="devide_button_border" />
                  <button className="student_freeboard_detail">삭제</button>
                </div>
              </div>
              <textarea className="freeboard_write_textarea" readOnly>
                {recomment?.text}
              </textarea>
              <div className="freeboard_writedate_box">
                <span className="freeboard_written_date">
                  {recomment?.date}
                </span>
              </div>
            </div>
          </div>
        ))}
      <StudentPatchModal
        isOpen={isModalOpen}
        closeModal={closeModal}
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        handleFileChange={handleFileChange}
        selectedFileName={selectedFileName}
        modalName="게시글 수정"
        contentTitle="제목"
        contentBody="내용"
        contentFile="이미지 첨부"
        url={`/students/boards/${boardId}`}
        submitName="게시글 수정"
        cancelName="수정 취소"
      />
    </div>
  );
};

export default StudentFreeBoardDetail;
