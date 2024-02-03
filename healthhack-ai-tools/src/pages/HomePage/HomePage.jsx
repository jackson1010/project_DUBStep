import { Box, Container, Flex } from "@chakra-ui/react";
import { Heading } from "@chakra-ui/react";
import { Text } from "@chakra-ui/react";

import {
    Accordion,
    AccordionItem,
    AccordionButton,
    AccordionPanel,
    AccordionIcon,
} from "@chakra-ui/react";

const HomePage = () => {
    return (
        <>
        <Container maxW={"container.lg"}>
            <Flex gap={20}>
                <Box flex={1} py={10}>
                    <Heading>Welcome to Our Healthcare AI Platform</Heading>
                    <Text>
                        Providing cutting-edge solutions for healthcare
                        innovation and patient care. Utilizing AI for accurate and advanced diagnostics, improving patient outcomes. 
                    </Text>
                    <br />
                    <Heading>We Offer:</Heading>
                    <Accordion>
                        <AccordionItem>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                      Understand Your Health
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                            Our advanced generative AI is trained on the latest medical data to explain complicated medical terms from your medical reports and doctors&apos; notes. When you understand your own body better, you can care for yourself better.
                            </AccordionPanel>
                        </AccordionItem>
                        <AccordionItem>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                        Real-time Patient Monitoring
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                                Monitor your health using your every-day wearables and sync the data to our app! Our advanced metrics AI provices real-time analysis of your physical data, enabling proactive intervention and care.
                            </AccordionPanel>
                        </AccordionItem>
                        <AccordionItem>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                        Personalized Treatment Plans
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                                Our advanced prescriptive AI tailors healthcare plans based your individual data, such as diet and meal plans, weekly cardio and gym programmes, and advised check-ups and treatment options.
                            </AccordionPanel>
                        </AccordionItem>


                    </Accordion>

                    <br />
                    <Heading>Join us in revolutionizing healthcare!</Heading>
                    <Text>
                        Our AI tool is designed to transform the way healthcare
                        is delivered. Embrace innovation, improve patient care,
                        and stay at the forefront of medical technology.
                    </Text>
                </Box>
            </Flex>

        </Container>
        </>
    );
};

export default HomePage;
