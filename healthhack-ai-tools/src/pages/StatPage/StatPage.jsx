import { Container,Heading } from "@chakra-ui/react";
import { useState, useEffect } from "react";
import { BarGraph } from "../../components/Graph/BarGraph";
import { testGraphData, testGraphOptions } from "../../../data/testGraphData";
import StatItem  from "./StatItem"

const metrics = [{
  "id":1,
  "sysBp": 140,
  "diaBp" : 90,
  "chol" : 5.5,
  "exerciseTime" : 80
}]


const StatPage = () => {
  const [fitbitRecords, setFitbitRecords] = useState(metrics);

  // useEffect(() => {
  //   // Fetch data from the Fitbit API or any other external health record provider
  //   fetch("api/fitbitRecords") // Replace 'api/fitbitRecords' with the actual API endpoint
  //     .then((response) => response.json())
  //     .then((data) => setFitbitRecords(data))
  //     .catch((error) => console.error("Error fetching Fitbit records:", error));
  // }, []);


  return (
    <>
      <Container maxW={"container.lg"}>
      <Heading as='h2'>External Health Records</Heading>
      <Container maxW={"container.lg"}>
      {fitbitRecords.map((record) => (
        <StatItem key={record.id} record={record} />
      ))}
    </Container>
      </Container>
      <Container maxW={"container.lg"} position="relative" centerContent>
        <BarGraph chartData={testGraphData} chartOptions={testGraphOptions}/>
      </Container>
    </>
  );
};

export default StatPage