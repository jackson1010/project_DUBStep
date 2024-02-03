import { Container } from "@chakra-ui/react";
import { useState, useEffect } from "react";
import { BarGraph } from "../../components/Graph/BarGraph";
import { testGraphData, testGraphOptions } from "../../../data/testGraphData";

const StatPage = () => {
  const [fitbitRecords, setFitbitRecords] = useState([]);

  useEffect(() => {
    // Fetch data from the Fitbit API or any other external health record provider
    fetch("api/fitbitRecords") // Replace 'api/fitbitRecords' with the actual API endpoint
      .then((response) => response.json())
      .then((data) => setFitbitRecords(data))
      .catch((error) => console.error("Error fetching Fitbit records:", error));
  }, []);

  return (
    <>
      <Container>
      <h1>External Health Records</h1>
      <ul>
        {fitbitRecords.map((record) => (
          <li key={record.id}>
            <p>Date: {record.date}</p>
            <p>Steps: {record.steps}</p>
            {/* Add more details based on Fitbit or other app data */}
          </li>
        ))}
      </ul>
      </Container>
      <Container maxW='3xl' position="relative" centerContent>
        <BarGraph chartData={testGraphData} chartOptions={testGraphOptions}/>
      </Container>
    </>
  );
};

export default StatPage