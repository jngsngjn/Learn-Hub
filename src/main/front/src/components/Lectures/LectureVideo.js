import React, { useState, useEffect } from "react";
import "./LectureVideo.css";

const LectureVideo = () => {
  const [lectureUrl, setLectureUrl] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // const token = "";
    fetch("/data/student/subLectureVideo.json", {
      method: "GET",
      // headers: {
      //   "Content-Type": "application/json;charset=utf-8",
      //   Authorization: `Bearer ${token}`,
      // },
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Network response was not ok");
        }
        console.log(res);
        return res.json();
      })
      .then((data) => {
        setLectureUrl(data.youtubeLink);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
        setLoading(false);
      });
  }, []);

  const extractVideoId = (url) => {
    const match = url.match(
      /(?:https?:\/\/)?(?:www\.)?youtube\.com\/(?:watch\?v=|embed\/|v\/|.+\?v=|video\/|playlist\?list=)?([a-zA-Z0-9_-]{11})|youtu\.be\/([a-zA-Z0-9_-]{11})/
    );
    return match ? match[1] || match[2] : null;
  };

  const videoId = extractVideoId(lectureUrl);

  if (loading) {
    return <p>비디오 로딩 중...</p>;
  }

  if (error) {
    return <p>오류 발생: {error.message}</p>;
  }

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
