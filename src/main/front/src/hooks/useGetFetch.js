import { useState, useEffect } from "react";
import axios from "axios";

const useGetFetch = (url, initialState) => {
  const [data, setData] = useState(initialState);
  const [error, setError] = useState(null);
  const access = "토큰값을 어디에 저장할지를 아직 미정";

  useEffect(() => {
    axios
      // .get( url, {
      //   headers: {
      //     acceess: `Bearer ${access}`,
      //   },
      // })
      .get("http://localhost:3000/" +url)
      .then((res) => {
        setData(res.data);
      })
      .catch((err) => {
        setError(err);
      });
  }, [url]);

  return { data, error };
};

export default useGetFetch;
