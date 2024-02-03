import {
    Card,
    CardBody,
    CardHeader,
    Heading,
    Accordion,
    AccordionItem,
    AccordionButton,
    AccordionIcon,
    AccordionPanel,
    Box,
} from "@chakra-ui/react";
import { BarGraph } from "../../components/Graph/BarGraph";

const GraphPage = ({graphprops}) => {
    return (
        <div style={{ marginTop: "2rem" }} >
            <Card minW="lg" width="100%" p={4} >
                <CardHeader>
                    <Heading>Your Health Metrics:</Heading>
                </CardHeader>
                <CardBody paddingTop={"0"}>
                    <Accordion>
                        {graphprops.map((graph, index) => (
                            <AccordionItem key={index}>
                                <h2>
                                    <AccordionButton>
                                        <Box
                                            as="span"
                                            flex="1"
                                            textAlign="left"
                                        >
                                            {graph.options.plugins.title.text}
                                        </Box>
                                        <AccordionIcon />
                                    </AccordionButton>
                                </h2>
                                <AccordionPanel maxH="xs">
                                    <BarGraph
                                        chartData={graph.data}
                                        chartOptions={graph.chartOptions}
                                    />
                                </AccordionPanel>
                            </AccordionItem>
                        ))}
                    </Accordion>
                </CardBody>
            </Card>
        </div>
    );
};

export default GraphPage;
