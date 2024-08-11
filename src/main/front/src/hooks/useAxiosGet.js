import { useState, useEffect } from "react";
import axios from "axios";

const useAxiosGet = (url, initialState) => {
  const [data, setData] = useState(initialState);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const accesstoken = localStorage.getItem("access-token");
  console.log(accesstoken);
  const token = accesstoken.replace("Bearer ", "");
  console.log(token);

  useEffect(() => {
    setLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL2 + url, {
        headers: {
          token: token,
        },
      })
      .then((res) => {
        console.log(res);
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

export default useAxiosGet;
