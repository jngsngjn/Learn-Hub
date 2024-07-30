import React, { useEffect, useState } from "react";
import "./RandomVideo.css";

const RandomVideo = () => {
  const youtubeKey = process.env.REACT_APP_YOUTUBE_API_KEY;
  const query = process.env.REACT_APP_YOUTUBE_QUERY;
  const maxCount = 10;
  const [videoUrl, setVideoUrl] = useState("");

  const getRandomVedioId = (array) => {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array[0];
  };

  const showRandomVideo = async () => {
    try {
      const response = await fetch(
        `https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=${query}&maxResults=${maxCount}&key=${youtubeKey}`
      );
      const data = await response.json();
      const selectedVideo = getRandomVedioId(data.items);
      const videoId = selectedVideo.id.videoId;
      const videoUrl = `https://www.youtube.com/embed/${videoId}`;
      setVideoUrl(videoUrl);
    } catch (err) {
      console.error("YouTube API 가져오기 오류:", err);
    }
  };

  useEffect(() => {
    showRandomVideo();
  }, []);

  return (
    <div className="random_video_container">
      {videoUrl && (
        <div className="iframe_wrapper">
          <iframe
            width="560"
            height="250"
            src={videoUrl}
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowFullScreen
            title="YouTube video"
            className="random_video_iframe"
          ></iframe>
        </div>
      )}
      <img
        src="/images/refresh.png"
        alt="newVideoButton"
        className="get_random_video_btn"
        onClick={showRandomVideo}
      />
    </div>
  );
};

export default RandomVideo;
