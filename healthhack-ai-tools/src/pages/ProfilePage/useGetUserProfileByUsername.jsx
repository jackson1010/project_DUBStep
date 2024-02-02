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

        // Assuming the API response structure has a 'userProfile' property
        // Modify this part according to your actual API response structure
        const userProfileData = {
          userId: data.userId,
          userProfile: {
            userId: data.userId,
            firstName: data.userProfile.firstName,
            lastName: data.userProfile.lastName,
            dateOfBirth: data.userProfile.dateOfBirth,
            gender: data.userProfile.gender,
            height: data.userProfile.height,
            weight: data.userProfile.weight,
            bloodType: data.userProfile.bloodType,
          },
          contactDetails: {
            userId: data.userId,
            address: data.contactDetails.address,
            email: data.contactDetails.email,
            mobileNumber: data.contactDetails.mobileNumber,
            homeNumber: data.contactDetails.homeNumber,
          },
        };

        setUserProfile(userProfileData);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchUserProfile();
  }, [username]);

  return { userProfile, error };
};

export default useGetUserProfileByUsername;