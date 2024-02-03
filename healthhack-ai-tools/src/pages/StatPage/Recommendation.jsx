import {
    Card,
    CardBody,
    CardHeader,
    Text,
    Heading,
    Accordion,
    AccordionItem,
    AccordionButton,
    AccordionIcon,
    AccordionPanel,
    Box,
} from "@chakra-ui/react";

const Recommendation = ({ recommendation }) => {
    return (
        <div style={{ marginTop: "2rem" }}>
            <Card>
                <CardHeader>
                    <Heading>Your Health Recommendation:</Heading>
                </CardHeader>
                <CardBody paddingTop={"0"}>
                    <Heading size="sm">{recommendation.heading}:</Heading>
                    <Accordion>
                        <AccordionItem>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                        Our Analysis:
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                                {recommendation.analysis.map((text, index) => (
                                    <Text key={index}>{index+1}. {text}</Text>
                                ))}
                            </AccordionPanel>
                        </AccordionItem>
                        <AccordionItem>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                        Our Recommendations:
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                            {recommendation.suggestions.map((text, index) => (
                                    <Text key={index}>{index+1}. {text}</Text>
                                ))}
                            </AccordionPanel>
                        </AccordionItem>
                    </Accordion>
                </CardBody>
            </Card>
        </div>
    );
};

export default Recommendation;
