import { Box, Flex } from "@chakra-ui/react"
import Sidebar from "../../components/Sidebar/Sidebar"
import { useLocation } from "react-router-dom"

const PageLayout = ({children, isLoggedIn, setIsLoggedIn}) => {
  const {pathname} = useLocation();
  return (
    <Flex>
      {/* sidebar on the left */}
      {pathname !== "/auth" ? (
        <Box w={{base:"70", md:"240px"}}> 
          <Sidebar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
        </Box>
      ) : null}
      
      {/* sidebar on the right */}
      <Box flex={1} w={{ base: "calc(100% -70px", md: "calc(100%-240px)" }}>
        {children}
      </Box>
    </Flex>
  )
}

export default PageLayout
