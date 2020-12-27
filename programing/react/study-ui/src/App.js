
import Welcome from './component/Welcome'
import Clock from './component/Clock'
import Toggle from './component/Toggle'
import NameForm from './component/NameForm'

function App() {
  return (
    <div>
      <Welcome name="Sara" />
      <Welcome name="Cahal" />
      <Welcome name="Edite" />
      <Clock />
      <Toggle />
      <NameForm />
    </div>
  );
}

export default App;
