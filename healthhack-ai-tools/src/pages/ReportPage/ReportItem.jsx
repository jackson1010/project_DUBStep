import {
  Card,
  CardBody,
  CardHeader,
  Heading,
  Button,
  Text,
  Flex,
  Spacer,
} from "@chakra-ui/react";
import { ChevronRightIcon } from "@chakra-ui/icons";
import { useState } from "react";

const defaultReport = {
  title: "Lung X Ray",
  date: "24 Jan 2023",
  comments: "Some doctor's commment here",
};

const ReportItem = (_props) => {
  const report = _props.report || defaultReport;
  const [showDetails, setShowDetails] = useState(false);

  const toggleShowDetails = () => {
    showDetails ? setShowDetails(false) : setShowDetails(true);
  };

  return (
    <div style={{ marginTop: "2rem" }}>
      <Card>
        <CardHeader>
          <Flex>
            <div>
              <Heading size="md">{report.title}</Heading>
              <Text>{report.date}</Text>
            </div>
            <Spacer />
            <Button variant="outline" onClick={toggleShowDetails}>
              {showDetails ? "Hide details" : "More details"} &nbsp;
              <ChevronRightIcon />
            </Button>
          </Flex>
        </CardHeader>
        <CardBody paddingTop={"0"}>
          <Text>{report.comments}</Text>
          {showDetails && (
            <>
              <br />
              <Text>Details ....</Text>
            </>
          )}
        </CardBody>
      </Card>
    </div>
  );
};

export default ReportItem;
