import React, { useState, useEffect } from "react";
import "./LectureVideo.css";

const LectureVideo = () => {
  const [links, setLinks] = useState("");
  const [subLecture, setSubLecture] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // fetch("http://localhost:8080/test") // 여기에 백이랑 통신시 사용한 코드는 주석처리
    fetch("/data/student/mainpage/lectureVideo.json")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Network response was not ok");
        }
        return res.json();
      })
      .then((data) => {
        if (data && typeof data.link === "string") {
          setLinks(data.link);
        } else {
          throw new Error("Invalid data format");
        }
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
        setLoading(false);
      });
  }, []);

  const extractVideoId = (link) => {
    const match = link.match(
      /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/
    );
    return match ? match[1] : null;
  };

  if (loading) {
    return <p>비디오 로딩 중...</p>;
  }

  if (error) {
    return <p>오류 발생: {error.message}</p>;
  }

  if (!links) {
    return <p>비디오를 찾을 수 없습니다.</p>;
  }

  const videoId = extractVideoId(links);

  return (
    <div className="lecture_container">
      {videoId ? (
        <div className="iframe_wrapper">
          <iframe
            width="560"
            height="315"
            src={`https://www.youtube.com/embed/${videoId}`}
            title="YouTube video player"
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowFullScreen
          ></iframe>
        </div>
      ) : (
        <p>비디오를 찾을 수 없습니다.</p>
      )}
    </div>
  );
};

export default LectureVideo;
