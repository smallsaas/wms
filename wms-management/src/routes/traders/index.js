import React, { PureComponent } from 'react';
import { connect } from 'dva';

import { GMApp } from 'kqd-general';
import config from './config';
import Query from './Query';

@connect(({ traders, loading }) => ({
  modelStatus: traders,
  namespace: 'traders',
  loading: loading.models.traders,
}))
export default class Index extends PureComponent {
  render() {
    return (
      <GMApp {...this.props}
        config={config}
        routerMap={{
          query: Query,
        }}
      />
    );
  }
}