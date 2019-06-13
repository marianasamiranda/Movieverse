import React from "react";
import DatePicker from "react-datepicker";
 
import "react-datepicker/dist/react-datepicker.css";
 
// CSS Modules, react-datepicker-cssmodules.css
// import 'react-datepicker/dist/react-datepicker-cssmodules.css';
 
export default class Datepicker extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      date: new Date()
    };
    this.handleChange = this.handleChange.bind(this);
  }
 
  handleChange(date) {
    this.setState({
      date: date
    });
    this.props.change({target: {name:"birthdate", value:date}})
  }
 
  render() {
    return (
      <DatePicker
        onChange={this.handleChange}
        selected={this.state.date}
        dateFormat="dd/MM/yyyy"
        placeholderText="Birthdate"
      />
    );
  }
}