import React, { Component } from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Dropdown from 'react-bootstrap/Dropdown'
import DropdownButton from 'react-bootstrap/DropdownButton'
import {labels, backend} from '../var'
import Axios from 'axios'
import { getToken } from '../cookies'
import Loading from './aux_pages/loading'
import '../styles/Admin.css'
import { Pie, Line } from 'react-chartjs-2'

const randomColor = () => '#'+Math.floor(Math.random()*16777215).toString(16)

const formatNumber = (n) => {
  const s = n.toString()
  if (s.length >= 4 && s.length <= 6)
    return (Math.round(n / 1000)).toString() + 'K'
  else if (s.length >= 7)
    return (Math.round(n / 100000)).toString() + 'M'
  else
    return n
}

const timeRangeDropdownData = [
  { value: 1, label: '1', type: 'minute' },
  { value: 5, label: '5', type: 'minutes' },
  { value: 30, label: '30', type: 'minutes' },
  { value: 60, label: '1', type: 'hour' },
  { value: 6 * 60, label: '6', type: 'hours' },
  { value: 12 * 60, label: '12', type: 'hours' },
  { value: 24 * 60, label: '1', type: 'day' },
  { value: 7 * 24 * 60, label: '1', type: 'week' },
  { value: 14 * 24 * 60, label: '2', type: 'weeks' },
  { value: 30 * 24 * 60, label: '1', type: 'month' },
  { value: 6 * 60 * 24 * 60, label: '6', type: 'months' },
  { value: 12 * 60 * 24 * 60, label: '1', type: 'year' }
]

const refreshRateDropdownData = [
  { value: 5, label: '5', type: 'seconds' },
  { value: 15, label: '15', type: 'seconds' },
  { value: 30, label: '30', type: 'seconds' },
  { value: 60, label: '1', type: 'minute' },
  { value: 5 * 60, label: '5', type: 'minutes' },
  { value: 30 * 60, label: '30', type: 'minutes' },
]

export default class Admin extends Component {

  constructor(props) {
    super(props)
    this.state = {
      time: 12 * 60, //minutes
      loading: true,
      refreshRate: 5, //seconds,
    }
    this.getStats()
    this.changeRefreshRate = this.changeRefreshRate.bind(this)
    this.getPlots = this.getPlots.bind(this)
  }

  componentDidMount() {
    this.updatePlots = setInterval(() => {
      this.getPlots(this.state.time)
    }, 5 * 1000);
  }

  getStats() {
    Axios.get(backend + '/admin/stats', { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      this.setState({
        data: x.data,
        loading: false,
      })
    })
  }

  getPlots(time) {
    Axios.get(backend + '/admin/stats/plots?time=' + time, { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      let data = this.state.data
      data.plotsData = x.data
      this.setState({
        data: data,
        time: time
      })
    })
  }

  changeRefreshRate(time) {
    if (time < 5)
      time = 5
    clearInterval(this.updatePlots)
    this.updatePlots = setInterval(() => {
      this.getPlots(this.state.time)
    }, time * 1000);
    this.setState({
      refreshRate: time,
    })
  }

  buildCard(title, text) {
    return (
      <Card className="admin-card">
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <Card.Text>
            {text}
          </Card.Text>
        </Card.Body>
      </Card>
    )
  }

