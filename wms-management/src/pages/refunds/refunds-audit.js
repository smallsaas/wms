import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/refunds-audit';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '审核' },
      ]);
		return <ZEle namespace="refunds-audit" config={config} />
  }