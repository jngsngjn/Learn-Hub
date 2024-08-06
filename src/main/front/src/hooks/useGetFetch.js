import { useState, useEffect } from "react";
import axios from "axios";

const useGetFetch = (url, initialState) => {
  const [data, setData] = useState(initialState);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const access = "토큰값을 어디에 저장할지를 아직 미정";

  useEffect(() => {
    setLoading(true); // 요청 시작 시 로딩 상태 true
    axios
      // .get(url, {
      //   headers: {
      //     acceess: `Bearer ${access}`,
      //   },
      // })
      .get("http://localhost:3000/" + url)
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
