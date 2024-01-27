import { Box, Container, Flex } from "@chakra-ui/react";

const HomePage = () => {
  return (
    <Container maxW={"container.lg"}>
      <Flex gap={20}>
        <Box flex={2} py={10} border={"1px solid blue"}>
          <h1>Welcome to Our Healthcare AI Platform</h1>
          <p>
            Providing cutting-edge solutions for healthcare innovation and patient care.
          </p>
          <h2>Advanced Diagnostics</h2>
            <p>
              Utilize our AI tool for accurate and advanced diagnostics, improving patient outcomes.
            </p>
            <h2>Personalized Treatment Plans</h2>
            <p>
              Tailor treatment plans based on individual patient data, ensuring personalized care.
            </p>
            <h2>Real-time Patient Monitoring</h2>
            <p>
              Monitor patient health in real-time, enabling proactive intervention and care.
            </p>
            <p>Join us in revolutionizing healthcare!</p>
          <p>
            Our AI tool is designed to transform the way healthcare is delivered. Embrace innovation,
            improve patient care, and stay at the forefront of medical technology.
          </p>
        </Box>

        <Box flex={3} mr={20} display={{base:"none", lg:"block"}} maxW={"300px"} border={"1px solid red"}>
          Treatment History
        </Box>
      </Flex>
    </Container>
  );
};

export default HomePage;
