import './home.scss';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { createMuiTheme } from '@material-ui/core/styles';
import validator from 'validator';
import axios from 'axios';

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  const [url, setURL] = useState('');
  const [isConverting, setConvert] = useState(false);

  const updateURL = event => {
    setURL(event.target.value);
  };

  const convertURL = event => {
    const urlToTransfer = '' + url;

    if (!validator.isURL(urlToTransfer)) {
      alert('Please enter a valid URL address. ');
    } else {
      setConvert(true);
      // Haven't tested this, not sure it works!
      /*
      const res = await axios.get("/screenshots", {url: urlToTransfer});
      axios({
        url: res,
        method: 'GET',
        responseType: 'blob',
      }).then((response) => {
        const url = window.URL.createURL(new Blob([response.data]));
        const link = document.createElement.('a');
        link.href = url;
        link.setAttribute('download', 'file.pdf');
        document.body.appendChild(link);
        link();
      });*/
      setConvert(false);
    }
  };

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
        </div>
      </header>
      <main>
        <input type="text" id="urlInputField" placeholder="Paste an URL here" value={url} onChange={updateURL} disabled={isConverting} />
        <Button variant="contained" size="small" color="primary" disableElevation onClick={convertURL} disabled={isConverting}>
          {isConverting ? 'Converting...' : 'Convert'}
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
