import { call, put, takeEvery, delay } from 'redux-saga/effects'
import { AppConfig } from './../config/config.js';

export function* fetchAync() {
  yield delay(1000)
  const response = yield call(
    fetchName
  );

  yield put({ type: 'SAMPLE_FETCHED_ACTION', sampleName: response.name })
}

function fetchName() {
  return fetch(AppConfig.url + "/ecs-server", {
    method: 'GET',
    headers: {
      'Access-Control-Allow-Origin': '*'
    }
  })
    .then(response => response.json())
    .catch(e => console.error(e.toString()));
}

export default function* rootSaga() {
  yield takeEvery('SAMPLE_FETCH_ACTION', fetchAync)
}
