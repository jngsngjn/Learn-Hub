import React, { useState, useEffect } from "react";
import "./LectureVideo.css";

const LectureVideo = ({ width, height }) => {
  const [links, setLinks] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
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
    return <p>잘못된 링크로 비디오를 찾을 수 없습니다.</p>;
  }

  const videoId = extractVideoId(links);

  return (
    <div className="lecture_container" style={{ position: "relative" }}>
      <iframe
        id="player"
        width={width}
        height={height}
        src={`https://www.youtube.com/embed/${videoId}?enablejsapi=1&modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0&playsinline=1`}
        title="YouTube video player"
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
      ></iframe>

      {/* <div className="hide_youtuebe_title_box">제목: 우어어어어어어엉</div>
      <div className="hide_youtuebe_bottom_box"></div> */}
    </div>
  );
};

export default LectureVideo;
