import React from 'react';
import './App.css';
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom';
import Login from "./Login";
import Registration from "./Registration";
import Lobby from "./Lobby/Lobby";

function App() {
  return (
    <div className="App">
      <Router>
        <div id='switches'>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/registration" component={Registration} />
            <Route exact path="/lobby" component={Lobby} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
