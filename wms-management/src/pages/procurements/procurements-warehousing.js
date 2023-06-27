import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/procurements-warehousing';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '入库' },
      ]);
		return <ZEle namespace="procurements-warehousing-add" config={config} />
  }