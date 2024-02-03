import { Box, Flex, Image, Text, VStack } from "@chakra-ui/react";
import { useState } from "react";
import Login from "./Login";
import Signup from "./Signup";

const AuthForm = ({ isLoggedIn, setIsLoggedIn }) => {
	const [showLoginComponent, setShowLoginComponent] = useState(false);

	return (
		<>
			<Box border={"1px solid gray"} borderRadius={4} padding={5}>
				<VStack spacing={4}>
					<Image src='/logo.png' maxH={24} cursor={"pointer"} alt='Healthhack logo' />

					{showLoginComponent ? <Login isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} /> : <Signup isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}

					{/* ---------------- OR -------------- */}
					<Flex alignItems={"center"} justifyContent={"center"} my={4} gap={1} w={"full"}>
						<Box flex={2} h={"1px"} bg={"gray.400"} />
						<Text mx={1} color={"black"}>
							OR
						</Text>
						<Box flex={2} h={"1px"} bg={"gray.400"} />
					</Flex>

					<Flex alignItems={"center"} justifyContent={"center"} cursor={"pointer"}>
						<Image src="/google.png" w={5} alt="Google Logo" />
						<Text mx={2} color={"blue.500"}>
							Log in with Google
						</Text>
					</Flex>
				</VStack>
			</Box>

			<Box border={"1px solid gray"} borderRadius={4} padding={5}>
				<Flex alignItems={"center"} justifyContent={"center"}>
					<Box mx={2} fontSize={14}>
						{showLoginComponent ? "Don't have an account?" : "Already have an account?"}
					</Box>
					<Box onClick={() => setShowLoginComponent(!showLoginComponent)} color={"blue.500"} cursor={"pointer"}>
						{showLoginComponent ? "Sign up" : "Log in"}
					</Box>
				</Flex>
			</Box>
		</>
	);
};

export default AuthForm;
