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
	
	handleSubmit() {
		this.props.callBackFromParent(this.state.value);

		this.setState({
			value: '',
			rows: 1,
			minRows: 2,
			maxRows: 5,
		});

		this.forceUpdate();
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
				<Form.Control style={{'background-color': 'transparent'}}
					as="textarea"
					rows={this.state.rows}
					value={this.state.value}
					placeholder={'Add a comment..'}
					onChange={this.handleChange}
				/>
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<Button variant="primary" onClick={this.handleSubmit.bind(this)} disabled={(this.state.value === '')}>
							Add comment
						</Button>
					</div>
				</div>

			</Form>
	}
}