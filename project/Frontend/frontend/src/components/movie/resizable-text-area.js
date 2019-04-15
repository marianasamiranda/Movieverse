import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import '../../styles/Comment.css';

export default class ResizableTextarea extends Component {
	constructor(props) {
		super(props);
		this.state = {
			value: '',
			rows: 1,
			minRows: 2,
			maxRows: 5,
		};
	}
	
	handleChange = (event) => {
		const textareaLineHeight = 24;
		const { minRows, maxRows } = this.state;
		
		const previousRows = event.target.rows;
  	event.target.rows = minRows;
		
		const currentRows = ~~(event.target.scrollHeight / textareaLineHeight);
    
    if (currentRows === previousRows) {
    	event.target.rows = currentRows;
    }
		
		if (currentRows >= maxRows) {
			event.target.rows = maxRows;
			event.target.scrollTop = event.target.scrollHeight;
		}
    
  	this.setState({
    	value: event.target.value,
      rows: currentRows < maxRows ? currentRows : maxRows,
    });
	};
	
	render() {
		return <Form>
				<Form.Control as="textarea"
					rows={this.state.rows}
					value={this.state.value}
					placeholder={'Add a comment..'}
					onChange={this.handleChange}
				/>
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<Button variant="primary" type="submit" disabled={(this.state.value == '')}>
							Add comment
						</Button>
					</div>
				</div>

			</Form>
	}
}