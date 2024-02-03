import { Container } from "@chakra-ui/react";
import ReportItem from "./ReportItem";
import { useState, useEffect } from "react";

const sampleReports = [
  {
    id: 1,
    doctorName: "Diamond Mao",
    date: "21 Dec 2023",
    comments: "The liver surface appears smooth. Its parenchyma shows normal echogenicity. No suspicious hepatic lesion is detected. The gallbladder wall appears normal and Murphy sign is negative. No gallstone is noted. The biliary tree is not dilated and the common duct measures 0.3 cm. The spleen is normal in length (9.6 cm) and no focal splenic lesion is seen. The imaged pancreas is unremarkable.",
  },
  {
    id: 2,
    doctorName: "Diamond Mao",
    date: "1 Jun 2021",
    comments: "LUMBOSACRAL SPINE (AP, both oblique and lateral views) \n\nThere is spondylolysis demonstrated on the right side at the L5 level. The left L5 pars interarticularis also shows some slight deformity but no discrete defect. The rest of the right and left pars interarticularis appear intact.  \n\nThere is loss of the normal lumbar lordosis, no subluxation or spondylolisthesis is evident. \n\nThe intervertebral disc spaces and bone density are preserved. No sacroilitis is seen.",
  },
  {
    id: 3,
    doctorName: "Green Rabbit",
    date: "30 Oct 2020",
    comments: "Summary of Report 3...",
  },
];
const ReportPage = () => {
  const [reports, setReports] = useState(sampleReports || []);

  useEffect(() => {
    // Fetch data from the server (replace 'api/healthRecords' with your actual API endpoint)
    fetch("api/reports/getAll/" + localStorage.getItem('userId'), {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then((response) => response.json())
      .then((response) => console.log(response))
      .catch((error) => console.error("Error fetching health records:", error));
  }, []);

  return (
    <Container maxW={"container.lg"}>
      {reports.map((report) => (
        <ReportItem key={report.id} report={report} />
      ))}
    </Container>
  );
};

export default ReportPage;
