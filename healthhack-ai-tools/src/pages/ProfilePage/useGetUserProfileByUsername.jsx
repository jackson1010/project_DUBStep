import React from "react";

const BASE_URL = "http://localhost:8080/api/auth"; // Replace with your actual API base URL

export const useGetUserProfileByUsername = (username) => {
  const [userProfile, setUserProfile] = React.useState(null);
  const [error, setError] = React.useState(null);

  React.useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await fetch(`${BASE_URL}/profile/${username}`);
        if (!response.ok) {
          throw new Error("Failed to fetch user profile");
        }
        const data = await response.json();
        setUserProfile(data);
      } catch (error) {
        setError(error.message);
      } 
    };

    fetchUserProfile();
  }, [username]);

  return { userProfile, error };
};

export default useGetUserProfileByUsername
