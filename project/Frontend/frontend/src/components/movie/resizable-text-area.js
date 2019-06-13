import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { labels } from '../../var'
import OopsModal from '../aux_pages/oops-modal'
import '../../styles/Comment.css';

export default class ResizableTextarea extends Component {
	constructor(props) {
		super(props);
		this.state = {
			noAuth: this.props.noAuth,
			showModal: false,
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

	handleShowNotLoggedInModal() {
    if(this.state.noAuth) {
      this.setState({
        showModal: true
      })
    }
  }

  handleCloseNotLoggedInModal() {
    this.setState({
      showModal: false
    })
  }
	
	render() {

		let message;

		if(this.state.noAuth) {
      message = <p>
        { labels[this.props.lang].youNeed } <a href="/">{ labels[this.props.lang].registeredOrLoggedIn }</a> { labels[this.props.lang].toBeAbleComment }
      </p>
    }

		return <>
		  <OopsModal showModal={ this.state.showModal } handleClose={this.handleCloseNotLoggedInModal.bind(this)} message={ message } />
			<Form>
				<Form.Control style={{'backgroundColor': 'transparent'}}
					as="textarea"
					rows={ this.state.rows }
					value={ this.state.value }
					placeholder={ labels[this.props.lang].addCommentPlaceholder }
					onChange={ this.handleChange }
					readOnly={ this.state.noAuth }
					onClick= { this.handleShowNotLoggedInModal.bind(this) }
					maxLength="255"
				/>
				<div className="d-flex flex-row-reverse">
					<div className="p-2">
						{ this.state.noAuth === false &&
							<Button variant="primary" onClick={ this.handleSubmit.bind(this) } disabled={ (this.state.value === '') }>
								{ labels[this.props.lang].addComment }
							</Button>
						}
						{ this.state.noAuth === true &&
							<Button variant="primary" disabled={ true } >
								{ labels[this.props.lang].addComment }
							</Button>
						}
					</div>
				</div>
			</Form>
		</>
	}
}