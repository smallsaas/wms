import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/procurements-submitAudit';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '提交审核' },
      ]);
		return <ZEle namespace="procurements-submitAudit" config={config} />
  }