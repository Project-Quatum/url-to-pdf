import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <p>Project Quantum. All rights reserved</p>
      </Col>
    </Row>
  </div>
);

export default Footer;
