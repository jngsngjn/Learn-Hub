// import { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import useGetFetch from "../../hooks/useGetFetch";
// import "./StudentLectureList.css";
// import LectureVideo from "../Teacher/play";

// const StudentLectureList = () => {
//   const navigate = useNavigate();
//   const [thumbnailUrls, setThumbnailUrls] = useState([]);
//   console.log(thumbnailUrls);

//   const { data: subjectVideos, error: subjectVideosError } = useGetFetch(
//     "/data/student/mainLecture/lectureList.json",
//     []
//   );

//   // 사이드바의 과목에서 받아와야하나?
//   const { data: subjectName, error: subjectNameError } = useGetFetch(
//     "/data/student/mainpage/subjectCategory.json",
//     []
//   );

//   useEffect(() => {
//     if (subjectVideos.length > 0) {
//       const urls = subjectVideos.map((el) =>
//         getYouTubeThumbnailUrl(el.youtubeUrl)
//       );
//       setThumbnailUrls(urls);
//     }
//   }, [subjectVideos]);

//   if (subjectNameError || subjectVideosError) {
//     return <div>데이터 로딩에 실패하였습니다.</div>;
//   }

//   const getYouTubeThumbnailUrl = (youtubeUrl) => {
//     const videoId =
//       youtubeUrl.split("v=")[1] || youtubeUrl.split("youtu.be/")[1];
//     if (videoId) {
//       const ampersandPosition = videoId.indexOf("&");
//       if (ampersandPosition !== -1) {
//         return `https://img.youtube.com/vi/${videoId.substring(
//           0,
//           ampersandPosition
//         )}/mqdefault.jpg`;
//       }
//       return `https://img.youtube.com/vi/${videoId}/mqdefault.jpg`;
//     }
//     return null;
//   };

//   return (
//     <div className="subject_lecture_list_body">
//       <div className="subject_lecture_list_main_container">
//         <div className="subject_lecture_list_page_title_box">
//           <h1 className="subject_lecture_list_page_title">강의 영상</h1>
//           <select className="subject_lecture_select_box">
//             <option value="all" selected>
//               전체
//             </option>
//             {subjectName.map((el) => (
//               <option value={`option_name_${el.id}`} key={el.id}>
//                 {el.name}
//               </option>
//             ))}
//           </select>
//         </div>
//         <div className="subject_lecture_list_container">
//           {subjectVideos.slice(0, 15).map((el, idx) => (
//             <div className="subject_lecture_list_content" key={idx}>
//               <img
//                 className="subject_lecture_list_image"
//                 alt="과목이미지"
//                 src={thumbnailUrls[idx]}
//               />
//               <h1
//                 className="subject_lecture_list_title"
//                 onClick={() =>
//                   navigate(
//                     `/students/${el.subjectName}/lecture/${el.lectureId}`
//                   )
//                 }
//               >
//                 {el.title}
//               </h1>
//               <p className="subject_lecture_list_description">{el.content}</p>
//             </div>
//           ))}
//         </div>
//       </div>
//       <LectureVideo />
//     </div>
//   );
// };

// export default StudentLectureList;

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useGetFetch from "../../hooks/useGetFetch";
import "./StudentLectureList.css";
import LectureVideo from "../Teacher/play";
import StudentVideoModal from "../../components/Modal/StudentModal/StudentVideoModal";

const StudentLectureList = () => {
  const navigate = useNavigate();
  const [thumbnailUrls, setThumbnailUrls] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedVideoUrl, setSelectedVideoUrl] = useState("");

  const { data: subjectVideos, error: subjectVideosError } = useGetFetch(
    "/data/student/mainLecture/lectureList.json",
    []
  );

  const { data: subjectName, error: subjectNameError } = useGetFetch(
    "/data/student/mainpage/subjectCategory.json",
    []
  );

  useEffect(() => {
    if (subjectVideos.length > 0) {
      const urls = subjectVideos.map((el) =>
        getYouTubeThumbnailUrl(el.youtubeUrl)
      );
      setThumbnailUrls(urls);
    }
  }, [subjectVideos]);

  const getYouTubeThumbnailUrl = (youtubeUrl) => {
    const videoId =
      youtubeUrl.split("v=")[1] || youtubeUrl.split("youtu.be/")[1];
    if (videoId) {
      const ampersandPosition = videoId.indexOf("&");
      if (ampersandPosition !== -1) {
        return `https://img.youtube.com/vi/${videoId.substring(
          0,
          ampersandPosition
        )}/mqdefault.jpg`;
      }
      return `https://img.youtube.com/vi/${videoId}/mqdefault.jpg`;
    }
    return null;
  };

  const openModal = (youtubeUrl) => {
    setSelectedVideoUrl(youtubeUrl);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedVideoUrl("");
  };

  if (subjectNameError || subjectVideosError) {
    return <div>데이터 로딩에 실패하였습니다.</div>;
  }

  return (
    <div className="subject_lecture_list_body">
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
            <div
              className="subject_lecture_list_content"
              key={idx}
              onClick={() => openModal(el.youtubeUrl)}
            >
              <img
                className="subject_lecture_list_image"
                alt="과목이미지"
                src={thumbnailUrls[idx]}
              />
              <h1 className="subject_lecture_list_title">{el.title}</h1>
              <p className="subject_lecture_list_description">{el.content}</p>
            </div>
          ))}
        </div>
      </div>
      <StudentVideoModal isOpen={isModalOpen} onClose={closeModal}>
        <LectureVideo url={selectedVideoUrl} />
      </StudentVideoModal>
    </div>
  );
};

export default StudentLectureList;
