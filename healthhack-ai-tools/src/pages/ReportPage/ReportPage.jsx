import { Container, Alert, AlertIcon } from "@chakra-ui/react";
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
    doctorName: "Jack Sparrow",
    date: "30 Oct 2020",
    comments: "Ultrasound study of the abdomen was performed. Previous ultrasound study dated 23 January 2020 was reviewed.\n\nIncluded sections of the pancreas show no gross abnormality.\n\nMild increased echogenicity of the liver parenchyma compatible with fatty infiltration. No hepatic lesion detected. The common bile duct measures 0.3cm in diameter and is normal in calibre. The gallbladder is unremarkable.\n\nThe spleen measures 8.8 cm and is normal in size.\n\nConclusion: Mild fatty liver. No hepatic lesion detected.",
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
    <div style={{marginTop: '2rem'}}>
    <Container maxW={"container.lg"}>
      <Container maxW={"container.lg"}>
        <Alert status="info">
          <AlertIcon />
          <div>
          <p style={{marginBottom: '1em'}}>This report should be interpreted in consultation with your doctor. The displayed information is retrieved from NEHR contributors and explanations are AI-generated. It may not contain all the details provided in the report given to you. </p>
          <p>The information on HealthAI is not intended to be a subtitute for the advice of a medical professional. You should never disregard or delay in seeking professional medical advice because of something you read on HealthAI. </p>
          </div>
        </Alert>
      </Container>
      {reports.map((report) => (
        <ReportItem key={report.id} report={report} />
      ))}
    </Container>
    </div>
  );
};

export default ReportPage;
