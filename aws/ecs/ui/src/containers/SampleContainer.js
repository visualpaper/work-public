import { connect } from 'react-redux'
import Sample from '../components/App'
import { sampleFetchAction } from '../actions'

const mapStateToProps = state => ({
  sampleName: state.main
})

const mapDispatchToProps = dispatch => ({
  fetch: () => dispatch(sampleFetchAction())
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Sample)
