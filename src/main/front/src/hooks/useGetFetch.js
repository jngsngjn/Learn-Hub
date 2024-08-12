import { useState, useEffect } from "react";
import axios from "axios";

const useGetFetch = (url, initialState) => {
  const [data, setData] = useState(initialState);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const accesstoken = localStorage.getItem("access-token");

  const token = accesstoken.replace("Bearer ", "");

  useEffect(() => {
    setLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + url, {
        headers: {
          token: token,
        },
      })
      // .get(process.env.REACT_APP_BASE_URL + url)
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
      });
  }, [url]);

  return { data, loading, error };
};

export default useGetFetch;
