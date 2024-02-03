import { Container } from "@chakra-ui/react";
import ReportItem from "./ReportItem";
import { useState, useEffect } from "react";

const reports = [
  {
    title: "Report 1",
    date: "21 Dec 2023",
    comments: "Summary of Report 1...",
  },
  {
    title: "Report 2",
    date: "1 Jun 2021",
    comments: "Summary of Report 2...",
  },
  {
    title: "Report 3",
    date: "30 Oct 2020",
    comments: "Summary of Report 3...",
  },
];
const ReportPage = () => {
  // const [reports, setReports] = useState([]);
  const [test, setTest] = useState("hello this is the default info");

  useEffect(() => {
    fetch('http://localhost:8080/api/test/get', {
      method: 'GET',
    })
      .then(response => console.log(response))
      .catch(error => console.error(error));  
  })

  return (
    <Container maxW={"container.lg"}>
      {reports.map((report) => (
        <ReportItem key={report.title} report={report} />
      ))}
    </Container>
  );
};

export default ReportPage;
