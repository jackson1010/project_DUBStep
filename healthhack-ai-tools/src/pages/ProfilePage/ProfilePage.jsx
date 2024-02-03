import { Container, Flex, Text, VStack, Link } from "@chakra-ui/react";
import { useParams, Link as RouterLink } from "react-router-dom";
import { SkeletonCircle } from "@chakra-ui/react";
import useGetUserProfileByUsername from "./useGetUserProfileByUsername";

const ProfilePage = () => {
  const { username } = useParams();
  const { isLoading, userProfile } = useGetUserProfileByUsername(username);

  const userNotFound = !isLoading && !userProfile;
  if (userNotFound) return <UserNotFound />;
  return (
    <Container maxW="container.lg" py={5}>
      <Flex
        py={10}
        px={4}
        pl={{ base: 4, md: 10 }}
        w={"full"}
        mx={"auto"}
        flexDirection={"column"}
      >
        {userProfile && <UserProfileHeader userProfile={userProfile} />}
        {<UserProfileHeader />}
      </Flex>
      <Flex
        px={{ base: 2, sm: 4 }}
        maxW={"full"}
        mx={"auto"}
        borderTop={"1px solid"}
        borderColor={"whiteAlpha.300"}
        direction={"column"}
      >
        {/* <ProfileTabs /> */}
      </Flex>
    </Container>
  );
};

export default ProfilePage;

const UserProfileHeader = ({ userProfile }) => {
  if (!userProfile) return null; // Add a check for userProfile existence

  const {
    user: { firstName, lastName },
    userProfile: {
      dateOfBirth,
      gender,
      height,
      weight,
      bloodType,
      contactDetails: { address, email, mobileNumber, homeNumber },
    },
  } = userProfile;
  return (
    <Flex
      gap={{ base: 4, sm: 10 }}
      py={10}
      direction={{ base: "column", sm: "row" }}
      justifyContent={"center"}
      alignItems={"center"}
    >
      <SkeletonCircle size="24" />

      <VStack
        alignItems={{ base: "center", sm: "flex-start" }}
        gap={2}
        mx={"auto"}
        flex={1}
      >
         <Text fontSize="lg">{`${firstName} ${lastName}`}</Text>
        <Text>{`Date of Birth: ${dateOfBirth}, Gender: ${gender}`}</Text>
        <Text>{`Height: ${height}, Weight: ${weight}, Blood Type: ${bloodType}`}</Text>
        <Text>{`Address: ${address}`}</Text>
        <Text>{`Email: ${email}`}</Text>
        <Text>{`Mobile Number: ${mobileNumber}, Home Number: ${homeNumber}`}</Text>
      </VStack>
    </Flex>
  );
};

const UserNotFound = () => {
  return (
    <Flex flexDir="column" textAlign={"center"} mx={"auto"}>
      <Text fontSize={"2xl"}>User Not Found</Text>
      <Link
        as={RouterLink}
        to={"/"}
        color={"blue.500"}
        w={"max-content"}
        mx={"auto"}
      >
        Go home
      </Link>
    </Flex>
  );
};
