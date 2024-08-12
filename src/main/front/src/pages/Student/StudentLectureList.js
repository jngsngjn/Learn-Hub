// import React, { useEffect, useState } from "react";

// import "./StudentLectureList.css";
// import LectureVideo from "../Teacher/play";
// import StudentVideoModal from "../../components/Modal/StudentModal/StudentVideoModal";
// import useAxiosGet from "../../hooks/useAxiosGet";

// const StudentLectureList = () => {
//   const [thumbnailUrls, setThumbnailUrls] = useState([]);
//   const [isModalOpen, setIsModalOpen] = useState(false);
//   const [selectedVideoUrl, setSelectedVideoUrl] = useState("");
//   console.log(selectedVideoUrl);

//   const { data: subjectVideosData, error: subjectVideosError } = useAxiosGet(
//     "/students/lectures",
//     {}
//   );

//   const subjectVideos = subjectVideosData.content || [];

//   const { data: subjectName, error: subjectNameError } = useAxiosGet(
//     "/students/lectures/subject-select",
//     []
//   );

//   useEffect(() => {
//     if (subjectVideos.length > 0) {
//       const urls = subjectVideos.map((el) => getYouTubeThumbnailUrl(el.link));
//       setThumbnailUrls(urls);
//     }
//   }, [subjectVideos]);

//   useEffect(() => {
//     const selected = document.querySelector(".select-selected");
//     const items = document.querySelector(".select-items");

//     const handleSelectClick = () => {
//       items.classList.toggle("select-hide");
//     };

//     const handleItemClick = (e) => {
//       if (e.target.tagName === "DIV") {
//         selected.innerHTML = e.target.innerHTML;
//         items.classList.add("select-hide");
//         console.log(`Selected value: ${e.target.dataset.value}`);
//       }
//     };

//     const handleClickOutside = (e) => {
//       if (!e.target.matches(".select-selected")) {
//         const dropdowns = document.querySelectorAll(".select-items");
//         dropdowns.forEach((dropdown) => {
//           dropdown.classList.add("select-hide");
//         });
//       }
//     };

//     selected.addEventListener("click", handleSelectClick);
//     items.addEventListener("click", handleItemClick);
//     document.addEventListener("click", handleClickOutside);

//     return () => {
//       selected.removeEventListener("click", handleSelectClick);
//       items.removeEventListener("click", handleItemClick);
//       document.removeEventListener("click", handleClickOutside);
//     };
//   }, [subjectName]);

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

//   const openModal = (youtubeUrl) => {
//     setSelectedVideoUrl(youtubeUrl);
//     setIsModalOpen(true);
//   };

//   const closeModal = () => {
//     setIsModalOpen(false);
//     setSelectedVideoUrl("");
//   };

//   if (subjectNameError || subjectVideosError) {
//     return <div>데이터 로딩에 실패하였습니다.</div>;
//   }

//   return (
//     <div className="subject_lecture_list_body">
//       <div className="subject_lecture_list_main_container">
//         <div className="subject_lecture_list_page_title_box">
//           <h1 className="subject_lecture_list_page_title">강의 영상</h1>
//           <div className="custom-select">
//             <div className="select-selected">전체</div>
//             <div className="select-items select-hide">
//               <div data-value="all">전체</div>
//               {subjectName.map((el) => (
//                 <div data-value={el.subjectId} key={el.subjectId}>
//                   {el.name}
//                 </div>
//               ))}
//             </div>
//           </div>
//         </div>
//         <div className="subject_lecture_list_container">
//           {subjectVideos.map((el, idx) => (
//             <div
//               className="subject_lecture_list_content"
//               key={idx}
//               onClick={() => openModal(el.link)}
//             >
//               <img
//                 className="subject_lecture_list_image"
//                 alt="과목이미지"
//                 src={thumbnailUrls[idx]}
//               />
//               <h1 className="subject_lecture_list_title">{el.title}</h1>
//               <p className="subject_lecture_list_description">{el.content}</p>
//             </div>
//           ))}
//         </div>
//       </div>
//       <StudentVideoModal isOpen={isModalOpen} onClose={closeModal}>
//         <LectureVideo url={selectedVideoUrl} />
//       </StudentVideoModal>
//     </div>
//   );
// };

// export default StudentLectureList;

import React, { useEffect, useState } from "react";

import "./StudentLectureList.css";
import LectureVideo from "../Teacher/play";
import StudentVideoModal from "../../components/Modal/StudentModal/StudentVideoModal";
import useAxiosGet from "../../hooks/useAxiosGet";

const StudentLectureList = () => {
  const [thumbnailUrls, setThumbnailUrls] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedVideoUrl, setSelectedVideoUrl] = useState("");
  const [selectedLectureId, setSelectedLectureId] = useState("all"); // State for selected lectureId
  console.log(selectedVideoUrl);

  const { data: subjectVideosData, error: subjectVideosError } = useAxiosGet(
    "/students/lectures",
    {}
  );

  console.log(subjectVideosData);

  const subjectVideos = subjectVideosData.content || [];

  const { data: subjectName, error: subjectNameError } = useAxiosGet(
    "/students/lectures/subject-select",
    []
  );

  useEffect(() => {
    if (subjectVideos.length > 0) {
      const urls = subjectVideos.map((el) => getYouTubeThumbnailUrl(el.link));
      setThumbnailUrls(urls);
    }
  }, [subjectVideos]);

  useEffect(() => {
    const selected = document.querySelector(".select-selected");
    const items = document.querySelector(".select-items");

    const handleSelectClick = () => {
      items.classList.toggle("select-hide");
    };

    const handleItemClick = (e) => {
      if (e.target.tagName === "DIV") {
        selected.innerHTML = e.target.innerHTML;
        items.classList.add("select-hide");
        const selectedId = e.target.dataset.value;
        setSelectedLectureId(selectedId); // Update selectedLectureId
        console.log(`Selected value: ${selectedId}`);
      }
    };

    const handleClickOutside = (e) => {
      if (!e.target.matches(".select-selected")) {
        const dropdowns = document.querySelectorAll(".select-items");
        dropdowns.forEach((dropdown) => {
          dropdown.classList.add("select-hide");
        });
      }
    };

    selected.addEventListener("click", handleSelectClick);
    items.addEventListener("click", handleItemClick);
    document.addEventListener("click", handleClickOutside);

    return () => {
      selected.removeEventListener("click", handleSelectClick);
      items.removeEventListener("click", handleItemClick);
      document.removeEventListener("click", handleClickOutside);
    };
  }, [subjectName]);

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
    //아래는 임시방편 (해결하면 지울 예정)
    window.location.reload();
  };

  if (subjectNameError || subjectVideosError) {
    return <div>데이터 로딩에 실패하였습니다.</div>;
  }

  const filteredVideos =
    selectedLectureId === "all"
      ? subjectVideos
      : subjectVideos.filter((el) => el.lectureId === selectedLectureId);

  return (
    <div className="subject_lecture_list_body">
      <div className="subject_lecture_list_main_container">
        <div className="subject_lecture_list_page_title_box">
          <h1 className="subject_lecture_list_page_title">강의 영상</h1>
          <div className="custom-select">
            <div className="select-selected">전체</div>
            <div className="select-items select-hide">
              <div data-value="all">전체</div>
              {subjectName.map((el) => (
                <div data-value={el.subjectId} key={el.subjectId}>
                  {el.name}
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="subject_lecture_list_container">
          {filteredVideos.map((el, idx) => (
            <div
              className="subject_lecture_list_content"
              key={idx}
              onClick={() => openModal(el.link)}
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
