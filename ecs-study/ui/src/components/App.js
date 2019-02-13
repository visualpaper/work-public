import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Link, Route } from "react-router-dom";
import ExamplePage from './Example.js';
import BasicPage from './Basic.js';

const topPage = () => <div><h1>Top Page</h1>ここがトップページです</div>
const samplePage = () => <div><h1>Top Page</h1>ここがSampleです</div>
const NoMatch = () => <div> no match </div>

class Sample extends React.Component {
	constructor(props) {
		super(props);
		this.state = { name: [] };
		this.fetchMethod = this.fetchMethod.bind(this);
	}

	fetchMethod() {
		return fetch("/app/ecs-server", {
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
			<Router basename={process.env.REACT_APP_DEV_BASE_URL} history={this.props.history} >
				<div style={{ width: '500px', textAlign: 'left' }}>
					<ul style={{ display: 'flex' }}>
						<li style={{ display: 'inline', width: '100px' }}><Link to='/'>top</Link></li>
						<li style={{ display: 'inline', width: '100px' }}><Link to='/sample'>sample</Link></li>
						<li style={{ display: 'inline', width: '100px' }}><Link to='/example'>reactstrap</Link></li>
						<li style={{ display: 'inline', width: '100px' }}><Link to='/formik'>formik</Link></li>
					</ul>

					<div>{this.state.name}</div>
					<div style={{ marginLeft: '50px' }}>
						<Switch>
							<Route path='/' exact component={topPage} />
							<Route path='/sample' exact component={samplePage} />
							<Route path='/example' exact component={ExamplePage} />
							<Route path='/formik' exact component={BasicPage} />
							<Route exact component={NoMatch} />
						</Switch>
					</div>
				</div>
			</Router>
		);
	}
}

export default Sample
