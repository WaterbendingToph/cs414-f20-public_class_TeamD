import React from 'react';
import Router from 'react';
import Switch from 'react';
import Route from 'react';
import logo from './logo.svg';
import Registration from "./Registration";
import './App.css';

function App() {
  /// Removed because the component does not yet exist: <Route exact path="/" component={Login} />
  return (
    <div className="App">
      <Router>
        <div id='switches'>
          <Switch>
            <Route exact path="/Registration" component={Registration} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
