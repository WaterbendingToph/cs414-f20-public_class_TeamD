import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom';
import Login from "./Login";

function App() {
  return (
    <div className="App">
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
