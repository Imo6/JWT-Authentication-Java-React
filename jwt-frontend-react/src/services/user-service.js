import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/auth";

const getPublicContent = () => {
  return axios.get(API_URL + "/welcome");
};

const getUserBoard = () => {
  return axios.get(API_URL + "/user/userProfile", { headers: authHeader() });
};


const UserService = {
  getPublicContent,
  getUserBoard
};

export default UserService;
