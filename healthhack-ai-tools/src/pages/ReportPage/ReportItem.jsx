import {
  Card,
  CardBody,
  CardHeader,
  Heading,
  Button,
  Text,
  Flex,
  Spacer,
  Container,
  Spinner,
  Accordion,
  AccordionItem,
  AccordionButton,
  AccordionPanel,
  AccordionIcon,
  Box,
} from "@chakra-ui/react";
import { ChevronRightIcon } from "@chakra-ui/icons";
import { useState } from "react";

const defaultReport = {
  doctorName: "Lung X Ray",
  date: "24 Jan 2023",
  comments: "Some doctor's commment here",
};

const ReportItem = (_props) => {
  const report = _props.report || defaultReport;
  const [showDetails, setShowDetails] = useState(false);
  const [explanations, setExplanations] = useState([]);
  const [detailedExplanations, setDetailedExplanations] = useState([]);

  const toggleShowDetails = () => {
    showDetails ? setShowDetails(false) : setShowDetails(true);
    if (explanations.length == 0) {
      fetch("http://localhost:8080/api/gpt/" + localStorage.getItem("userId"), {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          query: report.comments,
          number: 1,
        })
      }).then(response => response.text())
        .then(response => {
          console.log(response)
          let tempArr = explanations;
          tempArr.push(...response.split("\n"));
          setExplanations([...tempArr]);
        })
        .catch(error => console.log("good luck with this: ", error))
    }
  };

  const getMoreDetails = (point) => {
    if (!detailedExplanations.find(d => d.point === point)) {
      fetch("http://localhost:8080/api/gpt/" + localStorage.getItem("userId"), {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          query: point,
          number: 2,
        })
      }).then(response => response.text())
        .then(response => {
          let tempArr = detailedExplanations;
          tempArr.push({ 'point': point, 'details': response });
          setDetailedExplanations([...tempArr]);
        })
        .catch(error => console.log("good luck with this: ", error))
    }
  }

  return (
    <div style={{ marginTop: "2rem" }}>
      <Card>
        <CardHeader>
          <Flex>
            <div>
              <Heading size="md">{report.date}</Heading>
              <Text>{'Ordered by: ' + report.doctorName}</Text>
            </div>
            <Spacer />
            <Button variant="outline" onClick={toggleShowDetails}>
              {showDetails ? "Hide explanation" : "Help me understand"} &nbsp;
              <ChevronRightIcon />
            </Button>
          </Flex>
        </CardHeader>
        <CardBody paddingTop={"0"}>
          <Text>{report.comments}</Text>
          {showDetails && (explanations.length > 0 ? (
            <div className="gptSection">
              <Accordion>
                {explanations.map((point, index) =>
                  <AccordionItem key={index}>
                    <h2>
                      <AccordionButton onClick={() => getMoreDetails(point)}>
                        <Box as="span" flex="1" textAlign="left">
                          {point}
                        </Box>
                        <AccordionIcon />
                      </AccordionButton>
                    </h2>
                    <AccordionPanel pb={4}>
                      {
                        detailedExplanations.find(d => d.point === point) ?
                          <ReportExplanation points={detailedExplanations.find(d => d.point === point).details} />
                          :
                          <div className="spinner">
                            <Spinner size="sm" color="teal" />
                          </div>
                      }
                    </AccordionPanel>
                  </AccordionItem>)
                }
              </Accordion>
            </div>
          ) : (
            <div className="spinner">
              <Spinner size="xl" color="teal" />
            </div>
          )
          )}
        </CardBody>
      </Card>
    </div >
  );
};

const ReportExplanation = (_props) => {
  const points = _props.points;
  const handleMoreExplanation = _props.handleMoreExplanation

  return (
    <div className="detail">
      {points}
    </div>
  )
}

export default ReportItem;
