import { Card, CardBody, CardHeader, Heading, Button, Text, Flex, Spacer } from "@chakra-ui/react";
import { ChevronRightIcon } from "@chakra-ui/icons";

const defaultReport = {
    title: 'Lung X Ray',
    date: '24 Jan 2023',
    comments: 'Some doctor\'s commment here',
}

const ReportItem = (_props) => {
    const report = _props.report || defaultReport;

    return (
        <div style={{marginTop: '2rem'}}>
            <Card>
                <CardHeader>
                    <Flex>
                        <div>
                            <Heading size='md'>{report.title}</Heading>
                            <Text>{report.date}</Text>
                        </div>
                        <Spacer />
                        <Button variant='outline'>More details &nbsp;<ChevronRightIcon /></Button>
                    </Flex>
                </CardHeader>
                <CardBody>
                    <Text>{report.comments}</Text>
                </CardBody>
            </Card>
        </div>
    );
};

export default ReportItem;
