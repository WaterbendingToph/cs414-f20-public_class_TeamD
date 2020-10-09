import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom';
import Login from "./Login";

function App() {
  return (
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

      <Router>
          <div id='switches'>
            <Switch>
                <Route exact path="/" component={Login} />
            </Switch>
          </div>
      </Router>
    </div>
  );
}

export default App;
