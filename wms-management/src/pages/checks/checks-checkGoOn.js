import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/checks-checking';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '继续盘点' },
      ]);
		return <ZEle namespace="checks-checkGoOn" config={config} />
  }