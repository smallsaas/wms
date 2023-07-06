import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/transfers-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '详情' },
      ]);
		return <ZEle namespace="transfers-view" config={config} />
  }