import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/transfers-edit';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '编辑' },
      ]);
		return <ZEle namespace="transfers-edit" config={config} />
  }