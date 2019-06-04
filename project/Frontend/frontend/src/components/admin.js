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


export default class Admin extends Component {

  constructor(props) {
    super(props)
    this.state = {
      time: 12 * 60, //12 hours
      loading: true
    }
    this.getStats()
    this.getPlots = this.getPlots.bind(this)
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
          }
        }],
        yAxes: [{
          ticks: {
            beginAtZero: true,
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
                {this.buildCard(data.users, labels[this.props.lang].users)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.movies, labels[this.props.lang].movies)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.people, labels[this.props.lang].people)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.media, labels[this.props.lang].media)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.comments, labels[this.props.lang].comments)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.hours, labels[this.props.lang].hours)}
              </Col>
              <Col xs="12" md="6" xl="4">
                {this.buildCard(data.likes, labels[this.props.lang].likes)}
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
          <Col xs="12">
            <Dropdown>
              <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                {labels[this.props.lang].timeRange}
                </Dropdown.Toggle>

              <Dropdown.Menu>
                <Dropdown.Item active={this.state.time === 1} onSelect={() => this.getPlots(1)}>1 minute</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 5} onSelect={() => this.getPlots(5)}>5 minutes</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 30} onSelect={() => this.getPlots(30)}>30 minutes</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 1 * 60} onSelect={() => this.getPlots(1 * 60)}>1 hour</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 6 * 60} onSelect={() => this.getPlots(6 * 60)}>6 hours</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 12 * 60} onSelect={() => this.getPlots(12 * 60)}>12 hours</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 1 * 24 * 60} onSelect={() => this.getPlots(1 * 24 * 60)}>1 day</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 7 * 24 * 60} onSelect={() => this.getPlots(7 * 24 * 60)}>1 week</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 14 * 24 * 60} onSelect={() => this.getPlots(14 * 24 * 60)}>2 weeks</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 30 * 24 * 60} onSelect={() => this.getPlots(30 * 24 * 60)}>1 month</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 6 * 30 * 24 * 60} onSelect={() => this.getPlots(6 * 30 * 24 * 60)}>6 months</Dropdown.Item>
                <Dropdown.Item active={this.state.time === 12 * 30 * 24 * 60} onSelect={() => this.getPlots(12 * 30 * 24 * 60)}>1 year</Dropdown.Item>
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