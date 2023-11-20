import React from "react";
import AuthService from "../services/auth-service";
import "./Profile.css"; // Import your custom styles

const Profile = () => {
  const currentUser = AuthService.getCurrentUser();

  return (
    <div className="container mt-5">
      <div className="row justify-content-center align-items-center">
        <div className="col-md-6">
          <div className="jumbotron text-center twinkling">
            <h3 className="display-4">
              Welcome, <strong>{currentUser.username}</strong>!
            </h3>
            <p className="lead">This is your profile page.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;


