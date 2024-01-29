import { useState, useEffect } from "react";

const Feature2 = () => {
  const [fitbitRecords, setFitbitRecords] = useState([]);

  useEffect(() => {
    // Fetch data from the Fitbit API or any other external health record provider
    fetch("api/fitbitRecords") // Replace 'api/fitbitRecords' with the actual API endpoint
      .then((response) => response.json())
      .then((data) => setFitbitRecords(data))
      .catch((error) => console.error("Error fetching Fitbit records:", error));
  }, []);

  return (
    <div>
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
    </div>
  );
};

export default Feature2;
