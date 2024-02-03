import { faker } from '@faker-js/faker';

const labels = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Saturday'];

export const graphprops = [
  {data:{
    labels,
    datasets: [
      {
        label: 'Week 05',
        data: labels.map(() => faker.datatype.number({ min: 100, max: 150 })),
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
    ],
  },options:{
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: 'Systolic Blood Pressure (mmHg)',
      },
    },
  }},
  {data:{
    labels,
    datasets: [
      {
        label: 'Week 05',
        data: labels.map(() => faker.datatype.number({ min: 100, max: 300 })),
        backgroundColor: 'rgba(88, 99, 255, 0.5)',
      },
    ],
  },options:{
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: 'Weekly Activity (mins)',
      },
    },
  }},
  



]

export const testGraphData = {
    labels,
    datasets: [
      {
        label: 'Week 05',
        data: labels.map(() => faker.datatype.number({ min: 100, max: 150 })),
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
    ],
  };

export const testGraphOptions = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
    title: {
      display: true,
      text: 'Systolic Blood Pressure',
    },
  },
};