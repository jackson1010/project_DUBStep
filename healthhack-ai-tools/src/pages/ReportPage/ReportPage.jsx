import { Container } from "@chakra-ui/react";
import ReportItem from "./ReportItem";

const reports = [
  {
    title: "Report 1",
    date: "21 Dec 2023",
    comments: "Lalalalaa",
  },
  {
    title: "Report 2",
    date: "1 Jun 2021",
    comments: "Hahahahaaaa",
  },
  {
    title: "Report 3",
    date: "30 Oct 2020",
    comments: "Babababaaa",
  },
];
const ReportPage = () => {
  return (
    <Container maxW={"container.lg"}>
      {reports.map((report) => (
        <ReportItem key={report.title} report={report} />
      ))}
    </Container>
  );
};

export default ReportPage;
