import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/index';

export default function () {
  useBreadcrumb([
    { title: '主页', path: '/' },
    { title: '商品库存管理' },
  ]);
	return <ZEle namespace="skus" config={config} />
  }