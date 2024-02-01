import React from "react";

const BASE_URL = "https://api.example.com"; // Replace with your actual API base URL

export const useGetUserProfileByUsername = (username) => {
  const [userProfile, setUserProfile] = React.useState(null);
  const [isLoading, setIsLoading] = React.useState(true);
  const [error, setError] = React.useState(null);

  React.useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await fetch(`${BASE_URL}/user-profile/${username}`);
        if (!response.ok) {
          throw new Error("Failed to fetch user profile");
        }
        const data = await response.json();
        setUserProfile(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchUserProfile();
  }, [username]);

  return { isLoading, userProfile, error };
};

export default useGetUserProfileByUsername
