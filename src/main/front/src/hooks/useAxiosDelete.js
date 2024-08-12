import { useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const useAxiosDelete = (url) => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const accesstoken = localStorage.getItem("access-token");
  const token = accesstoken ? accesstoken.replace("Bearer ", "") : "";
  const navigate = useNavigate();

  const deleteRequest = useCallback(() => {
    setLoading(true);
    return axios
      .delete(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setLoading(false);
        navigate(-1);
        return res.data;
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
        throw err;
      });
  }, [url, token]);

  return { deleteRequest, loading, error };
};

export default useAxiosDelete;
