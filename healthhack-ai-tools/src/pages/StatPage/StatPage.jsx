import { Container,Heading } from "@chakra-ui/react";
import { useState, useEffect } from "react";
import { testGraphData, testGraphOptions, graphprops } from "../../../data/testGraphData";
import StatItem  from "./StatItem"
import Recommendation from "./Recommendation";
import GraphPage from "./GraphPage";

const metrics = [{
  "id":1,
  "sysBp": 140,
  "diaBp" : 90,
  "chol" : 5.5,
  "exerciseTime" : 80
}]

const recommendation = {
  heading: "Based on 21 Dec 2023 Medical Checkup and data from your wearable devices",
analysis: [
"You might have a possibility of having diabetes, please go for a diabetes health screening.",
"Your bloodpressure is in a higher range, xx is quite low",
"Overall, you are healthy in general. Your xx is in a higher range, xx is quite low"
],
suggestions: ["Eat less junk food","Drink less coke"]
}

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
      {/* {fitbitRecords.map((record) => (
        <StatItem key={record.id} record={record} />
      ))} */}
      <Recommendation recommendation={recommendation}/>

        <GraphPage graphprops={graphprops} />
      </Container>
    </>
  );
};

export default StatPage