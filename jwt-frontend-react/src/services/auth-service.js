import axios from "axios";

const API_URL = "http://localhost:8080/auth/addNewUser";
const API_URL1 = "http://localhost:8080/auth/generateToken";

const register = (username, email, password,roles) => {
  return axios.post(API_URL , {
    username,
    email,
    password,
    roles
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL1 , {
      username,
      password,
    })
    .then((response) => {
      if (response.data) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;