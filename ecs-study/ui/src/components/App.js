import React from 'react';
import { AppConfig } from './../config/config.js';

class Sample extends React.Component {
	constructor(props) {
		super(props);
		this.state = { name: [] };
		this.fetchMethod = this.fetchMethod.bind(this);
	}

	fetchMethod() {
		return fetch(AppConfig.url + "/ecs-server", {
			method: 'GET',
			headers: {
				'Content-Type': "application/json"
			}
		})
			.then(response => response.json())
			.catch(e => console.error(e.toString()));
	}

	componentDidMount() {
		this.fetchMethod().then(response => {
			this.setState({
				name: response.name
			});
		});
	}

	render() {
		return (
			<div>
				{this.state.name}
			</div>
		);
	}
}

export default Sample
