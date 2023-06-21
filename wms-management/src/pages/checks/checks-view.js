import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/checks-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '库存盘点详情' },
      ]);
		return <ZEle namespace="checks-view" config={config} />
  }