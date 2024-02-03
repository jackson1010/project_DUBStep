import { Box, Flex, Link, Tooltip } from "@chakra-ui/react";
import { Link as RouterLink, useNavigate } from "react-router-dom";
import {
  HealthAILogo,
  HealthAIMobileLogo,
  HealthReportLogo,
  HealthStatLogo,
  UserProfileLogo,
} from "../../assets/constants";
import { BiLogOut } from "react-icons/bi";

const Sidebar = () => {
  const navigate = useNavigate()

  const isLoggedIn = localStorage.getItem("loginState");

  const SidebarItems = [
    {
      icon: <HealthReportLogo />,
      text: "ExplainToMe",
      link: "/report",
    },
    {
      icon: <HealthStatLogo />,
      text: "HealthyMe",
      link: "/stat",
    },
    {
      icon: <UserProfileLogo />,
      text: "Profile",
      link: "/profile",
    },
  ];
  return (
    <Box
      height={"100vh"}
      borderRight={"1px solid"}
      borderColor={"gray.500"}
      py={8}
      position={"sticky"}
      top={0}
      left={0}
      px={{ base: 2, md: 4 }}
      backgroundColor={"#A7C7E7"}
    >
      <Flex direction={"column"} gap={10} w={"full"} height={"full"}>
        <Link
          to={"/"}
          as={RouterLink}
          pl={2}
          display={{ base: "none", md: "block" }}
          cursor="pointer"
        >
          <HealthAILogo />
        </Link>
        <Link
          to={"/"}
          as={RouterLink}
          pl={2}
          display={{ base: "block", md: "none" }}
          borderRadius={6}
          _hover={{
            bg: "#b5d2f0",
          }}
          w={10}
          cursor="pointer"
        >
          <HealthAIMobileLogo />
        </Link>
        <Flex direction={"column"} gap={5} cursor={"pointer"}>
          {SidebarItems.map((item, index) => (
            <Tooltip
              key={index}
              hasArrow
              label={item.text}
              placement="right"
              ml={1}
              openDelay={500}
              display={{ base: "block", md: "none" }}
            >
              <Link
                display={"flex"}
                to={item.link || null}
                as={RouterLink}
                alignItems={"center"}
                gap={4}
                _hover={{
                  // borderBottom: "2px solid white",
                  backgroundColor: "#b5d2f0",
                  // bg: "gray.500"
                }}
                // borderRadius={6}
                p={2}
                w={{ base: 10, md: "full" }}
                justifyContent={{ base: "center", md: "flex-start" }}
              >
                {item.icon}
                <Box display={{ base: "none", md: "block" }}>{item.text}</Box>
              </Link>
            </Tooltip>
          ))}
        </Flex>

        {/* LOGOUT */}
        <Tooltip
          hasArrow
          label={"Logout"}
          placement="right"
          ml={1}
          openDelay={500}
          display={{ base: "block", md: "none" }}
        >
          <Link
            display={"flex"}
            to={"/auth"}
            as={RouterLink}
            alignItems={"center"}
            gap={4}
            _hover={{ bg: "#b5d2f0" }}
            borderRadius={6}
            p={2}
            w={{ base: 10, md: "full" }}
            mt={"auto"}
            justifyContent={{ base: "center", md: "flex-start" }}
          >
            <BiLogOut size={25} />
            <Box display={{ base: "none", md: "block" }}>{isLoggedIn ? 'Logout' : 'Login/Signup'}</Box>
          </Link>
        </Tooltip>
      </Flex>
    </Box>
  );
};

export default Sidebar;
