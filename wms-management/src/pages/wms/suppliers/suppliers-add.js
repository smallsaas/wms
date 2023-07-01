import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/suppliers-add';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '新增' },
      ]);
		return <ZEle namespace="suppliers-add" config={config} />
  }