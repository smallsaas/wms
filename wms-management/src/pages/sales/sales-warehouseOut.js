import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/sales-warehouseOut';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '出库' },
      ]);
		return <ZEle namespace="sales-warehouseOut-add" config={config} />
  }