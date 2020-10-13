import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom';
import Login from "./Login";
import Registration from "./Registration";

function App() {
  return (
    <div className="App">
      <Router>
        <div id='switches'>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/Registration" component={Registration} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
