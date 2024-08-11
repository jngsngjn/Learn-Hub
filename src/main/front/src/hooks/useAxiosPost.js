import { useState } from "react";
import axios from "axios";

const useAxiosPost = (url) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const accesstoken = localStorage.getItem("access-token");
  const token = accesstoken ? accesstoken.replace("Bearer ", "") : "";

  const postRequest = async (commentData) => {
    setLoading(true);
    try {
      const response = await axios.post(url, commentData, {
        headers: {
          access: `Bearer ${token}`,
        },
      });
      setData(response.data);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return { data, loading, error, postRequest };
};

export default useAxiosPost;
