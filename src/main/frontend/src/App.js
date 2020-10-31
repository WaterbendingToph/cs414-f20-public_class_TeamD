import React from 'react';
import './App.css';
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom';
import Login from "./Login";
import Registration from "./Registration";
import Lobby from "./Lobby/Lobby";
import OngoingMatches from './Lobby/Ongoing Matches/OngoingMatches';

function App() {
  return (
    <div className="App">
      <Router>
        <div id='switches'>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/register" component={Registration} />
            <Route exact path="/lobby" component={Lobby} />
            <Route exact path="/matches" component={OngoingMatches} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
