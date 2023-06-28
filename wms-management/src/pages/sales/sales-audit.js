import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/sales-audit';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '审核' },
      ]);
		return <ZEle namespace="sales-audit" config={config} />
  }