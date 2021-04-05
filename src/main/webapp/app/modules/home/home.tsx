import './home.scss';
import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { createMuiTheme } from '@material-ui/core/styles';

import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  const theme = createMuiTheme({
    palette: {
      primary: {
        dark: '#2c387e',
        main: '#3f51b5',
        light: '#6573c3',
      },
    },
  });

  return (
    <div className="App">
      <header className="header">
        <div>
          <h1>A website to PDF converter</h1>
          <br />
          <h4>A COMP.SE.120 Project Assignment</h4>
        </div>
      </header>
      <main>
        <input
          type="text"
          id="url-input"
          placeholder="Paste an URL here!"
        />
        <Button
          variant="contained"
          size="small"
          color="primary"
          disableElevation
          onClick={() => {alert("We'll figure this out later")}}
          >
          Convert
        </Button>
      </main>
    </div>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
