import { Container, Heading, Text, Spinner, Card , Flex, Center} from "@chakra-ui/react";
import { useState, useEffect } from "react";
import {
    testGraphData,
    testGraphOptions,
    graphprops,
} from "../../../data/testGraphData";
import StatItem from "./StatItem";
import Recommendation from "./Recommendation";
import GraphPage from "./GraphPage";
import { mockPrediction } from "../../../data/predictionText";

const metrics = [
    {
        id: 1,
        sysBp: 140,
        diaBp: 90,
        chol: 5.5,
        exerciseTime: 80,
    },
];

const recommendation = {
    heading:
        "Based on 21 Dec 2023 Medical Checkup and data from your wearable devices",
    healthPrediction: [
        "You might have a possibility of having diabetes, please go for a diabetes health screening.",
        "Your bloodpressure is in a higher range, xx is quite low",
        "Overall, you are healthy in general. Your xx is in a higher range, xx is quite low",
    ],
    healthAdvisory: ["Eat less junk food", "Drink less coke"],
};

const StatPage = () => {
    const [prediction, setPrediction] = useState({healthPrediction:"",healthAdvisory:[":: *"]});
    const [isLoading, setIsLoading] = useState();

    useEffect(() => {
        async function getPrediction() {
            try {
              setIsLoading(true);
                console.log("CALLING");
                const response = await fetch(
                    "http://localhost:8080/api/predict/10002"
                );
                const data = await response.json();
                console.log(data);
                setPrediction(data);
            } catch (err) {
                alert("PredictionServer Error");
            } finally {
                setIsLoading(false);
            }
        }
        getPrediction();
    }, []);

    return (
        <>
            <Container maxW={"container.lg"}>
                <Heading as="h2">External Health Records</Heading>
                {/* {fitbitRecords.map((record) => (
        <StatItem key={record.id} record={record} />
      ))} */}

                {isLoading ? (
                    <Card mt="4">

                  <Center p="2">
                        <Heading as="h4" size="md" pr="4">
                            HealthAI is Loading
                        </Heading>
                        <Spinner
                            thickness="4px"
                            speed="0.65s"
                            emptyColor="gray.200"
                            color="blue.500"
                            size="xl"
                        />
                  </Center>

                    </Card>
                ) : (
                    <Recommendation recommendation={prediction} />
                )}
                <GraphPage graphprops={graphprops} />
            </Container>
        </>
    );
};

export default StatPage;
