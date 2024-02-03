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


    const getIndicator = (text) => {
         const title = text.split('::',2);
         return title[0];
    }

    const getText = (text) => {
        const body = text.split('::',2);
        console.log(body[1])
        return body[1].split("*");
   }

    return (
        <div style={{ marginTop: "2rem" }}>
            <Card>
                <CardHeader>
                    <Heading>Your Health Recommendation:</Heading>
                </CardHeader>
                <CardBody paddingTop={"0"}>
                    <Heading size="sm">{recommendation.healthPrediction}</Heading>
                    <Accordion>
                    {recommendation.healthAdvisory.map((text, index) => (
                        <AccordionItem key={index}>
                            <h2>
                                <AccordionButton>
                                    <Box as="span" flex="1" textAlign="left">
                                    {index+1}. {getIndicator(text)}
                                    </Box>
                                    <AccordionIcon />
                                </AccordionButton>
                            </h2>
                            <AccordionPanel pb={4}>
                            {getText(text).map((point, index) => (
                                <Text key={index}> {point}</Text>
                            ))}
                            </AccordionPanel>
                        </AccordionItem>
                        ))}
                    </Accordion>
                </CardBody>
            </Card>
        </div>
    );
};



export default Recommendation;