  buildLineCharts() {
    let plotsData = {}
    Object.entries(this.state.data.plotsData).forEach(k => {
      plotsData[k[0]] = {}
      plotsData[k[0]].labels = k[1].map(x => new Date(x.timestamp))
      plotsData[k[0]].datasets = [{
        data: k[1].map(x => x.count),
        label: false,
        backgroundColor: randomColor
      }]
    })

    const options = {
      legend: {
        display: false
      },
      elements: {
        line: {
          tension: 0
        }
      },  
      responsive: true,
      scales: {
        xAxes: [{
          type: 'time',
          display: true,
          scaleLabel: {
            display: true,
            labelString: "Date",
          },
          time: {
            max: new Date().getTime(),
            min: new Date().getTime() - (this.state.time * 60 * 1000)
          }
        }],
        yAxes: [{
          ticks: {
            beginAtZero: true,
            callback: (value) => { if (value % 1 === 0) { return value; } }
          },
          display: true,
          scaleLabel: {
            display: true,
            labelString: "Page Views",
          }
        }]
      }
    }
    

    let l = []
    let i = 0
    Object.entries(plotsData).forEach(x => {
      l.push(
        <Col xs="12" md="6" lg="4" key={i++}>
          {this.buildCard(x[0], <Line data={x[1]} options={options} />)}
        </Col>
      )
    })
    return l
  }

  render() {

    if (this.state.loading) {
      return (
        <Loading lang={this.props.lang} />
      )
    }

    const data = this.state.data

    const genderData = {
      labels: ['F', 'M', 'O'],
      datasets : [{
        data: [data.genders.F, data.genders.M, data.genders.O],
        backgroundColor: ['#fc3c8f', '#3c95fc']
      }]
    }

    const countriesData = {
      labels: Object.entries(data.countries).map(x => x[0]),
      datasets : [{
        data: Object.entries(data.countries).map(x => x[1]),
        backgroundColor: Object.entries(data.countries).map(() => randomColor())
      }]
    }

    let timeRangeItems = []
    timeRangeDropdownData.forEach(x => {
      timeRangeItems.push(
        <Dropdown.Item
            key={x.value}
            active={this.state.time === x.value} 
            onSelect={() => this.getPlots(x.value)}>
          {x.label + ' ' + labels[this.props.lang][x.type]}
        </Dropdown.Item>
      )
    })

    let refreshDataItems = []
    refreshRateDropdownData.forEach(x => {
      refreshDataItems.push(
        <Dropdown.Item
            key={x.value}
            active={this.state.refreshRate === x.value}
            onSelect={() => this.changeRefreshRate(x.value)}>
          {x.label + ' ' + labels[this.props.lang][x.type]}
        </Dropdown.Item>
      )
    })

    return (
      <Container fluid className="admin-container container-padding-large">
        <Row>
          <Col xs="12" className="title text-center">
            {labels[this.props.lang].globalStats}
          </Col>
        </Row>
        <Row>
          <Col lg="4">
            <Row>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.users), labels[this.props.lang].users)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.movies), labels[this.props.lang].movies)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.people), labels[this.props.lang].people)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.media), labels[this.props.lang].media)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.comments), labels[this.props.lang].comments)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.hours), labels[this.props.lang].hours)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(formatNumber(data.likes), labels[this.props.lang].likes)}
              </Col>
            </Row>
          </Col>
          <Col lg="8">
            <Row>
              <Col xs="12" md="6" lg="6">
                {this.buildCard(labels[this.props.lang].genders, <Pie data={genderData} />)}
              </Col>
              <Col xs="12" md="6" lg="6 ">
                {this.buildCard(labels[this.props.lang].countries, <Pie data={countriesData} />)}
              </Col>
            </Row>
          </Col>
        </Row>
        <Row className="text-center">
          <Col xs="6" className="text-right">
            <Dropdown>
              <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                {labels[this.props.lang].timeRange}
                </Dropdown.Toggle>

              <Dropdown.Menu>
                {timeRangeItems}
              </Dropdown.Menu>
            </Dropdown>
          </Col>
          <Col xs="6" className="text-left">
            <Dropdown>
              <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                {labels[this.props.lang].updateInterval}
              </Dropdown.Toggle>
              <Dropdown.Menu>
                {refreshDataItems}
              </Dropdown.Menu>
            </Dropdown>
          </Col>
        </Row>
        <Row>
          {this.buildLineCharts()}
        </Row>
      </Container>
    )
  }

}