import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/procurements-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '库存详情' },
      ]);
		return <ZEle namespace="procurements-view" config={config} />
  }