
// 라인차트
new Chart(document.querySelector('#line-chart'), {
    type: 'line',
    data: {
        labels: ['a', 'b', 'c', 'd', 'e'],
        datasets: [
            {
                data: [10.5, 11.5, 15.7, 8.5, 13.0],
                label: "최고기온",
                borderColor: "#c45850",
                fill: false
            }, 
            {
                data: [3.1, 5.7, 1.2, 2.4, 3.5],
                label: "최저기온",
                borderColor: "#3e95cd",
                fill: false
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'World population per region (in millions)'
        },
        scales: {
            y: {
                min: 0,
                max: 20
            }
        }
    }
});

// 바차트
new Chart(document.querySelector('#bar-chart'), {
    type: 'bar',
    data: {
        labels: ['a', 'b', 'c', 'd', 'e'],
        datasets: [
            {
                label: "최고기온",
                backgroundColor: "#c45850",
                data: [10.5, 11.5, 15.7, 8.5, 13.0]
            },
            {
                label: "최저기온",
                backgroundColor: "#3e95cd",
                data: [3.1, 5.7, 1.2, 2.4, 3.5]
            }
        ]
    },
    options: {
        legend: { display: true },
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        },
        scales: {
            y: {
                min: 0,
                max: 20
            }
        }
    }
});

//Pie chart
new Chart(document.querySelector('#pie-chart'), {
    type: 'pie',
    data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [{
            label: "Population (millions)",
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
            data: [2478, 5267, 734, 784, 433]
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        }
    }
});

//Radar chart
new Chart(document.querySelector('#radar-chart'), {
    type: 'radar',
    data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [
            {
                label: "1950",
                fill: true,
                backgroundColor: "rgba(179,181,198,0.2)",
                borderColor: "rgba(179,181,198,1)",
                pointBorderColor: "#fff",
                pointBackgroundColor: "rgba(179,181,198,1)",
                data: [8.77, 55.61, 21.69, 6.62, 6.82]
            }, {
                label: "2050",
                fill: true,
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                pointBorderColor: "#fff",
                pointBackgroundColor: "rgba(255,99,132,1)",
                pointBorderColor: "#fff",
                data: [25.48, 54.16, 7.61, 8.06, 4.45]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Distribution in % of world population'
        }
    }
});


//Polar area chart
new Chart(document.querySelector('#polar-chart'), {
    type: 'polarArea',
    data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                data: [2478, 5267, 734, 784, 433]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        }
    }
});

 //Doughnut chart
 new Chart(document.querySelector('#doughnut-chart'), {
    type: 'doughnut',
    data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                data: [2478, 5267, 734, 784, 433]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        }
    }
});

//Grouped bar chart
new Chart(document.querySelector('#bar-chart-grouped'), {
    type: 'bar',
    data: {
        labels: ["1900", "1950", "1999", "2050"],
        datasets: [
            {
                label: "Africa",
                backgroundColor: "#3e95cd",
                data: [133, 221, 783, 2478]
            }, {
                label: "Europe",
                backgroundColor: "#8e5ea2",
                data: [408, 547, 675, 734]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Population growth (millions)'
        }
    }
});

//Mixed chart
new Chart(document.querySelector('#mixed-chart'), {
    type: 'bar',
    data: {
        labels: ["1900", "1950", "1999", "2050"],
        datasets: [{
            label: "Europe",
            type: "line",
            borderColor: "#8e5ea2",
            data: [408, 547, 675, 734],
            fill: false
        }, {
            label: "Africa",
            type: "line",
            borderColor: "#3e95cd",
            data: [133, 221, 783, 2478],
            fill: false
        }, {
            label: "Europe",
            type: "bar",
            backgroundColor: "rgba(0,0,0,0.2)",
            data: [408, 547, 675, 734],
        }, {
            label: "Africa",
            type: "bar",
            backgroundColor: "rgba(0,0,0,0.2)",
            backgroundColorHover: "#3e95cd",
            data: [133, 221, 783, 2478]
        }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Population growth (millions): Europe & Africa'
        },
        legend: { display: false }
    }
});


//Bubble chart
new Chart(document.querySelector('#bubble-chart'), {
    type: 'bubble',
    data: {
        labels: "Africa",
        datasets: [
            {
                label: ["China"],
                backgroundColor: "rgba(255,221,50,0.2)",
                borderColor: "rgba(255,221,50,1)",
                data: [{
                    x: 21269017,
                    y: 5.245,
                    r: 15
                }]
            }, {
                label: ["Denmark"],
                backgroundColor: "rgba(60,186,159,0.2)",
                borderColor: "rgba(60,186,159,1)",
                data: [{
                    x: 258702,
                    y: 7.526,
                    r: 10
                }]
            }, {
                label: ["Germany"],
                backgroundColor: "rgba(0,0,0,0.2)",
                borderColor: "#000",
                data: [{
                    x: 3979083,
                    y: 6.994,
                    r: 15
                }]
            }, {
                label: ["Japan"],
                backgroundColor: "rgba(193,46,12,0.2)",
                borderColor: "rgba(193,46,12,1)",
                data: [{
                    x: 4931877,
                    y: 5.921,
                    r: 15
                }]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        }, scales: {
            yAxes: [{
                scaleLabel: {
                    display: true,
                    labelString: "Happiness"
                }
            }],
            xAxes: [{
                scaleLabel: {
                    display: true,
                    labelString: "GDP (PPP)"
                }
            }]
        }
    }
});