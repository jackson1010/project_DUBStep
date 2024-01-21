import { Box, Flex, Link } from "@chakra-ui/react"
import { Link as RouterLink } from "react-router-dom"
import { HealthAILogo, HealthAIMobileLogo } from "../../assets/constants"

const Sidebar = () => {
  return (
    <Box 
        height={"100vh"}
        borderRight={"1px solid"}
        borderColor={"gray.500"}
        py={8}
        position={"sticky"}
        top={0}
        left={0}
        px={{base:2, md:4}}
    >
        <Flex direction={"column"} gap={10} w={"full"} height={"full"}>
            <Link to={"/"} as={RouterLink} pl={2} display={{base:"none", md:"block"}} cursor="pointer">
                <HealthAILogo />
            </Link>
            <Link to={"/"} as={RouterLink} pl={2} display={{base:"block", md:"none"}} cursor="pointer">
                <HealthAIMobileLogo />
            </Link>
        </Flex>
    </Box>
  )
}

export default Sidebar
