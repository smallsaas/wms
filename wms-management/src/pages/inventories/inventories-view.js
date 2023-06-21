import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/inventories-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '出入库详情' },
      ]);
		return <ZEle namespace="inventories-view" config={config} />
  }