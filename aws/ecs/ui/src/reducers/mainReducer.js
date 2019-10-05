const mainReducer = (state = { sampleName: '' }, action) => {
  switch (action.type) {
    case 'SAMPLE_FETCHED_ACTION':
      return Object.assign({}, state, {
        sampleName: action.name
      })
    default:
      return state
  }
}

export default mainReducer
