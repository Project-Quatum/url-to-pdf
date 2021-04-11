import './home.scss';
import Button from '@material-ui/core/Button';
import validator from 'validator';
import axios from 'axios';

import React, { useState } from 'react';

import { connect } from 'react-redux';
import fileDownload from 'js-file-download';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const [url, setURL] = useState('');
  const [isConverting, setConvert] = useState(false);

  const updateURL = event => {
    setURL(event.target.value);
  };

  const convertURL = event => {
    const urlToTransfer = url.trim();

    if (!validator.isURL(urlToTransfer)) {
      alert('Please enter a valid URL address. ');
    } else {
      setConvert(true);
      axios({
        url: '/screenshots',
        method: 'POST',
        responseType: 'blob',
        data: {
          url: urlToTransfer,
        },
      }).then(response => {
        fileDownload(response.data, 'screenshot.pdf');
      });
      setConvert(false);
    }
  };

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
