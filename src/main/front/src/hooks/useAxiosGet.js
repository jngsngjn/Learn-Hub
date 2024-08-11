import { useState, useEffect } from "react";
import axios from "axios";

const useAxiosGet = (url, initialState) => {
  const [data, setData] = useState(initialState);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const accesstoken = localStorage.getItem("access-token");
  const token = accesstoken ? accesstoken.replace("Bearer ", "") : "";

  useEffect(() => {
    setLoading(true);
    axios
      .get(url, {
        headers: {
          // access 야 Authorization이야
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
      });
  }, [url, token]);

  return { data, loading, error };
};

export default useAxiosGet;
