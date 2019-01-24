import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';
import App from './components/App.js'
import { Provider } from 'react-redux'
import configureStore, { history, sagaMiddleware } from './store/configureStore'
import rootSaga from './sagas'

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

const store = configureStore({ main: { sampleName: '' } });

sagaMiddleware.run(rootSaga)

ReactDOM.render(
  <Provider store={store}>
    <App history={history} />
  </Provider>,
  document.getElementById('root')
);
