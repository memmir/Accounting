import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    customer: []
  }

  async componentDidMount() {
    const response = await fetch('/v1/customer/72bd89cf-6510-49be-99bc-7dc3e13bd7b6');
    const body = await response.json();
    this.setState({customer: body});
  }
  render() {
    const {customer} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>customer</h2>
                  <div key={customer.id}>
                    {customer.name} {customer.surname}
                  </div>
            </div>
          </header>
        </div>
    );
  }
  /*return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );*/
}

export default App;
