import { useState, useEffect } from "react";

const Feature1 = () => {
  const [healthRecords, setHealthRecords] = useState([]);

  useEffect(() => {
    // Fetch data from the server (replace 'api/healthRecords' with your actual API endpoint)
    fetch("api/healthRecords")
      .then((response) => response.json())
      .then((data) => setHealthRecords(data))
      .catch((error) => console.error("Error fetching health records:", error));
  }, []);

  return (
    <div>
      <h1>Personal Health Records</h1>
      <ul>
        {healthRecords.map((record) => (
          <li key={record.id}>
            <a href={record.pdfUrl} target="_blank" rel="noopener noreferrer">
              {record.patientName} Health Record
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Feature1;
