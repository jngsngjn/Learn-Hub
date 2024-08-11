import { useEffect, useState } from "react";
import "./StudentFreeBoardDetail.css";

const StudentFreeBoardDetail = (username) => {
  const [text, setText] = useState("");
  const maxLength = 500;

  const handleCheckTextCount = (e) => {
    const textarea = e.target;
    textarea.style.height = "auto";
    textarea.style.height = textarea.scrollHeight + "px";

    const curText = e.target.value;
    if (curText.length <= maxLength) {
      setText(curText);
    }
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="main_container">
      <div className="student_freeboard_detail_page_title_box">
        <h1 className="student_freeboard_detail_page_title">자유 게시판</h1>
        <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
        <div className="student_freeboard_detail_box">
          <button className="student_freeboard_detail">수정</button>
          <hr className="devide_button_border" />
          <button className="student_freeboard_detail">삭제</button>
          <hr className="devide_button_border" />
          <button className="student_freeboard_detail">스크랩</button>
        </div>
      </div>
      {/* 조회한 자유 게시판 글 */}
      <div className="freeboard_detail_container">
        <h1 className="freeboard_detail_title">다들 수업진도 어떻게 생각해?</h1>
        <p className="freeboard_detail_content">
          나는 조금 빠르다고 생각하는데 다들 ㅃㅏ르다고 생각하는 사람 얼마나
          되는지 궁금해서!
        </p>
        <div className="freeboard_detail_info_box">
          <div className="freeboard_watcher_info_box">
            <i class="bi bi-eye"></i>&nbsp;
            <span className="freeboard_view_count">146</span>&nbsp;&nbsp;
            <i class="bi bi-star"></i>&nbsp;
            <span className="freeboard_view_like_count">192</span>
          </div>
          <div className="freeboard_writer_info_box">
            <sapn className="freeboard_writer_name">김수정</sapn> | &nbsp;
            <sapn className="freeboard_writer_date">2024-08-04</sapn> 작성
          </div>
        </div>
      </div>
      {/* 댓글 리스트 */}
      <div className="freeboard_writer_comment_box">
        <i class="bi bi-chat"></i> &nbsp;
        <span className="freeboard_comment_count">30</span>
      </div>
      <div className="freeboard_comment_list_container">
        <div className="freeboard_comment_write_form">
          <div className="freeboard_user_info_box">
            <img
              src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_270/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg"
              className="freeboard_user_profile"
              alt=""
            />
            &nbsp;
            <span className="freeboard_user_name">김승민</span>
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
            <button className="student_submit_btn">답변 등록</button>
          </div>
        </div>
      </div>
      {/* 댓글 리스트들 */}
      <div className="student_freeboard_comment_list">
        <div className="freeboard_written_user_info_box">
          <div className="freeboard_user_info_box">
            <img
              src="https://i.namu.wiki/i/LlmtrVfgkA1KbtazOm8fjmX0dtbFQ3yGHfQbVK4OVfhq9oslyT-OLrflPDgcyZTSqBUKYbjAkAuP8F9c124TGg.webp"
              className="freeboard_user_profile"
              alt=""
            />
            &nbsp;
            <span className="freeboard_user_name">김승민</span>
          </div>
          <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
          <div className="freeboard_comment_modify_box">
            <button className="student_freeboard_detail">수정</button>
            <hr className="devide_button_border" />
            <button className="student_freeboard_detail">삭제</button>
          </div>
        </div>
        <textarea className="freeboard_write_textarea" readOnly>
          sdfdsfdsfdsfdfdsfsdf
        </textarea>
        <div className="freeboard_writedate_box">
          <span className="freeboard_written_date">2024-08-08 15:33</span>
        </div>
      </div>
      {/* 대댓글 리스트 */}
      <div className="freeboard_recoment_list">
        <div className="freeboard_recoment_box">
          <div className="freeboard_written_user_info_box">
            <div className="freeboard_user_info_box">
              <img
                src="https://i.namu.wiki/i/LlmtrVfgkA1KbtazOm8fjmX0dtbFQ3yGHfQbVK4OVfhq9oslyT-OLrflPDgcyZTSqBUKYbjAkAuP8F9c124TGg.webp"
                className="freeboard_user_profile"
                alt=""
              />
              &nbsp;
              <span className="freeboard_user_name">정성진</span>
            </div>
            <i class="bi bi-three-dots-vertical freeboard_three_dot"></i>
            <div className="freeboard_recomment_modify_box">
              <button className="student_freeboard_detail">수정</button>
              <hr className="devide_button_border" />
              <button className="student_freeboard_detail">삭제</button>
            </div>
          </div>
          <textarea className="freeboard_write_textarea" readOnly>
            sdfdsfdsfdsfdfdsfsdf
          </textarea>
          <div className="freeboard_writedate_box">
            <span className="freeboard_written_date">2024-08-08 15:33</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentFreeBoardDetail;
