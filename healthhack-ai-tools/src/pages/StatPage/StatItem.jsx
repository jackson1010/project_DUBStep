import {
    Card,
    CardBody,
    CardHeader,
    Text,
    Heading,
  } from "@chakra-ui/react";

const StatItem = ({record}) => {
    return (
    <div style={{ marginTop: "2rem" }}>
    <Card>
      <CardHeader>
      <Heading size="md">Record Id: {record.id}</Heading>
      </CardHeader>
      <CardBody paddingTop={"0"}>
        <Text>Systolic Blood Pressure: {record.sysBp}</Text>
        <Text>Diastolic Blood Pressure: {record.diaBp}</Text>
        <Text>Cholestrol: {record.chol}</Text>
        <Text>Exercise Time: {record.exerciseTime}</Text>
      </CardBody>
    </Card>
  </div>
    )

};


export default StatItem;